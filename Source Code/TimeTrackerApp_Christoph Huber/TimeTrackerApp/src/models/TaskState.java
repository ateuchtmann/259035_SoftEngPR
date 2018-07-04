package models;


public class TaskState {
    private int taskStateID;
    private String taskStateName;

    //Constructors
    public TaskState() {
    }

    public TaskState(int taskStateID, String taskStateName) {
        this.taskStateID = taskStateID;
        this.taskStateName = taskStateName;
    }

    //Getter&Setter
    public int getTaskStateID() {
        return taskStateID;
    }

    public void setTaskStateID(int taskStateID) {
        this.taskStateID = taskStateID;
    }

    public String getTaskStateName() {
        return taskStateName;
    }

    public void setTaskStateName(String taskStateName) {
        this.taskStateName = taskStateName;
    }
}