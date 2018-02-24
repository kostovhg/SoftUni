-- 1. Employees' SAlary
SELECT 
	e.employee_id,
    e.hire_date,
    e.salary,
    e.branch_id
FROM `employees` AS e
WHERE 
	e.salary > 2000
    AND
    e.hire_date > '2009-06-15'; 
    
-- 2. customer Age
-- Does not pass judge!
SELECT *
FROM
(SELECT
	c.first_name,
    c.date_of_birth,
    (SELECT YEAR(FROM_DAYS(TO_DAYS('2016-10-01')-TO_DAYS(c.date_of_birth)))) AS 'age'
FROM customers AS c) as t
WHERE t.age BETWEEN 40 AND 50
;

-- 3. Customer City
SELECT
	c.customer_id,
	c.first_name,
	c.last_name,
	c.gender,
	t.city_name
FROM customers AS c
LEFT JOIN cities AS t ON c.city_id = t.city_id
WHERE
	(c.last_name LIKE ('Bu%')
    OR
    c.first_name LIKE ('%a'))
    AND
    CHAR_LENGTH(t.city_name) > 7
ORDER BY
	c.customer_id ASC;
    
-- 4. Employee Accounts
SELECT
	e.employee_id,
	e.first_name,
	a.account_number
FROM employees AS e
JOIN employees_accounts AS ea ON e.employee_id = ea.employee_id
JOIN accounts AS a ON ea.account_id = a.account_id
where YEAR(a.start_date) > 2012
ORDER BY e.first_name DESC
LIMIT 5;

-- 5. Employee Cities
SELECT 
	t.city_name,
    t.name,
    t.`employees_count`
FROM
	(SELECT
		c.city_id,
		c.city_name,
		b.name,
		COUNT(e.employee_id) AS 'employees_count'
	FROM employees AS e
	JOIN branches AS b ON e.branch_id = b.branch_id
	LEFT JOIN cities AS c ON c.city_id = b.city_id
	GROUP BY c.city_name, b.name
	HAVING employees_count > 2) as t
WHERE t.city_id NOT IN (4, 5);
--
SELECT
	c.city_name,
	b.name,
	COUNT(e.employee_id) AS 'employees_count'
FROM employees AS e
JOIN branches AS b ON e.branch_id = b.branch_id
LEFT JOIN cities AS c ON c.city_id = b.city_id
WHERE c.city_id NOT IN (4, 5)
GROUP BY c.city_name, b.name
HAVING employees_count > 2;

-- 6. Loan Statistics
SELECT 
	SUM(l.amount) AS 'total_loan_amount',
    MAX(l.interest) AS 'max_interest',
    MIN(e.salary) AS 'min_employee_salary'
FROM loans AS l
JOIN employees_loans As el ON el.loan_id = l.loan_id
JOIN employees AS e ON el.employee_id = e.employee_id;

-- 7. Unite People
(SELECT 
	e.first_name,
    c.city_name
FROM employees AS e
JOIN branches AS b ON b.branch_id = e.branch_id
JOIN cities AS c ON c.city_id = b.city_id
limit 3)
UNION
(SELECT
 c.first_name,
 ci.city_name
 from customers AS c
 JOIN cities AS ci ON ci.city_id = c.city_id
 LIMIT 3);
 
 -- 8. Customers without Accounts
 SELECT
	c.customer_id,
    c.height
FROM customers AS c 
LEFT JOIN accounts AS a ON a.customer_id = c.customer_id
WHERE 
	a.account_id IS NULL
    AND
    (c.height BETWEEN 1.74 AND 2.04);
    
-- 9. Customers without Accounts
SELECT 
	c.customer_id,
    lo.amount
FROM customers AS c
JOIN loans AS lo ON lo.customer_id = c.customer_id
WHERE lo.amount > 
	(SELECT 
		avg(l.amount)
	FROM customers AS cus 
	JOIN loans AS l ON l.customer_id = cus.customer_id
	WHERE cus.gender = 'M')
Order by c.last_name ASC
LIMIT 5;

-- 10. Oldest Accout
SELECT 
	cus.customer_id,
    cus.first_name,
    acc.start_date
FROM customers AS cus 
JOIN accounts AS acc ON acc.customer_id = cus.customer_id
WHERE acc.start_date = (SELECT MIN(start_date) FROM accounts);




