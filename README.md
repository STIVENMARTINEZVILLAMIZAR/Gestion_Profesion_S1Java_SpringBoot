### SPRING BOOT JAVA -- STIVEN MARTINEZ VLLAMIZAR

## Documentacion API (Swagger)

- UI Swagger: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Modulos documentados

- `Productos`
  - `POST /api/productos`
  - `PUT /api/productos/{id}`
  - `GET /api/productos/{id}`
  - `GET /api/productos`
  - `GET /api/productos/buscar/{nombre}`
  - `DELETE /api/productos/{id}`
- `Detalles de venta`
  - `GET /api/detalles/{id}`
  - `GET /api/detalles/venta/{ventaId}`
- `Ventas`
  - `POST /api/ventas`
  - `GET /api/ventas/{id}`
  - `GET /api/ventas`
  - `DELETE /api/ventas/{id}`
  - `PATCH /api/ventas/{id}/estado?estado=PENDIENTE|COMPLETADA|CANCELADA`
