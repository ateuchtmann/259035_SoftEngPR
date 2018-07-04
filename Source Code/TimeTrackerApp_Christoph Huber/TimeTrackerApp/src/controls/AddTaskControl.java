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
import java.time.Duration;
import java.util.ResourceBundle;

public class AddTaskControl implements Initializable {

    //Set Variables
    private boolean invalidInput = false;

    //SceneControls
    @FXML private TextField textFieldTaskName;
    @FXML private TextField textFieldPlannedTimeHours;
    @FXML private TextField textFieldPlannedTimeMinutes;
    @FXML private TextArea textAreaDescription;
    @FXML private ChoiceBox<String> choiceBoxTaskOwner = new ChoiceBox<>();
    @FXML private ChoiceBox<String> choiceBoxTaskGroup = new ChoiceBox<>();
    @FXML private Button buttonSubmit;
    @FXML private Button buttonCancel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Set SceneControls
        choiceBoxTaskOwner.getItems().addAll(ViewLists.getInstance().getOlProjectUserNameIDStrings(CurrentSession.getInstance().getSessionProjectID()));
        choiceBoxTaskGroup.getItems().addAll(ViewLists.getInstance().getOlProjectTaskGroupNameIDStrings(CurrentSession.getInstance().getSessionProjectID()));
        textAreaDescription.setWrapText(true);

        buttonCancel.setOnAction(event -> {
            close();
        });
        buttonSubmit.setOnAction(event -> {
            if(addTask()) {
                close();
            }
            else {
                if(invalidInput) {
                    AlertBox.display("Errormessage", "Invalid PlannedTime Values!");
                    invalidInput=false;
                }
                else {
                    AlertBox.display("Errormessage", "Empty Fields are not allowed!");
                }
            }
        });
    }
    //Methods
    @FXML
    public boolean addTask(){

        if(textFieldTaskName.getText().isEmpty() || textAreaDescription.getText().isEmpty() || textFieldPlannedTimeHours.getText().isEmpty() || textFieldPlannedTimeMinutes.getText().isEmpty()){
            return false;
        }
        if(choiceBoxTaskOwner.getSelectionModel().isEmpty() || choiceBoxTaskGroup.getSelectionModel().isEmpty()) {
            return false;
        }

        //Set Variables
        String taskName="";
        String description="";
        int taskStateID=1; //Defaultvalue == assigned
        int projectID=CurrentSession.getInstance().getSessionProjectID();
        int taskGroupID=0;
        int taskOwnerID=0;
        long plannedTimeHours;
        long plannedTimeMinutes;
        Duration plannedTimeDuration=Duration.ofMinutes(0);

        //Try to Parse PlannedTime StringInputs to Long
        try{
            plannedTimeHours = Long.parseLong(textFieldPlannedTimeHours.getText());
            plannedTimeMinutes = Long.parseLong(textFieldPlannedTimeMinutes.getText());
        }
        catch(NumberFormatException nfe) {
            invalidInput=true;
            return false;
        }

        //Set Taskinformation
        taskName=textFieldTaskName.getText();
        description=textAreaDescription.getText();
        taskOwnerID = Lists.getInstance().getUserIDFromUserNameIDString(choiceBoxTaskOwner.getSelectionModel().getSelectedItem());
        taskGroupID = Lists.getInstance().getTaskGroupIDFromTaskGroupNameIDString(choiceBoxTaskGroup.getSelectionModel().getSelectedItem());
        plannedTimeDuration = plannedTimeDuration.plusHours(plannedTimeHours);
        plannedTimeDuration = plannedTimeDuration.plusMinutes(plannedTimeMinutes);

        Task newObject = new Task(taskName,description,taskStateID,projectID,taskGroupID,taskOwnerID,plannedTimeDuration);

        //Save Object to the Database
        Connection.openConnection();
        DatabaseUpdate databaseUpdate = new DatabaseUpdate();
        databaseUpdate.insertObject(newObject);
        Connection.closeConnection();

        Lists.getInstance().getTaskList().add(newObject);

        System.out.println("New Task createt: [ID]: " + newObject.getTaskID()  + " TaskName: "+ newObject.getTaskName());

        return true;
    }

    @FXML
    public void clearScene() {
        //clear
        textFieldTaskName.clear();
        textAreaDescription.clear();
        textFieldPlannedTimeHours.clear();
        textFieldPlannedTimeMinutes.clear();
        choiceBoxTaskOwner.getSelectionModel().clearSelection();
        choiceBoxTaskGroup.getSelectionModel().clearSelection();
    }

    @FXML
    public void close() {
        clearScene();
        PopupWindowFirst.getPopupWindow().close();
        LoadDependences.getInstance().openMainScene("projectDashboard");
    }
}