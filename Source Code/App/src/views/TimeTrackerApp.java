package views;
import javax.swing.UIManager;
import sounds.Sound;


/* Classname: App
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

public class TimeTrackerApp {
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		}catch(Exception e) {
			e.printStackTrace();
		}
				
	    CreateProjectView createProjectView = new CreateProjectView();
		createProjectView.getFrame().setVisible(true);
		Sound.playSound(".\\sounds\\start.wav");
	}	
	
}
