package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;



public class DbConnection implements Provider {
	private static Connection con=null;  
	static{  
	try{  
	Class.forName(DRIVER);  
	con=DriverManager.getConnection(CONNECTION_URL,USERNAME,PASSWORD);  
	}catch(Exception e){}  
	}  
	  
	public static Connection getConnection(){  
	    return con;  
	}  
	

}
