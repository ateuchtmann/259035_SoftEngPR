package Views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import Daten.Activity;
import Daten.Aufgabenbereich;
import Daten.Projekt;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateActivityView {

	private JFrame createActivityFrame;
	private static Projekt projekt;
	
	private int yCoordinate = 104;
	private int sum;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 * @param projekt 
	 */
	public CreateActivityView(Projekt projekt) {
		this.projekt = projekt;
		initialize();
	}
	
	
	public JFrame getFrame(){
		return this.createActivityFrame;
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		

		createActivityFrame = new JFrame();
		createActivityFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
		createActivityFrame.setBounds(0, 0, 1920, 1080);
		createActivityFrame.getContentPane().setBackground(new Color(102, 153, 204));
		createActivityFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		JButton btnActivity = new JButton("+ Aktivit\u00E4t");
		btnActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Activity activity = new Activity();
				ActivityView activityView = new ActivityView(createActivityFrame, activity, yCoordinate,projekt);
				
				if(yCoordinate <880){
					yCoordinate += 60;
				}
				
				createActivityFrame.repaint();
				
				
					
			}
		});
		btnActivity.setBounds(767, 13, 362, 57);
		
		btnActivity.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnActivity.setBackground(SystemColor.LIGHT_GRAY);
		Border b1 = new MatteBorder(3,3,4,3,Color.BLACK);
		
		JLabel lblIstZeit = new JLabel("IST ZEIT: ");
		lblIstZeit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIstZeit.setBackground(SystemColor.activeCaption);
		lblIstZeit.setBounds(1572, 954, 105, 35);
		createActivityFrame.getContentPane().add(lblIstZeit);
	
		
		JLabel lblH_1 = new JLabel("0.0 h");
		lblH_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblH_1.setBackground(SystemColor.activeCaption);
		lblH_1.setBounds(1675, 958, 76, 27);
		createActivityFrame.getContentPane().add(lblH_1);
		
		
		createActivityFrame.getContentPane().setLayout(null);
		btnActivity.setBorder(b1);
		createActivityFrame.getContentPane().add(btnActivity);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double sum = ActivityView.getSum();
				String s = sum + "h";
				
				lblH_1.setText(s);
				
			}
		});
		btnUpdate.setBounds(1442, 959, 115, 29);
		createActivityFrame.getContentPane().add(btnUpdate);
		createActivityFrame.repaint();
			
	
	}
	
	
}

