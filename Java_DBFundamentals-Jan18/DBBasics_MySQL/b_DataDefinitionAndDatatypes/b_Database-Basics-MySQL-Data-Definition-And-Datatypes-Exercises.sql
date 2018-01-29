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
	rate_applied VARCHAR(50) NOT NULL,
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
	(1, 1, 1, 'Excelent', 34.00, 432122, 432231, 109, '2018-01-20', '2018-01-28', 28, '90.00', 10.3, 3),
	(1, 2, 2, 'Excelent', 34.00, 432122, 432231, 109, '2018-01-20', '2018-01-28', 28, '90.00', 10.3, 3),
	(2, 3, 3, 'Excelent', 34.00, 432122, 432231, 109, '2018-01-26', '2018-01-29', 28, '90.00', 10.3, 3);