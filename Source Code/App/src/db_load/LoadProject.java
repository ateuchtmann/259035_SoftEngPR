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
import models.ProjectList;
import models.Task;
import models.TaskGroup;

public class LoadProject {
	// ******************************************************************
	// complete ProjectList
	
	public static ProjectList everythingFromProjects(){
		
		List<Project> projectListFiles = db_load.LoadProject.allProjects();
		ProjectList projectList = new ProjectList(); 
		
		for (Project p : projectListFiles) {

			p.setName(db_load.LoadProject.projectName(p));
			p.setDescription(db_load.LoadProject.projectDescription(p));
			
			projectList.addProject(p);

			List<Person> ProjectPersons = db_load.LoadProject.projectPersons(p);

			for (Person person : ProjectPersons) {
				
				person.setFirstName(db_load.LoadPerson.personFirstname(person));
				person.setLastname(db_load.LoadPerson.personLastname(person));
				
				p.addPerson(person);
			}

			List<TaskGroup> taskGroupList = db_load.LoadProject.projectTaskGroups(p);

			for (TaskGroup tg : taskGroupList) {

				tg.setName(db_load.LoadTaskGroup.taskGroupName(tg));

				List<Person> taskGroupPerson = db_load.LoadTaskGroup.taskGroupPersons(tg);

				for (Person person : taskGroupPerson) {
					
					person.setFirstName(db_load.LoadPerson.personFirstname(person));
					person.setLastname(db_load.LoadPerson.personLastname(person));
					
					tg.addPerson(person);
				}
				p.addTaskGroup(tg);

				List<Task> taskList = db_load.LoadTaskGroup.taskGroupTasks(tg);

				for (Task task : taskList) {

					task.setName(db_load.LoadTask.taskName(task));
					task.setPlanTime(db_load.LoadTask.taskPlanTime(task));

					List<Person> taskPerson = db_load.LoadTask.taskPersons(task);

					for (Person person : taskPerson) {
						
						person.setFirstName(db_load.LoadPerson.personFirstname(person));
						person.setLastname(db_load.LoadPerson.personLastname(person));
						
						task.addPerson(person);
					}

					tg.addTask(task);

					List<Activity> activityList =  db_load.LoadTask.taskActivities(task);
					
					for (Activity activity : activityList) {

						activity.setDescription(db_load.LoadActivity.activityDescription(activity));
			
						activity.setStart(db_load.LoadActivity.activityStart(activity));
						activity.setEnd(db_load.LoadActivity.activityEnd(activity));
						
						Person per = db_load.LoadActivity.activityPerson(activity); 
						per.setFirstName(db_load.LoadPerson.personFirstname(per));
						per.setLastname(db_load.LoadPerson.personLastname(per));
						
						activity.addPerson(per);
						
						task.addActivity(activity);
					}
				}	
			}
		}
		return projectList; 
	}
	
	
	// ******************************************************************
	// ProjectList

	public static List<Project> allProjects() {

		List<Project> projectList = new ArrayList<>();

		int projectId;
		PreparedStatement stmtSelectProjects = null;
		String querySelectProjects = "SELECT id FROM projects";

		ResultSet rs = null;

		Connection connection = null;
		try {
			connection = db_connection.Database.getConnection();
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

	public static int newProjectId() {

		int id = 0;

		PreparedStatement stmtID = null;

		String queryID = "SELECT MAX(id) FROM projects";
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

	public static String projectName(Project p) {

		int id = p.getId();

		String name = "";
		PreparedStatement stmtSelectName = null;
		String querySelectName = "SELECT name FROM projects WHERE id = '" + id + "'";

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

	public static String projectDescription(Project p) {

		int id = p.getId();
		String description = "";
		PreparedStatement stmtSelectDescription = null;
		String querySelectDescription = "SELECT description FROM projects WHERE id = '" + id + "'";

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

	public static List<TaskGroup> projectTaskGroups(Project p) {

		List<TaskGroup> list = new ArrayList<>();

		int id = p.getId();

		PreparedStatement stmtSelectID = null;
		String querySelectID = "SELECT ID FROM taskgroup WHERE id_project = '" + id + "'";

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

	public static List<Person> projectPersons(Project p) {
		List<Person> list = new ArrayList<>();

		int id = p.getId();

		PreparedStatement stmtSelectID = null;
		String querySelectID = "SELECT id_user FROM user_project WHERE id_project = '" + id + "'";

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

}
