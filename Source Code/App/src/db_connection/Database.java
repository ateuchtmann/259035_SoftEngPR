package db_connection;

public class Database {
	

		private static String url = "jdbc:mysql://e42776-mysql.services.easyname.eu:3306/u48005db20?useSSL=false";
		private  static String username = "u48005db20";
		private static String password = "prse2018";
		
		
		public static String getUrl() {
			return url;
		}
		
		public static String getUsername() {
			return username;
		}
		
		public static String getPassword() {
			return password;
		}
}
