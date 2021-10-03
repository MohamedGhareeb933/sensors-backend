CREATE SCHEMA IF NOT EXISTS `sensors_joined`;

USE `sensors_joined`;

CREATE TABLE IF NOT EXISTS `environment` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) DEFAULT NULL,
    `alarm` TINYINT(1) DEFAULT NULL, 
    `email` VARCHAR(255) DEFAULT NULL, 
    PRIMARY KEY(`id`)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `location` (
	`id` BIGINT NOT NULL AUTO_INCREMENT, 
    `name` VARCHAR(255) DEFAULT NULL,
    `ab_temp` TINYINT(1) DEFAULT NULL,
    `ab_light` TINYINT(1) DEFAULT NULL,
    `ab_humidity` TINYINT(1) DEFAULT NULL,
    `environment_id` BIGINT DEFAULT NULL,
    PRIMARY KEY(`id`),
    CONSTRAINT `fk_environment_location` FOREIGN KEY (`environment_id`) REFERENCES `environment`(`id`)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `sensor` (
    `id` BIGINT NOT NULL AUTO_INCREMENT, 
    `min` FLOAT DEFAULT NULL, 
    `max` FLOAT DEFAULT NULL, 
    `active` TINYINT(1) DEFAULT NULL,
    `location_id` BIGINT DEFAULT NULL , 
    PRIMARY KEY(`id`),
    CONSTRAINT `fk_location_sensor` FOREIGN KEY (`location_id`) REFERENCES `location`(`id`)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `humidity_sensor` (
    `id` BIGINT NOT NULL AUTO_INCREMENT, 
    `absolute` FLOAT DEFAULT NULL, 
    `relative` FLOAT DEFAULT NULL, 
    PRIMARY KEY(`id`),
    CONSTRAINT `fk_sensor_humidity` FOREIGN KEY (`id`) REFERENCES `sensor`(`id`)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `light_sensor` (
    `id` BIGINT NOT NULL AUTO_INCREMENT, 
    `radiometry` FLOAT DEFAULT NULL, 
    `luminous` FLOAT DEFAULT NULL, 
    PRIMARY KEY(`id`),
    CONSTRAINT `fk_sensor_light` FOREIGN KEY (`id`) REFERENCES `sensor`(`id`)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `temp_sensor` (
    `id` BIGINT NOT NULL AUTO_INCREMENT, 
    `temp` FLOAT DEFAULT NULL, 
    PRIMARY KEY(`id`),
    CONSTRAINT `fk_sensor_temp` FOREIGN KEY (`id`) REFERENCES `sensor`(`id`)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
