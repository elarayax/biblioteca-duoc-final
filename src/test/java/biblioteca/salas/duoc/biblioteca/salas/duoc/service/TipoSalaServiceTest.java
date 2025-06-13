package biblioteca.salas.duoc.biblioteca.salas.duoc.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.TipoSala;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.TipoSalaRepository;

@SpringBootTest
public class TipoSalaServiceTest {

    @Autowired
    private TipoSalaService tipoSalaService;

    @MockBean
    private TipoSalaRepository tipoSalaRepository;

    private TipoSala createTipoSala() {
        return new TipoSala(1, "sala 1");
    }

    @Test
    public void testFindAll(){
        when(tipoSalaRepository.findAll()).thenReturn(List.of(createTipoSala()));
        List<TipoSala> tipos = tipoSalaService.findAll();
        assertNotNull(tipos);
        assertEquals(1, tipos.size());
    }

    @Test
    public void testFindById() {
        when(tipoSalaRepository.findById(1L)).thenReturn(Optional.of(createTipoSala()));
        TipoSala tipoSala = tipoSalaService.findById(1L);
        assertNotNull(tipoSala);
        assertEquals("sala 1", tipoSala.getNombre());
    }

    @Test
    public void testSave() {
        TipoSala tipoSala = createTipoSala();
        when(tipoSalaRepository.save(tipoSala)).thenReturn(tipoSala);
        TipoSala savedTipoSala = tipoSalaService.save(tipoSala);
        assertNotNull(savedTipoSala);
        assertEquals("sala 1", savedTipoSala.getNombre());
    }

    @Test
    public void testPatchTipoSala() {
        TipoSala existingTipoSala = createTipoSala();

        TipoSala patchData = new TipoSala();
        patchData.setNombre("sala actualizada");

        when(tipoSalaRepository.findById(1L)).thenReturn(Optional.of(existingTipoSala));
        when(tipoSalaRepository.save(any(TipoSala.class))).thenAnswer(invocation -> invocation.getArgument(0));

        TipoSala updatedTipoSala = tipoSalaService.patchTipoSala(1L, patchData);

        assertNotNull(updatedTipoSala);
        assertEquals("sala actualizada", updatedTipoSala.getNombre());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(tipoSalaRepository).deleteById(1L);
        tipoSalaService.deleteById(1L);
        verify(tipoSalaRepository, times(1)).deleteById(1L);
    }
}
