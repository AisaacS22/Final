package isa.ejercicio;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "inscripciones")
public class InscripcionesClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscripcion")
    private int idInscripcion;

    @ManyToOne
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    private CursosClass cursosByIdCurso;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante")
    private EstudiantesClass estudiantesByIdEstudiante;

    @Column(name = "fecha_inscripcion")
    private Date fechaInscripcion;

    public InscripcionesClass() {
    }

    public InscripcionesClass(EstudiantesClass estudiantesByIdEstudiante, CursosClass cursosByIdCurso, java.util.Date fechaInscripcion) {
        this.estudiantesByIdEstudiante = estudiantesByIdEstudiante;
        this.cursosByIdCurso = cursosByIdCurso;
        this.fechaInscripcion = new Date(fechaInscripcion.getTime());
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public CursosClass getCursosByIdCurso() {
        return cursosByIdCurso;
    }

    public void setCursosByIdCurso(CursosClass cursosByIdCurso) {
        this.cursosByIdCurso = cursosByIdCurso;
    }

    public EstudiantesClass getEstudiantesByIdEstudiante() {
        return estudiantesByIdEstudiante;
    }

    public void setEstudiantesByIdEstudiante(EstudiantesClass estudiantesByIdEstudiante) {
        this.estudiantesByIdEstudiante = estudiantesByIdEstudiante;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
}
