package com.s1.gestion_profesion.service;

import java.util.List;

import com.s1.gestion_profesion.dto.request.ProductoRequestDTO;
import com.s1.gestion_profesion.dto.response.ProductoResponseDTO;

public interface ProductoService {
    ProductoResponseDTO guardarProducto(ProductoRequestDTO dto);
    ProductoResponseDTO actualizarProducto(ProductoRequestDTO dto, Long id);
    ProductoResponseDTO buscarPorId(Long id);
    List<ProductoResponseDTO> listarProductos();
    List<ProductoResponseDTO> buscarPorNombre(String nombre);
    void eliminarProducto(Long id);
}