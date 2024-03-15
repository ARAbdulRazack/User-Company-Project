--liquibase formatted sql

--changeset sergio:1
create table if not exists new(
id bigserial primary key,
name varchar(255)
);
create sequence if not exists new start 1000 increment 1;

--changeset sergio:2
CREATE TABLE if not exists company (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(20)
);

--changeset sergio:3
CREATE TABLE if not exists "user" (
    id BIGSERIAL PRIMARY KEY,
    company_id BIGINT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(20),
    FOREIGN KEY (company_id) REFERENCES company(id)
);

--changeset sergio:4
DROP table "user"


--changeset sergio:5
CREATE TABLE if not exists users (
    id BIGSERIAL PRIMARY KEY,
    company_id BIGINT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(20),
    FOREIGN KEY (company_id) REFERENCES company(id)
);

--changeset sergio:6
ALTER TABLE users
DROP CONSTRAINT IF EXISTS users_company_id_fkey;

--changeset sergio:7
CREATE TABLE IF NOT EXISTS usercompany (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    company_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (company_id) REFERENCES company(id)
);

--changeset sergio:8
ALTER TABLE users
DROP COLUMN company_id;

--changeset sergio:9
CREATE TABLE admin (
    id SERIAL PRIMARY KEY,
    phone_number VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

--changeset sergio:10
DROP table admin

--changeset sergio:11
CREATE TABLE userinfo (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    roles VARCHAR(255)
);

--changeset sergio:12
ALTER TABLE userinfo
ALTER COLUMN name SET NOT NULL;
ALTER TABLE userinfo
ALTER COLUMN password SET NOT NULL;
ALTER TABLE userinfo
ADD CONSTRAINT unique_name UNIQUE (name);

--changeset sergio:13
DROP table userinfo

--changeset sergio:14
CREATE TABLE userinfo (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);