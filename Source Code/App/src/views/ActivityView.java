package views;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import db_save.SaveActivity;
import models.*;
import sounds.Sound;

/* Classname: ActivityView
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

public class ActivityView {

	private static JFrame activityFrame;
	private Project project;
	private CreateActivityView createActivityView;
	private Activity activity;
	private JTextField fieldDescription;
	private JTextField fieldStart;
	private JTextField fieldEnd;
	private JTextField fieldTime;
	private int buttonId;
	private int ybuttonRadioPersonCoor = 15;
	private int yCoor;
	private Time start;
	private Time end;
	private double time;
	private ButtonGroup buttonGroup;
	private JLabel labelPersonInit;
	private List<Person> listPerson;

	public ActivityView(JFrame frame, Activity activity, int y, Project project, int id, CreateActivityView cav) {
		ActivityView.activityFrame = frame;
		this.activity = activity; 
		this.yCoor = y;
		this.project = project;
		this.buttonId = id;
		this.createActivityView = cav;
		initialize();
	}
	
	// getter
	public double getTime(){
		return time;
	}
	
	public int getId() {
		return buttonId;
	}
	
	// setter
	
	public void setDescription(String description) {
		this.fieldDescription.setText(description);
	}
	
	public void setStart(String s) {
		this.fieldStart.setText(s);
	}
	
	public void setEnd(String e) {
		this.fieldEnd.setText(e);
	}
	
	public void setDiff(String d) {
		this.fieldTime.setText(d);
	}
	
	public void setTimeNum(double t) {
		this.time = t;
	}
	
	public void setlabelPerson(Person person) {
		
		String fInit = "";
		String sInit = "";
		if(!person.getFirstName().equals("") && !person.getLastName().equals("")){
		fInit = person.getFirstName().substring(0, 1);
		sInit = person.getLastName().substring(0, 1);
		labelPersonInit.setText("  " + fInit + "." + sInit + ".");
		}else {
		labelPersonInit.setText("");
		}
		
		
	}
	
	Map<Person, JRadioButton> buttonRadioPersonList = new HashMap<>();
	Set<JLabel> labelList = new LinkedHashSet<>();

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JPanel activityPanel = new JPanel();
		activityPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		activityPanel.setBackground(SystemColor.LIGHT_GRAY);
		
		activityPanel.setBounds(40, yCoor, 1794, 51);
		activityFrame.getContentPane().add(activityPanel);
		activityPanel.setLayout(null);
		
		labelPersonInit = new JLabel();
		labelPersonInit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelPersonInit.setForeground(SystemColor.windowText);
		labelPersonInit.setBounds(998, 8, 57, 35);
		labelPersonInit.setBorder(new MatteBorder(2,2,2,2,Color.BLACK));
		activityPanel.add(labelPersonInit);
		
		fieldDescription = new JTextField();
		fieldDescription.setBounds(133, 13, 733, 26);
		activityPanel.add(fieldDescription);
		fieldDescription.setColumns(10);
		
		JLabel labelDescription = new JLabel("Beschreibung:");
		labelDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelDescription.setBounds(15, 16, 146, 20);
		activityPanel.add(labelDescription);
		
		JLabel labelStart = new JLabel("Start:");
		labelStart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelStart.setBounds(1412, 16, 50, 20);
		activityPanel.add(labelStart);
		
		JLabel labelEnd = new JLabel("End:");
		labelEnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelEnd.setBounds(1512, 16, 33, 20);
		activityPanel.add(labelEnd);
		
		fieldStart = new JTextField("00.00");
		fieldStart.setBounds(1453, 13, 50, 26);
		activityPanel.add(fieldStart);
		fieldStart.setColumns(10);
		
		fieldEnd = new JTextField("00.00");
		fieldEnd.setBounds(1549, 13, 50, 26);
		activityPanel.add(fieldEnd);
		fieldEnd.setColumns(10);
		
		JLabel labelHour = new JLabel("Stunden:");
		labelHour.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelHour.setBounds(1614, 16, 69, 20);
		activityPanel.add(labelHour);
		
		fieldTime = new JTextField("00.00");
		fieldTime.setBounds(1680, 13, 42, 26);
		activityPanel.add(fieldTime);
		fieldTime.setColumns(10);
		
		
		JLabel labelH = new JLabel(" h");
		labelH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelH.setBounds(1720, 16, 21, 20);
		activityPanel.add(labelH);
		
		JButton buttonPerson = new JButton("Person +");
		buttonPerson.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sound.playSound(".\\sounds\\open.wav");
				listPerson = project.getPersonList();
				buttonGroup = new ButtonGroup();
				JFrame personFrame = new JFrame();
				personFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				personFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
				personFrame.setBounds(700, 400, 350,360);
				personFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
				personFrame.getContentPane().setLayout(null);
				personFrame.setVisible(true);
				
				//adding radio buttons
				for(Person person : listPerson){
					
					JRadioButton buttonRadioPerson = new JRadioButton(person.getLastName());
					buttonRadioPerson.setBackground(Color.LIGHT_GRAY);
					buttonRadioPerson.setFont(new Font("Verdana", Font.PLAIN, 18));
					buttonRadioPerson.setBounds(11, ybuttonRadioPersonCoor, 139, 29);
					buttonGroup.add(buttonRadioPerson);
					personFrame.getContentPane().add(buttonRadioPerson);
					buttonRadioPersonList.put(person, buttonRadioPerson);
					ybuttonRadioPersonCoor += 40;	
				}
				ybuttonRadioPersonCoor = 30;	
				
				JButton buttonSave = new JButton("ok");
				buttonSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
				buttonSave.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						Sound.playSound(".\\sounds\\open.wav");
						//removing existing labels
						for(JLabel j : labelList){
							activityPanel.remove(j);
						}
						
						//listing all persons and checking if selected
						for(Person person: listPerson){
							if(buttonRadioPersonList.containsKey(person)){
								if(buttonRadioPersonList.get(person).isSelected()) {
									activity.addPerson(person);
									new SaveActivity().activityPerson(activity, person);
									person.addActivity(activity);
									String firstInit = person.getFirstName().substring(0,1);
									String scndInit = person.getLastName().substring(0,1);
									labelPersonInit.setText("  "+ firstInit + "." + scndInit + ".");
									labelList.add(labelPersonInit);
									activityPanel.add(labelPersonInit);
									activityPanel.repaint();
								}			
							}	
						}//end for
						personFrame.dispose();
					}		
				});
			
				buttonSave.setBounds(141, 275, 59, 25);
				personFrame.getContentPane().add(buttonSave);

			}
		});
		buttonPerson.setBounds(881, 12, 105, 29);
		activityPanel.add(buttonPerson);
		
		JButton buttonOk = new JButton("ok");
		buttonOk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Sound.playSound(".\\sounds\\open.wav");
				int hours=0;
				int minutes=0;
				double sumStartTime=0;
				double sumEndTime=0;
				
				activity.setDescription(fieldDescription.getText());
				new SaveActivity().activityDescription(activity, fieldDescription.getText());
				
				// Read starttime
				String hour = fieldStart.getText().substring(0,2) ;
				String min = fieldStart.getText().substring(3,5);
				
				hours = Integer.parseInt(hour);
				minutes = Integer.parseInt(min);
				start = new Time(hours, minutes);
				activity.setStart(start);
				new SaveActivity().activityStart(activity, start);
				sumStartTime=hours+(minutes/60.0);
				
				// Read endtime
				String h1 = fieldEnd.getText().substring(0,2);
				String m1 = fieldEnd.getText().substring(3,5);
				
				hours = Integer.parseInt(h1);
				minutes = Integer.parseInt(m1);
				end = new Time(hours, minutes);
				activity.setEnd(end);
				new SaveActivity().activityEnd(activity, end);
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
				fieldTime.setText(diffTimeString);
				
				createActivityView.updateTime();				
				
			}
		});
		buttonOk.setBounds(1739, 12, 50, 29);
		activityPanel.add(buttonOk);	
		
		// save when closing window 
				activityFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						
						//save description
						activity.setDescription(fieldDescription.getText());
						new SaveActivity().activityDescription(activity, fieldDescription.getText());
					}
				});
		
    }//initialize

	
}
