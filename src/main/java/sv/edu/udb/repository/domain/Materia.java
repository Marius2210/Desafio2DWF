package sv.edu.udb.repository.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "materia")
@JsonIgnoreProperties
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    //Muchas materias pertenecen a un solo profesor
    @ManyToOne
    @JoinColumn(name = "id_profesor")
    @JsonIgnoreProperties("materias") // Evita que el profesor intente cargar de nuevo sus materias
    private Profesor profesor;

    //Una materia puede tener muchos alumnos y un alumno puede estar en muchas materias
    @ManyToMany(mappedBy = "materias")
    @JsonIgnoreProperties("materias") // Evita que el alumno intente cargar de nuevo sus materias
    private List<Alumno> alumnos;

    // Constructores
    public Materia() {}

    public Materia(String nombre, Profesor profesor) {
        this.nombre = nombre;
        this.profesor = profesor;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Profesor getProfesor() { return profesor; }
    public void setProfesor(Profesor profesor) { this.profesor = profesor; }

    public List<Alumno> getAlumnos() { return alumnos; }
    public void setAlumnos(List<Alumno> alumnos) { this.alumnos = alumnos; }
}