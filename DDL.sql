/**
* Script mysql wjma90
**/

CREATE DATABASE IF NOT EXISTS maddoxBD CHARACTER SET latin1 COLLATE latin1_swedish_ci;

USE maddoxBD;

SET foreign_key_checks = 0;

DROP TABLE IF EXISTS details;
DROP TABLE IF EXISTS notes;
DROP TABLE IF EXISTS orders;

DROP PROCEDURE IF EXISTS SP_FIND_PRODUCTS_BY_ORDERID;
DROP PROCEDURE IF EXISTS SP_FIND_NOTES_BY_ORDERID;

SET foreign_key_checks = 1;

CREATE TABLE details(
   	id INT(11) NOT NULL AUTO_INCREMENT,
	orderID INT(11) NOT NULL,
	product_name VARCHAR(120) NOT NULL,
	rating INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE notes(
   id INT NOT NULL AUTO_INCREMENT,
   orderID INT NOT NULL,
   notes MEDIUMTEXT,
   PRIMARY KEY ( id )
);

-- It's a mock table
CREATE TABLE orders (
	id INT(11) NOT NULL,
	phoneNumber VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)

INSERT INTO `details` (`id`, `orderID`, `product_name`, `rating`) VALUES
	(1, 1, 'product 1', 2),
	(2, 1, 'product 2', 3),
	(3, 1, 'product 3', 2),
	(4, 2, 'product 1', 4),
	(5, 2, 'product 4', 2);
	
INSERT INTO `notes` (`id`, `orderID`, `notes`) VALUES
	(1, 1, 'asdas'),
	(2, 1, 'qweqwe'),
	(3, 2, 'gdfd');
	
INSERT INTO `orders` (`id`, `phoneNumber`) VALUES
	(1, '12345678'),
	(2, '12345678');
	
DELIMITER //
CREATE PROCEDURE `SP_FIND_PRODUCTS_BY_ORDERID` (IN orderID INT)
BEGIN
	
	SELECT d.orderID, d.product_name, d.rating, d.id
	FROM details d
	WHERE d.orderID = orderID;

END //

DELIMITER //
CREATE PROCEDURE `SP_FIND_NOTES_BY_ORDERID` (IN orderID INT)
BEGIN
	
	SELECT n.id, n.notes, n.orderID 
	FROM notes n
	WHERE n.orderID = orderID;

END //