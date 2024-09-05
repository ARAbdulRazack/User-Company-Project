--liquibase formatted sql
--changeset AbdulRazack:1

CREATE TABLE IF NOT EXISTS company (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS usercompany (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    company_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (company_id) REFERENCES company(id)
);
