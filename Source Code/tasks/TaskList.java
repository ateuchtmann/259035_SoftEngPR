package tasks;

public class TaskList {
	
	// ---------------------------------- Definition of variables
	
	private Task[] taskList;
	private static int MAX_TASKS = 500;
	private int totalTasks = 1;
	
	// ---------------------------------- Definition of constructors
	
	public TaskList() {
		
		taskList = new Task[MAX_TASKS];
	
	}
	
	// ---------------------------------- Definition of methods
	
	// ---------------- Method createTask
	
	public void createTask(String taskName) {
		
		if (totalTasks <= MAX_TASKS) {
			
			taskList[totalTasks]= new Task(taskName);
			System.out.println("Die Aufgabe mit der Nummer (#"+totalTasks+") wurde erfolgreich angelegt.");
			totalTasks++;
			
		} else {
			
			System.out.println("Es wurden bereits zuviel Aufgaben angelegt.");
			
		}
		
	}
	
	// ---------------- Method addDesc
	
	public boolean addDesc(int taskNo, String taskName) {
		
		for (int acNo = 0; acNo<MAX_TASKS; acNo++) {
			
			if (acNo == taskNo) {
				
				taskList[acNo].addDesc(taskName);
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	// ---------------- Method addAllocTime
	
	public boolean addAllocTime(int taskNo, int allocTime) {
		
		for (int acNo = 0; acNo<MAX_TASKS; acNo++) {
			
			if (acNo == taskNo) {
				
				taskList[acNo].addAllocTime(allocTime);
				return true;
				
			}
			
		}
		
		return false;		
		
	}

}
