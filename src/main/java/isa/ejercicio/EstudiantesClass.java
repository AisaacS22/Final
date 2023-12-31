package isa.ejercicio;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "estudiantes") // Nombre de la tabla en la base de datos
public class EstudiantesClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Estudiante") // Nombre de la columna en la base de datos
    private int idEstudiante;

    @Column(name = "nombre") // Nombre de la columna en la base de datos
    private String nombre;

    @Column(name = "apellido") // Nombre de la columna en la base de datos
    private String apellido;

    @Column(name = "email") // Nombre de la columna en la base de datos
    private String email;

    @OneToMany(mappedBy = "estudiantesByIdEstudiante", cascade = CascadeType.ALL)
    private Collection<InscripcionesClass> inscripcionesEstudiante;

    // Constructor predeterminado necesario para Hibernate
    public EstudiantesClass() {
    }

    // Constructor con parámetros si es necesario
    public EstudiantesClass(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    // Getters y Setters
    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<InscripcionesClass> getInscripcionesEstudiante() {
        return inscripcionesEstudiante;
    }

    public void setInscripcionesEstudiante(Collection<InscripcionesClass> inscripcionesEstudiante) {
        this.inscripcionesEstudiante = inscripcionesEstudiante;
    }

    // Método para mantener la relación bidireccional
    public void addInscripcion(InscripcionesClass inscripcion) {
        inscripcion.setEstudiantesByIdEstudiante(this);
        this.inscripcionesEstudiante.add(inscripcion);
    }
    public String getNombreCompleto() {
        // Suponiendo que tienes atributos 'nombre' y 'apellido'
        return nombre + " " + apellido;
    }
}
