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
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE,
    genero VARCHAR(10),
    nacionalidad VARCHAR(50),
    email VARCHAR(150) UNIQUE,
    telefono VARCHAR(50),
    numero_hijos INT DEFAULT 0,
    ocupacion VARCHAR(100),
    ingreso_anual DECIMAL(12,2),
    idiomas VARCHAR(200),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado BOOLEAN DEFAULT TRUE
);

-- Preferencias del cliente
CREATE TABLE client_preference (
    id SERIAL PRIMARY KEY,
    client_id INT REFERENCES client(id) ON DELETE CASCADE,
    categoria VARCHAR(50) NOT NULL,
    nivel_interes INT CHECK (nivel_interes BETWEEN 1 AND 5),
    detalles TEXT
);

-- Paquetes de viaje
CREATE TABLE travel_package (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT,
    duracion_dias INT,
    precio_estimado DECIMAL(12,2),
    tipo_enfoque VARCHAR(50),
    nivel_dificultad INT,
    publico_objetivo VARCHAR(50),
    idioma_atencion VARCHAR(50)
);

-- Hoteles
CREATE TABLE hotel (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    direccion TEXT,
    categoria INT CHECK (categoria BETWEEN 1 AND 5),
    precio_noche DECIMAL(12,2),
    tipo_habitacion VARCHAR(50),
    capacidad INT,
    servicios JSONB,
    ubicacion VARCHAR(100)
);

-- Restaurantes
CREATE TABLE restaurant (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    tipo_cocina VARCHAR(50),
    ubicacion VARCHAR(100),
    precio_promedio DECIMAL(12,2),
    capacidad INT,
    servicios JSONB
);

-- Lugares turísticos
CREATE TABLE tourist_spot (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    tipo_lugar VARCHAR(50),
    ubicacion VARCHAR(100),
    precio_entrada DECIMAL(12,2),
    nivel_dificultad INT,
    restricciones TEXT
);

-- Relaciones Paquete ↔ Hotel
CREATE TABLE package_hotel (
    id SERIAL PRIMARY KEY,
    package_id INT REFERENCES travel_package(id) ON DELETE CASCADE,
    hotel_id INT REFERENCES hotel(id) ON DELETE CASCADE,
    orden INT
);

-- Relaciones Paquete ↔ Restaurante
CREATE TABLE package_restaurant (
    id SERIAL PRIMARY KEY,
    package_id INT REFERENCES travel_package(id) ON DELETE CASCADE,
    restaurant_id INT REFERENCES restaurant(id) ON DELETE CASCADE,
    orden INT
);

-- Relaciones Paquete ↔ Lugar turístico
CREATE TABLE package_spot (
    id SERIAL PRIMARY KEY,
    package_id INT REFERENCES travel_package(id) ON DELETE CASCADE,
    spot_id INT REFERENCES tourist_spot(id) ON DELETE CASCADE,
    orden INT
);

-- Historial de recomendaciones
CREATE TABLE recommendation_history (
    id SERIAL PRIMARY KEY,
    client_id INT REFERENCES client(id) ON DELETE CASCADE,
    package_id INT REFERENCES travel_package(id) ON DELETE CASCADE,
    fecha_recomendacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    aceptado BOOLEAN DEFAULT FALSE
);