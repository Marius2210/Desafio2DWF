package sv.edu.udb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.repository.domain.Profesor;
import sv.edu.udb.service.ProfesorService;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    public List<Profesor> getAll() {
        return profesorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> getById(@PathVariable Long id){
        return profesorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Profesor create(@RequestBody Profesor profesor){
        return profesorService.save(profesor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> update(@PathVariable Long id, @RequestBody Profesor profesor){
        try{
            return ResponseEntity.ok(profesorService.update(id, profesor));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            profesorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
