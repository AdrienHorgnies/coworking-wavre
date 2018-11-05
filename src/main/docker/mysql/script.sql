CREATE TABLE `user` ( 
    `id` SERIAL NOT NULL AUTO_INCREMENT COMMENT 'identify a user unequivocally',
    `email` VARCHAR(255) NOT NULL COMMENT 'contact user, send private information or reset password',
    `password` CHAR(60) BINARY NOT NULL COMMENT 'cryptographic hash of user password',
    `last_name` VARCHAR(100) NOT NULL COMMENT 'family name used to address user in communication',
    `first_name` VARCHAR(100) NOT NULL COMMENT 'first name used to address user in communication',
    PRIMARY KEY (`id`),
    UNIQUE (`email`)) ENGINE = InnoDB;


CREATE TABLE `cp` (
  `id` SERIAL NOT NULL AUTO_INCREMENT,
  `code` INT(5) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `space` (
  `id` SERIAL NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `area` INT(5) NOT NULL,
  `location` VARCHAR(250) NOT NULL,
  `cp_id` BIGINT NOT NULL


) ENGINE=InnoDB;



INSERT INTO `user` (`id`, `email`, `password`, `last_name`, `first_name`) VALUES
    (NULL, 'adrien.pierre.horgnies@gmail.com', '$2y$10$rbE59kdmEfSgKQL8cvRBHuRzKktjVXRpICafsx5d7/SITS4zEcInS', 'Horgnies', 'Adrien'),
    (NULL, 'baran.1702.student@ifosupwavre.be', '$2y$10$7kohD8s7JhspL5goSZ1JT.f5jcVXvOk4Vjt.6GATWQi/lnsJuoklK', 'Baran', 'Katia'),
    (NULL, 'chan.1804.student@ifosupwavre.be', '$2y$10$78lnxSlBaWpyTd7Z/W7tu.NaaQN4fgZUBSOJM5THmKFtVc/l5VyLC', 'Chan', 'Phirum'),
    (NULL, 'thiry.2906.student@ifosupwavre.be', '$2y$10$ny6EgjXFr8ZGHf9IQZ7L0uLLoNQPh9Ilbeih3fgSjORNis6QOq4BW', 'Thiry', 'Stéphane'),
    (NULL, 'stephane.huguier@ifosupwavre.be', '$2y$10$F6G9d2X8jWvuTNYZb1Jme.rSAK4fLkliOhMJsAoarGPv/uHoUJ43q', 'Huguier', 'Stéphane'),
    (NULL, 'xavier.jeunejean@ifosupwavre.be', '$2y$10$ruYha1JkYyMkFimirQiuO.qEFeVDnIR7fhek7PFhCM2I/tvL5P1Ky', 'Jeunejean', 'Xavier');


INSERT INTO `cp` (`id`, `code`, `city`) VALUES
(1, 1300,'Wavre'),
(2, 1360,'Perwez'),
(3, 1410,'Waterloo');

INSERT INTO `space` (`id`, `name`, `area`, `location`,`cp_id` ) VALUES
(NULL, 'espace1', 20, 'Grand place 29',2),
(NULL, 'espace2', 25, 'Place Bosch 29',1),
(NULL, 'espace3', 30, 'Place de la Gare 1',2);



