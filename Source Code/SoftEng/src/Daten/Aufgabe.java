package Daten;

import java.util.ArrayList;
import java.util.List;

public class Aufgabe {

	private String beschreibung;
	private List<Person> personen = new ArrayList<>();
	private List<Activity> activities = new ArrayList<>();
	
    public void setBeschreibung(String b) {
    	this.beschreibung = b;
    }
	
    public void addPerson(Person p) {
    	personen.add(p);
    }
    
    public void addActivity(Activity a){
    	activities.add(a);
    }
	
	public String getBeschreibung() {
		return beschreibung;
	}

    public List getPeronen() {
    	return personen;
    }
    
    
}
