package views;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Font;

/* Classname: TaskGroupReportView
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

public class TaskGroupReportView {

	private JFrame frame;
	int xCoor;
	int yCoor;
	JLabel lblTaskGroupName;
	JLabel lblTaskGroup;
	JLabel lblCurrTime;
	JLabel lblPlanTime;
	JProgressBar progressBar;



	
	public TaskGroupReportView(JFrame frame, int xCoor, int yCoor) {
		this.frame = frame;
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		initialize();
	}
	
	
	//setter
	public void setName(String s){
		lblTaskGroupName.setText(s);
	}
	
	public void setTasks(String s){
		lblTaskGroup.setText(s);
	}

	public void setCurrTime(String s){
		lblCurrTime.setText(s);
	}
	
	public void setPlanTime(String s){
		lblPlanTime.setText(s);
	}
	
	public void setProgressBar(int i){
		progressBar.setValue(i);
	}
	
	

	
	private void initialize() {
		JPanel tskGroupReportPanel = new JPanel();
		tskGroupReportPanel.setBounds(xCoor, yCoor, 559, 313);
		tskGroupReportPanel.setLayout(null);
		tskGroupReportPanel.setBackground(Color.LIGHT_GRAY);
		tskGroupReportPanel.setBorder(new MatteBorder(2, 2, 3, 2, (Color) Color.DARK_GRAY));
		frame.getContentPane().add(tskGroupReportPanel);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(15, 35, 69, 20);
		tskGroupReportPanel.add(lblName);
		
		lblTaskGroupName = new JLabel(" ");
		lblTaskGroupName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTaskGroupName.setBounds(108, 35, 243, 20);
		tskGroupReportPanel.add(lblTaskGroupName);
		
		JLabel lblTaks = new JLabel("Aufgaben des Bereiches:");
		lblTaks.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTaks.setBounds(15, 71, 243, 29);
		tskGroupReportPanel.add(lblTaks);
		
		lblTaskGroup = new JLabel(" ");
		lblTaskGroup.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTaskGroup.setBounds(15, 104, 513, 45);
		tskGroupReportPanel.add(lblTaskGroup);
		
		JLabel lblTime = new JLabel("Istzeit: ");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTime.setBounds(15, 174, 69, 20);
		tskGroupReportPanel.add(lblTime);
		
		lblCurrTime = new JLabel(" ");
		lblCurrTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCurrTime.setBounds(99, 174, 69, 20);
		tskGroupReportPanel.add(lblCurrTime);
		
		JLabel lblTimePlan = new JLabel("SollZeit:");
		lblTimePlan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTimePlan.setBounds(15, 210, 69, 20);
		tskGroupReportPanel.add(lblTimePlan);
		
		lblPlanTime = new JLabel("  ");
		lblPlanTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPlanTime.setBounds(108, 210, 69, 20);
		tskGroupReportPanel.add(lblPlanTime);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(124, 252, 0));
		progressBar.setBounds(42, 261, 453, 20);
		tskGroupReportPanel.add(progressBar);
	}
	
	
}
