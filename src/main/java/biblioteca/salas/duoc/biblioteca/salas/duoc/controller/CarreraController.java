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

import biblioteca.salas.duoc.biblioteca.salas.duoc.service.CarreraService;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Carrera;
import java.util.List;

@RestController
@RequestMapping("/api/v1/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @GetMapping
    public ResponseEntity<List<Carrera>> getAllCarreras() {
        List<Carrera> carreras = carreraService.findAll();
        if (carreras.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carreras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrera> getCarreraById(@PathVariable String id) {
        Carrera carrera = carreraService.findById(id);
        if (carrera == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carrera);
    }

    @PostMapping
    public ResponseEntity<Carrera> createCarrera(@RequestBody Carrera carrera) {
        Carrera createdCarrera = carreraService.save(carrera);
        return ResponseEntity.status(201).body(createdCarrera);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrera> updateCarrera(@RequestBody Carrera carrera) {
        Carrera updatedCarrera = carreraService.save(carrera);
        if (updatedCarrera == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCarrera);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Carrera> patchCarrera(@PathVariable String id, @RequestBody Carrera carrera) {
        Carrera patchedCarrera = carreraService.patchCarrera(id, carrera);
        if (patchedCarrera == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedCarrera);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrera(@PathVariable String id) {
        if (carreraService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        carreraService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }
    
}
