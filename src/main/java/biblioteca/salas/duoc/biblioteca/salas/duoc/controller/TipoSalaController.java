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

import biblioteca.salas.duoc.biblioteca.salas.duoc.service.TipoSalaService;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.TipoSala;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tipoSalas")
public class TipoSalaController {

    @Autowired
    private TipoSalaService tipoSalaService;

    @GetMapping
    public ResponseEntity<List<TipoSala>> getAllTipoSalas() {
        List<TipoSala> tipoSalas = tipoSalaService.findAll();
        if (tipoSalas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipoSalas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoSala> getTipoSalaById(@PathVariable Long id) {
        TipoSala tipoSala = tipoSalaService.findById(id);
        if (tipoSala == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tipoSala);
    }

    @PostMapping
    public ResponseEntity<TipoSala> createTipoSala(@RequestBody TipoSala tipoSala) {
        TipoSala createdTipoSala = tipoSalaService.save(tipoSala);
        return ResponseEntity.status(201).body(createdTipoSala);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoSala> updateTipoSala(@RequestBody TipoSala tipoSala) {
        TipoSala updatedTipoSala = tipoSalaService.save(tipoSala);
        if (updatedTipoSala == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTipoSala);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TipoSala> patchTipoSala(@PathVariable Long id,@RequestBody TipoSala tipoSala) {
        TipoSala patchedTipoSala = tipoSalaService.patchTipoSala(id, tipoSala);
        if (patchedTipoSala == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedTipoSala);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoSala(@PathVariable Long id) {
        if (tipoSalaService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        tipoSalaService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }
    
}
