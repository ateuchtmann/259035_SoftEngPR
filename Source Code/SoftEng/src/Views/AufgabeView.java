package Views;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Daten.Activity;
import Daten.Aufgabe;
import Daten.Projekt;

public class AufgabeView {

	private static JFrame aufgabeFrame;
	private static JPanel aufBereichPanel;
	private JTextField fldSollZeit;
	private static Map<Integer, Integer> yCoordinateList = new HashMap<>();
	private static Aufgabe aufgabe;
	private static Projekt projekt;
	double planHours;
	double planMin;
	
	private static CreateActivityView createActivityView;
	
	
	
	/**
	 * Create the application.
	 * @param projekt 
	 */
	public AufgabeView(JFrame frame, JPanel panel, Map<Integer, Integer> list, Aufgabe aufgabe, Projekt projekt) {
		AufgabeView.aufgabeFrame = frame;
		AufgabeView.aufBereichPanel = panel;
		AufgabeView.aufgabe = aufgabe;
		AufgabeView.yCoordinateList = list;
		
		this.projekt = projekt;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	

	static Map<JButton, CreateActivityView> createActivityViewMap = new HashMap<>();

	
	
	private void initialize() {
		
		

		//adding panel behind input area (better look)
		
		JPanel aufgabePanel = new JPanel();
		aufgabePanel.setBounds(12, yCoordinateList.get(aufBereichPanel.hashCode()), 331, 100);
		aufgabePanel.setLayout(null);
		
		
		
		//adding input area for aufgabe text
		
		JTextArea fldAufgabeBeschreibung = new JTextArea();
		fldAufgabeBeschreibung.setBounds(0, 0, 331, 74);
		aufgabePanel.add(fldAufgabeBeschreibung);
		
		//adding sollzeit
		
		JLabel lblSollzeit = new JLabel("Sollzeit:");
		lblSollzeit.setBounds(10, 81, 56, 16);
		aufgabePanel.add(lblSollzeit);
		
		fldSollZeit = new JTextField("00.00");
		fldSollZeit.setBounds(60, 78, 45, 22);
		aufgabePanel.add(fldSollZeit);
		fldSollZeit.setColumns(10);
		
	
		createActivityView = new CreateActivityView(projekt);
		
		//adding button planTime
		
		JButton btnIstZeit = new JButton("Zeiterfassung");
		btnIstZeit.setBounds(180, 81, 120, 16);
		
		createActivityViewMap.put(btnIstZeit, createActivityView);
		
		
		btnIstZeit.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent arg0) {
			
				CreateActivityView myCreateActivityView =  createActivityViewMap.get(btnIstZeit);
				
				//save planedTime
				String typedPlanHour = fldSollZeit.getText().substring(0,2);
				String typedPlanMin = fldSollZeit.getText().substring(3,5);
				planHours = Double.parseDouble(typedPlanHour);
				planMin = Double.parseDouble(typedPlanMin);
				
				myCreateActivityView.setPlanHours(planHours);
				myCreateActivityView.setPlanMin(planMin);
			
				JFrame createActFrame = myCreateActivityView.getFrame();
				createActFrame.setVisible(true);
				
			}
		});
		
		
		
		aufgabePanel.add(btnIstZeit);
		
	
		
		
		aufBereichPanel.add(aufgabePanel);
		aufgabeFrame.repaint();
		aufgabe.setBeschreibung(fldSollZeit.getText());

		//adding to the y coordinate
		yCoordinateList.put(aufBereichPanel.hashCode(), yCoordinateList.get(aufBereichPanel.hashCode()) + 110);
		

		
	}//initialize

}
