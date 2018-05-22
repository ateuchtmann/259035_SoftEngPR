package Files;
import java.util.ArrayList;
import java.util.List;

/* Classname: ProjectList
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

public class ProjectList {
	
	private List<Project> projectList = new ArrayList<>();
	
	public void addProject(Project p) {
		projectList.add(p);
	}
	
	public List getProjectList() {
		return projectList;
	}

}
