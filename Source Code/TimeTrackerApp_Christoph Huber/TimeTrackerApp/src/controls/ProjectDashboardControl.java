package controls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;
import resources.LoadDependences;
import resources.References;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import views.AlertBox;
import views.PopupWindowFirst;
import java.net.URL;
import java.util.ListIterator;
import java.util.ResourceBundle;

public class ProjectDashboardControl implements Initializable {
    //Set Variables
    private int currentProjectID = CurrentSession.getInstance().getSessionProjectID();
    private int currentProjectListIndex = Lists.getInstance().getProjectListIndex(currentProjectID);
    private int currentUserID=CurrentSession.getInstance().getUserID();
    private boolean isProjectManager=Lists.getInstance().isProjectManager(currentUserID,currentProjectID);
    private int currentBusinessRoleID=CurrentSession.getInstance().getBusinessRoleID();

    //MainControls
    @FXML private Button buttonDashboard;
    @FXML private Button buttonUserManagement;
    @FXML private Button buttonProjects;
    @FXML private Button buttonLogout;
    @FXML private Label labelLeftStatus;
    @FXML private Label labelRightStatus;

    //SceneControls
    @FXML private Button buttonProjectDashboard;
    @FXML private Button buttonOpenTask;
    @FXML private Button buttonAddTask;
    @FXML private Button buttonEditDeleteTask;
    @FXML private Button buttonUpdateDatabase;
    @FXML private Label labelProjectName;
        //ProjectInformation
    @FXML private TextField textFieldProjectName;
    @FXML private TextField textFieldProjectManager;
    @FXML private TextField textFieldPlannedTime;
    @FXML private TextField textFieldLoggedTime;
    @FXML private TextField textFieldTimeDifference;
    @FXML private TextArea textAreaDescription;
    @FXML private ProgressBar progressBarProject;
    @FXML private Label labelCurrentProgressState;
        //TabPaneProjectOverview
    @FXML private TabPane tabPaneProjectOverview;
        //Additional Information
            //PieCharts
    @FXML private PieChart chartPieUsersSpentTime;
    @FXML private PieChart chartPieTaskTimes;
            //Table MyTasks
    @FXML private TableView<Task> tableMyTasks;
    @FXML private TableColumn<Task, String> tableMyTasksC1;
    @FXML private TableColumn <Task, String> tableMyTasksC2;
    @FXML private TableColumn <Task, String> tableMyTasksC3;
    @FXML private TableColumn <Task, String> tableMyTasksC4;
            //Table UnfinishedTasks
    @FXML private TableView<Task> tableUnfinishedTasks;
    @FXML private TableColumn<Task, String> tableUnfinishedTasksC1;
    @FXML private TableColumn <Task, String> tableUnfinishedTasksC2;
    @FXML private TableColumn <Task, String> tableUnfinishedTasksC3;
            //Table ProjectUsers
    @FXML private TableView<User> tableAssignedUsers;
    @FXML private TableColumn<User, String> tableAssignedUsersC1;
    @FXML private TableColumn <User, Integer> tableAssignedUsersC2;
    @FXML private TableColumn <User, Integer> tableAssignedUsersC3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //reset Current SessionTaskID
        CurrentSession.getInstance().setSessionTaskID(0);

        setUserPermissions();

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
        buttonUpdateDatabase.setOnAction(event -> {
            LoadDependences.getInstance().startRoutine();
            LoadDependences.getInstance().openMainScene("projectDashboard");
        });

        //Set SceneControls
        labelProjectName.setText(Lists.getInstance().getProjectList().get(currentProjectListIndex).getProjectName());
        buttonProjectDashboard.setOnAction(event -> LoadDependences.getInstance().openMainScene("projectDashboard"));
        buttonOpenTask.setOnAction(event -> {
            if(CurrentSession.getInstance().getSessionTaskID()!=0) {
                LoadDependences.getInstance().openMainScene("taskDashboard");
            }
            else {
                AlertBox.display("Errormessage", "No Task selected!");
            }
        });
        buttonAddTask.setOnAction(event -> PopupWindowFirst.display("addTask","Add Task"));
        buttonEditDeleteTask.setOnAction(event -> {
            if(CurrentSession.getInstance().getSessionTaskID()!=0) {
                PopupWindowFirst.display("editDeleteTask","Edit or Delete Task");
            }
            else {
                AlertBox.display("Errormessage", "No Task selected!");
            }
        });

        //Load Elements
        setProjectInformation();
        initializeProjectOverviewTabPane();
        chartPieUsersSpentTime.setData(ViewLists.getInstance().getOlChartPieProjectUserLoggedTimes(currentProjectID));
        chartPieTaskTimes.setData(ViewLists.getInstance().getOlChartPieProjectTaskTimes(currentProjectID));
        initializeTableMyTasks();
        initializeTableUnfinishedTasks();
        initializeTableProjectUsers();
    }

    @FXML
    public void setProjectInformation() {
        long projectLoggedTime=Lists.getInstance().getLoggedProjectTime(currentProjectID);
        long projectPlannedTime=Lists.getInstance().getPlannedProjectTime(currentProjectID);
        long timeDifference=projectPlannedTime-projectLoggedTime;
        int projectProgressStateProcent=Lists.getInstance().calculateProjectProgressState(currentProjectID);
        double projectProgressStateDecimal=projectProgressStateProcent/100.0; //Calculate Current ProgressState and transform Value 0-100 --> 0.0-1.0

        //Fields
        textFieldProjectName.setText(Lists.getInstance().getProjectList().get(currentProjectListIndex).getProjectName());
        textFieldProjectManager.setText(Lists.getInstance().getProjectList().get(currentProjectListIndex).getProjectManagerNameString());
        textFieldPlannedTime.setText(Lists.getInstance().parseDurationToString(projectPlannedTime));
        textFieldLoggedTime.setText(Lists.getInstance().parseDurationToString(projectLoggedTime));
        textAreaDescription.setText(Lists.getInstance().getProjectList().get(currentProjectListIndex).getDescription());
        textAreaDescription.setWrapText(true);

            //FieldTimeDifference: change BackgroundColor if LoggedTime>PlannedTime
        if(timeDifference>=0) {
        textFieldTimeDifference.setStyle("-fx-control-inner-background: #A7F432;"); //LightGreen
        }
        else {
            textFieldTimeDifference.setStyle("-fx-control-inner-background: #ED2939;"); //Lightred
        }
        textFieldTimeDifference.setText(Lists.getInstance().parseDurationToString(timeDifference));

        progressBarProject.setProgress(projectProgressStateDecimal);
        labelCurrentProgressState.setText(projectProgressStateProcent + " %");
    }

    @FXML
    public void initializeProjectOverviewTabPane(){
        ListIterator<TaskGroup> listIterator = Lists.getInstance().getTaskGroupList().listIterator();

        while (listIterator.hasNext()) {
            if(Lists.getInstance().getTaskGroupList().get(listIterator.nextIndex()).getProjectID() == currentProjectID) {
                createTabProjectOverview(listIterator.nextIndex());
            }
            listIterator.next();
        }
    }

    @FXML
    public void createTabProjectOverview(int taskGroupListIndex) {
        //Variables
        int currentTaskGroupID = Lists.getInstance().getTaskGroupList().get(taskGroupListIndex).getTaskGroupID();

        //Create New Tab
        Tab tabTaskGroup = new Tab(Lists.getInstance().getTaskGroupList().get(taskGroupListIndex).getTaskGroupName());
        //Create New Table
        TableView<Task> tableTaskGroup = new TableView<>();
        //Set Columnpolicy & Items
        tableTaskGroup.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableTaskGroup.setItems(ViewLists.getInstance().getOlTaskGroupTasks(currentTaskGroupID));

        //Create Columns
        TableColumn<Task, String> tableTaskGroupC1 = new TableColumn<>("Task Name");
        tableTaskGroupC1.setStyle("-fx-alignment: CENTER;");
        tableTaskGroupC1.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        TableColumn<Task, String> tableTaskGroupC2 = new TableColumn<>("Task State");
        tableTaskGroupC2.setStyle("-fx-alignment: CENTER;");
        tableTaskGroupC2.setCellValueFactory(new PropertyValueFactory<>("taskStateString"));
        TableColumn<Task, String> tableTaskGroupC3 = new TableColumn<>("Planned Time");
        tableTaskGroupC3.setStyle("-fx-alignment: CENTER;");
        tableTaskGroupC3.setCellValueFactory(new PropertyValueFactory<>("plannedTimeString"));
        TableColumn<Task, String> tableTaskGroupC4 = new TableColumn<>("Logged Time");
        tableTaskGroupC4.setStyle("-fx-alignment: CENTER;");
        tableTaskGroupC4.setCellValueFactory(new PropertyValueFactory<>("loggedTimeString"));
        TableColumn<Task, String> tableTaskGroupC5 = new TableColumn<>("Task Owner");
        tableTaskGroupC5.setStyle("-fx-alignment: CENTER;");
        tableTaskGroupC5.setCellValueFactory(new PropertyValueFactory<>("taskOwnerString"));

        tableTaskGroup.getColumns().addAll(tableTaskGroupC1, tableTaskGroupC2, tableTaskGroupC3, tableTaskGroupC4, tableTaskGroupC5);
        tabTaskGroup.setContent(tableTaskGroup);
        tabPaneProjectOverview.getTabs().add(tabTaskGroup);

        //Save Selection && Set UserPermissions
        tableTaskGroup.setOnMouseClicked(event -> {
            if(!(tableTaskGroup.getSelectionModel().isEmpty())){
                CurrentSession.getInstance().setSessionTaskID(tableTaskGroup.getSelectionModel().getSelectedItem().getTaskID());
                //Disable EditDeleteTaskButton on action
                boolean isTaskOwner = false;
                if(tableTaskGroup.getSelectionModel().getSelectedItem().getTaskOwnerID()==currentUserID) {
                    isTaskOwner=true;
                }
                if((currentBusinessRoleID > 1) && (!isProjectManager) && (!isTaskOwner))  {
                    buttonEditDeleteTask.setDisable(true);
                }
                else {
                    buttonEditDeleteTask.setDisable(false);
                }
            }
        });

        //Reset Selection --> TaskGroup (Tab) change
        tabTaskGroup.setOnSelectionChanged(event -> {
            CurrentSession.getInstance().setSessionTaskID(0);
            tableTaskGroup.getSelectionModel().clearSelection();
        });
    }

    @FXML
    public void initializeTableMyTasks(){
        tableMyTasks.setItems(ViewLists.getInstance().getOlMyProjectTasks(currentProjectID));
        tableMyTasksC1.setText("Task Name");
        tableMyTasksC1.setStyle("-fx-alignment: CENTER;");
        tableMyTasksC1.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        tableMyTasksC2.setText("Task State");
        tableMyTasksC2.setStyle("-fx-alignment: CENTER;");
        tableMyTasksC2.setCellValueFactory(new PropertyValueFactory<>("taskStateString"));
        tableMyTasksC3.setText("Planned Time");
        tableMyTasksC3.setStyle("-fx-alignment: CENTER;");
        tableMyTasksC3.setCellValueFactory(new PropertyValueFactory<>("plannedTimeString"));
        tableMyTasksC4.setText("Logged Time");
        tableMyTasksC4.setStyle("-fx-alignment: CENTER;");
        tableMyTasksC4.setCellValueFactory(new PropertyValueFactory<>("loggedTimeString"));
    }

    @FXML
    public void initializeTableUnfinishedTasks(){
        tableUnfinishedTasks.setItems(ViewLists.getInstance().getOlIncompleteProjectTasks(currentProjectID));
        tableUnfinishedTasksC1.setText("Task Name");
        tableUnfinishedTasksC1.setStyle("-fx-alignment: CENTER;");
        tableUnfinishedTasksC1.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        tableUnfinishedTasksC2.setText("Planned Time");
        tableUnfinishedTasksC2.setStyle("-fx-alignment: CENTER;");
        tableUnfinishedTasksC2.setCellValueFactory(new PropertyValueFactory<>("plannedTimeString"));
        tableUnfinishedTasksC3.setText("Logged Time");
        tableUnfinishedTasksC3.setStyle("-fx-alignment: CENTER;");
        tableUnfinishedTasksC3.setCellValueFactory(new PropertyValueFactory<>("loggedTimeString"));
    }

    @FXML
    public void initializeTableProjectUsers(){
        tableAssignedUsers.setItems(ViewLists.getInstance().getOlProjectUsers(currentProjectID));
        tableAssignedUsersC1.setText("Name");
        tableAssignedUsersC1.setStyle("-fx-alignment: CENTER;");
        tableAssignedUsersC1.setCellValueFactory(new PropertyValueFactory<>("userNameString"));
        tableAssignedUsersC2.setText("Assigned Tasks");
        tableAssignedUsersC2.setStyle("-fx-alignment: CENTER;");
        tableAssignedUsersC2.setCellValueFactory(new PropertyValueFactory<>("assignedProjectTasks"));
        tableAssignedUsersC3.setText("Completed Tasks");
        tableAssignedUsersC3.setStyle("-fx-alignment: CENTER;");
        tableAssignedUsersC3.setCellValueFactory(new PropertyValueFactory<>("completedProjectTasks"));
    }

    private void setUserPermissions(){
        if((currentBusinessRoleID > 1) && (!isProjectManager)) {
            buttonAddTask.setDisable(true);
        }
    }
}