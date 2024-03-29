CREATE DATABASE Plan DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE Plan;

CREATE TABLE Plan (
    id VARCHAR(100) NOT NULL,
    title VARCHAR(100) NOT NULL,
    text TEXT NOT NULL,
    startDt DATETIME NOT NULL,
    endDt DATETIME NOT NULL
) CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;