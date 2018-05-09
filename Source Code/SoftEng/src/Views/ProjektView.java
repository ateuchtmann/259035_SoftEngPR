package Views;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
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

	/**
	 * Launch the application.
	 */
	
	//coordinates for projects
	
		static int yCoordinate = 101;   
		static int xCoordinate = 15;

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
		
		createAufgabenbereichView = new CreateAufgabenbereichView();  
		
		JPanel projektPanel = new JPanel();           //panel for the project 
		projektPanel.setBackground(Color.LIGHT_GRAY);
		projektPanel.setBounds(xCoordinate, yCoordinate, 343, 240);
		projektFrame.getContentPane().add(projektPanel);
		projektPanel.setLayout(null);
		Border projectBorder = new MatteBorder(3,3,4,3,Color.BLACK);
		projektPanel.setBorder(projectBorder);
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
		fldInputBeschreibung.setBounds(110, 65, 210, 102);
		fldInputBeschreibung.setLineWrap(true);
		Border textAreaBorder = new MatteBorder(3,3,4,3,Color.GRAY);
		fldInputBeschreibung.setBorder(textAreaBorder);
		projektPanel.add(fldInputBeschreibung);
		
		JButton btnEdit = new JButton("Edit");  // button for editing the project (Aufgabenbereiche etc.)
		btnEdit.setBounds(111, 195, 115, 29);
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
			yCoordinate = yCoordinate + 280;
		}else{
			yCoordinate = 101;
			xCoordinate = xCoordinate + 380;
		}
		
	}

}
