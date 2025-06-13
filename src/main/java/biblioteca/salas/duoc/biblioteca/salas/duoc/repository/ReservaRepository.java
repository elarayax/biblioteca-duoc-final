package biblioteca.salas.duoc.biblioteca.salas.duoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Estudiante;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Reserva;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Sala;
import java.util.Date;


public interface ReservaRepository extends JpaRepository<Reserva, Long>{

    List<Reserva> findBySalaCodigo(Long codigo);

    void deleteByEstudiante(Estudiante estudiante);

    List<Reserva> findByEstudiante(Estudiante estudiante);

    List<Reserva> findByEstudianteId(Integer idEstudiante);

    void deleteBySala(Sala sala);

    List<Reserva> findByFechaSolicitada(Date fechaSolicitada);

    List<Reserva> findByFechaSolicitadaBetween(Date fechaInicio, Date fechaFin);

    List<Reserva> findByEstado(Integer estado);

    long countByEstudianteId(Integer idEstudiante);

    List<Reserva> findByEstudianteIdAndFechaSolicitada(Integer idEstudiante, Date fecha);

    List<Reserva> findBySalaCodigoAndEstado(Integer idSala, Integer estado);

    List<Reserva> findByEstudianteIdAndFechaSolicitadaBetween(Integer idEstudiante, Date fechaInicio, Date fechaFin);

    List<Reserva> findBySalaCodigoAndFechaSolicitadaBetween(Integer codigoSala, Date fechaInicio, Date fechaFin);
}
