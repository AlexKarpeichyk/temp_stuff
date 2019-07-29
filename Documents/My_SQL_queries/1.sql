/*SELECT a.salesPerson_id AS id, order_no, avg_sales, mx FROM 
	((SELECT salesPerson_id, order_no FROM 
	  FW_Places
	  GROUP BY quantity
	  ORDER BY order_no) a LEFT OUTER JOIN (SELECT salesPerson_id, ROUND(AVG(quantity), 0) AS avg_sales, MAX(quantity) AS mx FROM 
														 FW_Places														 
														 GROUP BY salesPerson_id) b
								  ON a.salesPerson_id = b.salesPerson_id)
WHERE order_no IN (SELECT order_no FROM FW_Places GROUP BY order_no HAVING COUNT(order_no) >= 2);*/

/*
SELECT salesPerson_id AS id, AVG(quantity) AS avg_sales, MAX(quantity) AS max_cosales FROM
FW_Places
GROUP BY id;*/

SELECT avg_q.salesPerson_id AS id, avg_q.avg_sales, max_q.max_cosales FROM
	((SELECT salesPerson_id, ROUND(AVG(quantity), 0) AS avg_sales, order_no FROM
	FW_Places
	WHERE order_no IN (SELECT order_no FROM FW_Places GROUP BY order_no HAVING COUNT(order_no) >= 2)
	GROUP BY salesPerson_id) avg_q 
	JOIN
	(SELECT salesPerson_id, MAX(quantity) AS max_cosales, order_no FROM
	FW_Places
	WHERE order_no IN (SELECT order_no FROM FW_Places GROUP BY order_no HAVING COUNT(order_no) >= 2)
	GROUP BY salesPerson_id) max_q ON
	avg_q.salesPerson_id <> max_q.salesPerson_id AND avg_q.order_no = max_q.order_no);