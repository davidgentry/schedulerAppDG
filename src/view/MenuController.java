/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author David Gentry
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     * @param event
     * @throws java.io.IOException
     */
    
    public void handleCustomer(ActionEvent event) throws IOException { 

        Parent customer = FXMLLoader.load(getClass().getResource("/view/CustomerMain.fxml"));
        Scene customerMain = new Scene(customer);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(customerMain);
        window.show();

    }
    
    public void handleAppointment(ActionEvent event) throws IOException { 
 
        Parent appointment = FXMLLoader.load(getClass().getResource("/view/AppointmentOverview.fxml"));
        Scene appointmentMain = new Scene(appointment);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(appointmentMain);
        window.show();

    }
    
    public void handleReport(ActionEvent event) throws IOException { 

        Parent report = FXMLLoader.load(getClass().getResource("/view/Report.fxml"));
        Scene reportMain = new Scene(report);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(reportMain);
        window.show();

    }
    
    public void handleLogout(ActionEvent event) throws IOException { 
        //logout if clicked
        Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Exit?");
            alert.setHeaderText("Unsaved Changes may be lost");
            alert.setContentText("If you exit now, any unsaved changes will be lost!");
            alert.showAndWait();
        System.exit(1);
        
    }
    
    public void handleLogFile(ActionEvent event) throws IOException {
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
