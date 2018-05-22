package Files;

import java.util.ArrayList;
import java.util.List;

/* Classname: TaskGroup
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

public class TaskGroup {
	
	private String name;
	private List<Task> taskList = new ArrayList<>();
	private List<Person> personList = new ArrayList<>();

	public void setName(String name) {
    	this.name = name;
    }
	
    public void addTsk(Task a) {
    	taskList.add(a);
    }
    
    public void addPerson(Person p) {
    	personList.add(p);
    }
    
    public String getName() {
    	return name;
    }

	public List getTaskList() {
		return taskList;
	}
	
    public List getPeronList() {
    	return personList;
    }

}
