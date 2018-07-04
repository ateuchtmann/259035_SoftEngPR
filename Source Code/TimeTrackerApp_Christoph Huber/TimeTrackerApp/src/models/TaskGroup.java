package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="taskGroup")
public class TaskGroup {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="taskGroupID")
	private int taskGroupID;
	@Column(name="taskGroupName")
	private String taskGroupName;
	@Column(name="projectID")
	private int projectID;
	//ObservableListItems
	@Transient
	private int taskQuantity;

	//Constructors
	public TaskGroup() {
	}

	public TaskGroup(String taskGroupName, int projectID) {
		this.taskGroupName = taskGroupName;
		this.projectID = projectID;
		this.taskQuantity=0;
	}

	//Getter and Setter
	public int getTaskQuantity() {
		return taskQuantity;
	}

	public void setTaskQuantity(int taskQuantity) {
		this.taskQuantity = taskQuantity;
	}

	public int getTaskGroupID() {
		return taskGroupID;
	}

	public void setTaskGroupID(int taskGroupID) {
		this.taskGroupID = taskGroupID;
	}

	public String getTaskGroupName() {
		return taskGroupName;
	}

	public void setTaskGroupName(String taskGroupName) {
		this.taskGroupName = taskGroupName;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
}
