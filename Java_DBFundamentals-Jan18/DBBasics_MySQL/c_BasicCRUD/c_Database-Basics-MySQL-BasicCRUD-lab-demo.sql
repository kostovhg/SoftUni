--- Importing Hospital database from
-- https://softuni.bg/downloads/svn/java-databases/Sept-2017/Database-Basics-MySQL/03.%20Basic-CRUD/Lab-Tests-DB-Author-Solutions/hospital.sql

CREATE DATABASE IF NOT EXISTS `hospital`; 
USE `hospital`;

CREATE TABLE departments (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50)
);

INSERT INTO departments(name) VALUES('Therapy'), ('Support'), ('Management'), ('Other');

CREATE TABLE employees (
	id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	job_title VARCHAR(50) NOT NULL,
	department_id INT NOT NULL,
	salary DOUBLE NOT NULL,
	CONSTRAINT `fk_department_id` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`)
);

INSERT INTO `employees` (`first_name`,`last_name`, `job_title`,`department_id`,`salary`) VALUES
	('John', 'Smith', 'Therapist',1, 900.00),
	('John', 'Johnson', 'Acupuncturist',1, 880.00),
	('Smith', 'Johnson', 'Technician',2, 1100.00),
	('Peter', 'Petrov', 'Supervisor',3, 1100.00),
	('Peter', 'Ivanov', 'Dentist',4, 1500.23),
	('Ivan' ,'Petrov', 'Therapist',1, 990.00),
	('Jack', 'Jackson', 'Epidemiologist',4, 1800.00),
	('Pedro', 'Petrov', 'Medical Director',3, 2100.00),
	('Nikolay', 'Ivanov', 'Nutrition Technician',4, 1600.00);
	

	
CREATE TABLE rooms (
	id INT PRIMARY KEY AUTO_INCREMENT,
	occupation VARCHAR(30)
);

INSERT INTO rooms(`occupation`) VALUES('free'), ('occupied'),('free'),('free'),('occupied');

CREATE TABLE patients (
	id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	room_id INT NOT NULL
);

INSERT INTO patients(`first_name`,`last_name`,`room_id`) VALUES('Pesho','Petrov',1),('Gosho','Georgiev',3),('Mariya','Marieva', 2), ('Katya','Katerinova', 2), ('Nikolay','Nikolaev',3);

-- select join logic order
SELECT 
CONCAT(e.`first_name`, ' ', e.`last_name`) AS 'Пълно име',
e.`job_title` AS 'Позиция',
d.`name` AS 'Департамент',
e.`salary` AS 'Възнаграждение'
FROM `employees` AS e JOIN `departments` AS d
WHERE (e.`department_id`=d.`id`)
AND
NOT (d.`id`=3 OR d.`id`=4)
ORDER BY e.`salary` DESC;
-- range
SELECT first_name, last_name, job_title, d.name, salary
FROM employees JOIN departments AS d
WHERE department_id=d.id AND
salary BETWEEN '1000' AND '1200';
-- in
SELECT 
CONCAT(e.`first_name`, ' ', e.`last_name`) AS 'Пълно име',
	e.`job_title` AS 'Позиция',
	d.`name` AS 'Департамент',
	e.`salary` AS 'Възнаграждение'
FROM `employees` AS e JOIN `departments` AS d
WHERE 
	(e.`department_id`=d.`id`)
AND
	salary IN (880, 980, 2100)
ORDER BY e.`salary` DESC;

-- VIews - views are virtual tables made from others tables, views or joins between them
CREATE VIEW `v_Hospital_employees_salaries` AS
SELECT
CONCAT(e.`first_name`, ' ', e.`last_name`) AS 'Пълно име',
e.`job_title` AS 'Позиция',
d.`name` AS 'Департамент',
e.`salary` AS 'Възнаграждение'
FROM `employees` AS e JOIN `departments` AS d
WHERE (e.`department_id`=d.`id`)
ORDER BY e.`salary` DESC;
-- Problem: Top Paid Employee
CREATE VIEW `v_top_paid_employee`
AS
	SELECT * FROM `employees`
	ORDER BY `salary` DESC LIMIT 1;
	
-- INSERT
-- 