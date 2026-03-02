package com.s1.gestion_profesion.dto.request;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(name = "ProductoRequestDTO", description = "Datos requeridos para crear o actualizar un producto")
public record ProductoRequestDTO(
    @Schema(description = "Nombre unico del producto", example = "Mouse Logitech G203")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    String nombre,
    
    @Schema(description = "Descripcion del producto", example = "Mouse gamer RGB")
    @Size(max = 500, message = "La descripción máximo 500 caracteres")
    String descripcion,
    
    @Schema(description = "Precio unitario del producto", example = "129900.00")
    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    BigDecimal precio,
    
    @Schema(description = "Cantidad disponible en stock", example = "25")
    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 1, message = "El stock debe ser mínimo 1")
    Integer stock
) {}
