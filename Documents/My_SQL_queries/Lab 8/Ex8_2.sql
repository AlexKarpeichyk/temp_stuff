SELECT title, artist_name
	FROM CD_CD JOIN CD_Artist ON
		CD_CD.is_fronted_by = CD_Artist.id_number
		ORDER BY title;
	