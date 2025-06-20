package biblioteca.salas.duoc.biblioteca.salas.duoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Carrera;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Estudiante;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.CarreraRepository;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.EstudianteRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private EstudianteRepository estudianteRepository;

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
        // Primero, verificar si la carrera existe 
        Carrera carrera = carreraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        // Luego, hacemos un listado de estudiantes asociados a la carrera
        List<Estudiante> estudiantes = estudianteRepository.findByCarrera(carrera);

        // Eliminamos los estudiantes asociados a la carrera, ejecutando el método deleteById de EstudianteService, como sabrán para eliminar un estudiante, 
        // se eliminan las reservas asociadas a este y luego se elimina el estudiante, al usar el método deleteById de estudianteService,
        // se asegura que las reservas asociadas a los estudiantes sean eliminadas antes de eliminar el estudiante.
        for (Estudiante estudiante : estudiantes) {
            estudianteService.deleteById(Long.valueOf(estudiante.getId()));
        }

        // Finalmente, eliminamos la carrera
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
