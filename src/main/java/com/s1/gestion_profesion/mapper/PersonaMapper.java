package com.s1.gestion_profesion.mapper;

import com.s1.gestion_profesion.dto.request.PersonaRequestDTO;
import com.s1.gestion_profesion.dto.response.PersonaResponseDTO;
import com.s1.gestion_profesion.model.Persona;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {
    
    // Convierte DTO a Entidad (para guardar en BD)
    public Persona dtoAEntidad(PersonaRequestDTO dto) {
        Persona persona = new Persona();
        persona.setNombre(dto.nombre());
        persona.setApellido(dto.apellido());
        return persona;
    }
    
    // Convierte Entidad a DTO (para devolver al cliente)
    public PersonaResponseDTO entidadADTO(Persona persona) {
        return new PersonaResponseDTO(
            persona.getId(),
            persona.getNombre(),
            persona.getApellido(),
            persona.getCreatedAt()
        );
    }
}