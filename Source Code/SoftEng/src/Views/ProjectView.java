package Views;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import Files.*;
import Files.Person;
import Files.Project;

/* Classname: ProjectView
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

public class ProjectView {

	private static JFrame prjctFrame;
	private static Project prjct;
	private int colourCount = 0;
	String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20";
	String username = "u48005db20";
	String password = "prse2018";

	/**
	 * Launch the application.
	 */
	
	//coordinates for projects
	
		static int yCoor = 101;   
		static int xCoor = 50;
	    int xPrsCoor = 88;
	    final Random rColor = new Random();

	/**
	 * Create the application.
	 */
	public ProjectView(JFrame frame, Project prjct) {
		ProjectView.prjct = prjct;
		ProjectView.prjctFrame = frame;
		initialize();
	}
	
	// list of tasks
	
	CreateTaskGroupView createTaskGroupView;
		static Map<Integer, CreateTaskGroupView> creTaskGroupMap = new HashMap <>();
		
	// list of persons
		
		static Map<JButton, PersonView> personViewMap = new HashMap<>();

	private void initialize() {
		
		/*
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
		    System.out.println("Database connected!");
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
		*/
		createTaskGroupView = new CreateTaskGroupView(prjct);  
		
		JPanel prjctPanel = new JPanel();           //panel for the project 
		prjctPanel.setBackground(Color.LIGHT_GRAY);
		prjctPanel.setBounds(xCoor, yCoor, 550, 286);
		prjctFrame.getContentPane().add(prjctPanel);
		prjctPanel.setLayout(null);
		Border projectBorder = new MatteBorder(3,3,4,3,Color.BLACK);
		prjctPanel.setBorder(new MatteBorder(2, 2, 3, 2, (Color) new Color(25, 25, 112)));
		prjctFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
		prjctFrame.repaint();	
		
		creTaskGroupMap.put(prjctPanel.hashCode(), createTaskGroupView);  
		
		JLabel lblName = new JLabel("Name:");   // adding indication of "name"
		lblName.setBounds(15, 19, 69, 20);
		prjctPanel.add(lblName);
		
		JLabel lblInputName = new JLabel("Projektname"); // adding space to hold the input name
		lblInputName.setBounds(110, 16, 166, 26);
		prjctPanel.add(lblInputName);
		
		
		JLabel lblDecr = new JLabel("Beschreibung:");  //adding indication of "description"
		lblDecr.setBounds(15, 65, 114, 20);
		prjctPanel.add(lblDecr);
		
		JTextArea fldInpuDescr = new JTextArea();  //adding area to input description of project
		fldInpuDescr.setBounds(110, 65, 403, 102);
		fldInpuDescr.setLineWrap(true);
		Border textAreaBorder = new MatteBorder(3,3,4,3,Color.GRAY);
		fldInpuDescr.setBorder(new MatteBorder(2, 2, 3, 2, (Color) new Color(128, 128, 128)));
		prjctPanel.add(fldInpuDescr);
		
		JButton btnEdit = new JButton("Edit");  // button for editing the project (Tasks etc.)
		btnEdit.setBounds(237, 244, 120, 29);
		prjctPanel.add(btnEdit);
		
		JButton btnEditName = new JButton("..."); //button for editing name of project
		btnEditName.setBounds(280, 16, 40, 26);
		
		// specifying the action after pressing the button for name edit
		
		btnEditName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//creating second frame (window) to make input when editing name of project
				
				JFrame inptNameFrame = new JFrame(); 
		
				JPanel inputNamePanel = new JPanel();
				inputNamePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
				inputNamePanel.setLayout(null);
				
				//adding "set name:" next to input
				
				JLabel lblSetName = new JLabel("Name: ");
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
						
						prjct.setName(fldInputName.getText());
						String name = fldInputName.getText();
						lblInputName.setText(name);
					    System.out.println(prjct.getName());
						inptNameFrame.dispose();
					}
				});
				
				// specifying second frame attributes
				
				inptNameFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
				inptNameFrame.setBounds(700, 400, 350,169);
				inptNameFrame.getContentPane().setBackground(new Color(102, 153, 204));
				inptNameFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				inptNameFrame.getContentPane().add(inputNamePanel);
				inptNameFrame.setVisible(true);
		        
				
			}
		}); //buttonEditName
		
		prjctPanel.add(btnEditName); // adding the EDIT button to the panel of the project
		
		
		
		// specifying the action after pressing the button + (add person)
		JButton btnAddPerson = new JButton("+"); 
		btnAddPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(xPrsCoor <= 463) {
					
				//create and add person
				Person newPrs = new Person();
				
				JButton btnPrsInfo = new JButton("X.X.\r\n");
				personViewMap.put(btnPrsInfo, new PersonView(newPrs, btnPrsInfo,prjct));
				
				System.out.println(colourCount);
				switch(colourCount) {      //set right color
				case 0:
					btnPrsInfo.setBackground(new Color(102,205,170));
					break;
				case 1:
					btnPrsInfo.setBackground(new Color(255,182,193));
					break;
				case 2:
					btnPrsInfo.setBackground(new Color(154,205,50));
					break;
				case 3:
					btnPrsInfo.setBackground(new Color(255,255,102));
					break;
				case 4:
					btnPrsInfo.setBackground(new Color(189,183,107));
					break;
				case 5:
					btnPrsInfo.setBackground(new Color(152,251,152));
					break;
				case 6:
					btnPrsInfo.setBackground(new Color(188,143,143));
				}
				colourCount++;
				btnPrsInfo.setForeground(new Color(0, 0, 0));
				btnPrsInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnPrsInfo.setBounds(xPrsCoor, 180, 69, 51);
				prjctPanel.add(btnPrsInfo);
				prjctFrame.repaint();
				xPrsCoor += 75;
	
				//adding person info (opening person view)
				btnPrsInfo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {		
						
						PersonView pView = personViewMap.get(btnPrsInfo);
						JFrame prsViewFrame = pView.getFrame();
						prsViewFrame.setVisible(true);
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
		btnAddPerson.setBounds(25, 224, 51, 26);
		prjctPanel.add(btnAddPerson);
		
		JLabel lblPersonen = new JLabel("Personen:");
		lblPersonen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPersonen.setBounds(15, 199, 69, 16);
		prjctPanel.add(lblPersonen);
		
		
		//handling task areas of project 
		btnEdit.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent arg0) {
				
				prjct.setDescr(fldInpuDescr.getText());
				// specifying the editing of a project (tasks etc.)
				
				JFrame taskGroupFrame = creTaskGroupMap.get(prjctPanel.hashCode()).getFrame();
				taskGroupFrame.setVisible(true);
				
			}
		});
		

		// calculating correct position of every project 
		
		if(yCoor < 661){
			yCoor = yCoor + 295;
		}else{
			yCoor = 101;
			xCoor = xCoor + 600;
		}
		
	}
}
