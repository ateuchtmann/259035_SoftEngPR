package controls;

import database.Connection;
import database.DatabaseUpdate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.CurrentSession;
import models.Lists;
import models.TaskGroup;
import resources.LoadDependences;
import views.AlertBox;
import views.PopupWindowFirst;
import views.PopupWindowSecond;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTaskGroupControl implements Initializable {
    //Set Variables
    int currentProjectID = CurrentSession.getInstance().getSessionProjectID();
    String taskGroupName = "";

    //SceneControls
    @FXML private TextField textFieldTaskGroupName;
    @FXML private Button buttonSubmit;
    @FXML private Button buttonCancel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Set SceneControls
        buttonCancel.setOnAction(event -> {
            close();
        });

        buttonSubmit.setOnAction(event -> {
            if(addTaskGroup()) {
                close();
            }
            else {
                AlertBox.display("Errormessage", "Empty Fields are not allowed!");
            }
        });
    }
    //Methods
    @FXML
    public boolean addTaskGroup(){

        if(textFieldTaskGroupName.getText().isEmpty()){
            return false;
        }
        else{
            //Set TaskGroupName
            taskGroupName=textFieldTaskGroupName.getText();

            TaskGroup newObject = new TaskGroup(taskGroupName, currentProjectID);

            //Save Object to the Database
            Connection.openConnection();
            DatabaseUpdate databaseUpdate = new DatabaseUpdate();
            databaseUpdate.insertObject(newObject);
            Connection.closeConnection();

            Lists.getInstance().getTaskGroupList().add(newObject);

            System.out.println("New TaskGroup createt: [ID]: " + newObject.getTaskGroupID()  + " ProjectID: "+ newObject.getProjectID());

            return true;
        }
    }

    @FXML
    public void clearScene() {
        //clear
        textFieldTaskGroupName.clear();
    }

    @FXML
    public void close() {
        clearScene();
        CurrentSession.getInstance().setMemoryNumber(2); //2 --> opens Tab TaskGroupSetting --> EditDeleteProjectScene
        PopupWindowSecond.getPopupWindow().close();
        PopupWindowFirst.getPopupWindow().setScene(LoadDependences.getInstance().loadScene("editDeleteProject"));
    }
}