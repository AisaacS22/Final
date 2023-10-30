package service;

import dao.CursosDAO;
import dao.EstudiantesDAO;
import dao.InscripcionesDAO;
import isa.ejercicio.CursosClass;
import isa.ejercicio.EstudiantesClass;
import isa.ejercicio.InscripcionesClass;

import java.util.Date;

public class InscripcionService {

    private final InscripcionesDAO inscripcionesDAO;
    private final EstudiantesDAO estudiantesDAO;
    private final CursosDAO cursosDAO;

    public InscripcionService(InscripcionesDAO inscripcionesDAO, EstudiantesDAO estudiantesDAO, CursosDAO cursosDAO) {
        this.inscripcionesDAO = inscripcionesDAO;
        this.estudiantesDAO = estudiantesDAO;
        this.cursosDAO = cursosDAO;
    }

    public void inscribirEstudiante(EstudiantesClass estudiante, String nombreCurso) {
        // Verificar si el estudiante existe en la base de datos
        EstudiantesClass estudianteExistente = estudiantesDAO.getByNombreApellido(estudiante.getNombre(), estudiante.getApellido());

        if (estudianteExistente == null) {
            // Si el estudiante no existe, guardarlo en la base de datos
            estudiantesDAO.saveOrUpdate(estudiante);
        } else {
            // Si el estudiante ya existe, utilizar la instancia existente
            estudiante = estudianteExistente;
        }

        // Verificar si el curso existe en la base de datos
        CursosClass curso = cursosDAO.getByNombre(nombreCurso);

        if (curso == null) {
            // Si el curso no existe, crear uno nuevo
            curso = new CursosClass();
            curso.setNombreCurso(nombreCurso);
            cursosDAO.saveOrUpdateCurso(curso);
        }

        // Crear una instancia de InscripcionesClass con la información
        InscripcionesClass nuevaInscripcion = new InscripcionesClass(estudiante, curso, new Date(System.currentTimeMillis()));

        // Guardar la inscripción en la base de datos utilizando el DAO
        inscripcionesDAO.saveOrUpdate(nuevaInscripcion);
    }
}
