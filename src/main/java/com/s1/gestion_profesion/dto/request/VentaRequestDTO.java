package com.s1.gestion_profesion.dto.request;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

@Schema(name = "VentaRequestDTO", description = "Datos requeridos para registrar una venta")
public record VentaRequestDTO(
    @Schema(description = "Numero unico de la venta", example = "VTA-2026-0001")
    @NotBlank(message = "El número de venta no puede estar vacío")
    String numeroVenta,
    
    @ArraySchema(schema = @Schema(implementation = DetalleVentaRequestDTO.class),
            arraySchema = @Schema(description = "Listado de items vendidos"))
    @NotEmpty(message = "Debe tener al menos un detalle")
    @Valid
    List<DetalleVentaRequestDTO> detalles
) {}
