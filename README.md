# Sistema POS (Point of Sale)

Sistema de punto de venta desarrollado con Spring Boot para gestión de restaurantes.

## Descripción

API REST para un sistema POS que actualmente incluye:

- Gestión de categorías de productos
- Gestión de productos del menú
- Mapeo de entidades con base de datos PostgreSQL
- Validaciones de negocio básicas

## Tecnologías

- Java 20
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok
- dotenv-java para variables de entorno

### Configuración de Base de Datos

1. Crear base de datos en PostgreSQL:

```sql
CREATE DATABASE sistema_pos;
```

2. Ejecutar el script POS.sql incluido en el proyecto para crear las tablas necesarias.

## API Endpoints

### Categorías

- GET `/api/categoria/all` - Listar todas las categorías
- POST `/api/categoria/save` - Crear nueva categoría

### Productos

- GET `/api/producto/all` - Listar todos los productos
- POST `/api/producto/save` - Crear nuevo producto

## Estructura de Base de Datos

El proyecto incluye las siguientes tablas:

- `categorias` - Categorías de productos
- `productos` - Productos del menú
- `mesas` - Mesas del restaurante
- `empleados` - Personal del establecimiento
- `pedidos` - Órdenes de clientes
- `detalle_pedido` - Detalles de cada pedido
- `comandas` - Comandas para cocina
- `facturas` - Facturas de venta

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/edu/usbcali/sistema/pos/
│   │   ├── controller/      # Controladores REST
│   │   ├── domain/          # Entidades JPA
│   │   ├── dto/             # Data Transfer Objects
│   │   ├── mapper/          # Mappers entre entidades y DTOs
│   │   ├── repository/      # Repositorios JPA
│   │   ├── service/         # Lógica de negocio
│   │   └── SistemaposApplication.java
│   └── resources/
│       └── application.properties
└── test/                    # Pruebas unitarias
```
