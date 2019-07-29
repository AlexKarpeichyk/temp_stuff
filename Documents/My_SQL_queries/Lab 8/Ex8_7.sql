SELECT Artist_name AS artist, track_title AS trackTitle, title AS cdTitle, runtime FROM
	(CD_Artist JOIN CD_CD ON	
		CD_Artist.id_number = CD_CD.is_fronted_by)
		JOIN CD_Track ON
			CD_CD.catalog_no = CD_Track.cd_no
	ORDER BY artist_name AND track_no;