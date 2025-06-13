package biblioteca.salas.duoc.biblioteca.salas.duoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Sala;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.TipoSala;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.ReservaRepository;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.SalaRepository;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.TipoSalaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TipoSalaService {

    @Autowired
    private TipoSalaRepository tipoSalaRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private SalaRepository salaRepository;

    public List<TipoSala> findAll() {
        return tipoSalaRepository.findAll();
    }

    public TipoSala findById(Long id) {
        return tipoSalaRepository.findById(id).orElse(null);
    }

    public TipoSala save(TipoSala tipoSala) {
        return tipoSalaRepository.save(tipoSala);
    }

    public void deleteById(Long id) {
        TipoSala tipoSala = tipoSalaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("TipoSala no encontrado"));

        List<Sala> salas = salaRepository.findByTipoSala(tipoSala);

        for (Sala sala : salas) {   
            reservaRepository.deleteBySala(sala);
            salaRepository.delete(sala);
        }

        tipoSalaRepository.deleteById(id);
    }

    public TipoSala patchTipoSala(Long id, TipoSala tipoSala) {
        TipoSala existingTipoSala = findById(id);
        if (existingTipoSala != null) {
            if (tipoSala.getNombre() != null) {
                existingTipoSala.setNombre(tipoSala.getNombre());
            }
            return save(existingTipoSala);
        }
        return null;
    }
}
