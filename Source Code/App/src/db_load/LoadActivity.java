package db_load;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Activity;
import models.Person;
import models.Time;

public class LoadActivity {

	public int newActivityId() {

		int id = 0;

		PreparedStatement stmtID = null;

		String queryID = "SELECT MAX(id) FROM activity";
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

	public Time activityStart(Activity a) {

		int id = a.getId();
		int hour = 0;
		int minute = 0;

		PreparedStatement stmtSelectStart = null;

		String querySelectStart = "SELECT startHour, startMinute FROM activity  WHERE id = '" + id + "'";
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
		Time start = new Time(hour, minute);
		return start;
	}

	public Time activityEnd(Activity a) {

		int id = a.getId();
		int hour = 0;
		int minute = 0;

		PreparedStatement stmtSelectStart = null;

		String querySelectStart = "SELECT endHour, endMinute FROM activity WHERE id = '" + id + "'";
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
		Time end = new Time(hour, minute);
		return end;
	}

	public String activityDescription(Activity a) {

		int id = a.getId();
		String description = "";
		PreparedStatement stmtSelectDescription = null;
		String querySelectDescription = "SELECT description FROM activity WHERE id = '" + id + "'";

		ResultSet rs = null;

		Connection connection = null;
		try {
			connection = db_connection.Database.getConnection();
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

	public Person activityPerson(Activity a) {

		int id = a.getId();
		int person = 0;
		Person user = null;

		ResultSet rs = null;

		PreparedStatement stmtSelectUser = null;
		String querySelectUser = "SELECT id_user FROM activity WHERE id = '" + id + "' ";
		Connection connection = null;
		try {
			connection = db_connection.Database.getConnection();

			stmtSelectUser = connection.prepareStatement(querySelectUser);
			rs = stmtSelectUser.executeQuery();

			while (rs.next()) {
				person = rs.getInt(1);
			}

			user = new Person(person);

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
			if (stmtSelectUser != null) {
				try {
					stmtSelectUser.close();
				} catch (SQLException e) {
					;
				}
				stmtSelectUser = null;
			}
		}
		return user;
	}
}
