package com.s1.gestion_profesion.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.s1.gestion_profesion.dto.response.DetalleVentaResponseDTO;
import com.s1.gestion_profesion.dto.response.VentaResponseDTO;
import com.s1.gestion_profesion.model.Venta;

@Component
public class VentaMapper {
    
    private final ProductoMapper productoMapper;
    
    public VentaMapper(ProductoMapper productoMapper) {
        this.productoMapper = productoMapper;
    }
    
    public VentaResponseDTO entidadADTO(Venta venta) {
        var detalles = venta.getDetalles().stream()
            .map(detalle -> new DetalleVentaResponseDTO(
                detalle.getId(),
                productoMapper.entidadADTO(detalle.getProducto()),
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                detalle.getSubtotal()
            ))
            .collect(Collectors.toList());
        
        return new VentaResponseDTO(
            venta.getId(),
            venta.getNumeroVenta(),
            venta.getFechaVenta(),
            venta.getTotal(),
            venta.getEstado(),
            venta.getCreatedAt(),
            detalles
        );
    }
}
