package com.s1.gestion_profesion.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DetalleVentaRequestDTO(
    @NotNull(message = "El ID del producto no puede ser nulo")
    Long productoId,
    
    @NotNull(message = "La cantidad no puede ser nula")
    @Min(value = 1, message = "La cantidad debe ser mínimo 1")
    Integer cantidad,
    
    @NotNull(message = "El precio unitario no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    BigDecimal precioUnitario
) {}