package dao;

import Datos.HibernateUtil;
import isa.ejercicio.InscripcionesClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class InscripcionesDAO {

    private final SessionFactory sessionFactory;

    public InscripcionesDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    public List<String> getAllCursosDisponibles() {
        try (Session session = sessionFactory.openSession()) {

            return List.of("Matemáticas", "Física", "Programación");
        }
    }

    public List<String> getAllEstudiantesInscritos() {
        try (Session session = sessionFactory.openSession()) {
            return List.of("Estudiante1", "Estudiante2", "Estudiante3");
        }
    }

    public InscripcionesClass getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(InscripcionesClass.class, id);
        }
    }

    public void saveOrUpdate(InscripcionesClass inscripcion) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(inscripcion);
            session.getTransaction().commit();
        }
    }

    public void delete(InscripcionesClass inscripcion) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(inscripcion);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e; // Relanzar la excepción después de realizar el rollback
            }
        }
    }

    public List<InscripcionesClass> consultarPorCampoYValor(String campo, String valor) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM InscripcionesClass i WHERE " + campo + " = :valor";
            return session.createQuery(hql, InscripcionesClass.class)
                    .setParameter("valor", valor)
                    .list();
        }
    }

    public List<InscripcionesClass> getAllInscripciones() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM InscripcionesClass", InscripcionesClass.class).list();
        }
    }
}
