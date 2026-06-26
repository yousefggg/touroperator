CREATE SEQUENCE tour_seq START 1 INCREMENT 1;

CREATE TABLE tours (
                       id BIGINT PRIMARY KEY DEFAULT nextval('tour_seq'),

                       tour_name VARCHAR(255) NOT NULL UNIQUE,
                       tour_description TEXT,

                       tour_price NUMERIC(10, 2) NOT NULL,

                       duration VARCHAR(255),

                       date_tour DATE NOT NULL,

                       active BOOLEAN NOT NULL,

                       created_at TIMESTAMP NOT NULL,
                       update_at TIMESTAMP NOT NULL
);