package files;

public class test {
	public static void main(String[] args) {
		
		ProjectList testlist = new ProjectList(); 
		
		for (int i=0; i < testlist.getProjectList().size(); i++){
			System.out.println(((Project) testlist.getProjectList().get(i)).getName());
		}
		
	}
}
