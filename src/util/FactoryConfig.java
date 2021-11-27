package util;
import entity.Customer;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Presented By - MINDARTLK.
 * Since - v0.1.0
 */
public class FactoryConfig {

    private static FactoryConfig factoryConfig;
    private final SessionFactory sessionFactory;

    private FactoryConfig() {
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Customer.class).addAnnotatedClass(Item.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfig getInstance() {
        return factoryConfig == null ? factoryConfig = new FactoryConfig() : factoryConfig;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}

