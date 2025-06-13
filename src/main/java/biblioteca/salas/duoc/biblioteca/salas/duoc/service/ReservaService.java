package biblioteca.salas.duoc.biblioteca.salas.duoc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Estudiante;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Reserva;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.ReservaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Reserva findById(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    public Reserva save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public void deleteById(Long id) {
        reservaRepository.deleteById(id);
    }

    public void deleteByEstudiante(Estudiante estudiante) {
        reservaRepository.deleteByEstudiante(estudiante);
    }

    public List<Reserva> findByEstudianteId(Integer idEstudiante) {
        return reservaRepository.findByEstudianteId(idEstudiante);
    }

    public List<Reserva> findBySalaCodigo(Long codigo) {
        return reservaRepository.findBySalaCodigo(codigo);
    }

    public List<Reserva> findByEstado(Integer estado) {
        return reservaRepository.findByEstado(estado);
    }

    public List<Reserva> findByFechaSolicitada(Date fecha) {
        return reservaRepository.findByFechaSolicitada(fecha);
    }

    public List<Reserva> findByFechaSolicitadaBetween(Date fechaInicio, Date fechaFin) {
        return reservaRepository.findByFechaSolicitadaBetween(fechaInicio, fechaFin);
    }

    public Long contByEstudianteId(Integer idEstudiante) {
        return reservaRepository.countByEstudianteId(idEstudiante);
    }

    public List<Reserva> findByEstudianteIdAndFechaSolicitada(Integer idEstudiante, Date fecha) {
        return reservaRepository.findByEstudianteIdAndFechaSolicitada(idEstudiante, fecha);
    }

    public List<Reserva> findBySalaAndEstado(Integer idSala, Integer estado) {
        return reservaRepository.findBySalaCodigoAndEstado(idSala, estado);
    }

    public List<Reserva> findByEstudianteIdAndFechaSolicitadaBetween(Integer idEstudiante, Date fechaInicio, Date fechaFin) {
        return reservaRepository.findByEstudianteIdAndFechaSolicitadaBetween(idEstudiante, fechaInicio, fechaFin);
    }

    public List<Reserva> findBySalaAndFechaSolicitadaBetween(Integer idSala, Date fechaInicio, Date fechaFin) {
        return reservaRepository.findBySalaCodigoAndFechaSolicitadaBetween(idSala, fechaInicio, fechaFin);
    }

    public Reserva patchReserva(Long id, Reserva reserva) {
        Reserva existingReserva = findById(id);
        if (existingReserva != null) {
            if (reserva.getSala() != null) {
                existingReserva.setSala(reserva.getSala());
            }
            if (reserva.getEstado() != null) {
                existingReserva.setEstado(reserva.getEstado());
            }
            if (reserva.getFechaSolicitada() != null) {
                existingReserva.setFechaSolicitada(reserva.getFechaSolicitada());
            }
            if (reserva.getHoraSolicitada() != null) {
                existingReserva.setHoraSolicitada(reserva.getHoraSolicitada());
            }
            if (reserva.getHoraCierre() != null) {
                existingReserva.setHoraCierre(reserva.getHoraCierre());
            }
            return save(existingReserva);
        }
        return null;
    }
}
