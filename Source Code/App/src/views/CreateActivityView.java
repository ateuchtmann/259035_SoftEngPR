package views;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;

import sounds.Sound;

import javax.swing.JLabel;

import db_load.LoadActivity;
import db_save.SaveActivity;
import db_save.SaveTask;
import models.*;

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

	private JFrame createActivityFrame;
	private Project project;
	private int yCoor = 104;
	private double planHour;
	private double planMin;
	private List<ActivityView> activityViewList;
	private Task task; 
	private JLabel labelCurrTimeNum;
	private JLabel labelDiffNum;
	private JButton buttonActivity;
	private CreateActivityView currClass;
	private double time;
	
	public CreateActivityView(Project project, Task task) {
		this.project = project;
		this.task = task; 
		this.activityViewList = new ArrayList<>();
		currClass = this;
		initialize();
	}
	
	public JFrame getFrame(){
		return this.createActivityFrame;
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
		

		createActivityFrame = new JFrame();
		createActivityFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
		createActivityFrame.setBounds(0, 0, 1920, 1080);
		createActivityFrame.getContentPane().setBackground(new Color(255, 255, 255));
		createActivityFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		buttonActivity = new JButton("+ Aktivit\u00E4t");
        buttonActivity.setBounds(767, 13, 362, 57);
		buttonActivity.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		buttonActivity.setBackground(SystemColor.LIGHT_GRAY);
		
		JLabel labelCurrTime = new JLabel("IST ZEIT: ");
		labelCurrTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelCurrTime.setBackground(SystemColor.activeCaption);
		labelCurrTime.setBounds(1572, 954, 105, 35);
		createActivityFrame.getContentPane().add(labelCurrTime);
	
		
		labelCurrTimeNum = new JLabel("00:00");
		labelCurrTimeNum.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelCurrTimeNum.setBackground(SystemColor.activeCaption);
		labelCurrTimeNum.setBounds(1675, 958, 76, 30);
		createActivityFrame.getContentPane().add(labelCurrTimeNum);
		
		
		createActivityFrame.getContentPane().setLayout(null);
		createActivityFrame.getContentPane().add(buttonActivity);
		
		
		JLabel labelDiff = new JLabel("Differenz: ");
		labelDiff.setFont(new Font("Tahoma", Font.PLAIN, 24));
		labelDiff.setBounds(60, 959, 131, 35);
		createActivityFrame.getContentPane().add(labelDiff);
		
		labelDiffNum = new JLabel("00:00");
		labelDiffNum.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelDiffNum.setBounds(172, 963, 76, 30);
		
		createActivityFrame.getContentPane().add(labelDiffNum);
		createActivityFrame.repaint();
		
		
		// loading data*********************************************************************
		// creating views for existing activities
		
		for(Activity a: task.getActivities()) {
			
			ActivityView activityView = new ActivityView(createActivityFrame, a, yCoor, project, buttonActivity.hashCode(), currClass);
			if(yCoor <880){
				yCoor += 60;
			}
			activityView.setDescription(a.getDescription());
			activityView.setStart(a.getStart().toString());
			activityView.setEnd(a.getEnd().toString());
			activityView.setlabelPerson(a.getPerson());
			
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
			activityView.setDiff(diffTimeString);
			activityView.setTimeNum(time);
		
			activityViewList.add(activityView);
			updateTime();
			createActivityFrame.repaint();
			
		}// loading data *******************************************************************
		
		

		buttonActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Sound.playSound(".\\sounds\\open.wav");
				Activity activity = new Activity(new LoadActivity().newActivityId());
				new SaveActivity().newActivity(activity);
				task.addActivity(activity);
				new SaveTask().taskActivity(task, activity);
				ActivityView activityView = new ActivityView(createActivityFrame, activity, yCoor,project, buttonActivity.hashCode(), currClass);
				activityViewList.add(activityView);
				
				if(yCoor <880){
					yCoor += 60;
				}
				
				createActivityFrame.repaint();
				
			}
		});
			
	}
	
	
	
	
	// helping methods
	
	public void updateTime() {
		double sumCurrTime = 0;
		for(ActivityView av : activityViewList) {
			if(av.getId() == buttonActivity.hashCode()) {
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
		labelCurrTimeNum.setText(currTimeString);
		
		String diffTimeString = (int)diffTimeHours + "h " + (int)diffTimeMinutes+"m";
		labelDiffNum.setText(diffTimeString);
		
	}
}

