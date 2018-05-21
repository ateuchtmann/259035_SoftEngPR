package Daten;

import java.util.List;

public class Activity {
	private Time start;
	private Time end;
	private String beschreibung;
	private List<Person> personen;
	
	
	
	public Time getStart(){
		return this.start;
	}
	
	public Time getEnd(){
		return this.end;
	}
	
	public String getBeschreibung(){
		return this.beschreibung;
	}
	
	public List getPersonen(){
		return this.personen;
	}
	
	public void setPersonen(List p){
		this.personen = p;
	}
	
	public void setStart(Time t){
		this.start = t;
	}
	
	public void setEnd(Time t){
		this.end = t;
	}
	
	public void setBeschreibung(String b){
		this.beschreibung = b;
	}
	
	
	
	

}
