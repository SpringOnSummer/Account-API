CREATE TABLE `Members`
(
    `member_id`    BIGINT AUTO_INCREMENT PRIMARY KEY ,
    `member_name`  VARCHAR(100) NOT NULL,
    `email_local`  VARCHAR(100) NOT NULL,
    `email_domain` VARCHAR(100) NOT NULL,
    `password`     VARCHAR(100) NOT NULL
);


CREATE TABLE `Member_details` (
    `member_detail_id` BIGINT AUTO_INCREMENT PRIMARY KEY ,
    `member_id` BIGINT NOT NULL,
    `phone_number` VARCHAR(100) NOT NULL,
    `nick_name` VARCHAR(100) NOT NULL,
    `joined_at` DATETIME NOT NULL,
    FOREIGN KEY (`member_id`) REFERENCES `Members`(`member_id`)
);
