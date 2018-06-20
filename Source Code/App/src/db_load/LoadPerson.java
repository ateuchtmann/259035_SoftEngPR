package db_load;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Activity;
import models.Person;
import models.Project;
import models.Task;
import models.TaskGroup;

public class LoadPerson {

	public int newPersonId() {

		int id = 0;

		PreparedStatement stmtID = null;

		String queryID = "SELECT MAX(id) FROM users";
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

	public String personFirstname(Person p) {

		int id = p.getId();
		String firstname = "";
		PreparedStatement stmtSelectFirstname = null;
		String querySelectFirstname = "SELECT firstname FROM users WHERE id = '" + id + "'";

		ResultSet rs = null;

		Connection connection = null;

		try {
			connection = db_connection.Database.getConnection();
			stmtSelectFirstname = connection.prepareStatement(querySelectFirstname);
			rs = stmtSelectFirstname.executeQuery();

			while (rs.next()) {
				firstname = rs.getString(1);
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
			if (stmtSelectFirstname != null) {
				try {
					stmtSelectFirstname.close();
				} catch (SQLException e) {
					;
				}
				stmtSelectFirstname = null;
			}
		}

		return firstname;
	}

	public String personLastname(Person p) {

		int id = p.getId();
		String lastname = "";
		PreparedStatement stmtSelectLastname = null;
		String querySelectLastname = "SELECT lastname FROM users WHERE id = '" + id + "'";

		ResultSet rs = null;

		Connection connection = null;

		try {
			connection = db_connection.Database.getConnection();
			stmtSelectLastname = connection.prepareStatement(querySelectLastname);
			rs = stmtSelectLastname.executeQuery();

			while (rs.next()) {
				lastname = rs.getString(1);
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
			if (stmtSelectLastname != null) {
				try {
					stmtSelectLastname.close();
				} catch (SQLException e) {
					;
				}
				stmtSelectLastname = null;
			}
		}
		return lastname;
	}

	public List<Project> personProjects(Person p) {

		List<Project> list = new ArrayList<>();

		int id = p.getId();

		PreparedStatement stmtSelectID = null;
		String querySelectID = "SELECT id_project FROM user_project WHERE id_user = '" + id + "'";

		ResultSet rs = null;

		Connection connection = null;
		try {
			connection = db_connection.Database.getConnection();
			stmtSelectID = connection.prepareStatement(querySelectID);
			rs = stmtSelectID.executeQuery();

			while (rs.next()) {
				list.add(new Project(rs.getInt(1)));
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

	public List<TaskGroup> personTaskGroups(Person p) {

		List<TaskGroup> list = new ArrayList<>();

		int id = p.getId();

		PreparedStatement stmtSelectID = null;
		String querySelectID = "SELECT id_taskgroup FROM user_taskgroup WHERE id_user = '" + id + "'";

		ResultSet rs = null;

		Connection connection = null;
		try {
			connection = db_connection.Database.getConnection();
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

	public List<Task> personTasks(Person p) {
		List<Task> list = new ArrayList<>();

		int id = p.getId();

		PreparedStatement stmtSelectID = null;
		String querySelectID = "SELECT id_task FROM user_task WHERE id_user = '" + id + "'";

		ResultSet rs = null;

		Connection connection = null;
		try {
			connection = db_connection.Database.getConnection();
			stmtSelectID = connection.prepareStatement(querySelectID);
			rs = stmtSelectID.executeQuery();

			while (rs.next()) {
				list.add(new Task(rs.getInt(1)));
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

	public List<Activity> personActivities(Person p) {
		List<Activity> list = new ArrayList<>();

		int id = p.getId();

		PreparedStatement stmtSelectID = null;
		String querySelectID = "SELECT id FROM activity WHERE id_user = '" + id + "'";

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
