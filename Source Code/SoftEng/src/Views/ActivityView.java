package Views;
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
import javax.swing.border.MatteBorder;

import Daten.Activity;
import Daten.Person;
import Daten.Projekt;
import Daten.Time;

public class ActivityView {

	private static JFrame activityFrame;
	private static Activity activity;
	private JTextField textFieldBesch;
	private JTextField textFieldStart;
	private JTextField textFieldEnd;
	private JTextField textFieldStunden;
	private Projekt projekt;
	int yCheckBoxCoor = 30;
	int xPersonCoordinate = 998;
	
	private int yCoordinate;
	private static double sum;
	
	
	List<Person> personenList = new ArrayList();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	
	 */
	public ActivityView(JFrame frame, Activity act, int y, Projekt projekt) {
		ActivityView.activityFrame = frame;
		ActivityView.activity = act; 
		this.yCoordinate = y;
		this.projekt = projekt;
		initialize();
	}
	
	public static double getSum(){
		return sum;
	}
	
	public void setSum(double sum){
		this.sum = sum;
	}
	
	
	Map<Person, JCheckBox> chechkboxList = new HashMap<>();
	
	Set<JLabel> labelList = new LinkedHashSet<>();
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		List<Person> list = projekt.getPersonen();
		
		JPanel activityPanel = new JPanel();
		activityPanel.setBackground(SystemColor.activeCaption);
		activityPanel.setBounds(40, yCoordinate, 1794, 51);
		activityFrame.getContentPane().add(activityPanel);
		activityPanel.setLayout(null);
		
		
		textFieldBesch = new JTextField();
		textFieldBesch.setBounds(133, 13, 733, 26);
		activityPanel.add(textFieldBesch);
		textFieldBesch.setColumns(10);
		
		JLabel lblBeschreibung = new JLabel("Beschreibung:");
		lblBeschreibung.setBounds(15, 16, 146, 20);
		activityPanel.add(lblBeschreibung);
		
		JLabel lblStart = new JLabel("Start:");
		lblStart.setBounds(1412, 16, 50, 20);
		activityPanel.add(lblStart);
		
		JLabel lblEnd = new JLabel("End:");
		lblEnd.setBounds(1512, 16, 33, 20);
		activityPanel.add(lblEnd);
		
		textFieldStart = new JTextField("00.00");
		textFieldStart.setBounds(1549, 13, 50, 26);
		activityPanel.add(textFieldStart);
		textFieldStart.setColumns(10);
		
		textFieldEnd = new JTextField("00.00");
		textFieldEnd.setBounds(1459, 13, 50, 26);
		activityPanel.add(textFieldEnd);
		textFieldEnd.setColumns(10);
		
		JLabel lblStunden = new JLabel("Stunden:");
		lblStunden.setBounds(1614, 16, 69, 20);
		activityPanel.add(lblStunden);
		
		textFieldStunden = new JTextField("00.00");
		textFieldStunden.setBounds(1680, 13, 42, 26);
		activityPanel.add(textFieldStunden);
		textFieldStunden.setColumns(10);
		
	
		
		JLabel lblH = new JLabel("h");
		lblH.setBounds(1729, 16, 21, 20);
		activityPanel.add(lblH);
		
		JButton btnPerson = new JButton("Person +");
		btnPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame PersonFrame = new JFrame();
				PersonFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				
				PersonFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
				PersonFrame.setBounds(700, 400, 350,360);
				PersonFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
				PersonFrame.getContentPane().setLayout(null);
				PersonFrame.setVisible(true);
				
				JButton btnSave = new JButton("ok");
				btnSave.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						//removing existing labels
						for(JLabel j : labelList){
							activityPanel.remove(j);
						}
						
						
						//listing all persons and checking if selected
						for(Person p: list){
							if(chechkboxList.containsKey(p)){	
								if(chechkboxList.get(p).isSelected()) {
								
									String firstInitial = p.getNachname().substring(0,1);
									String secondInitial = p.getVorname().substring(0,1);
									
									
									JLabel lblNewLabel = new JLabel("  "+ secondInitial + "." + firstInitial + ".");
									lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
									lblNewLabel.setForeground(SystemColor.windowText);
									lblNewLabel.setBounds(xPersonCoordinate, 13, 50, 26);
									lblNewLabel.setBorder(new MatteBorder(1,1,1,1,Color.BLACK));
									labelList.add(lblNewLabel);
									activityPanel.add(lblNewLabel);
									activityPanel.repaint();
									xPersonCoordinate += 60;
									personenList.add(p);
									
											
								}			
							}	
						}//end for
						xPersonCoordinate = 998;
							
					}
							
				});
				
				

				btnSave.setBounds(141, 275, 59, 25);
				PersonFrame.add(btnSave);
				
				//adding checkbox
				for(Person p : list){
					
					JCheckBox chckbxNewCheckBox = new JCheckBox(p.getNachname());
					chckbxNewCheckBox.setBackground(Color.LIGHT_GRAY);
					chckbxNewCheckBox.setFont(new Font("Verdana", Font.PLAIN, 18));
					chckbxNewCheckBox.setBounds(11, yCheckBoxCoor, 139, 29);
					PersonFrame.getContentPane().add(chckbxNewCheckBox);
					chechkboxList.put(p, chckbxNewCheckBox);
					yCheckBoxCoor += 40;	
				}
				yCheckBoxCoor = 30;

			}
		});
		btnPerson.setBounds(881, 12, 105, 29);
		activityPanel.add(btnPerson);
		
		JButton btnOk = new JButton("ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				
				String h = textFieldStunden.getText();
				double nr = Double.parseDouble(h);	
				sum = sum + nr;
					
				activity.setBeschreibung(textFieldBesch.getText());
				String hour = textFieldStart.getText().substring(0,2) ;
				int hours = Integer.parseInt(hour);
				String min = textFieldStart.getText().substring(3,4);
				int minuten = Integer.parseInt(min);
				Time start = new Time(hours, minuten);
				activity.setStart(start);
				
				String h1 = textFieldEnd.getText().substring(0,2) ;
				hours = Integer.parseInt(h1);
				String m = textFieldStart.getText().substring(3,4);
				minuten = Integer.parseInt(m);
				Time end = new Time(hours, minuten);
				activity.setStart(end);
				activity.setPersonen(personenList);
				
			}
		});
		btnOk.setBounds(1739, 12, 57, 29);
		activityPanel.add(btnOk);	
}
}
