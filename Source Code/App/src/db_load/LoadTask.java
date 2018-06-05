package db_load;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import files.Activity;
import files.Person;
import files.Task;
import files.Time;

public class LoadTask {

	public int newTaskId() {

		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int id = 0;

		PreparedStatement stmtID = null;

		String queryID = "SELECT MAX(id) FROM tasks";
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

	public Time taskPlanTime(Task t) {
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int id = t.getId();
		int hour = 0;
		int minute = 0;

		PreparedStatement stmtSelectStart = null;

		String querySelectStart = "SELECT planHour, planMinute FROM tasks WHERE id = '" + id + "'";
		ResultSet rs = null;

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(url, username, password);

			stmtSelectStart = connection.prepareStatement(querySelectStart);
			rs = stmtSelectStart.executeQuery();

			while (rs.next()) {
				hour = rs.getInt(1);
				minute = rs.getInt(2);
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
			if (stmtSelectStart != null) {
				try {
					stmtSelectStart.close();
				} catch (SQLException e) {
					;
				}
			}
		}
		Time plan = new Time(hour, minute);
		return plan;
	}

	public String taskName(Task t) {
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int id = t.getId();
		String name = "";
		PreparedStatement stmtSelectName = null;
		String querySelectName = "SELECT name FROM tasks WHERE id = '" + id + "'";

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

	public List<Person> taskPersons(Task t) {

		List<Person> list = new ArrayList<>();

		int id = t.getId();

		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		PreparedStatement stmtSelectID = null;
		String querySelectID = "SELECT id_user FROM user_task WHERE id_task = '" + id + "'";

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

	public List<Activity> taskActivities(Task t) {
		
		List<Activity> list = new ArrayList<>();

		int id = t.getId();	

		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		PreparedStatement stmtSelectID = null;
		String querySelectID = "SELECT id FROM activity WHERE id_task = '" + id + "'";

		ResultSet rs = null;

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			stmtSelectID = connection.prepareStatement(querySelectID);
			rs = stmtSelectID.executeQuery();

			while (rs.next()) {
				list.add(new Activity(rs.getInt(1)));
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