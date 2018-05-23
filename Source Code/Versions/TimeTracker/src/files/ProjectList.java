package files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/* Classname: ProjectList
*
* Programmers/Authors: 
* 
*  1.Milos Tomic
*  2.Maja Dusanic 
*  3.Alexander Teuchtmann 
*  4.Andrea Aistleithner 
*  5.Christopher Huber 
* 
*  Date: 22.05.2018
*  Version: 1.0.20
*
* Copyright notice
* - Programm is being build by the above mentioned programmers
* 
* Purpose of program: 
* - Time scheduling of projects, tasks etc.
*/

public class ProjectList {
	
	private List<Project> projectList = new ArrayList<>();
	
	public ProjectList(){
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20";
		String username = "u48005db20";
		String password = "prse2018";

		int projectId;
		PreparedStatement stmtSelectProjects = null;
		String querySelectProjects = "SELECT id FROM projects";

		ResultSet rs = null;

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			stmtSelectProjects = connection.prepareStatement(querySelectProjects);
			rs = stmtSelectProjects.executeQuery();

			while (rs.next()) {
				projectId = rs.getInt(1);
				this.projectList.add(new Project(projectId));
			}

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();

		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					;
				}
				connection = null;
			}
			if (stmtSelectProjects != null) {
				try {
					stmtSelectProjects.close();
				} catch (SQLException e) {
					;
				}
				stmtSelectProjects = null;
			}
		}
	}
	
	public void addProject(Project p) {
		this.projectList.add(p);
	}
	
	public List getProjectList() {
		return this.projectList;
	}
	

}
