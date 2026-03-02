package com.s1.gestion_profesion.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.s1.gestion_profesion.dto.request.ProductoRequestDTO;
import com.s1.gestion_profesion.dto.response.ProductoResponseDTO;
import com.s1.gestion_profesion.mapper.ProductoMapper;
import com.s1.gestion_profesion.model.Producto;
import com.s1.gestion_profesion.repository.ProductoRepository;
import com.s1.gestion_profesion.service.ProductoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    
    @Override
    public ProductoResponseDTO guardarProducto(ProductoRequestDTO dto) {
        // Validar que no exista producto con ese nombre
        productoRepository.findByNombreIgnoreCase(dto.nombre())
            .ifPresent(p -> {
                throw new RuntimeException("Ya existe un producto con ese nombre");
            });
        
        Producto producto = productoMapper.dtoAEntidad(dto);
        Producto productGuardado = productoRepository.save(producto);
        return productoMapper.entidadADTO(productGuardado);
    }
    
    @Override
    public ProductoResponseDTO actualizarProducto(ProductoRequestDTO dto, Long id) {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        producto.setNombre(dto.nombre());
        producto.setDescripcion(dto.descripcion());
        producto.setPrecio(dto.precio());
        producto.setStock(dto.stock());
        
        Producto productoActualizado = productoRepository.save(producto);
        return productoMapper.entidadADTO(productoActualizado);
    }
    
    @Override
    public ProductoResponseDTO buscarPorId(Long id) {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return productoMapper.entidadADTO(producto);
    }
    
    @Override
    public List<ProductoResponseDTO> listarProductos() {
        return productoRepository.findAll().stream()
            .map(productoMapper::entidadADTO)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<ProductoResponseDTO> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre).stream()
            .map(productoMapper::entidadADTO)
            .collect(Collectors.toList());
    }
    
    @Override
    public void eliminarProducto(Long id) {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        productoRepository.delete(producto);
    }
}