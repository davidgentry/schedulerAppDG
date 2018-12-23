/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.DBConnection;

/**
 *
 * @author David Gentry
 */
public class AppointmentMGR {
   
    public static ObservableList<Appointment> getAppointmentsByWeek(int id) { 
        ObservableList<Appointment> appointmentsWeek = FXCollections.observableArrayList();
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now().plusWeeks(1);
        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT * FROM appointment WHERE customerId = '" + id + "' AND " + 
                "start >= '" + start + "' AND start <= '" + end + "'";
            ResultSet results = stmt.executeQuery(query);
            while(results.next()) {
                Appointment appointment = new Appointment(
                    results.getInt("appointmentId"), 
                    results.getInt("customerId"), 
                    results.getString("start"),
                    results.getString("end"), 
                    results.getString("title"), 
                    results.getString("description"),
                    results.getString("location"), 
                    results.getString("contact"));
                appointmentsWeek.add(appointment);
            }
            stmt.close();
            return appointmentsWeek;
        } catch (SQLException error) {
            System.out.println("SQLException: " + error.getMessage());
            return null;
        }
    }
    
    public static ObservableList<Appointment> getAppointmentsByMonth (int id) {
        ObservableList<Appointment> appointmentsMonth = FXCollections.observableArrayList();
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now().plusMonths(1);
        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT * FROM appointment WHERE customerId = '" + id + "' AND " + 
                "start >= '" + start + "' AND start <= '" + end + "'"; 
            ResultSet results = stmt.executeQuery(query);
            while(results.next()) {
                Appointment appointment = new Appointment(
                    results.getInt("appointmentId"), 
                    results.getInt("customerId"), 
                    results.getString("start"),
                    results.getString("end"), 
                    results.getString("title"), 
                    results.getString("description"),
                    results.getString("location"), 
                    results.getString("contact"));
                appointmentsMonth.add(appointment);
            }
            stmt.close();
            return appointmentsMonth;
        } catch (SQLException error) {
            System.out.println("SQLException: " + error.getMessage());
            return null;
        }
    }
    
    public static boolean addAppointment(int customerId, String type, String contact, String location, String startTime, String endTime) {
       
        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "INSERT INTO appointment SET customerId='" + customerId + "', title='" + contact + "', description='" + type + "', contact='" + contact + "', location='" + location + "', start='" + startTime + "', end='" + 
                endTime + "', url='', createDate=NOW(), createdBy='', lastUpdate=NOW(), lastUpdateBy=''";
            int update = stmt.executeUpdate(query);
            if(update == 1) {
                return true;
            }
        } catch (SQLException error) {
            System.out.println("SQLException: " + error.getMessage());
        }
        return false;
    }
    
    public static boolean appointmentsOverlap(int id, String location, String startTime) {
       String start = startTime;
        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT * FROM appointment WHERE start = '" + start + "' AND location = '" + location + "'";
            ResultSet results = stmt.executeQuery(query);
            if(results.next()) {
                if(results.getString("start").equals(startTime)) {
                    stmt.close();
                    return false;
                }
                stmt.close();
                return true;
            } else {
                stmt.close();
                return false;
            }
        } catch (SQLException e) {
            System.out.println("SQLExcpection: " + e.getMessage());
            return true;
        }
    }
    
    
    public static boolean editAppointment(int id, String type, String contact, String location, String startTime, String endTime) {
       
        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "UPDATE appointment SET description='" + type + "', contact='" +
                contact + "', location='" + location + "', start='" + startTime + "', end='" + endTime + "' WHERE " +
                "appointmentId='" + id + "'";
            int update = stmt.executeUpdate(query);
            if(update == 1) {
                return true;
            }
        } catch (SQLException error) {
            System.out.println("SQLException: " + error.getMessage());
        }
        return false;
    }
    
     public static boolean deleteAppointment(int id) {
        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "DELETE FROM appointment WHERE appointmentId = " + id;
            int queryNumber = stmt.executeUpdate(query);
            if(queryNumber == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return false;
    }
     
     /*public static int getAppointmentID(String dateTime){ 
         String dateTimeValue;
         try {
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT * FROM appointment WHERE start = " + dateTime; 
              ResultSet results = stmt.executeQuery(query);
              results.getString("start");
           
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
     }
    */
    
    
}
