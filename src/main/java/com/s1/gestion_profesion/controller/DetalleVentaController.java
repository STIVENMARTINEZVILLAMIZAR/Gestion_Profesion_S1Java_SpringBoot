package com.s1.gestion_profesion.controller;

import com.s1.gestion_profesion.dto.response.DetalleVentaResponseDTO;
import com.s1.gestion_profesion.service.DetalleVentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/detalles")
@RequiredArgsConstructor
@Tag(name = "Detalles de venta", description = "Consulta de detalles individuales o por venta")
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener detalle por ID", description = "Retorna un detalle de venta específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalle encontrado",
                    content = @Content(schema = @Schema(implementation = DetalleVentaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Detalle no encontrado", content = @Content)
    })
    public ResponseEntity<DetalleVentaResponseDTO> obtenerPorId(
            @Parameter(description = "ID del detalle", example = "1", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(detalleVentaService.buscarPorId(id));
    }

    @GetMapping("/venta/{ventaId}")
    @Operation(summary = "Listar detalles de una venta", description = "Lista todos los detalles asociados a una venta")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de detalles",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = DetalleVentaResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Venta sin detalles o inexistente", content = @Content)
    })
    public ResponseEntity<List<DetalleVentaResponseDTO>> listarPorVenta(
            @Parameter(description = "ID de la venta", example = "1", required = true)
            @PathVariable Long ventaId) {
        return ResponseEntity.ok(detalleVentaService.listarPorVenta(ventaId));
    }
}
