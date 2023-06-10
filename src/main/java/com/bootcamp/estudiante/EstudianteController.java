package com.bootcamp.estudiante;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Estudiante> getEstudiantes() {
        return estudianteService.getAllEstudiantes();
    }

    @GetMapping("{id}")
    public Estudiante getEstudiante(@PathVariable Long id) {
        return estudianteService.getEstudiante(id);
    }

    @PostMapping
    public void createEstudiante(@RequestBody Estudiante e) {
        System.out.println("controller create estudiante entered");
        estudianteService.createEstudiante(e);
        System.out.println("controller create estudiante exited");
    }


    @DeleteMapping("{id}")
    public void deleteEstudiante(@PathVariable("id") Long estudianteId) {
        estudianteService.deleteEstudiante(estudianteId);
    }

    @PutMapping("{id}")
    public Estudiante updateEstudiante(@PathVariable("id") Long estudianteId, @RequestBody Estudiante estudiante ) {
        return estudianteService.updateEstudiante(estudianteId, estudiante);
    }
}
