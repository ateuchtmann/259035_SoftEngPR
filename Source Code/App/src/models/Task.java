package models;

import java.util.ArrayList;
import java.util.List;

/* Classname: Task
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

public class Task {

	private int id;
	private String name;
	private List<Person> personList;
	private Time planTime;
	private List<Activity> activityList;

	public Task(int id) {
		this.id = id;
		personList = new ArrayList<>();
		activityList = new ArrayList<>();
	}

	public int getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addPerson(Person p) {
		this.personList.add(p);
	}

	public void setPlanTime(Time planTime) {
		this.planTime = planTime;
	}

	public Time getPlanTime() {
		return this.planTime;
	}

	public void addActivity(Activity a) {
		this.activityList.add(a);
	}

	public String getName() {
		return this.name;
	}

	public List<Person> getPersonList() {
		return this.personList;
	}

	public List<Activity> getActivities() {
		return this.activityList;
	}

	public void deleteActivity(Activity a) {
		this.activityList.remove(a);
	}
}
