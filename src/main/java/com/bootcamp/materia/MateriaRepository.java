package com.bootcamp.materia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {

    boolean existsByNombre(String nombre);

    boolean existsByNombreAndIdIsNot(String nombre, Long id);
}
