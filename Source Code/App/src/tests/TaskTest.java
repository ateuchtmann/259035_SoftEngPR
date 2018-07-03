package tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import models.Project;
import models.Task;
import models.TaskGroup;
import models.Time;

public class TaskTest {
		
	Project testProject;
	TaskGroup testTaskGroup;
	List<Task> taskList = new ArrayList<>();

	@Before
	public void setUp() {
		testProject = new Project(db_load.LoadProject.newProjectId());
		testTaskGroup = new TaskGroup(db_load.LoadTaskGroup.newTaskGroupId());
	}

	@After
	public void tearDown() {
		
		db_delete.Delete.deleteProject(testProject);
		db_delete.Delete.deleteTaskGroup(testTaskGroup);
		
		for (Task t : taskList){
			db_delete.Delete.deleteTask(t);
		}
		
		testProject = null;
		testTaskGroup = null;
	}

	@Test
	public void T151CreateTask() {
		Task testTask = new Task(db_load.LoadTask.newTaskId());
		db_save.SaveTask.newTask(testTask);
		taskList.add(testTask); 
	}
		
		@Test
		public void T152CreateTasks() {
			Task testTask1 = new Task(db_load.LoadTask.newTaskId());
			Task testTask2 = new Task(db_load.LoadTask.newTaskId());
			Task testTask3 = new Task(db_load.LoadTask.newTaskId());
			
			db_save.SaveTask.newTask(testTask1);
			db_save.SaveTask.newTask(testTask2);
			db_save.SaveTask.newTask(testTask3);
			
			taskList.add(testTask1);
			taskList.add(testTask2);
			taskList.add(testTask3);
			
		}
		
		@Test
		public void T161SetTaskName() {
			Task testTask = new Task(db_load.LoadTask.newTaskId());
			db_save.SaveTask.newTask(testTask);
			taskList.add(testTask); 
			
			testTask.setName("TestAufgabe");
			
			db_save.SaveTask.taskName(testTask, "TestAufgabe");
		}
		
		@Test
		public void T162RenameTaskName() {
			Task testTask = new Task(db_load.LoadTask.newTaskId());
			db_save.SaveTask.newTask(testTask);
			taskList.add(testTask); 
			
			testTask.setName("TestAufgabe");
			db_save.SaveTask.taskName(testTask, "TestAufgabe");
			
			testTask.setName("Renamed TestAufgabe");
			db_save.SaveTask.taskName(testTask, "Renamed TestAufgabe");
		}
		
		@Test
		public void T171addTaskToTaskGroup() {
			Task testTask = new Task(db_load.LoadTask.newTaskId());
			db_save.SaveTask.newTask(testTask);
			taskList.add(testTask);
			
			testTaskGroup.addTask(testTask);
			db_save.SaveTaskGroup.taskGroupTask(testTaskGroup, testTask);

		}
		
		@Test
		public void T172addTasksToTaskGroup() {
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
		}
		
		
		@Test
		public void T181setPlanTime() {
			Task testTask = new Task(db_load.LoadTask.newTaskId());
			db_save.SaveTask.newTask(testTask);
			taskList.add(testTask);
			
			testTask.setPlanTime(new Time (15, 30)); 
			db_save.SaveTask.taskPlanTime(testTask, new Time (15, 30));
			
		}
		@Test
		public void T182changePlanTime() {
			Task testTask = new Task(db_load.LoadTask.newTaskId());
			db_save.SaveTask.newTask(testTask);
			taskList.add(testTask);
			
			testTask.setPlanTime(new Time (15, 30)); 
			db_save.SaveTask.taskPlanTime(testTask, new Time (15, 30));
			
			testTask.setPlanTime(new Time (20, 00)); 
			db_save.SaveTask.taskPlanTime(testTask, new Time (20, 00));
		}		
}
