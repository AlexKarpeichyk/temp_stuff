SELECT artist_name, title, publication_date
	FROM CD_Artist JOIN CD_CD ON
		CD_Artist.id_number = CD_CD.is_fronted_by
		WHERE CD_CD.publication_date >= (DATE_SUB(CURDATE(), INTERVAL 10 YEAR)) 
		AND CD_CD.publication_date < CURDATE()
		ORDER BY artist_name;