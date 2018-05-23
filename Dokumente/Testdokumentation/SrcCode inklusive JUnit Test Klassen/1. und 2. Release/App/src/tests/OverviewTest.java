package tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import files.Activity;
import files.Person;
import files.Project;
import files.ProjectList;
import files.Task;
import files.TaskGroup;

public class OverviewTest {
	
	ProjectList testProjectList; 
	Project testProject;
	TaskGroup testTaskGroup;
	Task testTask;
	Activity testActivity;
	Person testPerson; 
	

	@Before
	public void setUp() {
		testProjectList = new ProjectList(); 
		testProject = new Project();
		testTaskGroup = new TaskGroup();
		testTask = new Task();
		testActivity = new Activity();
		testPerson = new Person(); 
	}

	@After
	public void tearDown() {
		testProjectList = null; 
		testProject = null;
		testTaskGroup = null;
		testTask = null;
		testActivity = null;
		testPerson = null;
	}
	
	@Test
	public void T261getAllPersonsFromProject() {
		Person testPerson1 = new Person();
		Person testPerson2 = new Person();
		Person testPerson3 = new Person();
		
		testProject.addPerson(testPerson1);
		testProject.addPerson(testPerson2);
		testProject.addPerson(testPerson3);
		
		List<Person> projectList = new ArrayList<>();
		projectList =  testProject.getPersonList(); 	
	}

	@Test
	public void T262getAllPersonsFromTaskGroup() {
		Person testPerson1 = new Person();
		Person testPerson2 = new Person();
		Person testPerson3 = new Person();
		
		testTaskGroup.addPerson(testPerson1);
		testTaskGroup.addPerson(testPerson2);
		testTaskGroup.addPerson(testPerson3);
		
		
		List<TaskGroup> taskGroupList = new ArrayList<>();	
		taskGroupList = testTaskGroup.getPersonList(); 
	}
	
	@Test
	public void T263getAllPersonsFromTask() {
		Person testPerson1 = new Person();
		Person testPerson2 = new Person();
		Person testPerson3 = new Person();
		
		testTask.addPerson(testPerson1);
		testTask.addPerson(testPerson2);
		testTask.addPerson(testPerson3);
		
		List<Task> taskList = new ArrayList<>();	
		taskList = testTask.getPersonList(); 
		
	}
	
	@Test
	public void T264getPersonFromActivity() {
		Person testPerson = new Person();
	
		testActivity.addPerson(testPerson);

		Person activityPerson = testActivity.getPerson(); 
		
	}
	
	@Test
	public void T271getAllProjects() {
		
		Project testProject1 = new Project();
		Project testProject2 = new Project();
		Project testProject3 = new Project();

		testProjectList.addProject(testProject1);
		testProjectList.addProject(testProject2);
		testProjectList.addProject(testProject3);
		
		List<Project> projectList = new ArrayList<>();	
		projectList = testProjectList.getProjectList(); 	
	}
	
	@Test
	public void T281getAllTaskGroupsFromProject() {
		TaskGroup testTaskGroup1 = new TaskGroup();
		TaskGroup testTaskGroup2 = new TaskGroup();
		TaskGroup testTaskGroup3 = new TaskGroup();

		testProject.addTaskGroup(testTaskGroup1);
		testProject.addTaskGroup(testTaskGroup2);
		testProject.addTaskGroup(testTaskGroup3);
		
		
		List<TaskGroup> taskGroupList = new ArrayList<>();	
		taskGroupList =  testProject.getTsks(); 
	}
	
	@Test
	public void T291getAllTasksFromTaskGroup() {
		Task testTask1 = new Task();
		Task testTask2 = new Task();
		Task testTask3 = new Task();
		
		testTaskGroup.addTsk(testTask1);
		testTaskGroup.addTsk(testTask2);
		testTaskGroup.addTsk(testTask3);
		
		List<Task> taskList = new ArrayList<>();	
		taskList = testTaskGroup.getTaskList(); 
	}
	
	@Test
	public void T2101getAllActivitesFromTask() {
		Activity testActivity1 = new Activity();
		Activity testActivity2 = new Activity();
		Activity testActivity3 = new Activity(); 
		
		testTask.addActivity(testActivity1);
		testTask.addActivity(testActivity2);
		testTask.addActivity(testActivity3);
		
		List<Activity> activityList = new ArrayList<>();	
		activityList = testTask.getActivities(); 
	}
	
	
}
