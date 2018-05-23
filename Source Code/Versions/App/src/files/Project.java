package files;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* Classname: Project
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

public class Project {
	
	private int id;
	private String name;
	private String descr;
	private List<TaskGroup> tskGroup;
	private List<Person> personList;
	
	public Project() {
		
		Statement stmt = null;
		ResultSet rs = null;
		String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20";
		String username = "u48005db20";
		String password = "prse2018";
		
		/*
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
		    System.out.println("Database connected! Projekt Class");
		    stmt = connection.createStatement();
		    String sql="";
		    PreparedStatement ps = null;
		    
		    sql="SELECT MAX(id) FROM projects";
		    ps = connection.prepareStatement(sql);
		    
		    rs = ps.executeQuery();
		    while(rs.next()) {
		    	id=rs.getInt(1);
			    System.out.println("\nID Projekt MAX: " + id);
		    }
		    
		    id++;
		    
		    System.out.println("\nID Projekt New ID: " + id);
		    
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database! Projekt Class", e);
		}
		*/
		
		id = 0;
		name = "Projekt name";
		descr = " ";
		tskGroup = new ArrayList<>();
		personList = new ArrayList<>();
		//sqlstringinsert
	}
	
    public void setName(String name) {
    	this.name = name;
    }
	
    public void setDescr(String b) {
    	this.descr = b;
    }
	
    public String getName() {
    	return name;
    }
	
    public void addAufgabenberich(TaskGroup a) {
    	tskGroup.add(a);
    }
    
    public void addPerson(Person p) {
    	personList.add(p);
    }
    
	public String getDescr() {
		return descr;
	}
	
	public List getTsks() {
		return tskGroup;
	}
	
    public List getPersonList() {
    	return personList;
    }
	
	
	
	
}
