package views;

import java.awt.Color;

import java.awt.Font;

import javax.swing.JFrame;

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

public class WaitView {
	
	static JFrame waitFrame;
	
	
	public WaitView() {
		initialize();
	}
	
	
	private void initialize() {
		waitFrame = new JFrame();
		waitFrame.setBounds(0, 0, 1920, 1080);
		waitFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		waitFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
		waitFrame.getContentPane().setBackground(new Color(255, 255, 255));
		waitFrame.getContentPane().setLayout(null);
		
		
	
	}


	public JFrame getFrame() {
		return waitFrame;
		
	}
	

}
