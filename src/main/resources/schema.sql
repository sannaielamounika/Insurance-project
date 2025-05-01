-- src/main/resources/schema.sql

CREATE TABLE IF NOT EXISTS POLICY (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    policy_holder_name VARCHAR(255),
    policy_type VARCHAR(255),
    policy_price DECIMAL(10, 2),
    policy_start_date DATE,
    policy_end_date DATE
);
