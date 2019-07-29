SELECT artist_name
FROM CD_Artist NATURAL RIGHT OUTER JOIN CD_CD
WHERE id_number <> is_fronted_by
GROUP BY artist_name;
	