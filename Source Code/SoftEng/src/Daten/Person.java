package Daten;

public class Person {
	
	String name;
	Projekt projekt;
	Aufgabenbereich aBereich;
	Aufgabe aufgabe;
	
	public void setName(String name) {
    	this.name = name;
    }
	
	
	public void setProjekt(Projekt p) {
		this.projekt = p;
	}
	
	public void setAufgabenberich(Aufgabenbereich a) {
		this.aBereich = a;
	}
	
	public void setAufgabe(Aufgabe a) {
		this.aufgabe = a;
	}
	
	public String getName() {
		return name;
	}
	
	public Projekt getProjekt() {
		return projekt;
	}
	
	public Aufgabenbereich getAufgabenbereich() {
		return aBereich;
	}
	
	public Aufgabe getAufgabe() {
		return aufgabe;
	}
	
	


}
