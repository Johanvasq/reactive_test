DROP TABLE IF EXISTS account;
CREATE TABLE account (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255)
);