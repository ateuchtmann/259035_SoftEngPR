package views;

import java.awt.Color;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;

import db_load.LoadProject;
import models.Project;
import models.ProjectList;
import models.Task;
import models.TaskGroup;

import java.awt.Font;
import java.util.List;


/* Classname: CreateTaskGroupReportView
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
*  Version: 1.0.23
*
* Copyright notice
* - Programm is being build by the above mentioned programmers
* 
* Purpose of program: 
* - Time scheduling of projects, tasks etc.
*/

public class CreateTaskGroupReportView {

	private JFrame creTaskGroupReportFrame;
	private List<Project> list;
	private ProjectList listProject;
	int yCoor = 100;
	int xCoor = 50;
	

	public CreateTaskGroupReportView() {
		initialize();
	}


	private void initialize() {
		creTaskGroupReportFrame = new JFrame();
		creTaskGroupReportFrame.setBounds(0,0, 1920, 1080);
		creTaskGroupReportFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		creTaskGroupReportFrame.getContentPane().setLayout(null);
		creTaskGroupReportFrame.setVisible(true);
		creTaskGroupReportFrame.getContentPane().setBackground(new Color(255, 255, 255));
		
		JLabel lblTaskGroupReport = new JLabel("Aufgabenbereichsreport:");
		lblTaskGroupReport.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTaskGroupReport.setBounds(27, 28, 345, 31);
		creTaskGroupReportFrame.getContentPane().add(lblTaskGroupReport);
		
		
		listProject = new LoadProject().everythingFromProjects();
		list = listProject.getProjectList();
		
		
		for(Project p : list){
			for(TaskGroup t :p.getTaskGroups()){
				
				TaskGroupReportView pr = new TaskGroupReportView(creTaskGroupReportFrame, xCoor, yCoor);
				
				pr.setName(t.getName());
				String tasks = " ";
				
				List<Task> tsk = t.getTaskList();
				for(Task a : tsk){
					
					tasks = tasks + a.getName() + ";   ";
					
				}
				
				pr.setTasks(tasks);
				
				double time = p.getTaskGroupTime(t);
				
				double diffHours=0;
				double diffMinutes=0;	
				diffHours = (int) time;
				diffMinutes = time-diffHours;
				diffMinutes *= 60;
				String diffTimeString = (int)diffHours+":"+(int)diffMinutes;
				
				pr.setCurrTime(diffTimeString);
				
				
				double planTime = p.getPlanTime(t);
				diffHours = 0;
				diffMinutes = 0;
				diffHours = (int) planTime;
				diffMinutes = planTime-diffHours;
				diffMinutes *= 60;
				String timeString = (int)diffHours+":"+(int)diffMinutes;
				pr.setPlanTime(timeString);
				
				
				int diff = (int) ((time / planTime) * 100);
				pr.setProgressBar(diff);
				
				
				
				// calculating correct position of every project
							if (yCoor < 661) {
								yCoor = yCoor + 315;
							} else {
								yCoor = 101;
								xCoor = xCoor + 650;
							}
				
	
			}//end for	
		}//end for
			
	}
}
