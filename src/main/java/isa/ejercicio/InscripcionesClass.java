package isa.ejercicio;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "inscripciones")
public class InscripcionesClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Inscripcion")
    private int idInscripcion;

    @ManyToOne
    @JoinColumn(name = "id_Estudiante")
    private EstudiantesClass estudiantesByIdEstudiante;

    @ManyToOne
    @JoinColumn(name = "id_Curso")
    private CursosClass listCurso;

    @Column(name = "fecha_Inscripcion")
    private Date fechaInscripcion;

    public InscripcionesClass() {
        // Constructor por defecto necesario para Hibernate
    }

    public InscripcionesClass(EstudiantesClass estudiante, CursosClass curso, Date fechaInscripcion) {
        this.estudiantesByIdEstudiante = estudiante;
        this.listCurso = curso;
        this.fechaInscripcion = fechaInscripcion;
    }

    public InscripcionesClass(String usuario, String cursoSeleccionado) {
    }

    // Otros métodos, getters y setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InscripcionesClass that = (InscripcionesClass) o;

        if (idInscripcion != that.idInscripcion) return false;
        if (estudiantesByIdEstudiante != null ? !estudiantesByIdEstudiante.equals(that.estudiantesByIdEstudiante) : that.estudiantesByIdEstudiante != null) return false;
        if (listCurso != null ? !listCurso.equals(that.listCurso) : that.listCurso != null) return false;
        if (fechaInscripcion != null ? !fechaInscripcion.equals(that.fechaInscripcion) : that.fechaInscripcion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idInscripcion;
        result = 31 * result + (estudiantesByIdEstudiante != null ? estudiantesByIdEstudiante.hashCode() : 0);
        result = 31 * result + (listCurso != null ? listCurso.hashCode() : 0);
        result = 31 * result + (fechaInscripcion != null ? fechaInscripcion.hashCode() : 0);
        return result;
    }
}
