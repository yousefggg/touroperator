CREATE SEQUENCE user_seq START 1 INCREMENT 1;

CREATE TABLE users (
                       id BIGINT PRIMARY KEY DEFAULT nextval('user_seq'),
                       email VARCHAR(30) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL,
                       active BOOLEAN NOT NULL
);