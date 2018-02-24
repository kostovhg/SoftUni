CREATE TABLE deposit_types(
	deposit_type_id INT PRIMARY KEY,
    `name` VARCHAR(20)
);

CREATE TABLE deposits(
	deposit_id INT PRIMARY KEY,
	amount DECIMAL(10,2),
	start_date DATE,
	end_date DATE,
	deposit_type_id INT,
	customer_id INT,
    FOREIGN KEY (deposit_type_id) REFERENCES deposit_types(deposit_type_id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE employees_deposits(
	employee_id INT,
	deposit_id INT,
    PRIMARY KEY (employee_id, deposit_id),
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
    FOREIGN KEY (deposit_id) REFERENCES deposits(deposit_id)
);

CREATE TABLE credit_history(
	credit_history_id INT PRIMARY KEY,
	mark CHAR(1),
	start_date DATE,
	end_date DATE,
	customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE payments(
	payment_id INT PRIMARY KEY,
	`date` DATE,
	amount DECIMAL(10,2),
    loan_id INT,
    FOREIGN KEY (loan_id) REFERENCES loans(loan_id)
);

CREATE TABLE users(
	user_id INT PRIMARY KEY,
	user_name VARCHAR(20),
	`password` VARCHAR(20),
    customer_id INT UNIQUE,
    FOREIGN KEY(customer_id) REFERENCES customers(customer_id)
);

ALTER TABLE employees
ADD manager_id INT,
ADD FOREIGN KEY (manager_id) REFERENCES employees(employee_id);