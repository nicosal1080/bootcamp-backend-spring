package com.bootcamp.estudiante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

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

        return estudiantes;
    }

    public void createEstudiante(Estudiante e) {
        // check si el email es valido
        if(!checkValidezEmail(e.getEmail())) {
            throw new IllegalArgumentException("Email " + e.getEmail() + " no es valido");
        }

        // check si el email ya existe
        boolean emailExiste = estudianteRepository.existsByEmail(e.getEmail());
        if (emailExiste) {
            throw new IllegalArgumentException("Email " + e.getEmail() + " ya esta registrado");
        }

        estudianteRepository.save(e);
    }

    public void deleteEstudiante(Long estudianteId) {
        // check si id existe, si no imprimimos Warining
        boolean existe = getAllEstudiantes().stream().anyMatch(e -> e.getId().equals(estudianteId));

        if (!existe) {
            System.out.println("WARNING: el estudiante con ese id no existe");
            return;
        }

        estudianteRepository.deleteById(estudianteId);
    }

    public void updateEstudiante(Long id, Estudiante estudianteAActualizar) {
        // check si estudiante con ese id existe, si no botamos un Error
        Estudiante estudianteExistente = estudianteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Estudiante con ese id no existe, id: " + id));

        // check si el email es valido
        if(!checkValidezEmail(estudianteAActualizar.getEmail())) {
            throw new IllegalArgumentException("Email " + estudianteAActualizar.getEmail() + " no es valido");
        }

        // check si el email que se quiere actualizar ya existe
        boolean emailExiste = estudianteRepository.existsByEmailAndIdIsNot(estudianteAActualizar.getEmail(), estudianteExistente.getId());
        if (emailExiste) {
            throw new IllegalArgumentException("Email " + estudianteAActualizar.getEmail() + " ya esta registrado");
        }

        // Actualizar estudiante
        estudianteExistente.setPrimerNombre(estudianteAActualizar.getPrimerNombre());
        estudianteExistente.setSegundoNombre(estudianteAActualizar.getSegundoNombre());
        estudianteExistente.setPrimerApellido(estudianteAActualizar.getPrimerApellido());
        estudianteExistente.setSegundoApellido(estudianteAActualizar.getSegundoApellido());
        estudianteExistente.setFechaNacimiento(estudianteAActualizar.getFechaNacimiento());
        estudianteExistente.setEmail(estudianteAActualizar.getEmail());

        estudianteRepository.save(estudianteExistente);
    }

    public Estudiante getEstudiante(Long id) {
        return estudianteRepositoryMentiras.getEstudiante(id);
    }

    private boolean checkValidezEmail(String email) {
        return Pattern.compile(
                "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE
        ).asPredicate().test(email);
    }
}
