package com.s1.gestion_profesion.mapper;

import com.s1.gestion_profesion.dto.response.ProfesionResponseDTO;
import com.s1.gestion_profesion.model.Profesion;
import org.springframework.stereotype.Component;

@Component
public class ProfesionMapper {
    
    public ProfesionResponseDTO entidadADTO(Profesion profesion) {
        return new ProfesionResponseDTO(
            profesion.getId(),
            profesion.getNombre(),
            profesion.getDescripcion(),
            profesion.getCreatedAt()
        );
    }
}