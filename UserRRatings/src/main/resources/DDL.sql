/**
* Script mysql wjma90
**/

CREATE DATABASE IF NOT EXISTS maddoxBD CHARACTER SET latin1 COLLATE latin1_swedish_ci;

USE maddoxBD;

DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS details;

CREATE TABLE orders(
	id INT NOT NULL AUTO_INCREMENT,
    customerName VARCHAR(50),
    phoneNumber VARCHAR(15),
    PRIMARY KEY ( id )
);

CREATE TABLE details(
   id INT NOT NULL AUTO_INCREMENT,
   orderID INT NOT NULL,
   productName VARCHAR(120) NOT NULL,
   rating INT,
   notes MEDIUMTEXT,
   PRIMARY KEY ( id ),
   FOREIGN KEY (orderID) REFERENCES orders(id)
);