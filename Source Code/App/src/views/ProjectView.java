package views;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

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

	private static JFrame prjctFrame;
	private Project prjct;
	private int colourCount = 0;
	String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20";
	String username = "u48005db20";
	String password = "prse2018";
	private JLabel lblInputName;
	private JTextArea fldInputDescr;
	int yCheckBoxCoor = 30;
	PersonList prsList;
	Map<Person, JCheckBox> chechkboxList = new HashMap<>();
	Set<JButton> btnList = new LinkedHashSet<>();
	List <Person>listFiles = new ArrayList<>();
	WaitView wait;
	private static ProjectList prjctList; 
	
	//coordinates for projects
	    int yCoor = 101;   
	    int xCoor = 50;
	    int xPrsCoor = 88;
	    final Random rColor = new Random();

	
	public ProjectView(JFrame frame, Project prjct, int xCoor, int yCoor, WaitView wait, ProjectList prjctList) {
		this.prjct = prjct;
		ProjectView.prjctFrame = frame;
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.wait = wait;
		this.prjctList = prjctList; 
		initialize();
	}
	
	// list of tasks
	
	CreateTaskGroupView createTaskGroupView;
		static Map<Integer, CreateTaskGroupView> creTaskGroupMap = new HashMap <>();
		
	// list of persons
		
		static Map<JButton, PersonProjectView> personViewMap = new HashMap<>();
		
	// getter
		
		public static JFrame getFrame() {
			return ProjectView.prjctFrame;
		}
		
	// setter
		
		public void setName(String n) {
			lblInputName.setText(n);
		}
		
		public void setDescr(String d) {
			fldInputDescr.setText(d);
		}

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
		prjctPanel.setBorder(new MatteBorder(2, 2, 3, 2, (Color) Color.DARK_GRAY));
		prjctFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
		prjctFrame.repaint();	
		
		creTaskGroupMap.put(prjctPanel.hashCode(), createTaskGroupView);  
		
		JLabel lblName = new JLabel("Name:");   // adding indication of "name"
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(15, 19, 69, 20);
		prjctPanel.add(lblName);
		
		lblInputName = new JLabel("Projektname"); // adding space to hold the input name
		lblInputName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInputName.setBounds(110, 16, 166, 26);
		prjctPanel.add(lblInputName);
		
		JLabel lblDecr = new JLabel("Beschreibung:");  //adding indication of "description"
		lblDecr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDecr.setBounds(15, 65, 114, 20);
		prjctPanel.add(lblDecr);
		
		fldInputDescr = new JTextArea();  //adding area to input description of project
		fldInputDescr.setBounds(110, 65, 403, 102);
		fldInputDescr.setLineWrap(true);
		fldInputDescr.setBorder(new BevelBorder(BevelBorder.LOWERED));
		prjctPanel.add(fldInputDescr);
		
		JButton btnEdit = new JButton("Öffnen");  // button for editing the project (Tasks etc.)
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEdit.setBounds(237, 244, 120, 29);
		prjctPanel.add(btnEdit);
		
		JButton btnEditName = new JButton("..."); //button for editing name of project
		btnEditName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEditName.setBounds(280, 16, 40, 26);
		
		// specifying the action after pressing the button for name edit
		
		btnEditName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sound.playSound(".\\sounds\\open.wav");
				
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
				btnOk.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnOk.setBounds(271, 30, 57, 35);
				inputNamePanel.add(btnOk);
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Sound.playSound(".\\sounds\\open.wav");
						String name = fldInputName.getText();
						prjct.setName(name);
						db_save.SaveProject.projectName(prjct, name);
						lblInputName.setText(name);
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
		
		prjctPanel.add(btnEditName); // adding the EDIT button to the panel of the project
		
		
		// loading data ***************************************************************************
		// creating buttons for existing persons 
		
		for(Person prs: prjct.getPersonList()) {
			
			JButton btnPrsInfo = new JButton("X.X.\r\n");
			personViewMap.put(btnPrsInfo, new PersonProjectView(prs, btnPrsInfo, prjct));
			
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
			btnPrsInfo.setBounds(xPrsCoor, 180, 72, 51);
			prjctPanel.add(btnPrsInfo);
			prjctFrame.repaint();
			xPrsCoor += 75;
			
			//adding person info (opening person view)
			btnPrsInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {		
					
					PersonProjectView pView = personViewMap.get(btnPrsInfo);
					JFrame prsViewFrame = pView.getFrame();
					prsViewFrame.setVisible(true);
				}
			});
			
			String firstInit = null;
			String secondInit = null;
		
			if(prs.getFirstName() != null && !(prs.getFirstName().equals(""))) {
			firstInit = prs.getFirstName().substring(0, 1);
	        secondInit= prs.getLastName().substring(0,1);
			}
			
			if(firstInit == null) {
				btnPrsInfo.setText("X.X");
			}else {
			btnPrsInfo.setText(firstInit + "." + secondInit + ".");
			}
			
		}// loading data *******************************************************************************
		 
		
		// specifying the action after pressing the button + (add person)
		JButton btnAddPerson = new JButton("+"); 
		btnAddPerson.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sound.playSound(".\\sounds\\open.wav");
				
				listFiles = CreatePersonsView.getPrsListFiles();
				
				JFrame prsFrame = new JFrame();
				prsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				prsFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
				prsFrame.setBounds(700, 400, 350,600);
				prsFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
				prsFrame.getContentPane().setLayout(null);
				prsFrame.setVisible(true);
				
				//adding checkbox
				for(Person p : listFiles){
					
					JCheckBox chckbxNewCheckBox = new JCheckBox(p.getLastName());
					chckbxNewCheckBox.setBackground(Color.LIGHT_GRAY);
					chckbxNewCheckBox.setFont(new Font("Verdana", Font.PLAIN, 18));
					chckbxNewCheckBox.setBounds(11, yCheckBoxCoor, 139, 29);
					prsFrame.getContentPane().add(chckbxNewCheckBox);
					chechkboxList.put(p, chckbxNewCheckBox);
					yCheckBoxCoor += 40;	
				}
				yCheckBoxCoor = 30;
				
				
				JButton btnSave = new JButton("ok");
				btnSave.setBounds(130, 500, 59, 25);
				prsFrame.getContentPane().add(btnSave);
				
				
				btnSave.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						//removing existing buttons
						for(JButton j : btnList){
							prjctPanel.remove(j);
						}
						
						
						//listing all persons and checking if selected
						for(Person p: listFiles){
							if(chechkboxList.containsKey(p)){
								if(chechkboxList.get(p).isSelected()) {
									
								
									db_save.SavePerson.newPerson(p);
									prjct.addPerson(p);
									p.addProjekt(prjct);
									db_save.SaveProject.projectPerson(prjct, p);
									
								
									String firstInit = p.getFirstName().substring(0,1);
									String scndInit = p.getLastName().substring(0,1);
									JButton btnPrsInit = new JButton("  "+ firstInit + "." + scndInit + ".");
									btnPrsInit.setBounds(xPrsCoor, 13, 50, 26);
									
									switch(colourCount) {      //set right color
									case 0:
										btnPrsInit.setBackground(new Color(102,205,170));
										break;
									case 1:
										btnPrsInit.setBackground(new Color(255,182,193));
										break;
									case 2:
										btnPrsInit.setBackground(new Color(154,205,50));
										break;
									case 3:
										btnPrsInit.setBackground(new Color(255,255,102));
										break;
									case 4:
										btnPrsInit.setBackground(new Color(189,183,107));
										break;
									case 5:
										btnPrsInit.setBackground(new Color(152,251,152));
										break;
									case 6:
										btnPrsInit.setBackground(new Color(188,143,143));
									}
									colourCount++;
									btnPrsInit.setForeground(new Color(0, 0, 0));
									btnPrsInit.setFont(new Font("Tahoma", Font.PLAIN, 15));
									btnPrsInit.setBounds(xPrsCoor, 180, 69, 51);
									btnPrsInit.setBorder(new MatteBorder(1,1,1,1,Color.BLACK));
									btnList.add(btnPrsInit);
									prjctPanel.add(btnPrsInit);
									prjctPanel.repaint();
									xPrsCoor += 75;
												
								}			
							}	
						}//end for
						xPrsCoor = 998;
							
					}
							
				});
					
			}
		});
		btnAddPerson.setBounds(25, 224, 51, 26);
		prjctPanel.add(btnAddPerson);
		
		JLabel lblPersonen = new JLabel("Personen:");
		lblPersonen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPersonen.setBounds(15, 199, 69, 16);
		prjctPanel.add(lblPersonen);
		
		JButton btnDelete = new JButton("X");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				db_delete.Delete.deleteProject(prjct);
				ProjectView.prjctList.deleteProject(prjct);
				
				wait.getFrame().setVisible(true);
				prjctFrame.setVisible(false);
				CreateProjectView cr = new CreateProjectView();
				
				prjctFrame = cr.getFrame();
				prjctFrame.setVisible(true);	
				
			}
		});
		btnDelete.setForeground(Color.RED);
		btnDelete.setBounds(475, 244, 60, 29);
		prjctPanel.add(btnDelete);
		
		
		//handling task areas of project 
		btnEdit.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent arg0) {
				
				Sound.playSound(".\\sounds\\open.wav");
				
				prjct.setDescription(fldInputDescr.getText());
				db_save.SaveProject.projectDescription(prjct, fldInputDescr.getText());
				// specifying the editing of a project (tasks etc.)
				
				JFrame taskGroupFrame = creTaskGroupMap.get(prjctPanel.hashCode()).getFrame();
				taskGroupFrame.setVisible(true);
				
			}
		});
	
		// save descr when closing window 
		prjctFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				prjct.setDescription(fldInputDescr.getText());
				db_save.SaveProject.projectDescription(prjct, fldInputDescr.getText());
			}
		});
	}//initialize
}
