-- 	Lab: Built-in Functions
-- 1.Find Book Titles
--- Write a SQL query to find books which titles start with “The”.
--- Order the result by id.
--- Submit your query statements as Prepare DB & run queries.
SELECT b.`title` 
FROM `books` AS b
WHERE b.`title` LIKE('The%')
ORDER BY b.`id`;
-- OR
SELECT b.`title`
FROM `book_library`.`books` AS b
WHERE SUBSTRING(b.`title`, 1, 3) = 'The'
ORDER BY b.`id`;

-- 2.Replace Titles
--- Write a SQL query to find books which titles start with “The” and 
--- replace the substring with 3 asterisks. Retrieve data about the 
--- updated titles. Order the result by id. Submit your query statements --- as Prepare DB & run queries. 
SELECT REPLACE(b.`title`, 'The', '***') AS 'Replaced The'
FROM `books` AS b
WHERE b.`title` LIKE('The%')
ORDER BY b.`id`;

--3.Sum Cost of All Books
--- Write a SQL query to sum prices of all books.
--- Format the output to 2 digits after decimal point.
--- Submit your query statements as Prepare DB & run queries. 
SELECT ROUND(SUM(b.`cost`), 2) AS 'Sum of all books costs'
FROM `books` AS b;

-- 4.Days Lived
--- Write a SQL query to calculate the days that the authors have lived. 
--- NULL values mean that the author is still alive. 
--- Submit your query statements as Prepare DB & run queries. 
SELECT 
	CONCAT(
		a.`first_name`,
		' ',
		a.`last_name`) 
	AS 'Full Name',
	TIMESTAMPDIFF(
		DAY,
		a.`born`,
		a.`died`) AS 'Days Lived'
FROM `authors` AS a;

-- 5.Harry Potter Books
--- Write a SQL query to retrieve titles of all the Harry Potter books.
--- Order the information by id.
--- Submit your query statements as Prepare DB & run queries. 
SELECT 
	b.`title`
FROM `books` AS b
WHERE b.`title` LIKE('Harry Potter%');
	
