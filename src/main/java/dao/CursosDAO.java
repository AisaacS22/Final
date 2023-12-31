package dao;

import Datos.HibernateUtil;
import isa.ejercicio.CursosClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CursosDAO {

    private final SessionFactory sessionFactory;

    public CursosDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public CursosDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory; // Completa esta línea
    }

    public CursosClass getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(CursosClass.class, id);
        }
    }

    public void saveOrUpdateCurso(String nombreCurso) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            // Crear una instancia de CursosClass y establecer el nombre del curso
            CursosClass cursoClass = new CursosClass();
            cursoClass.setNombreCurso(nombreCurso);
            session.saveOrUpdate(cursoClass);
            session.getTransaction().commit();
        }
    }

    public void delete(int cursoId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CursosClass curso = session.get(CursosClass.class, cursoId);
            if (curso != null) {
                session.delete(curso);
            }
            session.getTransaction().commit();
        }
    }
    public CursosClass getByNombre(String nombreCurso) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM CursosClass c WHERE c.nombreCurso = :nombreCurso";
            return session.createQuery(hql, CursosClass.class)
                    .setParameter("nombreCurso", nombreCurso)
                    .uniqueResult();
        }
    }

    public void saveOrUpdateCurso(CursosClass curso) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(curso);
            session.getTransaction().commit();
        }
    }

    public List<CursosClass> getAllCursos() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM CursosClass", CursosClass.class).list();
        }
    }
}
