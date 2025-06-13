package biblioteca.salas.duoc.biblioteca.salas.duoc.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Estudiante;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Reserva;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Sala;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.ReservaRepository;

@SpringBootTest
public class ReservaServiceTest {

    @Autowired
    private ReservaService reservaService;

    @MockBean
    private ReservaRepository reservaRepository;

    private Reserva createReserva() {
        return new Reserva(
            1,
            new Estudiante(),
            new Sala(),
            new Date(),
            new Date(System.currentTimeMillis()),
            new Date(System.currentTimeMillis()+3600000),
            1
        );
    }

    @Test
    public void testFindAll() {
        when(reservaRepository.findAll()).thenReturn(List.of(createReserva()));
        List<Reserva> reservas = reservaService.findAll();
        assertNotNull(reservas);
        assertEquals(1, reservas.size());
    }

    @Test
    public void testFindById() {
        when(reservaRepository.findById(1L)).thenReturn(java.util.Optional.of(createReserva()));
        Reserva reserva = reservaService.findById(1L);
        assertNotNull(reserva);
        assertEquals(1, reserva.getId());
    }

    @Test
    public void testSave() {
        Reserva reserva = createReserva();
        when(reservaRepository.save(reserva)).thenReturn(reserva);
        Reserva savedReserva = reservaService.save(reserva);
        assertNotNull(savedReserva);
        assertEquals(1, savedReserva.getId());
    }

    @Test
    public void testPatchReserva() {
        Reserva existingReserva = createReserva();
        Reserva patchData = new Reserva();
        patchData.setEstado(2);

        when(reservaRepository.findById(1L)).thenReturn(java.util.Optional.of(existingReserva));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(existingReserva);

        Reserva updatedReserva = reservaService.patchReserva(1L, patchData);
        assertNotNull(updatedReserva);
        assertEquals(2, updatedReserva.getEstado());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(reservaRepository).deleteById(1L);
        reservaService.deleteById(1L);
        verify(reservaRepository, times(1)).deleteById(1L);
    }
}
