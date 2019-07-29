DELIMITER $$
DROP FUNCTION IF EXISTS individualSalesUpTo 
$$
CREATE FUNCTION individualSalesUpTo (person_id CHAR(9), from_date DATE, to_date DATE) RETURNS INTEGER 
BEGIN
DECLARE turnover INTEGER;
IF (person_id IN (SELECT person_id FROM FW_SalesPerson)) THEN
	IF (from_date <> '0000-00-00' AND to_date <> '0000-00-00') THEN
		IF (DATEDIFF(from_date, to_date) < 0) THEN 
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
$$

SELECT individualSalesUpTo('ZAS10158U', '2016-11-04', '2016-11-01') AS turnover;