package models;

import java.util.Date;

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
	private int day;
	private int month;
	Date actDate = new Date();

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

	public String getDescription() {
		return this.description;
	}

	public Person getPerson() {
		return this.person; 
	}
	
	@SuppressWarnings("deprecation")
	public int getDay() {
		return actDate.getDay();
	}
	
	public void setDay(Date d) {
		this.day = ;
	}

	@SuppressWarnings("deprecation")
	public int getMonth() {
		return actDate.getMonth();
	}
	
	public void setMonth() {
		this.month = ;
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

	public void setDescription(String description) {
		this.description = description; 
	}
	
	public double getTimeHour(){
		double time = 0;
		double sumStartTime = start.getHour() + (start.getMin()/60);
		double sumEndTime= end.getHour() + (end.getMin()/60);
		time = sumEndTime - sumStartTime; 
		return time;
		
	}
	
}

	
	
