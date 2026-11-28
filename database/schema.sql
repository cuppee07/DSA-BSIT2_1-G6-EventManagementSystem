CREATE DATABASE IF NOT EXISTS event_db;
USE event_db;

ALTER TABLE users MODIFY COLUMN password VARCHAR(255);
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(64) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS attendees (
    attendee_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    event_name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS registrations (
	registration_id VARCHAR(50) PRIMARY KEY,
    attendee_name VARCHAR(100) NOT NULL,
    event_name VARCHAR(100) NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'Checked-In',
    checked_in_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
    
CREATE TABLE IF NOT EXISTS events (
    event_id VARCHAR(50) PRIMARY KEY,
    event_name VARCHAR(100) NOT NULL,
    event_date VARCHAR(20) NOT NULL,
    category VARCHAR(50) NOT NULL,
    attendees INT NOT NULL DEFAULT 0
);
    
INSERT IGNORE INTO users (id, email, username, password, role)
VALUES (
    1,
    'admin@gmail.com',
    'admin',
    '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9',
    'Admin'
);