package db_save;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Person;
import models.Task;
import models.TaskGroup;

public class SaveTaskGroup {
	
	public static void newTaskGroup(TaskGroup a) {

		int id = a.getId();

		PreparedStatement stmtID = null;

		String queryID = "SELECT MAX(id) FROM taskgroup";
		ResultSet rs = null;

		String queryInsertTaskGroup = "INSERT INTO taskgroup (id, id_project, name) VALUES ( ?, ?, ?)";
		PreparedStatement insertTaskgroup = null;

		String name = "";

		Connection connection = null;

		try {
			connection = db_connection.Database.getConnection();

			stmtID = connection.prepareStatement(queryID);

			rs = stmtID.executeQuery();

			while (rs.next()) {
				id = rs.getInt(1);
			}

			id++;

			insertTaskgroup = connection.prepareStatement(queryInsertTaskGroup);

			insertTaskgroup.setInt(1, id);
			insertTaskgroup.setInt(2, 0);
			insertTaskgroup.setString(3, name);

			insertTaskgroup.executeUpdate();

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

			if (insertTaskgroup != null) {
				try {
					insertTaskgroup.close();
				} catch (SQLException e) {
					;
				}

				insertTaskgroup = null;
			}
		}
	}

	public static void taskGroupName(TaskGroup a, String name) {

		int id = a.getId();

		PreparedStatement stmtUpdateName = null;
		String queryUpdateName = "UPDATE taskgroup SET Name = '" + name + "' WHERE id = '" + id + "'";

		Connection connection = null;
		try {
			connection = db_connection.Database.getConnection();
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

	public static void taskGroupTask(TaskGroup a, Task t) {

		int task_id = t.getId();
		int tg_id = a.getId();

		PreparedStatement stmtUpdateTaskgroupId = null;
		String queryUpdateTaskgroupId = "UPDATE tasks SET id_taskgroup = '" + tg_id + "' WHERE tasks.id = '" + task_id
				+ "'";
		Connection connection = null;
		try {
			connection = db_connection.Database.getConnection();

			stmtUpdateTaskgroupId = connection.prepareStatement(queryUpdateTaskgroupId);
			stmtUpdateTaskgroupId.executeUpdate();

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
			if (stmtUpdateTaskgroupId != null) {
				try {
					stmtUpdateTaskgroupId.close();
				} catch (SQLException e) {
					;
				}
				stmtUpdateTaskgroupId = null;
			}
		}

	}

	public static void taskGroupPerson(TaskGroup a, Person p) {

		int id = 0;
		int person_id = p.getId();
		int tg_id = a.getId();

		PreparedStatement stmtID = null;
		String queryID = "SELECT MAX(id) FROM user_taskgroup";
		ResultSet rs = null;

		PreparedStatement stmtInsertUserProject = null;
		String queryInsertUserProject = "INSERT INTO user_taskgroup (id, id_user, id_taskgroup) VALUES (?, ?, ?)";
		Connection connection = null;
		try {
			connection = db_connection.Database.getConnection();

			stmtID = connection.prepareStatement(queryID);
			rs = stmtID.executeQuery();

			while (rs.next()) {
				id = rs.getInt(1);
			}

			id++;
			stmtInsertUserProject = connection.prepareStatement(queryInsertUserProject);

			stmtInsertUserProject.setInt(1, id);
			stmtInsertUserProject.setInt(2, person_id);
			stmtInsertUserProject.setInt(3, tg_id);

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
