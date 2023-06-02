package com.bootcamp.estudiante;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstudianteRepository {

    public List<Estudiante> getEstudiantes() {
        return List.of(
                new Estudiante(1L, "Fulano"),
                new Estudiante(2L, "Pepe"),
                new Estudiante(3L, "Maria"),
                new Estudiante(4L, "Ernesto")
        );
    }
}
