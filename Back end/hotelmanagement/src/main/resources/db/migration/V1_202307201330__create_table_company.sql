CREATE TABLE `company`
(
    `id`            bigint       NOT NULL AUTO_INCREMENT,
    `created_by`    varchar(255) DEFAULT NULL,
    `created_date`  datetime(6) DEFAULT NULL,
    `modified_by`   varchar(255) DEFAULT NULL,
    `modified_date` datetime(6) DEFAULT NULL,
    `address`       varchar(255) DEFAULT NULL,
    `company_name`  varchar(255) NOT NULL,
    `email`         varchar(255) DEFAULT NULL,
    `phone`         varchar(255) DEFAULT NULL,
    `tax_number`    varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;