CREATE TABLE `room`
(
    `id`            bigint         NOT NULL AUTO_INCREMENT,
    `created_by`    varchar(255) DEFAULT NULL,
    `created_date`  datetime(6) DEFAULT NULL,
    `modified_by`   varchar(255) DEFAULT NULL,
    `modified_date` datetime(6) DEFAULT NULL,
    `deleted`       bit(1)       DEFAULT NULL,
    `max_capacity`  int            NOT NULL,
    `note`          varchar(255) DEFAULT NULL,
    `price`         decimal(10, 2) NOT NULL,
    `room_number`   varchar(255)   NOT NULL,
    `room_status`   enum('CLEAN','DIRTY','NOT_READY','OUT_OF_ORDER') NOT NULL,
    `room_type`     enum('DLX','SIG_SUITE','SUITE','SUP') NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;