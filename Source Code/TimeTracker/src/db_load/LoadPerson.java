package db_load;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Activity;
import models.Person;
import models.PersonList;
import models.Project;
import models.Task;
import models.TaskGroup;

/* Classname: LoadPerson
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

public class LoadPerson {

	public static PersonList everythingFromPerson(){
		
		List<Person> personListFiles = db_load.LoadPerson.allPersons();
		PersonList personList = new PersonList(); 
		
		for (Person p : personListFiles) {

			p.setFirstName(db_load.LoadPerson.personFirstname(p));
			p.setLastname(db_load.LoadPerson.personLastname(p));
			
			personList.addPerson(p);
			
		}
		
		return personList; 
	}

	public static List<Person> allPersons() {

		List<Person> personList = new ArrayList<>();

		int personId;
		PreparedStatement stmtSelectPersons = null;
		String querySelectPersons = "SELECT id FROM users";

		ResultSet rs = null;

		Connection connection = null;
		try {
			connection = db_connection.Database.getConnection();
			stmtSelectPersons = connection.prepareStatement(querySelectPersons);
			rs = stmtSelectPersons.executeQuery();

			while (rs.next()) {
				personId = rs.getInt(1);
				personList.add(new Person(personId));
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
			if (stmtSelectPersons != null) {
				try {
					stmtSelectPersons.close();
				} catch (SQLException e) {
					;
				}
				stmtSelectPersons = null;
			}
		}
		return personList;
	}
	
	public static int newPersonId() {

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

	public static String personFirstname(Person p) {

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

	public static String personLastname(Person p) {

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

	public static List<Project> personProjects(Person p) {

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

	public static List<TaskGroup> personTaskGroups(Person p) {

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

	public static List<Task> personTasks(Person p) {
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

	public static List<Activity> personActivities(Person p) {
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
