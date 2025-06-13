package biblioteca.salas.duoc.biblioteca.salas.duoc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carrera")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carrera {

    @Id
    @Column(unique = true, name = "codigo", nullable = false, length = 100)
    private String codigo;
    
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
}
