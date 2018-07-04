package database;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import resources.References;
import views.AlertBox;

public class Connection {

    public static SessionFactory factory;

    public static void openConnection() {
        try{
            factory = new Configuration ()
                    .configure(References.getInstance().getHibernateConfigPath())
                    .addAnnotatedClass(Activity.class)
                    .addAnnotatedClass(Project.class)
                    .addAnnotatedClass(ProjectMember.class)
                    .addAnnotatedClass(Task.class)
                    .addAnnotatedClass(TaskGroup.class)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();

            System.out.println("Connection successfully opened!");
        }
        catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            AlertBox.display("Errormessage", "Failed to Open Database Connection");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void closeConnection() {
        try {
            factory.close();
            System.out.println("Connection successfully closed!");
        } catch (Throwable ex) {
            System.err.println("Failed to close sessionFactory object." + ex);
            AlertBox.display("Errormessage", "Failed to Close Database Connection");
            throw new ExceptionInInitializerError(ex);
        }
    }

}
