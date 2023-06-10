package com.bootcamp.estudiante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {

    boolean existsByEmailAndIdIsNot(String email, Long id);

    boolean existsByEmail(String email);

    List<Estudiante> findEstudianteByPrimerNombreOrPrimerApellido(String primerNombre, String primerApellido);
}
