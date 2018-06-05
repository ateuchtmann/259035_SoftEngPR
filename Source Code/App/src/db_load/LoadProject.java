package db_load;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import files.Person;
import files.Project;
import files.TaskGroup;

public class LoadProject {

	// ******************************************************************
	// ProjectList

	public List<Project> allProjects() {

		List<Project> projectList = new ArrayList<>();

		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
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
				projectList.add(new Project(projectId));
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
		return projectList;
	}

	// ******************************************************************
	// Project

	public int newProjectId() {

		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int id = 0;

		PreparedStatement stmtID = null;

		String queryID = "SELECT MAX(id) FROM projects";
		ResultSet rs = null;

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(url, username, password);

			stmtID = connection.prepareStatement(queryID);

			rs = stmtID.executeQuery();

			while (rs.next()) {
				id = rs.getInt(1);
			}

			id++;

			return id;
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

		}
		return id;
	}

	public String projectName(Project p) {
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int id = p.getId();

		String name = "";
		PreparedStatement stmtSelectName = null;
		String querySelectName = "SELECT name FROM projects WHERE id = '" + id + "'";

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

	public String projectDescription(Project p) {

		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int id = p.getId();
		String description = "";
		PreparedStatement stmtSelectDescription = null;
		String querySelectDescription = "SELECT description FROM projects WHERE id = '" + id + "'";

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

	public List<TaskGroup> projectTaskGroups(Project p) {

		List<TaskGroup> list = new ArrayList<>();

		int id = p.getId();
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		PreparedStatement stmtSelectID = null;
		String querySelectID = "SELECT ID FROM taskgroup WHERE id_project = '" + id + "'";

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

	public List<Person> projectPersons(Project p) {
		List<Person> list = new ArrayList<>();

		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int id = p.getId();

		PreparedStatement stmtSelectID = null;
		String querySelectID = "SELECT id_user FROM user_project WHERE id_project = '" + id + "'";

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