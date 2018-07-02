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

	private static JFrame tskGroupFrame;
	private static Map<Integer, Integer> yCoorList = new HashMap<>();
	private TaskGroup tskGroup;
	private int x;
	private static Project prjct;
	private JLabel lblTskName;
	
	public TaskGroupView(JFrame frame, TaskGroup tskGroup, int x, Project prjct) {
		TaskGroupView.tskGroupFrame = frame;
		this.tskGroup = tskGroup; 
		this.x = x;
		this.prjct = prjct;
		initialize();
	}
	
	// setter
	
	public void setName(String n) {
		lblTskName.setText(n);
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JPanel tskPanel = new JPanel();    
		yCoorList.put(tskPanel.hashCode(), 100); 
		
		tskPanel.setBackground(Color.LIGHT_GRAY);
		tskPanel.setBounds(x, 120, 355, 780);
		tskGroupFrame.getContentPane().add(tskPanel);
		Border prjctBorder = new MatteBorder(2,2,3,2,Color.BLACK);
		tskPanel.setBorder(prjctBorder);
		tskPanel.setLayout(null);
		
		lblTskName = new JLabel("Default Name");
		lblTskName.setBackground(new Color(255, 255, 255));
		lblTskName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTskName.setBounds(12, 13, 200, 34);
		tskPanel.add(lblTskName);
		
		 // new panel behind name (for better view)
		
		JPanel namePanel = new JPanel();
		namePanel.setBorder(new EmptyBorder(22,22,22,22));
		namePanel.setBackground(Color.WHITE);
		namePanel.setBounds(0, 0, 355, 63);
		Border panelNameBorder = new MatteBorder(3,3,4,3,Color.DARK_GRAY);
		namePanel.setBorder(panelNameBorder);
		namePanel.setLayout(null);
		tskPanel.add(namePanel);
		
		JButton btnEdditName = new JButton("...");
		btnEdditName.setBounds(251, 22, 39, 25);
		namePanel.add(btnEdditName);
		
		JButton btnDelete = new JButton("X");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				db_delete.Delete.deleteTaskGroup(tskGroup);
				TaskGroupView.prjct.deleteTaskGroup(tskGroup);
				

				tskGroupFrame.revalidate();
				tskGroupFrame.repaint();

			}
		});
		btnDelete.setForeground(Color.RED);
		btnDelete.setBounds(299, 21, 41, 26);
		namePanel.add(btnDelete);
		
			
			// specifying the action after pressing the button for name edit
			
			btnEdditName.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Sound.playSound(".\\sounds\\open.wav");
					//creating second frame (window) to make input when editing name of project
					
					JFrame inptNameFrame = new JFrame(); 
			
					JPanel inptNamePanel = new JPanel();
					inptNamePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
					inptNamePanel.setLayout(null);
					
					//adding "set name:" next to input
					
					JLabel lblSetName = new JLabel("Name: ");
					lblSetName.setFont(new Font("Verdana", Font.PLAIN, 15));
					lblSetName.setBounds(31, 30, 113, 25);
					inptNamePanel.add(lblSetName);
					
					//adding area to input the name
					
					JTextArea fldInputName = new JTextArea();
					fldInputName.setBounds(114, 30, 320, 25);
					fldInputName.setFont(new Font("Verdana", Font.PLAIN, 15));
					inptNamePanel.add(fldInputName);
					
					//creating button to save name and close second frame 
		
					JButton btnOk = new JButton("ok");
					btnOk.setBounds(450, 30, 57, 35);
					inptNamePanel.add(btnOk);
					btnOk.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Sound.playSound(".\\sounds\\open.wav");
							String str = fldInputName.getText();
							lblTskName.setText(str); 
							tskGroup.setName(fldInputName.getText());
							db_save.SaveTaskGroup.taskGroupName(tskGroup, fldInputName.getText());
							inptNameFrame.dispose();
						}
					});
					
					
					// specifying second frame attributes
					
					inptNameFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
					inptNameFrame.setBounds(700, 400, 550,169);
					inptNameFrame.getContentPane().setBackground(new Color(102, 153, 204));
					inptNameFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					inptNameFrame.getContentPane().add(inptNamePanel);
					inptNameFrame.setVisible(true);
			        
					
				}
			});
		
		
		// loading data*********************************************************************
		// creating views for existing tasks
		
		
		for(Task t: tskGroup.getTaskList()) {
			TaskView tskView = new TaskView(tskGroupFrame, tskPanel, yCoorList, t , prjct, t.getName(), t.getPlanTime());
			tskView.setPlanTime(t.getPlanTime());
			double currTime1 = tskView.getActView().getTime();
			double planTime1 = t.getPlanTime().getHour() + t.getPlanTime().getMin();
			int diff = (int) ((currTime1 / planTime1) * 100);
			tskView.setTaskProgress(diff); 
		}// loading data ************************************************************
		
		
		JButton btnTaskAdd = new JButton("Aufgabe Hinzuf\u00FCgen");
		btnTaskAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTaskAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			Sound.playSound(".\\sounds\\open.wav");
			   Task tsk = new Task(db_load.LoadTask.newTaskId());
			   db_save.SaveTask.newTask(tsk);
			   tskGroup.addTask(tsk);
			   db_save.SaveTaskGroup.taskGroupTask(tskGroup, tsk);
			   @SuppressWarnings("unused")
			   TaskView tskView = new TaskView(tskGroupFrame, tskPanel, yCoorList, tsk, prjct, tskGroup);
			   
			}
		});
		btnTaskAdd.setBounds(105, 62, 155, 25);
		tskPanel.add(btnTaskAdd);
	

	}
}
