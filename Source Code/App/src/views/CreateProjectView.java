package views;

import java.awt.Font;
import javax.swing.*;

import db_load.LoadProject;
import db_save.SaveProject;
import models.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

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

	private JFrame createProjectFrame;
	private static ProjectList projectList;
	private static List<ProjectView> projectViewList;
	private static List<Project> projectListFiles;
	int yCoor = 101;
	int xCoor = 50;

	// getter

	public JFrame getFrame() {
		return this.createProjectFrame;
	}

	// create the application

	public CreateProjectView() {
		projectViewList = new ArrayList<>();
		projectList = new ProjectList();
		
		initialize();
	}

	// contents of the frame

	private void initialize() {
		createProjectFrame = new JFrame();
		createProjectFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));

		createProjectFrame.setBounds(0, 0, 1920, 1080);
		createProjectFrame.getContentPane().setBackground(new Color(255, 255, 255));
		createProjectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Start-Button to make new Project

		JButton buttonCreateProject = new JButton("Neues Projekt erstellen");
		buttonCreateProject.setBounds(767, 13, 362, 57);

		buttonCreateProject.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		buttonCreateProject.setBackground(SystemColor.LIGHT_GRAY);

		// loading
		// data************************************************************
		
		projectList = new LoadProject().everythingFromProjects(); 
		
		// creating views for existing projects

		projectListFiles = projectList.getProjectList(); 
		
		for (Project p : projectListFiles) {
			ProjectView pv = new ProjectView(createProjectFrame, p, xCoor, yCoor);
			pv.setName(p.getName());
			pv.setDescription(p.getDescription());

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

		buttonCreateProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Sound.playSound(".\\sounds\\open.wav");
				Project project = new Project(new LoadProject().newProjectId());
				new SaveProject().newProject(project);
				projectList.addProject(project);
				ProjectView projectView = new ProjectView(createProjectFrame, project, xCoor, yCoor);
				projectViewList.add(projectView);
				projectListFiles.add(project);

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

		createProjectFrame.getContentPane().setLayout(null);
		createProjectFrame.getContentPane().add(buttonCreateProject);

	}
}