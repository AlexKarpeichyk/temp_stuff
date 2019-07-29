SELECT labelname
FROM CD_RecordLabel
WHERE (SELECT COUNT(title) FROM CD_CD WHERE labelname = released_by) >= 2;