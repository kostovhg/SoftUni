-- 15. Login
DELIMITER $$
CREATE PROCEDURE udp_login(username VARCHAR(30), `password` VARCHAR(30)) 
BEGIN 
	IF username NOT IN 
	 (
		SELECT `username`
		FROM users) 
	THEN 
		SIGNAL SQLSTATE '45000' 
		SET MESSAGE_TEXT = 'Username does not exist!'; 
	ELSEIF `password` <> 
	 (
		SELECT u.`password`
		FROM users AS u
		WHERE u.username = username) 
	THEN 
		SIGNAL SQLSTATE '45000' 
		SET message_text = 'Password is incorrect!'; 
	ELSEIF username IN 
		(
		SELECT lu.username
		FROM logged_in_users AS lu) 
	THEN 
		SIGNAL SQLSTATE '45000' 
		SET message_text = 'User is already logged in!'; 
	ELSE
		INSERT INTO logged_in_users (id, username, `password`, email)
		SELECT u.id, u.username, u.`password`, u.email
		FROM users AS u
		WHERE u.username = username;
	END IF; 
END $$
DELIMITER ;

-- 16. Evaluate Submission
DELIMITER $$
CREATE PROCEDURE udp_evaluate(id INT(11))
BEGIN
	if id in (select s.id from submissions as s)
    then 
		INSERT INTO evaluated_submissions
			(id, problem, user, result)
		SELECT t.id, t.problem, t.username, t.result FROM
        (select 
			s.id as 'id', 
            p.name as 'problem',
            u.username as 'username', 
			CEILING(p.points / p.tests * s.passed_tests) as 'result'
		from submissions as s
		INNER join problems as p on p.id = s.problem_id
		INNER join users as u on s.user_id = u.id
		where s.id = id) AS t;
	ELSE
		SIGNAL SQLSTATE '45000' 
		SET message_text = 'Submission does not exists!'; 
	END IF;
END $$
DELIMITER ;
