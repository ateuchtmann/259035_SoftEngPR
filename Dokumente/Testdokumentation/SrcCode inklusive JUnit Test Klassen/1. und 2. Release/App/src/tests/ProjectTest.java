package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import files.Project;
import files.ProjectList;

public class ProjectTest {

	ProjectList testProjectList;

	@Before
	public void setUp() {
		testProjectList = new ProjectList();
	}

	@After
	public void tearDown() {
		testProjectList = null;
	}

	@Test
	public void T111CreateProject() {
		Project testProject = new Project();
		testProjectList.addProject(testProject);
	}

	@Test
	public void T112setProjectName() {
		Project testProject = new Project();
		testProjectList.addProject(testProject);
		testProject.setName("TestProjekt");
	}

	@Test
	public void T113RenameProjectName() {
		Project testProject = new Project();
		testProjectList.addProject(testProject);
		testProject.setName("TestProjekt");
		testProject.setName("Renamed TestProjekt");
	}

	@Test
	public void T114getProjectName() {
		Project testProject = new Project();
		testProjectList.addProject(testProject);
		testProject.setName("TestProjekt");
		String ProjectName = testProject.getName();
	}

	@Test
	public void T115CreateProjects() {
		Project testProject1 = new Project();
		Project testProject2 = new Project();
		Project testProject3 = new Project();

		testProjectList.addProject(testProject1);
		testProjectList.addProject(testProject2);
		testProjectList.addProject(testProject3);
	}

	@Test
	public void T121setProjectDescription() {
		Project testProject = new Project();
		testProjectList.addProject(testProject);
		testProject.setDescr("Test Project Description");
	}

	@Test
	public void T122RenameProjectDescription() {
		Project testProject = new Project();
		testProjectList.addProject(testProject);
		testProject.setDescr("Test Project Description");
		testProject.setDescr("changed Test Project Description");
	}

	@Test
	public void T123getProjectDescription() {
		Project testProject = new Project();
		testProjectList.addProject(testProject);
		testProject.setDescr("Test Project Description");
		String projectDescription = testProject.getDescr();
	}

}
