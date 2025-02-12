CREATE TABLE `booking_guests`
(
    `booking_id` bigint NOT NULL,
    `guests_id`  bigint NOT NULL,
    PRIMARY KEY (`booking_id`, `guests_id`),
    KEY          `FK8mch9lbbg9uo9970t7bhuxjl7` (`guests_id`),
    CONSTRAINT `FK8mch9lbbg9uo9970t7bhuxjl7` FOREIGN KEY (`guests_id`) REFERENCES `guest` (`id`),
    CONSTRAINT `FKmn8ku1k7b14s5otjkuteobm1p` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;