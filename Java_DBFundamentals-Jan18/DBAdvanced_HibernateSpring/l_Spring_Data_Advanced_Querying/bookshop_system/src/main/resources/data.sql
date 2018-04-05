DELIMITER &&
DROP PROCEDURE IF EXISTS system_bookshop.total_number_of_books_by_author &&

create procedure system_bookshop.total_number_of_books_by_author(IN full_name varchar(50), OUT books_count INT)
  BEGIN
    SET books_count = (SELECT count(b.book_id)
                       FROM books AS b
                         INNER JOIN authors AS a
                           ON a.author_id = b.author_id
                       WHERE CONCAT(a.first_name, ' ', a.last_name) = full_name);
    Select books_count;
    END&&
DELIMITER ;