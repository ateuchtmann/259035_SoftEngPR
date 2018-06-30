package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;

import models.PersonList;

public class WaitView {
	
	JFrame waitFrame;
	
	
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
