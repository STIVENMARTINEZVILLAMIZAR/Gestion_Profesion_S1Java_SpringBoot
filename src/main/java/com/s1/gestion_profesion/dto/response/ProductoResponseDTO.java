package com.s1.gestion_profesion.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ProductoResponseDTO", description = "Respuesta con informacion de producto")
public record ProductoResponseDTO(
        @Schema(description = "ID del producto", example = "1")
        Long id,
        @Schema(description = "Nombre del producto", example = "Mouse Logitech G203")
        String nombre,
        @Schema(description = "Descripcion del producto", example = "Mouse gamer RGB")
        String descripcion,
        @Schema(description = "Precio unitario", example = "129900.00")
        BigDecimal precio,
        @Schema(description = "Stock disponible", example = "25")
        Integer stock,
        @Schema(description = "Fecha de creacion", example = "2026-03-02T09:30:00")
        LocalDateTime createdAt
        ) {

}
