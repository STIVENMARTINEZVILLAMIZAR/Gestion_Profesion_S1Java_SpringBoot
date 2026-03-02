package com.s1.gestion_profesion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.s1.gestion_profesion.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    List<Persona> findByNombreIgnoreCase(String nombre);
    List<Persona> findByApellidoIgnoreCase(String apellido);
}