-- 1.One-To-One Relationship
/* 
Create two tables as follows. Use appropriate data types.
+---------------------------------------------------------+
|                          persons                        |
+---------------+---------------+----------+--------------+
| person_id	    | first_name    | salary   | passport_id  |
+---------------+---------------+----------+--------------+
| 1             | Roberto       | 43300.00 | 102          |
| 2             | Tom           | 56100.00 | 103          | 
| 3             | Yana          | 60200.00 | 101          |
+---------------+---------------+----------+--------------+

+-----------------------------------------+
| passports                               |
+------------------+----------------------+
| passport_id      | passport_number      |
+------------------+----------------------+
| 101              | N34FG21B             |
| 102              | K65LO4R7             |
| 103              | ZE657QP2             |
+------------------+----------------------+
	
Insert the data from the example above.
 - Alter table persons and make person_id a primary key. 
 - Create a foreign key between persons and passports by using passport_id column. 
 - Think about which passport field should be UNIQUE
 */
 
-- DROP DATABASE exercises;
-- CREATE DATABASE exercises;
--  USE exercises;
 
 CREATE TABLE passports(
	passport_id INT(11) UNSIGNED UNIQUE PRIMARY KEY,
	passport_number VARCHAR(8) NOT NULL
);

 CREATE TABLE persons(
	person_id INT(11) UNSIGNED UNIQUE AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(30) NOT NULL,
	salary DECIMAL(10,2) NOT NULL,
	passport_id INT(11) UNSIGNED UNIQUE NOT NULL
);

ALTER TABLE `persons`
	ADD CONSTRAINT `fk_person_passport` FOREIGN KEY (`passport_id`) REFERENCES `passports` (`passport_id`);

INSERT INTO passports(passport_id, passport_number)
VALUES
	(101, 'N34FG21B'),
	(102, 'K65LO4R7'),
	(103, 'ZE657QP2');

INSERT INTO persons(person_id, first_name, salary, passport_id)
VALUES
	(1, 'Roberto', 43300.00, 102),
	(2, 'Tom', 56100.00, 103), 
	(3, 'Yana', 60200.00, 101);
	
-- 2.One-To-Many Relationship
-- Create two tables as follows. Use appropriate data types.
/*
+----------+---------+-----------------+
| model_id | name    | manufacturer_id |
+----------+---------+-----------------+
|      101 | X1      |               1 |
|      102 | i6      |               1 |
|      103 | Model S |               2 |
|      104 | Model X |               2 |
|      105 | Model 3 |               2 |
|      106 | Nova    |               3 |
+----------+---------+-----------------+

+-----------------+-------+----------------+
| manufacturer_id | name  | established_on |
+-----------------+-------+----------------+
|               1 | BMW   | 01/03/1916     |
|               2 | Tesla | 01/01/2003     |
|               3 | Lada  | 01/05/1966     |
+-----------------+-------+----------------+
*/
CREATE TABLE models(
	model_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(30) NOT NULL,
	manufacturer_id INT UNSIGNED NOT NULL
);

CREATE TABLE manufacturers(
	manufacturer_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(30) NOT NULL,
	established_on DATE NOT NULL
);

ALTER TABLE models
AUTO_INCREMENT = 101,
ADD CONSTRAINT fk_model_manufacturer
FOREIGN KEY (manufacturer_id)
REFERENCES manufacturers(manufacturer_id);

INSERT INTO manufacturers(`name`, established_on)
VALUES
	('BMW', '19160301'),
	('Tesla','20030101'),
	('Lada', '19660501');

INSERT INTO models(name, manufacturer_id)
VALUES	
	('X1', 1),
	('i6', 1),
	('Model S', 2),
	('Model X', 2),
	('Model 3', 2),
	('Nova', 3);
	
	
-- 3.Many-To-Many Relationship
-- Create three tables as follows. Use appropriate data types.
/*
students
+------------+------+
| student_id | name |
+------------+------+
|          1 | Mila |
|          2 | Toni |
|          3 | Ron  |
+------------+------+
exams
+---------+------------+
| exam_id | name       |
+---------+------------+
|     101 | Spring MVC |
|     102 | Neo4j      |
|     103 | Oracle 11g |
+---------+------------+
students_exams
+------------+---------+
| student_id | exam_id |
+------------+---------+
|          1 |     101 |
|          2 |     101 |
|          1 |     102 |
|          2 |     102 |
|          2 |     103 |
|          3 |     103 |
+------------+---------+
*/
-- Insert the data from the example above.
-- Add primary keys and foreign keys.
-- Have in mind that table student_exams should have a composite primary key.
-- Submit your queries by using “MySQL run queries & check DB” strategy.
CREATE TABLE students
(
	student_id INT AUTO_INCREMENT PRIMARY KEY,
	name NVARCHAR(50)
);

CREATE TABLE exams
(
	exam_id INT AUTO_INCREMENT PRIMARY KEY,
	name NVARCHAR(50)
);
ALTER TABLE exams AUTO_INCREMENT = 101;

CREATE TABLE students_exams
(
	student_id INT,
	exam_id INT,
	CONSTRAINT pk_students_exams PRIMARY KEY (student_id, exam_id),
	CONSTRAINT fk_students_exams_students FOREIGN KEY (student_id) REFERENCES students(student_id),
	CONSTRAINT fk_students_exams_exams FOREIGN KEY (exam_id) REFERENCES exams(exam_id)
);

INSERT INTO students(name) VALUES
('Mila'), ('Toni'), ('Ron');

INSERT INTO exams(name) VALUES
('Spring MVC'), ('Neo4j'), ('Oracle 11g');

INSERT INTO students_exams(student_id, exam_id) VALUES
(1, 101), (1, 102), (2, 101), (3, 103), (2, 102), (2, 103);


-- 4.Self-Referencing
-- Create a single table as follows. Use appropriate data types.
/*
+------------+--------+------------+
| teacher_id | name   | manager_id |
+------------+--------+------------+
|        101 | John   |            |
|        102 | Maya   |        106 |
|        103 | Silvia |        106 |
|        104 | Ted    |        105 |
|        105 | Mark   |        101 |
|        106 | Greta  |        101 |
+------------+--------+------------+
*/
-- Insert the data from the example above.
-- Add primary keys and foreign keys.
-- The foreign key should be between manager_id and teacher_id.
CREATE TABLE teachers(
	teacher_id INT(11) AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(30) NOT NULL,
	manager_id INT(11)
);

ALTER TABLE teachers
AUTO_INCREMENT = 101,
ADD CONSTRAINT fk_teacher_manager
FOREIGN KEY (manager_id)
REFERENCES teachers(teacher_id);

INSERT INTO teachers(name)
VALUES 
	('John'),
	('Maya'),
	('Silvia'),
	('Ted'),
	('Mark'),
	('Greta');
	
UPDATE teachers
SET manager_id = 106
WHERE teacher_id IN (102, 103);

UPDATE teachers
SET manager_id = 101
WHERE teacher_id IN (105, 106);

UPDATE teachers
SET manager_id = 105
WHERE teacher_id = 104;

-- 5.Online Store Database
-- Create a new database and design the following structure:
DROP database IF EXISTS online_store;

CREATE DATABASE online_store;
USE online_store;

CREATE TABLE item_types(
	item_type_id INT(11) AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(50)
);

CREATE TABLE items(
	item_id INT(11) AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(50),
	item_type_id INT(11),
	CONSTRAINT fk_item_item_type
	FOREIGN KEY (item_type_id)
	REFERENCES item_types(item_type_id)
);

CREATE TABLE cities(
	city_id INT(11) AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(50)
);

CREATE TABLE customers(
	customer_id INT(11) AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(50),
	birthday DATE,
	city_id INT(11),
	CONSTRAINT fk_customer_city
	FOREIGN KEY (city_id)
	REFERENCES cities(city_id)
);

CREATE TABLE orders(
	order_id INT(11) AUTO_INCREMENT PRIMARY KEY,
	customer_id INT(11),
	CONSTRAINT fk_orders_customer
	FOREIGN KEY (customer_id)
	REFERENCES customers(customer_id)
);

CREATE TABLE order_items(
	order_id INT(11),
	item_id INT(11),
	CONSTRAINT pk_oder_id_item_id
	PRIMARY KEY (order_id, item_id),
	CONSTRAINT fk_order_items_order_id
	FOREIGN KEY (order_id)
	REFERENCES orders(order_id),
	CONSTRAINT fk_order_item_item_id
	FOREIGN KEY (item_id)
	REFERENCES items(item_id)
);

-- 6.University Database
-- Create a new database and design the following structure:
DROP DATABASE IF EXISTS university;
CREATE DATABASE university;
USE university;

CREATE TABLE subjects(
	subject_id INT(11) AUTO_INCREMENT PRIMARY KEY,
	subject_name VARCHAR(50)
);

CREATE TABLE majors(
	major_id INT(11) AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(50)
);

CREATE TABLE students(
	student_id INT(11) AUTO_INCREMENT PRIMARY KEY,
	student_number VARCHAR(12),
	student_name VARCHAR(50),
	major_id INT(11),
	CONSTRAINT fk_student_major
	FOREIGN KEY (major_id)
	REFERENCES majors(major_id)
);

CREATE TABLE agenda(
	student_id INT(11),
	subject_id INT(11),
	CONSTRAINT pk_student_id_subject_id
	PRIMARY KEY (student_id, subject_id),
	CONSTRAINT fk_agenda_student
	FOREIGN KEY (student_id)
	REFERENCES students(student_id),
	CONSTRAINT fk_agenda_subject
	FOREIGN KEY (subject_id)
	REFERENCES subjects(subject_id)
);

CREATE TABLE payments(
	payment_id INT(11) AUTO_INCREMENT PRIMARY KEY,
	payment_date DATE,
	payment_amount DECIMAL(8,2),
	student_id INT(11),
	CONSTRAINT fk_payment_student
	FOREIGN KEY (student_id)
	REFERENCES students(student_id)
);

-- 9.Peaks in Rila
-- Display all peaks for "Rila" mountain_range. Include:
-- - mountain_range
-- - peak_name
-- - peak_elevation
-- Peaks should be sorted by peak_elevation descending.
SELECT
	m.mountain_range,
	p.peak_name,
	p.elevation AS 'peak_elevation'
	from peaks as p left join mountains as m
	on p.mountain_id = m.id
	WHERE m.mountain_range = 'Rila'
	ORDER BY `peak_elevation` DESC;


