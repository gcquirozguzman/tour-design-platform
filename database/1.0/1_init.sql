-- Database: tour_design

-- DROP DATABASE IF EXISTS tour_design;

CREATE DATABASE tour_design
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- 3️⃣ Crear tablas

-- Clientes
CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    birthDate DATE,
    gender VARCHAR(10),
    nationality VARCHAR(50),
    email VARCHAR(150) UNIQUE,
    phone VARCHAR(50),
    numberOfChildren INT DEFAULT 0,
    occupation VARCHAR(100),
    annualIncome DECIMAL(12,2),
    languages VARCHAR(200),
    registrationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    active BOOLEAN DEFAULT TRUE
);

-- Preferencias del cliente
CREATE TABLE client_preference (
    id SERIAL PRIMARY KEY,
    client_id INT REFERENCES client(id) ON DELETE CASCADE,
    category VARCHAR(50) NOT NULL,
    interestLevel INT CHECK (nivel_interes BETWEEN 1 AND 5),
    details TEXT
);

-- Paquetes de viaje
CREATE TABLE travel_package (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    durationDays INT,
    estimatedPrice DECIMAL(12,2),
    focusType VARCHAR(50),
    difficultyLevel INT,
    targetAudience VARCHAR(50),
    serviceLanguage VARCHAR(50)
);

-- Hoteles
CREATE TABLE hotel (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    address TEXT,
    stars INT CHECK (categoria BETWEEN 1 AND 5),
    pricePerNight DECIMAL(12,2),
    roomType VARCHAR(50),
    capacity INT,
    services JSONB,
    location VARCHAR(100)
);

-- Restaurantes
CREATE TABLE restaurant (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    cuisineType VARCHAR(50),
    location VARCHAR(100),
    averagePrice DECIMAL(12,2),
    capacity INT,
    services JSONB
);

-- Lugares turísticos
CREATE TABLE tourist_spot (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    spotType VARCHAR(50),
    location VARCHAR(100),
    entranceFee DECIMAL(12,2),
    difficultyLevel INT,
    restrictions TEXT
);

-- Relaciones Paquete ↔ Hotel
CREATE TABLE package_hotel (
    id SERIAL PRIMARY KEY,
    package_id INT REFERENCES travel_package(id) ON DELETE CASCADE,
    hotel_id INT REFERENCES hotel(id) ON DELETE CASCADE,
    orderIndex INT
);

-- Relaciones Paquete ↔ Restaurante
CREATE TABLE package_restaurant (
    id SERIAL PRIMARY KEY,
    package_id INT REFERENCES travel_package(id) ON DELETE CASCADE,
    restaurant_id INT REFERENCES restaurant(id) ON DELETE CASCADE,
    orderIndex INT
);

-- Relaciones Paquete ↔ Lugar turístico
CREATE TABLE package_spot (
    id SERIAL PRIMARY KEY,
    package_id INT REFERENCES travel_package(id) ON DELETE CASCADE,
    spot_id INT REFERENCES tourist_spot(id) ON DELETE CASCADE,
    orderIndex INT
);

-- Historial de recomendaciones
CREATE TABLE recommendation_history (
    id SERIAL PRIMARY KEY,
    client_id INT REFERENCES client(id) ON DELETE CASCADE,
    package_id INT REFERENCES travel_package(id) ON DELETE CASCADE,
    recommendationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    accepted BOOLEAN DEFAULT FALSE
);