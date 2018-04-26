package Daten;

import java.util.ArrayList;
import java.util.List;

public class Projekt {
	
	private String name;
	private String beschreibung;
	private List<Aufgabenbereich> aBereiche = new ArrayList<>();
	private List<Person> personen = new ArrayList<>();
	
    public void setName(String name) {
    	this.name = name;
    }
	
    public void setBeschreibung(String b) {
    	this.beschreibung = b;
    }
	
    public String getName() {
    	return name;
    }
	
    public void addAufgabenberich(Aufgabenbereich a) {
    	aBereiche.add(a);
    }
    
    public void addPerson(Person p) {
    	personen.add(p);
    }
    
	public String getBeschreibung() {
		return beschreibung;
	}
	
	public List getAufgabenbereiche() {
		return aBereiche;
	}
	
    public List getPeronen() {
    	return personen;
    }
	
	
	
	
}
