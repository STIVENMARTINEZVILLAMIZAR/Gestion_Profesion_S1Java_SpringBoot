package com.s1.gestion_profesion.service;

import java.util.List;

import com.s1.gestion_profesion.dto.request.VentaRequestDTO;
import com.s1.gestion_profesion.dto.response.VentaResponseDTO;

public interface VentaService {

    VentaResponseDTO guardarVenta(VentaRequestDTO dto);

    VentaResponseDTO buscarPorId(Long id);

    List<VentaResponseDTO> listarVentas();

    void eliminarVenta(Long id);

    VentaResponseDTO cambiarEstado(Long id, String nuevoEstado);
}
