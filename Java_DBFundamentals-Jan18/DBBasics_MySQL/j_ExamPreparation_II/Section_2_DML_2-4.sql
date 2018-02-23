use closed_judge_system;
-- After inserting data from data.sql
-- 02. Data Insertion
INSERT INTO submissions (passed_tests, problem_id, user_id)
SELECT
    ceiling(SQRT(POW(LENGTH(p.`name`), 3)) - length(p.`name`)) AS passed_tests,
    p.id AS problem_id,
    ceiling((p.id * 3) / 2) AS user_id
FROM problems AS p
WHERE p.id BETWEEN 1 AND 10;

-- 03. Data Update
UPDATE `problems` AS p
	INNER JOIN `contests` AS c ON p.`contest_id` = c.`id`
	INNER JOIN `categories` AS cat ON c.`category_id` = cat.`id`
    INNER JOIN `submissions` AS s ON p.`id` = s.`problem_id`
SET
	p.`tests` = 
		(CASE p.`id` % 3
			WHEN 0 THEN CHAR_LENGTH(cat.`name`)
            WHEN 1 THEN 
				(SELECT
					SUM(s.`id`)
				FROM
					`submissions` AS s
				WHERE
					s.`problem_id` = p.`id`)
			WHEN 2 THEN CHAR_LENGTH(c.`name`)
		END)
WHERE p.`tests` = 0;

-- 04. Date Deletion
DELETE u FROM `users` AS u
LEFT JOIN users_contests AS uc ON u.`id` = uc.`user_id`
WHERE uc.`user_id` IS NULL;
