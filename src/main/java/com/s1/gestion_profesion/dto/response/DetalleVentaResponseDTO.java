package com.s1.gestion_profesion.dto.response;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "DetalleVentaResponseDTO", description = "Respuesta de un item de detalle de venta")
public record DetalleVentaResponseDTO(
    @Schema(description = "ID del detalle", example = "10")
    Long id,
    @Schema(description = "Producto asociado al detalle")
    ProductoResponseDTO producto,
    @Schema(description = "Cantidad vendida", example = "2")
    Integer cantidad,
    @Schema(description = "Precio unitario aplicado", example = "129900.00")
    BigDecimal precioUnitario,
    @Schema(description = "Subtotal del detalle", example = "259800.00")
    BigDecimal subtotal
) {}
