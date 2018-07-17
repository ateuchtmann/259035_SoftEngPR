package models;

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
*  Date: 04.07.2018
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

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
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
	
	public int getTaskGroupNr(){
		int count = 0;
		
		for(TaskGroup g : this.taskGroupList){
			count ++;
		}
		return count;
	}
	
	public int getTaskNr(){
		int count = 0;
		
		for(TaskGroup g : this.taskGroupList){
			for(Task a: g.getTaskList()){
				count ++;
			}
		}
		return count;
	}
	
	public int getPersonNr(){
		int count = 0;
		for(Person p : personList){
			count ++;
		}
		return count;
	}
	
	
	public double getProjectTime(){
		double time = 0;
		
		for(TaskGroup g : this.taskGroupList){
			for(Task a: g.getTaskList()){
				for(Activity act : a.getActivities()){
					time = time + act.getTimeHour();
				}
			
			}
		}
		return time;
	}
	
	public double getTaskGroupTime(TaskGroup t){
		double time = 0;
		
		
			for(Task a: t.getTaskList()){
				for(Activity act : a.getActivities()){
					time = time + act.getTimeHour();
				}
			
			}
		
		return time;
	}
	
	
	public double getPlanTime(){
		double time = 0;
		double planTimeHour;
		
		for(TaskGroup g : this.taskGroupList){
			for(Task a: g.getTaskList()){
				planTimeHour = a.getPlanTime().getHour() + (a.getPlanTime().getMin()/60);
				time = time + planTimeHour;
			
			}
		}
		return time;
	}
	
	
	public double getPlanTime(TaskGroup t){
		double time = 0;
		double planTimeHour;
		
			for(Task a: t.getTaskList()){
				planTimeHour = a.getPlanTime().getHour() + (a.getPlanTime().getMin()/60);
				time = time + planTimeHour;
			
			}
		
		return time;
	}
	
}
