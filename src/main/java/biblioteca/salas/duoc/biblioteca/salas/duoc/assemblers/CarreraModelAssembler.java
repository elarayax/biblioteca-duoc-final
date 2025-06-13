package biblioteca.salas.duoc.biblioteca.salas.duoc.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import biblioteca.salas.duoc.biblioteca.salas.duoc.controller.CarreraControllerV2;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Carrera;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CarreraModelAssembler implements RepresentationModelAssembler<Carrera, EntityModel<Carrera>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Carrera> toModel(Carrera carrera) {
        return EntityModel.of(carrera,
                linkTo(methodOn(CarreraControllerV2.class).getCarreraByCodigo(carrera.getCodigo())).withSelfRel(),
                linkTo(methodOn(CarreraControllerV2.class).getAllCarreras()).withRel("carreras"),
                linkTo(methodOn(CarreraControllerV2.class).updateCarrera(carrera.getCodigo(), carrera)).withRel("actualizar"),
                linkTo(methodOn(CarreraControllerV2.class).deleteCarrera(carrera.getCodigo())).withRel("eliminar"),
                linkTo(methodOn(CarreraControllerV2.class).patchCarrera(carrera.getCodigo(), carrera)).withRel("actualizar-parcial")
        );
    }
}
