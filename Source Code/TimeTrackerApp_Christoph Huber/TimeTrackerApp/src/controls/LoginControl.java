package controls;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.CurrentSession;
import models.Lists;
import resources.LoadDependences;
import resources.References;
import views.AlertBox;
import views.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginControl implements Initializable {

    //Set Variables
    String username = "";
    String password = "";

    /* MainControls */
    @FXML
    private Button buttonClear;
    @FXML
    private Button buttonLogin;
    @FXML
    private Button buttonCloseApp;
    @FXML
    private Label labelLeftStatus;
    @FXML
    private Label labelRightStatus;
    @FXML
    private TextField textFieldUsername;
    @FXML
    private PasswordField textFieldPassword;


    /* SceneControls */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Set MainControls
        labelLeftStatus.setText(References.getInstance().getLabelLeftStatus());
        labelRightStatus.setText(References.getInstance().getLabelRightStatus());

        //Set SceneControls
        buttonLogin.setOnAction(event -> {
            if (loginAttempt()){
                clearScene();
                LoadDependences.getInstance().openMainScene("dashboard");
            }
            else {
                AlertBox.display("Errormessage", "Username or Password is empty or incorrect");
            }
        });

        buttonClear.setOnAction(event -> clearScene());

        buttonCloseApp.setOnAction(event -> Main.getWindow().close());
    }

    /* Methods */
    @FXML
    public void clearScene() {
        //clear
        textFieldUsername.clear();
        textFieldPassword.clear();
    }

    @FXML
    public boolean loginAttempt() {
        int listIndex = 0;

        if (textFieldUsername.getText().isEmpty() || textFieldPassword.getText().isEmpty()) {
            return false;
        }

        else {
            username = textFieldUsername.getText();
            listIndex = Lists.getInstance().searchUsername(username);
            if (listIndex != -1) {
                password = textFieldPassword.getText();
                if (Lists.getInstance().checkPassword(listIndex, password)) {
                    CurrentSession.getInstance().setUserID(Lists.getInstance().getUserList().get(listIndex).getUserID());
                    CurrentSession.getInstance().setBusinessRoleID(Lists.getInstance().getUserList().get(listIndex).getBusinessRoleID());
                    return true;
                }
            }
        }
        return false;
    }
}

