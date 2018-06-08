package db_save;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Activity;
import models.Person;
import models.Project;
import models.Task;
import models.TaskGroup;

public class SavePerson {
	
	public void newPerson(Person p) {

		/*
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";
		*/
		
		String url =db_connection.Database.getUrl();
		String username = db_connection.Database.getUsername();
		String password = db_connection.Database.getPassword();

		int id = p.getId();

		String queryInsertUser = "INSERT INTO users (id, username, password, firstname, lastname) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement insertUser = null;

		String firstname = "";
		String lastname = "";

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(url, username, password);

			insertUser = connection.prepareStatement(queryInsertUser);

			insertUser.setInt(1, id);
			insertUser.setString(2, "");
			insertUser.setString(3, "");
			insertUser.setString(4, firstname);
			insertUser.setString(5, lastname);

			insertUser.executeUpdate();

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
			if (insertUser != null) {
				try {
					insertUser.close();
				} catch (SQLException e) {
					;
				}
				insertUser = null;
			}
		}
	}

	public void personFirstname(Person p, String firstname) {

		/*
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";
		*/
		
		String url =db_connection.Database.getUrl();
		String username = db_connection.Database.getUsername();
		String password = db_connection.Database.getPassword();

		int id = p.getId();

		PreparedStatement stmtUpdateFirstname = null;
		String queryUpdateFirstname = "UPDATE users SET firstname = '" + firstname + "' WHERE id = '" + id + "'";
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(url, username, password);
			stmtUpdateFirstname = connection.prepareStatement(queryUpdateFirstname);

			stmtUpdateFirstname.executeUpdate();

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
			if (stmtUpdateFirstname != null) {
				try {
					stmtUpdateFirstname.close();
				} catch (SQLException e) {
					;
				}
				stmtUpdateFirstname = null;
			}
		}
	}

	public void personLastname(Person p, String lastname) {

		/*
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";
		*/
		
		String url =db_connection.Database.getUrl();
		String username = db_connection.Database.getUsername();
		String password = db_connection.Database.getPassword();

		int id = p.getId();

		PreparedStatement stmtUpdateLastname = null;
		String queryUpdateLastname = "UPDATE users SET lastname = '" + lastname + "' WHERE id = '" + id + "'";
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(url, username, password);
			stmtUpdateLastname = connection.prepareStatement(queryUpdateLastname);

			stmtUpdateLastname.executeUpdate();

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
			if (stmtUpdateLastname != null) {
				try {
					stmtUpdateLastname.close();
				} catch (SQLException e) {
					;
				}
				stmtUpdateLastname = null;
			}
		}
	}

	public void personProject(Person p, Project pro) {

		/*
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";
		*/
		
		String url =db_connection.Database.getUrl();
		String username = db_connection.Database.getUsername();
		String password = db_connection.Database.getPassword();

		int id = 0;
		int person_id = p.getId();
		int project_id = pro.getId();

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

	public void personTaskGroup(Person p, TaskGroup a) {

		/*
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";
		*/
		
		String url =db_connection.Database.getUrl();
		String username = db_connection.Database.getUsername();
		String password = db_connection.Database.getPassword();

		int id = 0;
		int person_id = p.getId();
		int taskgroup_id = a.getId();

		PreparedStatement stmtID = null;
		String queryID = "SELECT MAX(id) FROM user_taskgroup";
		ResultSet rs = null;

		PreparedStatement stmtInsertUserProject = null;
		String queryInsertUserProject = "INSERT INTO user_taskgroup (id, id_user, id_taskgroup) VALUES (?, ?, ?)";
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
			stmtInsertUserProject.setInt(3, taskgroup_id);

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

	public void personTask(Person p, Task a) {

		/*
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";
		*/
		
		String url =db_connection.Database.getUrl();
		String username = db_connection.Database.getUsername();
		String password = db_connection.Database.getPassword();

		int id = 0;
		int person_id = p.getId();
		int task_id = a.getId();

		PreparedStatement stmtID = null;
		String queryID = "SELECT MAX(id) FROM user_task";
		ResultSet rs = null;

		PreparedStatement stmtInsertUserProject = null;
		String queryInsertUserProject = "INSERT INTO user_task (id, id_user, id_task) VALUES (?, ?, ?)";
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
			stmtInsertUserProject.setInt(3, task_id);

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

	public void personActivity(Person p, Activity a) {
		/*
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";
		*/
		
		String url =db_connection.Database.getUrl();
		String username = db_connection.Database.getUsername();
		String password = db_connection.Database.getPassword();

		int id = a.getId();
		int person_id = p.getId();

		PreparedStatement stmtUpdateUser = null;
		String queryInsertUsertask = "Update activity SET id_user = '" + person_id + "' WHERE id = '" + id + "' ";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);

			stmtUpdateUser = connection.prepareStatement(queryInsertUsertask);
			stmtUpdateUser.executeUpdate();

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
			if (stmtUpdateUser != null) {
				try {
					stmtUpdateUser.close();
				} catch (SQLException e) {
					;
				}
				stmtUpdateUser = null;
			}
		}
	}
}
