package biblioteca.salas.duoc.biblioteca.salas.duoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Carrera;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Estudiante;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.EstudianteRepository;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.ReservaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private ReservaRepository reservaRepository;


    public List<Estudiante> findAll() {
        return estudianteRepository.findAll();
    }

    public Estudiante findById(Long id) {
        return estudianteRepository.findById(id).orElse(null);
    }

    public List<Estudiante> findByCarrera(Carrera carrera) {
        return estudianteRepository.findByCarrera(carrera);
    }

    public Estudiante save(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public void deleteById(Long id) {
        // Primero, verificar si el estudiante existe
        Estudiante estudiante = estudianteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        //por que no hay un for acá, porque el id es único, no hay más de un estudiante con el mismo id

        // Luego, eliminamos las reservas asociadas al estudiante
        // generamos el método en el repositorio reservaRepository, no en el service, ya que no es necesario, este método se lo se ejecutará desde acá
        reservaRepository.deleteByEstudiante(estudiante);

        // Finalmente, eliminamos el estudiante
        estudianteRepository.delete(estudiante);
    }

    public Estudiante patchEstudiante(Long id, Estudiante estudiante) {
        Estudiante existingEstudiante = findById(id);
        if (existingEstudiante != null) {
            if (estudiante.getNombres() != null) {
                existingEstudiante.setNombres(estudiante.getNombres());
            }
            if (estudiante.getCorreo() != null) {
                existingEstudiante.setCorreo(estudiante.getCorreo());
            }
            if (estudiante.getTelefono() != null) {
                existingEstudiante.setTelefono(estudiante.getTelefono());
            }
            if (estudiante.getJornada() != null) {
                existingEstudiante.setJornada(estudiante.getJornada());
            }
            if (estudiante.getCarrera() != null) {
                existingEstudiante.setCarrera(estudiante.getCarrera());
            }
            return save(existingEstudiante);
        }
        return null;
    }
}
