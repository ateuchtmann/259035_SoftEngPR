package models;

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
*  Date: 04.07.2018
*  Version: 1.0.20
*
* Copyright notice
* - Programm is being build by the above mentioned programmers
* 
* Purpose of program: 
* - Time scheduling of projects, tasks etc.
*/

public class ProjectList {

	private List<Project> projectList;

	public ProjectList() {
		projectList = new ArrayList<>();
	}

	public void addProject(Project p) {
		this.projectList.add(p);
	}

	public List<Project> getProjectList() {
		return this.projectList;
	}

	public void deleteProject(Project p) {
		this.projectList.remove(p);
	}
	
	
	public String getPerson(Person p){
		String s = " ";
		
		for(Project project : projectList){
			for(Person person : project.getPersonList()){
				if(p.getLastName().equals(person.getLastName())){
					s = s + project.getName();
				}
				
			}
		}
		
		return s;
	}
	
	

	public String getActivity(Person p){
		String s = " ";
		
		for(Project project : projectList){
			for(TaskGroup taskGroup : project.getTaskGroups()){
				for(Task task : taskGroup.getTaskList()){
					for(Activity act : task.getActivities()){
						
						if(p.getLastName().equals(act.getPerson().getLastName())){
							s = s + act.getDescription();
						}
					}
				}
			}
		}
		return s;
	}
	
	
	public double getPersonTime(Person p){
		double s = 0;
		
		for(Project project : projectList){
			for(TaskGroup taskGroup : project.getTaskGroups()){
				for(Task task : taskGroup.getTaskList()){
					for(Activity act : task.getActivities()){
						
						if(p.getLastName().equals(act.getPerson().getLastName())){
							s = s + act.getTimeHour();
						}
					}
				}
			}
		}
		return s;
	}
	
	
	
	
	
	

}
