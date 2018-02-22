USE report_service;
SET foreign_key_checks = 0;
INSERT INTO employees(first_name, last_name, gender, birthdate, department_id)
VALUES
	('Marlo', 'O\'Malley', 'M', STR_TO_DATE('9-21-1958', '%m-%d-%Y'), 1),
	('Niki', 'Stanaghan', 'F', STR_TO_DATE('11-26-1969', '%m-%d-%Y'), 4),
	('Ayrton', 'Senna', 'M', STR_TO_DATE('03-21-1960', '%m-%d-%Y'), 9),
	('Ronnie', 'Peterson', 'M', STR_TO_DATE('02-14-1944', '%m-%d-%Y'), 9),
	('Giovanna', 'Amati', 'F', STR_TO_DATE('07-20-1959', '%m-%d-%Y'), 5);

INSERT INTO reports
	(category_id, status_id, open_date, close_date, description, user_id, employee_id)
VALUES
	(1, 1, STR_TO_DATE('04-13-2017', '%m-%d-%Y'), NULL,'Stuck Road on Str.133', 6, 2),
	(6, 3, STR_TO_DATE('09-05-2015', '%m-%d-%Y'), STR_TO_DATE('12-06-2015', '%m-%d-%Y'), 'Charity trail running', 3, 5),
	(14, 2, STR_TO_DATE('09-07-2015', '%m-%d-%Y'), NULL, 'Falling bricks on Str.58', 5, 2),
	(4, 3, STR_TO_DATE('07-03-2017', '%m-%d-%Y'), STR_TO_DATE('07-06-2017', '%m-%d-%Y'), 'Cut off streetlight on Str.11', 1, 1);
SET foreign_key_checks = 1;