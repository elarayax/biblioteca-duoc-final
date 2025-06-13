package biblioteca.salas.duoc.biblioteca.salas.duoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Sala;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.ReservaRepository;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.SalaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Sala> findAll() {
        return salaRepository.findAll();
    }

    public Sala findById(Long id) {
        return salaRepository.findById(id).orElse(null);
    }

    public Sala save(Sala sala) {
        return salaRepository.save(sala);
    }

    public void deleteById(Long id) {
        Sala sala = salaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

        reservaRepository.deleteBySala(sala);

        salaRepository.deleteById(id);
    }

    public Sala patchSala(Long id, Sala sala) {
        Sala existingSala = findById(id);
        if (existingSala != null) {
            if (sala.getNombre() != null) {
                existingSala.setNombre(sala.getNombre());
            }
            if (sala.getCapacidad() != null) {
                existingSala.setCapacidad(sala.getCapacidad());
            }
            if (sala.getTipoSala() != null) {
                existingSala.setTipoSala(sala.getTipoSala());
            }
            if (sala.getIdInstituto() != null) {
                existingSala.setIdInstituto(sala.getIdInstituto());
            }
            return save(existingSala);
        }
        return null;
    }
}
