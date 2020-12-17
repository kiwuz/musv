package panel_glowny;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    private static Connection connection;
    // init properties object
    private static Properties properties;

    // create properties
    private static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        return properties;
    }

    // connect database
    public static Connection connect() {
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
    public static void disconnect() {
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
			ResultSet rs = stmt.executeQuery("SELECT Layout FROM musvix.User  WHERE Login='" + login_panel.temporaryLogin+"'");
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
    		stmt.executeUpdate("UPDATE musvix.User SET Language='" + LanguageFromCombobox + "' WHERE Login='" + login_panel.temporaryLogin+"'");
        	        	
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
    		ResultSet rs = stmt.executeQuery("SELECT Language From musvix.User WHERE Login='" + login_panel.temporaryLogin+"'");
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
    		ResultSet rs = stmt.executeQuery("SELECT adresObrazka FROM musvix.User WHERE Login='" + login_panel.temporaryLogin+"'");
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
    public static void sendStringToSQL(String imageAdress, String login) {
    	try {
    		connect();
    		PreparedStatement ps = connection.prepareStatement("UPDATE musvix.User SET adresObrazka = ? WHERE Login = ?");
    		ps.setString(1, imageAdress);
    		ps.setString(2, login);
			ps.executeUpdate();
			disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void sendLayoutToSQL(String login, int number) {
    	try {
    		connect();
    		PreparedStatement ps = connection.prepareStatement("UPDATE musvix.User SET Layout = ? WHERE Login = ?");
    		ps.setString(2, login);
    		ps.setInt(1, number);
    		ps.executeUpdate();
    		disconnect();
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public static void updatePassword(String login, String newPassword) {
    	try {
    		connect();
    		PreparedStatement preparedStatement = connection.prepareStatement("UPDATE musvix.User SET Password = ? WHERE Login = ?");
    		preparedStatement.setString(1, newPassword);
    		preparedStatement.setString(2, login);
    		preparedStatement.executeUpdate();
    		
    		disconnect();
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public static void updateStringToSQL(String login, String column, String data) {
    	try {
    		connect();
    		PreparedStatement preparedStatement = connection.prepareStatement("UPDATE musvix.User SET " + column + " = ? WHERE Login = ?");
    		preparedStatement.setString(1, data);
    		preparedStatement.setString(2, login);
    		
    		preparedStatement.executeUpdate();
    		
    		disconnect();
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public static void updateIntegerToSQL(String login, String column, int data) {
    	try {
    		connect();
    		PreparedStatement preparedStatement = connection.prepareStatement("UPDATE musvix.User SET " + column + " = ? WHERE Login = ?");
    		preparedStatement.setInt(1, data);
    		preparedStatement.setString(2, login);
    		preparedStatement.executeUpdate();
    		disconnect();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    
    public static int getIntFromSQL(String login, String column) {
    	int number = 0;
    	try {
    		connect();
    		ResultSet rs;
    		PreparedStatement ps = connection.prepareStatement("SELECT " + column + " FROM musvix.User WHERE Login = ?");
    		
    		ps.setString(1, login);
    		
    		rs = ps.executeQuery();
    		rs.next();
    		number = rs.getInt(column);
    		disconnect();
    		
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return number;
    }
    
    //metoda sluzaca do zczytywania z bazy danych 
    
    public static String getStringFromSQL(String login, String column) {
    	String data = "";
    	try {
    		connect();
    		ResultSet rs;
        	PreparedStatement preparedStatement = connection.prepareStatement("SELECT " + column + " FROM musvix.User WHERE Login = ?");
        	
        	preparedStatement.setString(1, login);
        	
        	rs = preparedStatement.executeQuery();
        	rs.next();
        	data = rs.getString(column);
        	disconnect();
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
		return data;
    }
    
    //funkcja sluzaca do sciagania hasla z bazy danych
    public static boolean getPassword(String login, String password) {
    	try {
    		connect();
        	ResultSet rs;
			PreparedStatement ps = connection.prepareStatement("SELECT Password FROM musvix.User WHERE Login = ?");
			ps.setString(1, login);
			rs = ps.executeQuery();
			if(rs.next()) {
				
				String data = rs.getString("Password");
				if(password.equals(data)) {
					return true;
				}
				else {
					return false;
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }
    
    public static List <userEntity> addToList() {
    	List <userEntity> listaUzytkownikow = new ArrayList<userEntity>();
		try {
			connect();
	    	ResultSet rs;
	    	PreparedStatement ps;
			ps = connection.prepareStatement("SELECT IDUser, Login, Name, Gender, Type, Language, Layout, adresObrazka FROM musvix.User");
			rs = ps.executeQuery();
			while(rs.next()) {
				userEntity newUser = new userEntity(); //tu jest wszystko na null
				newUser.setIDUser(rs.getInt("IDUser")); 
				newUser.setLogin(rs.getString("Login"));
				newUser.setName(rs.getString("Name"));
				newUser.setGender(rs.getString("Gender"));
				newUser.setType(rs.getInt("Type"));
				newUser.setLanguage(rs.getInt("Language"));
				newUser.setLayout(rs.getInt("Layout"));
				newUser.setAdresObrazka(rs.getString("adresObrazka"));
				listaUzytkownikow.add(newUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaUzytkownikow;
    }
    
    
    
}


