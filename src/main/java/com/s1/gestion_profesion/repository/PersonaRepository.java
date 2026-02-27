package com.s1.gestion_profesion.repository;

import com.s1.gestion_profesion.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    //select p.* from persona p left join profesion pr on p.profesion_id=pr.id
    //where pr.nombre="sistemas";
    List<Persona> findByProfesionNombreAndProfesionDescripcion(String profesion);
    //select dv.* from detalle_venta dv left join producto p on dv.producto_id=p.id
    //left join marca m on p.marca_id=m.marca where m.nombre="samsung";
    List<Persona> findByNombreIgnoreCase(String nombre);
}
