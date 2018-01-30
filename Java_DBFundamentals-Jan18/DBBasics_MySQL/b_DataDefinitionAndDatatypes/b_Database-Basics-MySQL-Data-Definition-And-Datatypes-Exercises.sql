-- 1. Create Database
-- CREATE DATABASE javadbbasics_datadefinitionanddatatypes_exercises; -- no such task in judge
-- 2. Create Table
CREATE TABLE minions (
	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	age INT(3) UNSIGNED NULL
	) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE towns (
	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL
	) ENGINE=InnoDB COLLATE=utf8_general_ci;
-- 3. Alter Minions Table -- JUDGE does not accept "UNSIGNED"!?
ALTER TABLE minions ADD town_id INT(11) NOT NULL DEFAULT 0;
ALTER TABLE minions ADD CONSTRAINT fk_minions_town FOREIGN KEY (town_id) REFERENCES towns(id);
-- 4. Insert Records in Both Tables -- JUDGE does not have auto increment on id's
INSERT INTO towns (id, name) VALUES
	(1, 'Sofia'),
	(2, 'Plovdiv'),
	(3, 'Varna');
INSERT INTO minions (id, name, age, town_id) VALUES
	(1, 'Kevin', 22, 1),
	(2, 'Bob', 15, 3),
	(3, 'Steward', NULL, 2);
-- 5. Truncate Table minions;
TRUNCATE TABLE minions;
-- 6. Drop ALl Tables
DROP TABLE minions;
DROP TABLE towns;
-- 7. Create Table People:
CREATE TABLE people (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(200) NOT NULL,
	picture BLOB NULL DEFAULT NULL,
	height FLOAT(3,2) UNSIGNED NULL DEFAULT NULL,
	weight FLOAT(5,2) UNSIGNED NULL DEFAULT NULL,
	gender ENUM('m', 'f') NOT NULL,
	birthdate DATE NOT NULL,
	biography LONGTEXT NULL DEFAULT NULL) ENGINE=InnoDB COLLATE=utf8_general_ci;
INSERT INTO people (name, height, weight, gender, birthdate) VALUES
	('Georgi Atanasov Ganchev', 1.72, 95.5, 'm', '1998-10-11'),
	('Asend Georgiev Stamatov', 1.68, 82.3, 'm', '1986-02-05'),
	('Stefka Georgieva Peshova', 1.65, 62.6, 'f', '1995-06-15'),
	('Ivanka Petrova Papazova', 1.69, 58.9, 'f', '1999-10-05'),
	('Pesho Petrov Smironv', 1.83, NULL, 'm', '2002-08-03');
-- 8. Create Table Users: (According the zero test in judge, is_deleted should allow NULL!)
CREATE TABLE users (
	id BIGINT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(30) UNIQUE NOT NULL,
	password VARCHAR(26) NOT NULL,
	profile_picture BLOB NULL DEFAULT NULL,
	last_login_time TIMESTAMP NULL DEFAULT NULL,
	is_deleted BIT(1) NULL DEFAULT 0
	) ENGINE=InnoDB COLLATE=utf8_general_ci;
INSERT INTO users (username, password) VALUES
	('pesho999', 'mnogotainaparola'),
	('gosho_master', 'qwert'),
	('diana_88', 'brm5sd'),
	('victor_oz', '12345'),
	('strange', 'password');
-- 9. CHange Primary Key: (first auto_increment should be removed)
-- ALTER TABLE users MODIFY id BIGINT NOT NULL;
-- ALTER TABLE users DROP PRIMARY KEY;
ALTER TABLE users DROP PRIMARY KEY, ADD PRIMARY KEY(id, username);
-- 10. Set Default Value of a Field;
ALTER TABLE users MODIFY last_login_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL;
-- 11. Set Unique Field:
ALTER TABLE users DROP PRIMARY KEY, ADD PRIMARY KEY (id);
ALTER TABLE users MODIFY username VARCHAR(30) UNIQUE NOT NULL;
-- 12. Movies Database
--CREATE DATABASE Movies;
--USE movies;
CREATE TABLE directors (
	id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	director_name VARCHAR(50) NOT NULL,
	notes TEXT) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE genres (
	id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	genre_name VARCHAR(50) NOT NULL,
	notes TEXT) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE categories (
	id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	category_name VARCHAR(50) NOT NULL,
	notes TEXT) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE movies (
	id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(50) NOT NULL,
	director_id INT(11) UNSIGNED NOT NULL,
	copyright_year YEAR NOT NULL,
	`length` TIME NOT NULL,
	genre_id INT(11) UNSIGNED NOT NULL,
	category_id INT(11) UNSIGNED NOT NULL,
	rating FLOAT(2,1) UNSIGNED,
	notes TEXT) ENGINE=InnoDB COLLATE=utf8_general_ci;
INSERT INTO directors (director_name) VALUES
	('Steven Spielberg'),
	('Martin Scorsese'),
	('Alfred Hitchcock'),
	('Stanley Kubrick'),
	('Quentin Tarantino');
INSERT INTO genres (genre_name) VALUES
	('Sci-Fi'),
	('Action'),
	('Comedy'),
	('Horror'),
	('Crime');
INSERT INTO categories (category_name) VALUES
	('Category 1'),
	('Category 2'),
	('Category 3'),
	('Category 4'),
	('Category 5');
INSERT INTO movies (title, director_id, copyright_year, `length`, genre_id, category_id) VALUES
	('Jurassic Park', 1, 1993, '020700', 1, 1),
	('A.I. Artificial Intelligence', 1, 2001, '022600', 1, 1),
	('Taxi Driver', 2, 1976, '015400', 5, 1),
	('A Space Odyssey', 4, 1968,'022900', 1, 1),
	('Reservoir Dogs', 5, 1992, '013900', 2, 1);
-- 13.Car Rental Database :
-- CREATE DATABASE car_rental;
-- USE var_rental;
CREATE TABLE categories (
	id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	category VARCHAR(10) NOT NULL,
	daily_rate DECIMAL(10,3) NOT NULL,
	weekly_rate DECIMAL(10,3),
	monthly_rate DECIMAL(10,3),
	weekend_rate DECIMAL(10,3)) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE cars (
	id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	plate_number VARCHAR(20) NOT NULL UNIQUE,
	make VARCHAR(50) NOT NULL,
	model VARCHAR(50) NOT NULL,
	car_year YEAR NOT NULL, 
	category_id INT(11) UNSIGNED NOT NULL,
	doors INT(1) NOT NULL,
	picture BLOB,
	car_condition VARCHAR(50),
	available BIT(1) NOT NULL DEFAULT 1) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE employees (
	id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	title VARCHAR(50) NOT NULL,
	notes TEXT) ENGINE=InnoDB COLLATE=utf8_general_ci;
-- If driver_licence_number in customers is UNIQUE, 4-th test in judge does not pass.
CREATE TABLE customers (
	id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	driver_licence_number VARCHAR(10) NOT NULL,
	full_name VARCHAR(50) NOT NULL,
	address VARCHAR(50) NOT NULL,
	city VARCHAR(50) NOT NULL,
	zip_code VARCHAR(10) NOT NULL,
	notes TEXT) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE rental_orders (
	id INT(20) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	employee_id INT(11) UNSIGNED NOT NULL,
	customer_id INT(11) UNSIGNED NOT NULL,
	car_id INT(11) UNSIGNED NOT NULL,
	car_condition VARCHAR(30),
	tank_level FLOAT(5,2) UNSIGNED NOT NULL,
	kilometrage_start INT(10) UNSIGNED NOT NULL,
	kilometrage_end INT(10) UNSIGNED NOT NULL,
	total_kilometrage INT(10) UNSIGNED NOT NULL,
	start_date DATE NOT NULL,
	end_date DATE NOT NULL,
	total_days INT(3) NOT NULL,
	rate_applied DECIMAL NOT NULL DEFAULT 0,
	tax_rate DECIMAL(5,2) NOT NULL,
	order_status ENUM('Reserved', 'Rented', 'Free') NOT NULL,
	notes TEXT) ENGINE=InnoDB COLLATE=utf8_general_ci;
INSERT INTO categories(category, daily_rate, weekly_rate, monthly_rate, weekend_rate)
	VALUES
	('Standard', 85.20, 70.00, 67.50, 90.00),
	('Improved', 90.00, 85.00, 80.00, 95.00),
	('Luxory', 120.00, 112.56, 105.50, 130.00);
INSERT INTO cars (plate_number, make, model, car_year, category_id, doors, car_condition) 
	VALUES
	('SF-1234-BB', 'BMW', 'model', 2010, 1, 2, 'Excelend'),
	('BN-3210-KK', 'Ford', 'model', 2010, 2, 4, 'Excelend'),
	('SF-1234-AA', 'OPEL', 'model', 2010, 3, 2, 'Excelend');
INSERT INTO employees (first_name, last_name, title) 
	VALUES
	('Ivan', 'Petrov','Salesman'),
	('Petar', 'Ivnanov','Salesman'),
	('Georgi', 'Stamenov','Manager');
INSERT INTO customers (driver_licence_number, full_name, address, city, zip_code) 
	VALUES
	('321654987', 'Grigor Atanasov', 'Tsarigradsko shose 192', 'Sofia', '1000'),
	('987654321', 'Stanimir Laskov', 'Pobeda 13', 'Sofia', '1000'),
	('354813572', 'Marting Kisiov', 'Malina 32', 'Sofia', '1000');
INSERT INTO rental_orders (
	employee_id, 
	customer_id, 
	car_id, 
	car_condition, 
	tank_level, 
	kilometrage_start, 
	kilometrage_end, 
	total_kilometrage,
	start_date, 
	end_date, 
	total_days,
	rate_applied, 
	tax_rate, 
	order_status)
	VALUES
	(1, 1, 1, 'Excelent', 34.00, 432122, 432231, 109, '2018-01-20', '2018-01-28', 28, 90.00, 10.3, 3),
	(1, 2, 2, 'Excelent', 34.00, 432122, 432231, 109, '2018-01-20', '2018-01-28', 28, 90.00, 10.3, 3),
	(2, 3, 3, 'Excelent', 34.00, 432122, 432231, 109, '2018-01-26', '2018-01-29', 28, 90.00, 10.3, 3);
	
-- 14. Hotel Database
CREATE DATABASE Hotel
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
  USE Hotel;
CREATE TABLE employees (
	id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	title VARCHAR(50) NOT NULL,
	notes TEXT);
CREATE TABLE customers (
	account_number VARCHAR(30) UNIQUE PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	phone_number VARCHAR(50) NOT NULL,
	emergency_name VARCHAR(50) NOT NULL,
	emergency_number VARCHAR(50),
	notes TEXT);
CREATE TABLE room_status (
	room_status VARCHAR(30) UNIQUE PRIMARY KEY,
	notes TEXT);
CREATE TABLE room_types (
	room_type VARCHAR(30) UNIQUE PRIMARY KEY,
	notes TEXT);
CREATE TABLE bed_types (
	bed_type VARCHAR(30) UNIQUE PRIMARY KEY,
	notes TEXT);
CREATE TABLE rooms (
	room_number VARCHAR(10) UNIQUE PRIMARY KEY,
	room_type VARCHAR(30) NOT NULL,
	bed_type VARCHAR(30) NOT NULL,
	rate VARCHAR(30),
	room_status VARCHAR(30) NOT NULL,
	notes TEXT);
CREATE TABLE payments (
	id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	employee_id INT(11) UNSIGNED NOT NULL,
	payment_date DATE NOT NULL,
	account_number VARCHAR(30) NOT NULL,
	first_date_occupied DATE NOT NULL,
	last_date_occupied DATE NOT NULL,
	total_days INT(4) NOT NULL,
	amount_charged DECIMAL(11,2) NOT NULL,
	tax_rate DECIMAL(11,2),
	tax_amount DECIMAL(11,2),
	payment_total DECIMAL(11,2),
	notes TEXT);
CREATE TABLE occupancies (
	id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	employee_id INT(11) UNSIGNED NOT NULL,
	date_occupied DATE NOT NULL,
	account_number VARCHAR(30) NOT NULL,
	room_number VARCHAR(10) NOT NULL,
	rate_applied DECIMAL(11,2) NOT NULL,
	phone_charge DECIMAL(11,2) NOT NULL DEFAULT 0,
	notes TEXT);
INSERT INTO employees (first_name, last_name, title, notes)
VALUES
	('Ivan', 'Petrov','Hotel Manager', 'New in the business'),
	('Petar', 'Ivnanov','Receptionist', NULL),
	('Georgi', 'Stamenov','Chambermaid', NULL);
INSERT INTO customers (account_number, first_name, last_name, phone_number, emergency_name, emergency_number, notes)
VALUES
	('321654987BUGBI43', 'Grigor', 'Atanasov', '+359 888 888 888', 'None', '1000', NULL),
	('987654321ASPN332', 'Stanimir', 'Laskov', '+359 888 888 882', 'None', '1000', NULL),
	('354813572BUIGBI1', 'Marting', 'Kisiov', '+359 888 777 666', 'None', '1000', NULL);
INSERT INTO room_status (room_status, notes)
VALUES
	('Ocuppied', NULL),
	('Ready', NULL),
	('For service', NULL);
INSERT INTO room_types (room_type, notes)
VALUES
	('Standard', NULL),
	('Apartment', NULL),
	('Extra', NULL);
INSERT INTO bed_types (bed_type, notes)
VALUES
	('Single', NULL),
	('Double', NULL),
	('Round', NULL);
INSERT INTO rooms (room_number, room_type, bed_type, rate, room_status, notes)
VALUES
	('A - 101', 'Standard', 'Single', NULL, 'Ready', NULL),
	('A - 102', 'Standard', 'Single', NULL, 'Ready', NULL),
	('A - 103', 'Standard', 'Single', NULL, 'Ready', NULL);
INSERT INTO payments (employee_id, payment_date, account_number, first_date_occupied, last_date_occupied, total_days, amount_charged, tax_rate, tax_amount, payment_total, notes) VALUES
	(1, '2018-01-25', 'S090921', '2018-01-10', '2018-01-25', 15, 263.15, 10.5, 27.63, 290.78, NULL),
	(2, '2018-01-25', 'S09095', '2018-01-10', '2018-01-25', 15, 263.15, 10.5, 27.63, 290.78, NULL),
	(3, '2018-01-25', 'S090971', '2018-01-10', '2018-01-25', 15, 263.15, 10.5, 27.63, 290.78, NULL);
INSERT INTO occupancies (employee_id, date_occupied, account_number, room_number, rate_applied, phone_charge, notes)
VALUES
	(2, '2018-01-15', 'S090921', 'A - 101', 85.50, 15.00, NULL),
	(2, '2018-01-15', 'S090921', 'A - 101', 85.50, 15.00, NULL),
	(2, '2018-01-15', 'S090921', 'A - 101', 85.50, 15.00, NULL);

-- 15. Create SoftUni Database:
CREATE DATABASE soft_uni 
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
  USE soft_uni;
	-- create tables
CREATE TABLE towns (
	id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	PRIMARY KEY (id));
CREATE TABLE addresses (
	id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	address_text VARCHAR(50) NOT NULL,
	town_id INT(11) UNSIGNED NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (town_id) REFERENCES towns(id));
CREATE TABLE departments (
	id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	PRIMARY KEY (id));
CREATE TABLE employees (
	id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(50) NOT NULL,
	middle_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	job_title VARCHAR(50) NOT NULL,
	department_id INT(11) UNSIGNED NOT NULL,
	hire_date DATE NOT NULL,
	salary DECIMAL(10,2) NOT NULL DEFAULT 510.00,
	address_id INT(11) UNSIGNED,
	PRIMARY KEY (id),
	FOREIGN KEY (department_id) REFERENCES departments(id),
	FOREIGN KEY (address_id) REFERENCES addresses(id));
-- 16. Backup Database:
-- mysqldump -u root -p soft_uni > g:\softuni_backup.sql
-- 17. Basic Insert:
-- USE soft_uni;
INSERT INTO towns (name) 
VALUES
	('Sofia'), ('Plovdiv'), ('Varna'), ('Burgas');
INSERT INTO departments (name)
VALUES
	('Engineering'), ('Sales'), ('Marketing'), ('Software Development'), ('Quality Assurance');
INSERT INTO employees (first_name, middle_name, last_name, job_title, department_id, hire_date, salary)
VALUES
	('Ivan', 'Ivanov', 'Ivanov', '.NET Developer', 4, '2013-02-01', 3500.00),
	('Petar', 'Petrov', 'Petrov', 'Senior Engineer', 1, '2004-03-02', 4000.00),
	('Maria', 'Petrova', 'Ivanova', 'Intern', 5, '2016-08-28', 525.25),
	('Georgi', 'Terziev', 'Ivanov', 'CEO', 2, '2007-12-09' , 3000.00),
	('Peter', 'Pan', 'Pan', 'Intern', 3, '2016-08-28', 599.88);
-- 18. Basic Select All Fields:
SELECT * FROM towns;
SELECT * FROM departments;
SELECT * FROM employees;
-- 19. Basic Select ALl FIelds and Order THem:
SELECT * FROM towns ORDER BY name ASC;
SELECT * FROM departments ORDER BY name ASC;
SELECT * FROM employees ORDER BY salary DESC;
-- 20. Basic Select Some Fields:
SELECT name FROM towns ORDER BY name ASC;
SELECT name FROM departments ORDER BY name ASC;
SELECT first_name, last_name, job_title, salary FROM employees ORDER BY salary DESC;
-- 21. Increase Employees Salary:
UPDATE employees
SET salary = salary * 1.1
WHERE id > -1;
SELECT salary FROM employees;
-- 22. Decrease Tax Rate:
-- USE Hotel;
-- UPDATE payments
-- SET tax_rate = tax_rate - (tax_rate * 3.5 / 100)
-- WHERE id > 0;
-- SELECT tax_rate FROM payments;
SELECT tax_rate * 0.97 FROM payments;
-- 23. Delete All REcords:
DELETE * FROM occupancies;