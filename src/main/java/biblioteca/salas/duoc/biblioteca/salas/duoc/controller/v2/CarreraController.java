package biblioteca.salas.duoc.biblioteca.salas.duoc.controller.v2;

import biblioteca.salas.duoc.biblioteca.salas.duoc.assemblers.CarreraModelAssembler;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Carrera;
import biblioteca.salas.duoc.biblioteca.salas.duoc.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @Autowired
    private CarreraModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Carrera>>> getAllCarreras() {
        List<EntityModel<Carrera>> carreras = carreraService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (carreras.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                carreras,
                linkTo(methodOn(CarreraController.class).getAllCarreras()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{codigo}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Carrera>> getCarreraByCodigo(@PathVariable String codigo) {
        Carrera carrera = carreraService.findById(codigo);
        if (carrera == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(carrera));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Carrera>> createCarrera(@RequestBody Carrera carrera) {
        Carrera newCarrera = carreraService.save(carrera);
        return ResponseEntity
                .created(linkTo(methodOn(CarreraController.class).getCarreraByCodigo(newCarrera.getCodigo())).toUri())
                .body(assembler.toModel(newCarrera));
    }

    @PutMapping(value = "/{codigo}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Carrera>> updateCarrera(@PathVariable String codigo, @RequestBody Carrera carrera) {
        carrera.setCodigo(codigo);
        Carrera updatedCarrera = carreraService.save(carrera);
        return ResponseEntity.ok(assembler.toModel(updatedCarrera));
    }

    @PatchMapping(value = "/{codigo}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Carrera>> patchCarrera(@PathVariable String codigo, @RequestBody Carrera carrera) {
        Carrera updatedCarrera = carreraService.patchCarrera(codigo, carrera);
        if (updatedCarrera == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updatedCarrera));
    }

    @DeleteMapping(value = "/{codigo}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> deleteCarrera(@PathVariable String codigo) {
        Carrera carrera = carreraService.findById(codigo);
        if (carrera == null) {
            return ResponseEntity.notFound().build();
        }
        carreraService.deleteById(codigo);
        return ResponseEntity.noContent().build();
    }
}
