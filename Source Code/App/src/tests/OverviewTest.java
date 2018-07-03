package tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import models.Activity;
import models.Person;
import models.Project;
import models.ProjectList;
import models.Task;
import models.TaskGroup;

public class OverviewTest {
	

	Project testProject;
	TaskGroup testTaskGroup;
	Task testTask;
	Activity testActivity;
	Person testPerson; 
	
	List<Project> projectList = new ArrayList<>();
	List<TaskGroup> taskGroupList = new ArrayList<>();
	List<Task> taskList = new ArrayList<>();
	List<Activity> activityList = new ArrayList<>();
	List<Person> personList = new ArrayList<>();

	

	@Before
	public void setUp() {
		projectList = new ArrayList <>(); 
		testProject = new Project(db_load.LoadProject.newProjectId());
		testTaskGroup = new TaskGroup(db_load.LoadTaskGroup.newTaskGroupId());
		testTask = new Task(db_load.LoadTask.newTaskId());
		testActivity = new Activity(db_load.LoadActivity.newActivityId());
		testPerson = new Person(db_load.LoadPerson.newPersonId()); 
	}

	@After
	public void tearDown() {
		
		db_delete.Delete.deleteProject(testProject);
		db_delete.Delete.deleteTaskGroup(testTaskGroup);
		db_delete.Delete.deleteTask(testTask);
		db_delete.Delete.deleteActivity(testActivity); 
		db_delete.Delete.deletePerson(testPerson);
		
		for (Project p : projectList){
			db_delete.Delete.deleteProject(p);
		}
		
		for (TaskGroup tg : taskGroupList){
			db_delete.Delete.deleteTaskGroup(tg);
		}
		
		for (Task t : taskList){
			db_delete.Delete.deleteTask(t);
		}
		
		for (Activity a : activityList){
			db_delete.Delete.deleteActivity(a);
		}
		
		for (Person p : personList){
			db_delete.Delete.deletePerson(p);
		}
		
		projectList = null; 
		testProject = null;
		testTaskGroup = null;
		testTask = null;
		testActivity = null;
		testPerson = null;
	}
	
	@Test
	public void T261getAllPersonsFromProject() {
		Person testPerson1 = new Person(db_load.LoadPerson.newPersonId());
		Person testPerson2 = new Person(db_load.LoadPerson.newPersonId());
		Person testPerson3 = new Person(db_load.LoadPerson.newPersonId());
		
		db_save.SavePerson.newPerson(testPerson1);
		db_save.SavePerson.newPerson(testPerson2);
		db_save.SavePerson.newPerson(testPerson3);
		
		personList.add(testPerson1);
		personList.add(testPerson2);
		personList.add(testPerson3);
		
		testProject.addPerson(testPerson1);
		testProject.addPerson(testPerson2);
		testProject.addPerson(testPerson3);
		
		db_save.SaveProject.projectPerson(testProject, testPerson1);
		db_save.SaveProject.projectPerson(testProject, testPerson2);
		db_save.SaveProject.projectPerson(testProject, testPerson3);
		
		List<Person> projectList = new ArrayList<>();
		projectList =  testProject.getPersonList(); 	
	}

	@Test
	public void T262getAllPersonsFromTaskGroup() {
		Person testPerson1 = new Person(db_load.LoadPerson.newPersonId());
		Person testPerson2 = new Person(db_load.LoadPerson.newPersonId());
		Person testPerson3 = new Person(db_load.LoadPerson.newPersonId());
		
		db_save.SavePerson.newPerson(testPerson1);
		db_save.SavePerson.newPerson(testPerson2);
		db_save.SavePerson.newPerson(testPerson3);
		
		personList.add(testPerson1);
		personList.add(testPerson2);
		personList.add(testPerson3);
		
		testTaskGroup.addPerson(testPerson1);
		testTaskGroup.addPerson(testPerson2);
		testTaskGroup.addPerson(testPerson3);
		
		db_save.SaveTaskGroup.taskGroupPerson(testTaskGroup, testPerson1);
		db_save.SaveTaskGroup.taskGroupPerson(testTaskGroup, testPerson2);
		db_save.SaveTaskGroup.taskGroupPerson(testTaskGroup, testPerson3);
		
		
		List<Person> taskGroupList = new ArrayList<>();	
		taskGroupList = testTaskGroup.getPersonList(); 
	}
	
	@Test
	public void T263getAllPersonsFromTask() {
		Person testPerson1 = new Person(db_load.LoadPerson.newPersonId());
		Person testPerson2 = new Person(db_load.LoadPerson.newPersonId());
		Person testPerson3 = new Person(db_load.LoadPerson.newPersonId());
		
		db_save.SavePerson.newPerson(testPerson1);
		db_save.SavePerson.newPerson(testPerson2);
		db_save.SavePerson.newPerson(testPerson3);
		
		personList.add(testPerson1);
		personList.add(testPerson2);
		personList.add(testPerson3);
		
		testTask.addPerson(testPerson1);
		testTask.addPerson(testPerson2);
		testTask.addPerson(testPerson3);
		
		db_save.SaveTask.taskPerson(testTask, testPerson1);
		db_save.SaveTask.taskPerson(testTask, testPerson2);
		db_save.SaveTask.taskPerson(testTask, testPerson3);
		
		List<Person> taskList = new ArrayList<>();	
		taskList = testTask.getPersonList(); 
		
	}
	
	@Test
	public void T264getPersonFromActivity() {
		Person testPerson = new Person(db_load.LoadPerson.newPersonId());
		db_save.SavePerson.newPerson(testPerson);
		personList.add(testPerson);
		
		testActivity.addPerson(testPerson);
		db_save.SaveActivity.activityPerson(testActivity, testPerson);

		Person activityPerson = testActivity.getPerson(); 
		
	}
	
	@Test
	public void T271getAllProjects() {
		
		Project testProject1 = new Project(db_load.LoadProject.newProjectId());
		Project testProject2 = new Project(db_load.LoadProject.newProjectId());
		Project testProject3 = new Project(db_load.LoadProject.newProjectId());
		
		db_save.SaveProject.newProject(testProject1);
		db_save.SaveProject.newProject(testProject2);
		db_save.SaveProject.newProject(testProject3);

		projectList.add(testProject1);
		projectList.add(testProject2);
		projectList.add(testProject3);
		
		List<Project> testProjectList = new ArrayList<>();	
		testProjectList = projectList; 	
	}
	
	@Test
	public void T281getAllTaskGroupsFromProject() {
		TaskGroup testTaskGroup1 = new TaskGroup(db_load.LoadTaskGroup.newTaskGroupId());
		TaskGroup testTaskGroup2 = new TaskGroup(db_load.LoadTaskGroup.newTaskGroupId());
		TaskGroup testTaskGroup3 = new TaskGroup(db_load.LoadTaskGroup.newTaskGroupId());
		
		taskGroupList.add(testTaskGroup1);
		taskGroupList.add(testTaskGroup2);
		taskGroupList.add(testTaskGroup3);

		testProject.addTaskGroup(testTaskGroup1);
		testProject.addTaskGroup(testTaskGroup2);
		testProject.addTaskGroup(testTaskGroup3);
		
		
		List<TaskGroup> taskGroupList = new ArrayList<>();	
		taskGroupList =  testProject.getTaskGroups(); 
	}
	
	@Test
	public void T291getAllTasksFromTaskGroup() {
		Task testTask1 = new Task(db_load.LoadTask.newTaskId());
		Task testTask2 = new Task(db_load.LoadTask.newTaskId());
		Task testTask3 = new Task(db_load.LoadTask.newTaskId());
		
		db_save.SaveTask.newTask(testTask1);
		db_save.SaveTask.newTask(testTask2);
		db_save.SaveTask.newTask(testTask3);
		
		taskList.add(testTask1);
		taskList.add(testTask2);
		taskList.add(testTask3);
		
		testTaskGroup.addTask(testTask1);
		testTaskGroup.addTask(testTask2);
		testTaskGroup.addTask(testTask3);
		
		db_save.SaveTaskGroup.taskGroupTask(testTaskGroup, testTask1);
		db_save.SaveTaskGroup.taskGroupTask(testTaskGroup, testTask2);
		db_save.SaveTaskGroup.taskGroupTask(testTaskGroup, testTask3);
		
		List<Task> taskList = new ArrayList<>();	
		taskList = testTaskGroup.getTaskList(); 
	}
	
	@Test
	public void T2101getAllActivitesFromTask() {
		Activity testActivity1 = new Activity(db_load.LoadActivity.newActivityId());
		Activity testActivity2 = new Activity(db_load.LoadActivity.newActivityId());
		Activity testActivity3 = new Activity(db_load.LoadActivity.newActivityId());
		
		db_save.SaveActivity.newActivity(testActivity1);
		db_save.SaveActivity.newActivity(testActivity2);
		db_save.SaveActivity.newActivity(testActivity3);
		
		activityList.add(testActivity1); 
		activityList.add(testActivity2); 
		activityList.add(testActivity3);
		
		testTask.addActivity(testActivity1);
		testTask.addActivity(testActivity2);
		testTask.addActivity(testActivity3);
		
		db_save.SaveTask.taskActivity(testTask, testActivity1);
		db_save.SaveTask.taskActivity(testTask, testActivity2);
		db_save.SaveTask.taskActivity(testTask, testActivity3);
		
		List<Activity> activityList = new ArrayList<>();	
		activityList = testTask.getActivities(); 
	}
	
	
}
