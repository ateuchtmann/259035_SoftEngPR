package controls;

import database.Connection;
import database.DatabaseUpdate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import models.Activity;
import models.CurrentSession;
import models.Lists;
import models.ViewLists;
import resources.LoadDependences;
import views.AlertBox;
import views.ConfirmBox;
import views.PopupWindowFirst;

import java.net.URL;
import java.time.*;
import java.util.ResourceBundle;

public class EditDeleteActivityControl implements Initializable {
    //Set Variables
    private boolean invalidInput = false;
    private int currentUserID = CurrentSession.getInstance().getUserID();
    private int currentTaskID = CurrentSession.getInstance().getSessionTaskID();
    private int currentTaskListIndex = Lists.getInstance().getTaskListIndex(currentTaskID);
    private int currentProjectID = Lists.getInstance().getTaskList().get(currentTaskListIndex).getProjectID();
    private int currentActivityID = CurrentSession.getInstance().getSessionActivitiyID();
    private int currentActivityListIndex = Lists.getInstance().getActivityListIndex(currentActivityID);

    //SceneControls
    @FXML private TextField textFieldActivityName;
    @FXML private DatePicker datePickerStartDate;
    @FXML private TextField textFieldStartTimeHours;
    @FXML private TextField textFieldStartTimeMinutes;
    @FXML private DatePicker datePickerEndDate;
    @FXML private TextField textFieldEndTimeHours;
    @FXML private TextField textFieldEndTimeMinutes;
    @FXML private TextField textFieldActivityDuration;
    @FXML private ChoiceBox<String> choiceBoxActivityOwner = new ChoiceBox<>();
    @FXML private TextArea textAreaDescription;
    @FXML private Button buttonSubmit;
    @FXML private Button buttonCancel;
    @FXML private RadioButton radioButtonDeleteActivity;
    @FXML private VBox vboxFirst;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set SceneControls
        choiceBoxActivityOwner.getItems().addAll(ViewLists.getInstance().getOlProjectUserNameIDStrings(currentProjectID));
        textAreaDescription.setWrapText(true);
        buttonCancel.setOnAction(event -> {
            close();
        });
        buttonSubmit.setOnAction(event -> {
            if(!(radioButtonDeleteActivity.isSelected())) {
                if(editActivity()){
                    close();
                }
                else {
                    if(invalidInput) {
                        AlertBox.display("Errormessage", "Invalid Time Values!");
                        setTimeDefaults();
                    }
                    else {
                        AlertBox.display("Errormessage", "Empty Fields are not allowed!");
                    }
                }
            }
            else {
                if(deleteActivity()) {
                    close();
                }
                else {
                    AlertBox.display("Notification", "No Action exectuted!");
                    setDefaults();
                }
            }
        });

        //Calculate Duration on Action
        vboxFirst.setOnMouseClicked(event -> {
            if(!refreshDuration()) {
                setTimeDefaults();
            }
        });

        //Load Defaults
        setDefaults();
        //Set UserPermissions
        setUserPermissions();
    }
    //Methods
    @FXML
    public boolean editActivity(){
        invalidInput=false;

        if(datePickerStartDate.getValue()==null && datePickerEndDate.getValue()==null) {
            return false;
        }

        if (textFieldStartTimeHours.getText().isEmpty() && textFieldStartTimeMinutes.getText().isEmpty() && textFieldEndTimeHours.getText().isEmpty() && textFieldEndTimeMinutes.getText().isEmpty()) {
            return false;
        }

        if(textFieldActivityName.getText().isEmpty() || choiceBoxActivityOwner.getSelectionModel().isEmpty() || textAreaDescription.getText().isEmpty()) {
            return false;
        }

        //Set Variables
        String activityName="";
        String description="";
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        int activityOwnerID;

        //Set ActivityInformations
        activityName=textFieldActivityName.getText();
        description=textAreaDescription.getText();
        activityOwnerID = Lists.getInstance().getUserIDFromUserNameIDString(choiceBoxActivityOwner.getSelectionModel().getSelectedItem());
        startDateTime=calculateStartDateTime();
        endDateTime=calculateEndDateTime();

        //Check if Date and TimeInput is correct!
        if(invalidInput || (!(endDateTime.isAfter(startDateTime)))) {
            invalidInput=true;
            return false;
        }

        //Save Changes to the Database
        Connection.openConnection();
        DatabaseUpdate databaseUpdate = new DatabaseUpdate();
        databaseUpdate.updateLoadObject(Lists.getInstance().getActivityList().get(currentActivityListIndex), currentActivityID);

        Lists.getInstance().getActivityList().get(currentActivityListIndex).setActivityName(activityName);
        Lists.getInstance().getActivityList().get(currentActivityListIndex).setDescription(description);
        Lists.getInstance().getActivityList().get(currentActivityListIndex).setUserID(activityOwnerID);
        Lists.getInstance().getActivityList().get(currentActivityListIndex).setStartDateTime(startDateTime);
        Lists.getInstance().getActivityList().get(currentActivityListIndex).setEndDateTime(endDateTime);

        databaseUpdate.updateSaveObject(Lists.getInstance().getActivityList().get(currentActivityListIndex), currentActivityID);

        Connection.closeConnection();

        System.out.println("Activity changed [ID]: " + currentActivityID);
        return true;
    }

    @FXML
    public boolean deleteActivity(){
        if (ConfirmBox.display("Confirm Action", "Delete Activity: " + Lists.getInstance().getActivityList().get(currentActivityListIndex).getActivityName() + " ?")) {

            Connection.openConnection();
            DatabaseUpdate databaseUpdate = new DatabaseUpdate();
            databaseUpdate.deleteObject(Lists.getInstance().getActivityList().get(currentActivityListIndex), currentActivityID);

            //Delete from the ActivityList
            Lists.getInstance().getActivityList().remove(Lists.getInstance().getActivityList().get(currentActivityListIndex));

            Connection.closeConnection();

            System.out.println("Activity [ID]: " + currentActivityID + " deleted");
            return true;
        }
        else {
            return false;
        }
    }

    @FXML
    public LocalDateTime calculateStartDateTime(){
        LocalDate startDate=LocalDate.of(2000,1,1);
        LocalTime startTime=LocalTime.of(0,0);
        LocalDateTime startDateTime=LocalDateTime.of(startDate,startTime);

        int inputHours=parseTimeStringToInteger(textFieldStartTimeHours.getText());
        int inputMinutes=parseTimeStringToInteger(textFieldStartTimeMinutes.getText());
        //invalidInput=false;

        try{
            startTime=LocalTime.of(inputHours,inputMinutes);
        }
        catch(DateTimeException dte) {
            invalidInput=true;
        }
        try{
            startDate=datePickerStartDate.getValue();
        }
        catch(NullPointerException npe) {
            invalidInput=true;
        }
        if(!invalidInput) {
            startDateTime=LocalDateTime.of(startDate,startTime);
        }

        //System.out.println("StartDate: " + Lists.getInstance().parseDateTimeToString(startDateTime));
        //System.out.println("StartDate: "+invalidInput);
        return startDateTime;
    }

    @FXML
    public LocalDateTime calculateEndDateTime(){
        LocalDate endDate=LocalDate.of(2000,1,1);
        LocalTime endTime=LocalTime.of(0,0);
        LocalDateTime endDateTime=LocalDateTime.of(endDate,endTime);

        int inputHours=parseTimeStringToInteger(textFieldEndTimeHours.getText());
        int inputMinutes=parseTimeStringToInteger(textFieldEndTimeMinutes.getText());
        //invalidInput=false;

        try{
            endTime=LocalTime.of(inputHours,inputMinutes);
        }
        catch(DateTimeException dte) {
            invalidInput=true;
        }
        try{
            endDate=datePickerEndDate.getValue();
        }
        catch(NullPointerException npe) {
            invalidInput=true;
        }
        if(!invalidInput) {
            endDateTime=LocalDateTime.of(endDate,endTime);
        }

        //System.out.println("EndDate: "+Lists.getInstance().parseDateTimeToString(endDateTime));
        //System.out.println("EndDate: "+invalidInput);
        return endDateTime;
    }

    @FXML
    public boolean refreshDuration() {
        try{
            if(!(datePickerStartDate.getValue()==null || datePickerEndDate.getValue()==null)) {
                if(!(textFieldStartTimeHours.getText().isEmpty() || textFieldStartTimeMinutes.getText().isEmpty() || textFieldEndTimeHours.getText().isEmpty() || textFieldEndTimeMinutes.getText().isEmpty())) {
                    long duration= Duration.between(calculateStartDateTime(),calculateEndDateTime()).toMinutes();
                    if(duration<=0 || invalidInput) {
                        invalidInput=true;
                        return false;
                    }
                    else {
                        textFieldActivityDuration.setText(Lists.getInstance().parseDurationToString(duration));
                    }
                }
            }
        }
        catch(NullPointerException npe) {
            System.out.println(npe);
            return false;
        }
        return true;
    }

    public int parseTimeStringToInteger(String timeString) {
        int parseTime;
        try{
            parseTime = Integer.parseInt(timeString);
        }
        catch(NumberFormatException nfe) {
            return -1;
        }
        return parseTime;
    }

    @FXML
    public void setDefaults() {
        //Set Variables
        int currentActivitiyOwner=Lists.getInstance().getActivityList().get(currentActivityListIndex).getUserID();
        //Set Fields
        textFieldActivityName.setText(Lists.getInstance().getActivityList().get(currentActivityListIndex).getActivityName());
        textAreaDescription.setText(Lists.getInstance().getActivityList().get(currentActivityListIndex).getDescription());
        choiceBoxActivityOwner.getSelectionModel().select(ViewLists.getInstance().getListIndexOlProjectUserNameIDStrings(currentActivitiyOwner));
        radioButtonDeleteActivity.setSelected(false);
        setTimeDefaults();
    }

    @FXML
    public void setTimeDefaults() {
        //Set Variables
        LocalDateTime startDateTime=Lists.getInstance().getActivityList().get(currentActivityListIndex).getStartDateTime();
        LocalDateTime endDateTime=Lists.getInstance().getActivityList().get(currentActivityListIndex).getEndDateTime();
        LocalDate startDate=startDateTime.toLocalDate();
        LocalDate endDate=endDateTime.toLocalDate();
        //Set Fields
        datePickerStartDate.setValue(startDate);
        textFieldStartTimeHours.setText(String.valueOf(startDateTime.getHour()));
        textFieldStartTimeMinutes.setText(String.valueOf(startDateTime.getMinute()));
        datePickerEndDate.setValue(endDate);
        textFieldEndTimeHours.setText(String.valueOf(endDateTime.getHour()));
        textFieldEndTimeMinutes.setText(String.valueOf(endDateTime.getMinute()));
        invalidInput = false;
    }

    @FXML
    public void close() {
        setDefaults();
        PopupWindowFirst.getPopupWindow().close();
        LoadDependences.getInstance().openMainScene("taskDashboard");
    }

    private void setUserPermissions(){
        if(CurrentSession.getInstance().getBusinessRoleID()>1 && (!Lists.getInstance().isProjectManager(currentUserID,currentProjectID))) {
            choiceBoxActivityOwner.setDisable(true);
            if(Lists.getInstance().getTaskList().get(currentTaskListIndex).getTaskOwnerID() != currentUserID) {
                if(Lists.getInstance().getActivityList().get(currentActivityListIndex).getUserID() != currentUserID){
                    radioButtonDeleteActivity.setDisable(true);
                }
            }
        }
    }
}