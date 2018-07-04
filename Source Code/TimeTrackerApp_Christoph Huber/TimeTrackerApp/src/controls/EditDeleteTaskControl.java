package controls;

import database.Connection;
import database.DatabaseUpdate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.CurrentSession;
import models.Lists;
import models.ViewLists;
import resources.LoadDependences;
import views.AlertBox;
import views.ConfirmBox;
import views.PopupWindowFirst;

import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

public class EditDeleteTaskControl implements Initializable {

    //Set Variables
    private int currentUserID = CurrentSession.getInstance().getUserID();
    private int currentBusinessRoleID = CurrentSession.getInstance().getBusinessRoleID();
    private int currentTaskID = CurrentSession.getInstance().getSessionTaskID();
    private int currentTaskListIndex = Lists.getInstance().getTaskListIndex(currentTaskID);
    private int currentTaskProjectID = Lists.getInstance().getTaskList().get(currentTaskListIndex).getProjectID();
    private boolean invalidInput = false;
    private boolean exceptionSignal = false;

    //SceneControls
    @FXML private TextField textFieldTaskName;
    @FXML private TextField textFieldPlannedTimeHours;
    @FXML private TextField textFieldPlannedTimeMinutes;
    @FXML private TextArea textAreaDescription;
    @FXML private ChoiceBox<String> choiceBoxTaskOwner = new ChoiceBox<>();
    @FXML private ChoiceBox<String> choiceBoxTaskGroup = new ChoiceBox<>();
    @FXML private ChoiceBox<String> choiceBoxTaskState = new ChoiceBox<>();
    @FXML private RadioButton radioButtonDeleteTask;
    @FXML private Button buttonSubmit;
    @FXML private Button buttonCancel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Set SceneControls
        choiceBoxTaskOwner.getItems().addAll(ViewLists.getInstance().getOlProjectUserNameIDStrings(currentTaskProjectID));
        choiceBoxTaskGroup.getItems().addAll(ViewLists.getInstance().getOlProjectTaskGroupNameIDStrings(CurrentSession.getInstance().getSessionProjectID()));
        choiceBoxTaskState.getItems().addAll(ViewLists.getInstance().getOlTaskStateNameStrings());
        textAreaDescription.setWrapText(true);

        buttonCancel.setOnAction(event -> {
            close();
        });
        buttonSubmit.setOnAction(event -> {
            if(!(radioButtonDeleteTask.isSelected())) {
                if(editTask()){
                    close();
                }
                else {
                    if(invalidInput) {
                        AlertBox.display("Errormessage", "Invalid PlannedTime Values!");
                        invalidInput=false;
                    }
                    else if(exceptionSignal) {
                        AlertBox.display("Errormessage", "TaskOwner cannot change the TaskOwner, only ProjectManagers and Supervisors can!");
                        exceptionSignal=false;
                        setDefaults();
                    }
                    else {
                        AlertBox.display("Errormessage", "Empty Fields are not allowed!");
                    }
                }
            }
            else {
                if(deleteTask()) {
                    close();
                }
                else {
                    AlertBox.display("Notification", "No Action exectuted!");
                    setDefaults();
                }
            }
        });
        setDefaults();
    }

    //Methods
    @FXML
    public boolean editTask() {

        if(textFieldTaskName.getText().isEmpty() || textAreaDescription.getText().isEmpty() || textFieldPlannedTimeHours.getText().isEmpty() || textFieldPlannedTimeMinutes.getText().isEmpty()){
            return false;
        }
        if(choiceBoxTaskOwner.getSelectionModel().isEmpty() || choiceBoxTaskGroup.getSelectionModel().isEmpty() || choiceBoxTaskState.getSelectionModel().isEmpty()) {
            return false;
        }
        //Set Variables
        String taskName="";
        String description="";
        int taskStateID=0;
        int taskOwnerID=0;
        int taskGroupID=0;
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
        taskName = textFieldTaskName.getText();
        description = textAreaDescription.getText();
        taskStateID = Lists.getInstance().getTaskStateList().get(choiceBoxTaskState.getSelectionModel().getSelectedIndex()).getTaskStateID();
        taskOwnerID = Lists.getInstance().getUserIDFromUserNameIDString(choiceBoxTaskOwner.getSelectionModel().getSelectedItem());
        taskGroupID = Lists.getInstance().getTaskGroupIDFromTaskGroupNameIDString(choiceBoxTaskGroup.getSelectionModel().getSelectedItem());
        plannedTimeDuration = plannedTimeDuration.plusHours(plannedTimeHours);
        plannedTimeDuration = plannedTimeDuration.plusMinutes(plannedTimeMinutes);

        //Check if the Method try to change the TaskOwner, only ProjectManagers and Superusers can change the TaskOwner
        if((currentBusinessRoleID > 1 ) && (!Lists.getInstance().isProjectManager(currentUserID, currentTaskProjectID))) {
            if(Lists.getInstance().getTaskList().get(currentTaskListIndex).getTaskOwnerID() != taskOwnerID) { //If TaskOwner changed --> TaskOwner cannot change the TaskOwner
                exceptionSignal=true;
                return false;
            }
        }

        //Save Changes to the Database
        Connection.openConnection();
        DatabaseUpdate databaseUpdate = new DatabaseUpdate();
        databaseUpdate.updateLoadObject(Lists.getInstance().getTaskList().get(currentTaskListIndex), currentTaskID);

        Lists.getInstance().getTaskList().get(currentTaskListIndex).setTaskName(taskName);
        Lists.getInstance().getTaskList().get(currentTaskListIndex).setDescription(description);
        Lists.getInstance().getTaskList().get(currentTaskListIndex).setTaskStateID(taskStateID);
        Lists.getInstance().getTaskList().get(currentTaskListIndex).setTaskOwnerID(taskOwnerID);
        Lists.getInstance().getTaskList().get(currentTaskListIndex).setTaskGroupID(taskGroupID);
        Lists.getInstance().getTaskList().get(currentTaskListIndex).setPlannedTimeDuration(plannedTimeDuration);

        databaseUpdate.updateSaveObject(Lists.getInstance().getTaskList().get(currentTaskListIndex), currentTaskID);

        Connection.closeConnection();

        System.out.println("Task changed [ID]: " + currentTaskID);

        return true;
    }

    @FXML
    public boolean deleteTask(){
        if ((currentBusinessRoleID > 1 ) && (!Lists.getInstance().isProjectManager(currentUserID, currentTaskProjectID))) {
            AlertBox.display("Errormessage", "TaskOwners cannot delete a Task, only ProjectManagers and Supervisors can!");
            return false;
        }
        if (Lists.getInstance().getTaskActivityQuantity(currentTaskID) > 0) {
            AlertBox.display("Errormessage", "It's not allowed to delete a Task with Activities inside!");
            return false;
        }
        if (ConfirmBox.display("Confirm Action", "Delete Task: " + Lists.getInstance().getTaskList().get(currentTaskListIndex).getTaskName() + " ?")) {

            Connection.openConnection();
            DatabaseUpdate databaseUpdate = new DatabaseUpdate();
            databaseUpdate.deleteObject(Lists.getInstance().getTaskList().get(currentTaskListIndex), currentTaskID);

            //Delete from the TaskList
            Lists.getInstance().getTaskList().remove(Lists.getInstance().getTaskList().get(currentTaskListIndex));

            Connection.closeConnection();

            System.out.println("Task [ID]: " + currentTaskID + " deleted");
            return true;
        }
        else {
            return false;
        }
    }

    @FXML
    public void setDefaults() {
        //Set Current Taskinformation
        long plannedTimeHours=Lists.getInstance().getTaskList().get(currentTaskListIndex).getPlannedTimeDuration().toHours();
        long plannedTimeMinutes=Lists.getInstance().getTaskList().get(currentTaskListIndex).getPlannedTimeDuration().minusHours(plannedTimeHours).toMinutes();

        radioButtonDeleteTask.setSelected(false);
        textFieldTaskName.setText(Lists.getInstance().getTaskList().get(currentTaskListIndex).getTaskName());
        textFieldPlannedTimeHours.setText(String.valueOf(plannedTimeHours));
        textFieldPlannedTimeMinutes.setText(String.valueOf(plannedTimeMinutes));
        textAreaDescription.setText(Lists.getInstance().getTaskList().get(currentTaskListIndex).getDescription());
        choiceBoxTaskOwner.getSelectionModel().select(ViewLists.getInstance().getListIndexOlProjectUserNameIDStrings(Lists.getInstance().getTaskList().get(currentTaskListIndex).getTaskOwnerID()));
        choiceBoxTaskGroup.getSelectionModel().select(ViewLists.getInstance().getListIndexOlProjectTaskGroupNameIDStrings(Lists.getInstance().getTaskList().get(currentTaskListIndex).getTaskGroupID()));
        choiceBoxTaskState.getSelectionModel().select(Lists.getInstance().getTaskStateListIndex(Lists.getInstance().getTaskList().get(currentTaskListIndex).getTaskStateID()));

    }
    @FXML
    private void close() {
        CurrentSession.getInstance().setSessionTaskGroupID(0);
        PopupWindowFirst.getPopupWindow().close();
        LoadDependences.getInstance().openMainScene("projectDashboard");
    }
}