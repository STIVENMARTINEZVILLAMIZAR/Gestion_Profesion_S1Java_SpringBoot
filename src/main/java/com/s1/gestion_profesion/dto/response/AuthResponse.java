package com.s1.gestion_profesion.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AuthResponse", description = "Token de acceso JWT")
public record AuthResponse(
        @Schema(description = "Token JWT Bearer", example = "eyJhbGciOiJIUzI1NiJ9...")
        String token
) {
}
