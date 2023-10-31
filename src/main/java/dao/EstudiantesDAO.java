package dao;

import isa.ejercicio.EstudiantesClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class EstudiantesDAO {

    private final SessionFactory sessionFactory;
    private EstudiantesDAO estudiantesDAO;

    public EstudiantesDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public EstudiantesDAO(SessionFactory sessionFactory, EstudiantesDAO estudiantesDAO) {
        this.sessionFactory = sessionFactory;
        this.estudiantesDAO = estudiantesDAO;
    }

    // Nuevo método para guardar información del estudiante
    public void saveEstudianteInfo(String nombre, String apellido, int idEstudiante) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            EstudiantesClass estudiante = session.get(EstudiantesClass.class, idEstudiante);
            if (estudiante != null) {
                estudiante.setNombre(nombre);
                estudiante.setApellido(apellido);
                session.saveOrUpdate(estudiante);
            }

            session.getTransaction().commit();
        }
    }

    public void saveOrUpdate(EstudiantesClass estudiantesByIdEstudiante) {
    }
}
