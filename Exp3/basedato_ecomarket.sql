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

-- Crear tabla soporte
DROP TABLE IF EXISTS `soporte`;
CREATE TABLE `soporte` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activo` bit(1) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `fecha_respuesta` datetime(6) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  `mensaje` varchar(1000) DEFAULT NULL,
  `respuesta` varchar(1000) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `soporte` ( `activo`, `estado`, `fecha_creacion`, `fecha_respuesta`, `id_usuario`, `mensaje`, `respuesta`, `tipo`) VALUES
(_binary '1', 'abierto', NOW(), NULL, 1, 'No recibí mi pedido completo.', NULL, 'reclamo'),
(_binary '1', 'resuelto', '2024-06-01 10:15:00.000000', '2024-06-01 12:00:00.000000', 2, '¿Cuáles son los métodos de pago disponibles?', 'Puede pagar con tarjeta, débito o transferencia.', 'consulta'),
(_binary '1', 'en_progreso', '2024-06-10 09:00:00.000000', NULL, 3, 'Sería bueno incluir más productos veganos.', NULL, 'sugerencia');

-- esto es para notificacion.
CREATE TABLE IF NOT EXISTS notificacion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    tipo VARCHAR(255),
    mensaje VARCHAR(1000),
    fecha_creacion DATETIME,
    leido BOOLEAN
);

-- Datos iniciales notificacion
INSERT INTO notificacion (id_usuario, tipo, mensaje, fecha_creacion, leido) VALUES
(1, 'sistema', 'Bienvenido a la plataforma', NOW(), false),
(2, 'promocion', '50% de descuento', NOW(), false),
(3, 'recordatorio', 'Actualiza tu perfil', NOW(), true);

-- Crear tabla sucursal
DROP TABLE IF EXISTS `sucursal`;
CREATE TABLE `sucursal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID Sucursal',
  `nombre` varchar(255) DEFAULT NULL COMMENT 'Nombre',
  `direccion` varchar(255) DEFAULT NULL COMMENT 'Dirección',
  `ciudad` varchar(255) DEFAULT NULL COMMENT 'Ciudad',
  `region` varchar(255) DEFAULT NULL COMMENT 'Región',
  `zona_cobertura` varchar(255) DEFAULT NULL COMMENT 'Zona de Cobertura',
  `activa` bit(1) DEFAULT NULL COMMENT 'Activa',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `sucursal` (`nombre`, `direccion`, `ciudad`, `region`, `zona_cobertura`, `activa`) VALUES
('Sucursal Santiago Centro', 'Av. Libertador Bernardo O''Higgins 1234', 'Santiago', 'RM', 'Zona Metropolitana', _binary '1'),
('Sucursal Valdivia', 'Calle Independencia 456', 'Valdivia', 'XIV', 'Zona Sur', _binary '1'),
('Sucursal Antofagasta', 'Av. Angamos 789', 'Antofagasta', 'II', 'Zona Norte', _binary '1'),
('Sucursal Ñuñoa', 'Av. Irarrázaval 2222', 'Ñuñoa', 'RM', 'Zona Metropolitana', _binary '1'),
('Sucursal Providencia', 'Av. Providencia 1357', 'Providencia', 'RM', 'Zona Metropolitana', _binary '1'),
('Sucursal La Florida', 'Walker Martínez 8900', 'La Florida', 'RM', 'Zona Metropolitana', _binary '1');
