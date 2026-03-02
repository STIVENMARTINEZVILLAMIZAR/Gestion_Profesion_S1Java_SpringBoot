package com.s1.gestion_profesion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.s1.gestion_profesion.dto.request.ProductoRequestDTO;
import com.s1.gestion_profesion.dto.response.ProductoResponseDTO;
import com.s1.gestion_profesion.service.ProductoService;

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
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Operaciones CRUD para la gestion de productos")
public class ProductoController {
    
    private final ProductoService productoService;
    
    @PostMapping
    @Operation(
            summary = "Crear producto",
            description = "Registra un producto nuevo en el inventario"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado",
                    content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos", content = @Content),
            @ApiResponse(responseCode = "409", description = "Producto duplicado", content = @Content)
    })
    public ResponseEntity<ProductoResponseDTO> crearProducto(@Valid @RequestBody ProductoRequestDTO dto) {
        ProductoResponseDTO response = productoService.guardarProducto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar producto",
            description = "Actualiza la informacion de un producto existente por ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado",
                    content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
    })
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @Parameter(description = "ID del producto", example = "1", required = true)
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequestDTO dto) {
        ProductoResponseDTO response = productoService.actualizarProducto(dto, id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener producto por ID",
            description = "Consulta un producto especifico por su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado",
                    content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
    })
    public ResponseEntity<ProductoResponseDTO> obtenerProducto(
            @Parameter(description = "ID del producto", example = "1", required = true)
            @PathVariable Long id) {
        ProductoResponseDTO response = productoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @Operation(
            summary = "Listar productos",
            description = "Retorna todos los productos registrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de productos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductoResponseDTO.class))))
    })
    public ResponseEntity<List<ProductoResponseDTO>> listarProductos() {
        List<ProductoResponseDTO> response = productoService.listarProductos();
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/buscar/{nombre}")
    @Operation(
            summary = "Buscar productos por nombre",
            description = "Busca productos por coincidencia de nombre sin distincion de mayusculas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado de la busqueda",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductoResponseDTO.class))))
    })
    public ResponseEntity<List<ProductoResponseDTO>> buscarPorNombre(
            @Parameter(description = "Texto parcial o completo del nombre", example = "laptop", required = true)
            @PathVariable String nombre) {
        List<ProductoResponseDTO> response = productoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar producto",
            description = "Elimina un producto existente por ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
    })
    public ResponseEntity<Void> eliminarProducto(
            @Parameter(description = "ID del producto", example = "1", required = true)
            @PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
