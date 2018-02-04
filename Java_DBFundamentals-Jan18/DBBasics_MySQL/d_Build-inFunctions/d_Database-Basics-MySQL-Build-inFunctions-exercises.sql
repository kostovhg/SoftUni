-- Part I – Queries for SoftUni Database
-- 1.Find Names of All Employees by First Name
--- Write a SQL query to find first and last names of all employees whose 
--- first name starts with “Sa” (case insensitively). Order the information by
---  id. Submit your query statements as Prepare DB & run queries.
SELECT 
	e.`first_name`,
	e.`last_name`
FROM employees AS e
WHERE LOWER(e.`first_name`) LIKE ('sa%')
ORDER BY e.`employee_id`;

-- 2.Find Names of All employees by Last Name 
--- Write a SQL query to find first and last names of all employees whose last
--- name contains “ei” (case insensitively). Order the information by id.
--- Submit your query statements as Prepare DB & run queries.
SELECT 
	e.`first_name`,
	e.`last_name`
FROM employees AS e
WHERE LOWER(e.`last_name`) LIKE ('%ei%')
ORDER BY e.`employee_id`;

-- 3.Find First Names of All Employees
--- Write a SQL query to find the first names of all employees in
--- the departments with ID 3 or 10 and whose hire year is
--- between 1995 and 2005 inclusive. Order the information by id.
--- Submit your query statements as Prepare DB & run queries.
SELECT 
	e.`first_name`
FROM `employees` AS e
WHERE
	e.`department_id` IN (3, 10)
AND
	YEAR(e.`hire_date`) BETWEEN '1995' AND '2005';
	
-- 4.Find All Employees Except Engineers
--- Write a SQL query to find the first and last names of all employees
--- whose job titles does not contain “engineer”.
--- Order by id.
--- Submit your query statements as Prepare DB & run queries.
SELECT 
	e.`first_name`,
	e.`last_name`
FROM employees AS e
WHERE LOWER(e.`job_title`) NOT LIKE ('%engineer%')
ORDER BY e.`employee_id`;

-- 5.Find Towns with Name Length
--- Write a SQL query to find town names that are 5 or 6 symbols
--- long and order them alphabetically by town name.
--- Submit your query statements as Prepare DB & run queries.
SELECT 
	t.`name`
FROM `towns` AS t
WHERE CHAR_LENGTH(t.`name`) IN (5, 6) 
ORDER BY t.`name` ASC;

-- 6. Find Towns Starting With
--- Write a SQL query to find all towns that
--- start with letters M, K, B or E (case insensitively).
--- Order them alphabetically by town name.
--- Submit your query statements as Prepare DB & run queries.
SELECT 
	t.`town_id`,
	t.`name`
FROM `towns` AS t
WHERE LOWER(t.`name`) REGEXP '^\[mkbe\]\.'
ORDER BY t.`name` ASC;

-- 7.Find Towns Not Starting With
--- Write a SQL query to find all towns that does not start with 
--- letters R, B or D (case insensitively).
--- Order them alphabetically by name.
--- Submit your query statements as Prepare DB & run queries.
SELECT 
	t.`town_id`,
	t.`name`
FROM `towns` AS t
WHERE LOWER(t.`name`) REGEXP '^\[\^rbd\]\.'
ORDER BY t.`name` ASC;

-- 8.Create View Employees Hired After 2000 Year
--- Write a SQL query to create view v_employees_hired_after_2000
--- with first and last name to all employees hired after 2000 year.
--- Submit your query statements as Run skeleton, run queries & check DB.
CREATE VIEW `v_employees_hired_after_2000` AS
SELECT
	e.`first_name`,
	e.`last_name`
FROM `employees` AS e
WHERE
	YEAR(e.`hire_date`) > '2000';
	
-- 9.Length of Last Name
--- Write a SQL query to find the names of all employees
--- whose last name is exactly 5 characters long.
SELECT 
	e.`first_name`,
	e.`last_name`
FROM employees AS e
WHERE CHAR_LENGTH(e.`last_name`) = 5
ORDER BY e.`employee_id`;

-- Part II – Queries for Geography Database 
-- 10.Countries Holding ‘A’ 3 or More Times
--- Find all countries that holds the letter 'A' in their name
--- at least 3 times (case insensitively),
--- sorted by ISO code.
--- Display the country name and ISO code.
--- Submit your query statements as Prepare DB & run queries.
SELECT
	c.`country_name`,
	c.`iso_code`
FROM `countries` AS c
WHERE 
	CHAR_LENGTH(c.`country_name`) - CHAR_LENGTH(REPLACE(LOWER(c.`country_name`), 'a', '')) >= 3
ORDER BY
	c.`iso_code`;
	
-- 11. Mix of Peak and River Names
--- Combine all peak names with all river names, so that the last letter
--- of each peak name is the same like the first letter of its
--- corresponding river name. Display the peak names, river names, and
--- the obtained mix. Sort the results by the obtained mix.
--- Submit your query statements as Prepare DB & run queries.
SELECT
	p.`peak_name`,
	r.`river_name`,
LOWER(
	CONCAT(
		p.`peak_name`,
		SUBSTRING(r.`river_name`, 2))
	) AS 'mix'
FROM `peaks` AS p, `rivers` AS r
WHERE
RIGHT(p.`peak_name`, 1) = LEFT(r.`river_name`,1)
ORDER BY `mix`;

-- Part III – Queries for Diablo Database
-- 12.Games from 2011 and 2012 year
--- Find the top 50 games ordered by start date, then by name of the game.
--- Display only games from 2011 and 2012 year.
--- Display start date in the format “YYYY-MM-DD”.
--- Submit your query statements as Prepare DB & run queries.
SELECT
	g.`name`,
	DATE_FORMAT(g.`start`, "%Y-%m-%d") AS 'start'
FROM `games` AS g
WHERE
	YEAR(g.`start`) IN ('2011', '2012')
ORDER BY
	g.`start`, g.`name`
LIMIT 50;

-- 13. User Email Providers
--- Find all users along with information about their email providers.
--- Display the user_name and email provider.
--- Sort the results by email provider alphabetically, then by username. 
--- Submit your query statements as Prepare DB & run queries.
SELECT
	u.`user_name`,
	SUBSTRING(u.`email`, LOCATE('@', u.`email`) + 1) AS 'Email Provider'
FROM `users` AS u
ORDER BY `Email Provider` ASC, u.`user_name` ASC;

-- 14.Get Users with IP Address Like Pattern
--- Find all user_name and  ip_address for each user sorted by
--- user_name alphabetically. Display only rows that ip_address
--- matches the pattern: “___.1%.%.___”.
--- Submit your query statements as Prepare DB & run queries.
SELECT
	u.`user_name`,
	u.`ip_address`
FROM `users` AS u
WHERE 
	u.`ip_address` LIKE '___.1%.%.___'
ORDER BY
	u.`user_name`;
	
-- 	15. Show All Games with Duration and Part of the Day
--- Find all games with part of the day and duration.
--- Parts of the day should be Morning (start time is >= 0 and < 12),
--- Afternoon (start time is >= 12 and < 18),
--- Evening (start time is >= 18 and < 24).
--- Duration should be Extra Short (smaller or equal to 3),
--- Short (between 3 and 6 including),
--- Long (between 6 and 10 including) and
--- Extra Long in any other cases or without duration.
--- Submit your query statements as Prepare DB & run queries.
SELECT
	g.`name`,
	CASE
		WHEN HOUR(g.`start`) BETWEEN 0 AND 11 THEN 'Morning'
		WHEN HOUR(g.`start`) BETWEEN 12 AND 17 THEN 'Afternoon'
		WHEN HOUR(g.`start`) BETWEEN 18 AND 23 THEN 'Evening'
	END AS 'Part of the Day',
	CASE
		WHEN g.`duration` BETWEEN 0 AND 3 THEN 'Extra Short'
		WHEN g.`duration` BETWEEN 4 AND 6 THEN 'Short'
		WHEN g.`duration` BETWEEN 7 AND 10 THEN 'Long'
		ELSE 'Extra Long'
	END AS 'Part of the Day'
FROM `games` AS g
ORDER BY g.`name`;

--	Part IV – Date Functions Queries
-- 16. Orders Table
--- You are given a table orders(id, product_name, order_date) filled
--- with data. Consider that the payment for that order must beaccomplished within 3 days after the order date.
--- Also the delivery date is up to 1 month.
--- Write a query to show each product’s name, order date,
--- pay and deliver due dates.
--- Submit your query statements as Prepare DB & run queries.
SELECT
	o.`product_name`,
	o.`order_date`,
	DATE_ADD(o.`order_date`, INTERVAL 3 DAY) AS 'pay_due',
	DATE_ADD(o.`order_date`, INTERVAL 1 MONTH) AS 'deliver_due'
FROM `orders` AS o;
	