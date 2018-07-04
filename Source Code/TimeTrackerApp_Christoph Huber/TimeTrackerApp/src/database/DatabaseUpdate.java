package database;

import models.Lists;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class DatabaseUpdate {
    Session session;
    Transaction tx;

    //Constructors
    public DatabaseUpdate() {
    }

    //Methods

    public void insertObject(Object object) {
        session = Connection.factory.getCurrentSession();
        tx=null;

        try {
            //start a transaction
            tx = session.beginTransaction();

            session.save(object);

            tx.commit();

        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public void deleteObject (Object object,int objectID) {
        session = Connection.factory.getCurrentSession();
        tx=null;

        try {
            tx = session.beginTransaction();

            object = session.get(object.getClass(), objectID);

            session.delete(object);

            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public void updateLoadObject (Object object, int objectID){
        session = Connection.factory.getCurrentSession();
        tx = null;

        try {
            tx = session.beginTransaction();
            session.load(object,objectID);
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateSaveObject (Object object, int objectID){
        session = Connection.factory.getCurrentSession();
        tx = session.getTransaction();

        Object tempObject2 = new Object();

        try {
            object = session.get(object.getClass(), objectID);
            session.update(object);
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}