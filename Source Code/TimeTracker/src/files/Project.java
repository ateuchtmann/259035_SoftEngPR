package files;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* Classname: Project
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

public class Project {
	
	private int id;

	
	public Project() {
		
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20";
		String username = "u48005db20";
		String password = "prse2018";

		this.id = 0;

		PreparedStatement stmtID = null;

		String queryID = "SELECT MAX(id) FROM projects";
		ResultSet rs = null;

		String queryInsertProjekt = "INSERT INTO projects (id, name, description) VALUES (?, ?, ?)";
		PreparedStatement insertProjekt = null;

		String name = "";
		String description = "";

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(url, username, password);

			stmtID = connection.prepareStatement(queryID);

			rs = stmtID.executeQuery();

			while (rs.next()) {
				this.id = rs.getInt(1);
			}

			this.id++;

			insertProjekt = connection.prepareStatement(queryInsertProjekt);

			insertProjekt.setInt(1, this.id);
			insertProjekt.setString(2, name);
			insertProjekt.setString(3, description);

			insertProjekt.executeUpdate();

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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					;
				}
				rs = null;
			}

			if (stmtID != null) {
				try {
					stmtID.close();
				} catch (SQLException e) {
					;
				}
				stmtID = null;
			}

			if (insertProjekt != null) {
				try {
					insertProjekt.close();
				} catch (SQLException e) {
					;
				}
				insertProjekt = null;
			}
		}
	}
	
	public Project(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
    public void setName(String name) {
    	
    	String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20";
		String username = "u48005db20";
		String password = "prse2018";

		PreparedStatement stmtUpdateName = null;
		String queryUpdateName = "UPDATE projects SET Name = '" + name + "' WHERE id = '" + this.id + "'";

		Connection connection = null;

		try {
			connection = DriverManager.getConnection(url, username, password);
			stmtUpdateName = connection.prepareStatement(queryUpdateName);

			stmtUpdateName.executeUpdate();

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
			if (stmtUpdateName != null) {
				try {
					stmtUpdateName.close();
				} catch (SQLException e) {
					;
				}
				stmtUpdateName = null;
			}
		}
    	
    }
	
    public void setDescr(String description) {
    	
    	String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20";
		String username = "u48005db20";
		String password = "prse2018";

		PreparedStatement stmtUpdateDescription = null;
		String queryUpdateDescription = "UPDATE projects SET description = '" + description + "' WHERE id = '" + this.id
				+ "'";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			stmtUpdateDescription = connection.prepareStatement(queryUpdateDescription);

			stmtUpdateDescription.executeUpdate();

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
			if (stmtUpdateDescription != null) {
				try {
					stmtUpdateDescription.close();
				} catch (SQLException e) {
					;
				}
				stmtUpdateDescription = null;
			}
		}
    }
	
    public String getName() {
		
    	String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20";
		String username = "u48005db20";
		String password = "prse2018";

		String name = "";
		PreparedStatement stmtSelectName = null;
		String querySelectName = "SELECT name FROM projects WHERE id = '" + this.id + "'";

		ResultSet rs = null;

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			stmtSelectName = connection.prepareStatement(querySelectName);
			rs = stmtSelectName.executeQuery();

			while (rs.next()) {
				name = rs.getString(1);
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
			if (stmtSelectName != null) {
				try {
					stmtSelectName.close();
				} catch (SQLException e) {
					;
				}
				stmtSelectName = null;
			}
		}
		return name;
    }
	
    public void addTaskGroup(TaskGroup a) {

    	String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20";
		String username = "u48005db20";
		String password = "prse2018";

		int a_id = a.getId();
		int p_id = this.id;

		PreparedStatement stmtUpdateProjectId = null;
		String queryUpdateProjectId = "UPDATE taskgroup SET id_project = '" + p_id + "' WHERE taskgroup.id = '" + a_id
				+ "'";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);

			stmtUpdateProjectId = connection.prepareStatement(queryUpdateProjectId);
			stmtUpdateProjectId.executeUpdate();

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
			if (stmtUpdateProjectId != null) {
				try {
					stmtUpdateProjectId.close();
				} catch (SQLException e) {
					;
				}
				stmtUpdateProjectId = null;
			}
		}
    }
    
    public void addPerson(Person p) {
    	
    	String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20";
		String username = "u48005db20";
		String password = "prse2018";

		int id = 0;
		int person_id = p.getId();
		int project_id = this.id;

		PreparedStatement stmtID = null;
		String queryID = "SELECT MAX(id) FROM user_project";
		ResultSet rs = null;

		PreparedStatement stmtInsertUserProject = null;
		String queryInsertUserProject = "INSERT INTO user_project (id, id_user, id_project) VALUES (?, ?, ?)";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);

			stmtID = connection.prepareStatement(queryID);
			rs = stmtID.executeQuery();

			while (rs.next()) {
				id = rs.getInt(1);
			}

			id++;
			stmtInsertUserProject = connection.prepareStatement(queryInsertUserProject);

			stmtInsertUserProject.setInt(1, id);
			stmtInsertUserProject.setInt(2, person_id);
			stmtInsertUserProject.setInt(3, project_id);

			stmtInsertUserProject.executeUpdate();

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
			if (stmtInsertUserProject != null) {
				try {
					stmtInsertUserProject.close();
				} catch (SQLException e) {
					;
				}
				stmtInsertUserProject = null;
			}
		}
    }
    
	public String getDescr() {
		
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20";
		String username = "u48005db20";
		String password = "prse2018";

		String description = "";
		PreparedStatement stmtSelectDescription = null;
		String querySelectDescription = "SELECT description FROM projects WHERE id = '" + this.id + "'";

		ResultSet rs = null;

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			stmtSelectDescription = connection.prepareStatement(querySelectDescription);
			rs = stmtSelectDescription.executeQuery();

			while (rs.next()) {
				description = rs.getString(1);
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
			if (stmtSelectDescription != null) {
				try {
					stmtSelectDescription.close();
				} catch (SQLException e) {
					;
				}
				stmtSelectDescription = null;
			}
		}
		return description;
	}
	
	public List getTsks() {
		List<TaskGroup> list = new ArrayList<>();

		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20";
		String username = "u48005db20";
		String password = "prse2018";

		PreparedStatement stmtSelectID = null;
		String querySelectID = "SELECT ID FROM taskgroup WHERE id_project = '" + this.id + "'";

		ResultSet rs = null;

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			stmtSelectID = connection.prepareStatement(querySelectID);
			rs = stmtSelectID.executeQuery();

			while (rs.next()) {
				list.add(new TaskGroup(rs.getInt(1)));
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
			if (stmtSelectID != null) {
				try {
					stmtSelectID.close();
				} catch (SQLException e) {
					;
				}
				stmtSelectID = null;
			}
		}
		return list;
	}
	
    public List getPersonList() {
    	
    	List<Person> list = new ArrayList<>();

		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20";
		String username = "u48005db20";
		String password = "prse2018";

		PreparedStatement stmtSelectID = null;
		String querySelectID = "SELECT id_user FROM user_project WHERE id_project = '" + this.id + "'";

		ResultSet rs = null;

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			stmtSelectID = connection.prepareStatement(querySelectID);
			rs = stmtSelectID.executeQuery();

			while (rs.next()) {
				list.add(new Person(rs.getInt(1)));
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
			if (stmtSelectID != null) {
				try {
					stmtSelectID.close();
				} catch (SQLException e) {
					;
				}
				stmtSelectID = null;
			}
		}
		return list;
    }
	
}
