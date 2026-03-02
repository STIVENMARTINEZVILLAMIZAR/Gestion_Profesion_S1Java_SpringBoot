package com.s1.gestion_profesion.mapper;

import com.s1.gestion_profesion.dto.request.PersonaRequestDTO;
import com.s1.gestion_profesion.dto.response.PersonaResponseDTO;
import com.s1.gestion_profesion.model.Persona;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {
    
    private final ProfesionMapper profesionMapper;
    
    public PersonaMapper(ProfesionMapper profesionMapper) {
        this.profesionMapper = profesionMapper;
    }
    
    public Persona dtoAEntidad(PersonaRequestDTO dto) {
        Persona persona = new Persona();
        persona.setDocumento(dto.documento());
        persona.setNombre(dto.nombre());
        persona.setApellido(dto.apellido());
        persona.setEdad(dto.edad());
        persona.setSalario(dto.salario());
        return persona;
    }
    
    public PersonaResponseDTO entidadADTO(Persona persona) {
        return new PersonaResponseDTO(
            persona.getId(),
            persona.getDocumento(),
            persona.getNombre(),
            persona.getApellido(),
            persona.getEdad(),
            persona.getSalario(),
            profesionMapper.entidadADTO(persona.getProfesion())
        );
    }
}
