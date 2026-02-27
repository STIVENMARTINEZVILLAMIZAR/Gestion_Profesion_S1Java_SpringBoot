package com.s1.gestion_profesion.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PersonaRequestDTO(
        String documento,
        String nombre,
        String apellido,
        @NotNull(message = "Error, la edad no puede ser nulo")
        Integer edad,
        BigDecimal salario,
        Long profesionId
) {
}
