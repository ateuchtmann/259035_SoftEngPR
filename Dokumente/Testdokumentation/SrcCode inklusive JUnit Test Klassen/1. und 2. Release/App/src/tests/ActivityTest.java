package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import files.Activity;
import files.Project;
import files.Task;
import files.TaskGroup;
import files.Time;

public class ActivityTest {

	Project testProject;
	TaskGroup testTaskGroup;
	Task testTask; 

	@Before
	public void setUp() {
		testProject = new Project();
		testTaskGroup = new TaskGroup();
		testTask = new Task(); 
	}

	@After
	public void tearDown() {
		testProject = null;
		testTaskGroup = null;
		testTask = null; 
	}

	@Test
	public void T191CreateActivity() {
		Activity testActivity = new Activity();
	}
	
	@Test
	public void T192CreateActivities() {
		Activity testActivity1 = new Activity();
		Activity testActivity2 = new Activity();
		Activity testActivity3 = new Activity();
	}
	
	@Test
	public void T1101setActivityDescription() {
		Activity testActivity = new Activity();
		testActivity.setDescr("Test description");
	}
	
	@Test
	public void T1102changeActivityDescription() {
		Activity testActivity = new Activity();
		testActivity.setDescr("Test description");
		testActivity.setDescr("changed Test description");
	}
	
	@Test
	public void T1103getActivityDescription() {
		Activity testActivity = new Activity();
		testActivity.setDescr("Test description");
		String description = testActivity.getDescrn(); 
	}
	
	@Test
	public void T1111setActivityStart() {
		Activity testActivity = new Activity();
		testActivity.setStart(new Time(8, 30));
	}
	
	@Test
	public void T1112changeActivityStart() {
		Activity testActivity = new Activity();
		testActivity.setStart(new Time(8, 30));
		testActivity.setStart(new Time(7, 0));
	}
	
	@Test
	public void T1113getActivityStart() {
		Activity testActivity = new Activity();
		testActivity.setStart(new Time(7, 0));
		String startTime = testActivity.getStart().toString(); 
	}
	
	
	
	@Test
	public void T1121setActivityEnd() {
		Activity testActivity = new Activity();
		testActivity.setEnd(new Time(15, 15));
	}
	
	@Test
	public void T1122changeActivityEnd() {
		Activity testActivity = new Activity();
		testActivity.setEnd(new Time(15, 15));
		testActivity.setEnd(new Time(16, 20));
	}
	
	@Test
	public void T1123getActivityEnd() {
		Activity testActivity = new Activity();
		testActivity.setEnd(new Time(16, 20));
		String endTime = testActivity.getEnd().toString(); 
	}
	
	@Test
	public void T1131addActivityToTask() {
		Activity testActivity = new Activity();
		testTask.addActivity(testActivity);
	}
	
	@Test
	public void T1132addActivitiesToTask() {
	
		Activity testActivity1 = new Activity();
		Activity testActivity2 = new Activity();
		Activity testActivity3 = new Activity(); 
		
		testTask.addActivity(testActivity1);
		testTask.addActivity(testActivity2);
		testTask.addActivity(testActivity3);
	}
}
