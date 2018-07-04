package resources;

import database.Connection;
import database.DatabaseReceive;
import database.DatabaseUpdate;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import models.*;
import views.AlertBox;
import views.Main;

public class LoadDependences {
    private static LoadDependences instance;
    private Scene currentScene;
    private Parent currentLayout;

    //Constructor
    public LoadDependences(){
    }

    //Methods
    public void startRoutine() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("********************Starting the Startroutine*******************************");
        System.out.println("----------------------------------------------------------------------------");

        //Load Database
        DatabaseReceive databaseReceive = new DatabaseReceive();
        databaseReceive.loadDataFromDatabase();

        //Add BusinessRoleElements
        Lists.getInstance().getBusinessRoleList().clear();
        BusinessRole supervisor = new BusinessRole(1,"Supervisor");
        BusinessRole employee = new BusinessRole(2,"Employee");
        BusinessRole testUsers = new BusinessRole(3,"TestUser");
        Lists.getInstance().getBusinessRoleList().add(supervisor);
        Lists.getInstance().getBusinessRoleList().add(employee);
        Lists.getInstance().getBusinessRoleList().add(testUsers);
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("*********************[BusinessRole]: successfully created*******************");
        System.out.println("----------------------------------------------------------------------------");

        //Update ActivityDuration
        Lists.getInstance().initializeAllActivityDurations();
        Lists.getInstance().initializeAllTaskPlannedTimeDurations();
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("*****************[Activitiy] & [Task]: Durations updated********************");
        System.out.println("----------------------------------------------------------------------------");

        //Add TaskStateElements
        Lists.getInstance().getTaskStateList().clear();
        TaskState assigned = new TaskState(1,"assigned");
        TaskState completed = new TaskState(2,"completed");
        Lists.getInstance().getTaskStateList().add(assigned);
        Lists.getInstance().getTaskStateList().add(completed);
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("*************************[Task]: States successfully created****************");
        System.out.println("----------------------------------------------------------------------------");

        //User exists?
        if(Lists.getInstance().getUserList().isEmpty()) {
            User admin = new User("John", "Doe","admin","admin",1);
            User user = new User("Jane", "Doe","user","user",2);

            DatabaseUpdate databaseUpdate = new DatabaseUpdate();
            Connection.openConnection();
            databaseUpdate.insertObject(admin);
            databaseUpdate.insertObject(user);
            Connection.closeConnection();

            Lists.getInstance().getUserList().add(admin);
            Lists.getInstance().getUserList().add(user);

            System.out.println("----------------------------------------------------------------------------");
            System.out.println("********************DefaultUser successfully created! **********************");
            System.out.println("*********[Username:'" + admin.getUsername() + "', Password:'"+admin.getPassword()+"', Permissions: Supervisor]******");
            System.out.println("*********[Username:'"+user.getUsername()+"', Password:'"+user.getPassword()+"', Permissions: Employee]**********");
            System.out.println("----------------------------------------------------------------------------");
        }


        System.out.println("----------------------------------------------------------------------------");
        System.out.println("***************************Startroutine completed***************************");
        System.out.println("----------------------------------------------------------------------------");
    }

    public Scene loadScene(String sceneName) {
        sceneName= References.getInstance().getFxmlPath() + sceneName + ".fxml";
        try{
            currentLayout=FXMLLoader.load(getClass().getResource(sceneName));
            currentScene= new Scene(currentLayout);
        }
        catch (Exception ex){
            AlertBox.display("Errormessage", "An error has occurred");
            System.out.println(ex);
        }
        return currentScene;
    }

    //Open and Switch MainScens
    public void openMainScene(String sceneName) {
        switch (sceneName) {
            case "login":
                Main.getWindow().setScene(loadScene(sceneName));
                break;

            case "dashboard":
                Main.getWindow().setScene(loadScene(sceneName));
                break;

            case "userManagement":
                Main.getWindow().setScene(loadScene(sceneName));
                break;

            case "projects":
                Main.getWindow().setScene(loadScene(sceneName));
                break;

            case "projectDashboard":
                Main.getWindow().setScene(loadScene(sceneName));
                break;

            case "taskDashboard":
                Main.getWindow().setScene(loadScene(sceneName));
                break;
        }
    }

    //Open and Switch First PopUpScene
    public Scene openFirstPopupScene(String sceneName) {

        switch (sceneName) {
            case "addProject":
                return loadScene(sceneName);

            case "addUser":
                return loadScene(sceneName);

            case "addTask":
                return loadScene(sceneName);

            case "editDeleteProject":
                return loadScene(sceneName);

            case "editDeleteUser":
                return loadScene(sceneName);

            case "editDeleteTask":
                return loadScene(sceneName);

            case "editDeleteActivity":
                return loadScene(sceneName);

            case "addActivity":
                return loadScene(sceneName);
        }
        return null;
    }

    //Open and Switch Second PopupScene
    public Scene openSecondPopupScene(String sceneName) {

        switch (sceneName) {

            case "addTaskGroup":
                return loadScene(sceneName);

            case "editDeleteTaskGroup":
                return loadScene(sceneName);
        }
        return null;
    }

    //Getter

    //Create an Instance of this Class
    public static LoadDependences getInstance(){
        if (instance == null){
            instance = new LoadDependences();
        }
        return instance;
    }

}