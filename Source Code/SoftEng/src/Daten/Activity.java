package Daten;

public class Activity {
	private Person person;
	private Time start;
	private Time end;
	private String beschreibung;
	
	
	public Person getPerson(){
		return this.person;
	}
	
	public Time getStart(){
		return this.start;
	}
	
	public Time getEnd(){
		return this.end;
	}
	
	public String getBeschreibung(){
		return this.beschreibung;
	}
	
	public void setPerson(Person p){
		this.person = p;
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
