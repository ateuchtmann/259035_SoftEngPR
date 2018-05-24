package views;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import files.*;

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
*  Date: 22.05.2018
*  Version: 1.0.20
*
* Copyright notice
* - Programm is being build by the above mentioned programmers
* 
* Purpose of program: 
* - Time scheduling of projects, tasks etc.
*/

public class ActivityView {

	private static JFrame actFrame;
	private static Activity act;
	private JTextField fldDescr;
	private JTextField fldStart;
	private JTextField fldEnd;
	private static JTextField fldTime;
	private Project prjct;
	private int btnId;
	private double time;
	int yCheckBoxCoor = 30;
	int xPrsCoor = 998;
	private int yCoor;

	public ActivityView(JFrame frame, Activity act, int y, Project prjct, int id) {
		ActivityView.actFrame = frame;
		ActivityView.act = act; 
		this.yCoor = y;
		this.prjct = prjct;
		this.btnId = id;
		initialize();
	}
	
	public double getTime(){
		return time;
	}
	
	public void setSum(double sum){
		
	}
	
	public int getId() {
		return btnId;
	}
	
	Map<Person, JCheckBox> chechkboxList = new HashMap<>();
	Set<JLabel> lblList = new LinkedHashSet<>();

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		List<Person> list = prjct.getPersonList();
		JPanel actPanel = new JPanel();
		actPanel.setBackground(SystemColor.LIGHT_GRAY);
		
		actPanel.setBounds(40, yCoor, 1794, 51);
		actFrame.getContentPane().add(actPanel);
		actPanel.setLayout(null);
		
		
		fldDescr = new JTextField();
		fldDescr.setBounds(133, 13, 733, 26);
		actPanel.add(fldDescr);
		fldDescr.setColumns(10);
		
		JLabel lblDescr = new JLabel("Beschreibung:");
		lblDescr.setBounds(15, 16, 146, 20);
		actPanel.add(lblDescr);
		
		JLabel lblStart = new JLabel("Start:");
		lblStart.setBounds(1412, 16, 50, 20);
		actPanel.add(lblStart);
		
		JLabel lblEnd = new JLabel("End:");
		lblEnd.setBounds(1512, 16, 33, 20);
		actPanel.add(lblEnd);

		fldStart = new JTextField("00.00");
		fldStart.setBounds(1459, 13, 50, 26);
		actPanel.add(fldStart);
		fldStart.setColumns(10);
		
		fldEnd = new JTextField("00.00");
		fldEnd.setBounds(1549, 13, 50, 26);
		actPanel.add(fldEnd);
		fldEnd.setColumns(10);
		
		
		JLabel lblHour = new JLabel("Stunden:");
		lblHour.setBounds(1614, 16, 69, 20);
		actPanel.add(lblHour);
		
		fldTime = new JTextField("00.00");
		fldTime.setBounds(1680, 13, 42, 26);
		actPanel.add(fldTime);
		fldTime.setColumns(10);
		
		
		JLabel lblh = new JLabel(" h");
		lblh.setBounds(1729, 16, 21, 20);
		actPanel.add(lblh);
		
		JButton btnPrs = new JButton("Person +");
		btnPrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame prsFrame = new JFrame();
				prsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				prsFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
				prsFrame.setBounds(700, 400, 350,360);
				prsFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
				prsFrame.getContentPane().setLayout(null);
				prsFrame.setVisible(true);
				
				//adding checkbox
				for(Person p : list){
					
					JCheckBox chckbxNewCheckBox = new JCheckBox(p.getLastname());
					chckbxNewCheckBox.setBackground(Color.LIGHT_GRAY);
					chckbxNewCheckBox.setFont(new Font("Verdana", Font.PLAIN, 18));
					chckbxNewCheckBox.setBounds(11, yCheckBoxCoor, 139, 29);
					prsFrame.getContentPane().add(chckbxNewCheckBox);
					chechkboxList.put(p, chckbxNewCheckBox);
					yCheckBoxCoor += 40;	
				}
				yCheckBoxCoor = 30;
				
				
				
				JButton btnSave = new JButton("ok");
				btnSave.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						//removing existing labels
						for(JLabel j : lblList){
							actPanel.remove(j);
						}
						
						
						//listing all persons and checking if selected
						for(Person p: list){
							if(chechkboxList.containsKey(p)){
								if(chechkboxList.get(p).isSelected()) {
								
									act.addPerson(p);
									String firstInit = p.getFirstName().substring(0,1);
									String scndInit = p.getLastname().substring(0,1);
									JLabel lblPrsInit = new JLabel("  "+ firstInit + "." + scndInit + ".");
									lblPrsInit.setFont(new Font("Tahoma", Font.PLAIN, 15));
									lblPrsInit.setForeground(SystemColor.windowText);
									lblPrsInit.setBounds(xPrsCoor, 13, 50, 26);
									lblPrsInit.setBorder(new MatteBorder(1,1,1,1,Color.BLACK));
									lblList.add(lblPrsInit);
									actPanel.add(lblPrsInit);
									actPanel.repaint();
									xPrsCoor += 60;
									
									
											
								}			
							}	
						}//end for
						xPrsCoor = 998;
							
					}
							
				});
				
				

				btnSave.setBounds(141, 275, 59, 25);
				prsFrame.add(btnSave);


			}
		});
		btnPrs.setBounds(881, 12, 105, 29);
		actPanel.add(btnPrs);
		
		JButton btnOk = new JButton("ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int hours=0;
				int minutes=0;
				double sumStartTime=0;
				double sumEndTime=0;
				
				/*
				double h = Double.parseDouble(typedHour);
				double m = Double.parseDouble(typedMin) / 60;
				String typedHour = fldTime.getText().substring(0,2);
				String typedMin = fldTime.getText().substring(3,5);
				time = h + m;
				*/
				
				act.setDescr(fldDescr.getText());
				
				// Read starttime
				String hour = fldStart.getText().substring(0,2) ;
				String min = fldStart.getText().substring(3,5);
				hours = Integer.parseInt(hour);
				minutes = Integer.parseInt(min);
				Time start = new Time(hours, minutes);
				act.setStart(start);
				sumStartTime=hours+(minutes/60.0);
				
				// Read endtime
				String h1 = fldEnd.getText().substring(0,2);
				String m1 = fldEnd.getText().substring(3,5);
				hours = Integer.parseInt(h1);
				minutes = Integer.parseInt(m1);
				Time end = new Time(hours, minutes);
				act.setEnd(end);
				sumEndTime=hours+(minutes/60.0);
				
				
				System.out.print("\nsumEndTime: " + sumEndTime + "\nsumStartTime: " + sumStartTime);
				System.out.print("\nEndTimemin: " + minutes + "\nEndTimeHours: " + hours);
				
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
				views.ActivityView.fldTime.setText(diffTimeString);
								
			}
		});
		btnOk.setBounds(1739, 12, 57, 29);
		actPanel.add(btnOk);	
}
}
