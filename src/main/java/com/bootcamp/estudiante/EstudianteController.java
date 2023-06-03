package com.bootcamp.estudiante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EstudianteController {

    private EstudianteService estudianteService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping("/estudiantes")
    public List<Estudiante> getEstudiantes() {
        return estudianteService.getEstudiantes();
    }

    @PostMapping("/crear-estudiante")
    public void createEstudiante(@RequestBody Estudiante e) {
        System.out.println("controller create estudiante entered");
        estudianteService.createEstudiante(e);
        System.out.println("controller create estudiante exited");
    }


    @DeleteMapping("/borrar-estudiante/{id}")
    public void deleteEstudiante(@PathVariable("id") Long estudianteId) {
        estudianteService.deleteEstudiante(estudianteId);
    }

    @PutMapping("actualizar-estudiante/{id}")
    public void updateEstudiante(@PathVariable("id") Long estudianteId, @RequestBody Estudiante estudiante ) {
        estudianteService.updateEstudiante(estudianteId, estudiante);
    }
}
