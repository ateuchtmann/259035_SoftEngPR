package views;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;

import db_load.LoadTaskGroup;
import db_save.SaveProject;
import db_save.SaveTaskGroup;
import models.*;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import sounds.Sound;

/* Classname: CreateTaskGroupView
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

public class CreateTaskGroupView {

	private JFrame createTaskGroupFrame;
	protected int xCoor = 20; // coordinate for moving taskgroup to the right
	private Project project;
	
	/**
	 * Launch the application.
	 */

	
	public JFrame getFrame() {
		return createTaskGroupFrame;
	}
	
	public int getX() {
		return xCoor;
	}

	/**
	 * Create the application.
	 * @param project2 
	 */
	public CreateTaskGroupView(Project project) {
		this.project = project;
		initialize();
	}

	/**
s	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		createTaskGroupFrame = new JFrame();
		createTaskGroupFrame.setBounds(0, 0, 1920, 1080);
		createTaskGroupFrame.getContentPane().setBackground(new Color(255, 255, 255));
		createTaskGroupFrame.getContentPane().setLayout(null);
		createTaskGroupFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JButton buttonCreateTaskGroup = new JButton("Neuen Aufgabenbereich erstellen");
		buttonCreateTaskGroup.setBounds(767, 13, 362, 57);
		
		buttonCreateTaskGroup.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		buttonCreateTaskGroup.setBackground(SystemColor.LIGHT_GRAY);
		
		// loading data*********************************************************************
				// creating views for existing taskGroups
				
				for(TaskGroup tg: project.getTaskGroups()) {
					TaskGroupView taskGroupView = new TaskGroupView(createTaskGroupFrame, tg, xCoor, project);
					taskGroupView.setName(tg.getName());
					
					// calculating correct position of every taskGroup
						xCoor = xCoor + 385;
					
				}// loading data ************************************************************
		
		

		// creating new taskGroup
		
		buttonCreateTaskGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				Sound.playSound(".\\sounds\\open.wav");
				TaskGroup taskGroup = new TaskGroup(new LoadTaskGroup().newTaskGroupId());
				new SaveTaskGroup().newTaskGroup(taskGroup);
				project.addTaskGroup(taskGroup);
				new SaveProject().projectTaskGroup(project, taskGroup);
				@SuppressWarnings("unused")
				TaskGroupView taskGroupView = new TaskGroupView(createTaskGroupFrame, taskGroup, xCoor, project);
				xCoor = xCoor + 385;
			}
		});
		
		createTaskGroupFrame.getContentPane().add(buttonCreateTaskGroup);
		
			
	}//initialize
	
	
	
	
}//CreateAufgabenbereichClass
