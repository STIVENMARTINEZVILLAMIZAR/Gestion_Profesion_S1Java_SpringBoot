package com.s1.gestion_profesion.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(name = "PersonaRequestDTO", description = "Datos para crear o actualizar una persona")
public record PersonaRequestDTO(
        @Schema(description = "Documento de identidad", example = "1000123456")
        String documento,
        @Schema(description = "Nombre de la persona", example = "Carlos")
        String nombre,
        @Schema(description = "Apellido de la persona", example = "Perez")
        String apellido,
        @Schema(description = "Edad de la persona", example = "28")
        @NotNull(message = "Error, la edad no puede ser nulo")
        Integer edad,
        @Schema(description = "Salario actual", example = "3200000.00")
        BigDecimal salario,
        @Schema(description = "ID de la profesion asociada", example = "1")
        Long profesionId
) {
}
