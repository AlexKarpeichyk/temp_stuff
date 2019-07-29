SELECT released_by AS labelName, COUNT(title) AS releases FROM
	CD_CD
	GROUP BY released_by
	HAVING releases >= 2
	ORDER BY releases DESC;
	