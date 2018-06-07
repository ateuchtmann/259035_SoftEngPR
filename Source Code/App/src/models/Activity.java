package models;

/* Classname: Activity
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

public class Activity {
	
	private int id; 
	private Time start;
	private Time end;
	private String description;
	private Person person;

	public Activity(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public Time getStart() {
		return this.start;
	}

	public Time getEnd() {
		return this.end;
	}

	public String getDescrn() {
		return this.description;
	}

	public Person getPerson() {
		return this.person; 
	}

	public void addPerson(Person p) {
		this.person = p; 
	}

	public void setStart(Time t) {
		this.start = t; 
	}

	public void setEnd(Time t) {
		this.end = t; 
	}

	public void setDescr(String description) {
		this.description = description; 
	}

}

	
	
