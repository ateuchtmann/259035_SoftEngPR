package views;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import db_save.SaveTask;
import models.*;
import sounds.Sound;

/* Classname: TaskView
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

public class TaskView {

	private static JFrame tskFrame;
	private JPanel tskGroupPanel;
	private JTextField fldPlanTime;
	private static Map<Integer, Integer> yCoorList = new HashMap<>();
	private Task tsk;
	private Project prjct;
	private JTextArea fldTaskDescr;
	private String descr;
	private Time planTime;
	double planHour;
	double planMin;
	JProgressBar taskProgress;
	
	private static CreateActivityView createActView;
	
	
	/**
	 * Create the application.
	 * @param descr 
	 * @param projekt 
	 * @wbp.parser.constructor
	 */
	public TaskView(JFrame frame, JPanel tskGroupPanel, Map<Integer, Integer> list, Task tsk, Project prjct) {
		TaskView.tskFrame = frame;
		this.tskGroupPanel = tskGroupPanel;
		this.tsk = tsk;
		this.prjct = prjct;
		TaskView.yCoorList = list;
		initialize();
	}

	
	/**
	 * @wbp.parser.constructor
	 */
	// constructor with description
	
	public TaskView(JFrame frame, JPanel tskGroupPanel, Map<Integer, Integer> list, Task tsk, Project prjct, String descr, Time time) {
		TaskView.tskFrame = frame;
		this.tskGroupPanel = tskGroupPanel;
		this.tsk = tsk;
		this.prjct = prjct;
		this.descr = descr;
		this.planTime = time;
		TaskView.yCoorList = list;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	

	static Map<JButton, CreateActivityView> createActivityViewMap = new HashMap<>();

	// setter
	
	public void setPlanTime(Time t) {
		this.fldPlanTime.setText(t.toString());
	}
	
	public void setDescr(String d) {
		this.fldTaskDescr.setText(d);
	}
	
	public void setTaskProgress(int i){
		taskProgress.setValue(i);
	}
	
	//getter
	
	public CreateActivityView getActView(){
		return this.createActView;
	}
	
	
	
	private void initialize() {

		//adding panel behind input area (better look)
		
		JPanel taskPanel = new JPanel();
		taskPanel.setBounds(12, yCoorList.get(tskGroupPanel.hashCode()), 331, 120);
		taskPanel.setLayout(null);
		
		
	//adding planTime
		
		JLabel lblPlanTime = new JLabel("Sollzeit:");
		lblPlanTime.setBounds(10, 81, 56, 16);
		taskPanel.add(lblPlanTime);
		
		fldPlanTime = new JTextField("00.00");
		fldPlanTime.setBounds(60, 78, 45, 22);
		taskPanel.add(fldPlanTime);
		fldPlanTime.setColumns(10);
		
		
		createActView = new CreateActivityView(prjct, tsk);
		
	
		
		//adding input area for task text
		
		fldTaskDescr = new JTextArea();
		fldTaskDescr.setBounds(0, 0, 331, 74);
		fldTaskDescr.setText(descr);
		taskPanel.add(fldTaskDescr);
	
		
		
		//adding progress bar
		
		taskProgress = new JProgressBar();
		taskProgress.setBounds(10, 105, 250, 15);
		taskPanel.add(taskProgress);
		
		
		//adding button delete
		
		JButton btnDelete = new JButton("X");
		btnDelete.setForeground(Color.RED);
		btnDelete.setBounds(272, 77, 50, 25);
		taskPanel.add(btnDelete);
		
		
		//adding button planTime
		
		JButton manageActivities = new JButton("Zeiterfassung");
		manageActivities.setFont((new Font("Tahoma", Font.PLAIN, 14)));
		manageActivities.setBounds(120, 77, 140, 21);
		
		createActivityViewMap.put(manageActivities, createActView);
		 
		
		manageActivities.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent arg0) {
			
				Sound.playSound(".\\sounds\\open.wav");
				CreateActivityView newCreateActView =  createActivityViewMap.get(manageActivities);
				
				//saving/parsing planedTime
				String typedPlanHour = fldPlanTime.getText().substring(0,2);
				String typedPlanMin = fldPlanTime.getText().substring(3,5);
				planHour = Double.parseDouble(typedPlanHour);
				planMin = Double.parseDouble(typedPlanMin);
				
				newCreateActView.setPlanHour(planHour);
				newCreateActView.setPlanMin(planMin);
				
				planTime = new Time ((int)planHour, (int)planMin);
				tsk.setPlanTime(planTime); 
				new SaveTask().taskPlanTime(tsk, planTime);
			
				newCreateActView.updateTime();
				JFrame createActFrame = newCreateActView.getFrame();
				createActFrame.setVisible(true);
				tsk.setName(fldTaskDescr.getText());
				new SaveTask().taskName(tsk, fldTaskDescr.getText());
				
				
				//progress bar
				double currTime1 = createActView.getTime();
				double planTime1 = tsk.getPlanTime().getHour() + tsk.getPlanTime().getMin();
				int diff = (int) ((currTime1 / planTime1) * 100);
				taskProgress.setValue(diff); 
				
			}
		});
		
		
		taskPanel.add(manageActivities);
		
		tskGroupPanel.add(taskPanel);
		tskFrame.repaint();
		tsk.setName(fldTaskDescr.getText());

		//adding to the y coordinate
		yCoorList.put(tskGroupPanel.hashCode(), yCoorList.get(tskGroupPanel.hashCode()) + 140);
		
		
		// save when closing window 
		tskFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				tsk.setName(fldTaskDescr.getText());
				new SaveTask().taskName(tsk, fldTaskDescr.getText());
				
				//saving/parsing planedTime
				CreateActivityView newCreateActView =  createActivityViewMap.get(manageActivities);
				String typedPlanHour = fldPlanTime.getText().substring(0,2);
				String typedPlanMin = fldPlanTime.getText().substring(3,5);
				planHour = Double.parseDouble(typedPlanHour);
				planMin = Double.parseDouble(typedPlanMin);
				
				newCreateActView.setPlanHour(planHour);
				newCreateActView.setPlanMin(planMin);
				
				planTime = new Time ((int)planHour, (int)planMin);
				tsk.setPlanTime(planTime);
				new SaveTask().taskPlanTime(tsk, planTime);
				
			}
			
		});	
	}//initialize

}
