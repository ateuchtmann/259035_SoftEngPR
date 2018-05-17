package Views;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Daten.Aufgabenbereich;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CreateAufgabenbereichView {

	private JFrame createAufgabenbereichFrame;
	protected int xCoordinate = 20; // coordinate for moving aufgabenbereich to the right
	
	/**
	 * Launch the application.
	 */

	
	public JFrame getFrame() {
		return createAufgabenbereichFrame;
	}
	
	public int getX() {
		return xCoordinate;
	}

	/**
	 * Create the application.
	 */
	public CreateAufgabenbereichView() {
		initialize();
	}

	/**
s	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		createAufgabenbereichFrame = new JFrame();
		createAufgabenbereichFrame.setBounds(0, 0, 1920, 1080);
		createAufgabenbereichFrame.getContentPane().setBackground(new Color(102, 153, 204));
		createAufgabenbereichFrame.getContentPane().setLayout(null);
		createAufgabenbereichFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JButton btnCreateAufgabenbereich = new JButton("Neuen Aufgabenbereich erstellen");
		btnCreateAufgabenbereich.setBounds(767, 13, 362, 57);
		
		btnCreateAufgabenbereich.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnCreateAufgabenbereich.setBackground(SystemColor.LIGHT_GRAY);
		Border borderBtnCreateAB = new MatteBorder(3,3,4,3,Color.BLACK);
		btnCreateAufgabenbereich.setBorder(borderBtnCreateAB);
		
		//specifying what happens after we press "create new aufgabenbsereich" 
		
		btnCreateAufgabenbereich.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				Aufgabenbereich aufgabenB = new Aufgabenbereich();
				AufgabenbereichView aufgabenbereichView = new AufgabenbereichView(createAufgabenbereichFrame, aufgabenB, xCoordinate);
				xCoordinate = xCoordinate + 385;
			}
		});
		
		createAufgabenbereichFrame.getContentPane().add(btnCreateAufgabenbereich);
		
		        // code f�r bessere sicht //
				//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
				//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
				/*		
				
				//panel for the aufgabenbereich
				
				JPanel aufBereichPanel = new JPanel();           
				aufBereichPanel.setBackground(Color.LIGHT_GRAY);
				aufBereichPanel.setBounds(x, 120, 355, 810);
				frame.getContentPane().add(aufBereichPanel);
				Border projectBorder = new MatteBorder(2,2,3,2,Color.BLACK);
				aufBereichPanel.setBorder(projectBorder);
				aufBereichPanel.setLayout(null);
				
				JLabel aufBereichName = new JLabel("Default Name");
				aufBereichName.setBackground(new Color(255, 255, 255));
				aufBereichName.setFont(new Font("Tahoma", Font.PLAIN, 17));
				aufBereichName.setBounds(12, 13, 294, 34);
				aufBereichPanel.add(aufBereichName);
				
				 // new panel behind name (for better view)
				
				JPanel contentPaneName = new JPanel();
				contentPaneName.setBorder(new EmptyBorder(22,22,22,22));
				contentPaneName.setBackground(Color.WHITE);
				contentPaneName.setBounds(0, 0, 355, 63);
				Border paneNameBorder = new MatteBorder(3,3,4,3,Color.DARK_GRAY);
				contentPaneName.setBorder(paneNameBorder);
				contentPaneName.setLayout(null);
				
				JButton buttonEditName = new JButton("..."); //button for editing name of project
				buttonEditName.setBounds(318, 19, 25, 25);
				
				// specifying the action after pressing the button for name edit
				
				buttonEditName.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//creating second frame (window) to make input when editing name of project
						
						JFrame frame2 = new JFrame(); 
				
						JPanel contentPane = new JPanel();
						contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
						contentPane.setLayout(null);
						
						//adding "set name:" next to input
						
						JLabel lblNewLabel2 = new JLabel("Set name: ");
						lblNewLabel2.setFont(new Font("Verdana", Font.PLAIN, 15));
						lblNewLabel2.setBounds(31, 30, 113, 25);
						contentPane.add(lblNewLabel2);
						
						//adding area to input the name
						
						JTextArea inputNameArea = new JTextArea();
						inputNameArea.setBounds(114, 30, 320, 25);
						inputNameArea.setFont(new Font("Verdana", Font.PLAIN, 15));
						contentPane.add(inputNameArea);
						
						//creating button to save name and close second frame 
			
						JButton btnNewButton_3 = new JButton("ok");
						btnNewButton_3.setBounds(450, 30, 57, 35);
						contentPane.add(btnNewButton_3);
						btnNewButton_3.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								String str = inputNameArea.getText();
								aufBereichName.setText(str);  
								frame2.dispose();
							}
						});
						
						
						// specifying second frame attributes
						
						frame2.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
						frame2.setBounds(700, 400, 550,169);
						frame2.getContentPane().setBackground(new Color(102, 153, 204));
						frame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
						frame2.getContentPane().add(contentPane);
						frame2.setVisible(true);
				        
						
					}
				}); //buttonEditName
				
				JButton btnAufgabeHinzufgen = new JButton("Aufgabe Hinzuf\u00FCgen");
				btnAufgabeHinzufgen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						JPanel aufgabePanel = new JPanel();
						aufgabePanel.setBounds(12, 100, 331, 100);
						aufgabePanel.setLayout(null);
						
						JTextArea aufgabeArea = new JTextArea();
						aufgabeArea.setBounds(0, 0, 331, 74);
						aufgabePanel.add(aufgabeArea);
						
						JLabel aufgabeSollzeit = new JLabel("Sollzeit:");
						aufgabeSollzeit.setBounds(10, 81, 56, 16);
						aufgabePanel.add(aufgabeSollzeit);
						
						textField = new JTextField();
						textField.setBounds(66, 78, 45, 22);
						aufgabePanel.add(textField);
						textField.setColumns(10);
						
						aufBereichPanel.add(aufgabePanel);
						frame.repaint();
						
					}
				});
				btnAufgabeHinzufgen.setBounds(105, 62, 147, 25);
				aufBereichPanel.add(btnAufgabeHinzufgen);
				aufBereichPanel.add(buttonEditName);
				aufBereichPanel.add(contentPaneName);
			
				*/
			
	}//initialize
}//CreateAufgabenbereichClass