package biblioteca.salas.duoc.biblioteca.salas.duoc.assemblers;

import biblioteca.salas.duoc.biblioteca.salas.duoc.controller.v2.ReservaControllerV2;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Reserva;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReservaModelAssembler implements RepresentationModelAssembler<Reserva, EntityModel<Reserva>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Reserva> toModel(Reserva reserva) {
        return EntityModel.of(reserva,
                linkTo(methodOn(ReservaControllerV2.class).getReservaById(Long.valueOf(reserva.getId()))).withSelfRel(),
                linkTo(methodOn(ReservaControllerV2.class).getReservasBySala(Long.valueOf(reserva.getSala().getCodigo()))).withRel("reservas-por-sala"),
                linkTo(methodOn(ReservaControllerV2.class).deleteReserva(Long.valueOf(reserva.getId()))).withRel("eliminar"),
                linkTo(methodOn(ReservaControllerV2.class).updateReserva(Long.valueOf(reserva.getId()), reserva)).withRel("actualizar")
        );
    }
}
