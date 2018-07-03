package views;

import java.awt.Font;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	private static WaitView wait = new WaitView();

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
		btnCrePrjct.setBounds(596, 16, 362, 30);

		btnCrePrjct.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnCrePrjct.setBackground(SystemColor.LIGHT_GRAY);

		// loading
		// data************************************************************
		
		prjctListFiles = db_load.LoadProject.allProjects();
		for (Project p : prjctListFiles) {

			p.setName(db_load.LoadProject.projectName(p));
			p.setDescription(db_load.LoadProject.projectDescription(p));
			prjctList.addProject(p);

			List<Person> ProjectPersons = db_load.LoadProject.projectPersons(p);

			for (Person person : ProjectPersons) {
				
				person.setFirstName(db_load.LoadPerson.personFirstname(person));
				person.setLastname(db_load.LoadPerson.personLastname(person));
				
				p.addPerson(person);
			}

			List<TaskGroup> taskGroupList = db_load.LoadProject.projectTaskGroups(p);

			for (TaskGroup tg : taskGroupList) {

				tg.setName(db_load.LoadTaskGroup.taskGroupName(tg));

				List<Person> taskGroupPerson = db_load.LoadTaskGroup.taskGroupPersons(tg);

				for (Person person : taskGroupPerson) {
					
					person.setFirstName(db_load.LoadPerson.personFirstname(person));
					person.setLastname(db_load.LoadPerson.personLastname(person));
					
					tg.addPerson(person);
				}
				p.addTaskGroup(tg);

				List<Task> taskList = db_load.LoadTaskGroup.taskGroupTasks(tg);

				for (Task task : taskList) {

					task.setName(db_load.LoadTask.taskName(task));
					task.setPlanTime(db_load.LoadTask.taskPlanTime(task));

					List<Person> taskPerson = db_load.LoadTask.taskPersons(task);

					for (Person person : taskPerson) {
						
						person.setFirstName(db_load.LoadPerson.personFirstname(person));
						person.setLastname(db_load.LoadPerson.personLastname(person));
						
						task.addPerson(person);
					}

					tg.addTask(task);

					List<Activity> activityList =  db_load.LoadTask.taskActivities(task);
					
					for (Activity activity : activityList) {

						activity.setDescription(db_load.LoadActivity.activityDescription(activity));
						activity.setStart(db_load.LoadActivity.activityStart(activity));
						activity.setEnd(db_load.LoadActivity.activityEnd(activity));
						
						Person per = db_load.LoadActivity.activityPerson(activity); 
						per.setFirstName(db_load.LoadPerson.personFirstname(per));
						per.setLastname(db_load.LoadPerson.personLastname(per));
						
						activity.addPerson(per);
						
						task.addActivity(activity);
					}
				}	
			}
		}

		// creating views for existing projects

		prjctListFiles = prjctList.getProjectList(); 
		
		for (Project p : prjctListFiles) {
			ProjectView pv = new ProjectView(crePrjctFrame, p, xCoor, yCoor, wait, prjctList);
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
				Project prjct = new Project(db_load.LoadProject.newProjectId());
				db_save.SaveProject.newProject(prjct);
				prjctList.addProject(prjct);
				ProjectView projektView = new ProjectView(crePrjctFrame, prjct, xCoor, yCoor,wait, prjctList);
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
		btnPerson.setBounds(1005, 16, 362, 30);		
		btnPerson.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnPerson.setBackground(SystemColor.LIGHT_GRAY);
		
		
		crePrjctFrame.getContentPane().add(btnPerson);
		JButton btnPrjctReport = new JButton("Projektreport");
		btnPrjctReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CreateProjectReportView cv = new CreateProjectReportView();
			
			}
		});
		
		btnPrjctReport.setBounds(1176, 62, 362, 30);
		
		btnPrjctReport.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnPrjctReport.setBackground(SystemColor.LIGHT_GRAY);
		
		crePrjctFrame.getContentPane().add(btnPrjctReport);
		
		JButton btnTaskGroupReport = new JButton("Aufgabenbereichsreport");
		btnTaskGroupReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateTaskGroupReportView ct = new CreateTaskGroupReportView();
				
				
			}
		});
		
		btnTaskGroupReport.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnTaskGroupReport.setBackground(SystemColor.LIGHT_GRAY);
		
		btnTaskGroupReport.setBounds(361, 62, 362, 30);
		crePrjctFrame.getContentPane().add(btnTaskGroupReport);
		
		JButton btnPersonReport = new JButton("PersonenReport");
		btnPersonReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreatePersonenReportView cr = new CreatePersonenReportView();
				
				
			}
		});
		btnPersonReport.setBounds(760, 62, 362, 30);
		crePrjctFrame.getContentPane().add(btnPersonReport);
		btnPersonReport.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnPersonReport.setBackground(SystemColor.LIGHT_GRAY);

		
		
		
		
		
		crePrjctFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				wait.getFrame().setVisible(false);
			}
		});

	}
	
	
}