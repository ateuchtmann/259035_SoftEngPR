package database;

import models.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

public class DatabaseReceive {
    Session session;
    Transaction tx=null;

    //Constructors
    public DatabaseReceive() {
    }

    //Methods
    public void loadDataFromDatabase(){

        //Open Connection
        Connection.openConnection();

        //Load Tables
        loadTableActivity();
        loadTableProject();
        loadTableProjectMember();
        loadTableTask();
        loadTableTaskGroup();
        loadTableUser();

        System.out.println("----------------------------------------------------------------------------");
        System.out.println("*********************Database Migration COMPLETE****************************");
        System.out.println("----------------------------------------------------------------------------");

        //Close Connection
        Connection.closeConnection();
    }

    public void loadTableActivity(){
        session = Connection.factory.openSession();

        try {
            tx = session.beginTransaction();
            List tempList = session.createQuery("FROM Activity").list();
            for (Iterator iterator = tempList.iterator(); iterator.hasNext();){
                Activity activity = (Activity) iterator.next();
                System.out.println("Table [activity] Record: (ID)"+activity.getActivityID() + " succesfully loaded");
            }
            tx.commit();
            Lists.getInstance().getActivityList().clear();
            Lists.getInstance().getActivityList().addAll(tempList);

        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void loadTableProject(){
        session = Connection.factory.openSession();

        try {
            tx = session.beginTransaction();
            List tempList = session.createQuery("FROM Project").list();
            for (Iterator iterator = tempList.iterator(); iterator.hasNext();){
                Project project = (Project) iterator.next();
                System.out.println("Table [project] Record: (ID)"+project.getProjectID() + " succesfully loaded");
            }
            tx.commit();
            Lists.getInstance().getProjectList().clear();
            Lists.getInstance().getProjectList().addAll(tempList);

        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void loadTableProjectMember(){
        session = Connection.factory.openSession();

        try {
            tx = session.beginTransaction();
            List tempList = session.createQuery("FROM ProjectMember").list();
            for (Iterator iterator = tempList.iterator(); iterator.hasNext();){
                ProjectMember projectMember = (ProjectMember) iterator.next();
                System.out.println("Table [projectMember] Record: (ID)"+projectMember.getProjectMemberID() + " succesfully loaded");
            }
            tx.commit();
            Lists.getInstance().getProjectMemberList().clear();
            Lists.getInstance().getProjectMemberList().addAll(tempList);

        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void loadTableTask(){
        session = Connection.factory.openSession();

        try {
            tx = session.beginTransaction();
            List tempList = session.createQuery("FROM Task").list();
            for (Iterator iterator = tempList.iterator(); iterator.hasNext();){
                Task task = (Task) iterator.next();
                System.out.println("Table [tasks] Record: (ID)"+task.getTaskID() + " succesfully loaded");
            }
            tx.commit();
            Lists.getInstance().getTaskList().clear();
            Lists.getInstance().getTaskList().addAll(tempList);

        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void loadTableTaskGroup(){
        session = Connection.factory.openSession();

        try {
            tx = session.beginTransaction();
            List tempList = session.createQuery("FROM TaskGroup").list();
            for (Iterator iterator = tempList.iterator(); iterator.hasNext();){
                TaskGroup taskGroup = (TaskGroup) iterator.next();
                System.out.println("Table [taskGroups] Record: (ID)"+taskGroup.getTaskGroupID() + " succesfully loaded");
            }
            tx.commit();
            Lists.getInstance().getTaskGroupList().clear();
            Lists.getInstance().getTaskGroupList().addAll(tempList);

        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void loadTableUser(){
        session = Connection.factory.openSession();

        try {
            tx = session.beginTransaction();
            List tempList = session.createQuery("FROM User").list();
            for (Iterator iterator = tempList.iterator(); iterator.hasNext();){
                User user = (User) iterator.next();
                System.out.println("Table [users] Record: (ID)"+user.getUserID() + " succesfully loaded");
            }
            tx.commit();
            Lists.getInstance().getUserList().clear();
            Lists.getInstance().getUserList().addAll(tempList);

        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
