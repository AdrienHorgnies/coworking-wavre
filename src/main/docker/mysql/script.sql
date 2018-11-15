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
  `id` int(11) NOT NULL,
  `name_city` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `cp_city` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `building` (
  `id` int(11) NOT NULL,
  `name_building` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address_building` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB;

CREATE TABLE `space` (
  `id` int(11) NOT NULL,
  `name_space` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type_space` enum('private','open','bubble') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `area_space` int(11) DEFAULT NULL,
  `capacity_space` int(11) DEFAULT NULL,
  `building_id` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`building_id`) REFERENCES `building` (`id`)
) ENGINE=InnoDB;

CREATE TABLE `equipment_type` (
  `id` int(11) NOT NULL,
  `name_equipment` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `service_type` (
  `id` int(11) NOT NULL,
  `name_service` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
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

INSERT INTO `city` (`id`, `name_city`, `cp_city`) VALUES
(1, 'Wavre', 1300),
(2, 'Perwez', 1360),
(3, 'Waterloo', 1410),
(4, 'Bierges', 1301);

INSERT INTO `building` (`id`, `name_building`, `address_building`, `city_id`) VALUES
(1, 'Quatre Sapins', 'Clos des 4 sapins, 18', 1),
(2, 'Les Mimosas', 'Chaussée de Charleroi, 1', 2),
(3, 'Cinq Sapins', 'Rue des 5 sapins, 20', 1),
(4, 'La Sucrerie', 'Chaussée de Tervuren, 198', 3);

INSERT INTO `space` (`id`, `name_space`, `type_space`, `area_space`, `capacity_space`, `building_id`) VALUES
(1, 'sapin1', 'private', 20, 2, 1),
(2, 'sapin2', 'open', 120, 10, 1),
(3, 'spain3', 'bubble', 5, 1, 1),
(4, 'fleur1', 'private', 25, 2, 2),
(5, 'fleur2', 'open', 80, 7, 2),
(6, 'sapin21', 'private', 20, 2, 3),
(7, 'sapin22', 'open', 120, 10, 3),
(8, 'sucre1', 'private', 20, 2, 4),
(9, 'sucre2', 'private', 30, 2, 4),
(10, 'sucre3', 'open', 120, 10, 4),
(11, 'sucre4', 'bubble', 5, 1, 4);

INSERT INTO `equipment_type` (`id`, `name_equipment`) VALUES
(1, 'moniteur'),
(2, 'pc'),
(3, 'armoire'),
(4, 'téléphone');

INSERT INTO `service_type` (`id`, `name_service`) VALUES
(1, 'domiciliation'),
(2, 'mailing'),
(3, 'marketing'),
(4, 'comptabilité');
