package views;

import java.awt.Color;
import java.awt.EventQueue;

import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import db_load.LoadPerson;
import db_load.LoadProject;
import models.Person;
import models.PersonList;
import models.Project;
import models.ProjectList;

/* Classname: CreatePersonenReportView
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


public class CreatePersonenReportView {

	private JFrame crePersonReportFrame;
	private List<Person> list;
	private PersonList listPerson;
	int yCoor = 100;
	int xCoor = 50;
	private ProjectList listProject;

	public CreatePersonenReportView() {
		initialize();
	}

	
	private void initialize() {
		crePersonReportFrame = new JFrame();
		crePersonReportFrame.setBounds(0,0, 1920, 1080);
		crePersonReportFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		crePersonReportFrame.getContentPane().setLayout(null);
		crePersonReportFrame.setVisible(true);
		crePersonReportFrame.getContentPane().setBackground(new Color(255, 255, 255));
		
		JLabel lblPersonReport = new JLabel("Personenrepors:");
		lblPersonReport.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblPersonReport.setBounds(27, 28, 345, 31);
		crePersonReportFrame.getContentPane().add(lblPersonReport);
		
		listPerson = db_load.LoadPerson.everythingFromPerson();
		list = listPerson.getPersonList();
		
		
		listProject = new LoadProject().everythingFromProjects();
		
		
		for(Person p: list){
			
			PersonReportView pr = new PersonReportView(crePersonReportFrame, xCoor, yCoor);
			pr.setName(p.getFirstName() + " " + p.getLastName());
			String s = listProject.getPerson(p);
			pr.setProjects(s);
			
			String s1 = listProject.getActivity(p);
			pr.setActivity(s1);
			
			double time = listProject.getPersonTime(p);
			double diffHours = 0;
			double diffMinutes = 0;
			diffHours = (int) time;
			diffMinutes = time-diffHours;
			diffMinutes *= 60;
			String timeString = (int)diffHours+":"+(int)diffMinutes;
			pr.setTime(timeString);
			
			
			
			// calculating correct position of every project
			if (yCoor < 661) {
				yCoor = yCoor + 315;
			} else {
				yCoor = 101;
				xCoor = xCoor + 650;
			}
			
		}
		
	}

}
