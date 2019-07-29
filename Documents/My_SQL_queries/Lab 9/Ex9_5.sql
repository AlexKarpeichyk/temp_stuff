SELECT catalog_no, IFNULL(MAX(track_no), 0) AS Total_Track_No, IFNULL(SEC_TO_TIME(SUM(TIME_TO_SEC(runtime))), 0) AS Total_Running_Time FROM 
CD_CD LEFT OUTER JOIN CD_Track ON catalog_no = cd_no
GROUP BY catalog_no;