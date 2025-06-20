package biblioteca.salas.duoc.biblioteca.salas.duoc.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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

    @Test
    public void testFindByEstudianteId() {
        when(reservaRepository.findByEstudianteId(10)).thenReturn(List.of(createReserva()));
        List<Reserva> reservas = reservaService.findByEstudianteId(10);
        assertNotNull(reservas);
        assertFalse(reservas.isEmpty());
    }

    @Test
    public void testFindBySalaCodigo() {
        when(reservaRepository.findBySalaCodigo(5L)).thenReturn(List.of(createReserva()));
        List<Reserva> reservas = reservaService.findBySalaCodigo(5L);
        assertEquals(1, reservas.size());
    }

    @Test
    public void testFindByEstado() {
        when(reservaRepository.findByEstado(1)).thenReturn(List.of(createReserva()));
        List<Reserva> reservas = reservaService.findByEstado(1);
        assertFalse(reservas.isEmpty());
    }

    @Test
    public void testFindByFechaSolicitada() {
        Date fecha = new Date();
        when(reservaRepository.findByFechaSolicitada(fecha)).thenReturn(List.of(createReserva()));
        List<Reserva> reservas = reservaService.findByFechaSolicitada(fecha);
        assertFalse(reservas.isEmpty());
    }

    @Test
    public void testFindByFechaSolicitadaBetween() {
        Date inicio = new Date();
        Date fin = new Date(System.currentTimeMillis() + 86400000); // +1 día
        when(reservaRepository.findByFechaSolicitadaBetween(inicio, fin)).thenReturn(List.of(createReserva()));
        List<Reserva> reservas = reservaService.findByFechaSolicitadaBetween(inicio, fin);
        assertEquals(1, reservas.size());
    }

    @Test
    public void testContByEstudianteId() {
        when(reservaRepository.countByEstudianteId(10)).thenReturn(2L);
        Long count = reservaService.contByEstudianteId(10);
        assertEquals(2L, count);
    }

    @Test
    public void testFindByEstudianteIdAndFechaSolicitada() {
        Date fecha = new Date();
        when(reservaRepository.findByEstudianteIdAndFechaSolicitada(10, fecha)).thenReturn(List.of(createReserva()));
        List<Reserva> reservas = reservaService.findByEstudianteIdAndFechaSolicitada(10, fecha);
        assertEquals(1, reservas.size());
    }

    @Test
    public void testFindBySalaAndEstado() {
        when(reservaRepository.findBySalaCodigoAndEstado(5, 1)).thenReturn(List.of(createReserva()));
        List<Reserva> reservas = reservaService.findBySalaAndEstado(5, 1);
        assertFalse(reservas.isEmpty());
    }

    @Test
    public void testFindByEstudianteIdAndFechaSolicitadaBetween() {
        Date inicio = new Date();
        Date fin = new Date(System.currentTimeMillis() + 86400000);
        when(reservaRepository.findByEstudianteIdAndFechaSolicitadaBetween(10, inicio, fin)).thenReturn(List.of(createReserva()));
        List<Reserva> reservas = reservaService.findByEstudianteIdAndFechaSolicitadaBetween(10, inicio, fin);
        assertFalse(reservas.isEmpty());
    }

    @Test
    public void testFindBySalaAndFechaSolicitadaBetween() {
        Date inicio = new Date();
        Date fin = new Date(System.currentTimeMillis() + 86400000);
        when(reservaRepository.findBySalaCodigoAndFechaSolicitadaBetween(5, inicio, fin)).thenReturn(List.of(createReserva()));
        List<Reserva> reservas = reservaService.findBySalaAndFechaSolicitadaBetween(5, inicio, fin);
        assertFalse(reservas.isEmpty());
    }

}
