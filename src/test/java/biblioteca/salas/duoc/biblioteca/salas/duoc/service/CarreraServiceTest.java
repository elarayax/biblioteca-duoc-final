package biblioteca.salas.duoc.biblioteca.salas.duoc.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Carrera;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.CarreraRepository;

@SpringBootTest
public class CarreraServiceTest {

    @Autowired
    private CarreraService carreraService;

    @MockBean
    private CarreraRepository carreraRepository;

    private Carrera createCarrera() {
        return new Carrera("001", "Ingeniería en Informática");
    }

    @Test
    public void testFindAll() {
        when(carreraRepository.findAll()).thenReturn(List.of(createCarrera()));
        List<Carrera> carreras = carreraService.findAll();
        assertNotNull(carreras);
        assertEquals(1, carreras.size());
    }

    @Test
    public void testFindById() {
        when(carreraRepository.findById("001")).thenReturn(java.util.Optional.of(createCarrera()));
        Carrera carrera = carreraService.findById("001");
        assertNotNull(carrera);
        assertEquals("Ingeniería en Informática", carrera.getNombre());
    }

    @Test
    public void testSave() {
        Carrera carrera = createCarrera();
        when(carreraRepository.save(carrera)).thenReturn(carrera);
        Carrera savedCarrera = carreraService.save(carrera);
        assertNotNull(savedCarrera);
        assertEquals("Ingeniería en Informática", savedCarrera.getNombre());
    }

    @Test
    public void testPatchCarrera() {
        Carrera existingCarrera = createCarrera();
        Carrera patchData = new Carrera();
        patchData.setNombre("Ingeniería en Computación");

        when(carreraRepository.findById("001")).thenReturn(java.util.Optional.of(existingCarrera));
        when(carreraRepository.save(any(Carrera.class))).thenReturn(existingCarrera);

        Carrera patchedCarrera = carreraService.patchCarrera("001", patchData);
        assertNotNull(patchedCarrera);
        assertEquals("Ingeniería en Computación", patchedCarrera.getNombre());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(carreraRepository).deleteById("001");
        carreraService.deleteById("001");
        verify(carreraRepository, times(1)).deleteById("001");
    }
}
