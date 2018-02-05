-- - String Functions
-- -- SUBSTRING() - extracts part of a string
-- -- # SUBSTRING(String, Position)
SELECT SUBSTRING('Some text to be splitted', -3);
-- with one argument, position from where we start getting remaining string;
--	+-------------------------------------------+
--	| ted                                       |
--	+-------------------------------------------+

SELECT SUBSTRING('Some text to be splitted', 2, 10);
-- whit two parameters, start where we getting and the count of characters
--	+----------------------------------------------+
--	| ome text t                                   |
--	+----------------------------------------------+

SELECT SUBSTRING('Some text to be splitted', -8, 3);
--	+----------------------------------------------+
--	| spl                                          |
--	+----------------------------------------------+

SELECT SUBSTRING('Some text to be splitted' FROM 6 FOR 4);
-- WHith FROM and FOR (start and length)
--	+----------------------------------------------------+
--	| text                                               |
--	+----------------------------------------------------+

-- EXAMPLES
SELECT e.`job_title` FROM `employees` AS e;
SELECT 
	CONCAT_WS(' ', e.`last_name`, 
		SUBSTRING(e.`job_title`, 1, 3))
AS 'job code' 
FROM `hospital`.`employees` AS e;
/*	+-------------+
	| job code    |
	+-------------+
	| Smith The   |
	| Johnson Acu |
	| 	....	  |
	+-------------+	*/

-- SUBSTRING -EXAMPLES
-- - Get short sumary of blog
SELECT `article_id`, `author`, `content`
	SUBSTRING(`content`, 1, 200) AS 'Sumarry'
FROM `articles`;

-- Problem: Find Book Title:
-- - Write a query to find all book titles that start with "The"
-- -- QUery book_library database
SELECT b.`title` FROM `books` AS b
    WHERE SUBSTRING(b.`title` FROM 1 FOR 3) = 'The';
-- OR
SELECT b.`title` FROM `books` AS b
    WHERE SUBSTRING(b.`title`, 1, 3) LIKE ('The');
-- OR
SELECT b.`title` FROM `books` AS b
    WHERE b.`title` LIKE('The%');
/*	+---------------------------------+
	| title                           |
	+---------------------------------+
	| The Mysterious Affair at Styles |
	| The Big Four                    |
	| The Murder at the Vicarage      |
	| The Mystery of the Blue Train   |
	| The Ring                        |
	| The Alchemist                   |
	| The Fifth Mountain              |
	| The Zahir                       |
	| The Dead Zone                   |
	| The Hobbit                      |
	| The Adventures of Tom Bombadil  |
	+---------------------------------+ */
	
-- Remove 'The' From titles
SELECT SUBSTRING(b.`title`, 4) AS 'Book Title'
FROM `book_library`.`books` AS b
WHERE SUBSTRING(b.`title`, 1, 3) LIKE 'The';
/*	+------------------------------+
	| Book Title                   |
	+------------------------------+
	|  Mysterious Affair at Styles |
	|  Big Four                    |
	|  Murder at the Vicarage      |
	|  Mystery of the Blue Train   |
	|  Ring                        |
	|  Alchemist                   |
	|  Fifth Mountain              |
	|  Zahir                       |
	|  Dead Zone                   |
	|  Hobbit                      |
	|  Adventures of Tom Bombadil  |
	+------------------------------+	*/
-- String Functions(2)
-- - REPLACE - replaces specific string with another
-- -- Performs a case-sensitive match
-- ### REPLACE(String, Pattern, Replacement)
SELECT REPLACE(b.`title`, 'The', '***') AS 'Book Title'
FROM `book_library`.`books` AS b
WHERE b.`title` LIKE ('The%');

-- Replace 'The' in the values;
UPDATE `book_library`.`books` AS b
SET b.`title` = REPLACE(b.`title`, 'The', '***')
WHERE b.`title` LIKE ('The%');
-- OR
UPDATE `book_library`.`books` AS b
SET b.`title` = REPLACE(b.`title`, 'The', '***')
WHERE SUBSTRING(b.`title`, 1, 3) = 'The';
-- OR
SELECT b.`title`
FROM `book_library`.`books` AS b
WHERE SUBSTRING(b.`title`, 1, 3) = '***';

-- String Functions (3)
-- - LTRIM & RTRIM - remove spaces from either side of the string
UPDATE `book_library`.`books` AS b
SET b.`title` = LTRIM(b.`title`)
WHERE b.`title` LIKE ('   %');
-- - CHAR_LENGTH - count numbers of characters
 SELECT c.`Name`, CHAR_LENGTH(c.`Name`) AS 'Name Len', c.`LocalName`, LENGTH(c.`LocalName`) AS 'LocalName Len'
 FROM `world`.`country` AS c
 LIMIT 10;
-- - LENGTH - get number of used bytes (double for unicode)
-- # LENGTH(String)
 
-- String functions (4)
 -- LEFT & RIGHT - get characters from beginning or end of string;
--- # LEFT (String, Count)
--- # RIGHT(String, COUNT
-- EXAMPLE:
SELECT LEFT(e.`first_name`, 3) AS 'First 3 char', RIGHT(e.`last_name`, 3) AS 'Last 3 char'
FROM `hospital`.`employees` AS e;
-- Same as
SELECT SUBSTRING(e.`first_name`, 1, 3) AS 'First 3 char', SUBSTRING(e.`last_name`, -3) AS 'Last 3 char'
FROM `hospital`.`employees` AS e;

-- String Functions(6)
-- - LOWER & UPPER - change letter casing
SELECT 
	LOWER(e.`first_name`) AS 'first_name', 
	UPPER(e.`last_name`) AS 'LAST_NAME'
FROM `hospital`.`employees` AS e;

-- REVERSE & REPEAT
SELECT 
	REVERSE(LOWER(e.`first_name`)) AS 'Lower and Reverced first name',REPEAT(UPPER(e.`last_name`), 2) AS 'Upper and Repeated last name' 
FROM `hospital`.`employees` AS e;

-- String Functions(7)
-- - LOCATE - Locate specific pattern(substring) in string
-- # LOCATE(Pattern, String,[Position])
SELECT LOCATE('er', e.`first_name`) AS '`er` position', e.`first_name`
FROM `hospital`.`employees` AS e;

SELECT LOCATE('a', e.`first_name`, 2) AS '`a` position after 2 char', e.`first_name`
FROM `hospital`.`employees` AS e;

-- - INSERT - insert substring at specific position
-- # INSERT(String, Position, Length, Substring)
SELECT 
	e.`first_name` AS 'Original first name',
	INSERT(e.`first_name`, 4, 1, '***') AS 'Cleaned 4 char and replaced with ***'
FROM `hospital`.`employees` AS e;

SELECT
	e.`first_name`,
	e.`last_name`,
	INSERT(e.`last_name`, LOCATE('ov', e.`last_name`), 2, 'ova') AS 'Wife name'
	FROM `employees` AS e
	WHERE e.`last_name` LIKE ('%ov');


-- Arithmetic Operators And Numerical Functions
SELECT
	3+2 AS 'SUM',
	3-2 AS 'SUB',
	3 DIV 2 AS 'DIV',
	3 MOD 2 AS 'MOD2',
	3 % 2 AS 'MOD',
	TRUNCATE(3 / 2, 2) AS 'DIVISION',
	- (3) AS 'Change SIGN';
	
USE hostpital;
SELECT
	SUM(`salary`),
	AVG(`salary`)
FROM `employees`;

-- Numerical functions
-- - Used primarily for numerical manipulation and/or mathematical calculations
-- - PI -get the values of Pi (15 digits precision)
-- #  SELECT PI()
-- - ABS - Absolute value
-- # ABS(Value)
-- - SQRT - square root
-- # SQRT(Value)
-- - POW - rise value to desired exponent
-- # POW(Value)
SELECT
	ABS(-3) AS 'ABS-',
	ABS(3) AS 'ABS+',
	TRUNCATE(SIN(45),2) AS 'SIN',
	POW(3,2) AS 'POW',
	SQRT(25) AS 'SQRT';
-- - CONV – Converts numbers between different number bases
-- # CONV(value, from_base, to_base)
SELECT CONV(10, 10, 2);

-- - ROUND – obtain desired precision
-- # ROUND(Value, precision)
SELECT ROUND(PI(), 4);
SELECT ROUND(232345, -3);

-- - FLOOR & CEILING – return the nearest integer
-- # FLOOR(Value)
-- # CEILING(Value)
SELECT 
	CEILING(PI()) AS 'CELLING',
	FLOOR(PI()) AS 'FLOOR';
	
-- - SIGN – returns +1, -1 or 0, depending on value sign
-- # SIGN(Value)
SELECT 
	SIN(3), SIGN(SIN(3)),
	SIN(-90), SIGN(SIN(-90)),
	SIN(0), SIGN(SIN(0));
	
-- - RAND – get a random value in range [0,1)
-- - - If Seed is not specified, one is assigned at random
-- # RAND()
-- # RAND(Seed)
SELECT
	RAND() AS 'RAND_1',
	RAND() AS 'RAND_2',
	RAND() AS 'RAND_3',
	RAND() AS 'RAND_4';
	
SELECT
	RAND(3) AS 'RAND_1',
	RAND(3) AS 'RAND_2',
	RAND(3) AS 'RAND_3',
	RAND(3) AS 'RAND_4';

-- Date functions

-- - EXTRACT – extract a segment from a date as an integer
-- # EXTRACT(Part FROM Date)
-- -- - Part can be any part and format of date or time
-- 		year, %Y, %y	|	YEAR(Date)
-- 		month, %M, %m	|	MONTH(Date)
-- 		day, %w, %D		|	DAY(Date)
SELECT
    u.`user_name`,
    EXTRACT(year FROM u.`registration_date`) AS 'Year of reg'
    FROM `diablo`.`users` AS u
	ORDER BY u.`registration_date` DESC;

-- - TIMESTAMPDIFF – find difference between two dates
-- # TIMESTAMPDIFF(Part, FirstDate, SecondDate)
-- -- - Part can be any part and format of date or time
SELECT
	u1.`user_name`,
	u1.`registration_date` AS 'Prev user reg. date',
	u2.`user_name`,
	u2.`registration_date` AS 'Next user reg. date',
	CONCAT(
		TIMESTAMPDIFF(
			YEAR,
			u1.`registration_date`,
			u2.`registration_date`),
		' years and ',
		TIMESTAMPDIFF(
			MONTH,
			u1.`registration_date`,
			u2.`registration_date`),
		' months'
		)
AS 'Stamp diff'
FROM `diablo`.`users` AS u1
INNER JOIN
	`diablo`.`users` AS u2 ON u2.`id` = u1.`id` + 1;

	--
SET @min_timestamp = MIN(`users`.`registration_date`);
SELECT @min_timestamp;
SELECT
	u.`user_name`,
	CONCAT(
		TIMESTAMPDIFF(
			YEAR,
			@min_timestamp,
			u.`registration_date`),
		' years and ',
		TIMESTAMPDIFF(
			MONTH,
			@min_timestamp,
			u.`registration_date`),
		' months')
	AS 'Diff from first reg'
FROM `diablo`.`users` AS u;

-- - Date Functions - Example
-- - Show employee experience
-- # SELECT `employee_id`, `first_name`, `last_name`,
-- #       TIMESTAMPDIFF(year, `hire_date`, '2017-05-31')
-- #    AS 'Years In Service'
-- # FROM `employees`;
SELECT e.`employee_id`, e.`first_name`, e.`last_name`,
	TIMESTAMPDIFF(year, e.`hire_date`, CURDATE())
	AS 'Years IN Service'
FROM `employees` AS e;

-- - Problem: Days Lived
-- - Write a query to calculate how many days have authors lived
-- -- - Use TIMESTAMPDIFF
-- -- - Query book_library database
SELECT
	CONCAT(
		a.`first_name`, 
		IF(a.`middle_name` IS NULL,
			' ', 
			CONCAT(a.`middle_name`, ' ')),
		a.`last_name`)
	AS 'Author name',
	TIMESTAMPDIFF(
		DAY,
		a.`born`,
		IFNULL(a.`died`, CURDATE())) AS 'Days lived'
FROM `book_library`.`authors` AS a;

-- - Days Lived - Solution
-- # SELECT  concat(first_name, ' ', last_name) as 'Full Name',
-- # TIMESTAMPDIFF(DAY, born, died) as 'Days Lived' 
-- # FROM authors;

-- - DATE_FORMAT – formats the date value according to the format
-- # SELECT DATE_FORMAT('2017/05/31', '%Y %b %D') AS 'Date';
-- - NOW – obtain current date and time
-- # SELECT NOW();
SELECT
	a.`first_name`,
	a.`last_name`,
	a.`born`,
	DATE_FORMAT(a.`born`, '%W, %D of %M %Y') AS 'Date of birth'
FROM `authors` AS a;

-- Wildcards
-- # Selecting results by partial match
-- - Used to substitute any other character(s) in a string
-- -- - '%' - represents zero, one, or multiple characters
-- -- - '_' - represents a single character
-- -- - Can be used in combinations
-- - Used with LIKE operator in a WHERE clause
-- -- -Similar to Regular Expressions

-- - Wildcards - Examples
-- - Find any values that start with "a"
-- # WHERE CustomerName LIKE 'a%';
-- - Find any values that have "r" in second position
-- # WHERE CustomerName LIKE '_r%';
-- - Finds any values that starts with "a" and ends with "o"
-- # WHERE ContactName LIKE 'a%o';

-- - Supported characters also include:
-- -- - \ – specify prefix to treat special characters as normal
-- -- - [charlist] – specifying which characters to look for
-- -- -- [!charlist] – excluding characters
-- # SELECT * FROM `customers`
-- # WHERE `city` LIKE '[a-c]%'; 
-- -- # "a", "b", or "c"
-- - Problem: Harry Potter Books
-- -- Write a query to retrieve information about the titles of all Harry Potter books
-- -- - Use Wildcards
-- -- - Query book_library database

SELECT b.`title` FROM `book_library`.`books` AS b
    WHERE b.`title` LIKE('%Harry Potter%');
-- OR full information;
SELECT 
	b.`id`, b.`title`,
	CONCAT(
		a.`first_name`, 
		IF(a.`middle_name` IS NULL,
			' ', 
			CONCAT(a.`middle_name`, ' ')),
		a.`last_name`)
	AS 'Author name',
	b.`year_of_release`,
	b.`cost`
FROM `book_library`.`books` AS b JOIN `authors` AS a
	WHERE b.`author_id` = a.`id` AND b.`title` LIKE('%Harry Potter%');

-- - Harry Potter Books - Solution
-- # SELECT title FROM books
-- # WHERE title LIKE 'Harry Potter%';

-- - Using Regular Expression
-- - REGEXP - pattern matching using regular expressions
-- # SELECT `employee_id`, `first_name`, `last_name`
-- # FROM `employees`
-- # WHERE `first_name` REGEXP '^\[^K\]{3}\$';









