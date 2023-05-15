CREATE DATABASE identity_provider;

USE identity_provider;

CREATE TABLE users(
    user_id BIGINT AUTO_INCREMENT,
    user_name VARCHAR(100) NOT NULL ,
    email_local VARCHAR(100) NOT NULL ,
    email_domain VARCHAR(100) NOT NULL ,
    password VARCHAR(100) NOT NULL ,
    CONSTRAINT
    PRIMARY KEY (user_id)
);