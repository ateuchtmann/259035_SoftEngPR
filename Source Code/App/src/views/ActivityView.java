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

import db_delete.Delete;
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

	private static JFrame actFrame;
	private Project prjct;
	private CreateActivityView creActView;
	private Activity act;
	private JTextField fldDescr;
	private JTextField fldStart;
	private JTextField fldEnd;
	private JTextField fldTime;
	private int btnId;
	private int ybtnRadioPrsCoor = 15;
	private int yCoor;
	private Time start;
	private Time end;
	private double time;
	private ButtonGroup btnGroup;
	private JLabel lblPrsInit;
	private List<Person> listPrs;

	public ActivityView(JFrame frame, Activity act, int y, Project prjct, int id, CreateActivityView cav) {
		ActivityView.actFrame = frame;
		this.act = act; 
		this.yCoor = y;
		this.prjct = prjct;
		this.btnId = id;
		this.creActView = cav;
		initialize();
	}
	
	// getter
	public double getTime(){
		return time;
	}
	
	public int getId() {
		return btnId;
	}
	
	// setter
	
	public void setDescr(String d) {
		this.fldDescr.setText(d);
	}
	
	public void setStart(String s) {
		this.fldStart.setText(s);
	}
	
	public void setEnd(String e) {
		this.fldEnd.setText(e);
	}
	
	public void setDiff(String d) {
		this.fldTime.setText(d);
	}
	
	public void setTimeNum(double t) {
		this.time = t;
	}
	
	public void setlblPrs(Person p) {
		
		String fInit = "";
		String sInit = "";
		if(!p.getFirstName().equals("") && !p.getLastName().equals("")){
		fInit = p.getFirstName().substring(0, 1);
		sInit = p.getLastName().substring(0, 1);
		lblPrsInit.setText("  " + fInit + "." + sInit + ".");
		}else {
		lblPrsInit.setText("");
		}
		
		
	}
	
	Map<Person, JRadioButton> btnRadioPrsList = new HashMap<>();
	Set<JLabel> lblList = new LinkedHashSet<>();

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JPanel actPanel = new JPanel();
		actPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		actPanel.setBackground(SystemColor.LIGHT_GRAY);
		
		actPanel.setBounds(40, yCoor, 1794, 51);
		actFrame.getContentPane().add(actPanel);
		actPanel.setLayout(null);
		
		lblPrsInit = new JLabel();
		lblPrsInit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrsInit.setForeground(SystemColor.windowText);
		lblPrsInit.setBounds(998, 8, 57, 35);
		lblPrsInit.setBorder(new MatteBorder(2,2,2,2,Color.BLACK));
		actPanel.add(lblPrsInit);
		
		fldDescr = new JTextField();
		fldDescr.setBounds(133, 13, 733, 26);
		actPanel.add(fldDescr);
		fldDescr.setColumns(10);
		
		JLabel lblDescr = new JLabel("Beschreibung:");
		lblDescr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescr.setBounds(15, 16, 146, 20);
		actPanel.add(lblDescr);
		
		JLabel lblStart = new JLabel("Start:");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStart.setBounds(1258, 16, 50, 20);
		actPanel.add(lblStart);
		
		JLabel lblEnd = new JLabel("End:");
		lblEnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnd.setBounds(1373, 16, 33, 20);
		actPanel.add(lblEnd);
		
		fldStart = new JTextField("00.00");
		fldStart.setBounds(1308, 13, 50, 26);
		actPanel.add(fldStart);
		fldStart.setColumns(10);
		
		fldEnd = new JTextField("00.00");
		fldEnd.setBounds(1410, 13, 50, 26);
		actPanel.add(fldEnd);
		fldEnd.setColumns(10);
		
		JLabel lblHour = new JLabel("Stunden:");
		lblHour.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHour.setBounds(1475, 16, 69, 20);
		actPanel.add(lblHour);
		
		fldTime = new JTextField("00.00");
		fldTime.setBounds(1535, 13, 50, 26);
		actPanel.add(fldTime);
		fldTime.setColumns(10);
		
		
		JLabel lblh = new JLabel(" h");
		lblh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblh.setBounds(1593, 16, 21, 20);
		actPanel.add(lblh);
		
		JButton btnPrs = new JButton("Person +");
		btnPrs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sound.playSound(".\\sounds\\open.wav");
				listPrs = prjct.getPersonList();
				btnGroup = new ButtonGroup();
				JFrame prsFrame = new JFrame();
				prsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				prsFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
				prsFrame.setBounds(700, 400, 350,360);
				prsFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
				prsFrame.getContentPane().setLayout(null);
				prsFrame.setVisible(true);
				
				//adding radio buttons
				for(Person p : listPrs){
					
					JRadioButton btnRadioPrs = new JRadioButton(p.getLastName());
					btnRadioPrs.setBackground(Color.LIGHT_GRAY);
					btnRadioPrs.setFont(new Font("Verdana", Font.PLAIN, 18));
					btnRadioPrs.setBounds(11, ybtnRadioPrsCoor, 139, 29);
					btnGroup.add(btnRadioPrs);
					prsFrame.getContentPane().add(btnRadioPrs);
					btnRadioPrsList.put(p, btnRadioPrs);
					ybtnRadioPrsCoor += 40;	
				}
				ybtnRadioPrsCoor = 30;	
				
				JButton btnSave = new JButton("ok");
				btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnSave.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						Sound.playSound(".\\sounds\\open.wav");
						//removing existing labels
						for(JLabel j : lblList){
							actPanel.remove(j);
						}
						
						//listing all persons and checking if selected
						for(Person p: listPrs){
							if(btnRadioPrsList.containsKey(p)){
								if(btnRadioPrsList.get(p).isSelected()) {
									act.addPerson(p);
									new SaveActivity().activityPerson(act, p);
									p.addActivity(act);
									String firstInit = p.getFirstName().substring(0,1);
									String scndInit = p.getLastName().substring(0,1);
									lblPrsInit.setText("  "+ firstInit + "." + scndInit + ".");
									lblList.add(lblPrsInit);
									actPanel.add(lblPrsInit);
									actPanel.repaint();
								}			
							}	
						}//end for
						prsFrame.dispose();
					}		
				});
			
				btnSave.setBounds(141, 275, 59, 25);
				prsFrame.getContentPane().add(btnSave);

			}
		});
		btnPrs.setBounds(881, 12, 105, 29);
		actPanel.add(btnPrs);
		
		JButton btnOk = new JButton("ok");
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Sound.playSound(".\\sounds\\open.wav");
				int hours=0;
				int minutes=0;
				double sumStartTime=0;
				double sumEndTime=0;
				
				act.setDescription(fldDescr.getText());
				new SaveActivity().activityDescription(act, fldDescr.getText());
				
				// Read starttime
				String hour = fldStart.getText().substring(0,2) ;
				String min = fldStart.getText().substring(3,5);
				
				hours = Integer.parseInt(hour);
				minutes = Integer.parseInt(min);
				start = new Time(hours, minutes);
				act.setStart(start);
				new SaveActivity().activityStart(act, start);
				sumStartTime=hours+(minutes/60.0);
				
				// Read endtime
				String h1 = fldEnd.getText().substring(0,2);
				String m1 = fldEnd.getText().substring(3,5);
				
				hours = Integer.parseInt(h1);
				minutes = Integer.parseInt(m1);
				end = new Time(hours, minutes);
				act.setEnd(end);
				new SaveActivity().activityEnd(act, end);
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
				fldTime.setText(diffTimeString);
				
				creActView.updateTime();				
				
			}
		});
		btnOk.setBounds(1628, 13, 50, 29);
		actPanel.add(btnOk);	
		
		JButton btnDelete = new JButton("X");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete d = new Delete();
				d.deleteActivity(act);
				actFrame.revalidate();
				actFrame.repaint();
				
			}
		});
		btnDelete.setForeground(Color.RED);
		btnDelete.setBounds(1692, 13, 50, 29);
		actPanel.add(btnDelete);
		
		// save when closing window 
				actFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						
						//save description
						act.setDescription(fldDescr.getText());
						new SaveActivity().activityDescription(act, fldDescr.getText());
					}
				});
		
    }//initialize

	
}
