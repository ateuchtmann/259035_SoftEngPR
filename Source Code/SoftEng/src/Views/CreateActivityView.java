package Views;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import Files.Activity;
import Files.Project;

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

	public CreateActivityView(Project prjct) {
		this.prjct = prjct;
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
		createActFrame.getContentPane().setBackground(new Color(102, 153, 204));
		createActFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		JButton btnAct = new JButton("+ Aktivit\u00E4t");
	
		btnAct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Activity act = new Activity();
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
	
		
		JLabel lblCurTimeNum = new JLabel("0.0 h");
		lblCurTimeNum.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCurTimeNum.setBackground(SystemColor.activeCaption);
		lblCurTimeNum.setBounds(1675, 958, 76, 27);
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
				double sum = 0;
				for(ActivityView av : actViewList) {
					if(av.getId() == btnAct.hashCode()) {
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
	
		
			}
		});
		btnUpdate.setBounds(1442, 959, 115, 29);
		createActFrame.getContentPane().add(btnUpdate);
		
		
		createActFrame.getContentPane().add(lblDiffNum);
		createActFrame.repaint();
			
	
	}
}

