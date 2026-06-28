
CREATE SEQUENCE IF NOT EXISTS order_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS "orders" (
                                        id BIGINT NOT NULL,
                                        user_id BIGINT NOT NULL,
                                        tour_id BIGINT NOT NULL,
                                        status VARCHAR(50) NOT NULL,
    total_price NUMERIC(10, 2) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    version BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL,

    CONSTRAINT pk_orders PRIMARY KEY (id),
    CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_orders_tour FOREIGN KEY (tour_id) REFERENCES tours(id)
    );

CREATE INDEX IF NOT EXISTS idx_orders_tour_dates ON "orders" (tour_id, start_date, end_date);
