package models;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userID")
	private int userID;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "businessRoleID")
	private int businessRoleID;
	//ObservableListItems
	@Transient
	private String businessRoleName;
	@Transient
	private String userNameString;
	@Transient
	private int completedProjectTasks;
	@Transient
	private int assignedProjectTasks;

	//Constructors
	public User() {
	}

	public User(String firstName, String lastName, String username, String password, int businessRoleID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.businessRoleID = businessRoleID;
		this.businessRoleName="";
		this.userNameString="";
		this.completedProjectTasks=0;
		this.assignedProjectTasks=0;
	}

	//Getter and Setter
	public int getCompletedProjectTasks() {
		return completedProjectTasks;
	}

	public void setCompletedProjectTasks(int completedProjectTasks) {
		this.completedProjectTasks = completedProjectTasks;
	}

	public int getAssignedProjectTasks() {
		return assignedProjectTasks;
	}

	public void setAssignedProjectTasks(int assignedProjectTasks) {
		this.assignedProjectTasks = assignedProjectTasks;
	}

	public String getUserNameString() {
		return userNameString;
	}

	public void setUserNameString(String userNameString) {
		this.userNameString = userNameString;
	}

	public String getBusinessRoleName() {
		return businessRoleName;
	}

	public void setBusinessRoleName(String businessRoleName) {
		this.businessRoleName = businessRoleName;
	}

	public void setBusinessRoleID(int businessRoleID) {
		this.businessRoleID = businessRoleID;
	}

	public void updateOlAttributes(){
		this.businessRoleName=Lists.getInstance().getBusinessRoleName(this.businessRoleID);
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getBusinessRoleID() {
		return businessRoleID;
	}

}