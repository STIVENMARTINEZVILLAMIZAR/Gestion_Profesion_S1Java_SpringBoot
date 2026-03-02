package com.s1.gestion_profesion.service;

import com.s1.gestion_profesion.dto.request.ProfesionRequestDTO;
import com.s1.gestion_profesion.dto.response.ProfesionResponseDTO;
import java.util.List;

public interface ProfesionService {
    ProfesionResponseDTO guardarProfesion(ProfesionRequestDTO dto);
    ProfesionResponseDTO actualizarProfesion(ProfesionRequestDTO dto, Long id);
    ProfesionResponseDTO buscarPorId(Long id);
    List<ProfesionResponseDTO> listarProfesiones();
    List<ProfesionResponseDTO> buscarPorNombre(String nombre);
    void eliminarProfesion(Long id);
}