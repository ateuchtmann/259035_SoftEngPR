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

/* Classname: PersonProjectView
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

public class PersonProjectView {

	private JFrame prsFrame;
	private JTextField fldFirstName;
	private JTextField fldSurName;
	private Person prs;
	private JButton btnPrsInfo;
	static Project prjct;

	
	public JFrame getFrame() {
		return prsFrame;
	}
	
	public PersonProjectView(Person prs){
		this.prs = prs;
		initialize();
	}
	

	public PersonProjectView(Person prs, JButton btnPrsInfo, Project prjct) {
		this.prs = prs;
		this.btnPrsInfo = btnPrsInfo;
		PersonProjectView.prjct = prjct;
		initialize();
	}
	
	// setter
	
	public void setFirstName(String firstName) {
		this.fldFirstName.setText(firstName);
	}
	
	public void setSurName(String SurName) {
		this.fldSurName.setText(SurName);
	}

	private void initialize() {
		
		prsFrame = new JFrame();
		prsFrame.setBounds(650, 200, 450, 300);
		prsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		JPanel prsInfoPanel = new JPanel();
		prsInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		prsFrame.getContentPane().add(prsInfoPanel, BorderLayout.CENTER);
		prsInfoPanel.setLayout(null);
		
		//label saying "first name"
		JLabel lblfirstName = new JLabel("Vorname:");
		lblfirstName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblfirstName.setBounds(40, 17, 72, 16);
		
		//label saying "surname"
		JLabel lblSurName = new JLabel("Nachname:");
		lblSurName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSurName.setBounds(31, 46, 81, 16);
		
		//textField for first name
		fldFirstName = new JTextField();
		fldFirstName.setBounds(114, 15, 167, 22);
		fldFirstName.setColumns(10);

		
		//textField for surname
		fldSurName = new JTextField();
		fldSurName.setBounds(114, 44, 167, 22);
		fldSurName.setColumns(10);
	

		JButton btnOk = new JButton("ok");
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sound.playSound(".\\sounds\\open.wav");
				String firstName = fldFirstName.getText();
				prs.setFirstName(firstName);
				db_save.SavePerson.personFirstname(prs, fldFirstName.getText());
				String surName = fldSurName.getText();
				
				PersonsView.setName(firstName + " " + surName);
				
				prs.setLastname(surName);
				db_save.SavePerson.personLastname(prs, surName);
				
			}
		});
		btnOk.setBounds(165, 215, 97, 25);
		
		
		//adding all components to panel
		prsInfoPanel.add(lblfirstName);
		prsInfoPanel.add(lblSurName);
		prsInfoPanel.add(fldFirstName);
		prsInfoPanel.add(fldSurName);
		prsInfoPanel.add(btnOk);
		prsFrame.setVisible(true);
	}
	
	
}
