DROP DATABASE IF EXISTS closed_judge_system;
CREATE DATABASE closed_judge_system;
USE closed_judge_system;

-- 01. Table Design
CREATE TABLE users(
	`id` INT(11) PRIMARY KEY,
    `username` VARCHAR(30) UNIQUE NOT NULL,
    `password` VARCHAR(30) NOT NULL,
    `email` VARCHAR(50)
);

CREATE TABLE categories(
	`id` INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL,
    `parent_id` INT(11),
    CONSTRAINT fk_category_category
    FOREIGN KEY (parent_id)
    REFERENCES categories(id)
); 

CREATE TABLE contests(
	`id` INT(11) PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL,
    `category_id` INT(11),
    CONSTRAINT fk_contest_category
    FOREIGN KEY (category_id)
    REFERENCES categories(id)
);

CREATE TABLE problems(
	`id` INT(11) PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    `points` INT(11) NOT NULL,
    `tests` INT(11) DEFAULT 0,
    `contest_id` INT(11),
    CONSTRAINT fk_problem_category
    FOREIGN KEY (contest_id)
    REFERENCES contests(id)
);

CREATE TABLE submissions(
	`id` INT(11) AUTO_INCREMENT PRIMARY KEY,
    `passed_tests` INT(11) NOT NULL,
    `problem_id` INT(11),
    `user_id` INT(11),
    CONSTRAINT fk_submission_problem
    FOREIGN KEY (problem_id)
    REFERENCES problems(id),
    CONSTRAINT fk_submission_user
    FOREIGN KEY (user_id)
    REFERENCES users(id)
);

CREATE TABLE users_contests(
	`user_id` INT(11) ,
    `contest_id` INT(11) ,
    CONSTRAINT pk_user_contest
    PRIMARY KEY (user_id, contest_id),
    CONSTRAINT fk_user_contest_user
    FOREIGN KEY (user_id)
    REFERENCES users(id),
    CONSTRAINT fk_user_contest_contest
    FOREIGN KEY (contest_id)
    REFERENCES contests(id)
);