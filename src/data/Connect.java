package data;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	public static final String URL = "jdbc:mysql://localhost:3306/tp_java";
    public static final String USER = "root";
    public static final String PASSWORD = "administrador";
     
    public Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return con;
    }
}