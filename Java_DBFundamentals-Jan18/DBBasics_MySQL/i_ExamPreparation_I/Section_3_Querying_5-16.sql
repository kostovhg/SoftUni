-- 5. Users by Age
SELECT u.username, u.age
FROM users AS u
ORDER BY u.age ASC, u.username DESC;

-- 6. Unassigned Reports
SELECT r.description, r.open_date
FROM reports AS r
WHERE employee_id IS NULL
ORDER BY r.open_date ASC, r.description ASC;

-- 7. Employees & Reports
SELECT 
	e.first_name,
    e.last_name,
    r.description,
    DATE_FORMAT(r.open_date, '%Y-%m-%d') AS 'open_date'
FROM reports AS r
	JOIN employees AS e
    ON r.employee_id = e.id
WHERE r.employee_id IS NOT NULL
ORDER BY e.id ASC, r.open_date ASC, r.id ASC;

-- 8. Most reported Category
SELECT 
	c.`name` AS 'category_name',
    COUNT(r.id) AS 'reports_number'
FROM categories AS c 
	JOIN reports AS r
	ON c.id = r.category_id
GROUP BY c.`name`
ORDER BY `reports_number` ASC, `category_name` ASC;

-- 9. Employees in Category !
SELECT 
	c.`name` AS 'category_name',
    COUNT(e.id) AS 'employees_number'
FROM categories AS c 
	INNER JOIN departments AS d
    ON d.id = c.department_id
		JOIN employees AS e
		ON d.id = e.department_id
GROUP BY c.`name`
ORDER BY `category_name` ASC;

-- 10. Birthday Report
SELECT DISTINCT
	c.`name`
FROM categories AS c
	JOIN reports AS r ON r.category_id = c.id
		JOIN users AS u ON u.id = r.user_id
WHERE
	MONTH(r.open_date) = MONTH(u.birthdate) 
	AND
    DAY(r.open_date) = DAY(u.birthdate)
ORDER BY c.`name`;

-- 11. User per Employee
SELECT
	CONCAT_WS(' ', e.first_name, e.last_name) AS 'first_name',
    COUNT(r.user_id) AS 'users_count'
FROM employees AS e
	LEFT JOIN reports AS r ON e.id = r.employee_id
GROUP BY e.id
ORDER BY `users_count` DESC, `first_name` ASC;

-- 12. Emergency Patrol
SELECT
	r.open_date,
    r.description,
    u.email
FROM
	reports AS r
    JOIN categories AS c ON r.category_id = c.id
	JOIN departments AS d ON c.department_id = d.id
	JOIN users AS u ON r.user_id = u.id
WHERE
	r.close_date IS NULL 
    AND
    (LENGTH(r.description) > 20 AND r.description LIKE ('%str%'))
    AND
    d.`name` IN ('Infrastructure', 'Emergency', 'Roads Maintenance')
ORDER BY
	r.open_date,
    u.email,
    u.id;
    
-- 13. Numbers Coincidence
SELECT DISTINCT
	u.username
FROM users AS u
	JOIN reports AS r ON r.user_id = u.id 
WHERE
	r.category_id IN (LEFT(u.username, 1), RIGHT(u.username, 1))
ORDER BY
	u.username;
    
-- 14. Open/Closed Statistics
SELECT 
	`name`, CONCAT_WS('/', cnt_closed, cnt_open) AS 'closed_open_reports'
FROM
	(SELECT
		CONCAT_WS(' ', e.first_name, e.last_name) AS 'name',
		COUNT(
			CASE
				WHEN YEAR(close_date) = 2016 THEN 'closed'
				WHEN YEAR(open_date) < 2016 AND YEAR(close_date) = 2016 THEN 'closed'
			END) cnt_closed,
		COUNT(
				CASE
					WHEN YEAR(open_date) = 2016 THEN 'opened'
				END) cnt_open
	FROM reports AS r
		JOIN employees AS e ON r.employee_id = e.id 
	GROUP BY `name`
	HAVING `cnt_open` > 0 OR cnt_closed > 0) select_count
ORDER BY select_count.`name`
;

-- 15. Average Closing Time
SELECT
	tmp.`department_name`,
    ifnull(tmp.`average`, 'no info') AS 'average_duration'
FROM
	(SELECT
		r.id,
		d.`name` AS 'department_name',
		FLOOR(AVG(datediff(r.close_date, r.open_date))) AS 'average'
	FROM departments AS d
	JOIN categories AS c ON c.department_id = d.id
	JOIN reports AS r ON r.category_id = c.id
	GROUP BY d.`name`) AS tmp
;

-- 16. Most Reported Category
SELECT
	d.`name`,
    c.`name` AS 'category_name',
	ROUND(
		(COUNT(r.id) /
        (SELECT COUNT(r.id) FROM reports as r
			JOIN categories as c ON r.category_id = c.id
			WHERE c.department_id = d.id)) 
		* 100, 0) AS percentage
FROM reports as r
JOIN categories AS c ON r.category_id = c.id
JOIN departments AS d ON c.department_id = d.id
GROUP BY c.`name`
ORDER BY d.`name` ASC, c.`name` ASC, percentage ASC;
