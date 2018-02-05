
-- GROUP BY

USE restaurant;

-- CLean check of employees salaries
SELECT 
	e.`department_id`,
	e.`salary`
FROM employees AS e;

-- Get count of employees by department_id and sum of their salaries
SELECT 
	e.`department_id`,
	count(e.`salary`) AS 'count for group',
	SUM(e.`salary`) AS 'Sum of salaries'
FROM employees AS e
GROUP BY e.`department_id`
ORDER BY e.`department_id`;

-- Counting job titles 
USE soft_uni;
SELECT e.`job_title` , COUNT(e.`employee_id`)
FROM employees As e
GROUP BY e.`job_title`;

USE soft_uni;
SELECT 
	e.`salary`,
	e.`job_title`, count(e.`employee_id`)
FROM employees As e
GROUP BY e.`salary`;

USE soft_uni;
SELECT 
	e.`department_id`,
	COUNT(e.`employee_id`) AS 'count',
	ROUND(AVG(e.`salary`), 2) AS 'Avg salary'
FROM employees As e
GROUP BY e.`department_id`
ORDER BY e.`department_id` ASC;

-- Problem: Departments Total Salaries

-- Get sum of salaries by department in employees table
 SELECT
	e.`department_id`,
	ROUND(SUM(e.`salary`), 2) AS 'total_salary'
	FROM `employees` AS e
	GROUP BY e.`department_id`
	ORDER BY e.`department_id` ASC;
	
-- Aggregate Functions
-- - Used to operate over one or more groups performing data analysis on every one
-- -- MIN, MAX, AVG, COUNT etc.
-- - THey usually ignore NULL values
SELECT
	e.`department_id`,
ROUND(MIN(e.`salary`), 2) AS 'min_salary',
ROUND(MAX(e.`salary`), 2) AS 'max_salary',
COUNT(e.`salary`) AS 'count_salaries',
ROUND(SUM(e.`salary`), 2) AS 'sum_salaries',
ROUND(AVG(e.`salary`), 2) AS 'avg_salary'
FROM `employees` AS e
GROUP BY e.`department_id`
ORDER BY `sum_salaries` ASC;

USE book_library;
SELECT a.`first_name`, a.`last_name`,
IFNULL(a.`died`, 'alive') AS 'day died',
FROM_DAYS(DATEDIFF(IFNULL(a.`died`, NOW()), a.`born`)) AS 'time_lived'
FROM `authors` AS a
ORDER BY a.`last_name`;

-- More complicated grouping
USE soft_uni;
SELECT
	e.`department_id`,
	e.`manager_id`,
	COUNT(e.`employee_id`)
FROM `employees` AS e
GROUP BY e.`department_id`, e.`manager_id`;

-- Having 
-- - Using predicates while grouping

-- HAVING
SELECT
	e.`department_id`,
	COUNT(e.`employee_id`),
	COUNT(e.`manager_id`) AS 'mng_num'
FROM `employees` AS e
GROUP BY e.`department_id`
HAVING mng_num > 1;

-- HAVING Syntax
SELECT
	-- e.`department_id`,
	d.`name` AS 'department',
	COUNT(e.`employee_id`) AS 'empl count',
	COUNT(e.`manager_id`) AS 'mng_num',
	ROUND(AVG(e.`salary`), 2) AS 'avg_sal'
FROM `employees` AS e
JOIN `departments` AS d
ON e.department_id = d.department_id
GROUP BY e.`department_id`
HAVING avg_sal > 12000
ORDER BY avg_sal DESC;

