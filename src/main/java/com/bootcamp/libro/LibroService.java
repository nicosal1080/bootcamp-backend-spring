package com.bootcamp.libro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
public class LibroService {

    private final LibroRepository libroRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Transactional(readOnly = true)
    public Page<Libro> findAllLibros(Pageable pageable) {
        return libroRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Libro getLibro(Long libroId) {
        return libroRepository.findById(libroId)
                .orElseThrow(() -> new NoSuchElementException("Libro con id " + libroId + " no existe"));
    }

    public Long createLibro(Libro libro) {
//        // check si un Libro con el mismo titulo y autor ya existe
//        boolean nombreExiste = libroRepository.existsByTituloAndAutor(libro.getTitulo(), libro.getAutor());
//        if (nombreExiste) {
//            throw new IllegalArgumentException("Libro " + libro.getTitulo() + " con autor " + libro.getAutor() + " ya existe");
//        }
        return libroRepository.save(libro).getId();
    }

    public Libro updateLibro(Long libroId, Libro libroAActualizar) {
        // check si libro con ese id existe, si no botamos un Error
        Libro libroExistente = libroRepository.findById(libroId)
                .orElseThrow(() -> new NoSuchElementException("Libro con ese id no existe, id: " + libroId));

//        // check si el titulo y autor que se quieren actualizar ya existe
//        boolean libroExiste = libroRepository.existsByTituloAndAutorAndIdIsNot(libroAActualizar.getTitulo(),
//                libroAActualizar.getAutor(), libroId);
//        if (libroExiste) {
//            throw new IllegalArgumentException("Libro " + libroAActualizar.getTitulo() + " con autor " + libroAActualizar.getAutor() + " ya existe");
//        }

        // Actualizar libro
        libroExistente.setTitulo(libroAActualizar.getTitulo());
        libroExistente.setAutor(libroAActualizar.getAutor());

        return libroExistente;
    }

    public void deleteLibro(Long libroId) {
        // check si id existe, si no botamos exception
        boolean libroExiste = libroRepository.existsById(libroId);
        if (!libroExiste) {
            throw new NoSuchElementException("Libro con id " + libroId + " no existe");
        }
        libroRepository.deleteById(libroId);
    }
}
