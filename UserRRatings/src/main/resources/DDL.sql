/**
* Script mysql wjma90
**/

CREATE DATABASE IF NOT EXISTS maddoxBD CHARACTER SET latin1 COLLATE latin1_swedish_ci;

USE maddoxBD;

SET foreign_key_checks = 0;

DROP TABLE IF EXISTS details;
DROP TABLE IF EXISTS notes;
DROP TABLE IF EXISTS orders;

DROP PROCEDURE IF EXISTS SP_FIND_DETAILS_BY_PHONENUMBER;

SET foreign_key_checks = 1;

/* this table is a mock */
CREATE TABLE orders(
	id INT NOT NULL AUTO_INCREMENT,
    customerName VARCHAR(50),
    phoneNumber VARCHAR(15),
    PRIMARY KEY ( id )
);
/**************************/

CREATE TABLE details(
   id INT NOT NULL AUTO_INCREMENT,
   orderID INT NOT NULL,
   productName VARCHAR(120) NOT NULL,
   rating INT,
   PRIMARY KEY ( id ),
   FOREIGN KEY (orderID) REFERENCES orders(id)
);

CREATE TABLE notes(
   id INT NOT NULL AUTO_INCREMENT,
   orderID INT NOT NULL,
   notes MEDIUMTEXT,
   PRIMARY KEY ( id ),
   FOREIGN KEY (orderID) REFERENCES details(orderID)
);

INSERT INTO `orders` (`id`, `customerName`, `phoneNumber`) VALUES
	(1, 'william', '12345678'),
	(2, 'wjma', '12345679');

INSERT INTO `details` (`id`, `orderID`, `productName`, `rating`) VALUES
	(1, 1, 'product 1', 4),
	(2, 1, 'product 2', 5),
	(3, 1, 'product 3', 6),
	(4, 2, 'product 1', 4),
	(5, 2, 'product 4', 2);
	
INSERT INTO `notes` (`id`, `orderID`, `notes`) VALUES
	(1, 1, 'text for table'),
	(2, 1, 'text for table 2');
	
DELIMITER //
CREATE PROCEDURE `SP_FIND_DETAILS_BY_PHONENUMBER` (IN phoneNumber VARCHAR(15))
BEGIN
	
	SELECT d.id, d.orderID, d.productName, d.rating 
	FROM details d
	WHERE d.orderID IN (SELECT o.id FROM orders o WHERE o.phoneNumber = phoneNumber);
	
	SELECT n.id, n.orderID, n.notes 
	FROM notes n
	WHERE n.orderID IN (SELECT o.id FROM orders o WHERE o.phoneNumber = phoneNumber);

END //