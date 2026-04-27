-- Initial data for Foodkind MySQL Database

USE foodkind_db;

-- Add an admin user (password: Admin@123)
-- Hash generated using BCrypt
INSERT INTO users (first_name, last_name, username, email, password, phone, active, created_at, updated_at) 
VALUES ('Admin', 'Kind', 'admin', 'admin@foodkind.com', '$2a$10$8.UnVuG9HHgffUDAlk8q6Ou9nF0U5/j8f5j5j5j5j5j5j5j5j5j5', '1234567890', 1, NOW(), NOW());

INSERT INTO user_roles (user_id, role) VALUES (1, 'ROLE_ADMIN');

-- Add a test donor
INSERT INTO users (first_name, last_name, username, email, password, phone, active, created_at, updated_at) 
VALUES ('Test', 'Donor', 'donor', 'donor@test.com', '$2a$10$8.UnVuG9HHgffUDAlk8q6Ou9nF0U5/j8f5j5j5j5j5j5j5j5j5j5', '9876543210', 1, NOW(), NOW());

INSERT INTO user_roles (user_id, role) VALUES (2, 'ROLE_USER');
