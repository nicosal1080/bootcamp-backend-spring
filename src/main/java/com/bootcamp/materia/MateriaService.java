package com.bootcamp.materia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
public class MateriaService {

    private final MateriaRepository materiaRepository;

    @Autowired
    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    @Transactional(readOnly = true)
    public Page<Materia> findAllMaterias(Pageable pageable) {
        return materiaRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Materia getMateria(Long materiaId) {
        return materiaRepository.findById(materiaId)
                .orElseThrow(() -> new NoSuchElementException("Materia con id " + materiaId + " no existe"));
    }

    public Long createMateria(Materia materia) {
        // check si una Materia con el mismo nombre ya existe
        boolean nombreExiste = materiaRepository.existsByNombre(materia.getNombre());
        if (nombreExiste) {
            throw new IllegalArgumentException("Materia " + materia.getNombre() + " ya existe");
        }
        return materiaRepository.save(materia).getId();
    }

    public Materia updateMateria(Long materiaId, Materia materiaAActualizar) {
        // check si materia con ese id existe, si no botamos un Error
        Materia materiaExistente = materiaRepository.findById(materiaId)
                .orElseThrow(() -> new NoSuchElementException("Materia con ese id no existe, id: " + materiaId));

        // check si el nombre que se quiere actualizar ya existe
        boolean nombreExiste = materiaRepository.existsByNombreAndIdIsNot(materiaAActualizar.getNombre(), materiaId);
        if (nombreExiste) {
            throw new IllegalArgumentException("Materia " + materiaAActualizar.getNombre() + " ya existe");
        }

        // Actualizar materia
        materiaExistente.setNombre(materiaAActualizar.getNombre());
        materiaExistente.setCreditos(materiaAActualizar.getCreditos());

        return materiaExistente;
    }

    public void deleteMateria(Long materiaId) {
        // check si id existe, si no botamos exception
        boolean materiaExiste = materiaRepository.existsById(materiaId);
        if (!materiaExiste) {
            throw new NoSuchElementException("Materia con id " + materiaId + " no existe");
        }
        materiaRepository.deleteById(materiaId);
    }
}
