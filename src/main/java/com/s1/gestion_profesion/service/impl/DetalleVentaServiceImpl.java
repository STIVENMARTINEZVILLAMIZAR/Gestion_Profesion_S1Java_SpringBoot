package com.s1.gestion_profesion.service.impl;

import com.s1.gestion_profesion.dto.response.DetalleVentaResponseDTO;
import com.s1.gestion_profesion.mapper.DetalleVentaMapper;
import com.s1.gestion_profesion.model.DetalleVenta;
import com.s1.gestion_profesion.repository.DetalleVentaRepository;
import com.s1.gestion_profesion.service.DetalleVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetalleVentaServiceImpl implements DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;
    private final DetalleVentaMapper detalleVentaMapper;

    @Override
    public DetalleVentaResponseDTO buscarPorId(Long id) {
        DetalleVenta detalle = detalleVentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado"));
        return detalleVentaMapper.entidadADTO(detalle);
    }

    @Override
    public List<DetalleVentaResponseDTO> listarPorVenta(Long ventaId) {
        return detalleVentaRepository.findByVentaId(ventaId).stream()
                .map(detalleVentaMapper::entidadADTO)
                .collect(Collectors.toList());
    }
}
