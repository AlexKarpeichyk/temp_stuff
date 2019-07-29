SELECT labelname, COUNT(title) AS releases
FROM CD_RecordLabel LEFT OUTER JOIN CD_CD ON labelname = released_by
GROUP BY labelname;
		
	
	