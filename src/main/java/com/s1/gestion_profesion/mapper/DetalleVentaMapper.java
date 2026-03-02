package com.s1.gestion_profesion.mapper;

import com.s1.gestion_profesion.dto.response.DetalleVentaResponseDTO;
import com.s1.gestion_profesion.model.DetalleVenta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DetalleVentaMapper {

    private final ProductoMapper productoMapper;

    public DetalleVentaResponseDTO entidadADTO(DetalleVenta detalle) {
        if (detalle == null) return null;
        return new DetalleVentaResponseDTO(
                detalle.getId(),
                productoMapper.entidadADTO(detalle.getProducto()),
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                detalle.getSubtotal()
        );
    }
}
