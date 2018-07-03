package views;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;

import db_load.LoadProject;
import models.Project;
import models.ProjectList;
import javax.swing.JLabel;
import java.awt.Font;

public class CreateProjectReportView {

	private JFrame creReportFrame;
	private List<Project> list;
	private ProjectList listProject;
	int yCoor = 100;
	int xCoor = 50;

	
	public CreateProjectReportView() {
		initialize();
	}


	private void initialize() {
		creReportFrame = new JFrame();
		creReportFrame.setBounds(0, 0, 1920, 1080);
		creReportFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		creReportFrame.getContentPane().setLayout(null);
		
		JLabel lblProjektreport = new JLabel("Projektreport : ");
		lblProjektreport.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblProjektreport.setBounds(15, 16, 213, 35);
		creReportFrame.getContentPane().add(lblProjektreport);
		creReportFrame.setVisible(true);
		 
		listProject = new LoadProject().everythingFromProjects();
		list = listProject.getProjectList();
		
		
		for(Project p : list){
			ProjectReportView pr = new ProjectReportView(creReportFrame, xCoor, yCoor);
			
			pr.setName(p.getName());
			int count = p.getTaskGroupNr();
			pr.setTaskGroupNr(count + "");
			int count1 = p.getTaskNr();
			pr.setTaskNr(count1 + " ");
			int count2 = p.getPersonNr();
			pr.setPersonNr(count2 + " ");
			double time = p.getProjectTime();
			
			double diffHours=0;
			double diffMinutes=0;	
			diffHours = (int) time;
			diffMinutes = time-diffHours;
			diffMinutes *= 60;
			String diffTimeString = (int)diffHours+":"+(int)diffMinutes;
			pr.setCurrTime(diffTimeString);
		
			
			double planTime = p.getPlanTime();
			diffHours = 0;
			diffMinutes = 0;
			diffHours = (int) planTime;
			diffMinutes = planTime-diffHours;
			diffMinutes *= 60;
			String timeString = (int)diffHours+":"+(int)diffMinutes;
			pr.setPlanTime(timeString);
			
			int diff = (int) ((time / planTime) * 100);
			pr.setProgressBar(diff);
			
			
			
			// calculating correct position of every project
			if (yCoor < 661) {
				yCoor = yCoor + 295;
			} else {
				yCoor = 101;
				xCoor = xCoor + 650;
			}
			
		}
	
	}
}
