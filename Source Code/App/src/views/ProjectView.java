package views;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import db_load.LoadPerson;
import db_save.SavePerson;
import db_save.SaveProject;
import models.*;
import sounds.Sound;

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
*  Date: 27.05.2018
*  Version: 1.0.23
*
* Copyright notice
* - Programm is being build by the above mentioned programmers
* 
* Purpose of program: 
* - Time scheduling of projects, tasks etc.
*/

public class ProjectView {

	private static JFrame projectFrame;
	private Project project;
	private int colorCount = 0;
	/*
	 String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20";
	String username = "u48005db20";
	String password = "prse2018";
	*/
	private JLabel labelInputName;
	private JTextArea fieldInputDescription;

	/**
	 * Launch the application.
	 */
	
	//coordinates for projects
	    int yCoor = 101;   
	    int xCoor = 50;
	    int xPersonCoor = 88;
	    final Random rColor = new Random();

	/**
	 * Create the application.
	 * @param yCoor 
	 * @param xCoor 
	 */
	public ProjectView(JFrame frame, Project project, int xCoor, int yCoor) {
		this.project = project;
		ProjectView.projectFrame = frame;
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		initialize();
	}
	
	// list of tasks
	
	CreateTaskGroupView createTaskGroupView;
		static Map<Integer, CreateTaskGroupView> creTaskGroupMap = new HashMap <>();
		
	// list of persons
		
		static Map<JButton, PersonView> personViewMap = new HashMap<>();
		
	// getter
		
		public static JFrame getFrame() {
			return ProjectView.projectFrame;
		}
		
	// setter
		
		public void setName(String n) {
			labelInputName.setText(n);
		}
		
		public void setDescription(String d) {
			fieldInputDescription.setText(d);
		}

	private void initialize() {
		
		/*
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
		    System.out.println("Database connected!");
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
		*/
		
		createTaskGroupView = new CreateTaskGroupView(project);  
		
		JPanel projectPanel = new JPanel();           //panel for the project 
		projectPanel.setBackground(Color.LIGHT_GRAY);
		projectPanel.setBounds(xCoor, yCoor, 550, 286);
		projectFrame.getContentPane().add(projectPanel);
		projectPanel.setLayout(null);
		projectPanel.setBorder(new MatteBorder(2, 2, 3, 2, (Color) Color.DARK_GRAY));
		projectFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
		projectFrame.repaint();	
		
		creTaskGroupMap.put(projectPanel.hashCode(), createTaskGroupView);  
		
		JLabel labelName = new JLabel("Name:");   // adding indication of "name"
		labelName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelName.setBounds(15, 19, 69, 20);
		projectPanel.add(labelName);
		
		labelInputName = new JLabel("Projektname"); // adding space to hold the input name
		labelInputName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelInputName.setBounds(110, 16, 166, 26);
		projectPanel.add(labelInputName);
		
		JLabel labelDescription = new JLabel("Beschreibung:");  //adding indication of "description"
		labelDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelDescription.setBounds(15, 65, 114, 20);
		projectPanel.add(labelDescription);
		
		fieldInputDescription = new JTextArea();  //adding area to input description of project
		fieldInputDescription.setBounds(110, 65, 403, 102);
		fieldInputDescription.setLineWrap(true);
		fieldInputDescription.setBorder(new BevelBorder(BevelBorder.LOWERED));
		projectPanel.add(fieldInputDescription);
		
		JButton buttonEdit = new JButton("Öffnen");  // button for editing the project (Tasks etc.)
		buttonEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonEdit.setBounds(237, 244, 120, 29);
		projectPanel.add(buttonEdit);
		
		JButton buttonEditName = new JButton("..."); //button for editing name of project
		buttonEditName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonEditName.setBounds(280, 16, 40, 26);
		
		// specifying the action after pressing the button for name edit
		
		buttonEditName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sound.playSound(".\\sounds\\open.wav");
				
				//creating second frame (window) to make input when editing name of project
				
				JFrame inptNameFrame = new JFrame(); 
		
				JPanel inputNamePanel = new JPanel();
				inputNamePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
				inputNamePanel.setLayout(null);
				
				//adding "set name:" next to input
				
				JLabel labelSetName = new JLabel("Name: ");
				labelSetName.setFont(new Font("Verdana", Font.PLAIN, 15));
				labelSetName.setBounds(31, 30, 113, 25);
				inputNamePanel.add(labelSetName);
				
				//adding area to input the name
				
				JTextArea fieldInputName = new JTextArea();
				fieldInputName.setBounds(114, 33, 150, 24);
				fieldInputName.setFont(new Font("Verdana", Font.PLAIN, 15));
				inputNamePanel.add(fieldInputName);
				
				//creating button to save name and close second frame 
	
				JButton buttonOk = new JButton("ok");
				buttonOk.setFont(new Font("Tahoma", Font.PLAIN, 15));
				buttonOk.setBounds(271, 30, 57, 35);
				inputNamePanel.add(buttonOk);
				buttonOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Sound.playSound(".\\sounds\\open.wav");
						String name = fieldInputName.getText();
						project.setName(name);
						new SaveProject().projectName(project, name);
						labelInputName.setText(name);
						inptNameFrame.dispose();
					}
				});
				
				// specifying second frame attributes
				
				inptNameFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
				inptNameFrame.setBounds(700, 400, 350,169);
				inptNameFrame.getContentPane().setBackground(SystemColor.LIGHT_GRAY);
				inptNameFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				inptNameFrame.getContentPane().add(inputNamePanel);
				inptNameFrame.setVisible(true);
		        
				
			}
		}); //buttonEditName
		
		projectPanel.add(buttonEditName); // adding the EDIT button to the panel of the project
		
		
		// loading data ***************************************************************************
		// creating buttons for existing persons 
		
		for(Person person: project.getPersonList()) {
			
			JButton buttonPersonInfo = new JButton("X.X.\r\n");
			personViewMap.put(buttonPersonInfo, new PersonView(person, buttonPersonInfo, project));
			
			switch(colorCount) {      //set right color
			case 0:
				buttonPersonInfo.setBackground(new Color(102,205,170));
				break;
			case 1:
				buttonPersonInfo.setBackground(new Color(255,182,193));
				break;
			case 2:
				buttonPersonInfo.setBackground(new Color(154,205,50));
				break;
			case 3:
				buttonPersonInfo.setBackground(new Color(255,255,102));
				break;
			case 4:
				buttonPersonInfo.setBackground(new Color(189,183,107));
				break;
			case 5:
				buttonPersonInfo.setBackground(new Color(152,251,152));
				break;
			case 6:
				buttonPersonInfo.setBackground(new Color(188,143,143));
			}
			
			colorCount++;
			buttonPersonInfo.setForeground(new Color(0, 0, 0));
			buttonPersonInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			buttonPersonInfo.setBounds(xPersonCoor, 180, 72, 51);
			projectPanel.add(buttonPersonInfo);
			projectFrame.repaint();
			xPersonCoor += 75;
			
			//adding person info (opening person view)
			buttonPersonInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {		
					
					PersonView personView = personViewMap.get(buttonPersonInfo);
					JFrame personViewFrame = personView.getFrame();
					personViewFrame.setVisible(true);
				}
			});
			
			String firstInit = null;
			String secondInit = null;
		
			if(person.getFirstName() != null && !(person.getFirstName().equals(""))) {
			firstInit = person.getFirstName().substring(0, 1);
	        secondInit= person.getLastName().substring(0,1);
			}
			
			if(firstInit == null) {
				buttonPersonInfo.setText("X.X");
			}else {
			buttonPersonInfo.setText(firstInit + "." + secondInit + ".");
			}
			
		}// loading data *******************************************************************************
		 
		
		// specifying the action after pressing the button + (add person)
		JButton buttonAddPerson = new JButton("+"); 
		buttonAddPerson.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonAddPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sound.playSound(".\\sounds\\open.wav");
				
				if(xPersonCoor <= 463) {
					
				//create and add person
				Person newPerson = new Person(new LoadPerson().newPersonId());
				new SavePerson().newPerson(newPerson);
				project.addPerson(newPerson);
				newPerson.addProjekt(project);
				new SaveProject().projectPerson(project, newPerson);
				
				JButton buttonPersonInfo = new JButton("X.X.\r\n");
				personViewMap.put(buttonPersonInfo, new PersonView(newPerson, buttonPersonInfo,project));
				
			
				switch(colorCount) {      //set right color
				case 0:
					buttonPersonInfo.setBackground(new Color(102,205,170));
					break;
				case 1:
					buttonPersonInfo.setBackground(new Color(255,182,193));
					break;
				case 2:
					buttonPersonInfo.setBackground(new Color(154,205,50));
					break;
				case 3:
					buttonPersonInfo.setBackground(new Color(255,255,102));
					break;
				case 4:
					buttonPersonInfo.setBackground(new Color(189,183,107));
					break;
				case 5:
					buttonPersonInfo.setBackground(new Color(152,251,152));
					break;
				case 6:
					buttonPersonInfo.setBackground(new Color(188,143,143));
				}
				colorCount++;
				buttonPersonInfo.setForeground(new Color(0, 0, 0));
				buttonPersonInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
				buttonPersonInfo.setBounds(xPersonCoor, 180, 72, 51);
				projectPanel.add(buttonPersonInfo);
				projectFrame.repaint();
				xPersonCoor += 75;
	
				//adding person info (opening person view)
				buttonPersonInfo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {		
						
						PersonView personView = personViewMap.get(buttonPersonInfo);
						JFrame personViewFrame = personView.getFrame();
						personViewFrame.setVisible(true);
					}
					
					
				});
				}else {  //if number of persons over max
				
					JFrame overMaxFrame = new JFrame(); 
					
					JPanel overMaxPanel = new JPanel();
					overMaxPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
					overMaxPanel.setLayout(null);
					
					//adding "error message:" next to input
					
					JLabel labelErrorMessage = new JLabel("Maximum erreicht!");
					labelErrorMessage.setFont(new Font("Verdana", Font.PLAIN, 15));
					labelErrorMessage.setBounds(31, 30, 150, 25);
					overMaxPanel.add(labelErrorMessage);
					
					//adding area to input the name
					
					//creating button to close overMax frame 
		
					JButton buttonOk = new JButton("ok");
					buttonOk.setBounds(220, 23, 57, 35);
					overMaxPanel.add(buttonOk);
					buttonOk.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Sound.playSound(".\\sounds\\open.wav");
							overMaxFrame.dispose();
						}
					});
					
					// specifying overMax frame attributes
					
					overMaxFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
					overMaxFrame.setBounds(700, 400, 350,130);
					overMaxFrame.getContentPane().setBackground(SystemColor.LIGHT_GRAY);
					overMaxFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					overMaxFrame.getContentPane().add(overMaxPanel);
					overMaxFrame.setVisible(true);
					
				}//overMaxPersonsError
				
			}//buttonAddPerson
		});
		buttonAddPerson.setBounds(25, 224, 51, 26);
		projectPanel.add(buttonAddPerson);
		
		JLabel labelPersonen = new JLabel("Personen:");
		labelPersonen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelPersonen.setBounds(15, 199, 69, 16);
		projectPanel.add(labelPersonen);
		
		
		//handling task areas of project 
		buttonEdit.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent arg0) {
				
				Sound.playSound(".\\sounds\\open.wav");
				
				project.setDescription(fieldInputDescription.getText());
				new SaveProject().projectDescription(project, fieldInputDescription.getText());
				// specifying the editing of a project (tasks etc.)
				
				JFrame taskGroupFrame = creTaskGroupMap.get(projectPanel.hashCode()).getFrame();
				taskGroupFrame.setVisible(true);
				
			}
		});
	
		// save descr when closing window 
		projectFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				project.setDescription(fieldInputDescription.getText());
				new SaveProject().projectDescription(project, fieldInputDescription.getText());
			}
		});
	}//initialize
	
}
