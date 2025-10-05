INSERT INTO client (firstName, lastName, birthDate, gender, nationality, email, phone, numberOfChildren, occupation, annualIncome, languages, active)
VALUES
('Carlos', 'Ramírez', '1985-03-10', 'M', 'Peruana', 'carlos.ramirez@email.com', '+51999999991', 2, 'Ingeniero', 55000.00, 'Español, Inglés', TRUE),
('María', 'González', '1990-07-22', 'F', 'Peruana', 'maria.gonzalez@email.com', '+51999999992', 1, 'Doctora', 72000.00, 'Español, Inglés', TRUE),
('Luis', 'Torres', '1978-11-02', 'M', 'Chilena', 'luis.torres@email.com', '+56999999993', 0, 'Arquitecto', 68000.00, 'Español', TRUE),
('Ana', 'Valdez', '1995-01-15', 'F', 'Argentina', 'ana.valdez@email.com', '+54999999994', 0, 'Diseñadora', 48000.00, 'Español, Inglés', TRUE);

INSERT INTO client_preference (client_id, category, interestLevel, details)
VALUES
(1, 'AVENTURA', 5, 'Le encantan los deportes extremos y la naturaleza.'),
(1, 'GASTRONOMÍA', 3, 'Disfruta probar platos locales.'),
(2, 'CULTURA', 4, 'Le interesa visitar museos y lugares históricos.'),
(2, 'DESCANSO', 5, 'Prefiere viajes relajantes en zonas naturales.'),
(3, 'AVENTURA', 4, 'Senderismo y escalada moderada.'),
(4, 'PLAYA', 5, 'Ama los destinos con mar y clima cálido.');

INSERT INTO hotel (name, address, stars, pricePerNight, roomType, capacity, services, location)
VALUES
('Hotel Miraflores Luxury', 'Av. Larco 789, Lima', 5, 380.00, 'Suite', 100, '{"wifi": true, "pool": true, "spa": true}', 'Lima'),
('Cusco Andes Lodge', 'Calle Saphi 222, Cusco', 4, 200.00, 'Doble', 60, '{"wifi": true, "breakfast": true}', 'Cusco'),
('Paracas Beach Resort', 'Playa Chaco, Paracas', 5, 450.00, 'Suite Vista al Mar', 120, '{"wifi": true, "pool": true, "restaurant": true}', 'Paracas'),
('Arequipa Colonial Hotel', 'Plaza de Armas 101, Arequipa', 3, 150.00, 'Simple', 40, '{"wifi": true, "breakfast": true}', 'Arequipa');

INSERT INTO restaurant (name, cuisineType, location, averagePrice, capacity, services)
VALUES
('El Señorío de Sulco', 'Peruana', 'Lima', 80.00, 120, '{"reserva": true, "wifi": true}'),
('Chicha', 'Andina', 'Cusco', 70.00, 80, '{"reserva": true, "delivery": true}'),
('La Huaca Pucllana', 'Gourmet Peruana', 'Lima', 120.00, 150, '{"wifi": true, "valet": true}'),
('Picantería Arequipeña', 'Tradicional', 'Arequipa', 50.00, 60, '{"reserva": false, "wifi": false}');

INSERT INTO tourist_spot (name, spotType, location, entranceFee, difficultyLevel, restrictions)
VALUES
('Machu Picchu', 'Histórico', 'Cusco', 150.00, 3, 'Altitud y escaleras.'),
('Islas Ballestas', 'Natural', 'Paracas', 80.00, 2, 'Prohibido nadar cerca de lobos marinos.'),
('Cañón del Colca', 'Natural', 'Arequipa', 50.00, 3, 'Caminar con guía obligatorio.'),
('Huacachina', 'Desierto', 'Ica', 40.00, 2, 'Proteger ojos del polvo.');

INSERT INTO travel_package (name, description, durationDays, estimatedPrice, focusType, difficultyLevel, targetAudience, serviceLanguage)
VALUES
('Aventura en los Andes', 'Explora montañas, cultura y gastronomía andina.', 5, 1200.00, 'AVENTURA', 3, 'Adultos jóvenes', 'Español'),
('Relax en la Costa', 'Descanso en playas y resorts del Pacífico.', 4, 950.00, 'DESCANSO', 1, 'Familias', 'Español'),
('Cultura Imperial', 'Visita las joyas históricas del Cusco y Machu Picchu.', 6, 1400.00, 'CULTURA', 2, 'Parejas', 'Español'),
('Gastronomía Peruana', 'Un recorrido por los mejores sabores del Perú.', 3, 800.00, 'GASTRONOMÍA', 1, 'Foodies', 'Español');

INSERT INTO package_hotel (package_id, hotel_id, orderIndex)
VALUES
(1, 2, 1), -- Cusco Andes Lodge
(2, 3, 1), -- Paracas Beach Resort
(3, 2, 1), -- Cusco Andes Lodge
(4, 1, 1); -- Hotel Miraflores Luxury

INSERT INTO package_restaurant (package_id, restaurant_id, orderIndex)
VALUES
(1, 2, 1), -- Chicha
(2, 1, 1), -- El Señorío de Sulco
(3, 2, 1), -- Chicha
(4, 3, 1); -- La Huaca Pucllana

INSERT INTO package_spot (package_id, spot_id, orderIndex)
VALUES
(1, 1, 1), -- Machu Picchu
(2, 2, 1), -- Islas Ballestas
(3, 1, 1), -- Machu Picchu
(3, 3, 2), -- Cañón del Colca
(4, 4, 1); -- Huacachina

INSERT INTO recommendation_history (client_id, package_id, accepted)
VALUES
(1, 1, TRUE),
(2, 3, FALSE),
(3, 2, TRUE),
(4, 4, FALSE);
