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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class CreateActivityView {

	private JFrame createActivityFrame;
	private static Projekt projekt;
	
	private int yCoordinate = 104;
	private int sum;
	private double planHour;
	private double planMin;
	List<ActivityView> activityViewList;
	int id;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 * @param projekt 
	 * @param min 
	 * @param hours 
	 * @param i 
	 */
	public CreateActivityView(Projekt projekt) {
		this.projekt = projekt;
		this.id = id;
		this.activityViewList = new ArrayList<>();
		initialize();
	}
	
	
	public JFrame getFrame(){
		return this.createActivityFrame;
	}
	
	public void setPlanHours(double pH) {
		this.planHour = pH;
	}
	
	public void setPlanMin(double pM) {
		this.planMin = pM;
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
				ActivityView activityView = new ActivityView(createActivityFrame, activity, yCoordinate,projekt, btnActivity.hashCode());
				activityViewList.add(activityView);
				
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
		
		
		JLabel lblDifferenz = new JLabel("Differenz: ");
		lblDifferenz.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblDifferenz.setBounds(60, 959, 131, 35);
		createActivityFrame.getContentPane().add(lblDifferenz);
		
		JLabel lblDiffNumber = new JLabel("00:00");
		lblDiffNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDiffNumber.setBounds(172, 963, 76, 30);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//Map<JButton, ActivityView> activityViewMap;
				double sum = 0;
				for(ActivityView av : activityViewList) {
					if(av.getId() == btnActivity.hashCode()) {
						sum = sum + av.getTime();
					}
				}
				
				double planTime = planHour + (planMin/60);
				double diffTime = planTime - sum;
				
				int roundedCur = (int)sum;
				int roundedPlan= (int)diffTime;
				
				double restCur = sum - roundedCur; 
				double restPlan= diffTime - roundedPlan;
				
				if(restCur == 0.75) {
					sum = sum - 0.3;
				}else if(restCur == 0.50) {
					sum = sum - 0.2;
				}else if(restCur == 0.25){
					sum = sum - 0.1;
				}

				String curTime = sum + "h";
				lblH_1.setText(curTime);
				
				if(restPlan == 0.75) {
					diffTime = diffTime - 0.3;
				}else if(restPlan == 0.50) {
					diffTime = diffTime - 0.2;
				}else if(restPlan == 0.25){
					diffTime = diffTime - 0.1;
				}
				
				String resDiffTime = diffTime + "h";
				lblDiffNumber.setText(resDiffTime);
	
		
			}
		});
		btnUpdate.setBounds(1442, 959, 115, 29);
		createActivityFrame.getContentPane().add(btnUpdate);
		
		
		createActivityFrame.getContentPane().add(lblDiffNumber);
		createActivityFrame.repaint();
			
	
	}
}

