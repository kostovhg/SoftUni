-- 1. String Joiner Function
DELIMITER $$
CREATE FUNCTION udf_concat_string(f_str VARCHAR(112), s_str VARCHAR(112))
RETURNS VARCHAR(224)
BEGIN
    RETURN CONCAT(REVERSE(f_str), REVERSE(s_str));	
END $$
DELIMITER ;

-- 2. Unexpired Loans Procedure
DELIMITER $$
CREATE PROCEDURE usp_customers_with_unexpired_loans(customer_id INT(11))
BEGIN
    SELECT 
		cus.customer_id,
		cus.first_name,
		lo.loan_id
	FROM
		customers AS cus
	JOIN
		loans AS lo 
		ON lo.customer_id = cus.customer_id
	WHERE
		lo.expiration_date IS NULL
		AND cus.customer_id = customer_id;
END $$
DELIMITER ;

-- 3. Take Loan Procedure
DELIMITER $$
CREATE PROCEDURE usp_take_loan(
	customer_id INT(11),
    loan_amount DECIMAL(18,2),
    interest DECIMAL(4,2),
    start_date DATE)
BEGIN
    INSERT INTO loans (
		customer_id, amount, interest, start_date)
	VALUES (customer_id, loan_amount, interest, start_date);
    IF (loan_amount NOT BETWEEN 0.01 AND 100000)
	THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'The problem\'s points cannot be less or equal to 0!';
        ROLLBACK;
    ELSE
		COMMIT;
    END IF;
END $$
DELIMITER ;

-- 4. Trigger Hire Employee
DELIMITER $$
CREATE TRIGGER tr_hire_employee
AFTER INSERT
ON employees
FOR EACH ROW
BEGIN
	UPDATE `employees_loans` AS el
    SET el.`employee_id` = new.employee_id
    WHERE el.employee_id + 1 = new.employee_id;
END $$
DELIMITER ;