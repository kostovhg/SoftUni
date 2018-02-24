-- 1. Extract All Tickets
SELECT
	t.ticket_id,
    t.price,
    t.class,
    t.seat
FROM tickets AS t;

-- 2. Extract All Customers
SELECT
	c.customer_id,
    concat_ws(' ', c.first_name, c.last_name) AS 'full_name',
    c.gender
FROM customers AS c
ORDER BY `full_name` ASC, c.customer_id ASC;

-- 3. Extract Delayed Flights
SELECT 
	f.flight_id,
    f.departure_time,
    f.arrival_time
FROM flights AS f
WHERE f.status LIKE ('Delayed')
ORDER BY f.flight_id ASC;

-- 4. Extract Top 5 Most Highly Rated Airlines which have any Flights
SELECT
	a.airline_id,
    a.airline_name,
    a.nationality,
    a.rating
FROM flights as f
join airlines as a on f.airline_id = a.airline_id
GROUP BY a.airline_id
ORDER BY a.rating DESC, a.airline_id ASC
LIMIT 5;

-- 5. Extract all Tickets with price below 5000, for First Class
SELECT 
	t.ticket_id,
    ap.airport_name AS 'destination',
    CONCAT(c.first_name, ' ', c.last_name) AS 'customer_name'
FROM tickets AS t
JOIN flights AS f ON t.flight_id = f.flight_id
JOIN airports AS ap ON f.destination_airport_id = ap.airport_id
JOIN customers AS c ON t.customer_id = c.customer_id
WHERE t.price < 5000 AND t.class LIKE ('First')
ORDER BY t.ticket_id ASC;

-- 6. Extract all Customers which are departing from their Home Town
SELECT 
	c.customer_id,
    CONCAT(c.first_name, ' ', c.last_name) AS 'full_name',
    town.town_name AS 'home_town'
FROM customers AS c
JOIN tickets AS t ON t.customer_id = c.customer_id
JOIN flights AS f ON t.flight_id = f.flight_id
JOIN airports AS a ON f.origin_airport_id = a.airport_id
JOIN towns AS town ON a.town_id = town.town_id
WHERE a.town_id = c.home_town_id
	AND f.`status` LIKE ('Departing')
ORDER BY c.customer_id ASC;

-- 7. Extract all Customers which will fly
SELECT DISTINCT 
	c.customer_id,
    CONCAT(c.first_name, ' ', c.last_name) AS 'full_name',
	(2016 - YEAR(c.date_of_birth)) AS 'age'
FROM customers AS c
JOIN tickets AS t ON t.customer_id = c.customer_id
JOIN flights AS f ON t.flight_id = f.flight_id
WHERE f.`status` LIKE ('departing')
ORDER BY `age` ASC, c.customer_id ASC;

-- 8. Extract Top 3 Customers which have Delayed Flights
SELECT 
	c.customer_id,
    CONCAT(c.first_name, ' ', c.last_name) AS 'full_name',
	t.price As 'ticket_price',
    a.airport_name AS 'destination'
FROM customers AS c
JOIN tickets AS t ON t.customer_id = c.customer_id
JOIN flights AS f ON t.flight_id = f.flight_id
JOIN airports AS a ON f.destination_airport_id = a.airport_id
WHERE f.`status` LIKE ('Delayed')
ORDER BY `ticket_price` DESC, c.customer_id ASC
LIMIT 3;

-- 9. Extract the Last 5 Flights, which are departing
SELECT 
	*
FROM (
	SELECT 
		f.flight_id,
		f.departure_time,
		f.arrival_time,
		a.airport_name AS 'origin',
		aa.airport_name AS 'destination'
	FROM flights as f
	JOIN airports AS a ON f.origin_airport_id = a.airport_id
	JOIN airports AS aa On f.destination_airport_id = aa.airport_id
	WHERE f.`status` LIKE ('Departing')
    ORDER BY f.departure_time DESC, f.flight_id DESC
    LIMIT 5) as tmp
ORDER BY tmp.departure_time ASC, tmp.flight_id ASC;

-- 10. Extract All Customers below 21 years, which have already flew at least once
SELECT
	*
FROM
    (SELECT 
	DISTINCT 
       	c.customer_id,
       	CONCAT(c.first_name, ' ', c.last_name) AS 'full_name',
		(2016 - YEAR(c.date_of_birth)) AS 'age'
	FROM customers as c
	JOIN tickets as t ON t.customer_id = c.customer_id
	JOIN flights AS f On t.flight_id = f.flight_id
	WHERE f.`status` LIKE ('Arrived')) AS tmp
WHERE tmp.`age` < 21
ORDER BY tmp.`age` DESC, tmp.customer_id ASC;

-- 11. Extract all Airports and the Count of People departing from them
-- ! Not finished
SELECT 
	a.airport_id, 
	a.airport_name,
	COUNT(t.ticket_id) AS passengers
FROM airports AS a
INNER JOIN flights AS f 
	ON f.origin_airport_id = a.airport_id
	AND f.Status = 'Departing'
INNER JOIN tickets AS t 
	ON t.flight_id = f.flight_id
INNER JOIN customers AS c 
	ON c.customer_id = t.customer_id
GROUP BY a.airport_id, a.airport_name;