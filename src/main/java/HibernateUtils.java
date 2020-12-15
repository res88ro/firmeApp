import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtils {


    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        Configuration configuration = new Configuration(); // conexiune noua


        Properties properties = new Properties(); // aici incepe configurarea conexiunii
        properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/copy");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "blank");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.HBM2DDL_AUTO,"update");


        configuration.setProperties(properties); // pasam toate proprietatile conexiunei facute mai sus
        configuration.addAnnotatedClass(Client.class);


        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }
    public static void cleanUp(){
        if(sessionFactory != null){
            sessionFactory.close();

        }
    }
}
