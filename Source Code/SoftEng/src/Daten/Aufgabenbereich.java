package Daten;

import java.util.ArrayList;
import java.util.List;

public class Aufgabenbereich {
	
	private String name;
	private String beschreibung;
	private List<Aufgabe> aufgaben = new ArrayList<>();
	private List<Person> personen = new ArrayList<>();
	
	public void setName(String name) {
    	this.name = name;
    }
	
    public void setBeschreibung(String b) {
    	this.beschreibung = b;
    }
	
    public void addAufgabe(Aufgabe a) {
    	aufgaben.add(a);
    }
    
    public void addPerson(Person p) {
    	personen.add(p);
    }
    
    public String getName() {
    	return name;
    }
	
	public String getBeschreibung() {
		return beschreibung;
	}
	
	public List getAufgaben() {
		return aufgaben;
	}
	
    public List getPeronen() {
    	return personen;
    }

}
