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

import biblioteca.salas.duoc.biblioteca.salas.duoc.service.ReservaService;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Reserva;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<Reserva>> getAllReservas() {
        List<Reserva> Reservas = reservaService.findAll();
        if (Reservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        Reserva Reserva = reservaService.findById(id);
        if (Reserva == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Reserva);
    }

    @PostMapping
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) {
        Reserva createdReserva = reservaService.save(reserva);
        return ResponseEntity.status(201).body(createdReserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@RequestBody Reserva reserva) {
        Reserva updatedReserva = reservaService.save(reserva);
        if (updatedReserva == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedReserva);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Reserva> patchReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        Reserva patchedReserva = reservaService.patchReserva(id, reserva);
        if (patchedReserva == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedReserva);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        if (reservaService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        reservaService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }
    
}
