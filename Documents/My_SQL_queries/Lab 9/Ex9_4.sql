SELECT artist_name FROM CD_Artist
WHERE NOT EXISTS (SELECT is_fronted_by FROM CD_CD WHERE id_number = is_fronted_by)	
ORDER BY artist_name;