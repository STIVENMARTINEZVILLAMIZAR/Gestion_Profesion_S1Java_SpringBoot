package com.s1.gestion_profesion.service;

import com.s1.gestion_profesion.dto.response.DetalleVentaResponseDTO;

import java.util.List;

public interface DetalleVentaService {
    DetalleVentaResponseDTO buscarPorId(Long id);
    List<DetalleVentaResponseDTO> listarPorVenta(Long ventaId);
}
