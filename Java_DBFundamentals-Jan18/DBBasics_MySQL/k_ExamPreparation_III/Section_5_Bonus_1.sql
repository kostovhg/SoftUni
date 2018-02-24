-- 1. Delete Trigger
-- CREATE TABLE account_logs
-- SELECT * FROM accounts WHERE 1 = 0;

DELIMITER $$
CREATE TRIGGER tr_log_deleted_accounts
BEFORE DELETE
ON accounts
FOR EACH ROW
BEGIN
	DELETE FROM `employees_accounts`
    WHERE account_id = OLD.account_id;
    
    INSERT INTO `account_logs`
    VALUES (OLD.account_id, OLD.account_number, OLD.start_date, OLD.customer_id);
END $$
DELIMITER ;