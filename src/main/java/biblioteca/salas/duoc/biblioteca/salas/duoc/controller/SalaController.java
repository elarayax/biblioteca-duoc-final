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

import biblioteca.salas.duoc.biblioteca.salas.duoc.service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Sala;
import java.util.List;

@RestController
@RequestMapping("/api/v1/salas")
@Tag(name = "Api que administra las salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping
    @Operation(summary = "Esta api llama todas las salas", description = "esta api se encarga de obtener todas las salas que hay")
    public ResponseEntity<List<Sala>> getAllSalas() {
        List<Sala> salas = salaService.findAll();
        if (salas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(salas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Esta api llama una sala por su id", description = "esta api se encarga de obtener una sala por su id")
    public ResponseEntity<Sala> getSalaById(@PathVariable Long id) {
        Sala sala = salaService.findById(id);
        if (sala == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sala);
    }

    @PostMapping
    @Operation(summary = "Esta api crea salas", description = "esta api se encarga de crear una nueva sala")
    public ResponseEntity<Sala> createSala(@RequestBody Sala sala) {
        Sala createdSala = salaService.save(sala);
        return ResponseEntity.status(201).body(createdSala);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Esta api actualiza una sala", description = "esta api se encarga de actualizar una sala existente")
    public ResponseEntity<Sala> updateSala(@RequestBody Sala sala) {
        Sala updatedSala = salaService.save(sala);
        if (updatedSala == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedSala);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Esta api actualiza parcialmente una sala", description = "esta api se encarga de actualizar parcialmente una sala existente")
    public ResponseEntity<Sala> patchSala(@PathVariable Long id,@RequestBody Sala sala) {
        Sala patchedSala = salaService.patchSala(id, sala);
        if (patchedSala == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedSala);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Esta api elimina una sala", description = "esta api se encarga de eliminar una sala existente")
    public ResponseEntity<Void> deleteSala(@PathVariable Long id) {
        if (salaService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        salaService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }
    
}
