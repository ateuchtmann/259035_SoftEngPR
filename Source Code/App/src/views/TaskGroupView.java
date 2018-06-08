package views;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import db_load.LoadTask;
import db_save.SaveTask;
import db_save.SaveTaskGroup;
import models.*;
import sounds.Sound;

/* Classname: TaskGroupView
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

public class TaskGroupView {

	private static JFrame taskGroupFrame;
	private static Map<Integer, Integer> yCoorList = new HashMap<>();
	private TaskGroup taskGroup;
	private int x;
	private Project project;
	private JLabel labelTaskName;
	
	public TaskGroupView(JFrame frame, TaskGroup taskGroup, int x, Project project) {
		TaskGroupView.taskGroupFrame = frame;
		this.taskGroup = taskGroup; 
		this.x = x;
		this.project = project;
		initialize();
	}
	
	// setter
	
	public void setName(String n) {
		labelTaskName.setText(n);
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JPanel taskPanel = new JPanel();    
		yCoorList.put(taskPanel.hashCode(), 100); 
		
		taskPanel.setBackground(Color.LIGHT_GRAY);
		taskPanel.setBounds(x, 120, 355, 780);
		taskGroupFrame.getContentPane().add(taskPanel);
		Border projectBorder = new MatteBorder(2,2,3,2,Color.BLACK);
		taskPanel.setBorder(projectBorder);
		taskPanel.setLayout(null);
		
		labelTaskName = new JLabel("Default Name");
		labelTaskName.setBackground(new Color(255, 255, 255));
		labelTaskName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		labelTaskName.setBounds(12, 13, 294, 34);
		taskPanel.add(labelTaskName);
		
		 // new panel behind name (for better view)
		
		JPanel namePanel = new JPanel();
		namePanel.setBorder(new EmptyBorder(22,22,22,22));
		namePanel.setBackground(Color.WHITE);
		namePanel.setBounds(0, 0, 355, 63);
		Border panelNameBorder = new MatteBorder(3,3,4,3,Color.DARK_GRAY);
		namePanel.setBorder(panelNameBorder);
		namePanel.setLayout(null);
		
		JButton buttonEditName = new JButton("..."); //button for editing name of project
		buttonEditName.setBounds(318, 19, 25, 25);
	
		
		// specifying the action after pressing the button for name edit
		
		buttonEditName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sound.playSound(".\\sounds\\open.wav");
				//creating second frame (window) to make input when editing name of project
				
				JFrame inputNameFrame = new JFrame(); 
		
				JPanel inputNamePanel = new JPanel();
				inputNamePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
				inputNamePanel.setLayout(null);
				
				//adding "set name:" next to input
				
				JLabel labelSetName = new JLabel("Name: ");
				labelSetName.setFont(new Font("Verdana", Font.PLAIN, 15));
				labelSetName.setBounds(31, 30, 113, 25);
				inputNamePanel.add(labelSetName);
				
				//adding area to input the name
				
				JTextArea fieldInputName = new JTextArea();
				fieldInputName.setBounds(114, 30, 320, 25);
				fieldInputName.setFont(new Font("Verdana", Font.PLAIN, 15));
				inputNamePanel.add(fieldInputName);
				
				//creating button to save name and close second frame 
	
				JButton buttonOk = new JButton("ok");
				buttonOk.setBounds(450, 30, 57, 35);
				inputNamePanel.add(buttonOk);
				buttonOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Sound.playSound(".\\sounds\\open.wav");
						String str = fieldInputName.getText();
						labelTaskName.setText(str); 
						taskGroup.setName(fieldInputName.getText());
						new SaveTaskGroup().taskGroupName(taskGroup, fieldInputName.getText());
						inputNameFrame.dispose();
					}
				});
				
				
				// specifying second frame attributes
				
				inputNameFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
				inputNameFrame.setBounds(700, 400, 550,169);
				inputNameFrame.getContentPane().setBackground(new Color(102, 153, 204));
				inputNameFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				inputNameFrame.getContentPane().add(inputNamePanel);
				inputNameFrame.setVisible(true);
		        
				
			}
		}); //buttonEditName
		
		taskPanel.add(buttonEditName);
		taskPanel.add(namePanel);
		
		
		// loading data*********************************************************************
		// creating views for existing tasks
		
		
		for(Task t: taskGroup.getTaskList()) {
			TaskView taskView = new TaskView(taskGroupFrame, taskPanel, yCoorList, t , project, t.getName(), t.getPlanTime());
			taskView.setPlanTime(t.getPlanTime());
		}// loading data ************************************************************
		
		
		JButton buttonTaskAdd = new JButton("Aufgabe Hinzuf\u00FCgen");
		buttonTaskAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonTaskAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			Sound.playSound(".\\sounds\\open.wav");
			   Task task = new Task(new LoadTask().newTaskId());
			   new SaveTask().newTask(task);
			   taskGroup.addTask(task);
			   new SaveTaskGroup().taskGroupTask(taskGroup, task);
			   @SuppressWarnings("unused")
			   TaskView taskView = new TaskView(taskGroupFrame, taskPanel, yCoorList, task, project);
			   
			}
		});
		buttonTaskAdd.setBounds(105, 62, 155, 25);
		taskPanel.add(buttonTaskAdd);
	

	}

}
