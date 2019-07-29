DELIMITER $$
DROP PROCEDURE IF EXISTS setBonuses
$$
CREATE PROCEDURE setBonuses(bonus_date DATE)
BEGIN 
DECLARE done INT DEFAULT 0;
DECLARE id CHAR(9); 
DECLARE sales DECIMAL(7, 0);
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
$$

CALL setBonuses('807897');