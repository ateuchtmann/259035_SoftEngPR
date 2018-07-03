package tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import models.Activity;
import models.Person;
import models.Project;
import models.Task;
import models.TaskGroup;

public class PersonTest {

	Project testProject;
	TaskGroup testTaskGroup;
	Task testTask;
	Activity testActivity;
	
	List<Person> personList = new ArrayList<>();

	@Before
	public void setUp() {
		testProject = new Project(db_load.LoadProject.newProjectId());
		testTaskGroup = new TaskGroup(db_load.LoadTaskGroup.newTaskGroupId());
		testTask = new Task(db_load.LoadTask.newTaskId());
		testActivity = new Activity(db_load.LoadActivity.newActivityId());
	}

	@After
	public void tearDown() {
		
		db_delete.Delete.deleteProject(testProject);
		db_delete.Delete.deleteTaskGroup(testTaskGroup);
		db_delete.Delete.deleteTask(testTask);
		db_delete.Delete.deleteActivity(testActivity);
		
		for (Person p : personList){
			db_delete.Delete.deletePerson(p);
		}
		
		testProject = null;
		testTaskGroup = null;
		testTask = null;
		testActivity = null;
	}
	
	@Test
	public void T211CreatePerson() {
		Person testPerson = new Person(db_load.LoadPerson.newPersonId());
		db_save.SavePerson.newPerson(testPerson);
		personList.add(testPerson);
	}
	
	@Test
	public void T212setFirstnamePerson() {
		Person testPerson = new Person(db_load.LoadPerson.newPersonId());
		db_save.SavePerson.newPerson(testPerson);
		personList.add(testPerson);
		
		testPerson.setFirstName("Test Vorname");
		db_save.SavePerson.personFirstname(testPerson, "Test Vorname");
	}

	@Test
	public void T213setLastnamePerson() {
		Person testPerson = new Person(db_load.LoadPerson.newPersonId());
		db_save.SavePerson.newPerson(testPerson);
		personList.add(testPerson);
		
		testPerson.setLastname("Test Nachname");
		db_save.SavePerson.personLastname(testPerson, "Test Nachname");
	}
	
	@Test
	public void T214changeFirstnamePerson() {
		Person testPerson = new Person(db_load.LoadPerson.newPersonId());
		db_save.SavePerson.newPerson(testPerson);
		personList.add(testPerson);
		
		testPerson.setFirstName("Test Vorname");
		db_save.SavePerson.personFirstname(testPerson, "Test Vorname");
		
		testPerson.setFirstName("changed Test Vorname");
		db_save.SavePerson.personFirstname(testPerson, "changed Test Vorname");
	}
	
	@Test
	public void T215changeLastnamePerson() {
		Person testPerson = new Person(db_load.LoadPerson.newPersonId());
		db_save.SavePerson.newPerson(testPerson);
		personList.add(testPerson);
		
		testPerson.setLastname("Test Nachname");
		db_save.SavePerson.personLastname(testPerson, "Test Nachname");
		
		testPerson.setLastname("changed Test Nachname");
		db_save.SavePerson.personLastname(testPerson, "changed Test Nachname");
	}
	
	@Test
	public void T216getFirstnamePerson() {
		Person testPerson = new Person(db_load.LoadPerson.newPersonId());
		db_save.SavePerson.newPerson(testPerson);
		personList.add(testPerson);
		
		testPerson.setFirstName("Test Vorname");
		db_save.SavePerson.personFirstname(testPerson, "Test Vorname");
		
		String vorname = testPerson.getFirstName(); 
	}
	
	@Test
	public void T217getLastnamePerson() {
		Person testPerson = new Person(db_load.LoadPerson.newPersonId());
		db_save.SavePerson.newPerson(testPerson);
		personList.add(testPerson);
		
		testPerson.setLastname("Test Nachname");
		db_save.SavePerson.personLastname(testPerson, "Test Nachname");
		
		String nachname = testPerson.getLastName(); 
	}
	
	
	@Test
	public void T221addPersonToProject() {
		Person testPerson = new Person(db_load.LoadPerson.newPersonId());
		db_save.SavePerson.newPerson(testPerson);
		personList.add(testPerson);
		
		testProject.addPerson(testPerson);
		db_save.SaveProject.projectPerson(testProject, testPerson);
	}
	
	@Test
	public void T222addPersonsToProject() {
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
		
	}
	
	@Test
	public void T231addPersonToTaskGroup() {
		Person testPerson = new Person(db_load.LoadPerson.newPersonId());
		db_save.SavePerson.newPerson(testPerson);
		personList.add(testPerson);
		
		testTaskGroup.addPerson(testPerson);
		
		db_save.SaveTaskGroup.taskGroupPerson(testTaskGroup, testPerson);
	}
	
	@Test
	public void T232addPersonsToTaskGroup() {
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
		
	}
	
	@Test
	public void T241addPersonToTask() {
		Person testPerson = new Person(db_load.LoadPerson.newPersonId());
		db_save.SavePerson.newPerson(testPerson);
		personList.add(testPerson);
		
		testTask.addPerson(testPerson);
		db_save.SaveTask.taskPerson(testTask, testPerson);
	}
	
	@Test
	public void T242addPersonsToTask() {
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
		
	}
	
	@Test
	public void T251addPersonToActivity() {
		Person testPerson = new Person(db_load.LoadPerson.newPersonId());
		db_save.SavePerson.newPerson(testPerson);
		personList.add(testPerson);
		
		testActivity.addPerson(testPerson);
		db_save.SaveActivity.activityPerson(testActivity, testPerson);
	}

}
