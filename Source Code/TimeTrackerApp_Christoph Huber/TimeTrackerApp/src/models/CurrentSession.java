package models;

public class CurrentSession {
    private static CurrentSession instance;
    private int userID;
    private int businessRoleID;
    private int sessionUserID;
    private int sessionProjectID;
    private int sessionTaskID;
    private int sessionTaskGroupID;
    private int sessionActivitiyID;
    private int memoryNumber;

    //Constructor
    public CurrentSession() {
    }

    //Getter&Setter

    //Create an Instance of this Class
    public static CurrentSession getInstance(){
        if (instance == null){
            instance = new CurrentSession();
        }
        return instance;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getBusinessRoleID() {
        return businessRoleID;
    }

    public void setBusinessRoleID(int businessRoleID) {
        this.businessRoleID = businessRoleID;
    }

    public int getSessionUserID() {
        return sessionUserID;
    }

    public void setSessionUserID(int sessionUserID) {
        this.sessionUserID = sessionUserID;
    }

    public int getSessionProjectID() {
        return sessionProjectID;
    }

    public void setSessionProjectID(int sessionProjectID) {
        this.sessionProjectID = sessionProjectID;
    }

    public int getSessionTaskID() {
        return sessionTaskID;
    }

    public void setSessionTaskID(int sessionTaskID) {
        this.sessionTaskID = sessionTaskID;
    }

    public int getSessionTaskGroupID() {
        return sessionTaskGroupID;
    }

    public void setSessionTaskGroupID(int sessionTaskGroupID) {
        this.sessionTaskGroupID = sessionTaskGroupID;
    }

    public int getSessionActivitiyID() {
        return sessionActivitiyID;
    }

    public void setSessionActivitiyID(int sessionActivitiyID) {
        this.sessionActivitiyID = sessionActivitiyID;
    }

    public int getMemoryNumber() {
        return memoryNumber;
    }

    public void setMemoryNumber(int memoryNumber) {
        this.memoryNumber = memoryNumber;
    }
}




