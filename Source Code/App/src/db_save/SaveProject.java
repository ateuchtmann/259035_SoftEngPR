package db_save;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import files.Person;
import files.Project;
import files.TaskGroup;

public class SaveProject {

	public void newProject(Project p) {

		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int id = 0;

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
				id = rs.getInt(1);
			}

			id++;

			insertProjekt = connection.prepareStatement(queryInsertProjekt);

			insertProjekt.setInt(1, id);
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

	public void projectName(Project p, String name) {

		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int id = p.getId();

		PreparedStatement stmtUpdateName = null;
		String queryUpdateName = "UPDATE projects SET Name = '" + name + "' WHERE id = '" + id + "'";

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

	public void projectDescription(Project p, String description) {

		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int id = p.getId();

		PreparedStatement stmtUpdateDescription = null;
		String queryUpdateDescription = "UPDATE projects SET description = '" + description + "' WHERE id = '" + id
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

	public void projectTaskGroup(Project p, TaskGroup a) {

		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int p_id = p.getId();
		int a_id = a.getId();

		PreparedStatement stmtUpdateProjectId = null;
		String queryUpdateProjectId = "UPDATE taskgroup SET id_project = '" + p_id + "' WHERE id = '" + a_id + "'";
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

	public void projectPerson(Project p, Person per) {

		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int id = 0;
		int project_id = p.getId();
		int person_id = per.getId();

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

}
