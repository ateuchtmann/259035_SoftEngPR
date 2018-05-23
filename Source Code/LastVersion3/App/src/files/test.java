package files;

public class test {
	public static void main(String[] args) {
		
		ProjectList testlist = new ProjectList(); 
		
		for (int i=0; i < testlist.getProjectList().size(); i++){
			System.out.println(((Project) testlist.getProjectList().get(i)).getName());
		}
		
		Task testTask = new Task (); 
		testTask.setPlanTime(new Time(15, 30));
		
		System.out.println(testTask.getPlanTime()); 
		
		Activity testact = new Activity(); 
		testact.setStart(new Time (8, 15));
		testact.setEnd(new Time (15, 30));
		
		System.out.println (testact.getStart()); 
		System.out.println (testact.getEnd());
		
	}
}
