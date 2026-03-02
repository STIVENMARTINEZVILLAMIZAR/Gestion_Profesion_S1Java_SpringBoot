package com.s1.gestion_profesion.dto.response;

import java.math.BigDecimal;

public record DetalleVentaResponseDTO(
    Long id,
    ProductoResponseDTO producto,
    Integer cantidad,
    BigDecimal precioUnitario,
    BigDecimal subtotal
) {}