-- 1. Mountains and Peaks
-- Write a query to create two tables – mountains and peaks and link their fields properly. Tables should have:
-- -Mountains:
-- -- id 
-- -- name
-- -Peaks: 
-- -- id
-- -- name
-- -- mountain_id
CREATE TABLE mountains(
	id INT UNSIGNED PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TABLE peaks(
	id INT PRIMARY KEY,
	name VARCHAR(50),
	mountain_id INT UNSIGNED,
	CONSTRAINT fk_peaks_mountains
	FOREIGN KEY (mountain_id)
	REFERENCES mountains(id)
);

-- 2. Posts and Authors
-- Write a query to create a one-to-many relationship between a table,
-- holding information about books and other -about authors,
-- so that when an author gets removed from the database
-- all his books are deleted too. The tables should have:
-- -Books
-- -- id
-- -- name  
-- -- author_id
-- - Authors
-- -- id
-- -- name
-- Submit your queries using the “MySQL run queries & check DB” strategy.
CREATE TABLE authors (
	id INT(11) UNSIGNED PRIMARY KEY,
	name VARCHAR(50) NOT NULL
);

CREATE TABLE books(
	id INT(11) UNSIGNED PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	author_id INT(11) UNSIGNED NOT NULL,
	CONSTRAINT fk_books_authors
	FOREIGN KEY (author_id)
	REFERENCES authors(id)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

-- 3. Trip Organization
-- Write a query to retrieve information about the SoftUni camp’s
-- transportation organization. Get information about the people
-- who drive(name and age) and their vehicle type.
-- Submit your queries using the “MySQL prepare DB and Run Queries” strategy.
SELECT
	v.driver_id,
	v.vehicle_type,
	CONCAT(c.`first_name`, ' ', c.`last_name`) AS 'driver_name'
FROM
	`vehicles` AS v
	JOIN 
	`campers` AS c
ON
	v.driver_id = c.id;

-- 4. SoftUni Hiking
-- Get information about the hiking routes and their leaders – name and id.
-- Submit your queries using the “MySQL prepare DB and Run Queries” strategy.
SELECT
    r.`starting_point` AS 'route_starting_point',
	r.`end_point` AS 'route_ending_point',
	r.`leader_id`,
	CONCAT (c.`first_name`, ' ', c.`last_name`) AS 'learder_name'
FROM 
	`routes` AS r
JOIN
	`campers` AS c
ON
	r.`leader_id` = c.`id`;
	
-- 5. Project Management DB*
-- Write a query to create a project management db according to the following -- E/R Diagram:
/*

														╔═══════════════════════════════╗
														║	employees					║
														╟───────────────────────────────╢
														║ pk id INT(11)					║
											+---------||║    client_name VARCHAR(100)	║
											|			║ fk project_id INT(11)			║
											|			╚═══════════════════════════════╝
											|							V
											|							-			
											=							|
											^							|
							╔═══════════════════════════════╗			|
                            ║    employees					║			|
							╟───────────────────────────────╢			|
                            ║ pk id INT(11)					║			|
				+---------|<║ fk client_id INT(11)			║ ||--------+
				|           ║ fk project_lead_id INT(11)	║
				|           ╚═══════════════════════════════╝
				|							=
				|							|			
				=							|
╔═══════════════════════════════╗			|
║    employees					║			|
╟───────────────────────────────╢			|
║ pk id INT(11)					║			|
║    first_name VARCHAR(30)		║>|---------+
║    last_name VARCHAR(30)		║
║ fk project_id INT(11)			║
╚═══════════════════════════════╝
*/

CREATE TABLE employees (
	id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(30) NOT NULL
);

CREATE TABLE clients (
	id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	client_name VARCHAR(100) NOT NULL
);

CREATE TABLE projects (
	id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	client_id INT(11) UNSIGNED, 
	project_lead_id INT(11) UNSIGNED,
	CONSTRAINT fk_project_client FOREIGN KEY (client_id) REFERENCES clients(id),
	CONSTRAINT fk_projects_employee FOREIGN KEY (project_lead_id) REFERENCES employees(id)
);

ALTER TABLE employees ADD project_id INT(11) UNSIGNED NOT NULL;
ALTER TABLE employees 
ADD CONSTRAINT fk_employee_project FOREIGN KEY (project_id) REFERENCES projects(id);

ALTER TABLE clients ADD project_id INT(11) UNSIGNED NOT NULL;
ALTER TABLE clients
ADD CONSTRAINT fk_clients_project FOREIGN KEY (project_id) REFERENCES projects(id);
