CREATE DATABASE  IF NOT EXISTS `coffeeshop`;
USE `coffeeshop`;


DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `manager` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;




LOCK TABLES `employee` WRITE;

INSERT INTO `employee` VALUES (1,'Frank','Bost','frank@ucla.edu', '9087653212', 'Denisse'),(2,'Ryan','Paul','ryan.paul@sjsu.edu', '7546547869', 'Jennie'),(3,'Philips','Chen','Chen@sjsu.edu', '9091235468', 'Brenda'),(4,'Aaron','Smith','asmith@sjsu.edu', '9078906584', 'Robert'),(5,'Dan','Perry','dperry@sjsu.edu', '4089245678', 'Sam'),(6,'Roger','Federer','roger@tennis.com', '8796123452', 'Jim'),(7,'Chris','Lattner','chris@google.com', '4567896512', 'Larry Page');

UNLOCK TABLES;

