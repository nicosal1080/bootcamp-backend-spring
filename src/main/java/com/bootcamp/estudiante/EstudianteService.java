package com.bootcamp.estudiante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

@Transactional
@Service
public class EstudianteService {

    private EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Transactional(readOnly = true)
    public List<Estudiante> getAllEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();

        return estudiantes;
    }

    @Transactional(readOnly = true)
    public List<Estudiante> getEstudiantesByPrimerNombreOrPrimerApellido(String primerNombre, String primerApellido) {
        List<Estudiante> estudiantes = estudianteRepository.findEstudianteByPrimerNombreOrPrimerApellido(primerNombre, primerApellido);

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
        // check si id existe, si no botamos exception
        boolean estudianteExiste = estudianteRepository.existsById(estudianteId);

        if (!estudianteExiste) {
            throw new NoSuchElementException("Estudiante con id " + estudianteId + " no existe");
        }

        estudianteRepository.deleteById(estudianteId);
    }

    public Estudiante updateEstudiante(Long id, Estudiante estudianteAActualizar) {
        // check si estudiante con ese id existe, si no botamos un Error
        Estudiante estudianteExistente = estudianteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Estudiante con ese id no existe, id: " + id));

        // Actualizar estudiante
        estudianteExistente.setPrimerNombre(estudianteAActualizar.getPrimerNombre());
        estudianteExistente.setSegundoNombre(estudianteAActualizar.getSegundoNombre());
        estudianteExistente.setPrimerApellido(estudianteAActualizar.getPrimerApellido());
        estudianteExistente.setSegundoApellido(estudianteAActualizar.getSegundoApellido());
        estudianteExistente.setFechaNacimiento(estudianteAActualizar.getFechaNacimiento());

        // check si el email es valido
        if(!checkValidezEmail(estudianteAActualizar.getEmail())) {
            throw new IllegalArgumentException("Email " + estudianteAActualizar.getEmail() + " no es valido");
        }

        // check si el email que se quiere actualizar ya existe
        boolean emailExiste = estudianteRepository.existsByEmailAndIdIsNot(estudianteAActualizar.getEmail(), id);
        if (emailExiste) {
            throw new IllegalArgumentException("Email " + estudianteAActualizar.getEmail() + " ya esta registrado");
        }

       // Actualizar email
        estudianteExistente.setEmail(estudianteAActualizar.getEmail());

        return estudianteExistente;
    }

    @Transactional(readOnly = true)
    public Estudiante getEstudiante(Long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Estudiante con id " + id + " no existe"));
    }

    private boolean checkValidezEmail(String email) {
        return Pattern.compile(
                "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE
        ).asPredicate().test(email);
    }
}
