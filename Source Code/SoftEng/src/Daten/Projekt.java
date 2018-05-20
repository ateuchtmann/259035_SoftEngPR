package Daten;

import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Projekt {
	
	private int id;
	private String name;
	private String beschreibung;
	private List<Aufgabenbereich> aBereiche;
	private List<Person> personen;
	
	public Projekt() {
		
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
		beschreibung = " ";
		aBereiche = new ArrayList<>();
		personen = new ArrayList<>();
		//sqlstringinsert
	}
	
    public void setName(String name) {
    	this.name = name;
    }
	
    public void setBeschreibung(String b) {
    	this.beschreibung = b;
    }
	
    public String getName() {
    	return name;
    }
	
    public void addAufgabenberich(Aufgabenbereich a) {
    	aBereiche.add(a);
    }
    
    public void addPerson(Person p) {
    	personen.add(p);
    }
    
	public String getBeschreibung() {
		return beschreibung;
	}
	
	public List getAufgabenbereiche() {
		return aBereiche;
	}
	
    public List getPeronen() {
    	return personen;
    }
	
	
	
	
}
