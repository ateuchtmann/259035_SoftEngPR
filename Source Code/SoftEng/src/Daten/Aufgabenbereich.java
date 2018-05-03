package Daten;

import java.util.ArrayList;
import java.util.List;

public class Aufgabenbereich {
	
	private String name;
	private List<Aufgabe> aufgaben = new ArrayList<>();
	private List<Person> personen = new ArrayList<>();

	public void setName(String name) {
    	this.name = name;
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

	public List getAufgaben() {
		return aufgaben;
	}
	
    public List getPeronen() {
    	return personen;
    }

}
