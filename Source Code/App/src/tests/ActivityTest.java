package tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import models.Activity;
import models.Project;
import models.Task;
import models.TaskGroup;
import models.Time;

public class ActivityTest {

	Project testProject;
	TaskGroup testTaskGroup;
	Task testTask; 
	
	List<Activity> activityList = new ArrayList<>();
	

	@Before
	public void setUp() {
		testProject = new Project(db_load.LoadProject.newProjectId());
		testTaskGroup = new TaskGroup(db_load.LoadTaskGroup.newTaskGroupId());
		testTask = new Task(db_load.LoadTask.newTaskId());
	}

	@After
	public void tearDown() {
		db_delete.Delete.deleteProject(testProject);
		db_delete.Delete.deleteTaskGroup(testTaskGroup);
		db_delete.Delete.deleteTask(testTask);
		
		for (Activity a : activityList){
			db_delete.Delete.deleteActivity(a);
		}
		
		testProject = null;
		testTaskGroup = null;
		testTask = null; 
	}

	@Test
	public void T191CreateActivity() {
		Activity testActivity = new Activity(db_load.LoadActivity.newActivityId());
		db_save.SaveActivity.newActivity(testActivity);
		activityList.add(testActivity); 
	}
	
	@Test
	public void T192CreateActivities() {
		Activity testActivity1 = new Activity(db_load.LoadActivity.newActivityId());
		Activity testActivity2 = new Activity(db_load.LoadActivity.newActivityId());
		Activity testActivity3 = new Activity(db_load.LoadActivity.newActivityId());
		
		db_save.SaveActivity.newActivity(testActivity1);
		db_save.SaveActivity.newActivity(testActivity2);
		db_save.SaveActivity.newActivity(testActivity3);
		
		activityList.add(testActivity1); 
		activityList.add(testActivity2); 
		activityList.add(testActivity3); 
	}
	
	@Test
	public void T1101setActivityDescription() {
		Activity testActivity = new Activity(db_load.LoadActivity.newActivityId());
		db_save.SaveActivity.newActivity(testActivity);
		activityList.add(testActivity);
		
		testActivity.setDescription ("Test description");
		db_save.SaveActivity.activityDescription(testActivity, "Test description");
	}
	
	@Test
	public void T1102changeActivityDescription() {
		Activity testActivity = new Activity(db_load.LoadActivity.newActivityId());
		db_save.SaveActivity.newActivity(testActivity);
		activityList.add(testActivity);
		
		testActivity.setDescription ("Test description");
		db_save.SaveActivity.activityDescription(testActivity, "Test description");
		
		testActivity.setDescription("changed Test description");
		db_save.SaveActivity.activityDescription(testActivity, "changed Test description");
	}
	
	@Test
	public void T1103getActivityDescription() {
		Activity testActivity = new Activity(db_load.LoadActivity.newActivityId());
		db_save.SaveActivity.newActivity(testActivity);
		activityList.add(testActivity);
		
		testActivity.setDescription ("Test description");
		db_save.SaveActivity.activityDescription(testActivity, "Test description");
		String description = testActivity.getDescription(); 
	}
	
	@Test
	public void T1111setActivityStart() {
		Activity testActivity = new Activity(db_load.LoadActivity.newActivityId());
		db_save.SaveActivity.newActivity(testActivity);
		activityList.add(testActivity);
		
		testActivity.setStart(new Time(8, 30));
		db_save.SaveActivity.activityStart(testActivity, new Time(8, 30));
	}
	
	@Test
	public void T1112changeActivityStart() {
		Activity testActivity = new Activity(db_load.LoadActivity.newActivityId());
		db_save.SaveActivity.newActivity(testActivity);
		activityList.add(testActivity);
		
		testActivity.setStart(new Time(8, 30));
		db_save.SaveActivity.activityStart(testActivity, new Time(8, 30));
		
		testActivity.setStart(new Time(7, 0));
		db_save.SaveActivity.activityStart(testActivity, new Time(7, 0));
	}
	
	@Test
	public void T1113getActivityStart() {
		Activity testActivity = new Activity(db_load.LoadActivity.newActivityId());
		db_save.SaveActivity.newActivity(testActivity);
		activityList.add(testActivity);
		
		testActivity.setStart(new Time(7, 0));
		db_save.SaveActivity.activityStart(testActivity, new Time(7, 0));
		
		String startTime = testActivity.getStart().toString(); 
	}
	
	
	
	@Test
	public void T1121setActivityEnd() {
		Activity testActivity = new Activity(db_load.LoadActivity.newActivityId());
		db_save.SaveActivity.newActivity(testActivity);
		activityList.add(testActivity);
		
		testActivity.setEnd(new Time(15, 15));
		db_save.SaveActivity.activityEnd(testActivity, new Time(15, 15));
	}
	
	@Test
	public void T1122changeActivityEnd() {
		Activity testActivity = new Activity(db_load.LoadActivity.newActivityId());
		db_save.SaveActivity.newActivity(testActivity);
		activityList.add(testActivity);
		
		testActivity.setEnd(new Time(15, 15));
		db_save.SaveActivity.activityEnd(testActivity, new Time(15, 15));
		
		testActivity.setEnd(new Time(16, 20));
		db_save.SaveActivity.activityEnd(testActivity, new Time(16, 20));
	}
	
	@Test
	public void T1123getActivityEnd() {
		Activity testActivity = new Activity(db_load.LoadActivity.newActivityId());
		db_save.SaveActivity.newActivity(testActivity);
		activityList.add(testActivity);
		
		testActivity.setEnd(new Time(16, 20));
		db_save.SaveActivity.activityEnd(testActivity, new Time(16, 20));
		
		String endTime = testActivity.getEnd().toString(); 
	}
	
	@Test
	public void T1131addActivityToTask() {
		Activity testActivity = new Activity(db_load.LoadActivity.newActivityId());
		db_save.SaveActivity.newActivity(testActivity);
		activityList.add(testActivity);
		
		testTask.addActivity(testActivity);
		db_save.SaveTask.taskActivity(testTask, testActivity);
	}
	
	@Test
	public void T1132addActivitiesToTask() {
	
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
	}
}
