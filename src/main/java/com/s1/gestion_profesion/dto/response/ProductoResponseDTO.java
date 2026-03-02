package com.s1.gestion_profesion.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductoResponseDTO(
    Long id,
    String nombre,
    String descripcion,
    BigDecimal precio,
    Integer stock,
    LocalDateTime createdAt
) {}