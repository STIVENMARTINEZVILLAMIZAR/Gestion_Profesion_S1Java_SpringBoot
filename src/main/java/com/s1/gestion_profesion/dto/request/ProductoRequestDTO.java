package com.s1.gestion_profesion.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductoRequestDTO(
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    String nombre,
    
    @Size(max = 500, message = "La descripción máximo 500 caracteres")
    String descripcion,
    
    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    BigDecimal precio,
    
    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 1, message = "El stock debe ser mínimo 1")
    Integer stock
) {}