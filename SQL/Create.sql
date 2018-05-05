-- DROP USER 'cmpe'@'localhost';
FLUSH PRIVILEGES;
CREATE USER 'cmpe'@'localhost' IDENTIFIED BY 'cmpe';


GRANT ALL PRIVILEGES ON * . * TO 'cmpe'@'localhost';
