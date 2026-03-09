package com.s1.gestion_profesion.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AuthRequest", description = "Credenciales para obtener un token JWT")
public record AuthRequest(
        @Schema(description = "Nombre de usuario", example = "admin")
        String username,
        @Schema(description = "Contraseña", example = "admin123")
        String password
) {
}
