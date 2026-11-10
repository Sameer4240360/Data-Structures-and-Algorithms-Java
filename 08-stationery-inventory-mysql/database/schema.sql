-- Run this once in MySQL before starting the program:
--   mysql -u root -p < database/schema.sql

CREATE DATABASE IF NOT EXISTS stationery_db;
USE stationery_db;

CREATE TABLE IF NOT EXISTS items (
    id        INT PRIMARY KEY,
    name      VARCHAR(100) NOT NULL,
    price     DOUBLE       NOT NULL,
    quantity  INT          NOT NULL
);
