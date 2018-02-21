/*  
	Exercises: Functions, Triggers and Transactions
*/
-- This document defines the exercise assignments for the "Databases Basics - MySQL" course @ Software University. 

/* Part I – Queries for SoftUni Database */

-- 1.Employees with Salary Above 35000
-- Create stored procedure usp_get_employees_salary_above_35000 that
-- returns all employees’ first and last names for whose salary is above
-- 35000. The result should be sorted by first_name then by last_name
-- alphabetically, and id ascending. Submit your query statement as Run
-- skeleton, run queries & check DB in Judge.
/* DELIMITER $$ */
CREATE PROCEDURE `usp_get_employees_salary_above_35000`()
BEGIN
	SELECT first_name, last_name
	FROM employees
	WHERE salary > 35000
	ORDER BY first_name, last_name, employee_id ASC;
END /*$$
DELIMITER ;*/

-- 2.Employees with Salary Above Number
-- Create stored procedure usp_get_employees_salary_above that accept a
-- number as parameter and return all employees’ first and last names whose
-- salary is above or equal to the given number. The result should be
-- sorted by first_name then by last_name alphabetically and id ascending.
-- Submit your query statement as Run skeleton, run queries & check DB in Judge.
/* DELIMITER $$ */
CREATE PROCEDURE usp_get_employees_salary_above(sal DECIMAL(19,4))
BEGIN
	SELECT first_name, last_name
	FROM employees
	WHERE salary >= sal
	ORDER BY first_name, last_name, employee_id ASC;
END /*$$
DELIMITER ;*/

-- 3.Town Names Starting With
-- Write a stored procedure usp_get_towns_starting_with that accept string
-- as parameter and returns all town names starting with that string.
-- The result should be sorted by town_name alphabetically.
-- Submit your query statement as Run skeleton, run queries & check DB in Judge.
/* DELIMITER $$ */
CREATE PROCEDURE usp_get_towns_starting_with(ts VARCHAR(50))
BEGIN
	SELECT name AS town_name
	FROM towns
	WHERE name LIKE (CONCAT(ts, '%'))
	ORDER BY town_name ASC;
END /*$$
DELIMITER ;*/

-- 4.Employees from Town
-- Write a stored procedure usp_get_employees_from_town that accepts 
-- town_name as parameter and return the employees’ first and last name
-- that live in the given town. The result should be sorted by first_name
-- then by last_name alphabetically and id ascending. Submit your query
-- statement as Run skeleton, run queries & check DB in Judge.
/* DELIMITER $$ */
CREATE PROCEDURE usp_get_employees_from_town(town_name VARCHAR(50))
BEGIN
	SELECT first_name, last_name
	FROM employees AS e
	JOIN addresses AS a ON e.address_id = a.address_id
		JOIN towns AS t ON a.town_id = t.town_id
	WHERE t.name = town_name
	ORDER BY first_name, last_name, employee_id ASC;
END /*$$
DELIMITER ;*/

-- 5.Salary Level Function
-- Write a function ufn_get_salary_level that receives salary of an
-- employee and returns the level of the salary.
-- - If salary is < 30000 return “Low”
-- - If salary is between 30000 and 50000 (inclusive) return “Average”
-- - If salary is > 50000 return “High”
-- Submit your query statement as Run skeleton, run queries & check DB in Judge.
/* DELIMITER $$ */
CREATE FUNCTION ufn_get_salary_level(sal DECIMAL(19,4))
RETURNS VARCHAR(30)
BEGIN
	DECLARE res VARCHAR(30);
	IF( sal < 30000)
	THEN SET res := 'Low';
	ELSEIF (sal <= 50000)
	THEN SET res := 'Average';
	ELSE SET res := 'High';
	END IF;
RETURN res;
END /*$$
DELIMITER ;*/
--
/* DELIMITER $$ */
CREATE FUNCTION ufn_get_salary_level(`parameter` DOUBLE(19,4))
RETURNS VARCHAR(8)
BEGIN
    DECLARE result VARCHAR(8);
    CASE
        WHEN `parameter`< 30000
            THEN SET `result` = 'Low';
        WHEN `parameter`>= 30000 AND `parameter` <= 50000
            THEN SET `result` = 'Average';
        ELSE SET `result` = 'High';
    END CASE;
    RETURN `result`;
END /*$$
DELIMITER ;*/

-- 6.Employees by Salary Level
-- Write a stored procedure usp_get_employees_by_salary_level that receive
-- as parameter level of salary (low, average or high) and print the names
-- of all employees that have given level of salary. The result should be
-- sorted by first_name then by last_name both in descending order.
-- Submit your query statement as Run skeleton, run queries & check DB in Judge.
/*DELIMITER $$*/
CREATE PROCEDURE usp_get_employees_by_salary_level(salary_level VARCHAR(20))
BEGIN
	SELECT first_name, last_name
	FROM employees
	WHERE (
		CASE salary_level
			WHEN 'low' THEN  salary < 30000 
			WHEN 'average' THEN salary >= 30000 AND salary <= 50000
			WHEN 'high' THEN salary > 50000
		END)
	ORDER BY first_name DESC, last_name DESC;
END /*$$
DELIMITER ;*/

-- 7.Define Function
-- Define a function ufn_is_word_comprised(set_of_letters varchar(50),
-- word varchar(50))  that returns true or false depending on that if
-- the word is a comprised of the given set of letters. 
-- Submit your query statement as Run skeleton, run queries & check DB in Judge.
/* DELIMITER $$ */
CREATE FUNCTION ufn_is_word_comprised(
	set_of_letters varchar(50),
	word varchar(50))
RETURNS BIT
BEGIN
	RETURN word REGEXP CONCAT('^[', set_of_letters, ']+$');
END /*$$
DELIMITER ;*/


-- !!! Missing from problem description 8*, existing in judge
-- Solution taken from 
-- https://github.com/Tuscann/Databases-Basics---MySQL---2017/blob/master/18.EXERCISE%20FUNCTIONS%2C%20TRIGGERS%20AND%20TRANSACTIONS/sql.sql

-- Write a SQL query to delete all Employees from the Production and Production Control departments. Also delete
-- these departments from the Departments table. After that exercise restore your database to revert those changes
DELETE FROM employees_projects
WHERE employees_projects.employee_id IN
(
	SELECT e.employee_id
	FROM employees AS `e`
	WHERE e.department_id = (SELECT d.department_id FROM departments AS `d` WHERE(d.name = 'Production'))
	OR e.department_id = (SELECT d.department_id FROM departments AS `d` WHERE(d.name = 'Production Control'))
);

UPDATE employees AS `e`
SET e.manager_id = NULL
WHERE e.department_id = (SELECT d.department_id FROM departments AS `d` WHERE(d.name = 'Production'))
OR e.department_id = (SELECT d.department_id FROM departments AS `d` WHERE(d.name = 'Production Control'));

ALTER TABLE departments
MODIFY COLUMN manager_id INT NULL;

UPDATE departments AS `d`
SET d.manager_id = NULL
WHERE(d.name = 'Production' or d.name = 'Production Control');

ALTER TABLE employees
DROP FOREIGN KEY fk_employees_employees;

DELETE FROM employees
WHERE employees.department_id = (SELECT d.department_id FROM departments AS `d` WHERE(d.name = 'Production'))
OR employees.department_id = (SELECT d.department_id FROM departments AS `d` WHERE(d.name = 'Production Control'));

DELETE FROM departments
WHERE (name = 'Production' OR name = 'Production Control');



/* PART II - Queries for Bank Database */

-- 8.Find Full Name
-- You are given a database schema with tables:
-- - account_holders(id (PK), first_name, last_name, ssn) 
-- - and 
-- - accounts(id (PK), account_holder_id (FK), balance).
-- Write a stored procedure usp_get_holders_full_name that selects the
-- full names of all people. . The result should be sorted by full_name
-- alphabetically and id ascending.
-- Submit your query statement as Run skeleton, run queries & check DB in Judge.
/*DELIMITER $$*/
CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
	SELECT 
		CONCAT(ah.first_name, ' ', ah.last_name) AS 'full_name'
	FROM account_holders AS ah
    ORDER BY `full_name`, ah.id ASC;
END/*$$
DELIMITER ;*/

-- 9.People with Balance Higher Than
-- Your task is to create a stored procedure
-- usp_get_holders_with_balance_higher_than that accepts a number as a
-- parameter and returns all people who have more money in total of all
-- their accounts than the supplied number. 
-- The result should be sorted by first_name then by last_name
-- alphabetically and account id ascending. 
-- Submit your query statement as Run skeleton, run queries & check DB in Judge.
-- !!! Sorting described in the problem definition is not accepted by judge.
/*DELIMITER $$*/
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(amount DECIMAL(19,4))
BEGIN
	SELECT ah.first_name, ah.last_name
    FROM account_holders AS ah
		JOIN accounts AS a
        ON a.account_holder_id = ah.id
	GROUP BY ah.id
    HAVING SUM(a.balance) > amount
    ORDER BY a.id, ah.first_name;
END/*$$
DELIMITER ;*/

-- 10.Future Value Function
-- Your task is to create a function ufn_calculate_future_value that
-- accepts as parameters – sum, yearly interest rate and number of years.
-- It should calculate and return the future value of the initial sum.
-- Using the following formula:
-- - I – Initial sum
-- - R – Yearly interest rate
-- - T – Number of years
-- Submit your query statement as Run skeleton, run queries & check DB in Judge.
/* DELIMITER $$ */
CREATE FUNCTION ufn_calculate_future_value(
	initial_sum DECIMAL(19,4),
	interest_rate DECIMAL(8,6),
	number_of_years INT(5))
RETURNS DOUBLE
BEGIN
	DECLARE future_value DECIMAL(19,4);
    SET future_value := pow(1 + interest_rate, number_of_years) * initial_sum;
RETURN ROUND(future_value, 2);
END /*$$
DELIMITER ; */

-- with loop

/* DELIMITER $$ */
CREATE FUNCTION ufn_calculate_future_value(
	initial_sum DECIMAL(19,4),
	interest_rate DECIMAL(8,6),
	number_of_years INT(5))
RETURNS DOUBLE
BEGIN
	DECLARE future_value DECIMAL(19,4);
    SET future_value := initial_sum;
    
    label1: WHILE number_of_years <> 0 DO
		SET future_value := future_value + (future_value * interest_rate);
		SET number_of_years := number_of_years - 1;
    END WHILE label1;
	RETURN ROUND(future_value, 2);
END
/*$$
DELIMITER ; */

-- 11.Calculating Interest
-- Your task is to create a stored procedure
-- usp_calculate_future_value_for_account that uses the function from the
-- previous problem to give an interest to a person's account for 5 years,
-- along with information about his/her account id, first name, last name
-- and current balance as it is shown in the example below. It should take
-- the account_id and the interest_rate as parameters. Interest rate should
-- have precision up to 0.0001, same as the calculated balance after 5
-- years. Be extremely careful to achieve the desired precision!
-- Submit your query statement as Run skeleton, run queries & check DB in Judge.
/*DELIMITER $$*/
CREATE PROCEDURE usp_calculate_future_value_for_account(
	acc_id INT(11), 
	rate DECIMAL(19,4))
BEGIN
	DECLARE future_value DECIMAL(19,4);
    DECLARE balance DECIMAL(19,4);
    SET balance := (SELECT a.balance FROM accounts AS a WHERE a.id = acc_id);
    SET future_value := balance * (pow(1 + rate, 5));
	SELECT 
		a.id AS 'account_id',
        ah.first_name,
        ah.last_name,
        balance AS 'current_balance',
        future_value AS 'balance_in_5_years'
	FROM accounts AS a
		JOIN account_holders AS ah
		ON a.account_holder_id = ah.id
        AND
        a.id = acc_id;
END /*$$
DELIMITER ;*/

-- 12.Deposit Money
-- Add stored procedure usp_deposit_money(account_id, money_amount) that
-- operate in transactions. 
-- Make sure to guarantee valid positive money_amount with precision up to
-- fourth sign after decimal point. The procedure should produce exact
-- results working with the specified precision.
-- Submit your query statement as Run skeleton, run queries & check DB in Judge.

/*DELIMITER $$*/
CREATE PROCEDURE usp_deposit_money(
	acc_id INT(11), 
	amount DECIMAL(19,4))
BEGIN
	START TRANSACTION;
    UPDATE accounts AS a SET a.balance = a.balance + amount
	WHERE a.id = acc_id;
	IF( amount < 0)
		THEN ROLLBACK;
	ELSE
		COMMIT;
	END IF;
END /*$$
DELIMITER ;*/

-- 13.Withdraw Money
-- Add stored procedures usp_withdraw_money(account_id, money_amount) that
-- operate in transactions. 
-- Make sure to guarantee withdraw is done only when balance is enough and
-- money_amount is valid positive number. Work with precision up to fourth
-- sign after decimal point. The procedure should produce exact results
-- working with the specified precision.
-- Submit your query statement as Run skeleton, run queries & check DB in Judge.
/*DELIMITER $$*/
CREATE PROCEDURE usp_withdraw_money(
	acc_id INT(11), 
	amount DECIMAL(19,4))
BEGIN
    DECLARE c_balance DECIMAL(19,4);
    START TRANSACTION;
    SET c_balance := (SELECT balance FROM accounts WHERE id = acc_id) - amount;
    UPDATE accounts AS a SET a.balance = c_balance
	WHERE a.id = acc_id;
	IF( amount < 0 OR c_balance < 0)
		THEN ROLLBACK;
	ELSE
		COMMIT;
	END IF;
END /*$$
DELIMITER ;*/


-- 14.Money Transfer
-- Write stored procedure usp_transfer_money(from_account_id,
-- to_account_id, amount) that transfers money from one account to another.
-- Consider cases when one of the account_ids is not valid, the amount of
-- money is negative number, outgoing balance is enough or transferring
-- from/to one and the same account. Make sure that the whole procedure
-- passes without errors and if error occurs make no change in the database. 
-- Make sure to guarantee exact results working with precision up to fourth sign after decimal point.
-- Submit your query statement as Run skeleton, run queries & check DB in Judge.
/*DELIMITER $$*/
DROP PROCEDURE IF EXISTS usp_transfer_money$$
CREATE PROCEDURE usp_transfer_money(
	from_account_id INT(11), 
    to_account_id INT(11), 
	amount DECIMAL(19,4))
proc_label: BEGIN
    START TRANSACTION;
	IF (
		(SELECT id FROM accounts WHERE id = from_account_id) IS NULL
        OR
        (SELECT id FROM accounts WHERE id = to_account_id) IS NULL
        OR
		from_account_id = to_account_id
        OR 
		((SELECT balance FROM accounts WHERE id = from_account_id) - amount < 0)
        OR
        amount < 0)
	THEN
		ROLLBACK;
	ELSE
		UPDATE accounts SET balance = balance - amount
		WHERE id = from_account_id;
		UPDATE accounts SET balance = balance + amount
		WHERE id = to_account_id;
	END IF;
END /*$$
DELIMITER ;*/

-- 15.Log Accounts Trigger
-- Create another table – logs(log_id, account_id, old_sum, new_sum). Add a
-- trigger to the accounts table that enters a new entry into the logs
-- table every time the sum on an account changes.
-- Submit your query statement as Run skeleton, run queries & check DB in Judge.
CREATE TABLE `logs`(
	log_id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
    account_id INT(11), 
    old_sum DECIMAL(19,4), 
    new_sum DECIMAL(19,4)
);
/* DELIMITER $$ */
CREATE TRIGGER tr_sum_changes
AFTER UPDATE
ON accounts
FOR EACH ROW
BEGIN
	INSERT INTO `logs`
		(account_id, old_sum, new_sum)
	VALUES (
		OLD.id,
        OLD.balance,
        NEW.balance);
END /*$$
DELIMITER ; */

-- 16.Emails Trigger
-- Create another table – notification_emails(id, recipient, subject,
-- body). Add a trigger to logs table to create new email whenever new
-- record is inserted in logs table. The following data is required to be
-- filled for each email:
-- - recipient – account_id
-- - subject – “Balance change for account: {account_id}”
-- - body - “On {date (current date)} your balance was changed from {old} to {new}.”
-- Submit your query statement as Run skeleton, run queries & check DB in Judge.
USE bank;
CREATE TABLE `logs`(
	log_id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
    account_id INT(11), 
    old_sum DECIMAL(19,4), 
    new_sum DECIMAL(19,4)
);
CREATE TABLE notification_emails(
	id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
    recipient INT(11), 
    `subject` VARCHAR(255), 
    `body` TEXT
);
/* DELIMITER $$ */
CREATE TRIGGER tr_emails
AFTER UPDATE
ON accounts
FOR EACH ROW
BEGIN
	INSERT INTO `logs`
		(account_id, old_sum, new_sum)
	VALUES (
		OLD.id,
        OLD.balance,
        NEW.balance);
	INSERT INTO `notification_emails`
		(recipient, `subject`, `body`)
	VALUES (
		OLD.id,
        CONCAT('Balance change for account: ', OLD.id),
        CONCAT(
			'On ', 
            DATE_FORMAT(NOW(), '%b %e %Y at %I:%i:%s %p'),
            ' your balance was changed from ', 
            OLD.balance,
            ' to ', 
            NEW.balance, 
            '.')
		);
END /*$$
DELIMITER ; */