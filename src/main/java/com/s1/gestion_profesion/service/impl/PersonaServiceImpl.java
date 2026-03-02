package com.s1.gestion_profesion.service.impl;

import com.s1.gestion_profesion.dto.request.PersonaRequestDTO;
import com.s1.gestion_profesion.dto.response.PersonaResponseDTO;
import com.s1.gestion_profesion.mapper.PersonaMapper;
import com.s1.gestion_profesion.model.Persona;
import com.s1.gestion_profesion.repository.PersonaRepository;
import com.s1.gestion_profesion.service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {
    
    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;
    
    @Override
    public PersonaResponseDTO guardarPersona(PersonaRequestDTO dto) {
        Persona persona = personaMapper.dtoAEntidad(dto);
        Persona personaGuardada = personaRepository.save(persona);
        return personaMapper.entidadADTO(personaGuardada);
    }
    
    @Override
    public PersonaResponseDTO actualizarPersona(PersonaRequestDTO dto, Long id) {
        Persona persona = personaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        
        persona.setNombre(dto.nombre());
        persona.setApellido(dto.apellido());
        
        Persona personaActualizada = personaRepository.save(persona);
        return personaMapper.entidadADTO(personaActualizada);
    }
    
    @Override
    public PersonaResponseDTO buscarPorId(Long id) {
        Persona persona = personaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        return personaMapper.entidadADTO(persona);
    }
    
    @Override
    public List<PersonaResponseDTO> listarPersonas() {
        return personaRepository.findAll().stream()
            .map(personaMapper::entidadADTO)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<PersonaResponseDTO> buscarPorNombre(String nombre) {
        return personaRepository.findByNombreIgnoreCase(nombre).stream()
            .map(personaMapper::entidadADTO)
            .collect(Collectors.toList());
    }
    
    @Override
    public void eliminarPersona(Long id) {
        Persona persona = personaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        personaRepository.delete(persona);
    }
}