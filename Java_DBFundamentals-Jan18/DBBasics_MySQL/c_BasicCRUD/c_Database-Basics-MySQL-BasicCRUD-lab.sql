-- Pasting Hospital database
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

-- 1. Select Employee Information
SELECT e.`id`, e.`first_name`, e.`last_name`, e.`job_title` 
FROM `employees` AS e
WHERE `id` > -1 
ORDER BY `id` ASC;

-- 2. Select Employees with Filter:
SELECT 
	e.`id`,
	CONCAT(e.`first_name`, ' ', e.`last_name`) AS 'full_name',
	e.`job_title`,
	e.`salary`
FROM `employees` AS e
WHERE `salary` > 1000
ORDER BY `id`;

-- 3. Update Employees Salary:
UPDATE employees AS e
SET e.`salary` = e.`salary` * 1.1
WHERE e.`job_title` = 'Therapist';
SELECT e.`salary` FROM `employees` AS e
ORDER BY e.`salary`;

-- 4. Top Paid Employee:
SELECT * FROM `employees` AS e
ORDER BY e.`salary` DESC
LIMIT 1;

-- 5. Select Employees by Multiple Filters:
SELECT * FROM `employees` AS e
WHERE e.`department_id` = 4 AND e.`salary` >= 1600
ORDER BY e.`id` ;

-- 6. Delete from Table:
DELETE FROM `employees`
WHERE `department_id` = 2 OR `department_id` = 1;
SELECT * FROM `employees` AS e
ORDER BY e.`id`;