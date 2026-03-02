
-- mysql -u root -p gestion_profesion < script.sql

CREATE DATABASE IF NOT EXISTS gestion_profesion;
USE gestion_profesion;

CREATE TABLE IF NOT EXISTS producto (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS venta (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    numero_venta VARCHAR(20) UNIQUE NOT NULL,
    fecha_venta DATETIME DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(12, 2) NOT NULL,
    estado VARCHAR(20) DEFAULT 'PENDIENTE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS detalle_venta (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    venta_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(12, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (venta_id) REFERENCES venta(id) ON DELETE CASCADE,
    FOREIGN KEY (producto_id) REFERENCES producto(id)
);

CREATE INDEX idx_venta_numero ON venta(numero_venta);
CREATE INDEX idx_detalle_venta_id ON detalle_venta(venta_id);
CREATE INDEX idx_detalle_producto_id ON detalle_venta(producto_id);