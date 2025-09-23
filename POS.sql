-- Mesas
CREATE TABLE mesas (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    numero INT UNIQUE NOT NULL,
    capacidad INT
);

-- Empleados
CREATE TABLE empleados (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    rol VARCHAR(50) NOT NULL, -- mesero, cocinero, admin
    email VARCHAR(100),
    contraseña TEXT NOT NULL
);

-- Categorías de productos
CREATE TABLE categorias (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Productos del menú
CREATE TABLE productos (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio NUMERIC(10,2) NOT NULL,
    categoria_id INT REFERENCES categorias(id),
    disponible BOOLEAN DEFAULT TRUE
);

-- Pedidos (uno por mesa o cliente)
CREATE TABLE pedidos (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    mesa_id INT REFERENCES mesas(id),
    empleado_id INT REFERENCES empleados(id),
    estado VARCHAR(50) DEFAULT 'pendiente', -- pendiente, en preparación, servido, pagado, cancelado
    observaciones TEXT
);

-- Detalle de productos por pedido
CREATE TABLE detalle_pedido (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    pedido_id INT REFERENCES pedidos(id) ON DELETE CASCADE,
    producto_id INT REFERENCES productos(id),
    cantidad INT NOT NULL,
    precio_unitario NUMERIC(10,2) NOT NULL,
    estado_preparacion VARCHAR(30) DEFAULT 'pendiente', -- pendiente, preparando, listo, servido
    notas TEXT -- opcional: "sin cebolla", "muy cocido", etc.
);

-- Comandas que se muestran en cocina
-- Esto puede ser una vista, pero también una tabla si se necesita persistencia
CREATE TABLE comandas (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    pedido_id INT REFERENCES pedidos(id),
    detalle_pedido_id INT REFERENCES detalle_pedido(id),
    hora_enviada TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(30) DEFAULT 'pendiente' -- pendiente, preparando, listo
);

-- Facturas (cuando se paga)
CREATE TABLE facturas (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    pedido_id INT REFERENCES pedidos(id),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total NUMERIC(10,2) NOT NULL,
    metodo_pago VARCHAR(50) -- efectivo, tarjeta, etc.
);
