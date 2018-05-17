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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Daten.Aufgabe;
import Daten.Aufgabenbereich;
import Daten.Person;

public class AufgabenbereichView {

	private static JFrame aufgabenbereichFrame;
	private static Map<Integer, Integer> yCoordinateList = new HashMap<>();
	private static Aufgabenbereich aufgabenB;
	private int x;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AufgabenbereichView(JFrame frame, Aufgabenbereich aufgabenB, int x) {
		AufgabenbereichView.aufgabenbereichFrame = frame;
		AufgabenbereichView.aufgabenB = aufgabenB; 
		this.x = x;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JPanel aufgabenBPanel = new JPanel();    
		yCoordinateList.put(aufgabenBPanel.hashCode(), 100); 
		
		aufgabenBPanel.setBackground(Color.LIGHT_GRAY);
		aufgabenBPanel.setBounds(x, 120, 355, 780);
		aufgabenbereichFrame.getContentPane().add(aufgabenBPanel);
		Border projectBorder = new MatteBorder(2,2,3,2,Color.BLACK);
		aufgabenBPanel.setBorder(projectBorder);
		aufgabenBPanel.setLayout(null);
		
		JLabel lblAufgabenBName = new JLabel("Default Name");
		lblAufgabenBName.setBackground(new Color(255, 255, 255));
		lblAufgabenBName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAufgabenBName.setBounds(12, 13, 294, 34);
		aufgabenBPanel.add(lblAufgabenBName);
		
		 // new panel behind name (for better view)
		
		JPanel namePanel = new JPanel();
		namePanel.setBorder(new EmptyBorder(22,22,22,22));
		namePanel.setBackground(Color.WHITE);
		namePanel.setBounds(0, 0, 355, 63);
		Border paneNameBorder = new MatteBorder(3,3,4,3,Color.DARK_GRAY);
		namePanel.setBorder(paneNameBorder);
		namePanel.setLayout(null);
		
		JButton btnEdditName = new JButton("..."); //button for editing name of project
		btnEdditName.setBounds(318, 19, 25, 25);
	
		
		// specifying the action after pressing the button for name edit
		
		btnEdditName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				//creating second frame (window) to make input when editing name of project
				
				JFrame inputNameFrame = new JFrame(); 
		
				JPanel inputNamePanel = new JPanel();
				inputNamePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
				inputNamePanel.setLayout(null);
				
				//adding "set name:" next to input
				
				JLabel lblSetName = new JLabel("Set name: ");
				lblSetName.setFont(new Font("Verdana", Font.PLAIN, 15));
				lblSetName.setBounds(31, 30, 113, 25);
				inputNamePanel.add(lblSetName);
				
				//adding area to input the name
				
				JTextArea fldInputName = new JTextArea();
				fldInputName.setBounds(114, 30, 320, 25);
				fldInputName.setFont(new Font("Verdana", Font.PLAIN, 15));
				inputNamePanel.add(fldInputName);
				
				//creating button to save name and close second frame 
	
				JButton btnOk = new JButton("ok");
				btnOk.setBounds(450, 30, 57, 35);
				inputNamePanel.add(btnOk);
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String str = fldInputName.getText();
						lblAufgabenBName.setText(str); 
						aufgabenB.setName(fldInputName.getText());
						inputNameFrame.dispose();
					}
				});
				
				
				// specifying second frame attributes
				
				inputNameFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
				inputNameFrame.setBounds(700, 400, 550,169);
				inputNameFrame.getContentPane().setBackground(new Color(102, 153, 204));
				inputNameFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				inputNameFrame.getContentPane().add(inputNamePanel);
				inputNameFrame.setVisible(true);
		        
				
			}
		}); //buttonEditName
		
		aufgabenBPanel.add(btnEdditName);
		aufgabenBPanel.add(namePanel);
		
		
		JButton btnAufgabeHinzufgen = new JButton("Aufgabe Hinzuf\u00FCgen");
		btnAufgabeHinzufgen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			   Aufgabe aufgabe = new Aufgabe();
			   aufgabenB.addAufgabe(aufgabe);
			   AufgabeView aufgabeView = new AufgabeView(aufgabenbereichFrame, aufgabenBPanel, yCoordinateList, aufgabe);
			   
			}
		});
		btnAufgabeHinzufgen.setBounds(105, 62, 147, 25);
		aufgabenBPanel.add(btnAufgabeHinzufgen);
	

	}

}
