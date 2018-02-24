CREATE TABLE flights(
	flight_id INT,
	departure_time DATETIME NOT NULL,
	arrival_time DATETIME NOT NULL,
	`status` VARCHAR(9) CHECK (`status` IN ('Departing', 'Delayed', 'Arrived', 'Cancelled')), 
	origin_airport_id INT,
	destination_airport_id INT,
	airline_id INT,
    CONSTRAINT PK_flights PRIMARY KEY(flight_id),
	CONSTRAINT FK_origin_airport FOREIGN KEY(origin_airport_id) REFERENCES airports(airport_id),
    CONSTRAINT FK_destination_airport FOREIGN KEY(destination_airport_id) REFERENCES airports(airport_id),
    CONSTRAINT FK_flights_airline FOREIGN KEY(airline_id) REFERENCES airlines(airline_id)
);

CREATE TABLE tickets(
	ticket_id INT,
	price DECIMAL(8,2) NOT NULL,
	class VARCHAR(6) CHECK (`class` IN ('First', 'Second', 'Third')),
	seat VARCHAR(5) NOT NULL,
	customer_id INT,
	flight_id INT,
    CONSTRAINT PK_tickets PRIMARY KEY(ticket_id),
	CONSTRAINT FK_ticket_customer FOREIGN KEY(customer_id) REFERENCES customers(customer_id),
    CONSTRAINT FK_ticket_flight FOREIGN KEY(flight_id) REFERENCES flights(flight_id)
);