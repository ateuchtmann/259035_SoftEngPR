package files;

import java.util.ArrayList;
import java.util.List;

/* Classname: Project
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

public class Project {

	private int id;
	private String name;
	private String description;
	private List<Person> personList;
	private List<TaskGroup> taskGroupList;

	public Project(int id) {
		this.id = id;
		personList = new ArrayList<>();
		taskGroupList = new ArrayList<>();
	}

	public int getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescr(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public String getDescr() {
		return this.description;
	}

	public void addTaskGroup(TaskGroup a) {
		taskGroupList.add(a);
	}

	public void addPerson(Person p) {
		this.personList.add(p);
	}

	public List<TaskGroup> getTaskGroups() {
		return this.taskGroupList;
	}

	public List<Person> getPersonList() {
		return this.personList;
	}

	public void setPersonList(List<Person> persons) {
		this.personList = persons;
	}

	public void setTaskGroupList(List<TaskGroup> taskgroups) {
		this.taskGroupList = taskgroups;
	}

	public void deleteTaskGroup(TaskGroup a) {
		this.taskGroupList.remove(a);
	}

	public List<Task> getTasks() {

		List<Task> taskList = new ArrayList<>();

		for (TaskGroup g : this.taskGroupList) {
			for (Task a : g.getTaskList()) {
				taskList.add(a);
			}
		}
		return taskList; 
	}

}
