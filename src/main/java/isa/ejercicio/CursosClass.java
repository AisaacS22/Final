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

    @OneToMany(mappedBy = "listCurso", cascade = CascadeType.ALL)
    private Collection<InscripcionesClass> inscripciones;

    // Constructor vacío necesario para Hibernate
    public CursosClass() {
    }

    // Constructor con parámetros
    public CursosClass(String nombreCurso, String profesor) {
        this.nombreCurso = nombreCurso;
        this.profesor = profesor;
    }

    // Getter y Setter para idCurso
    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    // Getter y Setter para nombreCurso
    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    // Getter y Setter para profesor
    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    // Getter y Setter para inscripciones
    public Collection<InscripcionesClass> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(Collection<InscripcionesClass> inscripciones) {
        this.inscripciones = inscripciones;
    }

    // Método para agregar inscripción y mantener la relación bidireccional
    public void addInscripcion(InscripcionesClass inscripcion) {
        inscripcion.setCurso(this);
        this.inscripciones.add(inscripcion);
    }



    // Equals y HashCode
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
