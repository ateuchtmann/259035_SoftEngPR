package models;

import java.util.ArrayList;
import java.util.List;

/* Classname: Person
*
* Programmers/Authors: 
* 
*  1.Milos Tomic
*  2.Maja Dusanic 
*  3.Alexander Teuchtmann 
*  4.Andrea Aistleithner 
*  5.Christopher Huber 
* 
*  Date: 22.05.2018
*  Version: 1.0.20
*
* Copyright notice
* - Programm is being build by the above mentioned programmers
* 
* Purpose of program: 
* - Time scheduling of projects, tasks etc.
*/

public class Person {

	private int id;
	private String firstname;
	private String lastname;
	private List<Project> projectList;
	private List<TaskGroup> taskGroupList;
	private List<Task> taskList;
	private List<Activity> activityList;

	public Person(int id) {
		this.id = id;
		projectList = new ArrayList<>();
		taskGroupList = new ArrayList<>();
		taskList = new ArrayList<>();
		activityList = new ArrayList<>();
	}

	public int getId() {
		return this.id;
	}

	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void addProjekt(Project p) {
		this.projectList.add(p);
	}

	public void addTskGroup(TaskGroup a) {
		this.taskGroupList.add(a);
	}

	public void addTsk(Task a) {
		this.taskList.add(a);
	}

	public String getFirstName() {
		return this.firstname;
	}

	public String getLastName() {
		return this.lastname;
	}

	public List<Project> getProjects() {
		return this.projectList;
	}

	public List<TaskGroup> getTskGroups() {
		return this.taskGroupList;
	}

	public List<Task> getTasks() {
		return this.taskList;
	}

	public void addActivity(Activity a) {
		this.activityList.add(a);
	}

	public List<Activity> getActivities() {
		return this.activityList;
	}

}
