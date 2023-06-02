package com.bootcamp.estudiante;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EstudianteRepository {

    private final List<Estudiante> estudiantes;

    public EstudianteRepository() {
        this.estudiantes = new ArrayList<Estudiante>();
                estudiantes.add(new Estudiante(1L, "Fulano"));
                estudiantes.add(new Estudiante(2L, "Pepe"));
                estudiantes.add(new Estudiante(3L, "Maria"));
                estudiantes.add(new Estudiante(4L, "Ernesto"));

    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void createEstudiante(Estudiante e) {
        System.out.println("repo create estudiante entered");
        estudiantes.add(e);
        System.out.println("repo create estudiante exited");
    }

    public void deleteEstudiante(Long estudianteId) {
//        for (int i = 0; i < estudiantes.size(); i++) {
//            Estudiante estudiante = estudiantes.get(i);
//            if(estudiante.getId().equals(estudianteId)) {
//                estudiantes.remove(i);
//            }
//        }

        estudiantes.removeIf(es -> es.getId().equals(estudianteId));
    }
}
