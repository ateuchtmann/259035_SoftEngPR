package tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import models.Project;
import models.TaskGroup;

public class TaskGroupTest {

	Project testProject;
	List<TaskGroup> taskGroupList = new ArrayList<>();
	

	@Before
	public void setUp() {
		testProject = new Project(db_load.LoadProject.newProjectId());
	}

	@After
	public void tearDown() {
		
		db_delete.Delete.deleteProject(testProject);
		
		for (TaskGroup tg : taskGroupList){
			db_delete.Delete.deleteTaskGroup(tg);
		}
		testProject = null;
	}

	@Test
	public void T131CreateTaskGroup() {
		TaskGroup testTaskGroup = new TaskGroup(db_load.LoadTaskGroup.newTaskGroupId());
		db_save.SaveTaskGroup.newTaskGroup(testTaskGroup);
		taskGroupList.add(testTaskGroup); 
	}

	@Test
	public void T132setTaskGroupName() {
		TaskGroup testTaskGroup = new TaskGroup(db_load.LoadTaskGroup.newTaskGroupId());
		db_save.SaveTaskGroup.newTaskGroup(testTaskGroup);
		taskGroupList.add(testTaskGroup); 
		testTaskGroup.setName("TestAufgabenbereich");
		db_save.SaveTaskGroup.taskGroupName(testTaskGroup, "TestAufgabenbereich");
	}

	@Test
	public void T133renameTaskGroupName() {
		TaskGroup testTaskGroup = new TaskGroup(db_load.LoadTaskGroup.newTaskGroupId());
		db_save.SaveTaskGroup.newTaskGroup(testTaskGroup);
		taskGroupList.add(testTaskGroup); 
		testTaskGroup.setName("TestAufgabenbereich");
		db_save.SaveTaskGroup.taskGroupName(testTaskGroup, "TestAufgabenbereich");
		testTaskGroup.setName("Renamed TestAufgabenbereich");
		db_save.SaveTaskGroup.taskGroupName(testTaskGroup, "Renamed TestAufgabenbereich");
	}

	@Test
	public void T134createTaskGroups() {
		TaskGroup testTaskGroup1 = new TaskGroup(db_load.LoadTaskGroup.newTaskGroupId());
		TaskGroup testTaskGroup2 = new TaskGroup(db_load.LoadTaskGroup.newTaskGroupId());
		TaskGroup testTaskGroup3 = new TaskGroup(db_load.LoadTaskGroup.newTaskGroupId());
		
		db_save.SaveTaskGroup.newTaskGroup(testTaskGroup1);
		db_save.SaveTaskGroup.newTaskGroup(testTaskGroup2);
		db_save.SaveTaskGroup.newTaskGroup(testTaskGroup3);
	
		taskGroupList.add(testTaskGroup1);
		taskGroupList.add(testTaskGroup2);
		taskGroupList.add(testTaskGroup3);
	}

	@Test
	public void T141addTaskGroupToProject() {
		TaskGroup testTaskGroup = new TaskGroup(db_load.LoadTaskGroup.newTaskGroupId());
		db_save.SaveTaskGroup.newTaskGroup(testTaskGroup);
		taskGroupList.add(testTaskGroup); 

		testProject.addTaskGroup(testTaskGroup);
	}

	@Test
	public void T142addTaskGroupsToProject() {
		TaskGroup testTaskGroup1 = new TaskGroup(db_load.LoadTaskGroup.newTaskGroupId());
		TaskGroup testTaskGroup2 = new TaskGroup(db_load.LoadTaskGroup.newTaskGroupId());
		TaskGroup testTaskGroup3 = new TaskGroup(db_load.LoadTaskGroup.newTaskGroupId());
		
		db_save.SaveTaskGroup.newTaskGroup(testTaskGroup1);
		db_save.SaveTaskGroup.newTaskGroup(testTaskGroup2);
		db_save.SaveTaskGroup.newTaskGroup(testTaskGroup3);
		
		taskGroupList.add(testTaskGroup1);
		taskGroupList.add(testTaskGroup2);
		taskGroupList.add(testTaskGroup3);

		testProject.addTaskGroup(testTaskGroup1);
		testProject.addTaskGroup(testTaskGroup2);
		testProject.addTaskGroup(testTaskGroup3);
	}

}
