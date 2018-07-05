package models;

import database.DatabaseUpdate;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Lists {

    //Instance of this Class, to work with the Lists
    private static Lists instance;

    //ArrayLists
    private List<Activity> activityList;
    private List<BusinessRole> businessRoleList;
    private List<Project> projectList;
    private List<ProjectMember> projectMemberList;
    private List<TaskGroup> taskGroupList;
    private List<Task> taskList;
    private List<TaskState> taskStateList;
    private List<User> userList;
    private List<Object> objectList;

    //Constructors
    public Lists() {
        //Arraylists
        activityList = new ArrayList<Activity>();
        businessRoleList = new ArrayList<BusinessRole>();
        projectList = new ArrayList<Project>();
        projectMemberList = new ArrayList<ProjectMember>();
        taskGroupList = new ArrayList<TaskGroup>();
        taskList = new ArrayList<Task>();
        taskStateList = new ArrayList<TaskState>();
        userList = new ArrayList<User>();
        objectList = new ArrayList<Object>();
    }

    //Methods
    //Activity
    public int getActivityListIndex(int activityID){
        ListIterator<Activity> listIterator = activityList.listIterator();
        int listIndex=0;

        while(listIterator.hasNext()){
            if (activityList.get(listIndex).getActivityID()==(activityID)) {
                return listIndex;
            }
            listIterator.next();
            listIndex++;
        }
        return -1;
    }


    public void initializeAllActivityDurations() {
        ListIterator<Activity> listIterator = activityList.listIterator();
        int listIndex=0;

        while(listIterator.hasNext()){
            activityList.get(listIndex).initializeLocalDateTimes();
            activityList.get(listIndex).updateDuration();

            //System.out.println(activityList.get(listIndex).getDuration());
            listIterator.next();
            listIndex++;
        }
    }



    //AddUser
    public int getBusinessRoleIDbyName(String businessRoleName){
        ListIterator<BusinessRole> listIterator= businessRoleList.listIterator();
        int listIndex=0;
        while(listIterator.hasNext()){
            if(businessRoleList.get(listIndex).getBusinessRoleName().equals(businessRoleName)){
                return businessRoleList.get(listIndex).getBusinessRoleID();
            }

            listIterator.next();
            listIndex++;
        }
        return -1;
    }

    //BusinessRole
    public int getBusinessRoleIndex(int businessRoleID){
        ListIterator<BusinessRole> listIterator = businessRoleList.listIterator();
        int listIndex=0;

        while(listIterator.hasNext()){
            if (businessRoleList.get(listIndex).getBusinessRoleID()==(businessRoleID)) {
                return listIndex;
            }
            listIterator.next();
            listIndex++;
        }
        return -1;
    }

    public String getBusinessRoleName(int businessRoleID) {
        return businessRoleList.get(getBusinessRoleIndex(businessRoleID)).getBusinessRoleName();
    }

    //Login
    public int searchUsername(String username) {
        ListIterator<User> listIterator = userList.listIterator();
        int listIndex=0;

        while(listIterator.hasNext()){
            if (userList.get(listIndex).getUsername().toLowerCase().equals(username.toLowerCase())) {
                return listIndex;
            }
            listIterator.next();
            listIndex++;
        }
        return -1;
    }

    public boolean checkPassword(int listIndex, String password){
        if(userList.get(listIndex).getPassword().equals(password)){
            return true;
        }
        return false;
    }

    //Project
    public int getProjectListIndex (int projectID){
        ListIterator<Project> listIterator = projectList.listIterator();
        int listIndex=0;

        while(listIterator.hasNext()){
            if (projectList.get(listIndex).getProjectID()==projectID) {
                return listIndex;
            }
            listIterator.next();
            listIndex++;
        }
        return -1;
    }

    public void setProjectProgressState(int projectID){
        int listIndex=getProjectListIndex(projectID);
        projectList.get(listIndex).setProgressState(calculateProjectProgressState(projectID));
    }
    
    public int calculateProjectProgressState(int projectID) {
        ListIterator<Task> listIterator = taskList.listIterator();
        int listIndex=0;
        int taskQuantity=0;
        int completeTaskQuantity=0;
        double progressState;

        while(listIterator.hasNext()){
            if (taskList.get(listIndex).getProjectID()==projectID) {
                if(taskList.get(listIndex).getTaskStateID()==2){
                    completeTaskQuantity++;
                }
                taskQuantity++;
            }
            listIterator.next();
            listIndex++;
        }
        try {
            progressState=((((double)completeTaskQuantity)/taskQuantity)*100);
        }
        catch (ArithmeticException ex) {
            progressState=0;
        }
        return (int)progressState;
    }

        //Calculate ProjectTime
    public long getLoggedProjectTime(int projectID) {
        ListIterator<Task> listIterator = taskList.listIterator();
        int listIndex=0;
        long loggedTimeDuration=0;

        while(listIterator.hasNext()){
            if (taskList.get(listIndex).getProjectID()==projectID) {
                loggedTimeDuration+= getLoggedTaskTime(taskList.get(listIndex).getTaskID());
            }
            listIterator.next();
            listIndex++;
        }
        return loggedTimeDuration;
    }

    public long getPlannedProjectTime(int projectID) {
        ListIterator<Task> listIterator = taskList.listIterator();
        int listIndex=0;
        long plannedTimeDuration=0;

        while(listIterator.hasNext()){
            if (taskList.get(listIndex).getProjectID()==projectID) {
                plannedTimeDuration+= Lists.getInstance().taskList.get(listIndex).getPlannedTimeMinutes();
            }
            listIterator.next();
            listIndex++;
        }
        return plannedTimeDuration;
    }

    public boolean isProjectManager(int userID, int projectID) {
        int projectListIndex=getProjectListIndex(projectID);
        if(projectList.get(projectListIndex).getProjectManagerID()==userID) {
            return true;
        }
        return false;
    }

    public int getProjectTaskQuantity(int projectID) {
        ListIterator<Task> listIterator = taskList.listIterator();
        int listIndex=0;
        int taskQuantity=0;
        while(listIterator.hasNext()){
            if (taskList.get(listIndex).getProjectID()==projectID) {
                taskQuantity++;
            }
            listIterator.next();
            listIndex++;
        }
        return taskQuantity;
    }

    //ProjectMember
    public int getProjectMemberIndex(int projectMemberID){
        ListIterator<ProjectMember> listIterator = projectMemberList.listIterator();
        int listIndex=0;

        while(listIterator.hasNext()){
            if (projectMemberList.get(listIndex).getProjectMemberID()==(projectMemberID)) {
                return listIndex;
            }
            listIterator.next();
            listIndex++;
        }
        return -1;
    }

    public int getProjectMemberIndexFromUserID(int userID, int projectID) {
        ListIterator<ProjectMember> listIterator = projectMemberList.listIterator();
        int listIndex = 0;

        while (listIterator.hasNext()) {
            if(projectMemberList.get(listIndex).getUserID() == userID && projectMemberList.get(listIndex).getProjectID()==projectID) {
                return listIndex;
            }
            listIterator.next();
            listIndex++;

        }
        return -1;
    }

    public void deleteProjectMemberRecordsFromProjectID(int projectID) {
        ListIterator<ProjectMember> listIterator = projectMemberList.listIterator();
        DatabaseUpdate databaseUpdate = new DatabaseUpdate();

        while (listIterator.hasNext()) {
            if(projectMemberList.get(listIterator.nextIndex()).getProjectID() == projectID) {
                databaseUpdate.deleteObject(projectMemberList.get(listIterator.nextIndex()), projectMemberList.get(listIterator.nextIndex()).getProjectMemberID());
                listIterator.remove();
                System.out.println("ProjectMemberRecord [ID]: " + projectMemberList.get(listIterator.nextIndex()).getProjectMemberID() + " deleted");
            }
            listIterator.next();
        }
    }

    public void deleteProjectMemberRecordsFromUserID(int userID) {
        ListIterator<ProjectMember> listIterator = projectMemberList.listIterator();
        DatabaseUpdate databaseUpdate = new DatabaseUpdate();

        while (listIterator.hasNext()) {
            if(projectMemberList.get(listIterator.nextIndex()).getUserID() == userID) {
                databaseUpdate.deleteObject(projectMemberList.get(listIterator.nextIndex()), projectMemberList.get(listIterator.nextIndex()).getProjectMemberID());
                listIterator.remove();
                System.out.println("ProjectMemberRecord [ID]: " + projectMemberList.get(listIterator.nextIndex()).getProjectMemberID() + " deleted");
            }
            listIterator.next();
        }
    }

    //Task
    public int getTaskListIndex(int taskID){
        ListIterator<Task> listIterator = taskList.listIterator();
        int listIndex=0;

        while(listIterator.hasNext()){
            if (taskList.get(listIndex).getTaskID()==(taskID)) {
                return listIndex;
            }
            listIterator.next();
            listIndex++;
        }
        return -1;
    }

    public long getLoggedTaskTime(int taskID) {
        ListIterator<Activity> listIterator = activityList.listIterator();
        int listIndex=0;
        long loggedTimeDuration=0;

        while(listIterator.hasNext()){
            if (activityList.get(listIndex).getTaskID()==taskID) {
                loggedTimeDuration+=activityList.get(listIndex).getDuration();
            }
            listIterator.next();
            listIndex++;
        }
        return loggedTimeDuration;
    }

    public int getTaskActivityQuantity (int taskID) {
        ListIterator<Activity> listIterator = activityList.listIterator();
        int taskQuantity=0;
        while(listIterator.hasNext()){
            if (activityList.get(listIterator.nextIndex()).getTaskID()==taskID) {
                taskQuantity++;
            }
            listIterator.next();
        }
        return taskQuantity;
    }

    //TaskGroup
    public int getTaskGroupListIndex(int taskGroupID){
        ListIterator<TaskGroup> listIterator = taskGroupList.listIterator();
        int listIndex=0;

        while(listIterator.hasNext()){
            if (taskGroupList.get(listIndex).getTaskGroupID()==(taskGroupID)) {
                return listIndex;
            }
            listIterator.next();
            listIndex++;
        }
        return -1;
    }

    public int getTaskGroupTaskQuantity(int taskGroupID){
        ListIterator<Task> listIterator = taskList.listIterator();
        int taskGroupQuantity=0;
        while(listIterator.hasNext()){
            if (taskList.get(listIterator.nextIndex()).getTaskGroupID()==(taskGroupID)) {
                taskGroupQuantity++;
            }
            listIterator.next();
        }
        return taskGroupQuantity;
    }

    public void initializeAllTaskPlannedTimeDurations(){
        ListIterator<Task> listIterator = taskList.listIterator();

        while(listIterator.hasNext()){
            taskList.get(listIterator.nextIndex()).initializePlannedTimeDuration();
            listIterator.next();
        }
    }

    public String getTaskGroupNameIDString(int taskGroupID){
        String taskGroupString="";
        int taskGroupIndex=getTaskGroupListIndex(taskGroupID);
        if(taskGroupIndex==-1){ //Errorhandling if TaskGroupID not exists
            return taskGroupString;
        }
        taskGroupString=(taskGroupList.get(taskGroupIndex).getTaskGroupName()) + " [ID:'" + taskGroupList.get(taskGroupIndex).getTaskGroupID() + "']";

        return taskGroupString;
    }

    public int getTaskGroupIDFromTaskGroupNameIDString (String taskGroupNameIDString) {
        if (taskGroupNameIDString.equals("")) {
            return -1;
        }
        String[]taskGroupNameIDStringParts = taskGroupNameIDString.split("'", 4);
        try {
            return Integer.parseInt(taskGroupNameIDStringParts[1]);
        }
        catch (Exception ex) {
            return -1;
        }
    }

    //TaskState
    public int getTaskStateListIndex(int taskStateID){
        ListIterator<TaskState> listIterator = taskStateList.listIterator();
        int listIndex=0;

        while(listIterator.hasNext()){
            if (taskStateList.get(listIndex).getTaskStateID()==(taskStateID)) {
                return listIndex;
            }
            listIterator.next();
            listIndex++;
        }
        return -1;
    }

    public String getTaskStateName(int taskStateID){
        return taskStateList.get(getTaskStateListIndex(taskStateID)).getTaskStateName();
    }

    //Timemanagement
    public LocalDateTime parseStringToDateTime(String timeStamp){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(timeStamp, formatter);
        // "1986-04-08 12:30" ---> "2018-05-16T06:45"

        return dateTime;
    }

    public String parseDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter); // "2018-05-16T06:45" ---> "1986-04-08 12:30"
    }

    public Long calculateTimeDuration(LocalDateTime startDateTime, LocalDateTime endDateTime) {

        return Duration.between(startDateTime,endDateTime).toMinutes();
    }

    public String parseDurationToString(long timeInMinutes){
        Duration timeDuration=Duration.ofMinutes(timeInMinutes);
        return (timeDuration.toHours() + " Hours " + timeDuration.minusHours(timeDuration.toHours()).toMinutes() + " Minutes");
    }

    //User
    public int getUserListIndex(int userID){
        ListIterator<User> listIterator = userList.listIterator();
        int listIndex=0;

        while(listIterator.hasNext()){
            if (userList.get(listIndex).getUserID()==(userID)) {
                return listIndex;
            }
            listIterator.next();
            listIndex++;
        }
        return -1;
    }

    public String getUserNameString(int userID){
        String userNameString="";
        int userListIndex=getUserListIndex(userID);
        if(userListIndex==-1){ //Errorhandling for the case, that f.e. the ProjectManagerID not exists in the Userlist.
            return userNameString;
        }
        userNameString=userList.get(userListIndex).getFirstName()+" "+userList.get(userListIndex).getLastName();

        return userNameString;
}

    public String getUserNameIDString(int userID){
        String userNameString="";
        int userListIndex=getUserListIndex(userID);
        if(userListIndex==-1){ //Errorhandling for the case, that f.e. the ProjectManagerID not exists in the Userlist.
            return userNameString;
        }
        userNameString=(userList.get(userListIndex).getFirstName())+(" "+userList.get(userListIndex).getLastName())+" [ID:'"+userList.get(userListIndex).getUserID()+ "']";

        return userNameString;
    }

    public int getUserIDFromUserNameIDString (String userNameIDString) {
        if (userNameIDString.equals("")) {
            return -1;
        }
        String[]userNameIDStringParts = userNameIDString.split("'", 4);

        try {
            return Integer.parseInt(userNameIDStringParts[1]);
        }
        catch (Exception ex) {
            return -1;
        }
    }

    public long getLoggedUserProjectTime (int userID, int projectID) {
        ListIterator<Activity> listIterator = activityList.listIterator();
        long loggedTimeDuration=0;
        int currentTaskListIndex=0;

        while(listIterator.hasNext()){
            if (activityList.get(listIterator.nextIndex()).getUserID()==userID) {
                currentTaskListIndex=Lists.getInstance().getTaskListIndex(activityList.get(listIterator.nextIndex()).getTaskID());
                if(Lists.getInstance().getTaskList().get(currentTaskListIndex).getProjectID()==projectID)
                    loggedTimeDuration+=activityList.get(listIterator.nextIndex()).getDuration();
            }
            listIterator.next();
        }
        return loggedTimeDuration;
    }

    public long getLoggedUserPeriodActivityTimes(int userID, LocalDateTime beginnTime, LocalDateTime endTime) {
        ListIterator<Activity> listIterator = activityList.listIterator();
        long loggedTimeDuration=0;

        while(listIterator.hasNext()){
            Activity currentActivity = listIterator.next();
            if(currentActivity.getUserID() == userID) {
                if(currentActivity.getStartDateTime().isAfter(beginnTime)){
                    if(currentActivity.getEndDateTime().isBefore(endTime)){
                        loggedTimeDuration+=currentActivity.getDuration();
                    }
                }
            }
        }
        return loggedTimeDuration;
    }

    //Getter&Setter

    //Create an Instance of this Class
    public static Lists getInstance(){
        if (instance == null){
            instance = new Lists();
        }
        return instance;
    }

    public List<Object> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<Object> objectList) {
        this.objectList = objectList;
    }

    public List<ProjectMember> getProjectMemberList() {
        return projectMemberList;
    }

    public void setProjectMemberList(List<ProjectMember> projectMemberList) {
        this.projectMemberList = projectMemberList;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    public List<BusinessRole> getBusinessRoleList() {
        return businessRoleList;
    }

    public void setBusinessRoleList(List<BusinessRole> businessRoleList) {
        this.businessRoleList = businessRoleList;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public List<TaskGroup> getTaskGroupList() {
        return taskGroupList;
    }

    public void setTaskGroupList(List<TaskGroup> taskGroupList) {
        this.taskGroupList = taskGroupList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<TaskState> getTaskStateList() {
        return taskStateList;
    }

    public void setTaskStateList(List<TaskState> taskStateList) {
        this.taskStateList = taskStateList;
    }
}
