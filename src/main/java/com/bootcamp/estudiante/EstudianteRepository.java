package com.bootcamp.estudiante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {

    boolean existsByEmailAndIdIsNot(String email, Long id);

    boolean existsByEmail(String email);
}
