package views;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import files.*;
import sounds.Sound;

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
*  Date: 27.05.2018
*  Version: 1.0.23
*
* Copyright notice
* - Programm is being build by the above mentioned programmers
* 
* Purpose of program: 
* - Time scheduling of projects, tasks etc.
*/

public class CreateActivityView {

	private JFrame createActFrame;
	private Project prjct;
	private int yCoor = 104;
	private double planHour;
	private double planMin;
	private List<ActivityView> actViewList;
	private Task task; 
	private JLabel lblCurTimeNum;
	private JLabel lblDiffNum;
	private JButton btnAct;
	private CreateActivityView currClass;
	private double time;
	
	public CreateActivityView(Project prjct, Task task) {
		this.prjct = prjct;
		this.task = task; 
		this.actViewList = new ArrayList<>();
		currClass = this;
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
		
		btnAct = new JButton("+ Aktivit\u00E4t");
        btnAct.setBounds(767, 13, 362, 57);
		btnAct.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnAct.setBackground(SystemColor.LIGHT_GRAY);
		
		JLabel lblCurTime = new JLabel("IST ZEIT: ");
		lblCurTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCurTime.setBackground(SystemColor.activeCaption);
		lblCurTime.setBounds(1572, 954, 105, 35);
		createActFrame.getContentPane().add(lblCurTime);
	
		
		lblCurTimeNum = new JLabel("00:00");
		lblCurTimeNum.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCurTimeNum.setBackground(SystemColor.activeCaption);
		lblCurTimeNum.setBounds(1675, 958, 76, 30);
		createActFrame.getContentPane().add(lblCurTimeNum);
		
		
		createActFrame.getContentPane().setLayout(null);
		createActFrame.getContentPane().add(btnAct);
		
		
		JLabel lblDiff = new JLabel("Differenz: ");
		lblDiff.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblDiff.setBounds(60, 959, 131, 35);
		createActFrame.getContentPane().add(lblDiff);
		
		lblDiffNum = new JLabel("00:00");
		lblDiffNum.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDiffNum.setBounds(172, 963, 76, 30);
		
		createActFrame.getContentPane().add(lblDiffNum);
		createActFrame.repaint();
		
		
		// loading data*********************************************************************
		// creating views for existing activities
		
		for(Activity a: task.getActivities()) {
			
			ActivityView actView = new ActivityView(createActFrame, a, yCoor, prjct, btnAct.hashCode(), currClass);
			if(yCoor <880){
				yCoor += 60;
			}
			actView.setDescr(a.getDescrn());
			actView.setStart(a.getStart().toString());
			actView.setEnd(a.getEnd().toString());
			actView.setlblPrs(a.getPerson());
			
			int hours=0;
			int minutes=0;
			double sumStartTime=0;
			double sumEndTime=0;
			
			// Read starttime
			String hour = a.getStart().toString().substring(0,2) ;
			String min = a.getStart().toString().substring(3,5);
			
			hours = Integer.parseInt(hour);
			minutes = Integer.parseInt(min);
			sumStartTime=hours+(minutes/60.0);
			
			// Read endtime
			String h1 = a.getEnd().toString().substring(0,2);
			String m1 = a.getEnd().toString().substring(3,5);
			
			hours = Integer.parseInt(h1);
			minutes = Integer.parseInt(m1);
			sumEndTime=hours+(minutes/60.0);

			// Calc difference (end-start)
			time = sumEndTime-sumStartTime;
			
			//extract Minutes and Hours from time
			double diffHours=0;
			double diffMinutes=0;	
			diffHours = (int) time;
			diffMinutes = time-diffHours;
			diffMinutes *= 60;
			String diffTimeString = (int)diffHours+":"+(int)diffMinutes;
			
			//Output difference
			actView.setDiff(diffTimeString);
			actView.setTimeNum(time);
		
			actViewList.add(actView);
			updateTime();
			createActFrame.repaint();
			
		}// loading data *******************************************************************
		
		

		btnAct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Sound.playSound(".\\sounds\\open.wav");
				Activity act = new Activity();
				task.addActivity(act);
				ActivityView actView = new ActivityView(createActFrame, act, yCoor,prjct, btnAct.hashCode(), currClass);
				actViewList.add(actView);
				
				if(yCoor <880){
					yCoor += 60;
				}
				
				createActFrame.repaint();
				
			}
		});
			
	}
	
	
	
	
	// helping methods
	
	public void updateTime() {
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
		//System.out.print("\nDifftime: " + diffTime + " DiffHour: " + diffTimeHours + " DiffMinutes: " + diffTimeMinutes);
		
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
		//System.out.print("\nCurrtime: " + sumCurrTime + " CurrHour: " + currTimeHours + " CurrMinutes: " + currTimeMinutes);
		
		//Output DiffTime & CurrTime
		String currTimeString = (int)currTimeHours + "h " + (int)currTimeMinutes+"m";
		lblCurTimeNum.setText(currTimeString);
		
		String diffTimeString = (int)diffTimeHours + "h " + (int)diffTimeMinutes+"m";
		lblDiffNum.setText(diffTimeString);
		
	}
}

