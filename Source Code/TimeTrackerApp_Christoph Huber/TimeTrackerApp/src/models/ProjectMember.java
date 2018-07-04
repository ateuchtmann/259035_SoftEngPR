package models;

import javax.persistence.*;

@Entity
@Table(name="projectMember")
public class ProjectMember {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="projectMemberID")
	private int projectMemberID;
	@Column(name="userID")
	private int userID;
	@Column(name="projectID")
	private int projectID;
	//ObservableListItems
	@Transient
	private String businessRoleName;

	//Constructors
	public ProjectMember() {
	}

	public ProjectMember(int userID, int projectID) {
		this.userID = userID;
		this.projectID = projectID;
	}

	//Getter and Setter
	public int getProjectMemberID() {
		return projectMemberID;
	}

	public void setProjectMemberID(int projectMemberID) {
		this.projectMemberID = projectMemberID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
}
