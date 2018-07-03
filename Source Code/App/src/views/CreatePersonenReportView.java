package views;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import db_load.LoadPerson;
import db_load.LoadProject;
import models.Person;
import models.PersonList;
import models.Project;
import models.ProjectList;

public class CreatePersonenReportView {

	private JFrame frame;
	private List<Person> list;
	private PersonList listPerson;
	int yCoor = 100;
	int xCoor = 50;
	private ProjectList listProject;

	public CreatePersonenReportView() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0,0, 1920, 1080);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblPersonReport = new JLabel("Personenrepors:");
		lblPersonReport.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblPersonReport.setBounds(27, 28, 345, 31);
		frame.getContentPane().add(lblPersonReport);
		
		listPerson = db_load.LoadPerson.everythingFromPerson();
		list = listPerson.getPersonList();
		
		
		listProject = new LoadProject().everythingFromProjects();
		
		
		for(Person p: list){
			
			PersonReportView pr = new PersonReportView(frame, xCoor, yCoor);
			
		
			pr.setName(p.getFirstName() + " " + p.getLastName());
			
			String s = listProject.getPerson(p);
			pr.setProjects(s);
			
			String s1 = listProject.getActivity(p);
			pr.setActivity(s1);
			
			double time = listProject.getPersonTime(p);
			double diffHours = 0;
			double diffMinutes = 0;
			diffHours = (int) time;
			diffMinutes = time-diffHours;
			diffMinutes *= 60;
			String timeString = (int)diffHours+":"+(int)diffMinutes;
			pr.setTime(timeString);
			
			
			
			// calculating correct position of every project
			if (yCoor < 661) {
				yCoor = yCoor + 350;
			} else {
				yCoor = 101;
				xCoor = xCoor + 650;
			}
			
		}
		
	}

}
