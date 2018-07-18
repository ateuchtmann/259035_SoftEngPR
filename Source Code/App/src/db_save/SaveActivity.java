package db_save;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.Activity;
import models.Person;
import models.Time;

/* Classname: SaveActivity
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

public class SaveActivity {

	public static void newActivity (Activity a){

		int id = a.getId(); 

		String queryInsertActivity = "INSERT INTO activity (id, id_user, id_task, description, startHour, startMinute, endHour, endMinute, day, month, year) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement insertActivity = null;

		Connection connection = null;

		int startHour = 0;
		int startMinute = 0;
		int endHour = 0;
		int endMinute = 0;
		int day = 0;
		int month = 0;
		int year = 0;

		String description = "";
		int id_user = 0;
		int id_task = 0;

		try {

			connection = db_connection.Database.getConnection();

			insertActivity = connection.prepareStatement(queryInsertActivity);

			insertActivity.setInt(1, id);
			insertActivity.setInt(2, id_user);
			insertActivity.setInt(3, id_task);
			insertActivity.setString(4, description);
			insertActivity.setInt(5, startHour);
			insertActivity.setInt(6, startMinute);
			insertActivity.setInt(7, endHour);
			insertActivity.setInt(8, endMinute);
			insertActivity.setInt(9, day);
			insertActivity.setInt(10, month);
			insertActivity.setInt(11, year);	

			insertActivity.executeUpdate();

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
			if (insertActivity != null) {
				try {
					insertActivity.close();
				} catch (SQLException e) {
					;
				}
				insertActivity = null;
			}
		}
	}

	public static void activityPerson (Activity a, Person p){

		int id = a.getId(); 
		int person_id = p.getId();

		PreparedStatement stmtUpdateUser = null;
		String queryInsertUsertask = "Update activity SET id_user = '" + person_id + "' WHERE id = '" + id + "' ";
		Connection connection = null;
		try {
			connection = db_connection.Database.getConnection();

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

	public static void activityStart (Activity a, Time t){

		int id = a.getId(); 
		int startHour = t.getHour();
		int startMinute = t.getMin();

		PreparedStatement stmtUpdateStart = null;
		String queryUpdateName = "UPDATE activity SET startHour = '" + startHour + "', startMinute = '" + startMinute
				+ "' WHERE id = '" + id + "'";

		Connection connection = null;
		try {
			connection = db_connection.Database.getConnection();
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

	public static void activityEnd (Activity a, Time t){

		int id = a.getId(); 
		int endHour = t.getHour();
		int endMinute = t.getMin();

		PreparedStatement stmtUpdateStart = null;
		String queryUpdateName = "UPDATE activity SET endHour = '" + endHour + "', endMinute = '" + endMinute
				+ "' WHERE id = '" + id + "'";

		Connection connection = null;
		try {
			connection = db_connection.Database.getConnection();
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
	
	public static void activityDescription (Activity a, String description){
		
		int id = a.getId(); 

		PreparedStatement stmtUpdateDescription = null;
		String queryUpdateDescription = "UPDATE activity SET description = '" + description + "' WHERE id = '" + id
				+ "'";

		Connection connection = null;
		try {
			connection = db_connection.Database.getConnection();
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
	
	public static void activityDay (Activity a, int day){
		
		int id = a.getId(); 

		PreparedStatement stmtUpdateDay = null;
		String queryUpdateDay = "UPDATE activity SET day = '" + day + "' WHERE id = '" + id
				+ "'";

		Connection connection = null;
		try {
			connection = db_connection.Database.getConnection();
			stmtUpdateDay = connection.prepareStatement(queryUpdateDay);

			stmtUpdateDay.executeUpdate();

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
			if (stmtUpdateDay != null) {
				try {
					stmtUpdateDay.close();
				} catch (SQLException e) {
					;
				}
				stmtUpdateDay = null;
			}
		}
	}
	
	public static void activityMonth (Activity a, int month){
		
		int id = a.getId(); 

		PreparedStatement stmtUpdateMonth = null;
		String queryUpdateMonth = "UPDATE activity SET month = '" + month + "' WHERE id = '" + id
				+ "'";

		Connection connection = null;
		try {
			connection = db_connection.Database.getConnection();
			stmtUpdateMonth = connection.prepareStatement(queryUpdateMonth);

			stmtUpdateMonth.executeUpdate();

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
			if (stmtUpdateMonth != null) {
				try {
					stmtUpdateMonth.close();
				} catch (SQLException e) {
					;
				}
				stmtUpdateMonth = null;
			}
		}
	}
	
	public static void activityYear(Activity a, int year){
		
		int id = a.getId(); 

		PreparedStatement stmtUpdateYear = null;
		String queryUpdateYear = "UPDATE activity SET year = '" + year + "' WHERE id = '" + id
				+ "'";

		Connection connection = null;
		try {
			connection = db_connection.Database.getConnection();
			stmtUpdateYear = connection.prepareStatement(queryUpdateYear);

			stmtUpdateYear.executeUpdate();

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
			if (stmtUpdateYear != null) {
				try {
					stmtUpdateYear.close();
				} catch (SQLException e) {
					;
				}
				stmtUpdateYear = null;
			}
		}
		
	}
	
}
