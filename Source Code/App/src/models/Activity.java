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
	private int year; 
	

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
		return this.day;
	}
	
	@SuppressWarnings("deprecation")
	public void setDay(int day) {	
		this.day = day; 
	}

	@SuppressWarnings("deprecation")
	public int getMonth() {
		return this.month;
	}
	@SuppressWarnings("deprecation")
	public void setMonth(int month) {
		this.month = month; 
	}
	
	public int getYear() {
		return this.year;
	}
	@SuppressWarnings("deprecation")
	public void setYear(int year) {
		this.year = year; 
	}
	
	public void addPerson(Person p) {
		this.person = p; 
	}

	public void setStart(Time t) {
		
		this.start = t; 
		
		//to save the current sysdate when the activity is created
		Date actDate = new Date(); 
		this.setDay(actDate.getDate());
		this.setMonth(actDate.getMonth() + 1);
		this.setYear(actDate.getYear() + 1900);
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

	
	
