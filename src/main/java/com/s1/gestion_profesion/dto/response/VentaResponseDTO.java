package com.s1.gestion_profesion.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "VentaResponseDTO", description = "Respuesta con cabecera y detalles de la venta")
public record VentaResponseDTO(
    @Schema(description = "ID de la venta", example = "1")
    Long id,
    @Schema(description = "Numero unico de la venta", example = "VTA-2026-0001")
    String numeroVenta,
    @Schema(description = "Fecha y hora de la venta", example = "2026-03-02T09:35:00")
    LocalDateTime fechaVenta,
    @Schema(description = "Valor total de la venta", example = "519600.00")
    BigDecimal total,
    @Schema(description = "Estado actual de la venta", example = "PENDIENTE",
            allowableValues = {"PENDIENTE", "COMPLETADA", "CANCELADA"})
    String estado,
    @Schema(description = "Fecha de creacion del registro", example = "2026-03-02T09:35:00")
    LocalDateTime createdAt,
    @ArraySchema(schema = @Schema(implementation = DetalleVentaResponseDTO.class),
            arraySchema = @Schema(description = "Items asociados a la venta"))
    List<DetalleVentaResponseDTO> detalles
) {}
