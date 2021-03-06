package views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import models.*;
import sounds.Sound;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

/* Classname: PersonsView
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

public class PersonsView {

	private static JFrame prsnFrame;
	
	 int yCoor = 101;   
	 int xCoor = 50;
	 static JLabel lblSetName;
	 static JLabel lblSetSurName;
	 Person prs;
	 WaitView wait;
	 private static PersonList personList; 
	

	
	public PersonsView(JFrame frame, int xCoor, int yCoor, Person newPrs, WaitView wait, PersonList personList) {
		PersonsView.prsnFrame = frame;
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.prs = newPrs;
		this.wait = wait;
		this.personList = personList; 
		initialize();
	}
	
	public static JFrame getFrame() {
		return PersonsView.prsnFrame;
	}

	
	private void initialize() {
		JPanel prsnPanel = new JPanel();           //panel for the person
		prsnPanel.setBackground(Color.LIGHT_GRAY);
		prsnPanel.setBounds(xCoor, yCoor, 377, 65);
		prsnPanel.setBorder(new MatteBorder(2, 2, 3, 2, (Color) Color.DARK_GRAY));
		prsnFrame.getContentPane().add(prsnPanel);
		prsnPanel.setLayout(null);
		
		JLabel lblFirstName = new JLabel("Name: ");
		lblFirstName.setBounds(15, 16, 63, 33);
		prsnPanel.add(lblFirstName);
		
		lblSetName = new JLabel("Personname");
		lblSetName.setBounds(91, 16, 197, 33);
		prsnPanel.add(lblSetName);
		
		
		JButton btnEdit = new JButton("...");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PersonProjectView p = new PersonProjectView(prs);
				
			}
		});
		
		btnEdit.setBounds(265, 20, 47, 29);
		prsnPanel.add(btnEdit);
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		prsnFrame.getContentPane().add(prsnPanel);
	
		
		
		JButton btnDelete = new JButton("X");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				db_delete.Delete.deletePerson(prs);		
				PersonsView.personList.deletePerson(prs);
				
				wait.getFrame().setVisible(true);
				prsnFrame.setVisible(false);
				CreatePersonsView cr = new CreatePersonsView();
				
				prsnFrame = cr.getFrame();
				prsnFrame.setVisible(true);
			
				
				
				
			}
		});
		
		btnDelete.setForeground(Color.RED);
		btnDelete.setBounds(315, 20, 47, 29);
		prsnPanel.add(btnDelete);
		prsnFrame.repaint();
		
	}
	
	public static void setName(String name){
		lblSetName.setText(name);
	}
	
}
