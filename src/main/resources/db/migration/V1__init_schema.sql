CREATE TABLE sleep_logs (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    bed_time TIMESTAMP NOT NULL,
    wake_time TIMESTAMP NOT NULL,
    total_time VARCHAR(5) NOT NULL,
    quality VARCHAR(10) NOT NULL
);

CREATE TABLE weight_logs (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    weight_kg NUMERIC(5,2) NOT NULL
);

CREATE TABLE food_logs (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    food_type VARCHAR(20) NOT NULL,
    description VARCHAR(100) NOT NULL
);

CREATE TABLE exercise_logs (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    activity VARCHAR(100) NOT NULL,
    total_time VARCHAR(5) NOT NULL
);

CREATE TABLE emotion_logs (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    feeling VARCHAR(100) NOT NULL,
    reason VARCHAR(500) NOT NULL
);

CREATE TABLE thought_logs (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    content VARCHAR(1500) NOT NULL
);
