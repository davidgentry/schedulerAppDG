/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import utilities.DBConnection;

/**
 * FXML Controller class
 *
 * @author David Gentry
 */

 


public class ReportController implements Initializable {
    
    @FXML
    private TextArea reportByMonth;
    
    @FXML
    private TextArea reportByContact;
    
    @FXML
    private TextArea reportByType;
    
    public void handleBack(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
        Scene menuMain = new Scene(menu);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(menuMain);
        window.show();
    }
    
    public void handleReportsByMonth() {
        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT description, MONTHNAME(start) as 'Month', COUNT(*) as 'Number of Appointments' FROM appointment GROUP BY description, MONTH(start)";
            ResultSet results = stmt.executeQuery(query);
            StringBuilder reportByMonthList = new StringBuilder();
            reportByMonthList.append(String.format("%1$-20s %2$-40s %3$s \n", "Month", "Appointment Type", "Number of Appointments"));
            reportByMonthList.append(String.join("", Collections.nCopies(200, "*")));
            reportByMonthList.append("\n");
            while(results.next()) {
                reportByMonthList.append(String.format("%1$-20s %2$-40s %3$d \n", 
                    results.getString("Month"), results.getString("description"), results.getInt("Number of Appointments")));
            }
            stmt.close();
            reportByMonth.setText(reportByMonthList.toString());
        } catch (SQLException error) {
            System.out.println("SQLException: " + error.getMessage());
        }
    }    
    
    public void handleReportsByContact() {
        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT appointment.contact, appointment.description, customer.customerName, start, end " +
                    "FROM appointment JOIN customer ON customer.customerId = appointment.customerId " +
                    "GROUP BY appointment.contact, MONTH(start), start";
            ResultSet results = stmt.executeQuery(query);
            StringBuilder reportByContactList = new StringBuilder();
            reportByContactList.append(String.format("%1$-20s %2$-30s %3$-25s %4$-35s %5$s \n", 
                    "Consultant", "Appointment", "Customer", "Start", "End"));
            reportByContactList.append(String.join("", Collections.nCopies(200, "*")));
            reportByContactList.append("\n");
            while(results.next()) {
                reportByContactList.append(String.format("%1$-20s %2$-30s %3$-25s %4$-35s %5$s \n", 
                    results.getString("contact"), results.getString("description"), results.getString("customerName"),
                    results.getString("start"), results.getString("end")));
            }
            stmt.close();
            reportByContact.setText(reportByContactList.toString());
        } catch (SQLException error) {
            System.out.println("SQLException: " + error.getMessage());
        }
    }
    
    public void handleReportsByType() {
        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            String query = "SELECT description, COUNT(*) as 'Total' FROM appointment GROUP BY description";
            ResultSet results = stmt.executeQuery(query);
            StringBuilder reportByTypeList = new StringBuilder();
            reportByTypeList.append(String.format("%1$-65s %2$-65s \n", "description", "Total"));
            reportByTypeList.append(String.join("", Collections.nCopies(200, "*")));
            reportByTypeList.append("\n");
            while(results.next()) {
                reportByTypeList.append(String.format("%1$s %2$65d \n", 
                    results.getString("description"), results.getInt("Total")));
            }
            stmt.close();
            reportByType.setText(reportByTypeList.toString());
        } catch (SQLException error) {
            System.out.println("SQLExcpetion: " + error.getMessage());
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        handleReportsByMonth();
        handleReportsByType();
        handleReportsByContact();
    }    
    
}
