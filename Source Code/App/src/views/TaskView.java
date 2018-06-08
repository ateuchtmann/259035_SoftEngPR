package views;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import db_save.SaveTask;
import models.*;
import sounds.Sound;

/* Classname: TaskView
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

public class TaskView {

	private static JFrame taskFrame;
	private JPanel taskGroupPanel;
	private JTextField fieldPlanTime;
	private static Map<Integer, Integer> yCoorList = new HashMap<>();
	private Task task;
	private Project project;
	private JTextArea fieldTaskDescription;
	private String description;
	private Time planTime;
	double planHour;
	double planMin;
	
	private static CreateActivityView createActivityView;
	
	
	/**
	 * Create the application.
	 * @param description 
	 * @param project 
	 * @wbp.parser.constructor
	 */
	public TaskView(JFrame frame, JPanel taskGroupPanel, Map<Integer, Integer> list, Task task, Project project) {
		TaskView.taskFrame = frame;
		this.taskGroupPanel = taskGroupPanel;
		this.task = task;
		this.project = project;
		TaskView.yCoorList = list;
		initialize();
	}

	
	/**
	 * @wbp.parser.constructor
	 */
	// constructor with description
	
	public TaskView(JFrame frame, JPanel taskGroupPanel, Map<Integer, Integer> list, Task task, Project project, String description, Time time) {
		TaskView.taskFrame = frame;
		this.taskGroupPanel = taskGroupPanel;
		this.task = task;
		this.project = project;
		this.description = description;
		this.planTime = time;
		TaskView.yCoorList = list;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	

	static Map<JButton, CreateActivityView> createActivityViewMap = new HashMap<>();

	// setter
	
	public void setPlanTime(Time time) {
		this.fieldPlanTime.setText(time.toString());
	}
	
	public void setDescription(String description) {
		this.fieldTaskDescription.setText(description);
	}
	
	private void initialize() {

		//adding panel behind input area (better look)
		
		JPanel taskPanel = new JPanel();
		taskPanel.setBounds(12, yCoorList.get(taskGroupPanel.hashCode()), 331, 100);
		taskPanel.setLayout(null);
		
		
		
		//adding input area for task text
		
		fieldTaskDescription = new JTextArea();
		fieldTaskDescription.setBounds(0, 0, 331, 74);
		fieldTaskDescription.setText(description);
		taskPanel.add(fieldTaskDescription);
		
		//adding planTime
		
		JLabel labelPlanTime = new JLabel("Sollzeit:");
		labelPlanTime.setBounds(10, 81, 56, 16);
		taskPanel.add(labelPlanTime);
		
		fieldPlanTime = new JTextField("00.00");
		fieldPlanTime.setBounds(60, 78, 45, 22);
		taskPanel.add(fieldPlanTime);
		fieldPlanTime.setColumns(10);
		
	
		createActivityView = new CreateActivityView(project, task);
		
		//adding button planTime
		
		JButton manageActivities = new JButton("Zeiterfassung");
		manageActivities.setFont((new Font("Tahoma", Font.PLAIN, 14)));
		manageActivities.setBounds(180, 77, 140, 21);
		
		createActivityViewMap.put(manageActivities, createActivityView);
		
		
		manageActivities.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent arg0) {
			
				Sound.playSound(".\\sounds\\open.wav");
				CreateActivityView newCreateActivityView =  createActivityViewMap.get(manageActivities);
				
				//saving/parsing planedTime
				String typedPlanHour = fieldPlanTime.getText().substring(0,2);
				String typedPlanMin = fieldPlanTime.getText().substring(3,5);
				planHour = Double.parseDouble(typedPlanHour);
				planMin = Double.parseDouble(typedPlanMin);
				
				newCreateActivityView.setPlanHour(planHour);
				newCreateActivityView.setPlanMin(planMin);
				
				planTime = new Time ((int)planHour, (int)planMin);
				task.setPlanTime(planTime); 
				new SaveTask().taskPlanTime(task, planTime);
			
				newCreateActivityView.updateTime();
				JFrame createActivityFrame = newCreateActivityView.getFrame();
				createActivityFrame.setVisible(true);
				task.setName(fieldTaskDescription.getText());
				new SaveTask().taskName(task, fieldTaskDescription.getText());
				
			}
		});
		
		
		taskPanel.add(manageActivities);
		
		taskGroupPanel.add(taskPanel);
		taskFrame.repaint();
		task.setName(fieldTaskDescription.getText());

		//adding to the y coordinate
		yCoorList.put(taskGroupPanel.hashCode(), yCoorList.get(taskGroupPanel.hashCode()) + 110);
		
		
		// save when closing window 
		taskFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				task.setName(fieldTaskDescription.getText());
				new SaveTask().taskName(task, fieldTaskDescription.getText());
				
				//saving/parsing planedTime
				CreateActivityView newCreateActivityView =  createActivityViewMap.get(manageActivities);
				String typedPlanHour = fieldPlanTime.getText().substring(0,2);
				String typedPlanMin = fieldPlanTime.getText().substring(3,5);
				planHour = Double.parseDouble(typedPlanHour);
				planMin = Double.parseDouble(typedPlanMin);
				
				newCreateActivityView.setPlanHour(planHour);
				newCreateActivityView.setPlanMin(planMin);
				
				planTime = new Time ((int)planHour, (int)planMin);
				task.setPlanTime(planTime);
				new SaveTask().taskPlanTime(task, planTime);
			}
		});
		
	}//initialize

}
