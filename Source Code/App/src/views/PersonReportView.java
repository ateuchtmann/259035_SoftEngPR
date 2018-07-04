package views;

import java.awt.Color;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import java.awt.Font;

/* Classname: PersonenReportView
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

public class PersonReportView {

	private JFrame frame;
	int xCoor;
	int yCoor;
	JLabel lblPrnsName;
	JLabel lblProjects;
	JLabel lblAct;
	JLabel lblCurrTime;

	
	public PersonReportView(JFrame frame, int xCoor, int yCoor) {
		this.frame = frame;
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		initialize();
	}

	
	private void initialize() {
		JPanel personReportPanel = new JPanel();
		personReportPanel.setBounds(xCoor, yCoor, 559, 313);
		personReportPanel.setLayout(null);
		personReportPanel.setBackground(Color.LIGHT_GRAY);
		personReportPanel.setBorder(new MatteBorder(2, 2, 3, 2, (Color) Color.DARK_GRAY));
		frame.getContentPane().add(personReportPanel);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(26, 34, 69, 20);
		personReportPanel.add(lblName);
		
		lblPrnsName = new JLabel("Name ");
		lblPrnsName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrnsName.setBounds(97, 34, 180, 20);
		personReportPanel.add(lblPrnsName);
		
		JLabel lblPrjcts = new JLabel("Projekte: ");
		lblPrjcts.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrjcts.setBounds(26, 70, 129, 28);
		personReportPanel.add(lblPrjcts);
		
		lblProjects = new JLabel(" ");
		lblProjects.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblProjects.setBounds(26, 97, 500, 38);
		personReportPanel.add(lblProjects);
		
		JLabel lblAktivity = new JLabel("Aktivitäten:");
		lblAktivity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAktivity.setBounds(26, 151, 106, 28);
		personReportPanel.add(lblAktivity);
		
		lblAct = new JLabel(" ");
		lblAct.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAct.setBounds(26, 195, 500, 38);
		personReportPanel.add(lblAct);
		
		JLabel lblTime = new JLabel("Investierte Zeit : ");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTime.setBounds(26, 264, 151, 20);
		personReportPanel.add(lblTime);
		
		lblCurrTime = new JLabel(" ");
		lblCurrTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCurrTime.setBounds(176, 265, 101, 20);
		personReportPanel.add(lblCurrTime);
		
		
	}
	
	public void setName(String s){
		lblPrnsName.setText(s);
	}
	
	public void setProjects(String s){
		lblProjects.setText(s);
	}
	
	public void setActivity(String s){
		lblAct.setText(s);
	}
	
	public void setTime(String s){
		lblCurrTime.setText(s);
	}

}
