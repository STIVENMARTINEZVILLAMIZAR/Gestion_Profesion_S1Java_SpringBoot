package com.s1.gestion_profesion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.s1.gestion_profesion.dto.request.VentaRequestDTO;
import com.s1.gestion_profesion.dto.response.VentaResponseDTO;
import com.s1.gestion_profesion.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
@Tag(name = "Ventas", description = "Operaciones para registrar y consultar ventas")
public class VentaController {
    
    private final VentaService ventaService;
    
    @PostMapping
    @Operation(
            summary = "Crear venta",
            description = "Crea una venta nueva y calcula el total a partir de sus detalles"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venta creada",
                    content = @Content(schema = @Schema(implementation = VentaResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content),
            @ApiResponse(responseCode = "409", description = "Numero de venta duplicado", content = @Content)
    })
    public ResponseEntity<VentaResponseDTO> crearVenta(@Valid @RequestBody VentaRequestDTO dto) {
        VentaResponseDTO response = ventaService.guardarVenta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener venta por ID",
            description = "Consulta una venta con sus detalles por identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta encontrada",
                    content = @Content(schema = @Schema(implementation = VentaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada", content = @Content)
    })
    public ResponseEntity<VentaResponseDTO> obtenerVenta(
            @Parameter(description = "ID de la venta", example = "1", required = true)
            @PathVariable Long id) {
        VentaResponseDTO response = ventaService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @Operation(
            summary = "Listar ventas",
            description = "Retorna todas las ventas registradas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de ventas",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = VentaResponseDTO.class))))
    })
    public ResponseEntity<List<VentaResponseDTO>> listarVentas() {
        List<VentaResponseDTO> response = ventaService.listarVentas();
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar venta",
            description = "Elimina una venta existente por ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Venta eliminada", content = @Content),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada", content = @Content)
    })
    public ResponseEntity<Void> eliminarVenta(
            @Parameter(description = "ID de la venta", example = "1", required = true)
            @PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{id}/estado")
    @Operation(
            summary = "Cambiar estado de venta",
            description = "Actualiza el estado de una venta. Estados permitidos: PENDIENTE, COMPLETADA, CANCELADA"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado actualizado",
                    content = @Content(schema = @Schema(implementation = VentaResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Estado invalido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada", content = @Content)
    })
    public ResponseEntity<VentaResponseDTO> cambiarEstado(
            @Parameter(description = "ID de la venta", example = "1", required = true)
            @PathVariable Long id,
            @Parameter(description = "Nuevo estado de la venta", example = "COMPLETADA", required = true,
                    schema = @Schema(allowableValues = {"PENDIENTE", "COMPLETADA", "CANCELADA"}))
            @RequestParam String estado) {
        VentaResponseDTO response = ventaService.cambiarEstado(id, estado);
        return ResponseEntity.ok(response);
    }
}
