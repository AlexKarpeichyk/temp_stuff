SELECT a.salesPerson_id AS id, avg_sales, c.salesPerson_id, max_cosales FROM
((SELECT salesPerson_id, ROUND(AVG(quantity), 0) AS avg_sales FROM
FW_Places
GROUP BY salesPerson_id) a JOIN (SELECT order_no, salesPerson_id FROM 
										   FW_Places
										   WHERE order_no IN (SELECT order_no FROM 
										  							 FW_Places 
																	 GROUP BY order_no 
																	 HAVING COUNT(salesPerson_id) > 1)) b ON a.salesPerson_id = b.salesPerson_id) 
CROSS JOIN
((SELECT salesPerson_id, MAX(quantity)AS max_cosales FROM
FW_Places
GROUP BY salesPerson_id) c JOIN (SELECT order_no, salesPerson_id FROM 
										   FW_Places
										   WHERE order_no IN (SELECT order_no FROM 
										  							 FW_Places 
																	 GROUP BY order_no 
																	 HAVING COUNT(salesPerson_id) > 1)) d ON c.salesPerson_id = d.salesPerson_id) 
ON b.order_no = d.order_no AND a.salesperson_id <> c.salesPerson_id
WHERE avg_sales > max_cosales
		