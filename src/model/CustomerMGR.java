/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.DBConnection;

/**
 *
 * @author David Gentry
 */
public class CustomerMGR {

   private static ObservableList<Customer> customers = FXCollections.observableArrayList();

    public static ObservableList<Customer> getCustomers() {
        //return customers in the DB
        customers.clear();
        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT customer.customerId, customer.customerName, address.address, address.phone, address.postalCode, city.city"
                + " FROM customer INNER JOIN address ON customer.addressId = address.addressId "
                + "INNER JOIN city ON address.cityId = city.cityId";
            ResultSet results = stmt.executeQuery(query);
            while(results.next()) {
                Customer customer = new Customer(
                    results.getInt("customerId"), 
                    results.getString("customerName"), 
                    results.getString("address"),
                    results.getString("city"),
                    results.getString("phone"),
                    results.getString("postalCode"));
                customers.add(customer);
            }
            stmt.close();
            return customers;
        } catch (SQLException error) {
            System.out.println("SQLException: " + error.getMessage());
            return null;
        }
    }
    
    
    //save a customer to the DB
    // Saves new Customer to Database
    public static boolean saveCustomer(String name, String address, int cityID, String zip, String phone) {
        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            String addressQuery = "INSERT INTO address SET address='" + address + "', phone='" + phone + "', postalCode='" + zip + "', cityID=" + cityID + ", createDate=current_timestamp, createdBy='" + name + "', lastUpdate=current_timestamp, lastUpdateBy='" + name + "'";
            int updateAddress = stmt.executeUpdate(addressQuery , Statement.RETURN_GENERATED_KEYS);
            int newAddressId = -1;
                ResultSet rs = stmt.getGeneratedKeys();
                
                if(rs.next()){
                    newAddressId = rs.getInt(1);
                   
                }
            if(updateAddress == 1) {
                String customerQuery = "INSERT INTO customer SET customerName='" + name + "', addressId=" + newAddressId;
                int updateCustomer = stmt.executeUpdate(customerQuery);
                if(updateCustomer == 1) {
                    return true;
                }
            }
        } catch (SQLException error) {
            System.out.println("SQLException: " + error.getMessage());
        }
        return false;
    }

    public static boolean deleteCustomer(int customerID) {
         try {
            Statement stmt = DBConnection.getConnection().createStatement();
            String addressIDQuery = "SELECT addressId FROM customer WHERE customerId=" + customerID;
            int newAddressId = -1;
            String customerQuery = "DELETE FROM customer WHERE customerId=" + customerID;
            int customerStmt = stmt.executeUpdate(customerQuery);
            if(customerStmt == 1) {
                ResultSet rs = stmt.executeQuery(addressIDQuery);
                    while(rs.next()){
                    newAddressId = rs.getInt(1);
                }   
                String addressQuery = "DELETE FROM address WHERE addressId=" + newAddressId;
                int addressStmt = stmt.executeUpdate(addressQuery);
                if(addressStmt == 1) {
                    System.out.println("statement happened");
                    return true;
                }
            }
        } catch(SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
         
        return false;
    }

    public static boolean modifyCustomer(int id, String name, String address, int cityID, String zip, String phone) {
         try {
            Statement stmt = DBConnection.getConnection().createStatement();
            String addressIDQuery = "SELECT addressId FROM customer WHERE customerId=" + id;
            String addressQuery = "UPDATE address SET address='" + address + "', phone='" + phone + "', postalCode='" + zip + "', cityID=" + cityID + " WHERE addressId=" + id;
            int newAddressId = -1;
            ResultSet rs = stmt.executeQuery(addressIDQuery);
            if (rs.next()){
                newAddressId = rs.getInt(1);
                //System.out.println(newAddressId);
            }
            int updateAddress = stmt.executeUpdate(addressQuery);
            if(updateAddress == 1) {
                String customerQuery = "UPDATE customer SET customerName='" + name + "', addressId=" + newAddressId + " WHERE customerId=" + id;
                int updateCustomer = stmt.executeUpdate(customerQuery);
                if(updateCustomer == 1) {
                    return true;
                }
            }
        } catch (SQLException error) {
            System.out.println("SQLException: " + error.getMessage());
        }
        return false;
    }
}
   
