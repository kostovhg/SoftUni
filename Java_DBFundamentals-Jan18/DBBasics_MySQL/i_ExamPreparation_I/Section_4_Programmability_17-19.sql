-- 17. Get Reports
DELIMITER $$
CREATE FUNCTION udf_get_reports_count(
	employee_id INT,
    status_id INT)
RETURNS INT
BEGIN
	RETURN (
		SELECT
			COUNT(r.status_id) AS 'reports_count'
		FROM 
			reports AS r
		WHERE r.employee_id = employee_id AND r.status_id = status_id);
END $$
DELIMITER ;
/* Query example
SELECT id, first_name, last_name, udf_get_reports_count(id, 2) AS reports_count
FROM employees AS e
ORDER BY e.id;
*/

-- 18. Assign Employee
DELIMITER $$
CREATE PROCEDURE usp_assign_employee_to_report(employee_id INT, report_id INT)
BEGIN
	DECLARE employee_department_id INT;
	DECLARE report_category_id INT;
	DECLARE category_department_id INT;
    
    SET employee_department_id := (SELECT department_id FROM employees AS e WHERE e.id = employee_id );
	SET report_category_id := (SELECT category_id FROM reports AS r WHERE r.id = report_id);
	SET category_department_id := (SELECT department_id FROM categories AS c WHERE c.id = report_category_id);

	START TRANSACTION;
		IF(employee_department_id != category_department_id)
		THEN
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Employee doesn\'t belong to the appropriate department!';
			ROLLBACK;
		ELSE
			UPDATE reports AS r
			SET r.employee_id = employee_id
			WHERE r.id = report_id;
		END IF;
	COMMIT;
END $$
DELIMITER ;
/*
CALL usp_assign_employee_to_report(30, 1);
SELECT employee_id FROM reports WHERE id = 2;

CALL usp_assign_employee_to_report(17, 2);
SELECT employee_id FROM reports WHERE id = 2;
*/

-- 19. Close Reports
DELIMITER $$
CREATE TRIGGER t_update_close_date
BEFORE UPDATE ON reports
FOR EACH ROW
BEGIN
	IF(old.close_date is null and new.close_date is not null) THEN
		SET new.status_id = 3;
	END IF;
END $$
DELIMITER ;
/*
UPDATE reports
SET close_date = now()
WHERE employee_id = 5;
*/

