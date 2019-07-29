SELECT AVG(releases) AS averageReleases FROM 
	(SELECT COUNT(title) AS releases FROM 
		CD_RecordLabel LEFT OUTER JOIN CD_CD ON labelname = released_by 
		GROUP BY labelname) AS stuff;