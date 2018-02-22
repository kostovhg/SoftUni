DROP DATABASE IF EXISTS report_service;
CREATE DATABASE IF NOT EXISTS report_service;
USE report_service;
CREATE TABLE users(
	id INT(11) UNSIGNED PRIMARY KEY,
    `username` VARCHAR(30) UNIQUE NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `name` VARCHAR(50),
    `gender` VARCHAR(1),
    `birthdate` DATETIME,
    `age` INT(11) UNSIGNED,
    `email` VARCHAR(50) NOT NULL
);
CREATE TABLE departments(
	id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL
);
CREATE TABLE employees(
	id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(25),
    `last_name` VARCHAR(25),
    `gender` VARCHAR(1),
    `birthdate` DATETIME,
    `age` INT(11) UNSIGNED,
    `department_id` INT(11) UNSIGNED NOT NULL,
    CONSTRAINT fk_employee_department
    FOREIGN KEY (department_id)
    REFERENCES departments(id)
);
CREATE TABLE categories(
	id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(50) NOT NULL,
	`department_id` INT(11) UNSIGNED,
    CONSTRAINT fk_category_department
    FOREIGN KEY (department_id)
    REFERENCES departments(id)
);
CREATE TABLE `status`(
	id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `label` VARCHAR(30) NOT NULL
);
CREATE TABLE reports(
	id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    category_id INT(11) UNSIGNED NOT NULL,
	status_id INT(11) UNSIGNED NOT NULL,
	open_date DATETIME NOT NULL,
	close_date DATETIME,
	description VARCHAR(200),
	user_id INT(11) UNSIGNED NOT NULL,
	employee_id INT(11) UNSIGNED,
    CONSTRAINT fk_report_category
    FOREIGN KEY (category_id)
    REFERENCES categories(id),
    CONSTRAINT fk_report_status
    FOREIGN KEY (status_id)
    REFERENCES `status`(id),
    CONSTRAINT fk_report_user
    FOREIGN KEY (user_id)
    REFERENCES `users`(id),
	CONSTRAINT fk_report_employee
    FOREIGN KEY (employee_id)
    REFERENCES `employees`(id)
);