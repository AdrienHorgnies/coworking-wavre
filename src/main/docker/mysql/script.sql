CREATE TABLE `user` (
    `id`            SERIAL          NOT NULL AUTO_INCREMENT
    COMMENT 'identify a user unequivocally',
    `email`         VARCHAR(255)    NOT NULL
    COMMENT 'contact user, send private information or reset password',
    `password_hash` CHAR(60) BINARY NOT NULL
    COMMENT 'cryptographic hash of user password',
    `last_name`     VARCHAR(100)    NOT NULL
    COMMENT 'family name used to address user in communication',
    `first_name`    VARCHAR(100)    NOT NULL
    COMMENT 'first name used to address user in communication',
    PRIMARY KEY (`id`),
    UNIQUE (`email`)
)
    ENGINE = InnoDB;

CREATE TABLE `authority` (
    `name` VARCHAR(50) NOT NULL
    COMMENT 'identify an access level recognized by the application code',
    PRIMARY KEY (`name`)
)
    ENGINE = InnoDB;

CREATE TABLE `user_authority` (
    `user_id`        BIGINT UNSIGNED NOT NULL,
    `authority_name` VARCHAR(50)     NOT NULL,
    PRIMARY KEY (`user_id`, `authority_name`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    FOREIGN KEY (`authority_name`) REFERENCES `authority` (`name`)
)
    ENGINE = InnoDB;

CREATE TABLE `city` (
  `id` SERIAL NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `cp_city` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

INSERT INTO `user`(`id`, `email`, `password_hash`, `last_name`, `first_name`)
VALUES (1, 'system@localhost', '$2a$10$t9A4RrSdlcAUCCPmYd.8xOfBq39sNev4oQRdUWfQnumlMmCpVdNZm', 'System', 'System'),
       (2,
        'admin@localhost',
        '$2a$10$wMeLcX1dCoPajBV9MpbZRu4PsMR1AWVO2CE6s/bfey7/h5bgLALi.',
        'Administrator',
        'Administrator'),
       (3, 'user@localhost', '$2a$10$.Vt5BdgeuqXd2rsqA.UlIOfLNYMO2Hse4BOI5UIn5.KZUcjRWW5di', 'User', 'User'),
       (4, 'anonymous@localhost', '$2a$10$C9nfAndBnWq9WRSDiVOQj.RJEbq6lwwaT1QUupAwrZfF2gsevTrOm', 'User', 'Anonymous');

INSERT INTO `authority`(`name`)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER'),
       ('ROLE_ANONYMOUS');

INSERT INTO `user_authority`(`user_id`, `authority_name`)
VALUES (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN'),
       (2, 'ROLE_USER'),
       (3, 'ROLE_USER');

INSERT INTO `city` (`id`, `name`, `cp_city`) VALUES
(1, 'Wavre', 1300),
(2, 'Perwez', 1360),
(3, 'Waterloo', 1410),
(4, 'Bierges', 1301);
