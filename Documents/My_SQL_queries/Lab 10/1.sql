SELECT catalog_no, title, COUNT(track_no) FROM
CD_CD LEFT OUTER JOIN CD_Track ON 
catalog_no = cd_no
GROUP BY catalog_no
HAVING COUNT(track_no) > (SELECT AVG(v.c) FROM (SELECT COUNT(track_no) AS c FROM 
	  							(CD_CD LEFT OUTER JOIN CD_Track ON 
								catalog_no = cd_no) LEFT OUTER JOIN CD_Genre ON 
								CD_CD.catalog_no = CD_Genre.catalog_no
								WHERE genre = 'Pop'
								GROUP BY CD_CD.catalog_no) v);