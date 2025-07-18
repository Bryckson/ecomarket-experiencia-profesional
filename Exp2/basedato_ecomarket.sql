CREATE DATABASE IF NOT EXISTS `basedato_ecomarket` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `basedato_ecomarket`;

-- Crear tabla catalogo
DROP TABLE IF EXISTS `catalogo`;
CREATE TABLE `catalogo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `promocion` bit(1) DEFAULT NULL,
  `sucursal` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `catalogo` VALUES 
(1, 'Limpieza', 'Detergente ecológico, biodegradable y sin fosfatos', 'Eco Detergente 1L', 2990, _binary '', 'Sucursal Valdivia'),
(2, 'Alimentos', 'Galletas veganas de avena y cacao, sin azúcar añadida', 'Galletas Avena Cacao', 1890, _binary '', 'Sucursal Santiago Centro'),
(3, 'Higiene Personal', 'Cepillo de dientes de bambú compostable', 'Cepillo Bambú Eco', 1490, _binary '', 'Sucursal Antofagasta'),
(4, 'Hogar', 'Set de bolsas reutilizables para compras', 'Bolsas Reutilizables x5', 2490, _binary '', 'Sucursal Ñuñoa'),
(5, 'Alimentos', 'Aceite de coco orgánico prensado en frío 500ml', 'Aceite Coco Orgánico', 4990, _binary '', 'Sucursal Providencia'),
(6, 'Limpieza', 'Limpiador multiuso con aroma cítrico, 100% natural', 'Limpiador Natural Cítrico', 3290, _binary '', 'Sucursal La Florida'),
(7, 'Higiene Personal', 'Shampoo sólido de lavanda, vegano y sin químicos', 'Shampoo Lavanda Sólido', 3590, _binary '', 'Sucursal Valdivia'),
(8, 'Mascotas', 'Arena para gatos compostable y libre de químicos', 'Arena Eco Gato 5kg', 6990, _binary '', 'Sucursal Santiago Centro'),
(9, 'Jardinería', 'Compost orgánico en bolsa de 2kg', 'Compost Orgánico', 1990, _binary '', 'Sucursal Antofagasta'),
(10, 'Bebidas', 'Bebida energética natural con yerba mate y stevia', 'Eco Mate Power', 2690, _binary '', 'Sucursal Ñuñoa');

-- Crear tabla inventario
DROP TABLE IF EXISTS `inventario`;
CREATE TABLE `inventario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activo` bit(1) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `nombre_producto` varchar(255) DEFAULT NULL,
  `precio_unitario` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `inventario` VALUES
(1, _binary '', 80, 'Accesorios', 'Bolsa Reutilizable', 1500),
(2, _binary '', 30, 'Limpieza', 'Detergente Ecológico', 4790),
(3, _binary '', 60, 'Higiene', 'Jabón Natural', 2500),
(4, _binary '', 40, 'Accesorios', 'Botella de Vidrio', 3990);

-- Crear tabla usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activo` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  `rol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `usuario` VALUES
(1, NULL, 'bryc_gu@ecomarket.com', 'Bryckson Gutierrez', '$2a$10$claveHash', 'VENDEDOR'),
(2, NULL, 'camila_to@ecomarket.com', 'Camila Torres', '$2a$10$Camil4Segura2024', 'GERENTE'),
(3, NULL, 'fer_di@ecomarket.com', 'Fernanda Díaz', '$2a$10$Fer123456Hash', 'GERENTE REGIONAL');
