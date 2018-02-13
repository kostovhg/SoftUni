 /* Lab: Subqueries and JOINs */ 

-- 1.Managers
-- Write a query to retrieve information about the managers:
-- – id, full_name, deparment_id and department_name.
-- Select first 5 deparments ordered by employee_id.
-- Submit your queries using the “MySQL prepare DB and Run Queries” strategy. 
SELECT
	d.`manager_id`,
	CONCAT_WS(' ', e.`first_name`, e.`last_name`) AS 'full_name',
	d.`department_id`,
	d.`name` AS 'department_name'
FROM `departments` AS d
JOIN `employees` AS e
ON d.`manager_id` = e.`employee_id`
ORDER BY e.`employee_id`
LIMIT 5;
-- Solution from presentation.
SELECT 
	e.`employee_id`,
	CONCAT(e.`first_name`, ' ', e.`last_name`) AS 'full_name',
	d.`department_id`,
	d.`name`
FROM `employees` AS e
RIGHT JOIN `departments` AS d
ON d.`manager_id` = e.`employee_id`
ORDER BY e.`employee_id`
LIMIT 5;

-- 2.Towns Adresses
-- Write a query to get information about adresses in the database,
-- which are in San Francisco, Sofia or Carnation.
-- Retrieve town_id, town_name, address_text.
-- Order the result by town_id, then by address_id.
-- Submit your queries using the “MySQL prepare DB and Run Queries” strategy. 
SELECT
	t.`town_id`, t.`name` AS 'town_name', a.`address_text`
FROM `addresses` AS a
	JOIN `towns` AS t
	ON a.`town_id` = t.`town_id`
WHERE t.`name` IN ('San Francisco', 'Sofia', 'Carnation')
ORDER BY t.`town_id`, a.`address_id`;

-- 3.Employees Without Managers
-- Write a get information about employee_id, first_name, last_name,
-- department_id and salary about all employees who don’t have a manager.
-- Submit your queries using the “MySQL prepare DB and Run Queries” strategy.
SELECT
	e.`employee_id`, e.`first_name`, e.`last_name`, e.`department_id`, e.`salary`
FROM `employees` AS e
WHERE e.`manager_id` IS NULL;

-- 4.Higher Salary
-- Write a query to count the number of employees 
-- who receive salary higher than the average.
-- Submit your queries using the “MySQL prepare DB and Run Queries” strategy.
SELECT COUNT(e.`employee_id`) AS 'count'
 FROM employees AS e
 WHERE e.`salary` >
(
	SELECT AVG(salary) AS 'average_salary' FROM employees
);
