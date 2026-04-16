package sv.edu.udb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.repository.domain.Materia;
import sv.edu.udb.service.MateriaService;

import java.util.List;

@RestController
@RequestMapping("/api/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public List<Materia> getAll() {
        return materiaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materia> getById(@PathVariable Long id) {
        return materiaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Materia> create(@RequestBody Materia materia) {
        try {
            return ResponseEntity.ok(materiaService.save(materia));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materia> update(@PathVariable Long id, @RequestBody Materia materia) {
        try {
            return ResponseEntity.ok(materiaService.update(id, materia));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            materiaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
