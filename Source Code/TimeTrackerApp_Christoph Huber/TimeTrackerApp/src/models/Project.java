package models;

import javax.persistence.*;


@Entity
@Table(name="project")
public class Project {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="projectID")
	private int projectID;
	@Column(name="projectName")
	private String projectName;
	@Column(name="description")
	private String description;
	@Column(name="projectManagerID")
	private int projectManagerID;
	@Transient
	private int progressState;
	//ObservableListItems
	@Transient
	private String projectManagerNameString;
	@Transient
	private String progressStateString;

	//Constructors
	public Project() {
	}

	public Project(String projectName, String description, int projectManagerID) {
		this.projectName = projectName;
		this.description = description;
		this.projectManagerID = projectManagerID;
		this.progressState=0;
		this.projectManagerNameString = "";
		this.progressStateString = "";
	}

	//Getter and Setter

	public String getProjectManagerNameString() {
		return projectManagerNameString;
	}

	public void setProjectManagerNameString(String projectManagerNameString) {
		this.projectManagerNameString = projectManagerNameString;
	}

	public String getProgressStateString() {
		return progressStateString;
	}

	public void setProgressStateString(String progressStateString) {
		this.progressStateString = progressStateString;
	}

	public void setProjectManagerID(int projectManagerID) {
		this.projectManagerID = projectManagerID;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getProjectManagerID() {
		return projectManagerID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProgressState() {
		return progressState;
	}

	public void setProgressState(int progressState) {
		this.progressState = progressState;
	}
}
