package dao;

import Datos.HibernateUtil;
import isa.ejercicio.InscripcionesClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class InscripcionesDAO {

    private final SessionFactory sessionFactory;

    public InscripcionesDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
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
            session.beginTransaction();
            session.delete(inscripcion);
            session.getTransaction().commit();
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
