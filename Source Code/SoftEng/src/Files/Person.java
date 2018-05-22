package Files;

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
	
	String firstName;
	String surName;
	Project project;
	TaskGroup tskGroup;
	Task tsk;
	
	public void setFirstName(String fs) {
    	this.firstName = fs;
    }
	
	public void setSurName(String sn) {
		this.surName = sn;
	}
	
	public void setProjekt(Project p) {
		this.project = p;
	}
	
	public void setTskGroup(TaskGroup a) {
		this.tskGroup = a;
	}
	
	public void setTsk(Task a) {
		this.tsk = a;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getSurName() {
		return surName;
	}
	
	public Project getProjekt() {
		return project;
	}
	
	public TaskGroup getTskGroup() {
		return tskGroup;
	}
	
	public Task getTask() {
		return tsk;
	}
	
	


}
