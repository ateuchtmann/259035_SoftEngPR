package views;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;

import models.*;
import sounds.Sound;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/* Classname: PersonView
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

public class PersonView {

	private JFrame personFrame;
	private JTextField fieldFirstName;
	private JTextField fieldLastName;
	private Person person;
	private JButton buttonPersonInfo;
	static Project project;

	
	public JFrame getFrame() {
		return personFrame;
	}

	public PersonView(Person person, JButton buttonPersonInfo, Project project) {
		this.person = person;
		this.buttonPersonInfo = buttonPersonInfo;
		PersonView.project = project;
		initialize();
	}
	
	// setter
	
	public void setFirstName(String firstName) {
		this.fieldFirstName.setText(firstName);
	}
	
	public void setLastName(String lastName) {
		this.fieldLastName.setText(lastName);
	}

	private void initialize() {
		
		personFrame = new JFrame();
		personFrame.setBounds(650, 200, 450, 300);
		personFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		JPanel personInfoPanel = new JPanel();
		personInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		personFrame.getContentPane().add(personInfoPanel, BorderLayout.CENTER);
		personInfoPanel.setLayout(null);
		
		//label saying "first name"
		JLabel labelFirstName = new JLabel("Vorname:");
		labelFirstName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelFirstName.setBounds(40, 17, 72, 16);
		
		//label saying "lastname"
		JLabel labelLastName = new JLabel("Nachname:");
		labelLastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelLastName.setBounds(31, 46, 81, 16);
		
		//textField for first name
		fieldFirstName = new JTextField();
		fieldFirstName.setBounds(114, 15, 167, 22);
		fieldFirstName.setColumns(10);
		fieldFirstName.setText(person.getFirstName());
		
		//textField for lastname
		fieldLastName = new JTextField();
		fieldLastName.setBounds(114, 44, 167, 22);
		fieldLastName.setColumns(10);
		fieldLastName.setText(person.getLastName());

		JButton buttonOk = new JButton("ok");
		buttonOk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonOk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sound.playSound(".\\sounds\\open.wav");
				String firstInit = null;
				String secondInit = null;
				try {
					firstInit = fieldFirstName.getText(0, 1);
		            secondInit= fieldLastName.getText(0,1);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
				person.setFirstName(fieldFirstName.getText());
				db_save.SavePerson.personFirstname(person, fieldFirstName.getText());
				person.setLastname(fieldLastName.getText());
				db_save.SavePerson.personLastname(person,fieldLastName.getText());
				
				//project.addPerson(person);
				buttonPersonInfo.setText(firstInit + "." + secondInit + ".");
				personFrame.setVisible(false);
			}
		});
		buttonOk.setBounds(165, 215, 97, 25);
		
		
		//adding all components to panel
		personInfoPanel.add(labelFirstName);
		personInfoPanel.add(labelLastName);
		personInfoPanel.add(fieldFirstName);
		personInfoPanel.add(fieldLastName);
		personInfoPanel.add(buttonOk);
	}
	
	
}
