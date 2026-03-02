package com.s1.gestion_profesion.dto.request;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(name = "DetalleVentaRequestDTO", description = "Item de detalle incluido en una venta")
public record DetalleVentaRequestDTO(
    @Schema(description = "ID del producto vendido", example = "1")
    @NotNull(message = "El ID del producto no puede ser nulo")
    Long productoId,
    
    @Schema(description = "Cantidad de unidades vendidas", example = "2")
    @NotNull(message = "La cantidad no puede ser nula")
    @Min(value = 1, message = "La cantidad debe ser mínimo 1")
    Integer cantidad,
    
    @Schema(description = "Precio unitario aplicado al item", example = "129900.00")
    @NotNull(message = "El precio unitario no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    BigDecimal precioUnitario
) {}
