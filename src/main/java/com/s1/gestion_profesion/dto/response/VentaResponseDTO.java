package com.s1.gestion_profesion.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record VentaResponseDTO(
    Long id,
    String numeroVenta,
    LocalDateTime fechaVenta,
    BigDecimal total,
    String estado,
    LocalDateTime createdAt,
    List<DetalleVentaResponseDTO> detalles
) {}