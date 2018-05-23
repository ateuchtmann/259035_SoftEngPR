package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import files.Project;
import files.Task;
import files.TaskGroup;
import files.Time;

public class TaskTest {
		
	Project testProject;
	TaskGroup testTaskGroup;

	@Before
	public void setUp() {
		testProject = new Project();
		testTaskGroup = new TaskGroup();
	}

	@After
	public void tearDown() {
		testProject = null;
		testTaskGroup = null;
	}

	@Test
	public void T151CreateTask() {
		Task testTask = new Task();
	}
		
		@Test
		public void T152CreateTasks() {
			Task testTask1 = new Task();
			Task testTask2 = new Task();
			Task testTask3 = new Task();
		}
		
		@Test
		public void T161SetTaskName() {
			Task testTask = new Task();
			testTask.setName("TestAufgabe");
		}
		
		@Test
		public void T162RenameTaskName() {
			Task testTask = new Task();
			testTask.setName("TestAufgabe");
			testTask.setName("Renamed TestAufgabe");
		}
		
		@Test
		public void T171addTaskToTaskGroup() {
			Task testTask = new Task();
			testTaskGroup.addTsk(testTask);

		}
		
		@Test
		public void T172addTasksToTaskGroup() {
			Task testTask1 = new Task();
			Task testTask2 = new Task();
			Task testTask3 = new Task();
			
			testTaskGroup.addTsk(testTask1);
			testTaskGroup.addTsk(testTask2);
			testTaskGroup.addTsk(testTask3);
		}
		@Test
		public void T181setPlanTime() {
			Task testTask = new Task();
			testTask.setPlanTime(new Time (15, 30)); 
		}
		@Test
		public void T182changePlanTime() {
			Task testTask = new Task();
			testTask.setPlanTime(new Time (15, 30));
			testTask.setPlanTime(new Time (20, 00)); 
		}		
}
