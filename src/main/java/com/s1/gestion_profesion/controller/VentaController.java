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

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {
    
    private final VentaService ventaService;
    
    @PostMapping
    public ResponseEntity<VentaResponseDTO> crearVenta(@Valid @RequestBody VentaRequestDTO dto) {
        VentaResponseDTO response = ventaService.guardarVenta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> obtenerVenta(@PathVariable Long id) {
        VentaResponseDTO response = ventaService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> listarVentas() {
        List<VentaResponseDTO> response = ventaService.listarVentas();
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{id}/estado")
    public ResponseEntity<VentaResponseDTO> cambiarEstado(
            @PathVariable Long id,
            @RequestParam String estado) {
        VentaResponseDTO response = ventaService.cambiarEstado(id, estado);
        return ResponseEntity.ok(response);
    }
}
