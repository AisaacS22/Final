package isa.ejercicio;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "cursos")
public class CursosClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private int idCurso;

    @Column(name = "nombre_Curso")
    private String nombreCurso;

    @Column(name = "profesor")
    private String profesor;

    @OneToMany(mappedBy = "cursosByIdCurso", cascade = CascadeType.ALL)
    private Collection<InscripcionesClass> inscripciones;

    public CursosClass() {
    }

    public CursosClass(String nombreCurso, String profesor) {
        this.nombreCurso = nombreCurso;
        this.profesor = profesor;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public Collection<InscripcionesClass> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(Collection<InscripcionesClass> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public void addInscripcion(InscripcionesClass inscripcion) {
        inscripcion.setCursosByIdCurso(this);
        this.inscripciones.add(inscripcion);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CursosClass curso = (CursosClass) o;

        return idCurso == curso.idCurso;
    }

    @Override
    public int hashCode() {
        return idCurso;
    }
}
