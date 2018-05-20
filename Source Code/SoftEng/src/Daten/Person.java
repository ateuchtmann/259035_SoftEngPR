package Daten;

public class Person {
	
	String vorname;
	String nachname;
	Projekt projekt;
	Aufgabenbereich aBereich;
	Aufgabe aufgabe;
	
	public void setVorname(String v) {
    	this.vorname = v;
    }
	
	public void setNachname(String n) {
		this.nachname = n;
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
	
	public String getVorname() {
		return vorname;
	}
	
	public String getNachname() {
		return nachname;
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
