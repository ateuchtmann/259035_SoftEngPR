package controls;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;
import models.ViewLists;
import resources.LoadDependences;
import resources.References;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import views.AlertBox;
import views.PopupWindowFirst;

import java.net.URL;
import java.util.ResourceBundle;

public class UserManagementControl implements Initializable {

    //MainControls
    @FXML private Button buttonDashboard;
    @FXML private Button buttonUserManagement;
    @FXML private Button buttonProjects;
    @FXML private Button buttonLogout;
    @FXML private Label labelLeftStatus;
    @FXML private Label labelRightStatus;

    //SceneControls
    @FXML private Button buttonAddUser;
    @FXML private Button buttonEditDeleteUser;
    @FXML private Button buttonUpdateDatabase;
    @FXML private Label labelUserStatistics;
        //Table
    @FXML private TableView<User> tableUserList;
    @FXML private TableColumn<User, Integer> tableUserListC1;
    @FXML private TableColumn <User, String> tableUserListC2;
    @FXML private TableColumn <User, String> tableUserListC3;
    @FXML private TableColumn <User, String> tableUserListC4;
    @FXML private TableColumn <User, String> tableUserListC5;
        //ChartBar
    @FXML private BarChart<String, Integer> chartBarUserStatistics;
    @FXML private NumberAxis chartBarUserStatisticsY;
    @FXML private CategoryAxis chartBarUserStatisticsX;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set Userpermissions
        setUserPermissions();

        //Set MainControls
        buttonDashboard.setOnAction(event -> {
            LoadDependences.getInstance().openMainScene("dashboard");
        });
        buttonUserManagement.setOnAction(event -> LoadDependences.getInstance().openMainScene("userManagement"));
        buttonProjects.setOnAction(event -> LoadDependences.getInstance().openMainScene("projects"));
        buttonUpdateDatabase.setOnAction(event -> {
            LoadDependences.getInstance().startRoutine();
            LoadDependences.getInstance().openMainScene("userManagement");
        });
        buttonLogout.setOnAction(event -> {
            LoadDependences.getInstance().openMainScene("login");
        });
        labelLeftStatus.setText(References.getInstance().getLabelLeftStatus());
        labelRightStatus.setText(References.getInstance().getLabelRightStatus());

        //Set SceneControls
        buttonAddUser.setOnAction(event -> PopupWindowFirst.display("addUser","Add User"));
        buttonEditDeleteUser.setOnAction(event -> {
            if(!(tableUserList.getSelectionModel().isEmpty())) {
                CurrentSession.getInstance().setSessionUserID(tableUserList.getSelectionModel().getSelectedItem().getUserID());
                //System.out.println(CurrentSession.getInstance().getSessionUserID());
                tableUserList.getSelectionModel().clearSelection();
                PopupWindowFirst.display("editDeleteUser","Edit or Delete User");
            }
            else {
                AlertBox.display("Errormessage", "No User selected!");
            }
        });
        labelUserStatistics.setText("User Statistics (last 4 Months)");


        //Load Elements
        initializeTableUsers();
            //Initialize BarChartProjectStates
        chartBarUserStatistics.getData().add(ViewLists.getInstance().getClBarChartUserStatisticsLastMonths(4));
        chartBarUserStatisticsY.setLabel("spent time [h]");
    }

    @FXML
    public void initializeTableUsers() {
        tableUserList.setItems(ViewLists.getInstance().getOlUsers());
        tableUserListC1.setText("UserID");
        tableUserListC1.setStyle("-fx-alignment: CENTER;");
        tableUserListC1.setCellValueFactory(new PropertyValueFactory<>("userID"));
        tableUserListC2.setText("First Name");
        tableUserListC2.setStyle("-fx-alignment: CENTER;");
        tableUserListC2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableUserListC3.setText("Last Name");
        tableUserListC3.setStyle("-fx-alignment: CENTER;");
        tableUserListC3.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableUserListC4.setText("Username");
        tableUserListC4.setStyle("-fx-alignment: CENTER;");
        tableUserListC4.setCellValueFactory(new PropertyValueFactory<>("username"));
        tableUserListC5.setText("BusinessRole");
        tableUserListC5.setStyle("-fx-alignment: CENTER;");
        tableUserListC5.setCellValueFactory(new PropertyValueFactory<>("businessRoleName"));
    }

    private void setUserPermissions(){
        if(CurrentSession.getInstance().getBusinessRoleID()>1) {
            buttonAddUser.setDisable(true);

            //Disable EditDeleteUserButton on action
            tableUserList.setOnMouseClicked(event -> {
                if(!(tableUserList.getSelectionModel().isEmpty())){
                    if(!(tableUserList.getSelectionModel().getSelectedItem().getUserID()==CurrentSession.getInstance().getUserID())){
                        buttonEditDeleteUser.setDisable(true);
                    }
                    else {
                        buttonEditDeleteUser.setDisable(false);
                    }
                }
            });
        }
    }
}