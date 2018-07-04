package controls;

import database.Connection;
import database.DatabaseUpdate;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;
import resources.LoadDependences;
import views.AlertBox;
import views.ConfirmBox;
import views.PopupWindowFirst;
import views.PopupWindowSecond;

import java.net.URL;
import java.util.ResourceBundle;

public class EditDeleteProjectControl implements Initializable {
    //Set Variables
    //General
    private int currentUserID = CurrentSession.getInstance().getUserID();
    private int currentProjectID = CurrentSession.getInstance().getSessionProjectID();
    private int currentProjectListIndex = Lists.getInstance().getProjectListIndex(currentProjectID);
    private int currentBusinessRoleLevel = CurrentSession.getInstance().getBusinessRoleID();
    private int currentProjectMemberListIndex;
    //TabProjectSettings
    private String projectName = "";
    private String description = "";
    private int projectManagerID;
    private boolean exceptionSignal = false;
    private int selectedUserID;
    private String selectedValue;

    //SceneControls
    //Main
    @FXML private TabPane tabPaneEditDeleteProject;

    //Tab Project Settings
    @FXML private Button buttonProjectSubmit;
    @FXML private Button buttonProjectCancel;
    @FXML private TextField textFieldProjectName;
    @FXML private TextArea textAreaDescription;
    @FXML private ChoiceBox<String> choiceBoxProjectManager = new ChoiceBox<>();
    @FXML private RadioButton radioButtonDeleteProject;

    //Tab User Settings
    @FXML private TableView tableAllUsers;
    @FXML private TableColumn<String, String> tableAllUsersC1;
    @FXML private TableView tableCurrentProjectMember;
    @FXML private TableColumn<String, String> tableCurrentProjectMemberC1;
    @FXML private Button buttonAddUser;
    @FXML private Button buttonRemoveUser;

    //Tab TaskGroup Settings
    @FXML private TableView <TaskGroup> tableTaskGroupSettings;
    @FXML private TableColumn <TaskGroup, String> tableTaskGroupSettingsC1;
    @FXML private TableColumn <TaskGroup, Integer> tableTaskGroupSettingsC2;
    @FXML private Button buttonAddTaskGroup;
    @FXML private Button buttonEditDeleteTaskGroup;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Open specific Tab --> important for the Refreshfunction from other Scenes
        SingleSelectionModel<Tab> selectionModel = tabPaneEditDeleteProject.getSelectionModel();
        selectionModel.select(CurrentSession.getInstance().getMemoryNumber());
            //ResetMemoryNumber
        CurrentSession.getInstance().setMemoryNumber(0);

        //Set SceneControls
        //Tab Project Settings
        choiceBoxProjectManager.getItems().addAll(ViewLists.getInstance().getOlUserNameIDStrings());
        textAreaDescription.setWrapText(true);
        radioButtonDeleteProject.setSelected(false);
        buttonProjectCancel.setOnAction(event -> {
            close();
        });
        buttonProjectSubmit.setOnAction(event -> {
            if (!(radioButtonDeleteProject.isSelected())) {
                if (editProject()) {
                    AlertBox.display("Notification", "Projectinformation successfully changed!");
                    refreshUserSettingsTables();
                } else {
                    if (!exceptionSignal) {
                        AlertBox.display("Errormessage", "Empty Fields are not allowed!");
                    }
                }
            } else {
                if (deleteProject()) {
                    AlertBox.display("Notification", "Project successfully deleted!");
                    close();
                } else {
                    AlertBox.display("Notification", "No Action exectuted!");
                    setDefaultTabProjectSettings();
                }
            }
        });
            //Set Default Project Information
        setDefaultTabProjectSettings();

        //Tab User Settings
        buttonAddUser.setOnAction(event -> {
            if (addProjectMember()) {
                refreshUserSettingsTables();
            }
            else {
                AlertBox.display("Errormessage", "No User selected!");
            }
        });
        buttonRemoveUser.setOnAction(event -> {
            if (deleteProjectMember()) {
                refreshUserSettingsTables();
            }
            else {
                AlertBox.display("Errormessage", "No User selected!");
            }
        });
            //Load Elements
        initializeTableAllUsers();
        initializeTableProjectMember();

        //Tab TaskGroup Settings
        buttonAddTaskGroup.setOnAction(event -> {
            PopupWindowSecond.display("addTaskGroup","Add Taskgroup");
        });
        buttonEditDeleteTaskGroup.setOnAction(event -> {
            if(!(tableTaskGroupSettings.getSelectionModel().isEmpty())) {
                CurrentSession.getInstance().setSessionTaskGroupID(tableTaskGroupSettings.getSelectionModel().getSelectedItem().getTaskGroupID());
                //System.out.println(CurrentSession.getInstance().getSessionTaskGroupID());
                PopupWindowSecond.display("editDeleteTaskGroup","Edit or Delete Taskgroup");
            }
            else {
                AlertBox.display("Errormessage", "No TaskGroup selected!");
            }
        });
            //Load Elements
        initializeTableTaskGroupSettings();
    }

    //Methods
    @FXML
    public boolean editProject() {

        if (textFieldProjectName.getText().isEmpty() || textAreaDescription.getText().isEmpty() || choiceBoxProjectManager.getSelectionModel().isEmpty()) {
            return false;
        } else {
            //Set Projectinformation
            projectName = textFieldProjectName.getText();
            description = textAreaDescription.getText();
            projectManagerID = Lists.getInstance().getUserList().get(choiceBoxProjectManager.getSelectionModel().getSelectedIndex()).getUserID();

            if ((Lists.getInstance().getProjectList().get(currentProjectListIndex).getProjectManagerID() != projectManagerID) && (currentBusinessRoleLevel > 1)) {
                AlertBox.display("Errormessage", "ProjectManagers cannot change the ProjectManager, only supervisors can!");
                exceptionSignal = true;
                setDefaultTabProjectSettings();
                return false;
            }

            //Save Changes to the Database && Remove the new ProjectManager from the Projectmembers
            Connection.openConnection();
            DatabaseUpdate databaseUpdate = new DatabaseUpdate();

            //Check if new ProjectManager is already a Projectmember
            currentProjectMemberListIndex = Lists.getInstance().getProjectMemberIndexFromUserID(projectManagerID, currentProjectID);
            if(currentProjectMemberListIndex!=-1) {
                //delete from the Database
                databaseUpdate.deleteObject(Lists.getInstance().getProjectMemberList().get(currentProjectMemberListIndex),Lists.getInstance().getProjectMemberList().get(currentProjectMemberListIndex).getProjectMemberID());
                //delete from the ProjectMemberList
                Lists.getInstance().getProjectMemberList().remove(currentProjectMemberListIndex);
            }

            databaseUpdate.updateLoadObject(Lists.getInstance().getProjectList().get(currentProjectListIndex), currentProjectID);

            Lists.getInstance().getProjectList().get(currentProjectListIndex).setProjectName(projectName);
            Lists.getInstance().getProjectList().get(currentProjectListIndex).setDescription(description);
            Lists.getInstance().getProjectList().get(currentProjectListIndex).setProjectManagerID(projectManagerID);

            databaseUpdate.updateSaveObject(Lists.getInstance().getProjectList().get(currentProjectListIndex), currentProjectID);
            Connection.closeConnection();

            System.out.println("Project [ID]: " + currentProjectID + " changed");

            return true;
        }
    }

    @FXML
    public boolean deleteProject() {

        if (currentBusinessRoleLevel > 1) {
            AlertBox.display("Errormessage", "ProjectManagers cannot delete a Project, only supervisors can!");
            return false;
        }
        if (Lists.getInstance().getProjectTaskQuantity(currentProjectID) > 0) {
            AlertBox.display("Errormessage", "It's not allowed to delete a Project with Tasks inside!");
            return false;
        }
        if (ConfirmBox.display("Confirm Action", "Delete Project: " + Lists.getInstance().getProjectList().get(currentProjectListIndex).getProjectName() + " ?")) {

            Connection.openConnection();
            DatabaseUpdate databaseUpdate = new DatabaseUpdate();
            databaseUpdate.deleteObject(Lists.getInstance().getProjectList().get(currentProjectListIndex), currentProjectID);


            //Delete from the ProjectList
            Lists.getInstance().getProjectList().remove(Lists.getInstance().getProjectList().get(currentProjectListIndex));

            //Delete linked Records
            Lists.getInstance().deleteProjectMemberRecordsFromProjectID(currentProjectID);

            Connection.closeConnection();

            System.out.println("Project [ID]: " + currentProjectID + " deleted");
            return true;
        }
        else {
            return false;
        }
    }

    @FXML
    public void setDefaultTabProjectSettings() {
        textFieldProjectName.setText(Lists.getInstance().getProjectList().get(currentProjectListIndex).getProjectName());
        textAreaDescription.setText(Lists.getInstance().getProjectList().get(currentProjectListIndex).getDescription());
        choiceBoxProjectManager.getSelectionModel().select(Lists.getInstance().getUserListIndex(Lists.getInstance().getProjectList().get(currentProjectListIndex).getProjectManagerID()));
        radioButtonDeleteProject.setSelected(false);
    }

    @FXML
    public void initializeTableTaskGroupSettings() {
        tableTaskGroupSettings.setItems(ViewLists.getInstance().getOlProjectTaskGroups(currentProjectID));
        tableTaskGroupSettingsC1.setText("Taskgroup Name");
        tableTaskGroupSettingsC1.setStyle("-fx-alignment: CENTER;");
        tableTaskGroupSettingsC1.setCellValueFactory(new PropertyValueFactory<>("taskGroupName"));
        tableTaskGroupSettingsC2.setText("Number of Tasks");
        tableTaskGroupSettingsC2.setStyle("-fx-alignment: CENTER;");
        tableTaskGroupSettingsC2.setCellValueFactory(new PropertyValueFactory<>("taskQuantity"));
    }

    @FXML
    public void initializeTableAllUsers() {
        tableAllUsers.setItems(ViewLists.getInstance().getOlUnAssignedProjectMemberNameStrings(currentProjectID));
        tableAllUsersC1.setText("All Users");
        tableAllUsersC1.setStyle("-fx-alignment: CENTER;");
        tableAllUsersC1.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
    }

    @FXML
    public void initializeTableProjectMember() {
        tableCurrentProjectMember.setItems(ViewLists.getInstance().getOlProjectMemberNameStrings(currentProjectID));
        tableCurrentProjectMemberC1.setText("Current Projectusers");
        tableCurrentProjectMemberC1.setStyle("-fx-alignment: CENTER;");
        tableCurrentProjectMemberC1.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
    }

    @FXML
    public boolean addProjectMember() {

        if (tableAllUsers.getSelectionModel().isEmpty()) {
            return false;
        }
        else {
            //Set Data
            selectedValue = tableAllUsers.getSelectionModel().getSelectedItem().toString();
            selectedUserID=Lists.getInstance().getUserIDFromUserNameIDString(selectedValue);

            ProjectMember newObject = new ProjectMember(selectedUserID, currentProjectID);

            //Save Object to the Database
            Connection.openConnection();
            DatabaseUpdate databaseUpdate = new DatabaseUpdate();
            databaseUpdate.insertObject(newObject);
            Connection.closeConnection();

            Lists.getInstance().getProjectMemberList().add(newObject);

            System.out.println("ProjectMember [ID]: " + newObject.getUserID() + " Project: " + newObject.getProjectID() + " added");

            return true;
        }
    }

    @FXML
    public boolean deleteProjectMember() {

        if (tableCurrentProjectMember.getSelectionModel().isEmpty()) {
            return false;
        }
        else {
            //Set Data
            selectedValue = tableCurrentProjectMember.getSelectionModel().getSelectedItem().toString();
            currentProjectMemberListIndex = Lists.getInstance().getProjectMemberIndexFromUserID(Lists.getInstance().getUserIDFromUserNameIDString(selectedValue),currentProjectID);

            //Delete Object from the Database
            Connection.openConnection();
            DatabaseUpdate databaseUpdate = new DatabaseUpdate();
            databaseUpdate.deleteObject(Lists.getInstance().getProjectMemberList().get(currentProjectMemberListIndex), Lists.getInstance().getProjectMemberList().get(currentProjectMemberListIndex).getProjectMemberID());
            Connection.closeConnection();

            System.out.println("ProjectMember [ID]: " + Lists.getInstance().getProjectMemberList().get(currentProjectMemberListIndex).getProjectMemberID() + " Project: " + Lists.getInstance().getProjectMemberList().get(currentProjectMemberListIndex).getProjectID() + " removed");

            //delete from the ProjectMemberList
            Lists.getInstance().getProjectMemberList().remove(currentProjectMemberListIndex);

            return true;
        }
    }

    @FXML
    public void refreshUserSettingsTables() {
        tableAllUsers.setItems(ViewLists.getInstance().getOlUnAssignedProjectMemberNameStrings(currentProjectID));
        tableCurrentProjectMember.setItems(ViewLists.getInstance().getOlProjectMemberNameStrings(currentProjectID));
    }

    @FXML
    public void close() {
        CurrentSession.getInstance().setSessionProjectID(0);
        PopupWindowFirst.getPopupWindow().close();
        LoadDependences.getInstance().openMainScene("projects");
    }
}