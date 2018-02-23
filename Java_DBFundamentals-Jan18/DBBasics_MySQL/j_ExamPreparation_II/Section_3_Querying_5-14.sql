-- 05. Users
SELECT 
	u.`id`,
    u.`username`,
    u.`email`
FROM `users` AS u
ORDER BY u.`id`;

-- 06. Root Categories
SELECT
	c.`id`,
    c.`name`
FROM `categories` AS c
WHERE c.`parent_id` IS NULL
ORDER BY c.`id` ASC;

-- 07. Well Tested Problems
SELECT p.`id`, p.`name`, p.`tests`
FROM `problems` AS p
WHERE 
	p.`tests` > p.`points`
AND
	p.`name` REGEXP ('^[^ ]+ [^ ]+$')
ORDER BY p.`id` DESC;

-- 08. Full Path Problems
SELECT 
	p.`id`,
    CONCAT_WS(' - ', cat.`name`, c.`name`, p.`name`) AS 'full_path' 
FROM `problems` AS p
JOIN `contests` AS c ON p.`contest_id` = c.`id`
JOIN `categories` AS cat ON c.`category_id` = cat.`id`
ORDER BY p.`id`;

-- 09. Leaf Categories
SELECT
	c.`id`,
    c.`name`
FROM `categories` AS c
LEFT OUTER JOIN `categories` AS parent
ON c.`id` = parent.`parent_id`
WHERE parent.`parent_id` IS NULL
ORDER BY c.`name` ASC, c.`id` ASC;

-- 10. Mainstream Passwords
SELECT DISTINCT
	u.`id`, u.`username`, u.`password`
FROM `users` AS u
JOIN `users` as uu ON u.`password` = uu.`password`
WHERE u.`id` <> uu.`id`
ORDER BY u.`username`, u.`id`;
--
SELECT u.`id`, u.`username`, u.`password`
FROM `users` AS u
WHERE u.`password` IN (
	SELECT du.`password` FROM `users` AS du
    WHERE du.`id` <> u.`id`)
ORDER BY u.`username`ASC, u.`id` ASC;

-- 11. Most Participated Contests
SELECT * 
FROM
	(SELECT
		c.`id`,
		c.`name`,
		COUNT(uc.`user_id`) as 'contestants'
	FROM
		contests AS c
	LEFT JOIN users_contests AS uc ON c.id = uc.`contest_id`
	GROUP BY c.`id`
	ORDER BY `contestants` DESC
	LIMIT 5) AS t
ORDER BY t.contestants, t.id;

-- 12.Most Valuable Person
SELECT 
	sub.id, 
	u.username, 
	p.`name`,
    CONCAT_WS(' / ', sub.passed_tests, p.tests) AS 'result'
FROM 
	(
	SELECT uc.`user_id` AS 'u_id', COUNT(contest_id) 'contests_participated'
	FROM users_contests AS uc
	GROUP BY u_id
	ORDER BY contests_participated DESC
	LIMIT 1) AS mcu
INNER JOIN users AS u ON mcu.u_id = u.id
INNER JOIN submissions AS sub ON sub.user_id = mcu.u_id
INNER JOIN problems AS p ON sub.problem_id = p.id
ORDER BY sub.id DESC;

-- 13. Contests Maximum Points
SELECT 
	c.`id`, c.`name`, SUM(p.points) AS 'points_sum'
FROM contests AS c
INNER JOIN problems AS p ON p.contest_id = c.id
GROUP BY c.`id`
ORDER BY points_sum DESC, c.id ASC;

-- 14. Contestants' Submissions
SELECT 
	c.id,
    c.name,
    COUNT(s.id) AS 'submissions'
FROM submissions AS s
INNER JOIN problems AS p ON s.problem_id = p.id
INNER JOIN contests AS c ON c.id = p.contest_id
WHERE s.user_id IN
	(SELECT uc.user_id
	FROM users_contests AS uc
	WHERE c.id = uc.contest_id
	)
GROUP BY c.id, c.name
ORDER BY `submissions` DESC, c.id ASC;