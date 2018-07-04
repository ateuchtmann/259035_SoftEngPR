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
import java.util.ResourceBundle;

public class EditDeleteUserControl implements Initializable {

    //Set Variables
    private int currentUserID = CurrentSession.getInstance().getSessionUserID();
    private  int currentUserListIndex = Lists.getInstance().getUserListIndex(currentUserID);
    private boolean currentUserDeleted= false;

    private String firstName="";
    private String lastName="";
    private String username="";
    private String password="";
    private int businessRoleID=0;

    //MainControls
    @FXML private TextField textFieldFirstName;
    @FXML private TextField textFieldLastName;
    @FXML private TextField textFieldUsername;
    @FXML private PasswordField textFieldPassword;
    @FXML private ChoiceBox<String> choiceBoxBusinessRole = new ChoiceBox<>();
    @FXML private RadioButton radioButtonDeleteUser;
    @FXML private Button buttonSubmit;
    @FXML private Button buttonCancel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setUserPermissions();

        //Set SceneControls
        choiceBoxBusinessRole.getItems().addAll(ViewLists.getInstance().getOlBusinessRoleNames());
        buttonCancel.setOnAction(event -> {
            LoadDependences.getInstance().openMainScene("userManagement");
            close();
        });
        buttonSubmit.setOnAction(event -> {
            if(!(radioButtonDeleteUser.isSelected())) {
                if(editUser()){
                    LoadDependences.getInstance().openMainScene("userManagement");
                    close();
                }
                else {
                    AlertBox.display("Errormessage", "Empty Fields are not allowed!");
                }
            }
            else {
                if(deleteUser()) {
                    if(currentUserDeleted) {
                        LoadDependences.getInstance().openMainScene("login");
                    }
                    else {
                        LoadDependences.getInstance().openMainScene("userManagement");
                    }
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
    public boolean editUser() {

        if (textFieldFirstName.getText().isEmpty() || textFieldLastName.getText().isEmpty() || textFieldUsername.getText().isEmpty() || textFieldPassword.getText().isEmpty() || choiceBoxBusinessRole.getSelectionModel().isEmpty()) {
            return false;
        }
        else {
            //Set Userinformation
            firstName = textFieldFirstName.getText();
            lastName = textFieldLastName.getText();
            username = textFieldUsername.getText();
            password = textFieldPassword.getText();
            businessRoleID = Lists.getInstance().getBusinessRoleList().get(choiceBoxBusinessRole.getSelectionModel().getSelectedIndex()).getBusinessRoleID();


            //Save Changes to the Database
            Connection.openConnection();
            DatabaseUpdate databaseUpdate = new DatabaseUpdate();
            databaseUpdate.updateLoadObject(Lists.getInstance().getUserList().get(currentUserListIndex), currentUserID);

            Lists.getInstance().getUserList().get(currentUserListIndex).setFirstName(firstName);
            Lists.getInstance().getUserList().get(currentUserListIndex).setLastName(lastName);
            Lists.getInstance().getUserList().get(currentUserListIndex).setUsername(username);
            Lists.getInstance().getUserList().get(currentUserListIndex).setPassword(password);
            Lists.getInstance().getUserList().get(currentUserListIndex).setBusinessRoleID(businessRoleID);

            databaseUpdate.updateSaveObject(Lists.getInstance().getUserList().get(currentUserListIndex), currentUserID);

            Connection.closeConnection();

            System.out.println("User changed [ID]: " + currentUserID);

            return true;
        }
    }

    @FXML
    public boolean deleteUser(){


        if(ConfirmBox.display("Confirm Action", "Delete User: " + Lists.getInstance().getUserList().get(currentUserListIndex).getUsername() + " ?")) {

            if(currentUserID == CurrentSession.getInstance().getUserID()) {
                if(ConfirmBox.display("Confirm Action", "Do you really want to delete your own Useraccount? Your Session ends after it automatically!")) {
                    currentUserDeleted=true;
                }
                else {
                    return false;
                }
            }

            Connection.openConnection();
            DatabaseUpdate databaseUpdate = new DatabaseUpdate();
            databaseUpdate.deleteObject(Lists.getInstance().getUserList().get(currentUserListIndex), currentUserID);

            //Delete linked Records
            Lists.getInstance().deleteProjectMemberRecordsFromUserID(currentUserID);

            Connection.closeConnection();

            //Delete from the ApplicationList
            Lists.getInstance().getUserList().remove(Lists.getInstance().getUserList().get(currentUserListIndex));

            System.out.println("User deleted [ID]: " + currentUserID);
            return true;
        }
        else {
            return false;
        }
    }

    private void setUserPermissions(){
        if(CurrentSession.getInstance().getBusinessRoleID()>1) {
            choiceBoxBusinessRole.setDisable(true);
        }
    }

    @FXML
    public void setDefaults() {
        //Set Current Userinformations
        textFieldFirstName.setText(Lists.getInstance().getUserList().get(currentUserListIndex).getFirstName());
        textFieldLastName.setText(Lists.getInstance().getUserList().get(currentUserListIndex).getLastName());
        textFieldUsername.setText(Lists.getInstance().getUserList().get(currentUserListIndex).getUsername());
        textFieldPassword.setText(Lists.getInstance().getUserList().get(currentUserListIndex).getPassword());
        choiceBoxBusinessRole.getSelectionModel().select(Lists.getInstance().getBusinessRoleIndex(Lists.getInstance().getUserList().get(currentUserListIndex).getBusinessRoleID()));
        radioButtonDeleteUser.setSelected(false);
    }
    @FXML
    private void close() {
        CurrentSession.getInstance().setSessionUserID(0);
        PopupWindowFirst.getPopupWindow().close();
    }
}