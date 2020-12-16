package panel_glowny;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class admin_functions {

	private Connection connect=null;
	private Statement statement=null;
	private ResultSet resultSet=null;
	public static String[][] Users ;
	private String user="musv";
	private String pass="a8..3Wob.ez";
	private String login = null;
	private String haslo = null;
	private String url;
	public static int count;
public admin_functions() {
	
	}
		

	public void displayUsers() throws Exception {
		try {
			url="jdbc:mysql://192.166.219.220:3306/musvix";
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url,user,pass);
			
			
			
			statement=connect.createStatement();
			Statement stmt = connect.createStatement();
			ResultSet songCount;
			songCount=stmt.executeQuery("SELECT COUNT(IDUser) FROM User");
		
			if(songCount.next()) 
			count = songCount.getInt(1);
			
		
			System.out.println(count);
		 Users = new String[count][3];
		 resultSet=statement.executeQuery("SELECT  IDUser, Login,  type FROM songs");
		 writeResultSet(resultSet,count,Users);
			connect.close();
			System.out.println("Connection closed");
			////INSERT//////////////////////////////////////
		}catch (Exception e) {
			System.out.println("Blad polaczenia z baza danych");
			System.out.println(e);
		}finally{
		      //closing ResultSet,PreparedStatement and Connection object
	    }
	}
	
	public void writeResultSet(ResultSet resultSet,int ct,String[][] tb) throws SQLException
	{
		
		System.out.println(tb[1]);
		int i =0;
		while (resultSet.next()) {
			tb[i][0]=resultSet.getString("IDUser");
		
			tb[i][1]=resultSet.getString("Login");
			tb[i][2]=resultSet.getString("Type");
			i++;
		
		}
		System.out.println(count);
	}
}

