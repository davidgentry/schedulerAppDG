/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import utilities.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author David Gentry
 */
public class UserMGR {
    
    private static User user;
    
    public static User getCurrentUser() {
        return user;
    }
     // Login or Else

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public static Boolean login(String username, String password) throws IOException {
        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT * FROM user WHERE userName='" + username + "' AND password='" + password + "'";
            ResultSet results = stmt.executeQuery(query);
            if(results.next()) {
                user = new User();
                user.setUser(results.getString("userName"));
                stmt.close();
                // write to log file
                return true;
            } else {
                return false;
            }
        } catch (SQLException error) {
            System.out.println("SQLException: " + error.getMessage());
            return false;
        }
    }
}






