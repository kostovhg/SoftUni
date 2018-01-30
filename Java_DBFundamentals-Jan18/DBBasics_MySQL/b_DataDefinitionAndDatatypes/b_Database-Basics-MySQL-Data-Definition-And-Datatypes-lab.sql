-- 1. Create New Database:
CREATE DATABASE gamebar;
-- Use newly created database:
USE gamebar;

-- 2. Create Table:
CREATE TABLE employees (
	id INT(11) UNSIGNED AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id));
CREATE TABLE categories (
	id INT(11) UNSIGNED AUTO_INCREMENT NOT NULL,
    `name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (id));
CREATE TABLE products (
	id INT(11) UNSIGNED AUTO_INCREMENT NOT NULL,
    `name` VARCHAR(50) NOT NULL,
    category_id INT(11) UNSIGNED NOT NULL,
    PRIMARY KEY (id)
    -- FOREIGN KEY (category_id) REFERENCES categories(id));
    );
	
-- 3. Insert Data in Tables:
INSERT INTO employees (first_name, last_name)
VALUES
	('Ivan', 'Ivanov'),
    ('Georgi', 'Georgiev'),
    ('Stanimir', 'Stanimirov');
	
-- 4. Altering Tables:
ALTER TABLE employees
ADD COLUMN
	middle_name VARCHAR(50) NOT NULL;
	
-- 5. Adding Constraints:
ALTER TABLE products
ADD CONSTRAINT FK_product_categories
FOREIGN KEY (category_id) REFERENCES categories(id);

-- 6. Modifying Columns:
ALTER TABLE employees
MODIFY middle_name VARCHAR(100) NOT NULL;

-- 7. Drop Database:
DROP DATABASE gamebar;