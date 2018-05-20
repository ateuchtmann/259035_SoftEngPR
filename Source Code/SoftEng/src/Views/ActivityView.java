package Views;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Daten.Activity;
import Daten.Aufgabe;
import Daten.Aufgabenbereich;
import Daten.Person;
import Daten.Projekt;

public class ActivityView {

	private static JFrame activityFrame;
	private static Activity activity;
	private JTextField textFieldBesch;
	private JTextField textFieldStart;
	private JTextField textFieldEnd;
	private JTextField textFieldStunden;
	private Projekt projekt;
	int yCheckBoxCoor = 30;
	
	private int yCoordinate;
	private static double sum;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * @param projekt 
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
	
	
	Map<Person, JCheckBox> chechkboxList = new HashMap<>();
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		System.out.println(projekt.getName());
		List<Person> list = projekt.getPeronen();
		
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
		
		textFieldStart = new JTextField("00:00");
		textFieldStart.setBounds(1549, 13, 50, 26);
		activityPanel.add(textFieldStart);
		textFieldStart.setColumns(10);
		
		textFieldEnd = new JTextField("00:00");
		textFieldEnd.setBounds(1459, 13, 50, 26);
		activityPanel.add(textFieldEnd);
		textFieldEnd.setColumns(10);
		
		JLabel lblStunden = new JLabel("Stunden:");
		lblStunden.setBounds(1614, 16, 69, 20);
		activityPanel.add(lblStunden);
		
		textFieldStunden = new JTextField("0");
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
				
				
				for(Person p : list){
					
					JCheckBox chckbxNewCheckBox = new JCheckBox(p.getNachname());
					chckbxNewCheckBox.setBackground(Color.LIGHT_GRAY);
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
				int nr = Integer.parseInt(h);		
				sum = sum + nr;	
				
				
			}
		});
		btnOk.setBounds(1739, 12, 57, 29);
		activityPanel.add(btnOk);
		
		JPanel panel = new JPanel();
		panel.setBounds(1001, 13, 57, 26);
		activityPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblMd = new JLabel("m.d.");
		lblMd.setBounds(0, 0, 58, 26);
		panel.add(lblMd);
		
	
        
		
		
}
}
