package controls;

import javafx.scene.chart.*;
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
import java.util.ResourceBundle;

public class TaskDashboardControl implements Initializable {
        //Variables
        private int currentUserID = CurrentSession.getInstance().getUserID();
        private int currentTaskID = CurrentSession.getInstance().getSessionTaskID();
        private int currentTaskListIndex = Lists.getInstance().getTaskListIndex(currentTaskID);
        private int currentProjectID = Lists.getInstance().getTaskList().get(currentTaskListIndex).getProjectID();
        //MainControls
        @FXML private Button buttonDashboard;
        @FXML private Button buttonUserManagement;
        @FXML private Button buttonProjects;
        @FXML private Button buttonLogout;
        @FXML private Label labelLeftStatus;
        @FXML private Label labelRightStatus;

        //SceneControls
        @FXML private Button buttonProjectDashboard;
        @FXML private Button buttonTaskDashboard;
        @FXML private Button buttonAddActivity;
        @FXML private Button buttonEditDeleteActivity;
        @FXML private Button buttonUpdateDatabase;
        @FXML private Label labelTaskName;
            //TaskOverview
        @FXML private TextField textFieldTaskName;
        @FXML private TextField textFieldTaskState;
        @FXML private TextField textFieldTaskOwner;
        @FXML private TextField textFieldPlannedTime;
        @FXML private TextField textFieldLoggedTime;
        @FXML private TextField textFieldTimeDifference;
        @FXML private TextArea textAreaDescription;
            //Table
        @FXML private TableView<Activity> tableActivityList;
        @FXML private TableColumn<Activity, String> tableActivityListC1;
        @FXML private TableColumn <Activity, String> tableActivityListC2;
        @FXML private TableColumn <Activity, String> tableActivityListC3;
        @FXML private TableColumn <Activity, String> tableActivityListC4;
        @FXML private TableColumn <Activity, String> tableActivityListC5;
        @FXML private TableColumn <Activity, String> tableActivityListC6;
            //Chart
        @FXML private LineChart<String, Integer> chartLineTaskActivities;
        @FXML private NumberAxis chartLineTaskActivitiesY;
        @FXML private CategoryAxis chartLineTaskActivitiesX;

        @Override
        public void initialize(URL location, ResourceBundle resources) {

            //Set MainControls
            buttonDashboard.setOnAction(event -> LoadDependences.getInstance().openMainScene("dashboard"));
            buttonUserManagement.setOnAction(event -> LoadDependences.getInstance().openMainScene("userManagement"));
            buttonProjects.setOnAction(event -> LoadDependences.getInstance().openMainScene("projects"));
            buttonProjectDashboard.setOnAction(event -> LoadDependences.getInstance().openMainScene("projectDashboard"));
            buttonTaskDashboard.setOnAction(event -> LoadDependences.getInstance().openMainScene("taskDashboard"));
            buttonUpdateDatabase.setOnAction(event -> {
                LoadDependences.getInstance().startRoutine();
                LoadDependences.getInstance().openMainScene("taskDashboard");
            });
            buttonLogout.setOnAction(event -> {
                LoadDependences.getInstance().openMainScene("login");
            });
            labelLeftStatus.setText(References.getInstance().getLabelLeftStatus());
            labelRightStatus.setText(References.getInstance().getLabelRightStatus());

            //Set SceneControls
            labelTaskName.setText(Lists.getInstance().getTaskList().get(currentTaskListIndex).getTaskName());
            buttonAddActivity.setOnAction(event -> PopupWindowFirst.display("addActivity","Add Activity"));
            buttonEditDeleteActivity.setOnAction(event -> {
                if(!(tableActivityList.getSelectionModel().isEmpty())) {
                    CurrentSession.getInstance().setSessionActivitiyID(tableActivityList.getSelectionModel().getSelectedItem().getActivityID());
                    tableActivityList.getSelectionModel().clearSelection();
                    PopupWindowFirst.display("editDeleteActivity","Edit or Delete Activity");
                }
                else {
                    AlertBox.display("Errormessage", "No Activity selected!");
                }
            });

            //Set UserPermissions
            setUserPermissions();
            //Load Elements
            setTaskInformations();
            initializeTableActivityList();
                //Initialize LineChartTaskActivities
            chartLineTaskActivities.getData().add(ViewLists.getInstance().getClLineChartTaskActivities(currentTaskID));
            chartLineTaskActivitiesY.setLabel("Activity Duration in Hours");
        }

    //Methods
    @FXML
    public void setTaskInformations() {
        long loggedTaskTimeMinutes = Lists.getInstance().getLoggedTaskTime(currentTaskID);
        long plannedTimeMinutes = Lists.getInstance().getTaskList().get(currentTaskListIndex).getPlannedTimeMinutes();
        long timeDifference = plannedTimeMinutes-loggedTaskTimeMinutes;

        //FieldTimeDifference
        //Change BackgroundColor if LoggedTime>PlannedTime
        if(timeDifference>=0) {
            textFieldTimeDifference.setStyle("-fx-control-inner-background: #A7F432;"); //LightGreen
        }
        else {
            textFieldTimeDifference.setStyle("-fx-control-inner-background: #ED2939;"); //Lightred
        }

        //Update Special TaskAttributes
        ViewLists.getInstance().updateTaskOlAttributes(currentTaskListIndex);
        //Fields
        textFieldTaskName.setText(Lists.getInstance().getTaskList().get(currentTaskListIndex).getTaskName());
        textFieldTaskState.setText(Lists.getInstance().getTaskList().get(currentTaskListIndex).getTaskStateString());
        textFieldTaskOwner.setText(Lists.getInstance().getTaskList().get(currentTaskListIndex).getTaskOwnerString());
        textFieldPlannedTime.setText(Lists.getInstance().getTaskList().get(currentTaskListIndex).getPlannedTimeString());
        textFieldLoggedTime.setText(Lists.getInstance().parseDurationToString(loggedTaskTimeMinutes));
        textFieldTimeDifference.setText(Lists.getInstance().parseDurationToString(timeDifference));
        textAreaDescription.setText(Lists.getInstance().getTaskList().get(currentTaskListIndex).getDescription());
        textAreaDescription.setWrapText(true);
    }

    @FXML
    public void initializeTableActivityList() {
        tableActivityList.setItems(ViewLists.getInstance().getOlTaskActivities(currentTaskID));
        tableActivityListC1.setText("Activity Name");
        tableActivityListC1.setStyle("-fx-alignment: CENTER;");
        tableActivityListC1.setCellValueFactory(new PropertyValueFactory<>("activityName"));
        tableActivityListC2.setText("BeginnTime");
        tableActivityListC2.setStyle("-fx-alignment: CENTER;");
        tableActivityListC2.setCellValueFactory(new PropertyValueFactory<>("startTimeStamp"));
        tableActivityListC3.setText("End Time");
        tableActivityListC3.setStyle("-fx-alignment: CENTER;");
        tableActivityListC3.setCellValueFactory(new PropertyValueFactory<>("endTimeStamp"));
        tableActivityListC4.setText("Duration");
        tableActivityListC4.setStyle("-fx-alignment: CENTER;");
        tableActivityListC4.setCellValueFactory(new PropertyValueFactory<>("durationString"));
        tableActivityListC5.setText("Activity Owner");
        tableActivityListC5.setStyle("-fx-alignment: CENTER;");
        tableActivityListC5.setCellValueFactory(new PropertyValueFactory<>("activityOwnerString"));
        tableActivityListC6.setText("Description");
        tableActivityListC6.setStyle("-fx-alignment: CENTER;");
        tableActivityListC6.setCellValueFactory(new PropertyValueFactory<>("description"));

        addTooltipToColumnCells(tableActivityListC6, "Description:");

    }
    private void addTooltipToColumnCells (TableColumn<Activity,String> tooltippedTableColumn, String toolTipTitle){
        tooltippedTableColumn.setCellFactory(column -> {
            return new TableCell<Activity, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item==null) {
                        setTooltip(null);
                    }
                    else {
                        setText( item );
                        Tooltip tooltip = new Tooltip();
                        setTooltip(tooltip);
                        tooltip.setText(toolTipTitle+"\n\n"+item);
                        tooltip.setPrefWidth(200);
                        tooltip.setWrapText(true);
                        tooltip.setStyle("-fx-alignment: top-left;"
                            + "-fx-font: normal bold 12 Langdon; "
                            + "-fx-base: #AE3522; "
                            + "-fx-text-fill: orange;");
                    }
                }
            };
        });
    }

    private void setUserPermissions(){
        if(CurrentSession.getInstance().getBusinessRoleID()>1) {
            if((!Lists.getInstance().isProjectManager(currentUserID,currentProjectID)) && (Lists.getInstance().getProjectMemberIndexFromUserID(currentUserID, currentProjectID)==-1)) {
                buttonAddActivity.setDisable(true);
                buttonEditDeleteActivity.setDisable(true);
            }
            else {
                if(Lists.getInstance().getProjectMemberIndexFromUserID(currentUserID, currentProjectID)!=-1) {
                    //Enable EditDeleteActivityButton on action --> IF CurrentUser=ProjectMember && ActivityOwner || TaskOwner
                    tableActivityList.setOnMouseClicked(event -> {
                        if(!(tableActivityList.getSelectionModel().isEmpty())){
                            if(!(Lists.getInstance().getTaskList().get(currentTaskListIndex).getTaskOwnerID() == currentUserID)){
                                if(tableActivityList.getSelectionModel().getSelectedItem().getUserID() != currentUserID){
                                    buttonEditDeleteActivity.setDisable(true);
                                }
                                else {
                                    buttonEditDeleteActivity.setDisable(false);
                                }
                            }
                        }
                    });
                }
            }
        }
    }
}