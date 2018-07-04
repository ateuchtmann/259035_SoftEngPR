package controls;

import database.Connection;
import database.DatabaseUpdate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.*;
import resources.LoadDependences;
import views.AlertBox;
import views.PopupWindowFirst;

import java.net.URL;
import java.util.*;

public class AddUserControl implements Initializable {
    //SceneControls
    @FXML private TextField textFieldFirstName;
    @FXML private TextField textFieldLastName;
    @FXML private TextField textFieldUsername;
    @FXML private PasswordField textFieldPassword;
    @FXML private ChoiceBox<String> choiceBoxBusinessRole = new ChoiceBox<>();
    @FXML private Button buttonSubmit;
    @FXML private Button buttonCancel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Set SceneControls
        choiceBoxBusinessRole.getItems().addAll(ViewLists.getInstance().getOlBusinessRoleNames());

        buttonCancel.setOnAction(event -> {
            close();

        });

        buttonSubmit.setOnAction(event -> {
            if(addUser()) {
                close();
            }
            else {
                AlertBox.display("Errormessage", "Empty Fields are not allowed!");
            }
        });
    }


    //Methods
    @FXML
    public boolean addUser(){

        if (textFieldFirstName.getText().isEmpty() || textFieldLastName.getText().isEmpty() || textFieldUsername.getText().isEmpty() || textFieldPassword.getText().isEmpty() || choiceBoxBusinessRole.getSelectionModel().isEmpty()) {
            return false;
        }
        else{
            //Set Variables
            String firstName="";
            String lastName="";
            String username="";
            String password="";
            int businessRoleID=0;

           //Set Userinformation
            firstName=textFieldFirstName.getText();
            lastName=textFieldLastName.getText();
            username=textFieldUsername.getText();
            password=textFieldPassword.getText();
            businessRoleID=Lists.getInstance().getBusinessRoleList().get(choiceBoxBusinessRole.getSelectionModel().getSelectedIndex()).getBusinessRoleID();


            User newObject = new User(firstName, lastName,username,password,businessRoleID);

            //Save Object to the Database
            Connection.openConnection();
            DatabaseUpdate databaseUpdate = new DatabaseUpdate();
            databaseUpdate.insertObject(newObject);
            Connection.closeConnection();

            Lists.getInstance().getUserList().add(newObject);

            System.out.println("New User createt: [ID]: " + newObject.getUserID() + " Username: "+ newObject.getUsername());

            return true;
        }
    }

    @FXML
    public void clearScene() {
        //clear
        textFieldFirstName.clear();
        textFieldLastName.clear();
        textFieldUsername.clear();
        textFieldPassword.clear();
        choiceBoxBusinessRole.getSelectionModel().clearSelection();
    }

    private void close() {
        clearScene();
        PopupWindowFirst.getPopupWindow().close();
        LoadDependences.getInstance().openMainScene("userManagement");
    }
}