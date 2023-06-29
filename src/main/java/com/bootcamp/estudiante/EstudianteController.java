package com.bootcamp.estudiante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/estudiantes")
@PreAuthorize("hasAnyRole('COOR', 'ADMIN')")
public class EstudianteController {

    private EstudianteService estudianteService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('BIBL','COOR', 'ADMIN')")
    public List<Estudiante> getEstudiantes(
            @RequestParam(value = "primerNombre", required = false) String primerNombre,
            @RequestParam(value = "primerApellido", required = false) String primerApellido
    ) {
        if(primerNombre != null || primerApellido != null){
            return estudianteService.getEstudiantesByPrimerNombreOrPrimerApellido(primerNombre, primerApellido);
        }

        return estudianteService.getAllEstudiantes();
    }

    // este metodo podria remplazar el que nos retribuye la lista de estudiantes
    @GetMapping("/paged")
    @PreAuthorize("hasAnyRole('BIBL','COOR', 'ADMIN')")
    public Page<Estudiante> getEstudiantes(@PageableDefault(size = 3, page = 0) Pageable pageable) {
        // size = tamano de la pagina
        // page = numero de pagina
        // sort = orden sobre alguno de los atributos, podemos agregar direccion "asc", "desc"
        return estudianteService.findAllEstudiantes(pageable);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('BIBL','COOR', 'ADMIN')")
    public Estudiante getEstudiante(@PathVariable Long id) {
        return estudianteService.getEstudiante(id);
    }

    @PostMapping
    public ResponseEntity<Long> createEstudiante(@RequestBody Estudiante e) {
        Long idEstudiante = estudianteService.createEstudiante(e);
        return new ResponseEntity<>(idEstudiante, HttpStatus.CREATED);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEstudiante(@PathVariable("id") Long estudianteId) {
        estudianteService.deleteEstudiante(estudianteId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public Estudiante updateEstudiante(@PathVariable("id") Long estudianteId, @RequestBody Estudiante estudiante ) {
        return estudianteService.updateEstudiante(estudianteId, estudiante);
    }

    @PutMapping("{estudianteId}/libros/{libroId}")
    @PreAuthorize("hasAnyRole('BIBL', 'ADMIN')")
    public Estudiante agregarLibroAEstudiante(@PathVariable Long estudianteId, @PathVariable Long libroId) {
        return estudianteService.agregarLibroAEstudiante(estudianteId, libroId);
    }

    @PutMapping("{estudianteId}/materias/{materiaId}")
    public Estudiante agregarMateriaAEstudiante(@PathVariable Long estudianteId, @PathVariable Long materiaId) {
        return estudianteService.agregarMateriaAEstudiante(estudianteId, materiaId);
    }

    @PutMapping("{estudianteId}/cuentas/{cuentaId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Estudiante agregarCuentaAEstudiante(@PathVariable Long estudianteId, @PathVariable Long cuentaId) {
        return estudianteService.agregarCuentaAEstudiante(estudianteId, cuentaId);
    }
}
