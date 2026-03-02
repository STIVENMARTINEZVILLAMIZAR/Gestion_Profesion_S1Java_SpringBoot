package com.s1.gestion_profesion.service.impl;

import com.s1.gestion_profesion.dto.request.DetalleVentaRequestDTO;
import com.s1.gestion_profesion.dto.request.VentaRequestDTO;
import com.s1.gestion_profesion.dto.response.VentaResponseDTO;
import com.s1.gestion_profesion.mapper.VentaMapper;
import com.s1.gestion_profesion.model.DetalleVenta;
import com.s1.gestion_profesion.model.Producto;
import com.s1.gestion_profesion.model.Venta;
import com.s1.gestion_profesion.repository.ProductoRepository;
import com.s1.gestion_profesion.repository.VentaRepository;
import com.s1.gestion_profesion.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {
    
    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final VentaMapper ventaMapper;
    
    @Override
    public VentaResponseDTO guardarVenta(VentaRequestDTO dto) {
        // Validar que no exista venta con ese número
        ventaRepository.findByNumeroVenta(dto.numeroVenta())
            .ifPresent(v -> {
                throw new RuntimeException("Ya existe una venta con ese número");
            });
        
        Venta venta = new Venta();
        venta.setNumeroVenta(dto.numeroVenta());
        venta.setEstado("PENDIENTE");
        
        // Procesar detalles
        BigDecimal totalVenta = BigDecimal.ZERO;
        List<DetalleVenta> detalles = dto.detalles().stream()
            .map(detalleDto -> procesarDetalle(detalleDto, venta))
            .collect(Collectors.toList());
        
        // Calcular total
        totalVenta = detalles.stream()
            .map(DetalleVenta::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        venta.setDetalles(detalles);
        venta.setTotal(totalVenta);
        
        Venta ventaGuardada = ventaRepository.save(venta);
        return ventaMapper.entidadADTO(ventaGuardada);
    }
    
    private DetalleVenta procesarDetalle(DetalleVentaRequestDTO detalleDto, Venta venta) {
        Producto producto = productoRepository.findById(detalleDto.productoId())
            .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + detalleDto.productoId()));
        
        // Validar stock
        if (producto.getStock() < detalleDto.cantidad()) {
            throw new RuntimeException("Stock insuficiente para " + producto.getNombre());
        }
        
        // Reducir stock
        producto.setStock(producto.getStock() - detalleDto.cantidad());
        productoRepository.save(producto);
        
        // Crear detalle
        DetalleVenta detalle = new DetalleVenta();
        detalle.setVenta(venta);
        detalle.setProducto(producto);
        detalle.setCantidad(detalleDto.cantidad());
        detalle.setPrecioUnitario(detalleDto.precioUnitario());
        detalle.setSubtotal(detalleDto.precioUnitario().multiply(BigDecimal.valueOf(detalleDto.cantidad())));
        
        return detalle;
    }
    
    @Override
    public VentaResponseDTO buscarPorId(Long id) {
        Venta venta = ventaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        return ventaMapper.entidadADTO(venta);
    }
    
    @Override
    public List<VentaResponseDTO> listarVentas() {
        return ventaRepository.findAll().stream()
            .map(ventaMapper::entidadADTO)
            .collect(Collectors.toList());
    }
    
    @Override
    public void eliminarVenta(Long id) {
        Venta venta = ventaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        ventaRepository.delete(venta);
    }
    
    @Override
    public VentaResponseDTO cambiarEstado(Long id, String nuevoEstado) {
        Venta venta = ventaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        
        if (!nuevoEstado.matches("PENDIENTE|COMPLETADA|CANCELADA")) {
            throw new RuntimeException("Estado inválido");
        }
        
        venta.setEstado(nuevoEstado);
        Venta ventaActualizada = ventaRepository.save(venta);
        return ventaMapper.entidadADTO(ventaActualizada);
    }
}