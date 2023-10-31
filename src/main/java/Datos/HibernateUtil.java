package Datos;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private HibernateUtil() {
        // Constructor privado para evitar instanciación externa
    }

    private static SessionFactory buildSessionFactory() {
        try {
            // Carga la configuración de hibernate.cfg.xml
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception ex) {
            throw new RuntimeException("Error al construir la SessionFactory: " + ex.getMessage(), ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
