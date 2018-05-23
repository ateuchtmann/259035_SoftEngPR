package files;

import java.util.ArrayList;
import java.util.List;

/* Classname: Task
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

public class Task {

	private String descr;
	private List<Person> personList = new ArrayList<>();
	private List<Activity> activityList = new ArrayList<>();
	
	
    public void setDescr(String b) {
    	this.descr= b;
    }
	
    public void addPerson(Person p) {
    	personList.add(p);
    }
    
    public void addActivity(Activity a){
    	activityList.add(a);
    }
	
	public String getDescr() {
		return descr;
	}

    public List getPersonList() {
    	return personList;
    }
    
    
}
