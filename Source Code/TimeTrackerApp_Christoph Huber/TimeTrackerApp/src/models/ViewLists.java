package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.ListIterator;


public class ViewLists {

    //Instance of this Class, to work with the Lists
    private static ViewLists instance;

    //ObservableLists
    private ObservableList<String> olBusinessRoleNames = FXCollections.observableArrayList();
    private ObservableList<String> olUserNameIDStrings = FXCollections.observableArrayList();
    private ObservableList<String> olProjectUserNameIDStrings = FXCollections.observableArrayList();
    private ObservableList<String> olProjectTaskGroupNameIDStrings = FXCollections.observableArrayList();
    private ObservableList<String> olProjectMemberNameStrings = FXCollections.observableArrayList();
    private ObservableList<String> olUnAssignedProjectMemberNameStrings = FXCollections.observableArrayList();
    private ObservableList<String> olTaskStateNameStrings = FXCollections.observableArrayList();
    private ObservableList<Activity> olMyActivities = FXCollections.observableArrayList();
    private ObservableList<Activity> olTaskActivities = FXCollections.observableArrayList();
    private ObservableList<Project> olIncompleteProjects = FXCollections.observableArrayList();
    private ObservableList<Project> olMyIncompleteProjects = FXCollections.observableArrayList();
    private ObservableList<Project> olProjects = FXCollections.observableArrayList();
    private ObservableList<Task> olMyIncompleteTasks = FXCollections.observableArrayList();
    private ObservableList<Task> olMyProjectTasks = FXCollections.observableArrayList();
    private ObservableList<Task> olIncompleteProjectTasks = FXCollections.observableArrayList();
    private ObservableList<TaskGroup> olProjectTaskGroups = FXCollections.observableArrayList();
    private ObservableList<User> olProjectUsers = FXCollections.observableArrayList();
    private ObservableList<User> olUsers = FXCollections.observableArrayList();

    //Constructors
    public ViewLists() {
    }

    //Methods

        //Models ObservableList Attribute Methods

    public void updateActivityOlAttributes(int activityListIndex){
        //Variables
        int currentActivityOwnerID = Lists.getInstance().getActivityList().get(activityListIndex).getUserID();
        long currentActivityDuration = Lists.getInstance().getActivityList().get(activityListIndex).getDuration();

        //Update Objectattributes
        Lists.getInstance().getActivityList().get(activityListIndex).setActivityOwnerString(Lists.getInstance().getUserNameString(currentActivityOwnerID));
        Lists.getInstance().getActivityList().get(activityListIndex).setDurationString(Lists.getInstance().parseDurationToString(currentActivityDuration));
    }

    public void updateProjectOlAttributes(int projectListIndex){
        //Update current ProjectProgressState
        Lists.getInstance().setProjectProgressState(Lists.getInstance().getProjectList().get(projectListIndex).getProjectID());

        int currentProjectManagerID=Lists.getInstance().getProjectList().get(projectListIndex).getProjectManagerID();
        int currentProgressState=Lists.getInstance().getProjectList().get(projectListIndex).getProgressState();

        String progressStateString=currentProgressState+" %";
        Lists.getInstance().getProjectList().get(projectListIndex).setProjectManagerNameString(Lists.getInstance().getUserNameString(currentProjectManagerID));
        Lists.getInstance().getProjectList().get(projectListIndex).setProgressStateString(progressStateString);
    }

    public void updateTaskOlAttributes(int taskListIndex){
        //Variables
        int currentTaskID=Lists.getInstance().getTaskList().get(taskListIndex).getTaskID();
        int currentTaskStateID=Lists.getInstance().getTaskList().get(taskListIndex).getTaskStateID();
        int currentTaskOwnerID=Lists.getInstance().getTaskList().get(taskListIndex).getTaskOwnerID();
        String currentPlannedTimeString=Lists.getInstance().parseDurationToString(Lists.getInstance().getTaskList().get(taskListIndex).getPlannedTimeMinutes());
        String currentLoggedTimeString=Lists.getInstance().parseDurationToString(Lists.getInstance().getLoggedTaskTime(currentTaskID));
        //Update Objectattributes
        Lists.getInstance().getTaskList().get(taskListIndex).setTaskStateString(Lists.getInstance().getTaskStateName(currentTaskStateID));
        Lists.getInstance().getTaskList().get(taskListIndex).setTaskOwnerString(Lists.getInstance().getUserNameString(currentTaskOwnerID));
        Lists.getInstance().getTaskList().get(taskListIndex).setPlannedTimeString(currentPlannedTimeString);
        Lists.getInstance().getTaskList().get(taskListIndex).setLoggedTimeString(currentLoggedTimeString);
    }

    public void updateTaskGroupOlAttributes (int taskGroupListIndex){
        int taskGroupID=Lists.getInstance().getTaskGroupList().get(taskGroupListIndex).getTaskGroupID();
        Lists.getInstance().getTaskGroupList().get(taskGroupListIndex).setTaskQuantity(Lists.getInstance().getTaskGroupTaskQuantity(taskGroupID));
    }

    public void updateUserOlAttributes(int userListIndex, int projectID) { //IF TaskID = -1 TaskAttributes will be ignored
        //Variables
        int currentUserID = Lists.getInstance().getUserList().get(userListIndex).getUserID();
        int currentBusinessRoleID = Lists.getInstance().getUserList().get(userListIndex).getBusinessRoleID();
        String currentUserNameString = Lists.getInstance().getUserNameString(Lists.getInstance().getUserList().get(userListIndex).getUserID());
        //Update Objectattributes
        Lists.getInstance().getUserList().get(userListIndex).setBusinessRoleName(Lists.getInstance().getBusinessRoleName(currentBusinessRoleID));
        Lists.getInstance().getUserList().get(userListIndex).setUserNameString(currentUserNameString);

        if (projectID != -1) {
            ListIterator<Task> listIterator = Lists.getInstance().getTaskList().listIterator();
            int completedProjectTasks = 0;
            int assignedProjectTasks = 0;
            while (listIterator.hasNext()) {
                Task currentTask = listIterator.next();
                if ((currentTask.getTaskOwnerID() == currentUserID) && (currentTask.getProjectID() == projectID)) {
                    if (currentTask.getTaskStateID() == 1) assignedProjectTasks++;
                    else completedProjectTasks++;
                }
            }
            Lists.getInstance().getUserList().get(userListIndex).setAssignedProjectTasks(assignedProjectTasks);
            Lists.getInstance().getUserList().get(userListIndex).setCompletedProjectTasks(completedProjectTasks);
        }
    }


    //AddProjectControl && AddTaskControl
    public ObservableList<String> getOlUserNameIDStrings() {
        olUserNameIDStrings.clear();
        ListIterator<User> listIterator = Lists.getInstance().getUserList().listIterator();
        int currentUserID;

        while (listIterator.hasNext()) {
            currentUserID=Lists.getInstance().getUserList().get(listIterator.nextIndex()).getUserID();
            olUserNameIDStrings.add(Lists.getInstance().getUserNameIDString(currentUserID));

            listIterator.next();
        }
        return olUserNameIDStrings;
    }

    //AddTaskControl && EditDeleteTaskControl
    public ObservableList<String> getOlProjectTaskGroupNameIDStrings(int projectID) {
        olProjectTaskGroupNameIDStrings.clear();
        ListIterator<TaskGroup> listIterator = Lists.getInstance().getTaskGroupList().listIterator();
        int currentTaskGroupID;

        while (listIterator.hasNext()) {
            if(Lists.getInstance().getTaskGroupList().get(listIterator.nextIndex()).getProjectID()==projectID) {
                currentTaskGroupID=Lists.getInstance().getTaskGroupList().get(listIterator.nextIndex()).getTaskGroupID();
                olProjectTaskGroupNameIDStrings.add(Lists.getInstance().getTaskGroupNameIDString(currentTaskGroupID));
            }
            listIterator.next();
        }
        return olProjectTaskGroupNameIDStrings;
    }

    public ObservableList<String> getOlProjectUserNameIDStrings(int projectID) {
        olProjectUserNameIDStrings.clear();
        ListIterator<User> listIterator = Lists.getInstance().getUserList().listIterator();
        int currentUserID;

        while (listIterator.hasNext()) {
            currentUserID=Lists.getInstance().getUserList().get(listIterator.nextIndex()).getUserID();
            if((Lists.getInstance().getProjectMemberIndexFromUserID(currentUserID,projectID)!=-1)||(Lists.getInstance().isProjectManager(currentUserID,projectID))) {
                olProjectUserNameIDStrings.add(Lists.getInstance().getUserNameIDString(currentUserID));
            }
            listIterator.next();
        }
        return olProjectUserNameIDStrings;
    }

    //AddUserControl & EditDeleteUserControl
    public ObservableList<String> getOlBusinessRoleNames() {
        olBusinessRoleNames.clear();
        ListIterator<BusinessRole> listIterator = Lists.getInstance().getBusinessRoleList().listIterator();
        int listIndex = 0;
        while (listIterator.hasNext()) {
            olBusinessRoleNames.add(Lists.getInstance().getBusinessRoleList().get(listIndex).getBusinessRoleName());

            listIterator.next();
            listIndex++;
        }
        return olBusinessRoleNames;
    }

    //DashboardControl
    public ObservableList<Project> getOlIncompleteProjects() {
        olIncompleteProjects.clear();
        ListIterator<Project> listIterator = Lists.getInstance().getProjectList().listIterator();
        int listIndex = 0;
        while (listIterator.hasNext()) {
            if (Lists.getInstance().getProjectList().get(listIndex).getProgressState() < 100) {
                updateProjectOlAttributes(listIndex);
                olIncompleteProjects.add(Lists.getInstance().getProjectList().get(listIndex));
            }
            listIterator.next();
            listIndex++;
        }
        return olIncompleteProjects;
    }

    public ObservableList<Project> getOlMyIncompleteProjects() {
        olMyIncompleteProjects.clear();
        ListIterator<Project> projectListIterator = Lists.getInstance().getProjectList().listIterator();
        int currentUserID=CurrentSession.getInstance().getUserID();
        int listIndex = 0;
        while (projectListIterator.hasNext()) {
            if ((Lists.getInstance().getProjectList().get(listIndex).getProgressState() < 100) && (Lists.getInstance().getProjectList().get(listIndex).getProjectManagerID()==currentUserID)) {
                updateProjectOlAttributes(listIndex);
                olMyIncompleteProjects.add(Lists.getInstance().getProjectList().get(listIndex));
            }
            projectListIterator.next();
            listIndex++;
        }

        ListIterator<ProjectMember> projectMemberListIterator = Lists.getInstance().getProjectMemberList().listIterator();
        listIndex = 0;
        int currentProjectID=0;
        int currentProjectListIndex=0;
        while (projectMemberListIterator.hasNext()) {
            if ((Lists.getInstance().getProjectMemberList().get(listIndex).getUserID()==currentUserID)) {
                currentProjectID=Lists.getInstance().getProjectMemberList().get(listIndex).getProjectID();
                currentProjectListIndex=Lists.getInstance().getProjectListIndex(currentProjectID);
                if((Lists.getInstance().getProjectList().get(currentProjectListIndex).getProgressState()) < 100 ){
                    updateProjectOlAttributes(currentProjectListIndex);
                    olMyIncompleteProjects.add(Lists.getInstance().getProjectList().get(currentProjectListIndex));
                }
            }
            projectMemberListIterator.next();
            listIndex++;
        }
        return olMyIncompleteProjects;
    }

    public ObservableList<Task> getOlMyIncompleteTasks() {
        olMyIncompleteTasks.clear();
        ListIterator<Task> listIterator = Lists.getInstance().getTaskList().listIterator();
        int currentUserID=CurrentSession.getInstance().getUserID();
        int listIndex = 0;
        while (listIterator.hasNext()) {
            if ((Lists.getInstance().getTaskList().get(listIndex).getTaskStateID() == 1) && (Lists.getInstance().getTaskList().get(listIndex).getTaskOwnerID()==currentUserID)) {
                updateTaskOlAttributes(listIndex);
                olMyIncompleteTasks.add(Lists.getInstance().getTaskList().get(listIndex));
            }
            listIterator.next();
            listIndex++;
        }
        return olMyIncompleteTasks;
    }

    public ObservableList<Activity> getOlMyActivities() {
        olMyActivities.clear();
        ListIterator<Activity> listIterator = Lists.getInstance().getActivityList().listIterator();
        int currentUserID=CurrentSession.getInstance().getUserID();
        int listIndex = 0;
        while (listIterator.hasNext()) {
            if ((Lists.getInstance().getActivityList().get(listIndex).getUserID()==currentUserID)) {
                olMyActivities.add(Lists.getInstance().getActivityList().get(listIndex));
            }
            listIterator.next();
            listIndex++;
        }
        return olMyActivities;
    }

        //EditDeleteProjectControl
    public ObservableList<String> getOlUnAssignedProjectMemberNameStrings(int projectID) {
        olUnAssignedProjectMemberNameStrings.clear();
        ListIterator<User> listIterator = Lists.getInstance().getUserList().listIterator();
        int listIndex = 0;
        int currentProjectManagerID = Lists.getInstance().getProjectList().get(Lists.getInstance().getProjectListIndex(projectID)).getProjectManagerID();
        int currentUserID;

        while (listIterator.hasNext()) {
            currentUserID=Lists.getInstance().getUserList().get(listIndex).getUserID();
            if((Lists.getInstance().getProjectMemberIndexFromUserID(currentUserID, projectID)==-1) && (currentProjectManagerID != currentUserID)) {
                olUnAssignedProjectMemberNameStrings.add(Lists.getInstance().getUserNameIDString(currentUserID));
            }
            listIterator.next();
            listIndex++;
        }
        return olUnAssignedProjectMemberNameStrings;
    }

    public ObservableList<String> getOlProjectMemberNameStrings(int projectID) {
        olProjectMemberNameStrings.clear();
        ListIterator<User> listIterator = Lists.getInstance().getUserList().listIterator();
        int listIndex = 0;
        int currentUserID;

        while (listIterator.hasNext()) {
            currentUserID=Lists.getInstance().getUserList().get(listIndex).getUserID();
            if(Lists.getInstance().getProjectMemberIndexFromUserID(currentUserID, projectID)!=-1) {
                olProjectMemberNameStrings.add(Lists.getInstance().getUserNameIDString(currentUserID));
            }
            listIterator.next();
            listIndex++;
        }
        return olProjectMemberNameStrings;
    }

    public ObservableList<TaskGroup> getOlProjectTaskGroups(int projectID) {
        olProjectTaskGroups.clear();
        ListIterator<TaskGroup> listIterator = Lists.getInstance().getTaskGroupList().listIterator();
        //int listIndex = 0;
        while (listIterator.hasNext()) {
            if (Lists.getInstance().getTaskGroupList().get(listIterator.nextIndex()).getProjectID() == projectID) {
                updateTaskGroupOlAttributes(listIterator.nextIndex());
                olProjectTaskGroups.add(Lists.getInstance().getTaskGroupList().get(listIterator.nextIndex()));
            }
            listIterator.next();
        }
        return olProjectTaskGroups;
    }

    //EditDeleteTaskControl
    public ObservableList<String> getOlTaskStateNameStrings() {
        olTaskStateNameStrings.clear();
        ListIterator<TaskState> listIterator = Lists.getInstance().getTaskStateList().listIterator();
        while (listIterator.hasNext()) {
            olTaskStateNameStrings.add(Lists.getInstance().getTaskStateList().get(listIterator.nextIndex()).getTaskStateName());
            listIterator.next();
        }
        return olTaskStateNameStrings;
    }

    public int getListIndexOlProjectTaskGroupNameIDStrings(int taskGroupID){
        ListIterator<String> listIterator = olProjectTaskGroupNameIDStrings.listIterator();
        int currentListTaskGroupID;
        while (listIterator.hasNext()) {
            currentListTaskGroupID=Lists.getInstance().getTaskGroupIDFromTaskGroupNameIDString(olProjectTaskGroupNameIDStrings.get(listIterator.nextIndex()));
            if(currentListTaskGroupID == taskGroupID){
                return listIterator.nextIndex();
            }
            listIterator.next();
        }
        return -1;
    }

    public int getListIndexOlProjectUserNameIDStrings(int userID){
        ListIterator<String> listIterator = olProjectUserNameIDStrings.listIterator();
        int currentUserID;
        while (listIterator.hasNext()) {
            currentUserID=Lists.getInstance().getTaskGroupIDFromTaskGroupNameIDString(olProjectUserNameIDStrings.get(listIterator.nextIndex()));
            if(currentUserID == userID){
                return listIterator.nextIndex();
            }
            listIterator.next();
        }
        return -1;
    }

        //ProjectsControl
    public ObservableList<Project> getOlProjects() {
        olProjects.clear();
        ListIterator<Project> listIterator = Lists.getInstance().getProjectList().listIterator();
        int listIndex = 0;
        while (listIterator.hasNext()) {
            updateProjectOlAttributes(listIndex);
            olProjects.add(Lists.getInstance().getProjectList().get(listIndex));
            listIterator.next();
            listIndex++;
        }
        return olProjects;
    }

    public BarChart.Series<String, Integer> getClBarChartProjectStates(){
        ObservableList<Project> olIncompleteProjects = FXCollections.observableArrayList();
        olIncompleteProjects=getOlIncompleteProjects();
        BarChart.Series<String, Integer> clIncompleteProjects = new XYChart.Series<>();

        ListIterator<Project> listIterator = olIncompleteProjects.listIterator();

        while(listIterator.hasNext()) {
            clIncompleteProjects.getData().add(new XYChart.Data<>(olIncompleteProjects.get(listIterator.nextIndex()).getProjectName(),olIncompleteProjects.get(listIterator.nextIndex()).getProgressState()));
            listIterator.next();
        }
        return clIncompleteProjects;
    }

        //ProjectDashboardControl
     public ObservableList<Task> getOlTaskGroupTasks(int taskGroupID) {
        ObservableList<Task> olTaskGroupTasks = FXCollections.observableArrayList();

        ListIterator<Task> listIterator = Lists.getInstance().getTaskList().listIterator();

        while (listIterator.hasNext()) {
            if(Lists.getInstance().getTaskList().get(listIterator.nextIndex()).getTaskGroupID()==taskGroupID){
                updateTaskOlAttributes(listIterator.nextIndex());
                olTaskGroupTasks.add(Lists.getInstance().getTaskList().get(listIterator.nextIndex()));
            }
            listIterator.next();
        }
        return olTaskGroupTasks;
    }

    public ObservableList<Task> getOlMyProjectTasks(int projectID) {
        olMyProjectTasks.clear();
        ListIterator<Task> listIterator = Lists.getInstance().getTaskList().listIterator();
        int currentUserID=CurrentSession.getInstance().getUserID();
        while (listIterator.hasNext()) {
            Task currentTask = listIterator.next();
            if((currentTask.getTaskOwnerID()==currentUserID) && (currentTask.getProjectID()==projectID)) {
                updateTaskOlAttributes(listIterator.previousIndex());
                olMyProjectTasks.add(currentTask);
            }
        }
        return olMyProjectTasks;
    }

    public ObservableList<Task> getOlIncompleteProjectTasks(int projectID) {
        olIncompleteProjectTasks.clear();
        ListIterator<Task> listIterator = Lists.getInstance().getTaskList().listIterator();
        while (listIterator.hasNext()) {
            Task currentTask = listIterator.next();
            if((currentTask.getProjectID()==projectID) && (currentTask.getTaskStateID()==1)) {
                updateTaskOlAttributes(listIterator.previousIndex());
                olIncompleteProjectTasks.add(currentTask);
            }
        }
        return olIncompleteProjectTasks;
    }

    public ObservableList<User> getOlProjectUsers(int projectID) {
        olProjectUsers.clear();
        ListIterator<User> listIterator = Lists.getInstance().getUserList().listIterator();
        while (listIterator.hasNext()) {
            User currentUser = listIterator.next();
            if((Lists.getInstance().isProjectManager(currentUser.getUserID(),projectID)) || (Lists.getInstance().getProjectMemberIndexFromUserID(currentUser.getUserID(),projectID) != -1)) {
                updateUserOlAttributes(listIterator.previousIndex(),projectID);
                olProjectUsers.add(currentUser);
            }
        }
        return olProjectUsers;
    }

    public ObservableList<PieChart.Data> getOlChartPieProjectUserLoggedTimes(int projectID) {
        ObservableList<PieChart.Data> olChartPieProjectUserLoggedTimes= FXCollections.observableArrayList();
        ListIterator<User> listIterator = Lists.getInstance().getUserList().listIterator();
        long currentLoggedUserProjectTime=0;
        double currentLoggedProjectTime=Lists.getInstance().getLoggedProjectTime(projectID);
        double currentRatio;
        String currentUserNameString="";

        while (listIterator.hasNext()) {
            User currentUser = listIterator.next();
            if((Lists.getInstance().isProjectManager(currentUser.getUserID(),projectID)) || (Lists.getInstance().getProjectMemberIndexFromUserID(currentUser.getUserID(),projectID)!=-1)) {
                currentLoggedUserProjectTime=Lists.getInstance().getLoggedUserProjectTime(currentUser.getUserID(),projectID);
                if(currentLoggedUserProjectTime>0) {
                    currentRatio=(currentLoggedUserProjectTime/currentLoggedProjectTime)*100;
                    currentUserNameString=currentUser.getFirstName() + " " + currentUser.getLastName();
                    olChartPieProjectUserLoggedTimes.add(new PieChart.Data(currentUserNameString,currentRatio));
                }
            }
        }
        return olChartPieProjectUserLoggedTimes;
    }

    public ObservableList<PieChart.Data> getOlChartPieProjectTaskTimes (int projectID) {
        ObservableList<PieChart.Data> olChartPieProjectTaskTimes= FXCollections.observableArrayList();
        ListIterator<Task> listIterator = Lists.getInstance().getTaskList().listIterator();
        double currentLoggedProjectTime=Lists.getInstance().getLoggedProjectTime(projectID);
        double currentLoggedTaskTime;
        double currentRatio;

        while (listIterator.hasNext()) {
            Task currentTask = listIterator.next();
            if(currentTask.getProjectID()==projectID) {
                currentLoggedTaskTime=Lists.getInstance().getLoggedTaskTime(currentTask.getTaskID());
                currentRatio=(currentLoggedTaskTime/currentLoggedProjectTime)*100;
                olChartPieProjectTaskTimes.add(new PieChart.Data(currentTask.getTaskName(),currentRatio));
            }
        }
        return olChartPieProjectTaskTimes;
    }

        //TaskDashboardControl
    public ObservableList<Activity> getOlTaskActivities(int currentTaskID) {
        olTaskActivities.clear();
        ListIterator<Activity> listIterator = Lists.getInstance().getActivityList().listIterator();

        while (listIterator.hasNext()) {
            if ((Lists.getInstance().getActivityList().get(listIterator.nextIndex()).getTaskID()==currentTaskID)) {
                updateActivityOlAttributes(listIterator.nextIndex());
                olTaskActivities.add(Lists.getInstance().getActivityList().get(listIterator.nextIndex()));
            }
            listIterator.next();
        }
        return olTaskActivities;
    }

    public BarChart.Series<String, Integer> getClLineChartTaskActivities(int currentTaskID){
        ObservableList<Activity> olTaskActivities = FXCollections.observableArrayList();
        olTaskActivities=getOlTaskActivities(currentTaskID);

        BarChart.Series<String, Integer> clRecentTaskActivities = new XYChart.Series<>();
        LocalDateTime currentDateTime = LocalDateTime.of(LocalDateTime.now().getYear(),1,1,0,0);

        String currentMonthString="";
        int currentMonth=1;

        while (currentMonth<=12) {
            ListIterator<Activity> listIterator = olTaskActivities.listIterator();
            long currentDuration=0;

            while(listIterator.hasNext()) {
                Activity currentActivity = listIterator.next();
                if(currentActivity.getStartDateTime().getYear() == currentDateTime.getYear()) {
                    if(currentActivity.getStartDateTime().getMonthValue() == currentMonth) {
                        currentDuration+=currentActivity.getDuration();
                    }
                }
            }
            currentDuration=currentDuration/60;
            currentMonthString=currentDateTime.getMonth().toString();
            clRecentTaskActivities.getData().add(new XYChart.Data<>(currentMonthString,(int)currentDuration));

            currentDateTime=currentDateTime.plusMonths(1);
            currentMonth++;
        }
        return clRecentTaskActivities;
    }

        //UserManagementControl
    public ObservableList<User> getOlUsers() {
        olUsers.clear();
        ListIterator<User> listIterator = Lists.getInstance().getUserList().listIterator();
        int listIndex = 0;
        while (listIterator.hasNext()) {
            updateUserOlAttributes(listIndex, -1);
            olUsers.add(Lists.getInstance().getUserList().get(listIndex));
            listIterator.next();
            listIndex++;
        }
        return olUsers;
    }

    //Getter
        //Create an Instance of this Class
    public static ViewLists getInstance() {
        if (instance == null) {
            instance = new ViewLists();
        }
        return instance;
    }
}
