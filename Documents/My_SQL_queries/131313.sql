SELECT salesPerson_id AS id, avg_sales, max_cosales FROM 
(SELECT av.salesPerson_id, avg_sales, max_cosales, av.order_no FROM 
(SELECT FW_Places.salesPerson_id, avg_sales, FW_Places.order_no FROM
(FW_Places LEFT OUTER JOIN (SELECT salesPerson_id, order_no, ROUND(AVG(quantity), 0) AS avg_sales FROM 
									FW_Places GROUP BY salesPerson_id) a ON FW_Places.salesPerson_id = a.salesPerson_id)) av
CROSS JOIN 
(SELECT FW_Places.salesPerson_id, max_cosales, FW_Places.order_no FROM
(FW_Places LEFT OUTER JOIN (SELECT salesPerson_id, order_no, MAX(quantity) AS max_cosales FROM 
									FW_Places GROUP BY salesPerson_id) a ON FW_Places.salesPerson_id = a.salesPerson_id)) ma
ON av.salesPerson_id <> ma.salesperson_id AND av.order_no = ma.order_no
ORDER BY avg_sales DESC, max_cosales DESC) overall
GROUP BY order_no
HAVING avg_sales > max_cosales;