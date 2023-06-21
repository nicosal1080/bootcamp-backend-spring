package com.bootcamp.materia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/materias")
public class MateriaController {

    private final MateriaService materiaService;

    @Autowired
    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping
    public Page<Materia> getMaterias(@PageableDefault(size = 3, page = 0) Pageable pageable) {
        return materiaService.findAllMaterias(pageable);
    }

    @GetMapping("{materiaId}")
    public Materia getMateria(@PathVariable Long materiaId) {
        return materiaService.getMateria(materiaId);
    }

    @PostMapping
    public Long createMateria(@RequestBody Materia materia) {
        return materiaService.createMateria(materia);
    }

    @PutMapping("{materiaId}")
    public Materia updateMateria(@PathVariable Long materiaId, @RequestBody Materia materia ) {
        return materiaService.updateMateria(materiaId, materia);
    }

    @DeleteMapping("{materiaId}")
    public void deleteMateria(@PathVariable Long materiaId) {
        materiaService.deleteMateria(materiaId);
    }
}
