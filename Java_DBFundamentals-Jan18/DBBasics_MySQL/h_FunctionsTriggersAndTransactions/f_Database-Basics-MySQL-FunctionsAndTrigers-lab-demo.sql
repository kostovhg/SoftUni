/*

*/

-- User-Defined Functions
/* ufn_count_employees_by_town(town_name) */
DELIMITER $$
DROP FUNCTION IF EXISTS ufn_count_employees_by_town$$
CREATE FUNCTION ufn_count_employees_by_town(town_name VARCHAR(20))
RETURNS DOUBLE
BEGIN
	DECLARE e_count DOUBLE;
	SET e_count := (SELECT COUNT(employee_id) FROM employees AS e
	INNER JOIN addresses AS a ON a.address_id = e.address_id
	INNER JOIN towns AS t ON t.town_id = a.town_id
	WHERE t.`name` = town_name);
    RETURN e_count;
END$$
DELIMITER ;

SELECT ufn_count_employees_by_town('Sofia') AS 'count';

-- Stored Procedures
DELIMITER $$
DROP PROCEDURE IF EXISTS usp_select_employees_by_seniority$$
CREATE PROCEDURE usp_select_employees_by_seniority()
BEGIN
	SELECT *
    FROM employees
    WHERE ROUND((DATEDIFF(NOW(), hire_date) / 365.25)) < 15;
END $$
DELIMITER ;

CALL usp_select_employees_by_seniority;

-- combining procedure with two functions
DELIMITER $$
CREATE PROCEDURE usp_fnc_count_employees_from_Sf_Br()
BEGIN
	SELECT
		ufn_count_employees_by_town('Sofia') AS 'employees in Sofia', ufn_count_employees_by_town('Berlin') AS 'employees in Berlin';
END$$
DELIMITER ;

CALL usp_fnc_count_employees_from_Sf_Br;

-- Parametrized Stored procedures
DELIMITER $$
CREATE PROCEDURE
usp_select_employees_by_seniority_p(min_years_at_work INT)
BEGIN
	SELECT first_name, last_name, hire_date,
		ROUND(DATEDIFF(NOW(), DATE(hire_date)) / 365.25, 0) AS 'years'
	FROM employees
	WHERE ROUND(DATEDIFF(NOW(), DATE(hire_date)) / 365.25, 0) > min_years_at_work
	ORDER BY hire_date;
END $$
DELIMITER ;

CALL usp_select_employees_by_seniority_p(15);
-- modify the procedure to skip calculation of worked years
DELIMITER $$
CREATE FUNCTION ufn_years_at_work(begin_date TIMESTAMP)
RETURNS INT
BEGIN
	DECLARE years_at_work INT;
    SET years_at_work :=
		(ROUND(DATEDIFF(NOW(), DATE(begin_date)) / 365.25, 0));
	RETURN years_at_work;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE
usp_select_employees_by_seniority_p_s(min_years_at_work INT)
BEGIN
	SELECT first_name, last_name, hire_date,
		ufn_years_at_work(hire_date) AS 'years'
	FROM employees
	WHERE ufn_years_at_work(hire_date) > min_years_at_work
	ORDER BY hire_date;
END $$
DELIMITER ;

CALL usp_select_employees_by_seniority_p_s(15);


-- EMPLOYEES PROMOTION
DELIMITER $$
CREATE PROCEDURE
usp_raise_salaries(department_name VARCHAR(30))
BEGIN
	UPDATE employees AS e
    JOIN departments AS d
    ON e.department_id = d.department_id
    SET e.salary = e.salary * 1.05
    WHERE d.`name` = department_name;
END $$
DELIMITER ;

CALL usp_rise_salaries('Engineering');
SELECT first_name, salary FROM employees;
-- Query in join in place of WHERE
DELIMITER $$
CREATE PROCEDURE
usp_raise_salaries(department_name VARCHAR(30))
BEGIN
	UPDATE employees AS e
    JOIN departments AS d
    ON e.department_id = d.department_id AND d.`name` = department_name
    SET e.salary = e.salary * 1.05;
END $$
DELIMITER ;

CALL usp_rise_salaries('Engineering');
SELECT first_name, salary FROM employees;

-- Returning Values Using OUTPUT Parameters
DELIMITER $$
CREATE PROCEDURE 
	usp_add_numbers (first_number INT, second_number INT, OUT result INT)
BEGIN
	SET result = first_number + second_number;
END $$
DELIMITER ;

SET @answer=0;
CALL usp_add_numbers(5, 6, @answer);
SELECT @Answer;

-- TRANSACTIONS
/*
select e.first_name, e.department_id, e.salary, e.salary * 1.05 AS 'raised by 5%'
    from
    employees AS e
    JOIN departments AS d
    -> ON e.department_id = d.department_id AND d.`name` = 'Engineering';
*/
-- Employees Promotion BY ID
DELIMITER $$
CREATE PROCEDURE usp_raise_salary_by_id(id INT)
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
END$$
DELIMITER ;

-- Triggers
-- - small programs in the database itself, activated by database events application layr
-- -- - UPDATE, DELETE or INSERT queries
-- -- - Called in case of specific event
-- - We do not call triggers explicitly
-- -- - Trigers are attached to a table
-- can be Before and After some event.

-- Triggered - create a table deleted_employees
-- employee_id - primari key
-- -first_name, last_name, middle_name, job_title, department_id, salary
-- Add a trigger to employees table that logs deleted employees into the deleted employees table
-- > copy table
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
-- OR -- > create new
CREATE TABLE deleted_employees(
	employee_id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	middle_name VARCHAR(50),
	job_title VARCHAR(50),
	department_id INT,
	salary DECIMAL(19,4)
);
-- AND --> creating trigger for employees;
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
