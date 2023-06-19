package com.bootcamp.estudiante;

import com.bootcamp.libro.Libro;
import com.bootcamp.materia.Materia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/estudiantes")
public class EstudianteController {

    private EstudianteService estudianteService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
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
    public Page<Estudiante> getEstudiantes(@PageableDefault(size = 3, page = 0) Pageable pageable) {
        // size = tamano de la pagina
        // page = numero de pagina
        // sort = orden sobre alguno de los atributos, podemos agregar direccion "asc", "desc"
        return estudianteService.findAllEstudiantes(pageable);
    }

    @GetMapping("{id}")
    public Estudiante getEstudiante(@PathVariable Long id) {
        return estudianteService.getEstudiante(id);
    }

    @PostMapping
    public void createEstudiante(@RequestBody Estudiante e) {
        estudianteService.createEstudiante(e);
    }


    @DeleteMapping("{id}")
    public void deleteEstudiante(@PathVariable("id") Long estudianteId) {
        estudianteService.deleteEstudiante(estudianteId);
    }

    @PutMapping("{id}")
    public Estudiante updateEstudiante(@PathVariable("id") Long estudianteId, @RequestBody Estudiante estudiante ) {
        return estudianteService.updateEstudiante(estudianteId, estudiante);
    }

    @PostMapping("{estudianteId}/libros")
    public Estudiante agregarLibroAEstudiante(@PathVariable Long estudianteId, @RequestBody Libro libro) {
        return estudianteService.agregarLibroAEstudiante(estudianteId, libro);
    }

    @PostMapping("{estudianteId}/materias")
    public Estudiante agregarMateriaAEstudiante(@PathVariable Long estudianteId, @RequestBody Materia materia) {
        return estudianteService.agregarMateriaAEstudiante(estudianteId, materia);
    }
}
