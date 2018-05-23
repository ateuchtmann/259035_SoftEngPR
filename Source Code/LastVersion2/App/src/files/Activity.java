package files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/* Classname: Activity
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

public class Activity {
	
	private int id; 
	
	public Activity() {
		
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		this.id = 0;

		PreparedStatement stmtID = null;

		String queryID = "SELECT MAX(id) FROM activity";
		ResultSet rs = null;

		String queryInsertActivity = "INSERT INTO activity (id, id_user, id_task, description, startHour, startMinute, endHour, endMinute) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement insertActivity = null;

		Connection connection = null;

		int startHour = 0;
		int startMinute = 0;
		int endHour = 0;
		int endMinute = 0;

		String description = "";
		int id_user = 0;
		int id_task = 0;

		try {

			connection = DriverManager.getConnection(url, username, password);

			stmtID = connection.prepareStatement(queryID);

			rs = stmtID.executeQuery();

			while (rs.next()) {
				this.id = rs.getInt(1);
			}

			this.id++;

			insertActivity = connection.prepareStatement(queryInsertActivity);

			insertActivity.setInt(1, this.id);
			insertActivity.setInt(2, id_user);
			insertActivity.setInt(3, id_task);
			insertActivity.setString(4, description);
			insertActivity.setInt(5, startHour);
			insertActivity.setInt(6, startMinute);
			insertActivity.setInt(7, endHour);
			insertActivity.setInt(8, endMinute);

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

	public Activity(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public Time getStart(){
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int hour = 0;
		int minute = 0;

		PreparedStatement stmtSelectStart = null;

		String querySelectStart = "SELECT startHour, startMinute FROM activity";
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
		Time start = new Time(hour, minute);
		return start;
	}
	
	public Time getEnd(){
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int hour = 0;
		int minute = 0;

		PreparedStatement stmtSelectStart = null;

		String querySelectStart = "SELECT endHour, endMinute FROM activity";
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
	
	public String getDescrn(){
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		String description = "";
		PreparedStatement stmtSelectDescription = null;
		String querySelectDescription = "SELECT description FROM activity WHERE id = '" + this.id + "'";

		ResultSet rs = null;

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
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
	
	public Person getPerson(){
		
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int id = this.id;
		int person = 0;
		Person user = null;

		ResultSet rs = null;

		PreparedStatement stmtSelectUser = null;
		String querySelectUser = "SELECT id_user FROM activity WHERE id = '" + id + "' ";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);

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
	
	public void addPerson(Person p){
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int id = this.id;
		int person_id = p.getId();

		PreparedStatement stmtUpdateUser = null;
		String queryInsertUsertask = "Update activity SET id_user = '" + person_id + "' WHERE id = '" + id + "' ";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);

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
	
	public void setStart(Time t){
		
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int startHour = t.getHour();
		int startMinute = t.getMin();

		PreparedStatement stmtUpdateStart = null;
		String queryUpdateName = "UPDATE activity SET startHour = '" + startHour + "', startMinute = '" + startMinute
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
	
	public void setEnd(Time t){
		
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		int endHour = t.getHour();
		int endMinute = t.getMin();

		PreparedStatement stmtUpdateStart = null;
		String queryUpdateName = "UPDATE activity SET endHour = '" + endHour + "', endMinute = '" + endMinute
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
	
	public void setDescr(String description){
		
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		String username = "u48005db20";
		String password = "prse2018";

		PreparedStatement stmtUpdateDescription = null;
		String queryUpdateDescription = "UPDATE activity SET description = '" + description + "' WHERE id = '" + this.id
				+ "'";

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
	
	
	
	

}
