package db_load;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Activity;
import models.Person;
import models.Task;
import models.Time;

/* Classname: LoadTask
*
* Programmers/Authors: 
* 
*  1.Milos Tomic
*  2.Maja Dusanic 
*  3.Alexander Teuchtmann 
*  4.Andrea Aistleithner 
*  5.Christopher Huber 
* 
*  Date: 04.07.2018
*  Version: 1.0.23
*
* Copyright notice
* - Programm is being build by the above mentioned programmers
* 
* Purpose of program: 
* - Time scheduling of projects, tasks etc.
*/


public class LoadTask {

	public static int newTaskId() {

		int id = 0;

		PreparedStatement stmtID = null;

		String queryID = "SELECT MAX(id) FROM tasks";
		ResultSet rs = null;

		Connection connection = null;

		try {

			connection = db_connection.Database.getConnection();

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

	public static Time taskPlanTime(Task t) {

		int id = t.getId();
		int hour = 0;
		int minute = 0;

		PreparedStatement stmtSelectStart = null;

		String querySelectStart = "SELECT planHour, planMinute FROM tasks WHERE id = '" + id + "'";
		ResultSet rs = null;

		Connection connection = null;

		try {

			connection = db_connection.Database.getConnection();

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

	public static String taskName(Task t) {

		int id = t.getId();
		String name = "";
		PreparedStatement stmtSelectName = null;
		String querySelectName = "SELECT name FROM tasks WHERE id = '" + id + "'";

		ResultSet rs = null;

		Connection connection = null;

		try {
			connection = db_connection.Database.getConnection();
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

	public static List<Person> taskPersons(Task t) {

		List<Person> list = new ArrayList<>();

		int id = t.getId();

		PreparedStatement stmtSelectID = null;
		String querySelectID = "SELECT id_user FROM user_task WHERE id_task = '" + id + "'";

		ResultSet rs = null;

		Connection connection = null;
		try {
			connection = db_connection.Database.getConnection();
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

	public static List<Activity> taskActivities(Task t) {
		
		List<Activity> list = new ArrayList<>();

		int id = t.getId();	

		PreparedStatement stmtSelectID = null;
		String querySelectID = "SELECT id FROM activity WHERE id_task = '" + id + "'";

		ResultSet rs = null;

		Connection connection = null;
		try {
			connection = db_connection.Database.getConnection();
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
