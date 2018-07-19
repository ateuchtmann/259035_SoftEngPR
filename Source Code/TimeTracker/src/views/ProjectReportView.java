package views;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.border.MatteBorder;

import models.Project;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/* Classname: ProjectReportView
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

public class ProjectReportView {

	private JFrame frame;
	private int xCoor;
	private int yCoor;
	private Project prjct;
	JLabel lblProjektname;
	JLabel lblTaskGroupNr;
	JLabel lblTaskNr;
	JLabel lblPrsnNr;
	JLabel lblCurrentTime;
	JLabel lblPlanTime;
	JProgressBar progressBar;


	public ProjectReportView(Project prjct, JFrame frame, int xCoor, int yCoor) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.frame = frame;
		this.prjct = prjct;
		initialize();
	}
	
	
	//setter
	
	public void setName(String text){
		lblProjektname.setText(text);
	}
	
	public void setTaskGroupNr(String text){
			lblTaskGroupNr.setText(text);
	}
	
	public void setTaskNr(String text){
		lblTaskNr.setText(text);
	}
	
	public void setPersonNr(String text){
		lblPrsnNr.setText(text);
	}
	
	public void setCurrTime(String text){
		lblCurrentTime.setText(text);
	}
	
	
	public void setPlanTime(String text){
		lblPlanTime.setText(text);
	}
	
	public void setProgressBar(int i){
		progressBar.setValue(i);
	}
	
	
	

	private void initialize() {
		
		JPanel prjctReportPanel = new JPanel();
		prjctReportPanel.setBounds(xCoor, yCoor, 559, 313);
		prjctReportPanel.setLayout(null);
		prjctReportPanel.setBackground(Color.LIGHT_GRAY);
		prjctReportPanel.setBorder(new MatteBorder(2, 2, 3, 2, (Color) Color.DARK_GRAY));
		
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(15, 16, 69, 20);
		prjctReportPanel.add(lblName);
		
		lblProjektname = new JLabel(" ");
		lblProjektname.setBounds(99, 16, 179, 20);
		prjctReportPanel.add(lblProjektname);
		
		JLabel lblTaskGroup = new JLabel("Aufgabenbereiche: ");
		lblTaskGroup.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTaskGroup.setBounds(15, 52, 179, 20);
		prjctReportPanel.add(lblTaskGroup);
		
		lblTaskGroupNr = new JLabel(" ");
		lblTaskGroupNr.setBounds(189, 52, 60, 20);
		prjctReportPanel.add(lblTaskGroupNr);
		
		JLabel lblTask = new JLabel("Aufgabe: ");
		lblTask.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTask.setBounds(15, 88, 102, 25);
		prjctReportPanel.add(lblTask);
		
		lblTaskNr = new JLabel(" ");
		lblTaskNr.setBounds(125, 88, 93, 20);
		prjctReportPanel.add(lblTaskNr);
		
		JLabel lblPersons = new JLabel("beteiligte Personen:");
		lblPersons.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPersons.setBounds(15, 129, 179, 20);
		prjctReportPanel.add(lblPersons);
		
		lblPrsnNr = new JLabel(" ");
		lblPrsnNr.setBounds(180, 129, 134, 20);
		prjctReportPanel.add(lblPrsnNr);
		
		JLabel label = new JLabel("--------------------------------------------------------------------");
		label.setBounds(15, 153, 535, 20);
		prjctReportPanel.add(label);
		
		JLabel lblCurrTime = new JLabel("Istzeit des Projekts");
		lblCurrTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCurrTime.setBounds(15, 189, 161, 20);
		prjctReportPanel.add(lblCurrTime);
		
		lblCurrentTime = new JLabel(" ");
		lblCurrentTime.setBounds(180, 189, 80, 20);
		prjctReportPanel.add(lblCurrentTime);
		
		JLabel lblPlanT = new JLabel("Sollzeit des Projekts");
		lblPlanT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPlanT.setBounds(15, 225, 203, 20);
		prjctReportPanel.add(lblPlanT);
		
		lblPlanTime = new JLabel(" ");
		lblPlanTime.setBounds(180, 225, 80, 20);
		prjctReportPanel.add(lblPlanTime);
		
		
		frame.getContentPane().add(prjctReportPanel);		
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(50, 205, 50));
		progressBar.setBounds(38, 261, 441, 24);
		prjctReportPanel.add(progressBar);
		

		JButton btnMonthReport = new JButton("Monatsreport");
		btnMonthReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type = "SingMonth";
				ProjectBarChart pb = new ProjectBarChart(type, prjct, prjct.getName(),"Monatsreport des Projekts pro Person");
				pb.pack();
				pb.setBounds(500, 200, 900, 600);
				pb.setVisible(true);
			}
		});
		btnMonthReport.setBounds(421, 52, 122, 25);
		prjctReportPanel.add(btnMonthReport);
		
		JButton btnWeekReport = new JButton("Wochenreport");
		btnWeekReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type = "SingWeek";
				ProjectBarChart pb = new ProjectBarChart(type, prjct, prjct.getName(),"Wochenreport des Projekts pro Person");
				pb.pack();
				pb.setBounds(500, 200, 900, 600);
				pb.setVisible(true);
			}
		});
		btnWeekReport.setBounds(421, 90, 122, 25);
		prjctReportPanel.add(btnWeekReport);
		
		JButton btnMonthReport2 = new JButton("Monatsreport");
		btnMonthReport2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type = "AllMonth";
				ProjectBarChart pb = new ProjectBarChart(type, prjct, prjct.getName(),"Monatsreport des Projekts");
				pb.pack();
				pb.setBounds(500, 200, 900, 600);
				pb.setVisible(true);
			}
		});		
		btnMonthReport2.setBounds(290, 52, 122, 25);
		prjctReportPanel.add(btnMonthReport2);
		
		JButton btnWeekReport2 = new JButton("Wochenreport");
		btnWeekReport2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type = "AllWeek";
				ProjectBarChart pb = new ProjectBarChart(type, prjct, prjct.getName(),"Wochenreport des Projekts");
				pb.pack();
				pb.setBounds(500, 200, 900, 600);
				pb.setVisible(true);
			}
		});
		btnWeekReport2.setBounds(290, 90, 122, 25);
		prjctReportPanel.add(btnWeekReport2);
		
		JLabel lblAllPersons = new JLabel("  Alle Personen:");
		lblAllPersons.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAllPersons.setBounds(282, 19, 134, 16);
		prjctReportPanel.add(lblAllPersons);
		
		JLabel lblIndividual = new JLabel("  Einzeln:");
		lblIndividual.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIndividual.setBounds(413, 19, 122, 16);
		prjctReportPanel.add(lblIndividual);
		
	}
	
	
}
