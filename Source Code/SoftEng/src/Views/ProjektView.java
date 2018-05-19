package Views;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import Daten.*;

public class ProjektView {

	private static JFrame projektFrame;
	private static Projekt projekt;
	String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20";
	String username = "u48005db20";
	String password = "prse2018";

	

	/**
	 * Launch the application.
	 */
	
	//coordinates for projects
	
		static int yCoordinate = 101;   
		static int xCoordinate = 50;
	    int xPersCoor = 88;
	    final Random rColor = new Random();

	/**
	 * Create the application.
	 */
	public ProjektView(JFrame frame, Projekt projekt) {
		ProjektView.projekt = projekt;
		ProjektView.projektFrame = frame;
		initialize();
	}
	
	// list of aufgabenbereiche
	
		CreateAufgabenbereichView createAufgabenbereichView;
		static Map<Integer, CreateAufgabenbereichView> createAufgabenbereicheCoordinates = new HashMap <>();


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
		    System.out.println("Database connected!");
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
		
		createAufgabenbereichView = new CreateAufgabenbereichView();  
		
		JPanel projektPanel = new JPanel();           //panel for the project 
		projektPanel.setBackground(Color.LIGHT_GRAY);
		projektPanel.setBounds(xCoordinate, yCoordinate, 550, 286);
		projektFrame.getContentPane().add(projektPanel);
		projektPanel.setLayout(null);
		Border projectBorder = new MatteBorder(3,3,4,3,Color.BLACK);
		projektPanel.setBorder(new MatteBorder(2, 2, 3, 2, (Color) new Color(25, 25, 112)));
		projektFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
		
		projektFrame.repaint();	
		
		createAufgabenbereicheCoordinates.put(projektPanel.hashCode(), createAufgabenbereichView);  //****************************************************
		
		JLabel lblName = new JLabel("Name:");   // adding indication of "name"
		lblName.setBounds(15, 19, 69, 20);
		projektPanel.add(lblName);
		
		JLabel lblInputName = new JLabel("Projektname"); // adding space to hold the input name
		lblInputName.setBounds(110, 16, 166, 26);
		projektPanel.add(lblInputName);
		
		
		JLabel lblBeschreibung = new JLabel("Beschreibung:");  //adding indication of "description"
		lblBeschreibung.setBounds(15, 65, 114, 20);
		projektPanel.add(lblBeschreibung);
		
		JTextArea fldInputBeschreibung = new JTextArea();  //adding area to input description of project
		fldInputBeschreibung.setBounds(110, 65, 403, 102);
		fldInputBeschreibung.setLineWrap(true);
		Border textAreaBorder = new MatteBorder(3,3,4,3,Color.GRAY);
		fldInputBeschreibung.setBorder(new MatteBorder(2, 2, 3, 2, (Color) new Color(128, 128, 128)));
		projektPanel.add(fldInputBeschreibung);
		
		JButton btnEdit = new JButton("Edit");  // button for editing the project (Aufgabenbereiche etc.)
		btnEdit.setBounds(237, 244, 120, 29);
		projektPanel.add(btnEdit);
		
		JButton btnEditName = new JButton("..."); //button for editing name of project
		btnEditName.setBounds(280, 16, 40, 26);
		
		// specifying the action after pressing the button for name edit
		
		btnEditName.addActionListener(new ActionListener() {
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
				fldInputName.setBounds(114, 33, 150, 24);
				fldInputName.setFont(new Font("Verdana", Font.PLAIN, 15));
				inputNamePanel.add(fldInputName);
				
				//creating button to save name and close second frame 
	
				JButton btnOk = new JButton("ok");
				btnOk.setBounds(271, 30, 57, 35);
				inputNamePanel.add(btnOk);
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						projekt.setName(fldInputName.getText());
						String name = fldInputName.getText();
						lblInputName.setText(name);
					    System.out.println(projekt.getName());
						inputNameFrame.dispose();
					}
				});
				
				// specifying second frame attributes
				
				inputNameFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
				inputNameFrame.setBounds(700, 400, 350,169);
				inputNameFrame.getContentPane().setBackground(new Color(102, 153, 204));
				inputNameFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				inputNameFrame.getContentPane().add(inputNamePanel);
				inputNameFrame.setVisible(true);
		        
				
			}
		}); //buttonEditName
		
		projektPanel.add(btnEditName); // adding the EDIT button to the panel of the project
		
		
		
		JButton btnPersonen = new JButton("+"); 
		btnPersonen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(xPersCoor <= 463) {
					
				//create and add person
				Person newPerson = new Person();
				Person p = new Person();
				projekt.addPerson(p);
				
				JButton btnNewButton = new JButton("X.X.\r\n");
				btnNewButton.setBackground(new Color(210,rColor.nextInt(256),rColor.nextInt(256)));
				btnNewButton.setForeground(new Color(0, 0, 0));
				btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnNewButton.setBounds(xPersCoor, 180, 69, 51);
				projektPanel.add(btnNewButton);
				projektFrame.repaint();
				xPersCoor += 75;
	
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
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
						fldInputName.setBounds(114, 33, 150, 24);
						fldInputName.setFont(new Font("Verdana", Font.PLAIN, 15));
						inputNamePanel.add(fldInputName);
						
						//creating button to save name and close second frame 
			
						JButton btnOk = new JButton("ok");
						btnOk.setBounds(271, 30, 57, 35);
						inputNamePanel.add(btnOk);
						btnOk.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								btnNewButton.setText(fldInputName.getText());
								String name = fldInputName.getText();
								p.setVorname(name);
								inputNameFrame.dispose();
							}
						});
						
						// specifying second frame attributes
						
						inputNameFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
						inputNameFrame.setBounds(700, 400, 350,169);
						inputNameFrame.getContentPane().setBackground(new Color(102, 153, 204));
						inputNameFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
						inputNameFrame.getContentPane().add(inputNamePanel);
						inputNameFrame.setVisible(true);
				        
						
						
					}
					
					
				});
				}else {  //if number of persons over max
				
					JFrame overMaxFrame = new JFrame(); 
					
					JPanel overMaxPanel = new JPanel();
					overMaxPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
					overMaxPanel.setLayout(null);
					
					//adding "error message:" next to input
					
					JLabel lblErrorMessage = new JLabel("Maximum erreicht!");
					lblErrorMessage.setFont(new Font("Verdana", Font.PLAIN, 15));
					lblErrorMessage.setBounds(31, 30, 150, 25);
					overMaxPanel.add(lblErrorMessage);
					
					//adding area to input the name
					
					//creating button to close overMax frame 
		
					JButton btnOk = new JButton("ok");
					btnOk.setBounds(220, 23, 57, 35);
					overMaxPanel.add(btnOk);
					btnOk.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							overMaxFrame.dispose();
						}
					});
					
					// specifying overMax frame attributes
					
					overMaxFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
					overMaxFrame.setBounds(700, 400, 350,130);
					overMaxFrame.getContentPane().setBackground(new Color(102, 153, 204));
					overMaxFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					overMaxFrame.getContentPane().add(overMaxPanel);
					overMaxFrame.setVisible(true);
					
				}//overMaxPersonsError
				
			}//buttonAddPerson
		});
		btnPersonen.setBounds(25, 224, 51, 26);
		projektPanel.add(btnPersonen);
		
		JLabel lblPersonen = new JLabel("Personen:");
		lblPersonen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPersonen.setBounds(15, 199, 69, 16);
		projektPanel.add(lblPersonen);
		
		
		
		btnEdit.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				projekt.setBeschreibung(fldInputBeschreibung.getText());
	
				// specifying the editing of a project (aufgabenbereiche etc.)
				
				JFrame aufgabeBereichFrame = createAufgabenbereicheCoordinates.get(projektPanel.hashCode()).getFrame();
				aufgabeBereichFrame.setVisible(true);
				
			}
		});
		
		
		
		// calculating correct position of every project 
		
		if(yCoordinate < 661){
			yCoordinate = yCoordinate + 295;
		}else{
			yCoordinate = 101;
			xCoordinate = xCoordinate + 600;
		}
		
	}
	
	//helping methods
	
	private static int randomWithRange(int min, int max) {
		return (int)Math.random() * (max - min) + min;
	}
}
