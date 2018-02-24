-- 1. Review Registering Procedure
DELIMITER $$
CREATE PROCEDURE usp_submit_review(
	_customer_id INT,
	_review_content VARCHAR(255),
	_review_grade INT(2),
	_airline_name VARCHAR(30))
BEGIN
	DECLARE _id INT;
        SET _id := (If((SELECT COUNT(*) FROM customer_reviews) < 1, 1, (SELECT review_id FROM customer_reviews ORDER BY review_id LIMIT 1) +1));
	IF _airline_name NOT IN 
	 (
		SELECT `airline_name`
		FROM airlines) 
	THEN 
		SIGNAL SQLSTATE '45000' 
		SET MESSAGE_TEXT = 'Airline does not exist!'; 
	ELSE
		INSERT INTO customer_reviews
			(review_id, review_content, review_grade, airline_id, customer_id)
		VALUES(
			_id,
			_review_content, _review_grade, 
            (SELECT airline_id FROM airlines AS a WHERE a.airline_name = _airline_name),
            _customer_id);
    END IF;
END $$
DELIMITER ;

-- 2.