/*
Data Aggregation - Lab
*/

-- 1. Departments Info
-- Write a query to count the number of employees in each department by id.
-- Order the information by deparment_id, then by employees count.
-- Submit your queries with the MySQL prepare DB & run queries strategy.
SELECT
	e.`department_id`,
	COUNT(e.`id`) AS 'Number of employees'
FROM `employees` AS e
GROUP BY e.`department_id`
ORDER BY e.`department_id`, COUNT(e.`id`);

-- 2.Average Salary
-- Write a query to calculate the average salary in each department.
-- Order the information by department_id. Round the salary result 
-- to two digits after the decimal point.
-- Submit your queries with the MySQL prepare DB & run queries strategy.
SELECT
	e.`department_id`,
	ROUND(AVG(e.`salary`), 2) AS 'Average Salary'
FROM `employees` AS e
GROUP BY e.`department_id`
ORDER BY e.`department_id`;

-- 3. Min Salary
-- Write a query to retrieve information about the departments
-- grouped by department_id with minumum salary higher than 800.
-- Round the salary result to two digits after the decimal point.
-- Submit your queries with the MySQL prepare DB & run queries strategy.
SELECT
	e.`department_id`,
	ROUND(MIN(e.`salary`), 2) AS 'Min Salary'
FROM `employees` AS e
GROUP BY e.`department_id`
HAVING MIN(e.`salary`) > 800
ORDER BY e.`department_id`;

-- 4. Appetizers Count
-- Write a query to retrieve the count of all appetizers (category id = 2)
-- with price higher than 8.
-- Submit your queries with the MySQL prepare DB & run queries strategy. 
SELECT 
	COUNT(p.`category_id`)
FROM `products` AS p
WHERE p.`price` > 8 AND p.`category_id` LIKE (2);

-- 5.Menu Prices
-- Write a query to retrieve information about the prices of each category.
-- The output should consist of:
-- - Category_id
-- - Average Price 
-- - Cheapest Product
-- - Most Expensive Product
-- See the examples for more information.
-- Round the results to 2 digits after the decimal point.
-- Submit your queries with the MySQL prepare DB & run queries strategy. 
SELECT
	p.`category_id`,
	ROUND(AVG(p.`price`), 2) AS 'Average Price',
	ROUND(MIN(p.`price`), 2) AS 'Cheapest Product',
	ROUND(MAX(p.`price`), 2) AS 'Most Expensive Product'
FROM 
	`products` AS p
GROUP BY
	p.`category_id`;