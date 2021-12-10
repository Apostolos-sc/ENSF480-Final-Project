DROP DATABASE IF EXISTS PROPERTY_MANAGEMENT;
CREATE DATABASE PROPERTY_MANAGEMENT; 
USE PROPERTY_MANAGEMENT;

DROP TABLE IF EXISTS CONTRACT;
DROP TABLE IF EXISTS PROPERTY;
DROP TABLE IF EXISTS LANDLORD;
DROP TABLE IF EXISTS RENTER;
DROP TABLE IF EXISTS MANAGER;
DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS (
	userID INTEGER NOT NULL,
	fName VARCHAR(30) NOT NULL,
	lName VARCHAR(30) NOT NULL,
	email VARCHAR(30) NOT NULL,
	pass VARCHAR(30) NOT NULL,
	dob DATE NOT NULL,
	PRIMARY KEY(userID)
);

CREATE  TABLE LANDLORD (
	landlordID INTEGER NOT NULL,
	PRIMARY KEY (landlordID),
	FOREIGN KEY (landlordID) REFERENCES USERS(userID)
);

CREATE  TABLE RENTER (
	renterID INTEGER NOT NULL,
	PRIMARY KEY (renterID),
	FOREIGN KEY (renterID) REFERENCES USERS(userID)
);

CREATE  TABLE MANAGER (
	managerID INTEGER NOT NULL,
	PRIMARY KEY (managerID),
	FOREIGN KEY (managerID) REFERENCES USERS(userID)
);


CREATE TABLE PROPERTY (
	propertyID INTEGER NOT NULL, 
    landlordID INTEGER NOT NULL, 
    price DECIMAL(15, 2) NOT NULL,
	validPeriod INTEGER NOT NULL,
	startDate DATE NOT NULL,
	endDate DATE  NOT NULL,
	payment DECIMAL(15, 2) NOT NULL,
    address VARCHAR(30) NOT NULL,
	propertyType VARCHAR(30) NOT NULL,
    quadrant VARCHAR(2) NOT NULL, 
	state VARCHAR(30) NOT NULL,
	noBedrooms INTEGER NOT NULL,
    noBathrooms INTEGER NOT NULL,
    isFurnished TINYINT(1),
	PRIMARY KEY (propertyID),
	FOREIGN KEY(landlordID) REFERENCES LANDLORD(landlordID)
);

CREATE TABLE CONTRACT (
	contractID INTEGER NOT NULL,
	landlordID INTEGER NOT NULL,
	renterID INTEGER NOT NULL,
    propertyID INTEGER NOT NULL,
	startDate DATE NOT NULL,
	endDate DATE NOT NULL,
	monthlyRent DECIMAL(15, 2) NOT NULL,
	PRIMARY KEY (contractID),
    FOREIGN KEY (propertyID) REFERENCES PROPERTY(propertyID),
	FOREIGN KEY (landlordID) REFERENCES LANDLORD(landlordID),
	FOREIGN KEY (renterID) REFERENCES RENTER(renterID)
);

CREATE TABLE NOTIFCRITERIA(
	renterID INTEGER NOT NULL,
	propertyType VARCHAR(30) NOT NULL,
	noBedrooms INTEGER NOT NULL,
    noBathrooms INTEGER NOT NULL,
	quadrant VARCHAR(2) NOT NULL, 
	isFurnished TINYINT(1)
)

CREATE TABLE SUGGESTEDPROPERTY(
	renterID INTEGER NOT NULL,
	propertyID INTEGER NOT NULL
)

INSERT INTO USERS (userID, fname, lname, email, pass, dob) 
VALUES    
		(12, 'Tony', 'Montana', 't.montana@gmail.com', 'testing', '2012-02-17'),
		(15, 'Tony', 'Montana', 't.montana@gmail.com', 'testing', '2012-02-17'),
		(16, 'Al', 'Montana', 'a.montana@gmail.com', 'testing', '2012-02-17'),
		(17, 'Tony', 'Roma', 't.roma@gmail.com', 'testing', '2012-02-17'),
		(22, 'Zs', 'Montana', 'z.montana@gmail.com', 'testing', '2012-02-17'),
		(33, 'Angela', 'Montana', 'ang.montana@gmail.com', 'testing', '2012-02-17'),
		(44, 'Mike', 'Montana', 'm.montana@gmail.com', 'testing', '2012-02-17'),
		(55, 'John', 'Montana', 'j.montana@gmail.com', 'testing', '2012-02-17');
INSERT INTO RENTER (renterID)
VALUES 
	(12), (15), (16), (17), (22), (33), (44), (55);
INSERT INTO USERS (userID, fname, lname, email, pass, dob) 
VALUES  
	(5,'John', 'Smith', 'j.smith@gmail.com', 'tester', '2015-05-06');
INSERT INTO MANAGER (managerID)
VALUES
	(5);
INSERT INTO USERS (userID, fname, lname, email, pass, dob) 
VALUES
        (3, 'Jenny', 'Lance', 'j.lance@gmail.com', 'tested', '2016-12-12'),
        (4, 'Sandy', 'Lance', 's.lance@gmail.com', 'tested2', '2010-12-12'),
        (6, 'Randy', 'Lance', 'r.lance@gmail.com', 'tested3', '2009-12-12'),
        (7, 'Bandy', 'Lance', 'b.lance@gmail.com', 'tested4', '2008-12-12'),
		(8, 'Xandy', 'Lance', 'x.lance@gmail.com', 'tested5', '2001-12-12'),
        (9, 'Landy', 'Lance', 'l.lance@gmail.com', 'tested6', '2008-12-12'),
        (10, 'Pandy', 'Lance', 'p.lance@gmail.com', 'tested7', '1999-12-12'),
		(11, 'Zandy', 'Lance', 'z.lance@gmail.com', 'tested8', '1998-12-12');
INSERT INTO LANDLORD (landlordID)
VALUES
	(3), (4), (6), (7), (8), (9), (10), (11);
    
INSERT INTO PROPERTY (propertyID, landlordID, propertyType, noBedrooms, noBathrooms, isFurnished, address, quadrant, state, price)
VALUES
		(500, 3, 'Apartment', 4,1,true,'25 Bow Valley Crescent', 'SW','Listed', 500),
		(501, 4, 'Attached', 2,5,false,'20 Landing Ridge Crescent', 'NW','Listed', 700),
		(502, 3,'Townhouse', 1,3,false,'20 Combo Ridgeside Crescent', 'SW','Listed', 800),
		(503, 3, 'Detached', 14,25,true,'20 Bow Meadow Crescent', 'SE','Listed', 1000),
		(504, 3,'Detached', 4,5,true,'20 Bow Ridge Crescent', 'NW','Listed', 1200),
		(505, 4, 'Detached', 4,5,true,'20 Bow Ridge Crescent', 'SW','Listed', 1500),
		(506, 8,'Detached', 4,5,true,'20 Bow Ridge Crescent', 'SW','Listed', 1700),
		(507, 8, 'Detached', 4,5,true,'20 Bow Ridge Crescent', 'SW','Listed', 1900);