### API de Gestión de Productos y Ventas (Spring Boot)

<p align="center">
<img src="https://img.shields.io/badge/Java-17-orange?logo=java&logoColor=white"/> <img src="https://img.shields.io/badge/MySQL-8.0.45-blue?logo=mysql&logoColor=white"/> <img src="https://img.shields.io/badge/Maven-4.0.0-C71A36?logo=apache-maven&logoColor=white"/> <img src="https://img.shields.io/badge/Editor-VSCode-007ACC?logo=visualstudiocode&logoColor=white"/> <img src="https://img.shields.io/badge/github-repo-blue?logo=github"/><img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=Spring&logoColor=white"/> 
</p>


Backend REST con autenticación JWT, documentación Swagger y persistencia MySQL.

## Alcance
- CRUD de productos con búsqueda por nombre y control de stock.
- Registro y consulta de ventas con múltiples ítems y estados (PENDIENTE, COMPLETADA, CANCELADA).
- Consulta de detalles de venta.
- Autenticación con JWT para proteger los endpoints.

## Tecnologías
- Java 17 · Spring Boot 3.5.11 (Web, Data JPA, Security, Validation)
- MySQL 8 · Hibernate
- JWT (jjwt 0.11.5)
- Springdoc OpenAPI 2.8.15
- Maven 4.0.0

## Endpoints principales
- Auth
  - `POST /api/auth/login` → genera token JWT.
- Productos
  - `POST /api/productos`
  - `PUT /api/productos/{id}`
  - `GET /api/productos/{id}`
  - `GET /api/productos`
  - `GET /api/productos/buscar/{nombre}`
  - `DELETE /api/productos/{id}`
- Ventas
  - `POST /api/ventas`
  - `GET /api/ventas/{id}`
  - `GET /api/ventas`
  - `DELETE /api/ventas/{id}`
  - `PATCH /api/ventas/{id}/estado?estado=PENDIENTE|COMPLETADA|CANCELADA`
- Detalles de venta
  - `GET /api/detalles/{id}`
  - `GET /api/detalles/venta/{ventaId}`

## Seguridad (JWT)
1) Autenticarse: `POST /api/auth/login` con body `{"username":"admin","password":"admin123"}` (usuarios in-memory: admin/admin123, user/user123).
2) Enviar el token en `Authorization: Bearer <token>`.
3) En Swagger UI usar **Authorize** con esquema Bearer.

## Configuración
`src/main/resources/application.properties`
```
spring.datasource.url=jdbc:mysql://localhost:3306/gestion_profesion
spring.datasource.username=campus2023
spring.datasource.password=campus2023
spring.jpa.hibernate.ddl-auto=update
jwt.secret=MiClaveSecretaMuySeguraParaJWT1234567890
jwt.expiration=3600000
```

## Ejecución
```bash
mvn clean compile
mvn spring-boot:run
```
Swagger UI: `http://localhost:8080/swagger-ui.html`  
OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Validaciones clave
- Producto: nombre único, precio > 0, stock ≥ 1.
- Venta: número único, stock verificado por ítem, estados permitidos definidos.

## Estructura resumida
- `controller`: Auth, Producto, Venta, DetalleVenta
- `service` / `impl`: reglas de negocio
- `repository`: JPA repositories
- `dto`: request/response y mappers
- `security`: JWT util, filtro
- `config`: OpenAPI y Security
