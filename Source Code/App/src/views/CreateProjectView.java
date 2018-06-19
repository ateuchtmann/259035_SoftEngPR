package views;

import java.awt.Font;
import javax.swing.*;

import db_load.LoadActivity;
import db_load.LoadPerson;
import db_load.LoadProject;
import db_load.LoadTask;
import db_load.LoadTaskGroup;
import db_save.SaveProject;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import models.*;
import sounds.Sound;

import java.awt.SystemColor;

/* Classname: CreateProjectView
*
* Programmers/Authors: 
* 
*  1.Milos Tomic
*  2.Maja Dusanic 
*  3.Alexander Teuchtmann 
*  4.Andrea Aistleithner 
*  5.Christopher Huber 
* 
*  Date: 27.05.2018
*  Version: 1.0.23
*
* Copyright notice
* - Programm is being build by the above mentioned programmers
* 
* Purpose of program: 
* - Time scheduling of projects, tasks etc.
*/

public class CreateProjectView {

	private JFrame crePrjctFrame;
	private static ProjectList prjctList;
	private static List<ProjectView> prjctViewList;
	private static List<Project> prjctListFiles;
	int yCoor = 101;
	int xCoor = 50;

	// getter

	public JFrame getFrame() {
		return this.crePrjctFrame;
	}

	// create the application

	public CreateProjectView() {
		prjctViewList = new ArrayList<>();
		prjctList = new ProjectList();
		
		initialize();
	}

	// contents of the frame

	private void initialize() {
		crePrjctFrame = new JFrame();
		crePrjctFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));

		crePrjctFrame.setBounds(0, 0, 1920, 1080);
		crePrjctFrame.getContentPane().setBackground(new Color(255, 255, 255));
		crePrjctFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Start-Button to make new Project

		JButton btnCrePrjct = new JButton("Neues Projekt erstellen");
		btnCrePrjct.setBounds(767, 13, 362, 57);

		btnCrePrjct.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnCrePrjct.setBackground(SystemColor.LIGHT_GRAY);

		// loading
		// data************************************************************
		
		prjctListFiles = new LoadProject().allProjects();
		for (Project p : prjctListFiles) {

			p.setName(new LoadProject().projectName(p));
			p.setDescription(new LoadProject().projectDescription(p));
			prjctList.addProject(p);

			List<Person> ProjectPersons = new LoadProject().projectPersons(p);

			for (Person person : ProjectPersons) {
				
				person.setFirstName(new LoadPerson().personFirstname(person));
				person.setLastname(new LoadPerson().personLastname(person));
				
				p.addPerson(person);
			}

			List<TaskGroup> taskGroupList = new LoadProject().projectTaskGroups(p);

			for (TaskGroup tg : taskGroupList) {

				tg.setName(new LoadTaskGroup().taskGroupName(tg));

				List<Person> taskGroupPerson = new LoadTaskGroup().taskGroupPersons(tg);

				for (Person person : taskGroupPerson) {
					
					person.setFirstName(new LoadPerson().personFirstname(person));
					person.setLastname(new LoadPerson().personLastname(person));
					
					tg.addPerson(person);
				}
				p.addTaskGroup(tg);

				List<Task> taskList = new LoadTaskGroup().taskGroupTasks(tg);

				for (Task task : taskList) {

					task.setName(new LoadTask().taskName(task));
					task.setPlanTime(new LoadTask().taskPlanTime(task));

					List<Person> taskPerson = new LoadTask().taskPersons(task);

					for (Person person : taskPerson) {
						
						person.setFirstName(new LoadPerson().personFirstname(person));
						person.setLastname(new LoadPerson().personLastname(person));
						
						task.addPerson(person);
					}

					tg.addTask(task);

					List<Activity> activityList =  new LoadTask().taskActivities(task);
					
					for (Activity activity : activityList) {

						activity.setDescription(new LoadActivity().activityDescription(activity));
						activity.setStart(new LoadActivity().activityStart(activity));
						activity.setEnd(new LoadActivity().activityEnd(activity));
						
						Person per = new LoadActivity().activityPerson(activity); 
						per.setFirstName(new LoadPerson().personFirstname(per));
						per.setLastname(new LoadPerson().personLastname(per));
						
						activity.addPerson(per);
						
						task.addActivity(activity);
					}
				}	
			}
		}

		// creating views for existing projects

		prjctListFiles = prjctList.getProjectList(); 
		
		for (Project p : prjctListFiles) {
			ProjectView pv = new ProjectView(crePrjctFrame, p, xCoor, yCoor);
			pv.setName(p.getName());
			pv.setDescr(p.getDescription());

			// calculating correct position of every project
			if (yCoor < 661) {
				yCoor = yCoor + 295;
			} else {
				yCoor = 101;
				xCoor = xCoor + 600;
			}
		} // loading data
			// ************************************************************

		// Action after pressing create project button

		btnCrePrjct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Sound.playSound(".\\sounds\\open.wav");
				Project prjct = new Project(new LoadProject().newProjectId());
				new SaveProject().newProject(prjct);
				prjctList.addProject(prjct);
				ProjectView projektView = new ProjectView(crePrjctFrame, prjct, xCoor, yCoor);
				prjctViewList.add(projektView);
				prjctListFiles.add(prjct);

				// calculating correct position of every project
				if (yCoor < 661) {
					yCoor = yCoor + 295;
				} else {
					yCoor = 101;
					xCoor = xCoor + 600;
				}

			} // createProjectButton (actionPerformed)
		}); // createProjectButton (Listener - he has only one method: action
			// performed (bzw. the above one))

		crePrjctFrame.getContentPane().setLayout(null);
		crePrjctFrame.getContentPane().add(btnCrePrjct);
		
		JButton btnPerson = new JButton("Neue Person anlegen");
		btnPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreatePersonsView personsView = new CreatePersonsView();
			}
		});
		btnPerson.setBounds(1163, 16, 362, 57);		
		btnPerson.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnPerson.setBackground(SystemColor.LIGHT_GRAY);
		
		
		crePrjctFrame.getContentPane().add(btnPerson);
		
		
		

	}
}