package views;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import files.*;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

/* Classname: CreateActivityView
*
* Programmers/Authors: 
* 
*  1.Milos Tomic
*  2.Maja Dusanic 
*  3.Alexander Teuchtmann 
*  4.Andrea Aistleithner 
*  5.Christopher Huber 
* 
*  Date: 22.05.2018
*  Version: 1.0.20
*
* Copyright notice
* - Programm is being build by the above mentioned programmers
* 
* Purpose of program: 
* - Time scheduling of projects, tasks etc.
*/

public class CreateActivityView {

	private JFrame createActFrame;
	private static Project prjct;
	private int yCoor = 104;
	private double planHour;
	private double planMin;
	List<ActivityView> actViewList;
	private Task task; 
	
	public CreateActivityView(Project prjct, Task task) {
		this.prjct = prjct;
		this.task = task; 
		this.actViewList = new ArrayList<>();
		initialize();
	}
	
	public JFrame getFrame(){
		return this.createActFrame;
	}
	
	public void setPlanHour(double pH) {
		this.planHour = pH;
	}
	
	public void setPlanMin(double pM) {
		this.planMin = pM;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		

		createActFrame = new JFrame();
		createActFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
		createActFrame.setBounds(0, 0, 1920, 1080);
		createActFrame.getContentPane().setBackground(new Color(255, 255, 255));
		createActFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		JButton btnAct = new JButton("+ Aktivit\u00E4t");
	
		btnAct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Activity act = new Activity();
				task.addActivity(act);
				ActivityView actView = new ActivityView(createActFrame, act, yCoor,prjct, btnAct.hashCode());
				actViewList.add(actView);
				
				if(yCoor <880){
					yCoor += 60;
				}
				
				createActFrame.repaint();
				
			}
		});
		btnAct.setBounds(767, 13, 362, 57);
		
		btnAct.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnAct.setBackground(SystemColor.LIGHT_GRAY);
		Border b1 = new MatteBorder(3,3,4,3,Color.BLACK);
		
		JLabel lblCurTime = new JLabel("IST ZEIT: ");
		lblCurTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCurTime.setBackground(SystemColor.activeCaption);
		lblCurTime.setBounds(1572, 954, 105, 35);
		createActFrame.getContentPane().add(lblCurTime);
	
		
		JLabel lblCurTimeNum = new JLabel("00:00");
		lblCurTimeNum.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCurTimeNum.setBackground(SystemColor.activeCaption);
		lblCurTimeNum.setBounds(1675, 958, 76, 30);
		createActFrame.getContentPane().add(lblCurTimeNum);
		
		
		createActFrame.getContentPane().setLayout(null);
		btnAct.setBorder(b1);
		createActFrame.getContentPane().add(btnAct);
		
		
		JLabel lblDiff = new JLabel("Differenz: ");
		lblDiff.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblDiff.setBounds(60, 959, 131, 35);
		createActFrame.getContentPane().add(lblDiff);
		
		JLabel lblDiffNum = new JLabel("00:00");
		lblDiffNum.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDiffNum.setBounds(172, 963, 76, 30);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//Map<JButton, ActivityView> activityViewMap;
				//double sum = 0;
				double sumCurrTime = 0;
				for(ActivityView av : actViewList) {
					if(av.getId() == btnAct.hashCode()) {
						sumCurrTime = sumCurrTime + av.getTime();
					}
				}
				
				double planTime = planHour + (planMin/60);
				double diffTime = planTime - sumCurrTime;
				
				//Convert decimalTime to Time & Round DiffTime
				int diffTimeHours = 0;
				double diffTimeMinutes = 0;
				diffTimeHours = (int) diffTime;
				diffTimeMinutes = diffTime-diffTimeHours;
				diffTimeMinutes = diffTimeMinutes*60;
				
				diffTimeMinutes=Math.round(100*diffTimeMinutes)/100;
				
				//Test DiffTime
				System.out.print("\nDifftime: " + diffTime + " DiffHour: " + diffTimeHours + " DiffMinutes: " + diffTimeMinutes);
				
				//Convert decimalTime to Time & Round CurrTime
				int currTimeHours = 0;
				double currTimeMinutes = 0;
				currTimeHours = (int) sumCurrTime;
				currTimeMinutes = sumCurrTime-currTimeHours;
				currTimeMinutes = currTimeMinutes*60;
				
				currTimeHours=Math.round(currTimeHours);
				currTimeMinutes=Math.round(currTimeMinutes);
				//currTimeMinutes=Math.round(100*currTimeMinutes)/100;
				
				//Test CurrTime
				System.out.print("\nCurrtime: " + sumCurrTime + " CurrHour: " + currTimeHours + " CurrMinutes: " + currTimeMinutes);
				
				//Output DiffTime & CurrTime
				String currTimeString = (int)currTimeHours + "h " + (int)currTimeMinutes+"m";
				lblCurTimeNum.setText(currTimeString);
				
				String diffTimeString = (int)diffTimeHours + "h " + (int)diffTimeMinutes+"m";
				lblDiffNum.setText(diffTimeString);
				
				/*
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
				lblCurTimeNum.setText(curTime);
				
				if(restPlan == 0.75) {
					diffTime = diffTime - 0.3;
				}else if(restPlan == 0.50) {
					diffTime = diffTime - 0.2;
				}else if(restPlan == 0.25){
					diffTime = diffTime - 0.1;
				}
				
				String resDiffTime = diffTime + "h";
				lblDiffNum.setText(resDiffTime);
				*/
				
			}
		});
		btnUpdate.setBounds(1442, 959, 115, 29);
		createActFrame.getContentPane().add(btnUpdate);
		
		
		createActFrame.getContentPane().add(lblDiffNum);
		createActFrame.repaint();
			
	
	}
}

