package tasks;

public class Task {
	
	// ---------------------------------- Definition of variables
	
	private String taskName;
	private String taskDesc;
	private int allocTime;
	
	// ---------------------------------- Definition of constructors
	
	public Task(String taskName) {
		
		this.taskName = taskName;
		this.taskDesc = "";
		this.allocTime = 0;
		
	}
	
	// ---------------------------------- Definition of methods
	
	// ---------------- Method addDesc
	
	public void addDesc(String taskDesc) {
		
		this.taskDesc = taskDesc;

	}
	
	// ---------------- Method addAllocTime
	
	public void addAllocTime(int allocTime) {

		this.allocTime = allocTime;
		
	}	

}