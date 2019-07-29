-- Answer to the 2nd Database Assignment 2017/18
--
-- 164835 
-- Please insert your candidate number in the line above
-- Do not remove ANY lines of this template.


-- In each section below put your answer in a new line BELOW the corresponding comment.
-- Use ONE SQL statement ONLY per question.
-- If you don’t answer a question just leave the corresponding space blank. 
-- Anything that does not run in SQL you have to put in comments. Your code should never throw a syntax error.
-- Questions with syntax errors will receive zero marks. 

-- DO NOT REMOVE ANY LINE FROM THIS FILE.

-- START OF ASSIGNMENT CODE

-- @@01
CREATE TABLE FW_Lorry (
	vehicleRegNo	CHAR(7) PRIMARY KEY,
	make				ENUM('Ashok', 'Ford', 'Hyundai', 'Iveco', 'MAN', 'Mercedes-Benz', 'Scania', 'Skoda', 'Tata', 'Volvo'),
	model				VARCHAR(30),
	maxLoad			DECIMAL(3, 1) UNSIGNED DEFAULT 40.0,
	accessories		SET('Combination', 'Dual', 'EOBR', 'Tandem')
);


-- @@02
CREATE TABLE FW_TransportRequirement (
	number					TINYINT UNSIGNED,
	order_no					INT(10) UNSIGNED NOT NULL,
	lorry						VARCHAR(7),
	transport_quantity	DECIMAL(3, 1) UNSIGNED DEFAULT '0',
	PRIMARY KEY(number, order_no),
	CONSTRAINT order_no_fk_ FOREIGN KEY (order_no) REFERENCES FW_SalesOrder(order_no)
		ON DELETE CASCADE		ON UPDATE CASCADE,
	CONSTRAINT lorry_fk_ FOREIGN KEY (lorry) REFERENCES FW_Lorry(vehicleRegNo)
		ON DELETE SET NULL
);


-- @@03
ALTER TABLE FW_Person
	ADD COLUMN dob DATE;
	
	
-- @@04
UPDATE FW_Person
	SET house_no = TRIM(LEADING '0' FROM house_no)
	WHERE house_no LIKE '0%';


-- @@05
UPDATE FW_PhoneNumbers
	SET phoneNum = ''
	WHERE phoneNum = '01273007007' AND
			owner IN (SELECT person_id FROM FW_Client);


-- @@06
SELECT salesPerson_id FROM FW_Places 
WHERE quantity > 200
GROUP BY salesPerson_id;


-- @@07
SELECT FW_SalesPerson.person_id, lastName AS lastname, monthly_sales_target FROM 
FW_SalesPerson NATURAL JOIN FW_Person
ORDER BY lastName, monthly_sales_target DESC;


-- @@08
SELECT COUNT(order_no) FROM FW_SalesOrder
WHERE order_date BETWEEN (DATE_SUB(CURDATE(), INTERVAL 20 DAY)) AND CURDATE();


-- @@09
SELECT FW_Client.person_id, firstName AS firstname, lastName AS lastname FROM 
FW_Client NATURAL JOIN FW_Person
WHERE LEFT(firstName, 2) = BINARY LEFT(lastName, 2) AND LENGTH(firstName) > 1 AND LENGTH(lastName) > 1;


-- @@10
SELECT FW_SalesOrder.order_no, (SUM(quantity)*price) AS total FROM
FW_Places RIGHT OUTER JOIN FW_SalesOrder ON
	FW_SalesOrder.order_no = FW_Places.order_no
GROUP BY order_no
ORDER BY total DESC;


-- @@11
SELECT person_id, CONCAT(LEFT(firstName, 1), ' ', lastName) AS name, IFNULL(SUM(quantity*price), 0) AS quarterly_sales FROM 
(FW_Person NATURAL JOIN FW_SalesPerson) LEFT OUTER JOIN 
	(SELECT * FROM 
	FW_Places NATURAL JOIN FW_SalesOrder 
	WHERE QUARTER(order_date) = QUARTER(NOW()) AND 
			YEAR(order_date) = YEAR(NOW())) q ON 
	person_id = salesPerson_id
GROUP BY person_id;


-- @@12
SELECT salesPerson_id AS person_id, CONCAT((ROUND(AVG(contribution), 0)), '%') AS avg_vol FROM
	(SELECT salesPerson_id, (quantity/total*100) AS contribution FROM
	FW_Places NATURAL JOIN	(SELECT order_no, SUM(quantity) AS total FROM 
									FW_Places GROUP BY order_no) quantities_per_order
	GROUP BY quantity) cont
GROUP BY person_id;


-- @@13
SELECT salesPerson_id AS id, avg_sales, max_cosales FROM 
-- Table of averag sales
(SELECT av.salesPerson_id, avg_sales, max_cosales, av.order_no FROM 
(SELECT FW_Places.salesPerson_id, avg_sales, FW_Places.order_no FROM
(FW_Places LEFT OUTER JOIN (SELECT salesPerson_id, order_no, ROUND(AVG(quantity), 0) AS avg_sales FROM 
									FW_Places GROUP BY salesPerson_id) a ON FW_Places.salesPerson_id = a.salesPerson_id)) av								
CROSS JOIN 
-- Table of maximum sales
(SELECT FW_Places.salesPerson_id, max_cosales, FW_Places.order_no FROM
(FW_Places LEFT OUTER JOIN (SELECT salesPerson_id, order_no, MAX(quantity) AS max_cosales FROM 
									FW_Places GROUP BY salesPerson_id) b ON FW_Places.salesPerson_id = b.salesPerson_id)) ma
									
ON av.salesPerson_id <> ma.salesperson_id AND av.order_no = ma.order_no

ORDER BY avg_sales DESC, order_no) overall
GROUP BY order_no
HAVING avg_sales > max_cosales;


-- Do not write any DELIMITER command in the submission.
-- For Q14 and Q15 OMIT the termination symbol at the end of your function/procedure declaration


-- @@14
CREATE FUNCTION individualSalesUpTo (person_id CHAR(9), from_date DATE, to_date DATE) RETURNS INTEGER 
BEGIN
DECLARE turnover INTEGER;
IF person_id IN (SELECT person_id FROM FW_SalesPerson) THEN
	IF from_date <> '0000-00-00' AND to_date <> '0000-00-00' THEN
		IF DATEDIFF(from_date, to_date) < 0 THEN 
			SET turnover = (SELECT SUM(quantity*price) FROM
								FW_Places LEFT OUTER JOIN FW_SalesOrder ON FW_Places.order_no = FW_SalesOrder.order_no
								WHERE order_date BETWEEN from_date AND to_date AND salesPerson_id = person_id
								GROUP BY salesperson_id);
			RETURN turnover;
		ELSE 
			RETURN 0;
		END IF;
	ELSE 
		RETURN -2;
	END IF;
ELSE 
	RETURN -1;
END IF;
END


-- @@15
CREATE PROCEDURE setBonuses (bonus_date DATE)
BEGIN 
DECLARE done INT DEFAULT 0;
DECLARE id CHAR(9); 
DECLARE sales INT;
DECLARE target_sales DECIMAL(7, 0);
DECLARE bonus DECIMAL(7, 0);
DECLARE b_date DATE;
IF (bonus_date <> '0000-00-00') THEN
	BEGIN
	DECLARE c Cursor FOR SELECT person_id, IFNULL(SUM(quantity*price), 0) AS current_sales, monthly_sales_target, IFNULL(bonusAmount, 0) AS bonus_amount, bonusDate FROM 
								FW_SalesPerson LEFT OUTER JOIN (SELECT * FROM 
																		 FW_Places NATURAL JOIN FW_SalesOrder 
																		 WHERE MONTH(order_date) = MONTH(bonus_date)) q ON person_id = salesPerson_id
																		 GROUP BY person_id;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
	OPEN c;
	REPEAT 
	FETCH c INTO id, sales, target_sales, bonus, b_date;
		IF NOT done THEN
			IF MONTH(b_date) < MONTH(bonus_date) OR b_date IS NULL THEN
				IF sales >= target_sales AND bonus = 0 THEN 
					UPDATE FW_SalesPerson
						SET bonusAmount = ROUND((0.1*(sales - target_sales)), 0),
							 bonusDate = LAST_DAY(bonus_date)
						WHERE person_id = id;
				ELSEIF sales >= target_sales AND bonus <> 0 THEN
					UPDATE FW_SalesPerson
						SET bonusAmount = ROUND((0.15*(sales - target_sales)), 0),
							 bonusDate = LAST_DAY(bonus_date)
						WHERE person_id = id;
				ELSE 
					UPDATE FW_SalesPerson
						SET bonusAmount = 0
						WHERE person_id = id;
				END IF;
			END IF;			
		END IF;
	UNTIL done END REPEAT;
	CLOSE c;
	END;	 
ELSE
	CALL date();
END IF;
END

-- END OF ASSIGNMENT CODE
