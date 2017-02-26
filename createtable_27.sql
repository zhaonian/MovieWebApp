CREATE TABLE movies (
    id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    year INTEGER NOT NULL,
    director VARCHAR(100) NOT NULL,
    banner_url VARCHAR(200),
    trailer_url VARCHAR(200)
);

CREATE TABLE stars (
id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
dob DATE,
photo_url VARCHAR(200));

CREATE TABLE stars_in_movies (
    star_id INTEGER NOT NULL REFERENCES stars.id,
    movie_id INTEGER NOT NULL REFERENCES movies.id
);

CREATE TABLE genres (
id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
name VARCHAR(32));

CREATE TABLE genres_in_movies (
genre_id INTEGER NOT NULL REFERENCES genres.id,
movie_id INTEGER NOT NULL REFERENCES movies.id);

CREATE TABLE customers (
id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
cc_id VARCHAR(20) NOT NULL REFERENCES creditcards.id,
address VARCHAR(200) NOT NULL,
email VARCHAR(50) NOT NULL UNIQUE,
password VARCHAR(20) NOT NULL);

CREATE TABLE sales (
id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
customer_id INTEGER NOT NULL REFERENCES customers.id,
movie_id INTEGER NOT NULL REFERENCES movies.id,
sale_date DATE NOT NULL);

CREATE TABLE creditcards (
id VARCHAR(20) PRIMARY KEY,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
expiration DATE NOT NULL);         

CREATE TABLE employees (
    email VARCHAR(50) PRIMARY KEY,
    password VARCHAR(20) NOT NULL,
    fullname VARCHAR(100));        
    
DELIMITER $$
CREATE PROCEDURE `add_movie`(IN `title` VARCHAR(100), IN `year` INT, IN `director` VARCHAR(100), IN `banner_url` VARCHAR(200), IN `trailer_url` VARCHAR(200), IN `first_name` VARCHAR(50), IN `genre_name` VARCHAR(32), IN `last_name` VARCHAR(50), OUT `return_value` INT(11))
BEGIN
IF NOT EXISTS (SELECT * FROM movies where title = movies.title) THEN
INSERT INTO movies(id, title, year, director, banner_url, trailer_url)
VALUES(null, title, year, director, banner_url, trailer_url);
IF EXISTS (SELECT * FROM stars WHERE stars.first_name = first_name AND stars.last_name = last_name) THEN # star exists
INSERT INTO stars_in_movies(star_id, movie_id)
VALUES((SELECT id from stars WHERE stars.first_name = first_name AND stars.last_name = last_name), (SELECT movies.id FROM movies where title = movies.title));

ELSEIF NOT EXISTS (SELECT * FROM stars WHERE stars.first_name = first_name AND stars.last_name = last_name) THEN # star not exist 
INSERT INTO stars(id, first_name, last_name, dob, photo_url)
VALUES(null, first_name, last_name, null, null);
INSERT INTO stars_in_movies(star_id, movie_id)
VALUES((SELECT id from stars WHERE stars.first_name = first_name AND stars.last_name = last_name), (SELECT movies.id FROM movies where title = movies.title));
END IF;

IF EXISTS (SELECT * FROM genres where genres.name = genre_name) THEN # genre exists
INSERT INTO genres_in_movies(genre_id, movie_id)
VALUES((SELECT genres.id FROM genres WHERE genres.name = genre_name), (SELECT movies.id FROM movies where title = movies.title));

ELSEIF NOT EXISTS (SELECT * FROM genres WHERE genres.name = genre_name) THEN # genre not exist
INSERT INTO genres(id, name)
VALUES(null, genre_name);
INSERT INTO genres_in_movies(genre_id, movie_id)
VALUES((SELECT genres.id FROM genres WHERE genres.name = genre_name), (SELECT movies.id FROM movies WHERE title = movies.title));
END IF;
END IF;
END$$
DELIMITER ;