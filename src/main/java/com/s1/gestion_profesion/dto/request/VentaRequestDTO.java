package com.s1.gestion_profesion.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

public record VentaRequestDTO(
    @NotBlank(message = "El número de venta no puede estar vacío")
    String numeroVenta,
    
    @NotEmpty(message = "Debe tener al menos un detalle")
    @Valid
    List<DetalleVentaRequestDTO> detalles
) {}