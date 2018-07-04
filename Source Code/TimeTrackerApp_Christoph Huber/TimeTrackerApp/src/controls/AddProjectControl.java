package controls;

import database.Connection;
import database.DatabaseUpdate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.*;
import resources.LoadDependences;
import views.AlertBox;
import views.PopupWindowFirst;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProjectControl implements Initializable {

    //SceneControls
    @FXML private TextField textFieldProjectName;
    @FXML private TextArea textAreaDescription;
    @FXML private ChoiceBox<String> choiceBoxProjectManager = new ChoiceBox<>();
    @FXML private Button buttonSubmit;
    @FXML private Button buttonCancel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Set SceneControls
        choiceBoxProjectManager.getItems().addAll(ViewLists.getInstance().getOlUserNameIDStrings());
        textAreaDescription.setWrapText(true);

        buttonCancel.setOnAction(event -> {
            close();
        });

        buttonSubmit.setOnAction(event -> {
            if(addProject()) {
                close();
            }
            else {
                AlertBox.display("Errormessage", "Empty Fields are not allowed!");
            }
        });
    }
    //Methods
    @FXML
    public boolean addProject(){

        if(textFieldProjectName.getText().isEmpty() || textAreaDescription.getText().isEmpty() || choiceBoxProjectManager.getSelectionModel().isEmpty()){
            return false;
        }
        else{
            //Set Variables
            String projectName="";
            String description="";
            int projectManagerID=0;

            //Set Projectinformation
            projectName=textFieldProjectName.getText();
            description=textAreaDescription.getText();
            projectManagerID= Lists.getInstance().getUserList().get(choiceBoxProjectManager.getSelectionModel().getSelectedIndex()).getUserID();

            Project newObject = new Project(projectName,description,projectManagerID);

            //Save Object to the Database
            Connection.openConnection();

            DatabaseUpdate databaseUpdate = new DatabaseUpdate();
            databaseUpdate.insertObject(newObject);

                //Add DefaultTaskGroup to the Project
            TaskGroup newDefaultTaskGroup = new TaskGroup("DefaultTaskGroup",newObject.getProjectID());
            databaseUpdate.insertObject(newDefaultTaskGroup);

            Connection.closeConnection();

            Lists.getInstance().getProjectList().add(newObject);
            Lists.getInstance().getTaskGroupList().add(newDefaultTaskGroup);

            System.out.println("New Project createt: [ID]: " + newObject.getProjectID()  + " ProjectName: "+ newObject.getProjectName());

            return true;
        }
    }

    @FXML
    public void clearScene() {
        //clear
        textFieldProjectName.clear();
        textAreaDescription.clear();
        choiceBoxProjectManager.getSelectionModel().clearSelection();
    }

    @FXML
    public void close() {
        clearScene();
        PopupWindowFirst.getPopupWindow().close();
        LoadDependences.getInstance().openMainScene("projects");
    }
}