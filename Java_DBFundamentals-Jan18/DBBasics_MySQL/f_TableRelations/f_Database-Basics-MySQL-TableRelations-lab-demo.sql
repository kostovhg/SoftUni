/*
	Table Relations
	- Database Design and Rules
*/

-- Steps in Database Design
-- 1. Identification of the entities
-- 2. Defining table columns
-- 3. Defining primary keys
-- 4. Modeling relationships
-- 5. Defining constrains
-- 6. Filling

CREATE DATABASE 'TableRelation_demo';
USE `TableRelation_demo`;

CREATE TABLE mountains(
	mountain_id INT UNSIGNED PRIMARY KEY,
	mountain_name VARCHAR(50)
);

CREATE TABLE peaks(
	peak_id INT PRIMARY KEY,
	mountain_id INT UNSIGNED,
	CONSTRAINT fk_peaks_mountains
	FOREIGN KEY (mountain_id)
	REFERENCES mountains(mountain_id)
);

--- employees

CREATE TABLE employees(
	employee_id INT UNSIGNED PRIMARY KEY, 
	employee_name VARCHAR(50)
);

CREATE TABLE projects(
	project_id INT UNSIGNED PRIMARY KEY,
	project_name VARCHAR(50)
);

CREATE TABLE emploees_projects(
	employee_id INT UNSIGNED,
	project_id INT UNSIGNED,
	CONSTRAINT pk_employees_projects
	PRIMARY KEY (employee_id, project_id),
	CONSTRAINT fk_employees_projects_employees
	FOREIGN KEY (employee_id)
	REFERENCES employees(employee_id),
	CONSTRAINT fk_employees_projects_projects
	FOREIGN KEY(project_id)
	REFERENCES projects(project_id) 
);

--- students, courses and towns
CREATE DATABASE table_relations_demo;
USE table_relations_demo;

CREATE TABLE town (
	town_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL
);

CREATE TABLE course(
	course_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	town_id INT UNSIGNED NOT NULL,
	CONSTRAINT fk_course_town
	FOREIGN KEY (town_id)
	REFERENCES town(town_id)
);

CREATE TABLE students(
	student_id INT UNSIGNED PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	faculty_number VARCHAR(50) NOT NULL UNIQUE,
	photo BLOB,
	town_id INT UNSIGNED NOT NULL,
	date_of_signing DATE
);

CREATE TABLE student_course(
	student_id INT UNSIGNED NOT NULL,
	course_id  INT UNSIGNED NOT NULL,
	CONSTRAINT pk_student_course
	PRIMARY KEY (student_id, course_id),
	CONSTRAINT fk_students_course_student
	FOREIGN KEY (student_id)
	REFERENCES students(student_id) ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT fk_students_course_course
	FOREIGN KEY (course_id)
	REFERENCES course(course_id) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Drivers
CREATE TABLE drivers(
	driver_id INT UNSIGNED PRIMARY KEY,
	driver_name VARCHAR(50)
);

CREATE TABLE cars(
	car_id INT UNSIGNED PRIMARY KEY,
	driver_id INT UNSIGNED UNIQUE,
	CONSTRAINT fk_cars_drivers
	FOREIGN KEY (driver_id)
	REFERENCES drivers(driver_id)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

-- Joining
USE restaurant;

SELECT p.name, p.price, c.name AS 'category'
FROM `products` AS p
JOIN `categories` AS c
ON p.category_id = c.id;

-- Foreign key delete cascade

-- Example for CASCADE DELETE / UPDATE
CREATE DATABASE demo_cars;
USE demo_dars;
CREATE TABLE driver(
	driver_id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	driver_name VARCHAR(50)
);

CREATE TABLE cars(
	car_id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	driver_id INT(11) UNSIGNED NOT NULL UNIQUE,
	CONSTRAINT fk_car_driver FOREIGN KEY(driver_id)	
	REFERENCES driver(driver_id) ON DELETE CASCADE
);

 INSERT INTO driver(driver_name) VALUES ('gosho'), ('pesho');
 
 INSERT INTO cars(car_id, driver_id) VALUES (1, 1), (2, 2);
 
 DELETE FROM driver WHERE driver_id = 1;
 -- result
-- select * from driver;
-- +-----------+-------------+
-- | driver_id | driver_name |
-- +-----------+-------------+
-- |         2 | pesho       |
-- +-----------+-------------+
-- 
-- select * from cars;
-- +--------+-----------+
-- | car_id | driver_id |
-- +--------+-----------+
-- |      2 |         2 |
-- +--------+-----------+

