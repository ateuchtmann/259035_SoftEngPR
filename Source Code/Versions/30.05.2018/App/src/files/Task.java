package files;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/* Classname: Task
*
* Programmers/Authors: 
* 
*  1.Milos Tomic
*  2.Maja Dusanic 
*  3.Alexander Teuchtmann 
*  4.Andrea Aistleithner 
*  5.Christopher Huber 
* 
*  Date: 22.05.2018
*  Version: 1.0.20
*
* Copyright notice
* - Programm is being build by the above mentioned programmers
* 
* Purpose of program: 
* - Time scheduling of projects, tasks etc.
*/

public class Task {
	
	private int id; 
	
	public Task(){
		
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		this.id = 0;

		PreparedStatement stmtID = null;

		String queryID = "SELECT MAX(id) FROM tasks";
		ResultSet rs = null;

		String queryInsertTask = "INSERT INTO tasks (id, id_taskgroup, name, planHour, planMinute) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement insertTask = null;

		String name = "";

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(url, username, password);

			stmtID = connection.prepareStatement(queryID);

			rs = stmtID.executeQuery();

			while (rs.next()) {
				this.id = rs.getInt(1);
			}

			this.id++;

			insertTask = connection.prepareStatement(queryInsertTask);

			insertTask.setInt(1, this.id);
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
	
	public Task(int id){
		this.id = id; 
	}

	public int getId() {
		return this.id;
	}
	
    public void setName(String name) {
    	
    	String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		PreparedStatement stmtUpdateDescription = null;
		String queryUpdateDescription = "UPDATE tasks SET name = '" + name + "' WHERE id = '" + this.id + "'";
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
	
    public void addPerson(Person p) {
    	
    	String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int id = 0;
		int person_id = p.getId();
		int t_id = this.id;

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
    
    public void setPlanTime (Time planTime){
    	
    	String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int planHour = planTime.getHour();
		int planMinute = planTime.getMin();

		PreparedStatement stmtUpdateStart = null;
		String queryUpdateName = "UPDATE tasks SET planHour = '" + planHour + "', planMinute = '" + planMinute
				+ "' WHERE id = '" + this.id + "'";

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
    
    public Time getPlanTime () {
    	String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int hour = 0;
		int minute = 0;

		PreparedStatement stmtSelectStart = null;

		String querySelectStart = "SELECT planHour, planMinute FROM tasks WHERE id = '" + this.id +"'";
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
		Time end = new Time(hour, minute);
		return end;
    }
    
    
    
    public void addActivity(Activity a){
    	
    	String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int id = this.id;
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
	
	public String getName() {
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		String name = "";
		PreparedStatement stmtSelectName = null;
		String querySelectName = "SELECT name FROM tasks WHERE id = '" + this.id + "'";

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

    public List<Person> getPersonList() {
    	List<Person> list = new ArrayList<>();

		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		PreparedStatement stmtSelectID = null;
		String querySelectID = "SELECT id_user FROM user_task WHERE id_task = '" + this.id + "'";

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
    
    public List<Activity> getActivities(){
    	
    	List<Activity> list = new ArrayList<>();

		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		PreparedStatement stmtSelectID = null;
		String querySelectID = "SELECT id FROM activity WHERE id_task = '" + this.id + "'";

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
