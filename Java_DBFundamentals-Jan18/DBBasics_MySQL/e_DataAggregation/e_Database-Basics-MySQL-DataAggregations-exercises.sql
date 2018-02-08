/*
Exercises: Data Aggregation
*/

-- 1. Records’ Count
-- Import the database and send the total count of records to 
-- Mr. Bodrog. Make sure nothing got lost.
USE gringotts;
SELECT 
    COUNT(w.`id`) AS 'count'
FROM `wizzard_deposits` AS w;

-- 2.	 Longest Magic Wand
-- Select the size of the longest magic wand.
-- Rename the new column appropriately.
SELECT
	MAX(w.`magic_wand_size`) AS 'longest_magic_wand'
FROM `wizzard_deposits` AS w;

-- 3. Longest Magic Wand per Deposit Groups
-- For wizards in each deposit group show the longest magic wand.
-- Sort result by longest magic wand for each deposit group in increasing order,
-- then by deposit_group alphabetically. Rename the new column appropriately.
SELECT
	w.`deposit_group`,
	MAX(w.`magic_wand_size`) AS 'longest_magic_wand'
FROM `wizzard_deposits` AS w 
GROUP BY 
	w.`deposit_group` 
ORDER BY 
	`longest_magic_wand` ASC,
	w.`deposit_group` ASC;
	
-- 4. Smallest Deposit Group per Magic Wand Size*
-- Select the deposit group with the lowest average wand size.
SELECT
	w.`deposit_group`
FROM
	`wizzard_deposits` AS w
GROUP BY
	w.`deposit_group`
ORDER BY 
	AVG(w.`magic_wand_size`)
LIMIT 1;

-- 5. Deposits Sum
-- Select all deposit groups and its total deposit sum.
-- Sort result by total_sum in increasing order.
SELECT
	w.`deposit_group`,
	SUM(w.`deposit_amount`) AS 'total_sum'
FROM
	`wizzard_deposits` AS w
GROUP BY
	w.`deposit_group`
ORDER BY
	`total_sum`;
	
-- 6. Deposits Sum for Ollivander family
-- Select all deposit groups and its total deposit sum but only
-- for the wizards who has their magic wand crafted by Ollivander family.
-- Sort result by deposit_group alphabetically.
SELECT
	w.`deposit_group`,
	SUM(w.`deposit_amount`) AS 'total_sum'
FROM
	`wizzard_deposits` AS w
WHERE
	w.`magic_wand_creator` = 'Ollivander family'
GROUP BY
	w.`deposit_group`;
	
-- 7. Deposits Filter
-- Select all deposit groups and its total deposit sum but only
-- for the wizards who has their magic wand crafted by Ollivander family. -- After this, filter total deposit sums lower than 150000.
-- Order by total deposit sum in descending order.
SELECT
	w.`deposit_group`,
	SUM(w.`deposit_amount`) AS 'total_sum'
FROM
	`wizzard_deposits` AS w
WHERE
	w.`magic_wand_creator` = 'Ollivander family'
GROUP BY
	w.`deposit_group`
HAVING 
	`total_sum` < 150000
ORDER BY
	`total_sum` DESC;
	

-- 8. Deposit charge
-- Create a query that selects:
-- • Deposit group 
-- • Magic wand creator
-- • Minimum deposit charge for each group 
-- Group by deposit_group and magic_wand_creator.
-- Select the data in ascending order by magic_wand_creator
-- and deposit_group.
SELECT
	w.`deposit_group`,
	w.`magic_wand_creator`,
	MIN(w.`deposit_charge`) AS 'min_deposit_charge'
FROM 
	`wizzard_deposits` AS w
GROUP BY
	w.`deposit_group`,
	w.`magic_wand_creator`
ORDER BY
	w.`magic_wand_creator`,
	w.`deposit_group`;
	
-- 9. Age Groups
-- Write down a query that creates 7 different groups based on their age.
-- Age groups should be as follows:
-- •	[0-10]
-- •	[11-20]
-- •	[21-30]
-- •	[31-40]
-- •	[41-50]
-- •	[51-60]
-- •	[61+]
-- The query should return:
-- •	Age groups
-- •	Count of wizards in it
-- Sort result by increasing size of age groups.
-- Not for judge. First test does not pass
SELECT
	CASE
		WHEN w.`age` < 61 
			THEN
				CONCAT('[', ROUND(MIN(w.`age`), -1) + 1,
					'-', ROUND(MAX(w.`age`), -1), ']')
		ELSE '[61+]'
		END
	AS 'age_group',
	COUNT(w.`age`) AS 'wizard_count'
FROM `wizzard_deposits` AS w
GROUP BY
	CASE
		WHEN w.`age` < 61
			THEN (w.`age` - 1) DIV 10 -- (- 1 is needed to lower the upper bound of the ranges)
		ELSE w.`age` DIV 1000
	END 
ORDER BY
	`age_group` ASC;
-- for judge 100/100 
SELECT
	w.`range` AS 'age_group',
	COUNT(*) AS 'wizard_count'
FROM (
	SELECT CASE
		WHEN `age` BETWEEN 0 AND 10 THEN '[0-10]'
		WHEN `age` BETWEEN 11 AND 20 THEN '[11-20]'
		WHEN `age` BETWEEN 21 AND 30 THEN '[21-30]'
		WHEN `age` BETWEEN 31 AND 40 THEN '[31-40]'
		WHEN `age` BETWEEN 41 AND 50 THEN '[41-50]'
		WHEN `age` BETWEEN 51 AND 60 THEN '[51-60]'
		WHEN `age` >= 61 THEN '[61+]'
		END
	AS 'range'
	FROM
		`wizzard_deposits`) AS w
GROUP BY
	`age_group`
ORDER BY
	`age_group`;  
	
-- 10. First Letter
-- Write a query that returns all unique wizard first letters of their
-- first names only if they have deposit of type Troll Chest.
-- Order them alphabetically. Use GROUP BY for uniqueness.
SELECT 
	LEFT(w.`first_name`, 1) AS 'first_letter'
FROM 
	`wizzard_deposits` AS w
WHERE 
	w.`deposit_group` = 'Troll Chest'
GROUP BY
	`first_letter`;

-- 11.	Average Interest 
-- Mr. Bodrog is highly interested in profitability.
-- He wants to know the average interest of all deposits groups
-- split by whether the deposit has expired or not. But that’s not all.
-- He wants you to select deposits with start date after 01/01/1985.
-- Order the data descending by Deposit Group and ascending by Expiration Flag.
SELECT
	w.`deposit_group`,
	w.`is_deposit_expired` + 0 AS 'is_deposit_expired',
	AVG(w.`deposit_interest`) AS 'average_interest'
FROM
	`wizzard_deposits` AS w
WHERE
	w.`deposit_start_date` > '19850101'
GROUP BY
	w.`deposit_group`, w.`is_deposit_expired`
ORDER BY 
	w.`deposit_group` DESC,
	w.`is_deposit_expired` ASC;
	
-- 12.	Rich Wizard, Poor Wizard*
-- Give Mr. Bodrog some data to play his favorite game Rich Wizard, Poor Wizard.
-- The rules are simple: You compare the deposits of every wizard with
-- the wizard after him. If a wizard is the last one in the database, simply ignore it.
-- At the end you have to sum the difference between the deposits.
SELECT
	SUM(w.`difference`) AS 'SUM'
FROM
	(SELECT
		w1.`first_name` AS 'host_wizard',
		w1.`deposit_amount` AS 'host_wizard_deposit',
		w2.`first_name` AS 'guest_wizard',
		w2.`deposit_amount` AS 'guest_wizard-deposit',
		(w1.`deposit_amount` - w2.`deposit_amount`) AS 'difference'
	FROM
		`wizzard_deposits` AS w1 JOIN `wizzard_deposits` AS w2
	WHERE	
		w2.`id` = w1.`id` + 1) as w;

-- In fact all columns are not necessary. They are only a demo of sub selection that forms new table for consecutive rows comparison
SELECT
	SUM(w.`difference`) AS 'SUM'
FROM
	(SELECT
		(w1.`deposit_amount` - w2.`deposit_amount`) AS 'difference'
	FROM
		`wizzard_deposits` AS w1 JOIN `wizzard_deposits` AS w2
	WHERE	
		w2.`id` = w1.`id` + 1) as w;
		
-- 13.	 Employees Minimum Salaries
-- That’s it! You no longer work for Mr. Bodrog.
-- You have decided to find a proper job as an analyst in SoftUni. 
-- It’s not a surprise that you will use the soft_uni database. 
-- Select the minimum salary from the employees for departments with ID (2,5,7)
-- but only for those who are hired after 01/01/2000.Sort result by department_id in ascending order.
-- Your query should return:
-- •	department_id
SELECT
	e.`department_id`,
	MIN(e.`salary`) AS 'minimu_salary'
FROM
	`employees` AS e
WHERE
	e.`hire_date` > '20000101'
GROUP BY 
	e.`department_id`
HAVING
	e.`department_id` IN (2, 5, 7)
ORDER BY
	e.`department_id` ASC;
	
-- 14. Employees Average Salaries
-- Select all high paid employees who earn more than 30000 into a new table.
-- Then delete all high paid employees who have manager_id = 42 from the new table;
-- Then increase the salaries of all high paid employees with department_id =1
-- with 5000 in the new table.
-- Finally, select the average salaries in each department from the new table.
-- Sort result by department_id in increasing order.
/* NOTE: With selecting trough 'WHERE' clause the NULL values from 'manager_id` are not collected.
For that reason we need to include them explicitly in 'WHERE' clause that filters our temporal 't' table.
This make difference only in the zero test in judge */
SELECT 
	t.`department_id`,
	t.`modified_salary`
FROM
(SELECT 
		e.`department_id`,
        AVG(IF(e.`department_id` = 1, e.`salary` + 5000, e.`salary`)) AS 'modified_salary'
    FROM `employees` AS e
    WHERE (e.`salary` > 30000.00 AND (e.`manager_id` != 42 OR e.`manager_id` IS NULL))
    GROUP BY e.`department_id`) AS t
ORDER BY `department_id`;

-- 15. Employees Maximum Salaries
-- Find the max salary for each department.
-- Filter those which have max salaries not in the range 30000 and 70000.
-- Sort result by department_id in increasing order.
SELECT
	e.`department_id`,
	MAX(e.`salary`) AS 'max_salary'
FROM `employees` AS e
GROUP BY e.`department_id`
HAVING MAX(e.`salary`) NOT BETWEEN 30000 AND 70000
ORDER BY e.`department_id`;
