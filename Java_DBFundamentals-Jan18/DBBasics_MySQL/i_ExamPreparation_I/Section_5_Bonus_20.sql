-- 20. Categories Revision
SELECT 
	c.`name`,
    COUNT(r.id) AS 'reports_number',
    CASE
		WHEN (
			SELECT 
				COUNT(*) 
				FROM reports AS rr 
				WHERE
					rr.status_id = (SELECT id FROM `status` WHERE label = 'waiting') 
					AND rr.category_id = c.id
			) > (
			SELECT
				COUNT(*) 
				FROM reports AS rr 
				WHERE
					rr.status_id = (SELECT id FROM `status` WHERE label = 'in progress') 
					AND rr.category_id = c.id
			) THEN 'waiting'
				WHEN (
			SELECT 
				COUNT(*) 
				FROM reports AS rr 
				WHERE
					rr.status_id = (SELECT id FROM `status` WHERE label = 'in progress') 
					AND rr.category_id = c.id
			) > (
			SELECT
				COUNT(*) 
				FROM reports AS rr 
				WHERE
					rr.status_id = (SELECT id FROM `status` WHERE label = 'waiting') 
					AND rr.category_id = c.id
			) THEN 'in progress'
		ELSE 'equal'
	END as main_status
FROM categories AS c
JOIN reports AS r ON r.category_id = c.id
JOIN `status` AS s ON r.status_id = s.id
WHERE s.label IN ('waiting', 'in progress')
GROUP BY c.`name`;