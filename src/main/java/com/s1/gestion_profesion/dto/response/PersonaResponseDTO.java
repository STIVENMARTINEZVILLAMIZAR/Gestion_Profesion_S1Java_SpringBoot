package com.s1.gestion_profesion.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(name = "PersonaResponseDTO", description = "Respuesta con informacion de persona")
public record PersonaResponseDTO(
        @Schema(description = "ID de la persona", example = "1")
        Long id, String documento, String nombre, String apellido,
        @Schema(description = "Edad", example = "28")
        Integer edad, BigDecimal salario, ProfesionResponseDTO profesion
) {

}
