package com.s1.gestion_profesion.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ProfesionResponseDTO", description = "Respuesta con informacion de profesion")
public record ProfesionResponseDTO(
        @Schema(description = "ID de la profesion", example = "1")
        Long id, String nombre, String descripcion
) {
}
