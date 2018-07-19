package db_connection;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

/* Classname: Database
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



public class Database {
	
	private static String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
	private static String username = "u48005db20";
	private static String password = "prse2018";
	
	public static Connection getConnection(){
		
		Connection connection = null; 
		try {
			connection = DriverManager.getConnection(url, username, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection; 
	}
}