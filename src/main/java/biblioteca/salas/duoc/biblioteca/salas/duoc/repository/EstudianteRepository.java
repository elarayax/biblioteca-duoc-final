package biblioteca.salas.duoc.biblioteca.salas.duoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Carrera;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long>{

    List<Estudiante> findByCarrera(Carrera carrera);

    void deleteByCarrera(Carrera carrera);
}
