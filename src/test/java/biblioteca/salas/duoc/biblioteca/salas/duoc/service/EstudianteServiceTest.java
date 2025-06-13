package biblioteca.salas.duoc.biblioteca.salas.duoc.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Estudiante;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Carrera;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.EstudianteRepository;

@SpringBootTest
public class EstudianteServiceTest {

    @Autowired
    private EstudianteService estudianteService;

    @MockBean
    private EstudianteRepository estudianteRepository;
    
    private Estudiante createEstudiante() {
        return new Estudiante(
            1, 
            "121231239", 
            "Juan Perez", 
            "correo@correo.com", 
            'D', 
            123123123, 
            new Carrera()
        );
    }

    @Test
    public void testFindAll() {
        when(estudianteRepository.findAll()).thenReturn(List.of(createEstudiante()));
        List<Estudiante> estudiantes = estudianteService.findAll();
        assertNotNull(estudiantes);
        assertEquals(1, estudiantes.size());
    }

    @Test
    public void testFindById() {
        when(estudianteRepository.findById(1L)).thenReturn(java.util.Optional.of(createEstudiante()));
        Estudiante estudiante = estudianteService.findById(1L);
        assertNotNull(estudiante);
        assertEquals("Juan Perez", estudiante.getNombres());
    }

    @Test
    public void testSave() {
        Estudiante estudiante = createEstudiante();
        when(estudianteRepository.save(estudiante)).thenReturn(estudiante);
        Estudiante savedEstudiante = estudianteService.save(estudiante);
        assertNotNull(savedEstudiante);
        assertEquals("Juan Perez", savedEstudiante.getNombres());
    }

    @Test
    public void testPatchEstudiante() {
        Estudiante existingEstudiante = createEstudiante();
        Estudiante patchData = new Estudiante();
        patchData.setNombres("Juan Actualizado");

        when(estudianteRepository.findById(1L)).thenReturn(java.util.Optional.of(existingEstudiante));
        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(existingEstudiante);

        Estudiante patchedEstudiante = estudianteService.patchEstudiante(1L, patchData);
        assertNotNull(patchedEstudiante);
        assertEquals("Juan Actualizado", patchedEstudiante.getNombres());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(estudianteRepository).deleteById(1L);
        estudianteService.deleteById(1L);
        verify(estudianteRepository, times(1)).deleteById(1L);
    }
}
