package views;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import files.*;
import sounds.Sound;

/* Classname: CreateTaskGroupView
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

public class CreateTaskGroupView {

	private JFrame creTskGroupFrame;
	protected int xCoor = 20; // coordinate for moving taskgroup to the right
	private Project prjct;
	
	/**
	 * Launch the application.
	 */

	
	public JFrame getFrame() {
		return creTskGroupFrame;
	}
	
	public int getX() {
		return xCoor;
	}

	/**
	 * Create the application.
	 * @param projekt2 
	 */
	public CreateTaskGroupView(Project prjct) {
		this.prjct = prjct;
		initialize();
	}

	/**
s	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		creTskGroupFrame = new JFrame();
		creTskGroupFrame.setBounds(0, 0, 1920, 1080);
		creTskGroupFrame.getContentPane().setBackground(new Color(255, 255, 255));
		creTskGroupFrame.getContentPane().setLayout(null);
		creTskGroupFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JButton btnCreTaskGroup = new JButton("Neuen Aufgabenbereich erstellen");
		btnCreTaskGroup.setBounds(767, 13, 362, 57);
		
		btnCreTaskGroup.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnCreTaskGroup.setBackground(SystemColor.LIGHT_GRAY);
		
		// loading data*********************************************************************
				// creating views for existing taskGroups
				
				for(TaskGroup tg: prjct.getTsks()) {
					TaskGroupView taskGroupView = new TaskGroupView(creTskGroupFrame, tg, xCoor, prjct);
					taskGroupView.setName(tg.getName());
					
					// calculating correct position of every taskGroup
						xCoor = xCoor + 385;
					
				}// loading data ************************************************************
		
		

		// creating new taskGroup
		
		btnCreTaskGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				Sound.playSound("C:\\Users\\tomic\\Desktop\\SE-PR\\App\\open.wav");
				TaskGroup taskGroup = new TaskGroup();
				prjct.addTaskGroup(taskGroup);
				@SuppressWarnings("unused")
				TaskGroupView taskGroupView = new TaskGroupView(creTskGroupFrame, taskGroup, xCoor, prjct);
				xCoor = xCoor + 385;
			}
		});
		
		creTskGroupFrame.getContentPane().add(btnCreTaskGroup);
		
			
	}//initialize
	
	
	
	
}//CreateAufgabenbereichClass
