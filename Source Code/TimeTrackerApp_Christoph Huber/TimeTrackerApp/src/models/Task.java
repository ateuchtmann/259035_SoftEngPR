package models;

import javax.persistence.*;
import java.time.Duration;

@Entity
@Table(name="task")
public class Task {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="taskID")
	private int taskID;
	@Column(name="taskName")
	private String taskName;
	@Column(name="description")
	private String description;
	@Column(name="taskStateID")
	private int taskStateID;
	@Column(name="projectID")
	private int projectID;
	@Column(name="taskGroupID")
	private int taskGroupID;
	@Column(name="taskOwnerID")
	private int taskOwnerID;
	@Column(name="plannedTimeMinutes")
	private long plannedTimeMinutes;
	@Transient
	private Duration plannedTimeDuration;
	//ObservableListItems
	@Transient
	private String taskStateString;
	@Transient
	private String taskOwnerString;
	@Transient
	private String plannedTimeString;
	@Transient
	private String loggedTimeString;

	//Constructors
	public Task() {
	}

	public Task(String taskName, String description, int taskState, int projectID, int taskGroupID, int taskOwnerID, int plannedTimeMinutes) {
		this.taskName = taskName;
		this.description = description;
		this.taskStateID = taskState;
		this.plannedTimeMinutes = plannedTimeMinutes;
		this.projectID = projectID;
		this.taskGroupID = taskGroupID;
		this.taskOwnerID = taskOwnerID;
		this.plannedTimeDuration=Duration.ofMinutes(plannedTimeMinutes);
		this.taskStateString = "";
		this.taskOwnerString = "";
		this.plannedTimeString = "";
		this.loggedTimeString = "";
	}

	public Task(String taskName, String description, int taskState, int projectID, int taskGroupID, int taskOwnerID, Duration plannedTimeDuration) {
		this.taskName = taskName;
		this.description = description;
		this.taskStateID = taskState;
		this.projectID = projectID;
		this.taskGroupID = taskGroupID;
		this.taskOwnerID = taskOwnerID;
		this.plannedTimeDuration = plannedTimeDuration;
		this.plannedTimeMinutes = plannedTimeDuration.toMinutes();
		this.taskStateString = "";
		this.taskOwnerString = "";
		this.plannedTimeString = "";
		this.loggedTimeString = "";
	}

	//Getter and Setter

		//Individual Getter&Setter
	public void setPlannedTimeMinutes(long plannedTimeMinutes) {
		this.plannedTimeMinutes = plannedTimeMinutes;
		this.plannedTimeDuration = Duration.ofMinutes(plannedTimeMinutes);
	}

	public void setPlannedTimeDuration(Duration plannedTimeDuration) {
		this.plannedTimeDuration = plannedTimeDuration;
		this.plannedTimeMinutes = plannedTimeDuration.toMinutes();
	}

	public void initializePlannedTimeDuration() {
		this.plannedTimeDuration = Duration.ofMinutes(plannedTimeMinutes);
	}

	//Classic Getter&Setter
	public String getPlannedTimeString() {
		return plannedTimeString;
	}

	public void setPlannedTimeString(String plannedTimeString) {
		this.plannedTimeString = plannedTimeString;
	}

	public String getLoggedTimeString() {
		return loggedTimeString;
	}

	public void setLoggedTimeString(String loggedTimeString) {
		this.loggedTimeString = loggedTimeString;
	}

	public String getTaskOwnerString() {
		return taskOwnerString;
	}

	public void setTaskOwnerString(String taskOwnerString) {
		this.taskOwnerString = taskOwnerString;
	}

	public void setTaskStateID(int taskStateID) {
		this.taskStateID = taskStateID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String getTaskStateString() {
		return taskStateString;
	}

	public void setTaskStateString(String taskStateString) {
		this.taskStateString = taskStateString;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getProjectID() {
		return projectID;
	}

	public int getTaskGroupID() {
		return taskGroupID;
	}

	public void setTaskGroupID(int taskGroupID) {
		this.taskGroupID = taskGroupID;
	}

	public int getTaskOwnerID() {
		return taskOwnerID;
	}

	public void setTaskOwnerID(int taskOwnerID) {
		this.taskOwnerID = taskOwnerID;
	}

	public int getTaskStateID() {
		return taskStateID;
	}

	public long getPlannedTimeMinutes() {
		return plannedTimeMinutes;
	}
	public Duration getPlannedTimeDuration() {
		return plannedTimeDuration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
