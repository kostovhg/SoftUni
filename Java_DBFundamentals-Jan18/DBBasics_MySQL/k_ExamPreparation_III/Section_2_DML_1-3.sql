-- 01. Insert data
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO employees_deposits(
	employee_id, deposit_id)
VALUES 
(15, 4),
(20, 15),
(8, 7),
(4, 8),
(3, 13),
(3, 8),
(4, 10),
(10, 1),
(13, 4),
(14, 9);

-- ! there is error in sceleton or in problem description with the name of second column in the table
-- in the description it is mentioned as 'name', in the judge it is 'deposit_name'
INSERT INTO deposit_types(
	deposit_type_id, `deposit_name`)
VALUES
	(1, 'Time Deposit'),
	(2, 'Call Deposit'),
	(3, 'Free Deposit');

-- ! deposits  does not auto increment its id
INSERT INTO deposits (amount, start_date, end_date, deposit_type_id, customer_id)
SELECT
	(CASE
		WHEN c.date_of_birth > '1980-01-01' THEN 1000
        ELSE 1500
	END
    +
    CASE
		WHEN c.gender = 'm' THEN 100
        ELSE 200
	END) AS 'amount',
    DATE(now()) AS 'start_date',
    NULL AS 'end_date',
    (CASE
		WHEN c.customer_id > 15 THEN 3
        WHEN c.customer_id%2=0 THEN 2
        ELSE 1
	END) AS 'deposit_type_id',
    c.customer_id AS 'customer_id'
FROM customers AS c
where c.customer_id < 20;

SET FOREIGN_KEY_CHECKS=1;

-- 02. Update Employees
UPDATE employees
SET manager_id =
	(CASE
		WHEN employee_id BETWEEN 2 and 10 THEN 1
        WHEN employee_id BETWEEN 12 and 20 then 11
        when employee_id BETWEEN 22 and 30 then 21
        when employee_id IN (11, 21) then 1
	END)
;

DELETE FROM employees_deposits WHERE deposit_id = 9 OR employee_id = 3;