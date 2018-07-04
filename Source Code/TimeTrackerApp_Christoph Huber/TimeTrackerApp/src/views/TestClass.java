package views;
import database.Connection;
import database.DatabaseUpdate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import models.*;
import resources.LoadDependences;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ListIterator;

import static java.math.BigDecimal.ZERO;

public class TestClass {
    private static Duration duration;

    public static void main(String[] args) {
        //Activity testActivity = new Activity("TestAktivität","I soit echt amoi mehr schlofen^^","2018-06-17 20:00","2018-06-19 05:00",123,66);


        // use the session object to save Java object
        //create a object
/*

        String s = "HallÖ";
        System.out.println(s.toLowerCase());
        int a = 1;
        int b = 1;
        int erg = (int) ((double) a / b * 100);
        double x = 0;

        System.out.println(erg);

*/
        //LoadDependences.getInstance().startRoutine();

        long x = 3542;
        System.out.println(x);
        x=x/60;
        System.out.println(x);

        /*
        Lists.getInstance().getBusinessRoleList().clear();
        BusinessRole supervisor = new BusinessRole(1, "Supervisor");
        BusinessRole employee = new BusinessRole(2, "Employee");
        BusinessRole testUsers = new BusinessRole(3, "TestUser");
        Lists.getInstance().getBusinessRoleList().add(supervisor);
        Lists.getInstance().getBusinessRoleList().add(employee);
        Lists.getInstance().getBusinessRoleList().add(testUsers);
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("*********************[BusinessRole]: successfully created*******************");
        System.out.println("----------------------------------------------------------------------------");

        Connection.openConnection();
        for (int i = 1; i < 12; i++) {
            //System.out.println("Creating a new object");
            int currentProjectManager=(int)(Math.random() * 11) + 1;

            //BusinessRole tempBusinessRole = new BusinessRole("Supervisor");
            User tempUser = new User("Franz" + i, "Ortner"+ i, "ORFR"+ i, "pw4ORFR"+ i, 1);
            Project tempProject = new Project("TestProjectHibernate"+ i, "Das ist nur ein Testobject"+ i, currentProjectManager);
            ProjectMember tempProjectMember = new ProjectMember(i, i);
            //Task tempTask = new Task("HibernateInitialize"+ i, "Ob des nu wos wird bis murgn?"+ i, i, i, i, i, i);
            TaskGroup tempTaskGroup = new TaskGroup("HibernateRocksGroup", i);
            Activity tempActivity = new Activity("TestAktivität", "I soit echt amoi mehr schlofen^^", "2018-06-17 20:00", "2018-06-19 05:00", i, i);

            //System.out.println("Before DBCONN: USERID --> "+tempUser.getProjectID());

            DatabaseUpdate databaseUpdate = new DatabaseUpdate();

            for(int j=0;j<10;j++) { //Create 2 Tasks, 1 with state 1, 1 with state 2
                int currentTaskState=(int)(Math.random() * 2) + 1;
                Task tempTask = new Task("HibernateInitialize"+ i, "Ob des nu wos wird bis murgn?"+ i, currentTaskState, i, i, i, i);
                databaseUpdate.insertObject(tempTask);
            }

            databaseUpdate.insertObject(tempUser);
            databaseUpdate.insertObject(tempProject);
            databaseUpdate.insertObject(tempProjectMember);
            databaseUpdate.insertObject(tempTaskGroup);
            //databaseUpdate.insertObject(tempTask);
            databaseUpdate.insertObject(tempActivity);

        }
        Connection.closeConnection();
       /*
*/

       /*

           private void initializeBarChartProjectStatesTest(){
        ObservableList<Project> olIncompleteProjects = FXCollections.observableArrayList();
        olIncompleteProjects=ViewLists.getInstance().getOlIncompleteProjects();
        int currentProjectManagerID=0;
        int i=0;
        boolean deletedObject=false;

        while (i < olIncompleteProjects.size()) {
            currentProjectManagerID=olIncompleteProjects.get(i).getProjectManagerID();
            BarChart.Series<String, Integer> clIncompleteProjectsPM = new XYChart.Series<>();
            int j=i;
            deletedObject=false;

            while (j < olIncompleteProjects.size()) {
                if (olIncompleteProjects.get(j).getProjectManagerID()==currentProjectManagerID) {
                    clIncompleteProjectsPM.getData().add(new XYChart.Data<>(olIncompleteProjects.get(j).getProjectName(),olIncompleteProjects.get(j).getProgressState()));
                    olIncompleteProjects.remove(j);
                    deletedObject=true;
                    j--;
                }
                j++;
            }
            chartBarProjectStates.getData().add(clIncompleteProjectsPM);
            if(!deletedObject) i++;
        }
    }

        long plannedTimeSeconds=25*60 + 61;
        int plannedTimeMinutes=1980;

        Duration test = Duration.ofMinutes(plannedTimeSeconds);

        //System.out.println("Days: " + test.toDays() + " Hours: " + test.toHours() + " Minutes: " + test.toMinutes());
        System.out.println("Hours: " + test.toHours() + " Minutes: " + test.minusHours(test.toHours()).toMinutes());

        //test.plusMinutes(plannedTimeMinutes);
       // System.out.println("Days: " + test.toMinutes());

        //test=Duration.ofMinutes(0);
        //test=test.plusDays(3);
        //test=test.plusHours(3);
       // test=test.plusMinutes(118);
       // System.out.println("Days: " + test.toDays() + " Hours: " + test.minusDays(test.toDays()).toHours());
        //System.out.println(test.toString());


*/







        /*


        Activity tempActivity = new Activity("TestAktivität","I soit echt amoi mehr schlofen^^","2018-06-17 20:00","2018-06-19 05:00",1,1);
        DatabaseUpdate databaseUpdate = new DatabaseUpdate();
        //databaseUpdate.insertObject(tempActivity);

        System.out.println(tempActivity.getDuration());
        tempActivity.setEndTimeStamp("2018-06-17 22:00");
        System.out.println(tempActivity.getDuration());

        */
///*


/*
        User emptyUser = new User();

        //Connection.openConnection();
        DatabaseReceive databaseReceive = new DatabaseReceive();
        databaseReceive.loadDataFromDatabase();
        DatabaseUpdate databaseAction = new DatabaseUpdate();

        //databaseReceive.databaseObjectsToListTest(emptyUser);
        databaseReceive.loadDataFromDatabase();

        //Connection.closeConnection();


*/
/*
        Connection.openConnection();

        DatabaseReceive databaseReceive = new DatabaseReceive();

        Activity tempActivity = new Activity();
        BusinessRole tempBusinessRole = new BusinessRole();
        User tempUser = new User();
        Project tempProject = new Project();
        ProjectMember tempProjectMember = new ProjectMember();
        TaskGroup tempTaskGroup = new TaskGroup();
        Task tempTask=new Task();

        databaseReceive.databaseObjectsToList(tempActivity);
        databaseReceive.databaseObjectsToList(tempBusinessRole);
        databaseReceive.databaseObjectsToList(tempProject);
        databaseReceive.databaseObjectsToList(tempProjectMember);
        databaseReceive.databaseObjectsToList(tempTask);
        databaseReceive.databaseObjectsToList(tempTaskGroup);
        databaseReceive.databaseObjectsToList(tempUser);

        //databaseReceive.getUser();

        Connection.closeConnection();
        */

        //System.out.println(Lists.getInstance().getUserList().size());
//        System.out.println(Lists.getInstance().getUserList().get(1).getLastName());
        //      System.out.println(Lists.getInstance().getUserList().get(1).getLastName());
        //    System.out.println(Lists.getInstance().getUserList().get(2).getLastName());


        //Lists.getInstance().getActivityList().add(testActivity);
        //  System.out.println(Lists.getInstance().getActivityList().size());
        // System.out.println(Lists.getInstance().getActivityList().get(0).getDescription());

        /*
        BusinessRole tempBusinessRole = new BusinessRole("Supervisor");
        User tempUser = new User("Franz", "Ortner","ORFR","pw4ORFR",0);
        User tempUser1 = new User("Franz", "Ortner","ORFR","pw4ORFR",0);

        DatabaseUpdate databaseAction = new DatabaseUpdate();

        databaseAction.insertObject(tempUser);
        System.out.println("Object Created! ID: "+tempUser.getProjectID());

        //Session session = Connection.factory.getCurrentSession();
        //Transaction tx = null;

         /*

        databaseAction.updateLoadObject(tempUser,tempUser.getProjectID());


        tempUser.setFirstName("OIDA I HOFF JETZT GEHTS....");
        tempUser.setFirstName("I HOFF JETZT GEHTS....");
        tempUser.setLastName("I HOFF JETZT GEHTS....");
        tempUser.setPassword("MOOOO SALAHHHHHHH!");

        databaseAction.updateSaveObject(tempUser,tempUser.getProjectID());
        INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country)
        VALUES ('Cardinal', 'Tom B. Erichsen', 'Skagen 21', 'Stavanger', '4006', 'Norway');
        */

        /*

        try {
            tx = session.beginTransaction();

            session.load(tempUser,tempUser.getProjectID());

            tempUser.setFirstName("OIDA I HOFF JETZT GEHTS....");
            tempUser.setFirstName("I HOFF JETZT GEHTS....");
            tempUser.setLastName("I HOFF JETZT GEHTS....");

            session.flush();
            tx=session.getTransaction();

            tempUser = session.get(tempUser.getClass(), tempUser.getProjectID());
            session.update(tempUser);

            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

*/


        //tempBusinessRole.setBusinessRoleName("Wenn des jetzt klopppt!!!!!");

        //databaseAction.updateObject(tempBusinessRole,tempBusinessRole.getProjectID());
        //System.out.println("Object Updatet! ID: "+tempBusinessRole.getProjectID()+" " + tempBusinessRole.getBusinessRoleName());


        /*
        DatabaseDelete queryTest1 = new DatabaseDelete();
        queryTest1.deleteObject(tempBusinessRole, tempBusinessRole.getProjectID());
        System.out.println("Object Deleted! ID: ");

        databaseAction.insertObject(tempUser);
        System.out.println("Object Created! ID: "+tempBusinessRole.getProjectID());

        databaseAction.insertObject(tempUser1);
        System.out.println("Object Created! ID: "+tempBusinessRole.getProjectID());

        queryTest1.deleteObject(tempUser, tempUser.getProjectID());
        System.out.println("Object Deleted! ID: ");

        */




        /*
        Time time = new Time();

        String testString="2018-06-19 04:25";
        LocalDateTime testStamp;
        testStamp = LocalDateTime.of(2018,05,16,06,45);

        System.out.println("STRING: " + testString);
        System.out.println("TO DATETIME: " + time.parseStringToDateTime(testString));

        System.out.println("DATETIME: " + testStamp);
        System.out.println("TO STRING: " + time.parseDateTimeToString(testStamp));

        */

         /*// TIMECLASS JAVA!!!!!
        LocalDateTime dateTime = LocalDateTime.of(2015, 12, 28, 13, 37);
        LocalTime test2 = LocalTime.of(24, 62);
        LocalTime test3 = LocalTime.of(12, 17);

        LocalDateTime test5 = LocalDateTime.of(2015, 11, 30, 12, 12);
        LocalDateTime test6 = LocalDateTime.of(2015, 12, 01, 12, 12);

        LocalDate testDate = LocalDate.of(2018, 10, 11);
        LocalTime testTime = LocalTime.of(18, 11);
        LocalDateTime testDateTime = LocalDateTime.of(testDate, testTime);

        System.out.println("LOCALDATETIME: " + testDateTime);

        Instant now;
        System.out.println(test2);
        System.out.println(test3.getHour());
        System.out.println(test3.getMinute());

        System.out.println(test2.compareTo(test3));
        System.out.println(now = Instant.now());

        String asIsoDateTime = dateTime.format(DateTimeFormatter.BASIC_ISO_DATE);
        String asIsoDateTime1 = dateTime.format(DateTimeFormatter.ISO_DATE_TIME);

        System.out.println(asIsoDateTime1);
        System.out.println(Duration.between(test2, test3).

                toMinutes());
        System.out.println(Duration.between(test5, test6).
toMinutes());


          */

    }
}



