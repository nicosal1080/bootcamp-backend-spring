package com.bootcamp.estudiante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    private EstudianteRepositoryMentiras estudianteRepositoryMentiras;
    private EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteService(EstudianteRepositoryMentiras estudianteRepositoryMentiras, EstudianteRepository estudianteRepository) {
        this.estudianteRepositoryMentiras = estudianteRepositoryMentiras;
        this.estudianteRepository = estudianteRepository;
    }

    public List<Estudiante> getAllEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        // logica de negocio

        return estudiantes;
    }

    public void createEstudiante(Estudiante e) {
        // logica ...

        estudianteRepository.save(e);
    }

    public void deleteEstudiante(Long estudianteId) {
        // check si id existe, si no imprimimos Warining
        boolean existe = getAllEstudiantes().stream().anyMatch(e -> e.getId().equals(estudianteId));

        if(!existe) {
            System.out.println("WARNING: el estudiante con ese id no existe");
            return;
        }

        estudianteRepository.deleteById(estudianteId);
    }

    public void updateEstudiante(Long id, Estudiante estudianteAActualizar) {
        // check si id existe, si no imprimimos Warining

        boolean existe = getAllEstudiantes().stream().anyMatch(e -> e.getId().equals(id));

        if(!existe) {
            System.out.println("WARNING: el estudiante con ese id no existe");
            return;
        }

        estudianteRepositoryMentiras.updateEstudiante(id, estudianteAActualizar);
    }

    public Estudiante getEstudiante(Long id) {
        return estudianteRepositoryMentiras.getEstudiante(id);
    }
}
