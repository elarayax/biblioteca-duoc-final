package biblioteca.salas.duoc.biblioteca.salas.duoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biblioteca.salas.duoc.biblioteca.salas.duoc.service.EstudianteService;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Estudiante;
import java.util.List;

@RestController
@RequestMapping("/api/v1/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<Estudiante>> getAllEstudiantes() {
        List<Estudiante> Estudiantes = estudianteService.findAll();
        if (Estudiantes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Estudiantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getEstudianteById(@PathVariable Long id) {
        Estudiante Estudiante = estudianteService.findById(id);
        if (Estudiante == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Estudiante);
    }

    @PostMapping
    public ResponseEntity<Estudiante> createEstudiante(@RequestBody Estudiante estudiante) {
        Estudiante createdEstudiante = estudianteService.save(estudiante);
        return ResponseEntity.status(201).body(createdEstudiante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> updateEstudiante(@RequestBody Estudiante estudiante) {
        Estudiante updatedEstudiante = estudianteService.save(estudiante);
        if (updatedEstudiante == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEstudiante);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Estudiante> patchEstudiante(@PathVariable Long id,@RequestBody Estudiante estudiante) {
        Estudiante patchedEstudiante = estudianteService.patchEstudiante(id, estudiante);
        if (patchedEstudiante == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedEstudiante);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable Long id) {
        if (estudianteService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        estudianteService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }
    
}
