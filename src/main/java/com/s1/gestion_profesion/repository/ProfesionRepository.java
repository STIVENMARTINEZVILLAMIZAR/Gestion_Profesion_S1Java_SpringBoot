package com.s1.gestion_profesion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.s1.gestion_profesion.model.Profesion;

@Repository
public interface ProfesionRepository extends JpaRepository<Profesion, Long> {
    Optional<Profesion> findByNombreIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCase(String nombre);
}