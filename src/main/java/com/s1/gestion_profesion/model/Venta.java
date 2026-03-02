package com.s1.gestion_profesion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false, length = 20)
    private String numeroVenta;
    
    @Column(nullable = false)
    private LocalDateTime fechaVenta;
    
    @Column(nullable = false)
    private BigDecimal total;
    
    @Column(length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'PENDIENTE'")
    private String estado;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    
    // Relación 1:N con DetalleVenta
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DetalleVenta> detalles;
    
    @PrePersist
    protected void onCreate() {
        fechaVenta = LocalDateTime.now();
        createdAt = LocalDateTime.now();
        if (estado == null) estado = "PENDIENTE";
    }
}