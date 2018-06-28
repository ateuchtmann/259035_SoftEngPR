package views;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JFrame;

import db_load.LoadActivity;
import db_load.LoadPerson;
import db_load.LoadProject;
import db_load.LoadTask;
import db_load.LoadTaskGroup;
import db_save.SavePerson;
import models.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

/* Classname: CreatePersonsView
*
* Programmers/Authors: 
* 
*  1.Milos Tomic
*  2.Maja Dusanic 
*  3.Alexander Teuchtmann 
*  4.Andrea Aistleithner 
*  5.Christopher Huber 
* 
*  Date: 19.06.2018
*  Version: 1.0.23
*
* Copyright notice
* - Programm is being build by the above mentioned programmers
* 
* Purpose of program: 
* - Time scheduling of projects, tasks etc.
*/

public class CreatePersonsView {

	private JFrame crePrsnsFrame;
	int yCoor = 101;   
	int xCoor = 50;
	private static PersonList prsList;
	private static List<Person> prsListFiles;
	
	

	public CreatePersonsView() {
		prsList = new PersonList();
		prsListFiles = prsList.getPersonList();
		initialize();
	}
	
	public static List getPrsListFiles(){
		return prsListFiles;
	}
	

	
	
	private void initialize() {
		crePrsnsFrame = new JFrame();
		crePrsnsFrame.setBounds(0, 0, 1920, 1080);
		crePrsnsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		crePrsnsFrame.setVisible(true);
		crePrsnsFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
		crePrsnsFrame.getContentPane().setBackground(new Color(255, 255, 255));
		crePrsnsFrame.getContentPane().setLayout(null);
		
		JButton btnPrsns = new JButton("Neue Person anlegen");
		btnPrsns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Person newPrs = new Person(new LoadPerson().newPersonId());
				PersonsView prsnsView = new PersonsView(crePrsnsFrame, xCoor, yCoor, newPrs);
				prsListFiles.add(newPrs);
				new SavePerson().newPerson(newPrs);
			
		
				if(yCoor < 950){
					yCoor = yCoor + 80;
				}else{
					yCoor = 101;
					xCoor = xCoor + 400;
				}
				
			}
		});
		btnPrsns.setBounds(40, 16, 362, 57);
		crePrsnsFrame.getContentPane().add(btnPrsns);	
		btnPrsns.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnPrsns.setBackground(SystemColor.LIGHT_GRAY);
		
		
		
		// loading
		// data************************************************************
				
		prsListFiles = new LoadPerson().allPersons();
		for (Person p : prsListFiles) {

			p.setFirstName(new LoadPerson().personFirstname(p));
			p.setLastname(new LoadPerson().personLastname(p));
					
			prsList.addPerson(p);
		}//end for

		// creating views for existing projects
		
		
		prsListFiles = prsList.getPersonList(); 
		
		
		// loading
		// data************************************************************
				
		for (Person p : prsListFiles) {
			PersonsView pv = new PersonsView(crePrsnsFrame, xCoor, yCoor, p);
			pv.setName(p.getFirstName() + " " + p.getLastName());
	
			
			// calculating correct position of every person
			if(yCoor < 950){
				yCoor = yCoor + 80;
			}else{
				yCoor = 101;
				xCoor = xCoor + 400;
			}	
	}//end for

}
}
