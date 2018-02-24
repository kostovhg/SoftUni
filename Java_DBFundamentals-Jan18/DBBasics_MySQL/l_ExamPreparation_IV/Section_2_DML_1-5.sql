-- 1. Data Insertion
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO flights (
 flight_id, departure_time, arrival_time, status, origin_airport_id, destination_airport_id, airline_id)
VALUES
	(1, STR_TO_DATE('2016-10-13 06:00 AM', '%Y-%m-%d %h:%i %p'), STR_TO_DATE('2016-10-13 10:00 AM', '%Y-%m-%d %h:%i %p'), 'Delayed', 1, 4, 1),
	(2, STR_TO_DATE('2016-10-12 12:00 PM', '%Y-%m-%d %h:%i %p'), STR_TO_DATE('2016-10-12 12:01 PM', '%Y-%m-%d %h:%i %p'), 'Departing', 1, 3, 2),
	(3, STR_TO_DATE('2016-10-14 03:00 PM', '%Y-%m-%d %h:%i %p'), STR_TO_DATE('2016-10-20 04:00 AM', '%Y-%m-%d %h:%i %p'), 'Delayed', 4, 2, 4),
	(4, STR_TO_DATE('2016-10-12 01:24 PM', '%Y-%m-%d %h:%i %p'), STR_TO_DATE('2016-10-12 4:31 PM', '%Y-%m-%d %h:%i %p'), 'Departing', 3, 1, 3),
	(5, STR_TO_DATE('2016-10-12 08:11 AM', '%Y-%m-%d %h:%i %p'), STR_TO_DATE('2016-10-12 11:22 PM', '%Y-%m-%d %h:%i %p'), 'Departing', 4, 1, 1),
	(6, STR_TO_DATE('1995-06-21 12:30 PM', '%Y-%m-%d %h:%i %p'), STR_TO_DATE('1995-06-22 08:30 PM', '%Y-%m-%d %h:%i %p'), 'Arrived', 2, 3, 5),
	(7, STR_TO_DATE('2016-10-12 11:34 PM', '%Y-%m-%d %h:%i %p'), STR_TO_DATE('2016-10-13 03:00 AM', '%Y-%m-%d %h:%i %p'), 'Departing', 2, 4, 2),
	(8, STR_TO_DATE('2016-11-11 01:00 PM', '%Y-%m-%d %h:%i %p'), STR_TO_DATE('2016-11-12 10:00 PM', '%Y-%m-%d %h:%i %p'), 'Delayed', 4, 3, 1),
	(9, STR_TO_DATE('2015-10-01 12:00 PM', '%Y-%m-%d %h:%i %p'), STR_TO_DATE('2015-12-01 01:00 AM', '%Y-%m-%d %h:%i %p'), 'Arrived', 1, 2, 1),
	(10, STR_TO_DATE('2016-10-12 07:30 PM', '%Y-%m-%d %h:%i %p'), STR_TO_DATE('2016-10-13 12:30 PM', '%Y-%m-%d %h:%i %p'), 'Departing', 2, 1, 7);

INSERT INTO tickets
	(ticket_id, price, `class`, seat, customer_id, flight_id)
VALUES
	(1,	3000.00	, 'First', '233-A', 3, 8),
	(2,	1799.90	, 'Second', '123-D', 1, 1),
	(3,	1200.50	, 'Second', '12-Z', 2, 5),
	(4,	410.68	, 'Third', '45-Q', 2, 8),
	(5,	560.00	, 'Third', '201-R', 4, 6),
	(6,	2100.00	, 'Second', '13-T', 1, 9),
	(7,	5500.00	, 'First', '98-O', 2, 7);
SET FOREIGN_KEY_CHECKS=1;

-- 2. Update Arrived Flights
UPDATE flights
SET airline_id = 1
WHERE status = 'Arrived';

-- 3. Update Tickets
Update tickets AS t
JOIN flights AS f ON f.flight_id = t.flight_id
JOIN airlines AS a ON f.airline_id = a.airline_id
SET t.price = t.price * 1.5
WHERE a.rating = (SELECT MAX(rating) FROM airlines);

-- 4. Table Creation
CREATE TABLE customer_reviews(
	review_id INT,
	review_content VARCHAR(255) NOT NULL,
	review_grade INT(2) UNSIGNED check(`review_grade` BETWEEN 0 AND 10),
	airline_id INT,
	customer_id INT,
	CONSTRAINT PK_customer_reviews PRIMARY KEY(review_id),
    CONSTRAINT FK_customer_review_airline FOREIGN KEY(airline_id) REFERENCES airlines(airline_id),
	CONSTRAINT FK_review_customer FOREIGN KEY(customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE customer_bank_accounts(
	account_id INT,
	account_number VARCHAR(10) UNIQUE NOT NULL,
	balance DECIMAL(10,2) NOT NULL,
	customer_id INT,
	CONSTRAINT PK_customer_bank_account PRIMARY KEY(account_id),
	CONSTRAINT FK_bank_account_customer FOREIGN KEY(customer_id) REFERENCES customers(customer_id)
);

-- 5. Fill the new Tables with Data
INSERT INTO customer_reviews(
	review_id, review_content, review_grade, airline_id, customer_id)
VALUES
	(1, 'Me is very happy. Me likey this airline. Me good.', 10, 1, 1),
	(2, 'Ja, Ja, Ja... Ja, Gut, Gut, Ja Gut! Sehr Gut!', 10, 1, 4),
	(3, 'Meh...', 5, 4, 3),
	(4, 'Well Ive seen better, but Ive certainly seen a lot worse...', 7, 3, 5);

INSERT INTO customer_bank_accounts
	(account_id, account_number, balance, customer_id)
VALUES
	(1, '123456790', 2569.23, 1),
	(2, '18ABC23672', 14004568.23, 2),
	(3, 'F0RG0100N3', 19345.20, 5);

