package db_save;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import files.Activity;
import files.Person;
import files.Task;
import files.Time;

public class SaveTask {
	
	public void newTask (Task t){
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int id = t.getId();

		String queryInsertTask = "INSERT INTO tasks (id, id_taskgroup, name, planHour, planMinute) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement insertTask = null;

		String name = "";

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(url, username, password);


			insertTask = connection.prepareStatement(queryInsertTask);
			insertTask.setInt(1, id);
			insertTask.setInt(2, 0);
			insertTask.setString(3, name);
			insertTask.setInt(4, 0);
			insertTask.setInt(5, 0);

			insertTask.executeUpdate();

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


			if (insertTask != null) {
				try {
					insertTask.close();
				} catch (SQLException e) {
					;
				}
				insertTask = null;
			}
		}
	}

	public void taskName (Task t, String name){
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int id = t.getId();
		PreparedStatement stmtUpdateDescription = null;
		String queryUpdateDescription = "UPDATE tasks SET name = '" + name + "' WHERE id = '" + id + "'";
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

	public void taskPerson (Task t, Person p){
		
    	String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int id = 0;
		int person_id = p.getId();
		
		int t_id = t.getId(); 

		PreparedStatement stmtID = null;
		String queryID = "SELECT MAX(id) FROM user_task";
		ResultSet rs = null;

		PreparedStatement stmtInsertUserTask = null;
		String queryInsertUsertask = "INSERT INTO user_task (id, id_user, id_task) VALUES (?, ?, ?)";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);

			stmtID = connection.prepareStatement(queryID);
			rs = stmtID.executeQuery();

			while (rs.next()) {
				id = rs.getInt(1);
			}

			id++;
			stmtInsertUserTask = connection.prepareStatement(queryInsertUsertask);

			stmtInsertUserTask.setInt(1, id);
			stmtInsertUserTask.setInt(2, person_id);
			stmtInsertUserTask.setInt(3, t_id);

			stmtInsertUserTask.executeUpdate();

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
			if (stmtInsertUserTask != null) {
				try {
					stmtInsertUserTask.close();
				} catch (SQLException e) {
					;
				}
				stmtInsertUserTask = null;
			}
		}
	}

	public void taskPlanTime (Task t, Time planTime){
		
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int id = t.getId(); 
		int planHour = planTime.getHour();
		int planMinute = planTime.getMin();

		PreparedStatement stmtUpdateStart = null;
		String queryUpdateName = "UPDATE tasks SET planHour = '" + planHour + "', planMinute = '" + planMinute
				+ "' WHERE id = '" + id + "'";

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			stmtUpdateStart = connection.prepareStatement(queryUpdateName);

			stmtUpdateStart.executeUpdate();

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
			if (stmtUpdateStart != null) {
				try {
					stmtUpdateStart.close();
				} catch (SQLException e) {
					;
				}
				stmtUpdateStart = null;
			}
		}
	}

	public void taskActivity (Task t, Activity a){
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int id = t.getId();
		int activity_id = a.getId();

		PreparedStatement stmtUpdateTask = null;
		String queryUpdateTask = "Update activity SET id_task = '" + id + "' WHERE id = '" + activity_id + "' ";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);

			stmtUpdateTask = connection.prepareStatement(queryUpdateTask);
			stmtUpdateTask.executeUpdate();

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
			if (stmtUpdateTask != null) {
				try {
					stmtUpdateTask.close();
				} catch (SQLException e) {
					;
				}
				stmtUpdateTask = null;
			}
		}
	}

}
