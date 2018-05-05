DROP USER 'webstudent'@'localhost';
FLUSH PRIVILEGES;
CREATE USER 'webstudent'@'localhost' IDENTIFIED BY 'webstudent';


GRANT ALL PRIVILEGES ON * . * TO 'webstudent'@'localhost';
