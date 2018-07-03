package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import models.Activity;
import models.Person;
import models.Project;
import models.Task;
import models.TaskGroup;
import models.Time;

public class DeleteAndChangeTests {

	Activity testActivity; 

	@Test
	public void T311ChangeStartTime() {
		testActivity = new Activity(db_load.LoadActivity.newActivityId());
		db_save.SaveActivity.newActivity(testActivity);
		
		testActivity.setStart(new Time(8, 30));
		db_save.SaveActivity.activityStart(testActivity, new Time(8, 30));
		
		testActivity.setStart(new Time(11, 0));
		db_save.SaveActivity.activityStart(testActivity, new Time(11, 0));	
		
		//to make sure, nothing is left in the db, after the test
		db_delete.Delete.deleteActivity(testActivity);
	}
	
	@Test
	public void T312ChangeEndTime() {
		 testActivity = new Activity(db_load.LoadActivity.newActivityId());
		db_save.SaveActivity.newActivity(testActivity);
		
		testActivity.setEnd(new Time(15, 15));
		db_save.SaveActivity.activityEnd(testActivity, new Time(15, 15));
		
		testActivity.setEnd(new Time(16, 20));
		db_save.SaveActivity.activityEnd(testActivity, new Time(17, 35));	
		
		//to make sure, nothing is left in the db, after the test
		db_delete.Delete.deleteActivity(testActivity);
	}
	
	@Test
	public void T321DeleteTime() {
		 testActivity = new Activity(db_load.LoadActivity.newActivityId());
		db_save.SaveActivity.newActivity(testActivity);
		
		testActivity.setStart(new Time(15, 15));
		db_save.SaveActivity.activityEnd(testActivity, new Time(10, 30));
		
		testActivity.setEnd(new Time(16, 20));
		db_save.SaveActivity.activityEnd(testActivity, new Time(12, 0));
		
		
		testActivity.setStart(new Time(15, 15));
		db_save.SaveActivity.activityEnd(testActivity, new Time(0, 0));
		
		testActivity.setEnd(new Time(16, 20));
		db_save.SaveActivity.activityEnd(testActivity, new Time(0, 0));
		
		//to make sure, nothing is left in the db, after the test
		db_delete.Delete.deleteActivity(testActivity);
		
	}
	
	@Test
	public void T331DeletePerson() {
		Person testPerson = new Person(db_load.LoadPerson.newPersonId());
		db_save.SavePerson.newPerson(testPerson);
		
		db_delete.Delete.deletePerson(testPerson);	
	}
	
	@Test
	public void T341DeleteProject() {
		Project testProject = new Project(db_load.LoadProject.newProjectId());
		db_save.SaveProject.newProject(testProject);
		
		db_delete.Delete.deleteProject(testProject);	
	}
	
	@Test
	public void T351DeleteTaskGroup() {
		TaskGroup testTaskGroup = new TaskGroup(db_load.LoadTaskGroup.newTaskGroupId());
		db_save.SaveTaskGroup.newTaskGroup(testTaskGroup);
		
		db_delete.Delete.deleteTaskGroup(testTaskGroup);	
	}
	
	@Test
	public void T361DeleteTaskGroup() {
		Task testTask = new Task(db_load.LoadTask.newTaskId());
		db_save.SaveTask.newTask(testTask);
		
		db_delete.Delete.deleteTask(testTask);	
	}
	
	
	
	
	
	
	

}
