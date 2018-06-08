package db_delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Activity;
import models.Project;
import models.Task;
import models.TaskGroup;

public class Delete {

	public void deleteProject(Project p) {

		/*
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";
		*/
		
		String url =db_connection.Database.getUrl();
		String username = db_connection.Database.getUsername();
		String password = db_connection.Database.getPassword();

		int project_id = p.getId();
		int taskgroup_id = 0;
		int task_id = 0;

		ResultSet rsTaskGroup = null;
		ResultSet rsTask = null;

		Connection connection = null;

		PreparedStatement stmtSelectTaskGroupIds = null;
		String querySelectsTaskGroupIds = "SELECT id FROM taskgroup WHERE id_project ='" + project_id + "'";

		PreparedStatement stmtSelectTaskIds = null;
		String querySelectTaskIds = "SELECT id FROM tasks WHERE id_taskgroup ='" + taskgroup_id + "'";

		PreparedStatement stmtDeleteFromActivities = null;
		String queryDeleteFromActivities = "DELETE FROM activity WHERE id_task = '" + task_id + "'";

		PreparedStatement stmtDeleteFromTasks = null;
		String queryDeleteFromTasks = "DELETE FROM tasks WHERE id_taskgroup = '" + taskgroup_id + "'";

		PreparedStatement stmtDeleteFromTaskGroups = null;
		String queryDeleteFromTaskGroups = "DELETE FROM  taskgroup WHERE id_project = '" + project_id + "'";

		PreparedStatement stmtDeleteFromProjects = null;
		String queryDeleteFromProjects = "DELETE FROM  projects WHERE id = '" + project_id + "'";

		
		PreparedStatement stmtDeleteUserProject = null; 
		String queryDeleteUserProject = "DELETE FROM user_project WHERE id_project ='" + project_id + "'"; 
		
		PreparedStatement stmtDeleteUserTaskgroup = null; 
		String queryDeleteUserTaskGroup = "DELETE FROM user_taskgroup WHERE id_taskgroup ='" + taskgroup_id + "'"; 

		
		PreparedStatement stmtDeleteUserTask = null; 
		String queryDeleteUserTask = "DELETE FROM user_task WHERE id_task ='" + task_id + "'";
		
		try {

			connection = DriverManager.getConnection(url, username, password);

			stmtSelectTaskGroupIds = connection.prepareStatement(querySelectsTaskGroupIds);
			rsTaskGroup = stmtSelectTaskGroupIds.executeQuery();

			while (rsTaskGroup.next()) {

				taskgroup_id = rsTaskGroup.getInt(1);

				querySelectTaskIds = "SELECT id FROM tasks WHERE id_taskgroup ='" + taskgroup_id + "'";
				stmtSelectTaskIds = connection.prepareStatement(querySelectTaskIds);
				rsTask = stmtSelectTaskIds.executeQuery();

				while (rsTask.next()) {
					task_id = rsTask.getInt(1); 
					
					queryDeleteFromActivities = "DELETE FROM activity WHERE id_task = '" + task_id + "'";
					stmtDeleteFromActivities = connection.prepareStatement(queryDeleteFromActivities);
					stmtDeleteFromActivities.executeUpdate();
					
					queryDeleteUserTask = "DELETE FROM user_task WHERE id_task ='" + task_id + "'";
					stmtDeleteUserTask = connection.prepareStatement(queryDeleteUserTask); 
					stmtDeleteUserTask.executeUpdate();
				}
				
				queryDeleteFromTasks = "DELETE FROM tasks WHERE id_taskgroup = '" + taskgroup_id + "'";
				stmtDeleteFromTasks = connection.prepareStatement(queryDeleteFromTasks);
				stmtDeleteFromTasks.executeUpdate();
				
				queryDeleteUserTaskGroup = "DELETE FROM user_taskgroup WHERE id_taskgroup ='" + taskgroup_id + "'"; 
				stmtDeleteUserTaskgroup = connection.prepareStatement(queryDeleteUserTaskGroup); 
				stmtDeleteUserTaskgroup.executeUpdate();
			}

			stmtDeleteFromTaskGroups = connection.prepareStatement(queryDeleteFromTaskGroups);
			stmtDeleteFromTaskGroups.executeUpdate();

			stmtDeleteFromProjects = connection.prepareStatement(queryDeleteFromProjects);
			stmtDeleteFromProjects.executeUpdate();
			
			stmtDeleteUserProject = connection.prepareStatement(queryDeleteUserProject); 
			stmtDeleteUserProject.executeUpdate(); 

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
			if (stmtDeleteFromProjects != null) {
				try {
					stmtDeleteFromProjects.close();
				} catch (SQLException e) {
					;
				}
				stmtDeleteFromProjects = null;
			}

			if (stmtDeleteFromTaskGroups != null) {
				try {
					stmtDeleteFromTaskGroups.close();
				} catch (SQLException e) {
					;
				}
				stmtDeleteFromTaskGroups = null;
			}

			if (stmtDeleteFromTasks != null) {
				try {
					stmtDeleteFromTasks.close();
				} catch (SQLException e) {
					;
				}
				stmtDeleteFromTasks = null;
			}
			if (stmtDeleteFromActivities != null) {
				try {
					stmtDeleteFromActivities.close();
				} catch (SQLException e) {
					;
				}
				stmtDeleteFromActivities = null;
			}
			if (stmtDeleteUserTask != null) {
				try {
					stmtDeleteUserTask.close();
				} catch (SQLException e) {
					;
				}
				stmtDeleteUserTask = null;
			}
			if (stmtDeleteUserTaskgroup != null) {
				try {
					stmtDeleteUserTaskgroup.close();
				} catch (SQLException e) {
					;
				}
				stmtDeleteUserTaskgroup = null;
			}
			if (stmtDeleteUserProject != null) {
				try {
					stmtDeleteUserProject.close();
				} catch (SQLException e) {
					;
				}
				stmtDeleteUserProject = null;
			}
		}
	}

	public void deleteTaskGroup(TaskGroup a) {

		/*
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";
		*/
		
		String url =db_connection.Database.getUrl();
		String username = db_connection.Database.getUsername();
		String password = db_connection.Database.getPassword();

		int taskgroup_id = a.getId();
		int task_id = 0;
		
		System.out.println(taskgroup_id); 

		ResultSet rsTask = null;

		Connection connection = null;

		PreparedStatement stmtSelectTaskIds = null;
		String querySelectTaskIds = "SELECT id FROM tasks WHERE id_taskgroup ='" + taskgroup_id + "'";

		PreparedStatement stmtDeleteFromActivities = null;
		String queryDeleteFromActivities = "DELETE FROM activity WHERE id_task = '" + task_id + "'";

		PreparedStatement stmtDeleteFromTasks = null;
		String queryDeleteFromTasks = "DELETE FROM tasks WHERE id_taskgroup = '" + taskgroup_id + "'";

		PreparedStatement stmtDeleteFromTaskGroups = null;
		String queryDeleteFromTaskGroups = "DELETE FROM  taskgroup WHERE id = '" + taskgroup_id + "'";

		PreparedStatement stmtDeleteUserTaskgroup = null; 
		String queryDeleteUserTaskGroup = "DELETE FROM user_taskgroup WHERE id_taskgroup ='" + taskgroup_id + "'"; 
		
		PreparedStatement stmtDeleteUserTask = null; 
		String queryDeleteUserTask = "DELETE FROM user_task WHERE id_task ='" + task_id + "'";
		
		try {

			connection = DriverManager.getConnection(url, username, password);

			querySelectTaskIds = "SELECT id FROM tasks WHERE id_taskgroup ='" + taskgroup_id + "'";
			stmtSelectTaskIds = connection.prepareStatement(querySelectTaskIds);
			rsTask = stmtSelectTaskIds.executeQuery();

			while (rsTask.next()) {
				
				task_id = rsTask.getInt(1);
				
				queryDeleteFromActivities = "DELETE FROM activity WHERE id_task = '" + task_id + "'";
				stmtDeleteFromActivities = connection.prepareStatement(queryDeleteFromActivities);
				stmtDeleteFromActivities.executeUpdate();
				
				queryDeleteUserTask = "DELETE FROM user_task WHERE id_task ='" + task_id + "'";
				stmtDeleteUserTask = connection.prepareStatement(queryDeleteUserTask); 
				stmtDeleteUserTask.executeUpdate();
			}

			stmtDeleteFromTasks = connection.prepareStatement(queryDeleteFromTasks);
			stmtDeleteFromTasks.executeUpdate();

			
			System.out.println(taskgroup_id);
			queryDeleteFromTaskGroups = "DELETE FROM  taskgroup WHERE id = '" + taskgroup_id + "'";
			stmtDeleteFromTaskGroups = connection.prepareStatement(queryDeleteFromTaskGroups);
			stmtDeleteFromTaskGroups.executeUpdate();
			
			stmtDeleteUserTaskgroup = connection.prepareStatement(queryDeleteUserTaskGroup); 
			stmtDeleteUserTaskgroup.executeUpdate();
			
			
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
			if (stmtDeleteFromTaskGroups != null) {
				try {
					stmtDeleteFromTaskGroups.close();
				} catch (SQLException e) {
					;
				}
				stmtDeleteFromTaskGroups = null;
			}

			if (stmtDeleteFromTasks != null) {
				try {
					stmtDeleteFromTasks.close();
				} catch (SQLException e) {
					;
				}
				stmtDeleteFromTasks = null;
			}
			if (stmtDeleteFromActivities != null) {
				try {
					stmtDeleteFromActivities.close();
				} catch (SQLException e) {
					;
				}
				stmtDeleteFromActivities = null;
			}
			if (stmtDeleteUserTask != null) {
				try {
					stmtDeleteUserTask.close();
				} catch (SQLException e) {
					;
				}
				stmtDeleteUserTask = null;
			}
			if (stmtDeleteUserTaskgroup != null) {
				try {
					stmtDeleteUserTaskgroup.close();
				} catch (SQLException e) {
					;
				}
				stmtDeleteUserTaskgroup = null;
			}
		}
	}

	public void deleteTask(Task a) {
		/*
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";
		*/
		
		String url =db_connection.Database.getUrl();
		String username = db_connection.Database.getUsername();
		String password = db_connection.Database.getPassword();

		int task_id = a.getId();

		Connection connection = null;

		PreparedStatement stmtDeleteFromActivities = null;
		String queryDeleteFromActivities = "DELETE FROM activity WHERE id_task = '" + task_id + "'";

		PreparedStatement stmtDeleteFromTasks = null;
		String queryDeleteFromTasks = "DELETE FROM tasks WHERE id = '" + task_id + "'";

		PreparedStatement stmtDeleteUserTask = null; 
		String queryDeleteUserTask = "DELETE FROM user_task WHERE id_task ='" + task_id + "'";
		
		
		try {

			connection = DriverManager.getConnection(url, username, password);

			stmtDeleteFromActivities = connection.prepareStatement(queryDeleteFromActivities);
			stmtDeleteFromActivities.executeUpdate();

			stmtDeleteFromTasks = connection.prepareStatement(queryDeleteFromTasks);
			stmtDeleteFromTasks.executeUpdate(); 
			
			stmtDeleteUserTask = connection.prepareStatement(queryDeleteUserTask); 
			stmtDeleteUserTask.executeUpdate();

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
			if (stmtDeleteFromTasks != null) {
				try {
					stmtDeleteFromTasks.close();
				} catch (SQLException e) {
					;
				}
				stmtDeleteFromTasks = null;
			}
			if (stmtDeleteFromActivities != null) {
				try {
					stmtDeleteFromActivities.close();
				} catch (SQLException e) {
					;
				}
				stmtDeleteFromActivities = null;
			}
			if (stmtDeleteUserTask != null) {
				try {
					stmtDeleteUserTask.close();
				} catch (SQLException e) {
					;
				}
				stmtDeleteUserTask = null;
			}
		}
	}

	public void deleteActivity(Activity a) {
		/*
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";
		*/
		
		String url =db_connection.Database.getUrl();
		String username = db_connection.Database.getUsername();
		String password = db_connection.Database.getPassword();

		int activity_id = a.getId(); 

		Connection connection = null;

		PreparedStatement stmtDeleteFromActivities = null;
		String queryDeleteFromActivities = "DELETE FROM activity WHERE id = '" + activity_id + "'";


		try {

			connection = DriverManager.getConnection(url, username, password);

			stmtDeleteFromActivities = connection.prepareStatement(queryDeleteFromActivities);
			stmtDeleteFromActivities.executeUpdate();


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
			
			if (stmtDeleteFromActivities != null) {
				try {
					stmtDeleteFromActivities.close();
				} catch (SQLException e) {
					;
				}
				stmtDeleteFromActivities = null;
			}
		}
	}

}
