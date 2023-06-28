package com.bootcamp.libro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/libros")
public class LibroController {

    private final LibroService libroService;

    @Autowired
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }


    @GetMapping
    public Page<Libro> getLibros(@PageableDefault(size = 3, page = 0) Pageable pageable) {
        return libroService.findAllLibros(pageable);
    }

    @GetMapping("{libroId}")
    public Libro getLibro(@PathVariable Long libroId) {
        return libroService.getLibro(libroId);
    }

    @PostMapping
    public ResponseEntity<Long> createLibro(@RequestBody Libro Libro) {
        Long idLibro = libroService.createLibro(Libro);
        return new ResponseEntity<>(idLibro, HttpStatus.CREATED);
    }

    @PutMapping("{libroId}")
    public Libro updateLibro(@PathVariable Long libroId, @RequestBody Libro Libro ) {
        return libroService.updateLibro(libroId, Libro);
    }

    @DeleteMapping("{libroId}")
    public ResponseEntity<?> deleteLibro(@PathVariable Long libroId) {
        libroService.deleteLibro(libroId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
