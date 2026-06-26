CREATE SEQUENCE user_seq START 1 INCREMENT 1;
CREATE SEQUENCE order_seq START 1 INCREMENT 1;



CREATE TABLE users (
                       id BIGINT PRIMARY KEY DEFAULT nextval('user_seq'),

                       email VARCHAR(30) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,

                       role VARCHAR(50) NOT NULL,
                       active BOOLEAN NOT NULL
);



CREATE TABLE orders (
                        id BIGINT PRIMARY KEY DEFAULT nextval('order_seq'),

                        user_id BIGINT NOT NULL,
                        tour_id BIGINT NOT NULL,

                        status VARCHAR(50) NOT NULL,

                        total_price NUMERIC(10, 2) NOT NULL,

                        created_at TIMESTAMP NOT NULL,

                        CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users(id),
                        CONSTRAINT fk_orders_tour FOREIGN KEY (tour_id) REFERENCES tours(id)
);