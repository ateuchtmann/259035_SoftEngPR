package Views;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import Daten.*;

import java.awt.SystemColor;

public class CreateProjektView {

	private JFrame createProjektFrame;
	private static ProjektListe projektListe;
	private static List<ProjektView> projektViewListe;

	//launch the application
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateProjektView window = new CreateProjektView();
					window.createProjektFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// create the application
	
	public CreateProjektView() {
		projektViewListe = new ArrayList<>();
		projektListe = new ProjektListe();
		initialize();
	}
	

	// contents of the frame
	
	private void initialize() {
		createProjektFrame = new JFrame();
		createProjektFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
		createProjektFrame.setBounds(0, 0, 1920, 1080);
		createProjektFrame.getContentPane().setBackground(new Color(102, 153, 204));
		createProjektFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Start-Button to make new Project
		
		JButton btnCreateProject = new JButton("Neues Projekt erstellen");
		btnCreateProject.setBounds(767, 13, 362, 57);
		
		btnCreateProject.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnCreateProject.setBackground(SystemColor.LIGHT_GRAY);
		Border b1 = new MatteBorder(3,3,4,3,Color.BLACK);
		btnCreateProject.setBorder(b1);
		
		// Action after pressing create project button
		
		btnCreateProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Projekt p = new Projekt();
				projektListe.addProjekt(p);
				ProjektView projektView = new ProjektView(createProjektFrame, p);
			    projektViewListe.add(projektView);
				
			} // createProjectButton (actionPerformed)	
		}); // createProjectButton (Listener - he has only one method: action performed (bzw. the above one))
		
		createProjektFrame.getContentPane().setLayout(null); 
		createProjektFrame.getContentPane().add(btnCreateProject);
		
	}
}