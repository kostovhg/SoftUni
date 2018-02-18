/*   Subqueries and JOINs */

-- For problems from 1 to 11 (inclusively) use "soft_uni" database
-- and for the other – "geography".

-- 1.Employee Address
-- Write a query that selects:
-- - employee_id
-- - job_title
-- - address_id
-- - address_text
-- Return the first 5 rows sorted by address_id in ascending order
SELECT
	e.`employee_id`,
	e.`job_title`,
	a.`address_id`,
	a.`address_text`
FROM `addresses` AS a
INNER JOIN `employees` AS e
ON a.`address_id` = e.`address_id`
ORDER BY a.`address_id`
LIMIT 5;

-- 2.Addresses with Towns
-- Write a query that selects:
-- - first_name
-- - last_name
-- - town
-- - address_text
-- Sorted by first_name in ascending order then by last_name.
-- Select first 5 employees.
SELECT
	e.`first_name`,
	e.`last_name`,
	t.`name`,
	a.`address_text`
FROM `employees` AS e
JOIN `addresses` AS a
ON e.`address_id` = a.`address_id`
	JOIN `towns` AS t
	ON a.`town_id` = t.`town_id`
ORDER BY e.`first_name`, e.`last_name`
LIMIT 5;

-- 3.Sales Employee
-- Write a query that selects:
-- - employee_id
-- - first_name
-- - last_name
-- - department_name
-- Sorted by employee_id in descending order.
-- Select only employees from “Sales” department.
SELECT
	e.`employee_id`,
	e.`first_name`,
	e.`last_name`,
	d.`name` AS 'department_name'
FROM `employees` AS e
JOIN `departments` AS d
ON e.`department_id` = d.`department_id`
WHERE d.`name` = 'Sales'
ORDER BY e.`employee_id` DESC;

-- 4.Employee Departments
-- Write a query that selects:
-- - employee_id
-- - first_name
-- - salary
-- - department_name
-- Filter only employees with salary higher than 15000.
-- Return the first 5 rows sorted by department_id in descending order.
SELECT
	e.`employee_id`,
	e.`first_name`,
	e.`salary`,
	d.`name` AS 'department_name'
FROM `employees` AS e
	INNER JOIN `departments` AS d
	ON e.`department_id` = d.`department_id`
WHERE e.`salary` > 15000
ORDER BY d.`department_id` DESC
LIMIT 5;

-- 5.Employees Without Project
-- Write a query that selects:
-- - employee_id
-- - first_name
-- Filter only employees without a project.
-- Return the first 3 rows sorted by employee_id in descending order.
SELECT e.`employee_id`, e.`first_name`
FROM `employees` AS e
	LEFT JOIN `employees_projects` AS ep
	ON e.`employee_id` = ep.`employee_id`
WHERE ep.`project_id` IS NULL
ORDER BY e.`employee_id` DESC
LIMIT 3;

-- 6.Employees Hired After
-- Write a query that selects:
-- - first_name
-- - last_name
-- - hire_date
-- - dept_name
-- Filter only employees with hired after 1/1/1999 and are
-- from either "Sales" or "Finance" departments.
-- Sorted by hire_date (ascending).
/* Second test in judge does not pass */
SELECT
	e.`first_name`,
	e.`last_name`,
	e.`hire_date`,
	d.`name` AS 'dept_name'
FROM `employees` AS e
INNER JOIN `departments` AS d
ON d.`department_id` = e.`department_id`
WHERE DATE(e.`hire_date`) > DATE('19990101') AND d.`name` IN ('Sales', 'Finance') 
ORDER BY e.`hire_date`;

-- 7.Employees with Project
-- Write a query that selects:
-- - employee_id
-- - first_name
-- - project_name
-- Filter only employees with a project which has started after 13.08.2002
-- and it is still ongoing (no end date).
-- Return the first 5 rows sorted by first_name
-- then by project_name both  in ascending order.
SELECT ep.`employee_id`, e.`first_name`, p.`name` AS 'project_name'
FROM `employees` AS e
JOIN employees_projects AS ep
ON e.`employee_id` = ep.`employee_id`
	JOIN `projects` AS p
	ON p.`project_id` = ep.`project_id`
WHERE p.`start_date` >= '20020814' AND p.`end_date` IS NULL
ORDER BY e.`first_name` ASC, p.`name` ASC
LIMIT 5;
--
SELECT pf.`employee_id`, e.`first_name`, pf.`name` AS 'project_name'
FROM `employees` AS e
JOIN
	(SELECT 
		ep.`employee_id`, f.`name`
	FROM `employees_projects` AS ep
	JOIN 
		(SELECT
			p.`project_id`, p.`name`, p.`start_date`
			FROM `projects` AS p
			WHERE p.`start_date` >= '20020814'
				AND p.`end_date` IS NULL) AS f
	ON ep.`project_id` = f.`project_id`) AS pf
ON e.`employee_id` = pf.`employee_id`
ORDER BY e.`first_name` ASC, pf.`name` ASC
LIMIT 5;

-- 8.Employee 24
-- Write a query that selects:
-- - employee_id
-- - first_name
-- - project_name
-- Filter all the projects of employees with id 24.
-- If the project has started after 2005 inclusively the return value should be NULL.
-- Sort result by project_name alphabetically.
SELECT pf.`employee_id`, e.`first_name`, pf.`name` AS 'project_name'
FROM `employees` AS e
JOIN
	(SELECT 
		ep.`employee_id`, f.`name`
	FROM `employees_projects` AS ep
	JOIN 
		(SELECT
			p.`project_id`, IF(YEAR(p.`start_date`) >= '2005', NULL, p.`name`) AS 'name'
			FROM `projects` AS p) AS f
	ON ep.`project_id` = f.`project_id`) AS pf
ON e.`employee_id` = pf.`employee_id`
WHERE e.`employee_id` = 24
ORDER BY pf.`name` ASC;
--
SELECT
	e.`employee_id`,
	e.`first_name`,
	IF(YEAR(p.`start_date`) >= '2005', NULL, p.`name`) AS 'project_name'
FROM projects AS p
JOIN employees_projects AS ep
ON p.`project_id` = ep.`project_id`
	JOIN `employees` AS e
	ON ep.`employee_id` = e.`employee_id`
WHERE e.`employee_id` = 24
ORDER BY p.`name`;

-- 9.Employee Manager
-- Write a query that selects:
-- - employee_id
-- - first_name
-- - manager_id
-- - manager_name
-- Filter all employees with a manager who has id equals to 3 or 7.
-- Return the all rows sorted by employee first_name in ascending order.
SELECT
	e.`employee_id`, e.`first_name`, e.`manager_id`, m.`manager_name`
FROM `employees` AS e
JOIN 
	(SELECT
		`employee_id`,
		`first_name` AS 'manager_name'
	FROM 
		`employees`
	WHERE
		`employee_id` IN (3,7)) AS m
ON e.`manager_id` = m.`employee_id`
ORDER BY e.`first_name`;

-- 10.Employee Summary
-- Write a query that selects:
-- - employee_id
-- - employee_name
-- - manager_name
-- - department_name
-- Show first 5 employees (only for employees who has a manager) with their
-- managers and the departments which they are in (show the departments 
-- of the employees). Order by employee_id.
SELECT
	e.`employee_id`,
	CONCAT(e.`first_name`, ' ', e.`last_name`) AS 'employee_name',
    m.`manager_name`,
    d.`name`
FROM `employees` AS e
JOIN 
	(
	SELECT 
		managers.`manager_id`, 
		CONCAT_WS(' ', e.`first_name`, e.`last_name`) AS 'manager_name'
	FROM 
		(
			SELECT DISTINCT manager_id
			FROM employees
		) AS managers
	JOIN employees AS e
	ON managers.`manager_id` = e.`employee_id`
	) AS m
ON e.`manager_id` = m.`manager_id`
JOIN `departments` AS d
ON e.`department_id` = d.`department_id`
ORDER BY e.`employee_id`
LIMIT 5;

-- 11.Min Average Salary
-- Write a query that return the value of the lowest average salary of all departments.
SELECT MIN(dep_avg_salary.`avg_dep_salary`) AS 'min_average_salary'
FROM
	(
	SELECT
		e.`department_id`,
		AVG (e.`salary`) AS 'avg_dep_salary'
	FROM
		employees AS e
	GROUP BY e.`department_id`
    ) as dep_avg_salary;
	
-- 12.Highest Peaks in Bulgaria
-- Write a query that selects:
-- - country_code	
-- - mountain_range
-- - peak_name
-- - elevation
-- Filter all peaks in Bulgaria with elevation over 2835.
-- Return the all rows sorted by elevation in descending order.
SELECT 
	bg_mountains.`country_code`,
    bg_mountains.`mountain_range`,
    p.`peak_name`,
    p.`elevation`
FROM
    (SELECT
		ranges.`country_code`, m.`mountain_range`, m.`id`
	FROM mountains_countries AS ranges
    LEFT JOIN mountains AS m
		ON ranges.`mountain_id` = m.`id`
	WHERE 
		ranges.`country_code` = 'BG'
	) AS bg_mountains
LEFT JOIN peaks AS p
ON p.`mountain_id` = bg_mountains.`id`
WHERE p.`elevation` > 2835
ORDER BY p.`elevation` DESC;

-- 13.Count Mountain Ranges
-- Write a query that selects:
-- - country_code
-- - mountain_range
-- Filter the count of the mountain ranges in the United States, Russia
-- and Bulgaria. Sort result by mountain_range count  in decreasing order.
SELECT
	mc.`country_code`,
    COUNT(mc.`mountain_id`) AS 'mountain_range'
FROM
	`mountains_countries` AS mc
    JOIN `countries` AS c
    ON mc.`country_code` = c.`country_code`
WHERE c.`country_name` IN ('United States', 'Russia', 'Bulgaria')
GROUP BY c.`country_code`
ORDER BY `mountain_range` DESC;

-- 14.Countries with Rivers
-- Write a query that selects:
-- - country_name
-- - river_name
-- Find the first 5 countries with or without rivers in Africa.
-- Sort them by country_name in ascending order.
SELECT africa_countries.`country_name`, r.`river_name`
FROM
	(
	SELECT c.`country_name`, c.`country_code`
	FROM countries AS c
	WHERE c.`continent_code` = 
		(
		SELECT cont.`continent_code`
		FROM continents as cont
		WHERE cont.`continent_name` LIKE ('Africa')
		) 
	) AS africa_countries
LEFT JOIN countries_rivers AS cr
ON africa_countries.`country_code` = cr.`country_code`
	LEFT JOIN rivers AS r
    ON cr.`river_id` = r.`id`
ORDER BY africa_countries.`country_name`
LIMIT 5;

-- 15.*Continents and Currencies
-- Write a query that selects:
-- - continent_code
-- - currency_code
-- - currency_usage
-- Find all continents and their most used currency.
-- Filter any currency that is used in only one country.
-- Sort your results by continent_code and currency_code.
SELECT
	c.`continent_code`, c.`currency_code`, c.`currency_usage`
FROM (
    SELECT ccount.`continent_code`, ccount.`currency_code`, MAX(ccount.`currency_usage`) AS currency_usage
    FROM (
		SELECT `continent_code`, `currency_code`, COUNT(`currency_code`) AS currency_usage
		FROM countries
		GROUP BY `continent_code`, `currency_code`
	) AS ccount
	GROUP BY ccount.`continent_code`
	HAVING `currency_usage` > 1
	) AS filtered
JOIN (
	SELECT `continent_code`, `currency_code`, COUNT(`currency_code`) AS currency_usage
	FROM countries
	GROUP BY `continent_code`, `currency_code`
	) AS c
ON filtered.`continent_code` = c.`continent_code`
WHERE c.`currency_usage` = filtered.`currency_usage`
ORDER BY c.`continent_code`, c.`currency_usage`;
-- Separating counts in temporal table
/*
	It will contain grouped count of currency usage
*/
CREATE TABLE IF NOT EXISTS temp AS
SELECT `continent_code`, `currency_code`, COUNT(`currency_code`) AS currency_usage
		FROM countries
		GROUP BY `continent_code`, `currency_code`
		HAVING `currency_usage` > 1;
/*
	Calculate maximum value by continent in select from temp
	and take only records with currency_usage equal to maximum count of usage
*/
SELECT
	c.`continent_code`, c.`currency_code`, c.`currency_usage`
FROM (
    SELECT `continent_code`, `currency_code`, MAX(`currency_usage`) AS currency_usage
    FROM temp
	GROUP BY `continent_code`
	) AS max_count
JOIN temp AS c
ON max_count.`continent_code` = c.`continent_code`
WHERE c.`currency_usage` = max_count.`currency_usage`
ORDER BY c.`continent_code`, c.`currency_usage`;

DROP TABLE IF EXISTS temp;

-- 16.Countries without any Mountains
-- Find all the count of all countries which don’t have a mountain.
Select Count(*) as 'country_count'
FROM (
	SELECT 
		c.country_code, COUNT(mc.mountain_id) as 'count'
	FROM countries AS c
	LEFT JOIN mountains_countries AS mc
	ON mc.country_code = c.country_code
	GROUP BY c.country_code
	) as t
Where t.`count` = 0;

-- 17.Highest Peak and Longest River by Country
-- For each country, find the elevation of the highest peak and the length of the longest river,
-- sorted by the highest peak_elevation (from highest to lowest),
-- then by the longest river_length (from longest to smallest),
-- then by country_name (alphabetically).
-- Display NULL when no data is available in some of the columns.
-- Limit only the first 5 rows.
SELECT
	c.country_name,
	MAX(p.elevation) AS highest_peak_elevation,
    r.length AS longest_river_length
FROM countries AS c
	LEFT JOIN mountains_countries AS mc
	ON c.country_code = mc.country_code
		LEFT JOIN mountains AS m
		ON mc.mountain_id = m.id
			LEFT JOIN peaks AS p
			ON m.id = p.mountain_id
	LEFT JOIN countries_rivers AS cr
	ON c.country_code = cr.country_code
		LEFT JOIN rivers AS r
		ON cr.river_id = r.id
GROUP BY c.country_name
ORDER BY
	highest_peak_elevation DESC,
	longest_river_length DESC,
	c.country_name ASC
LIMIT 5;
