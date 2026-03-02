package com.s1.gestion_profesion.mapper;

import com.s1.gestion_profesion.dto.request.ProductoRequestDTO;
import com.s1.gestion_profesion.dto.response.ProductoResponseDTO;
import com.s1.gestion_profesion.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {
    
    public Producto dtoAEntidad(ProductoRequestDTO dto) {
        return new Producto(
            null,
            dto.nombre(),
            dto.descripcion(),
            dto.precio(),
            dto.stock(),
            null
        );
    }
    
    public ProductoResponseDTO entidadADTO(Producto producto) {
        return new ProductoResponseDTO(
            producto.getId(),
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getPrecio(),
            producto.getStock(),
            producto.getCreatedAt()
        );
    }
}
