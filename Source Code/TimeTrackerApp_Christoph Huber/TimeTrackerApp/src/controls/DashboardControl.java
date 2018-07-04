package controls;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;
import resources.LoadDependences;
import resources.References;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import views.Main;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class DashboardControl implements Initializable {
    //MainControls
    @FXML private Button buttonDashboard;
    @FXML private Button buttonUserManagement;
    @FXML private Button buttonProjects;
    @FXML private Button buttonLogout;
    @FXML private Label labelLeftStatus;
    @FXML private Label labelRightStatus;
    @FXML private Label labelLoggedUser;

    //SceneControls
    @FXML private Button buttonUpdateDatabase;

    //Table 1
    @FXML private TableView tableIncompleteProjects;
    @FXML private TableColumn <Project, String> tableIncompleteProjectsC1;
    @FXML private TableColumn <Project, String> tableIncompleteProjectsC2;
    @FXML private TableColumn <Project, String> tableIncompleteProjectsC3;
    //Table 2
    @FXML private TableView  tableMyIncompleteProjects;
    @FXML private TableColumn <Project, String> tableMyIncompleteProjectsC1;
    @FXML private TableColumn <Project, String> tableMyIncompleteProjectsC2;
    @FXML private TableColumn <Project, String> tableMyIncompleteProjectsC3;
    //Table 2
    @FXML private TableView tableMyIncompleteTasks;
    @FXML private TableColumn <Task, String> tableMyIncompleteTasksC1;
    @FXML private TableColumn <Task, String> tableMyIncompleteTasksC2;
    @FXML private TableColumn <Task, String> tableMyIncompleteTasksC3;
    //Table 3
    @FXML private TableView tableMyActivities;
    @FXML private TableColumn <Activity, String> tableMyActivitiesC1;
    @FXML private TableColumn <Activity, String> tableMyActivitiesC2;
    @FXML private TableColumn <Activity, Long> tableMyActivitiesC3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set MainControls
        buttonDashboard.setOnAction(event -> {
            LoadDependences.getInstance().openMainScene("dashboard");
        });
        buttonUserManagement.setOnAction(event -> LoadDependences.getInstance().openMainScene("userManagement"));
        buttonProjects.setOnAction(event -> LoadDependences.getInstance().openMainScene("projects"));
        buttonLogout.setOnAction(event -> {
            LoadDependences.getInstance().openMainScene("login");
        });
        labelLeftStatus.setText(References.getInstance().getLabelLeftStatus());
        labelRightStatus.setText(References.getInstance().getLabelRightStatus());

        //Set SceneControls
        labelLoggedUser.setText(getCurrentUser());
        buttonUpdateDatabase.setOnAction(event -> {
            LoadDependences.getInstance().startRoutine();
            LoadDependences.getInstance().openMainScene("dashboard");
        });

        //Load Elements
        initialiteTableIncompleteProjects();
        initializeTableMyIncompleteProjects();
        initializeTableMyIncompleteTasks();
        initializeTableMyActivities();
    }

    //Methods

    @FXML
    public void initialiteTableIncompleteProjects() {
        tableIncompleteProjects.setItems(ViewLists.getInstance().getOlIncompleteProjects());
        tableIncompleteProjectsC1.setText("Project Name");
        tableIncompleteProjectsC1.setStyle("-fx-alignment: CENTER;");
        tableIncompleteProjectsC1.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        tableIncompleteProjectsC2.setText("Project Manager");
        tableIncompleteProjectsC2.setStyle("-fx-alignment: CENTER;");
        tableIncompleteProjectsC2.setCellValueFactory(new PropertyValueFactory<>("projectManagerNameString"));
        tableIncompleteProjectsC3.setText("Completed [in procent]");
        tableIncompleteProjectsC3.setStyle("-fx-alignment: CENTER;");
        tableIncompleteProjectsC3.setCellValueFactory(new PropertyValueFactory<>("progressStateString"));
    }

    @FXML
    public void initializeTableMyIncompleteProjects() {
        tableMyIncompleteProjects.setItems(ViewLists.getInstance().getOlMyIncompleteProjects());
        tableMyIncompleteProjectsC1.setText("Project Name");
        tableMyIncompleteProjectsC1.setStyle("-fx-alignment: CENTER;");
        tableMyIncompleteProjectsC1.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        tableMyIncompleteProjectsC2.setText("Project Manager");
        tableMyIncompleteProjectsC2.setStyle("-fx-alignment: CENTER;");
        tableMyIncompleteProjectsC2.setCellValueFactory(new PropertyValueFactory<>("projectManagerNameString"));
        tableMyIncompleteProjectsC3.setText("Completed [in procent]");
        tableMyIncompleteProjectsC3.setStyle("-fx-alignment: CENTER;");
        tableMyIncompleteProjectsC3.setCellValueFactory(new PropertyValueFactory<>("progressStateString"));
    }

    @FXML
    public void initializeTableMyIncompleteTasks() {
        tableMyIncompleteTasks.setItems(ViewLists.getInstance().getOlMyIncompleteTasks());
        tableMyIncompleteTasksC1.setText("Task Name");
        tableMyIncompleteTasksC1.setStyle("-fx-alignment: CENTER;");
        tableMyIncompleteTasksC1.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        tableMyIncompleteTasksC2.setText("Planned Time");
        tableMyIncompleteTasksC2.setStyle("-fx-alignment: CENTER;");
        tableMyIncompleteTasksC2.setCellValueFactory(new PropertyValueFactory<>("plannedTimeString"));
        tableMyIncompleteTasksC3.setText("Logged Time");
        tableMyIncompleteTasksC3.setStyle("-fx-alignment: CENTER;");
        tableMyIncompleteTasksC3.setCellValueFactory(new PropertyValueFactory<>("loggedTimeString"));
    }

    @FXML
    public void initializeTableMyActivities() {
        tableMyActivities.setItems(ViewLists.getInstance().getOlMyActivities());
        tableMyActivitiesC1.setText("Activity Name");
        tableMyActivitiesC1.setStyle("-fx-alignment: CENTER;");
        tableMyActivitiesC1.setCellValueFactory(new PropertyValueFactory<>("activityName"));
        tableMyActivitiesC2.setText("Beginn Date");
        tableMyActivitiesC2.setStyle("-fx-alignment: CENTER;");
        tableMyActivitiesC2.setCellValueFactory(new PropertyValueFactory<>("startTimeStamp"));
        tableMyActivitiesC3.setText("Duration");
        tableMyActivitiesC3.setStyle("-fx-alignment: CENTER;");
        tableMyActivitiesC3.setCellValueFactory(new PropertyValueFactory<>("duration"));
    }


    @FXML
    public String getCurrentUser(){
        String currentUserNameString=Lists.getInstance().getUserNameString(CurrentSession.getInstance().getUserID());
        if(!(currentUserNameString.equals(""))) {
            return currentUserNameString;
        }
        else{
            return("<Unknown User>");
        }
    }
}