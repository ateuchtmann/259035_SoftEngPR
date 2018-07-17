package models;

import java.util.ArrayList;
import java.util.List;

/* Classname: TaskGroup
*
* Programmers/Authors: 
* 
*  1.Milos Tomic
*  2.Maja Dusanic 
*  3.Alexander Teuchtmann 
*  4.Andrea Aistleithner 
*  5.Christopher Huber 
* 
*  Date: 04.07.2018
*  Version: 1.0.20
*
* Copyright notice
* - Programm is being build by the above mentioned programmers
* 
* Purpose of program: 
* - Time scheduling of projects, tasks etc.
*/

public class TaskGroup {

	private int id;
	private String name;
	private List<Task> taskList;
	private List<Person> personList;

	public TaskGroup(int id) {
		this.id = id;
		taskList = new ArrayList<>();
		personList = new ArrayList<>();
	}

	public int getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addTask(Task a) {
		this.taskList.add(a);
	}

	public void addPerson(Person p) {
		this.personList.add(p);
	}

	public String getName() {
		return this.name;
	}

	public List<Task> getTaskList() {
		return this.taskList;
	}

	public List<Person> getPersonList() {
		return this.personList;
	}

	public void deleteTask(Task a) {
		this.taskList.remove(a);
	}
	
	public void setTaskList(List<Task> t){
		taskList = t;
	}
	
}
