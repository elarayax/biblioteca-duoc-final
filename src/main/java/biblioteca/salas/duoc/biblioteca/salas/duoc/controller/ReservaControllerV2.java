package biblioteca.salas.duoc.biblioteca.salas.duoc.controller;

import biblioteca.salas.duoc.biblioteca.salas.duoc.assemblers.ReservaModelAssembler;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Reserva;
import biblioteca.salas.duoc.biblioteca.salas.duoc.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/reservas")
public class ReservaControllerV2 {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ReservaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Reserva>> getAllReservas() {
        List<EntityModel<Reserva>> reservas = reservaService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(reservas,
                linkTo(methodOn(ReservaControllerV2.class).getAllReservas()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Reserva>> getReservaById(@PathVariable Long id) {
        Reserva reserva = reservaService.findById(id);
        if (reserva == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(reserva));
    }

    @GetMapping(value = "/{id}/sala", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Reserva>> getReservaByIdWithSala(@PathVariable Long id) {
        Reserva reserva = reservaService.findById(id);
        if (reserva == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(reserva));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Reserva>> createReserva(@RequestBody Reserva reserva) {
        Reserva nuevaReserva = reservaService.save(reserva);
        return ResponseEntity
                .created(linkTo(methodOn(ReservaControllerV2.class).getReservaById(Long.valueOf(nuevaReserva.getId()))).toUri())
                .body(assembler.toModel(nuevaReserva));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Reserva>> updateReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        reserva.setId(id.intValue());
        Reserva updated = reservaService.save(reserva);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Reserva>> patchReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        Reserva patched = reservaService.patchReserva(id, reserva);
        if (patched == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(patched));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        Reserva existing = reservaService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        reservaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/sala/{codigo}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Reserva>> getReservasBySala(@PathVariable Long codigo) {
        List<EntityModel<Reserva>> reservas = reservaService.findBySalaCodigo(codigo).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                reservas,
                linkTo(methodOn(ReservaControllerV2.class).getReservasBySala(codigo)).withSelfRel()
        );
    }

    @GetMapping(value = "/estudiante/{idEstudiante}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Reserva>> getReservasByEstudiante(@PathVariable Integer idEstudiante) {
        List<EntityModel<Reserva>> reservas = reservaService.findByEstudianteId(idEstudiante).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                reservas,
                linkTo(methodOn(ReservaControllerV2.class).getReservasByEstudiante(idEstudiante)).withSelfRel()
        );
    }

    @GetMapping(value = "/estado/{estado}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Reserva>> getReservasByEstado(@PathVariable Integer estado) {
        List<EntityModel<Reserva>> reservas = reservaService.findByEstado(estado).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                reservas,
                linkTo(methodOn(ReservaControllerV2.class).getReservasByEstado(estado)).withSelfRel()
        );
    }

    @GetMapping(value = "/fecha/{fecha}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Reserva>> getReservasByFechaSolicitada(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
        List<EntityModel<Reserva>> reservas = reservaService.findByFechaSolicitada(fecha).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                reservas,
                linkTo(methodOn(ReservaControllerV2.class).getReservasByFechaSolicitada(fecha)).withSelfRel()
        );
    }

    @GetMapping(value = "/fechas/{fechaInicio}/{fechaFin}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Reserva>> getReservasByFechaSolicitadaBetween(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")  Date fechaInicio, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")  Date fechaFin) {
        List<EntityModel<Reserva>> reservas = reservaService.findByFechaSolicitadaBetween(fechaInicio, fechaFin).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                reservas,
                linkTo(methodOn(ReservaControllerV2.class).getReservasByFechaSolicitadaBetween(fechaInicio, fechaFin)).withSelfRel()
        );
    }

    @GetMapping(value = "/estudiantes/cantidad/{idEstudiante}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Long> countReservasByEstudianteId(@PathVariable Integer idEstudiante) {
        long count = reservaService.contByEstudianteId(idEstudiante);
        return ResponseEntity.ok(count);
    }

    @GetMapping(value = "/estudiante/{idEstudiante}/fecha/{fecha}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Reserva>> getReservasByEstudianteAndFecha(@PathVariable Integer idEstudiante, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {

        List<EntityModel<Reserva>> reservas = reservaService.findByEstudianteIdAndFechaSolicitada(idEstudiante, fecha)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                reservas,
                linkTo(methodOn(ReservaControllerV2.class).getReservasByEstudianteAndFecha(idEstudiante, fecha)).withSelfRel()
        );
    }

    @GetMapping(value = "/sala/{idSala}/estado/{estado}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Reserva>> getReservasBySalaAndEstado(@PathVariable Integer idSala, @PathVariable Integer estado) {

        List<EntityModel<Reserva>> reservas = reservaService.findBySalaAndEstado(idSala, estado)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                reservas,
                linkTo(methodOn(ReservaControllerV2.class).getReservasBySalaAndEstado(idSala, estado)).withSelfRel()
        );
    }

    @GetMapping(value = "/estudiante/{idEstudiante}/fechas/{fechaInicio}/{fechaFin}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Reserva>> getReservasByEstudianteBetweenFechas(@PathVariable Integer idEstudiante, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {

        List<EntityModel<Reserva>> reservas = reservaService.findByEstudianteIdAndFechaSolicitadaBetween(idEstudiante, fechaInicio, fechaFin)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                reservas,
                linkTo(methodOn(ReservaControllerV2.class).getReservasByEstudianteBetweenFechas(idEstudiante, fechaInicio, fechaFin)).withSelfRel()
        );
    }

    @GetMapping(value = "/sala/{idSala}/fechas/{fechaInicio}/{fechaFin}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Reserva>> getReservasBySalaBetweenFechas(@PathVariable Integer idSala, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {

        List<EntityModel<Reserva>> reservas = reservaService.findBySalaAndFechaSolicitadaBetween(idSala, fechaInicio, fechaFin)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                reservas,
                linkTo(methodOn(ReservaControllerV2.class).getReservasBySalaBetweenFechas(idSala, fechaInicio, fechaFin)).withSelfRel()
        );
    }

}
