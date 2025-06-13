package biblioteca.salas.duoc.biblioteca.salas.duoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Carrera;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Estudiante;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.CarreraRepository;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.EstudianteRepository;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.ReservaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Carrera> findAll() {
        return carreraRepository.findAll();
    }

    public Carrera findById(String id) {
        return carreraRepository.findById(id).orElse(null);
    }

    public Carrera save(Carrera carrera) {
        return carreraRepository.save(carrera);
    }

    public void deleteById(String id) {
        Carrera carrera = carreraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        List<Estudiante> estudiantes = estudianteRepository.findByCarrera(carrera);

        for (Estudiante estudiante : estudiantes) {
            reservaRepository.deleteByEstudiante(estudiante);
            estudianteRepository.delete(estudiante);
        }

        carreraRepository.delete(carrera);
    }

    public Carrera patchCarrera(String id, Carrera carrera) {
        Carrera existingCarrera = findById(id);
        if (existingCarrera != null) {
            if (carrera.getNombre() != null) {
                existingCarrera.setNombre(carrera.getNombre());
            }
            return save(existingCarrera);
        }
        return null;
    }
}
