package controls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;
import resources.LoadDependences;
import resources.References;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import views.AlertBox;
import views.PopupWindowFirst;

import javax.persistence.Id;
import java.net.URL;
import java.util.ListIterator;
import java.util.Observable;
import java.util.ResourceBundle;

public class ProjectsControl implements Initializable {

    //MainControls
    @FXML private Button buttonDashboard;
    @FXML private Button buttonUserManagement;
    @FXML private Button buttonProjects;
    @FXML private Button buttonLogout;
    @FXML private Label labelLeftStatus;
    @FXML private Label labelRightStatus;

    //SceneControls
    @FXML private Button buttonOpenProject;
    @FXML private Button buttonAddProject;
    @FXML private Button buttonEditDeleteProject;
    @FXML private Button buttonUpdateDatabase;
        //ChartBar
    @FXML private BarChart <String, Integer> chartBarProjectStates;
    @FXML private NumberAxis chartBarProjectStatesY;
    @FXML private CategoryAxis chartBarProjectStatesX;
        //Table
    @FXML private TableView <Project> tableProjectList;
    @FXML private TableColumn<Project, Integer> tableProjectListC1;
    @FXML private TableColumn <Project, String> tableProjectListC2;
    @FXML private TableColumn <Project, String> tableProjectListC3;
    @FXML private TableColumn <Project, String> tableProjectListC4;
    @FXML private TableColumn <Project, String> tableProjectListC5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set UserPermissions
        setUserPermissions();

        //Set MainControls
        buttonDashboard.setOnAction(event -> LoadDependences.getInstance().openMainScene("dashboard"));
        buttonUserManagement.setOnAction(event -> LoadDependences.getInstance().openMainScene("userManagement"));
        buttonProjects.setOnAction(event -> LoadDependences.getInstance().openMainScene("projects"));
        buttonLogout.setOnAction(event -> LoadDependences.getInstance().openMainScene("login"));
        labelLeftStatus.setText(References.getInstance().getLabelLeftStatus());
        labelRightStatus.setText(References.getInstance().getLabelRightStatus());
        buttonUpdateDatabase.setOnAction(event -> {
            LoadDependences.getInstance().startRoutine();
            LoadDependences.getInstance().openMainScene("projects");
        });

        //Set SceneControls
        buttonOpenProject.setOnAction(event -> {
            if(!(tableProjectList.getSelectionModel().isEmpty())) {
                CurrentSession.getInstance().setSessionProjectID(tableProjectList.getSelectionModel().getSelectedItem().getProjectID());
                tableProjectList.getSelectionModel().clearSelection();
                LoadDependences.getInstance().openMainScene("projectDashboard");

            }
            else {
                AlertBox.display("Errormessage", "No Project selected!");
            }
        });
        buttonAddProject.setOnAction(event -> PopupWindowFirst.display("addProject","Add Project"));
        buttonEditDeleteProject.setOnAction(event -> {
            if(!(tableProjectList.getSelectionModel().isEmpty())) {
                CurrentSession.getInstance().setSessionProjectID(tableProjectList.getSelectionModel().getSelectedItem().getProjectID());
                tableProjectList.getSelectionModel().clearSelection();
                PopupWindowFirst.display("editDeleteProject","Edit or Delete Project");
            }
            else {
                AlertBox.display("Errormessage", "No Project selected!");
            }
        });

        //Load Elements
        initializeTableProjectList();
            //Initialize BarChartProjectStates
        chartBarProjectStates.getData().add(ViewLists.getInstance().getClBarChartProjectStates());
        chartBarProjectStatesY.setLabel("Tasks completed [%]");
    }

    //Methods

    @FXML
    public void initializeTableProjectList(){
        tableProjectList.setItems(ViewLists.getInstance().getOlProjects());
        tableProjectListC1.setText("Project [ID]");
        tableProjectListC1.setStyle("-fx-alignment: CENTER;");
        tableProjectListC1.setCellValueFactory(new PropertyValueFactory<>("projectID"));
        tableProjectListC2.setText("Project Name");
        tableProjectListC2.setStyle("-fx-alignment: CENTER;");
        tableProjectListC2.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        tableProjectListC3.setText("Completed [in procent]");
        tableProjectListC3.setStyle("-fx-alignment: CENTER;");
        tableProjectListC3.setCellValueFactory(new PropertyValueFactory<>("progressStateString"));
        tableProjectListC4.setText("Project Manager");
        tableProjectListC4.setStyle("-fx-alignment: CENTER;");
        tableProjectListC4.setCellValueFactory(new PropertyValueFactory<>("projectManagerNameString"));
        tableProjectListC5.setText("Project Description");
        tableProjectListC5.setStyle("-fx-alignment: CENTER;");
        tableProjectListC5.setCellValueFactory(new PropertyValueFactory<>("description"));

        addTooltipToColumnCells(tableProjectListC5, "Description:");
    }

    private void addTooltipToColumnCells (TableColumn<Project, String> tooltippedTableColumn, String toolTipTitle){
        tooltippedTableColumn.setCellFactory(column -> {
            return new TableCell<Project, String>() {
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
            buttonAddProject.setDisable(true);

            //Disable EditDeleteProjectButton on action
            tableProjectList.setOnMouseClicked(event -> {
                if(!(tableProjectList.getSelectionModel().isEmpty())){
                    if(!(Lists.getInstance().isProjectManager(CurrentSession.getInstance().getUserID(),tableProjectList.getSelectionModel().getSelectedItem().getProjectID()))){
                        buttonEditDeleteProject.setDisable(true);
                    }
                    else {
                        buttonEditDeleteProject.setDisable(false);
                    }
                }
            });
        }
    }
}