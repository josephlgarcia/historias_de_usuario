/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  xxx
 * Created: Oct 11, 2025
 */

DROP DATABASE IF EXISTS minitienda_db;
CREATE DATABASE minitienda_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE minitienda_db;

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(60) NOT NULL UNIQUE,
	stock INT NOT NULL,
	price DOUBLE NOT NULL
);

SELECT * FROM products;