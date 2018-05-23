package views;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import files.*;
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
*  Date: 22.05.2018
*  Version: 1.0.20
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

	//launch the application
	
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateProjectView window = new CreateProjectView();
					window.crePrjctFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// create the application
	
	public CreateProjectView() {
		prjctViewList = new ArrayList<>();
		prjctList = new ProjectList();
		initialize();
	}
	

	// contents of the frame
	
	private void initialize() {
		crePrjctFrame = new JFrame();
		crePrjctFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
		crePrjctFrame.setBounds(0, 0, 1920, 1080);
		crePrjctFrame.getContentPane().setBackground(new Color(102, 153, 204));
		crePrjctFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Start-Button to make new Project
		
		JButton btnCrePrjct = new JButton("Neues Projekt erstellen");
		btnCrePrjct.setBounds(767, 13, 362, 57);
		
		btnCrePrjct.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnCrePrjct.setBackground(SystemColor.LIGHT_GRAY);
		Border b1 = new MatteBorder(3,3,4,3,Color.BLACK);
		btnCrePrjct.setBorder(b1);
		
		// Action after pressing create project button
		
		btnCrePrjct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Project prjct = new Project();
				prjctList.addProject(prjct);
				ProjectView projektView = new ProjectView(crePrjctFrame, prjct);
			    prjctViewList.add(projektView);
				
			} // createProjectButton (actionPerformed)	
		}); // createProjectButton (Listener - he has only one method: action performed (bzw. the above one))
		
		crePrjctFrame.getContentPane().setLayout(null); 
		crePrjctFrame.getContentPane().add(btnCrePrjct);	
		
	}
}