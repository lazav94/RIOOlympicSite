package hibernate;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            AnnotationConfiguration ac = new AnnotationConfiguration().configure().addPackage("hibernate");
            ac.addAnnotatedClass(User.class);
            ac.addAnnotatedClass(UserRequest.class);
            ac.addAnnotatedClass(Nation.class);
            ac.addAnnotatedClass(Sport.class);
            ac.addAnnotatedClass(Sportsman.class);
            ac.addAnnotatedClass(Discipline.class);
            ac.addAnnotatedClass(Record.class);
            ac.addAnnotatedClass(Tournament.class);
            ac.addAnnotatedClass(Team.class);
            ac.addAnnotatedClass(Matchh.class);
            ac.addAnnotatedClass(Matchh8.class);
          

            sessionFactory = ac.buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
