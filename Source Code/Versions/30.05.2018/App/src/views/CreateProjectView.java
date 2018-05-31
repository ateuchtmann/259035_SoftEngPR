package views;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import files.*;
import sounds.Sound;

import java.awt.SystemColor;

/* Classname: CreateProjectView
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

public class CreateProjectView {

	private JFrame crePrjctFrame;
	private static ProjectList prjctList;
	private static List<ProjectView> prjctViewList;
	private static List<Project> prjctListFiles;
	int yCoor = 101;   
	int xCoor = 50;
	
	// getter
	
	public JFrame getFrame() {
		return this.crePrjctFrame;
	}
	
	// create the application
	
	public CreateProjectView() {
		prjctViewList = new ArrayList<>();
		prjctList = new ProjectList();
		prjctListFiles = prjctList.getProjectList();
		initialize();
	}
	

	// contents of the frame
	
	private void initialize() {
		crePrjctFrame = new JFrame();
		crePrjctFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
		
		//Toolkit tk = Toolkit.getDefaultToolkit(); 
		//int width = tk.getScreenSize().width; 
		//int height =  tk.getScreenSize().height; 
		
		crePrjctFrame.setBounds(0, 0, 1920, 1080);
		crePrjctFrame.getContentPane().setBackground(new Color(255, 255, 255));
		crePrjctFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Start-Button to make new Project
		
		JButton btnCrePrjct = new JButton("Neues Projekt erstellen");
		btnCrePrjct.setBounds(767, 13, 362, 57);
		
		btnCrePrjct.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnCrePrjct.setBackground(SystemColor.LIGHT_GRAY);

		
		// loading data************************************************************
		// creating views for existing projects
		
		for(Project p: prjctListFiles) {
			ProjectView pv = new ProjectView(crePrjctFrame,p, xCoor, yCoor);
			pv.setName(p.getName());
			pv.setDescr(p.getDescr());
			
			// calculating correct position of every project 
			if(yCoor < 661){
				yCoor = yCoor + 295;
			}else{
				yCoor = 101;
				xCoor = xCoor + 600;
			}
		}//loading data ************************************************************
		
		// Action after pressing create project button
		
		btnCrePrjct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Sound.playSound(".\\sounds\\open.wav");
				Project prjct = new Project();
				prjctList.addProject(prjct);
				ProjectView projektView = new ProjectView(crePrjctFrame, prjct, xCoor, yCoor);
			    prjctViewList.add(projektView);
			    prjctListFiles.add(prjct);
			    
			 // calculating correct position of every project 
				if(yCoor < 661){
					yCoor = yCoor + 295;
				}else{
					yCoor = 101;
					xCoor = xCoor + 600;
				}
				
			} // createProjectButton (actionPerformed)	
		}); // createProjectButton (Listener - he has only one method: action performed (bzw. the above one))
		
		crePrjctFrame.getContentPane().setLayout(null); 
		crePrjctFrame.getContentPane().add(btnCrePrjct);
		
	}
}