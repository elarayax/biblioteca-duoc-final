package biblioteca.salas.duoc.biblioteca.salas.duoc.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Sala;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.TipoSala;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.SalaRepository;

@SpringBootTest
public class SalaServiceTest {

    @Autowired
    private SalaService salaService;

    @MockBean
    private SalaRepository salaRepository;

    private Sala createSala() {
        return new Sala(1, "sala 1", 10, 10, new TipoSala());
    }

    @Test
    public void testFindAll() {
        when(salaRepository.findAll()).thenReturn(List.of(createSala()));
        List<Sala> salas = salaService.findAll();
        assertNotNull(salas);
        assertEquals(1, salas.size());
    }

    @Test
    public void testFindById() {
        when(salaRepository.findById(1L)).thenReturn(java.util.Optional.of(createSala()));
        Sala sala = salaService.findById(1L);
        assertNotNull(sala);
        assertEquals("sala 1", sala.getNombre());
    }

    @Test
    public void testSave() {
        Sala sala = createSala();
        when(salaRepository.save(sala)).thenReturn(sala);
        Sala savedSala = salaService.save(sala);
        assertNotNull(savedSala);
        assertEquals("sala 1", savedSala.getNombre());
    }

    @Test
    public void testPatchSala() {
        Sala existingSala = createSala();
        Sala patchData = new Sala();
        patchData.setNombre("sala 2");

        when(salaRepository.findById(1L)).thenReturn(java.util.Optional.of(existingSala));
        when(salaRepository.save(any(Sala.class))).thenReturn(existingSala);

        Sala patchedSala = salaService.patchSala(1L, patchData);
        assertNotNull(patchedSala);
        assertEquals("sala 2", patchedSala.getNombre());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(salaRepository).deleteById(1L);
        salaService.deleteById(1L);
        verify(salaRepository, times(1)).deleteById(1L);
    }
}
