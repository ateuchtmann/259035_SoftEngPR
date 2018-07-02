package models;

import java.util.ArrayList;
import java.util.List;

/* Classname: PersonList
*
* Programmers/Authors: 
* 
*  1.Milos Tomic
*  2.Maja Dusanic 
*  3.Alexander Teuchtmann 
*  4.Andrea Aistleithner 
*  5.Christopher Huber 
* 
*  Date: 19.06.2018
*  Version: 1.0.20
*
* Copyright notice
* - Programm is being build by the above mentioned programmers
* 
* Purpose of program: 
* - Time scheduling of projects, tasks etc.
*/

public class PersonList {

	private List<Person> personList = new ArrayList<>();

	public void addPerson(Person p) {
		this.personList.add(p);
	}

	public List<Person> getPersonList() {
		return this.personList;
	}

	public void deletePerson(Person p) {

		this.personList.remove(p);
	}

}
