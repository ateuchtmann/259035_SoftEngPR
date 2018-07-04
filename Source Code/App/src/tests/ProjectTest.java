package tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import models.Project;

/* Classname: ProjectTest
*
* Programmers/Authors: 
* 
*  1.Milos Tomic
*  2.Maja Dusanic 
*  3.Alexander Teuchtmann 
*  4.Andrea Aistleithner 
*  5.Christopher Huber 
* 
*  Date: 04.07.2018
*  Version: 1.0.20
*
* Copyright notice
* - Programm is being build by the above mentioned programmers
* 
* Purpose of program: 
* - Time scheduling of projects, tasks etc.
*/

public class ProjectTest {

	List<Project> projectList = new ArrayList<>();

	@Before
	public void setUp() {
		projectList = new ArrayList <>(); 
	}

	@After
	public void tearDown() {
		for (Project p : projectList){
			db_delete.Delete.deleteProject(p);
		}
		projectList = null; 
	}

	@Test
	public void T111CreateProject() {
		Project testProject = new Project(db_load.LoadProject.newProjectId());
		db_save.SaveProject.newProject(testProject);
		projectList.add(testProject);
	}

	@Test
	public void T112setProjectName() {
		Project testProject = new Project(db_load.LoadProject.newProjectId());
		db_save.SaveProject.newProject(testProject);
		projectList.add(testProject);
		
		testProject.setName("TestProjekt");
		db_save.SaveProject.projectName(testProject, "TestProjekt");
	}

	@Test
	public void T113RenameProjectName() {
		Project testProject = new Project(db_load.LoadProject.newProjectId());
		db_save.SaveProject.newProject(testProject);
		projectList.add(testProject);
		
		testProject.setName("TestProjekt");
		db_save.SaveProject.projectName(testProject, "TestProjekt");
		testProject.setName("Renamed TestProjekt");
		db_save.SaveProject.projectName(testProject, "Renamed TestProjekt");
		
	}

	@Test
	public void T114getProjectName() {
		Project testProject = new Project(db_load.LoadProject.newProjectId());
		db_save.SaveProject.newProject(testProject);
		projectList.add(testProject);
		
		testProject.setName("TestProjekt");
		db_save.SaveProject.projectName(testProject, "TestProjekt");
		String ProjectName = testProject.getName();
	}

	@Test
	public void T115CreateProjects() {
		Project testProject1 = new Project(db_load.LoadProject.newProjectId());
		Project testProject2 = new Project(db_load.LoadProject.newProjectId());
		Project testProject3 = new Project(db_load.LoadProject.newProjectId());
		
		db_save.SaveProject.newProject(testProject1);
		db_save.SaveProject.newProject(testProject2);
		db_save.SaveProject.newProject(testProject3);

		projectList.add(testProject1);
		projectList.add(testProject2);
		projectList.add(testProject3);
	}

	@Test
	public void T121setProjectDescription() {
		Project testProject = new Project(db_load.LoadProject.newProjectId());
		db_save.SaveProject.newProject(testProject);
		
		projectList.add(testProject);
		
		testProject.setDescription("Test Project Description");
		db_save.SaveProject.projectDescription(testProject, "Test Project Description");
	}

	@Test
	public void T122RenameProjectDescription() {
		
		Project testProject = new Project(db_load.LoadProject.newProjectId());
		db_save.SaveProject.newProject(testProject);
		
		projectList.add(testProject);
		
		testProject.setDescription("Test Project Description");
		db_save.SaveProject.projectDescription(testProject, "Test Project Description");
		
		testProject.setDescription("changed Test Project Description");
		db_save.SaveProject.projectDescription(testProject, "changed Test Project Description");
	}

	@Test
	public void T123getProjectDescription() {
		Project testProject = new Project(db_load.LoadProject.newProjectId());
		db_save.SaveProject.newProject(testProject);
		
		projectList.add(testProject);
		
		testProject.setDescription("Test Project Description");
		db_save.SaveProject.projectDescription(testProject, "Test Project Description");
		
		String projectDescription = testProject.getDescription();
	}

}
