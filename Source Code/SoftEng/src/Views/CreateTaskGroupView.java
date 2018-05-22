package Views;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import Files.Project;
import Files.TaskGroup;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
*  Date: 22.05.2018
*  Version: 1.0.20
*
* Copyright notice
* - Programm is being build by the above mentioned programmers
* 
* Purpose of program: 
* - Time scheduling of projects, tasks etc.
*/

public class CreateTaskGroupView {

	private JFrame creTaskGroupFrame;
	protected int xCoor = 20; // coordinate for moving taskgroup to the right
	private Project prjct;
	
	/**
	 * Launch the application.
	 */

	
	public JFrame getFrame() {
		return creTaskGroupFrame;
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
		creTaskGroupFrame = new JFrame();
		creTaskGroupFrame.setBounds(0, 0, 1920, 1080);
		creTaskGroupFrame.getContentPane().setBackground(new Color(102, 153, 204));
		creTaskGroupFrame.getContentPane().setLayout(null);
		creTaskGroupFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JButton btnCreTaskGroup = new JButton("Neuen Aufgabenbereich erstellen");
		btnCreTaskGroup.setBounds(767, 13, 362, 57);
		
		btnCreTaskGroup.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		btnCreTaskGroup.setBackground(SystemColor.LIGHT_GRAY);
		Border borderBtnCreateTaskG = new MatteBorder(3,3,4,3,Color.BLACK);
		btnCreTaskGroup.setBorder(borderBtnCreateTaskG);
		
		//specifying what happens after we press "create new aufgabenbsereich" 
		
		btnCreTaskGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				TaskGroup taskGroup = new TaskGroup();
				TaskGroupView taskGroupView = new TaskGroupView(creTaskGroupFrame, taskGroup, xCoor, prjct);
				xCoor = xCoor + 385;
			}
		});
		
		creTaskGroupFrame.getContentPane().add(btnCreTaskGroup);
			
	}//initialize
}//CreateAufgabenbereichClass
