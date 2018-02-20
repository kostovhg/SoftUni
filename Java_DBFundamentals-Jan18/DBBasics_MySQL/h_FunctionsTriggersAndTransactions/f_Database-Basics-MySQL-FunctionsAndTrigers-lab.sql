-- 1.Count Employees by Town
-- Write a function ufn_count_employees_by_town(town_name) that accepts town_name 
-- as parameter and returns the count of employees who live in that town.
-- Submit your queries using the “MySQL Run Skeleton, run queries and check DB” strategy.

CREATE FUNCTION ufn_count_employees_by_town(town_name VARCHAR(20))
RETURNS DOUBLE
BEGIN
	DECLARE e_count DOUBLE;
	SET e_count := (SELECT COUNT(employee_id) FROM employees AS e
	INNER JOIN addresses AS a ON a.address_id = e.address_id
	INNER JOIN towns AS t ON t.town_id = a.town_id
	WHERE t.`name` = town_name);
    RETURN e_count;
END

-- 2.Employees Promotion
-- Write a stored procedure usp_raise_salaries(department_name) to raise the salary
-- of all employees in given department as parameter by 5%.
-- Submit your queries using the “MySQL Run Skeleton, run queries and check DB” strategy. 
CREATE PROCEDURE `usp_raise_salaries`(department_name VARCHAR(30))
BEGIN
	UPDATE employees AS e
    JOIN departments AS d
    ON e.department_id = d.department_id
    SET e.salary = e.salary * 1.05
    WHERE d.`name` = department_name;
END

-- 3.Employees Promotion By ID
-- Write a stored procedure usp_raise_salary_by_id(id) that raises a given
-- employee’s salary (by id as parameter) by 5%.
-- Consider that you cannot promote an employee that doesn’t exist – if that happens, 
-- no changes to the database should be made.
-- Submit your queries using the “MySQL Run Skeleton, run queries and check DB” strategy. 
CREATE PROCEDURE `usp_raise_salary_by_id`(id INT)
BEGIN
	START TRANSACTION;
	IF ((SELECT COUNT(employee_id)
		FROM employees WHERE employee_id LIKE id) <> 1)
	THEN
		ROLLBACK;
	ELSE
		UPDATE employees AS e SET salary = salary + salary * 0.05
		WHERE e.employee_id = id;
	END IF;
END

-- 4.Triggered
-- Create a table deleted_employees(employee_id PK, first_name,last_name,
-- middle_name,job_title,deparment_id,salary) that will hold information
-- about fired(deleted) employees from the employees table.
-- Add a trigger to employees table that inserts the corresponding information
-- in deleted_employees.
-- Submit your queries using the “MySQL Run Skeleton, run queries and check DB” strategy.
CREATE TABLE deleted_employees(
	employee_id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	middle_name VARCHAR(50),
	job_title VARCHAR(50),
	department_id INT,
	salary DECIMAL(19,4)
);
CREATE TRIGGER tr_deleted_employees
AFTER DELETE
ON employees
FOR EACH ROW
BEGIN
	INSERT INTO deleted_employees
	(first_name, last_name, middle_name, job_title, department_id, salary)
	VALUES(
		OLD.first_name,
		OLD.last_name,
		OLD.middle_name,
		OLD.job_title,
		OLD.department_id,
		OLD.salary);
END;
-- OR --
CREATE TABLE deleted_employees
SELECT
	e.first_name,
    e.last_name,
    e.middle_name,
    e.job_title,
    e.department_id,
    e.salary
FROM employees AS e;
TRUNCATE deleted_employees;
ALTER TABLE deleted_employees ADD COLUMN employee_id INT PRIMARY KEY AUTO_INCREMENT FIRST;
CREATE TRIGGER tr_deleted_employees
AFTER DELETE
ON employees
FOR EACH ROW
BEGIN
	INSERT INTO deleted_employees
	(first_name, last_name, middle_name, job_title, department_id, salary)
	VALUES(
		OLD.first_name,
		OLD.last_name,
		OLD.middle_name,
		OLD.job_title,
		OLD.department_id,
		OLD.salary);
END;
