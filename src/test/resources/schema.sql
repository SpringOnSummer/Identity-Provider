
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


MERGE INTO `Members` values (100,'Member_One','one','mail.com','one_password');
MERGE INTO `Members` values (200,'Member_One','one','mail.com','one_password');

MERGE INTO `Member_details` values (100,100,'010-1234-5678','nick_name',now());