package views;
import java.awt.Color;
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
import files.*;

/* Classname: TaskGroupView
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

public class TaskGroupView {

	private static JFrame tskGroupFrame;
	private static Map<Integer, Integer> yCoorList = new HashMap<>();
	private static TaskGroup tskGroup;
	private int x;
	private static Project prjct;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * @param projekt 
	 */
	public TaskGroupView(JFrame frame, TaskGroup tskGroup, int x, Project prjct) {
		TaskGroupView.tskGroupFrame = frame;
		TaskGroupView.tskGroup = tskGroup; 
		this.x = x;
		this.prjct = prjct;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JPanel tskPanel = new JPanel();    
		yCoorList.put(tskPanel.hashCode(), 100); 
		
		tskPanel.setBackground(Color.LIGHT_GRAY);
		tskPanel.setBounds(x, 120, 355, 780);
		tskGroupFrame.getContentPane().add(tskPanel);
		Border prjctBorder = new MatteBorder(2,2,3,2,Color.BLACK);
		tskPanel.setBorder(prjctBorder);
		tskPanel.setLayout(null);
		
		JLabel lblTaskName = new JLabel("Default Name");
		lblTaskName.setBackground(new Color(255, 255, 255));
		lblTaskName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTaskName.setBounds(12, 13, 294, 34);
		tskPanel.add(lblTaskName);
		
		 // new panel behind name (for better view)
		
		JPanel namePanel = new JPanel();
		namePanel.setBorder(new EmptyBorder(22,22,22,22));
		namePanel.setBackground(Color.WHITE);
		namePanel.setBounds(0, 0, 355, 63);
		Border panelNameBorder = new MatteBorder(3,3,4,3,Color.DARK_GRAY);
		namePanel.setBorder(panelNameBorder);
		namePanel.setLayout(null);
		
		JButton btnEdditName = new JButton("..."); //button for editing name of project
		btnEdditName.setBounds(318, 19, 25, 25);
	
		
		// specifying the action after pressing the button for name edit
		
		btnEdditName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				//creating second frame (window) to make input when editing name of project
				
				JFrame inptNameFrame = new JFrame(); 
		
				JPanel inptNamePanel = new JPanel();
				inptNamePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
				inptNamePanel.setLayout(null);
				
				//adding "set name:" next to input
				
				JLabel lblSetName = new JLabel("Name: ");
				lblSetName.setFont(new Font("Verdana", Font.PLAIN, 15));
				lblSetName.setBounds(31, 30, 113, 25);
				inptNamePanel.add(lblSetName);
				
				//adding area to input the name
				
				JTextArea fldInputName = new JTextArea();
				fldInputName.setBounds(114, 30, 320, 25);
				fldInputName.setFont(new Font("Verdana", Font.PLAIN, 15));
				inptNamePanel.add(fldInputName);
				
				//creating button to save name and close second frame 
	
				JButton btnOk = new JButton("ok");
				btnOk.setBounds(450, 30, 57, 35);
				inptNamePanel.add(btnOk);
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String str = fldInputName.getText();
						lblTaskName.setText(str); 
						tskGroup.setName(fldInputName.getText());
						inptNameFrame.dispose();
					}
				});
				
				
				// specifying second frame attributes
				
				inptNameFrame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 21));
				inptNameFrame.setBounds(700, 400, 550,169);
				inptNameFrame.getContentPane().setBackground(new Color(102, 153, 204));
				inptNameFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				inptNameFrame.getContentPane().add(inptNamePanel);
				inptNameFrame.setVisible(true);
		        
				
			}
		}); //buttonEditName
		
		tskPanel.add(btnEdditName);
		tskPanel.add(namePanel);
		
		
		JButton btnTaskAdd = new JButton("Aufgabe Hinzuf\u00FCgen");
		btnTaskAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			   Task tsk = new Task();
			   tskGroup.addTsk(tsk);
			   TaskView tskView = new TaskView(tskGroupFrame, tskPanel, yCoorList, tsk, prjct);
			   
			}
		});
		btnTaskAdd.setBounds(105, 62, 147, 25);
		tskPanel.add(btnTaskAdd);
	

	}

}
