CREATE TABLE towns (
	town_id INT,
	town_name VARCHAR(30) NOT NULL,
	CONSTRAINT PK_towns PRIMARY KEY(town_id)
);

CREATE TABLE airports (
	airport_id INT,
	airport_name VARCHAR(50) NOT NULL,
	town_id INT NOT NULL,
	CONSTRAINT PK_airports PRIMARY KEY(airport_id),
	CONSTRAINT FK_airports_towns FOREIGN KEY(town_id) REFERENCES towns(town_id)
);

CREATE TABLE airlines (
	airline_id INT,
	airline_name VARCHAR(30) NOT NULL,
	nationality VARCHAR(30) NOT NULL,
	rating INT DEFAULT 0,
	CONSTRAINT PK_airlines PRIMARY KEY(airline_id)
);

CREATE TABLE customers (
	customer_id INT,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	date_of_birth DATE NOT NULL,
	gender VARCHAR(1) NOT NULL CHECK (Gender='M' OR Gender='F'),
	home_town_id INT NOT NULL,
	CONSTRAINT PK_customers PRIMARY KEY(customer_id),
	CONSTRAINT FK_customers_towns FOREIGN KEY(home_town_id) REFERENCES towns(town_id)
);


INSERT INTO towns(town_id, town_name)
VALUES
(1, 'Sofia'),
(2, 'Moscow'),
(3, 'Los Angeles'),
(4, 'Athene'),
(5, 'New York');

INSERT INTO airports(airport_id, airport_name, town_id)
VALUES
(1, 'Sofia International Airport', 1),
(2, 'New York Airport', 5),
(3, 'Royals Airport', 1),
(4, 'Moscow Central Airport', 2);

INSERT INTO airlines(airline_id, airline_name, nationality, rating)
VALUES
(1, 'Royal Airline', 'Bulgarian', 200),
(2, 'Russia Airlines', 'Russian', 150),
(3, 'USA Airlines', 'American', 100),
(4, 'Dubai Airlines', 'Arabian', 149),
(5, 'South African Airlines', 'African', 50),
(6, 'Sofia Air', 'Bulgarian', 199),
(7, 'Bad Airlines', 'Bad', 10);

INSERT INTO customers(customer_id, first_name, last_name, date_of_birth, gender, home_town_id)
VALUES
(1, 'Cassidy', 'Isacc', '1997-10-20', 'F', 1),
(2, 'Jonathan', 'Half', '1983-03-22', 'M', 2),
(3, 'Zack', 'Cody', '1989-08-08', 'M', 4),
(4, 'Joseph', 'Priboi', '1950-01-01', 'M', 5),
(5, 'Ivy', 'Indigo', '1993-12-31', 'F', 1);