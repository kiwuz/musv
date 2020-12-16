package panel_glowny;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Statement;


public class MysqlConnect {
    // init database constants
	
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://192.166.219.220/musvix";
    private static final String USERNAME = "musv";
    private static final String PASSWORD = "a8..3Wob.ez";
    private static final String MAX_POOL = "250";
    
    
    
    
    
	public static String title = "";
	public int language;
	public String adresObrazka = "";
    
    // init connection object
    private Connection connection;
    // init properties object
    private Properties properties;

    // create properties
    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        return properties;
    }

    // connect database
    public Connection connect() {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                e.getCause();
            }
        }
        return connection;
    }

    // disconnect database
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean loadTheme() {
        connect();
    	Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Layout FROM musvix.User  WHERE Login='" + login_panel.logged_user+"'");
			rs.next();
			boolean result = rs.getBoolean("Layout");
			System.out.println(result);
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	disconnect();
    	return false;
    }
    
    public String getTitleFromSQL() {
        connect();
    	Statement stmt;
        //String title = "";
    	try {
			stmt = connection.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT title FROM musvix.Application");
        	rs.next();
        	
        	title = rs.getString("title");
        	
			System.out.println("Title z sql" + title);

    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	disconnect();
		return title;
    }
    
    public void sendTitleToSQL(String newTitleFromField) {
        connect();
    	Statement stmt;
    	try {
			stmt = connection.createStatement();
    		stmt.executeUpdate("UPDATE musvix.Application SET title='" + newTitleFromField +"'");
        	        	
			System.out.println("Sending title to mysql: " + newTitleFromField);

    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	disconnect();
    }
    
    public void sendSelectedLanguage(int LanguageFromCombobox) {
        connect();
    	Statement stmt;
    	try {
			stmt = connection.createStatement();
    		stmt.executeUpdate("UPDATE musvix.User SET Language='" + LanguageFromCombobox + "' WHERE Login='" + login_panel.logged_user+"'");
        	        	
			System.out.println("Sending  language to mysql: " + LanguageFromCombobox);

    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	disconnect();
    }
    
    
    public int getSelectedLanguage() {
        connect();
    	Statement stmt;
    	try {
			stmt = connection.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT Language From musvix.User WHERE Login='" + login_panel.logged_user+"'");
        	rs.next();
        	language = rs.getInt("Language");
        	
			System.out.println("geting language from mysql" );
			System.out.println("selected language: "+ language);
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	disconnect();
		return language;
    }  
    
    
    public String getimgLocation() {
        connect();
    	Statement stmt;
        //String title = "";
    	try {
			stmt = connection.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT adresObrazka FROM musvix.User WHERE Login='" + login_panel.logged_user+"'");
        	rs.next();
        	
        	adresObrazka = rs.getString("adresObrazka");
        	
			System.out.println("Avatar: " + adresObrazka);

    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	disconnect();
		return adresObrazka;
    }
    
    
}


