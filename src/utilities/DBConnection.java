/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author David Gentry
 */
public class DBConnection {
    private static final String databaseName = "U04KCq";
    private static final String DB_URL = "jdbc:mysql://52.206.157.109/" + databaseName;
    private static final String username = "U04KCq";
    private static final String password = "53688263595";
    private static final String driver = "com.mysql.jdbc.Driver";
    static Connection conn;
    //open connect
    public static void makeConnection() throws ClassNotFoundException, SQLException, Exception
    {
        Class.forName(driver);
        conn = (Connection) DriverManager.getConnection(DB_URL,username,password);
        System.out.println("connection successful");
    }
    //close connect
    public static void closeConnection() throws ClassNotFoundException, SQLException, Exception 
    {
        conn.close();
        System.out.println("connection closed");
    }
    
    // get DBConnection
    public static java.sql.Connection getConnection() {
        return conn;
    }
}
