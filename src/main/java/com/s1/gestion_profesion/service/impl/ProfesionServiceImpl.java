package com.s1.gestion_profesion.service.impl;

import com.s1.gestion_profesion.dto.request.ProfesionRequestDTO;
import com.s1.gestion_profesion.dto.response.ProfesionResponseDTO;
import com.s1.gestion_profesion.mapper.ProfesionMapper;
import com.s1.gestion_profesion.model.Profesion;
import com.s1.gestion_profesion.repository.ProfesionRepository;
import com.s1.gestion_profesion.service.ProfesionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfesionServiceImpl implements ProfesionService {
    
    private final ProfesionRepository profesionRepository;
    private final ProfesionMapper profesionMapper;
    
    @Override
    public ProfesionResponseDTO guardarProfesion(ProfesionRequestDTO dto) {
        if (profesionRepository.existsByNombreIgnoreCase(dto.nombre())) {
            throw new RuntimeException("Ya existe una profesión con ese nombre");
        }
        
        Profesion profesion = new Profesion();
        profesion.setNombre(dto.nombre());
        profesion.setDescripcion(dto.descripcion());
        
        Profesion profesionGuardada = profesionRepository.save(profesion);
        return profesionMapper.entidadADTO(profesionGuardada);
    }
    
    @Override
    public ProfesionResponseDTO actualizarProfesion(ProfesionRequestDTO dto, Long id) {
        Profesion profesion = profesionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Profesión no encontrada"));
        
        profesion.setNombre(dto.nombre());
        profesion.setDescripcion(dto.descripcion());
        
        Profesion profesionActualizada = profesionRepository.save(profesion);
        return profesionMapper.entidadADTO(profesionActualizada);
    }
    
    @Override
    public ProfesionResponseDTO buscarPorId(Long id) {
        Profesion profesion = profesionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Profesión no encontrada"));
        return profesionMapper.entidadADTO(profesion);
    }
    
    @Override
    public List<ProfesionResponseDTO> listarProfesiones() {
        return profesionRepository.findAll().stream()
            .map(profesionMapper::entidadADTO)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<ProfesionResponseDTO> buscarPorNombre(String nombre) {
        return profesionRepository.findByNombreIgnoreCase(nombre)
            .map(p -> List.of(profesionMapper.entidadADTO(p)))
            .orElse(List.of());
    }
    
    @Override
    public void eliminarProfesion(Long id) {
        Profesion profesion = profesionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Profesión no encontrada"));
        profesionRepository.delete(profesion);
    }
}