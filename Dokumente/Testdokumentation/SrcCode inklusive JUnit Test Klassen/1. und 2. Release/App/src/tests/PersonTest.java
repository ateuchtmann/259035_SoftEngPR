package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import files.Activity;
import files.Person;
import files.Project;
import files.Task;
import files.TaskGroup;

public class PersonTest {

	Project testProject;
	TaskGroup testTaskGroup;
	Task testTask;
	Activity testActivity;

	@Before
	public void setUp() {
		testProject = new Project();
		testTaskGroup = new TaskGroup();
		testTask = new Task();
		testActivity = new Activity();
	}

	@After
	public void tearDown() {
		testProject = null;
		testTaskGroup = null;
		testTask = null;
		testActivity = null;
	}
	
	@Test
	public void T211CreatePerson() {
		Person testPerson = new Person();
	}
	
	@Test
	public void T212setFirstnamePerson() {
		Person testPerson = new Person();
		testPerson.setFirstName("Test Vorname");
	}

	@Test
	public void T213setLastnamePerson() {
		Person testPerson = new Person();
		testPerson.setLastname("Test Nachname");
	}
	
	@Test
	public void T214changeFirstnamePerson() {
		Person testPerson = new Person();
		testPerson.setFirstName("Test Vorname");
		testPerson.setFirstName("changed Test Vorname");
	}
	
	@Test
	public void T215changeLastnamePerson() {
		Person testPerson = new Person();
		testPerson.setLastname("Test Nachname");
		testPerson.setLastname("changed Test Nachname");
	}
	
	@Test
	public void T216getFirstnamePerson() {
		Person testPerson = new Person();
		testPerson.setFirstName("Test Vorname");
		String vorname = testPerson.getFirstName(); 
	}
	
	@Test
	public void T217getLastnamePerson() {
		Person testPerson = new Person();
		testPerson.setLastname("Test Nachname");
		String nachname = testPerson.getLastname(); 
	}
	
	
	@Test
	public void T221addPersonToProject() {
		Person testPerson = new Person();
		testProject.addPerson(testPerson);
	}
	
	@Test
	public void T222addPersonsToProject() {
		Person testPerson1 = new Person();
		Person testPerson2 = new Person();
		Person testPerson3 = new Person();
		
		testProject.addPerson(testPerson1);
		testProject.addPerson(testPerson2);
		testProject.addPerson(testPerson3);
		
	}
	
	@Test
	public void T231addPersonToTaskGroup() {
		Person testPerson = new Person();
		testTaskGroup.addPerson(testPerson);
	}
	
	@Test
	public void T232addPersonsToTaskGroup() {
		Person testPerson1 = new Person();
		Person testPerson2 = new Person();
		Person testPerson3 = new Person();
		
		testTaskGroup.addPerson(testPerson1);
		testTaskGroup.addPerson(testPerson2);
		testTaskGroup.addPerson(testPerson3);
		
	}
	
	@Test
	public void T241addPersonToTask() {
		Person testPerson = new Person();
		testTask.addPerson(testPerson);
	}
	
	@Test
	public void T242addPersonsToTask() {
		Person testPerson1 = new Person();
		Person testPerson2 = new Person();
		Person testPerson3 = new Person();
		
		testTask.addPerson(testPerson1);
		testTask.addPerson(testPerson2);
		testTask.addPerson(testPerson3);
		
	}
	
	@Test
	public void T251addPersonToActivity() {
		Person testPerson = new Person();
		testActivity.addPerson(testPerson);
	}

}
