package biblioteca.salas.duoc.biblioteca.salas.duoc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estudiante")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 12)
    private String run;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "correo", unique = true, length = 100)
    private String correo;

    @Column(name="jornada", length = 1)
    private Character jornada;

    @Column(name = "telefono", length = 15)
    private Integer telefono;

    @ManyToOne
    @JoinColumn(name = "codigo_carrera")
    private Carrera carrera;

}
