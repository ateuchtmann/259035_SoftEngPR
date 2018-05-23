package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import files.Project;
import files.TaskGroup;

public class TaskGroupTest {

	Project testProject;

	@Before
	public void setUp() {
		testProject = new Project();
	}

	@After
	public void tearDown() {
		testProject = null;
	}

	@Test
	public void T131CreateTaskGroup() {
		TaskGroup testTaskGroup = new TaskGroup();
	}

	@Test
	public void T132setTaskGroupName() {
		TaskGroup testTaskGroup = new TaskGroup();
		testTaskGroup.setName("TestAufgabenbereich");
	}

	@Test
	public void T133renameTaskGroupName() {
		TaskGroup testTaskGroup = new TaskGroup();
		testTaskGroup.setName("TestAufgabenbereich");
		testTaskGroup.setName("Renamed TestAufgabenbereich");
	}

	@Test
	public void T134createTaskGroups() {
		TaskGroup testTaskGroup1 = new TaskGroup();
		TaskGroup testTaskGroup2 = new TaskGroup();
		TaskGroup testTaskGroup3 = new TaskGroup();
	}

	@Test
	public void T141addTaskGroupToProject() {
		TaskGroup testTaskGroup = new TaskGroup();

		testProject.addTaskGroup(testTaskGroup);
	}

	@Test
	public void T142addTaskGroupsToProject() {
		TaskGroup testTaskGroup1 = new TaskGroup();
		TaskGroup testTaskGroup2 = new TaskGroup();
		TaskGroup testTaskGroup3 = new TaskGroup();

		testProject.addTaskGroup(testTaskGroup1);
		testProject.addTaskGroup(testTaskGroup2);
		testProject.addTaskGroup(testTaskGroup3);
	}

}
