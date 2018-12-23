/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import model.AppointmentMGR;

/**
 * FXML Controller class
 *
 * @author David Gentry
 */
public class EditAppointmentController implements Initializable {
    
    @FXML 
    private DatePicker appointmentDate;
    
    @FXML
    private ComboBox<String> appointmentHour;
    
    @FXML
    private ComboBox<String> appointmentMinutes;
    
    @FXML
    private ComboBox<String> appointmentLocation;
    
    @FXML
    private ComboBox<String> appointmentWith;
    
    @FXML
    private ComboBox<String> appointmentType;
    
    private ObservableList<String> reasonList = FXCollections.observableArrayList();

    private ObservableList<String> contactList = FXCollections.observableArrayList();
    
    private ObservableList<String> hourList = FXCollections.observableArrayList();
    
    private ObservableList<String> minuteList = FXCollections.observableArrayList();
    
    private ObservableList<String> locationList = FXCollections.observableArrayList();
    
    private int aptID;
    

    /**
     * Initializes the controller class.
     */
    
    public void handleEdit(ActionEvent event) throws IOException { 
        //edit functionality here 
        LocalDate date = appointmentDate.getValue();
        String hour = appointmentHour.getValue();
        String minute = appointmentMinutes.getValue();
        String with = appointmentWith.getValue();
        String reason = appointmentType.getValue();
        String location = appointmentLocation.getValue();
        int id = aptID;
        LocalDateTime ldt = LocalDateTime.of(date.getYear(), date.getMonthValue(),
                date.getDayOfMonth(), Integer.parseInt(hour), Integer.parseInt(minute));
        LocalDateTime end = ldt.plusHours(1);
        String startTime = ldt.toString();
        String endTime = end.toString();
        Parent menu = FXMLLoader.load(getClass().getResource("/view/AppointmentOverview.fxml"));
        Parent editMenu = FXMLLoader.load(getClass().getResource("/view/EditAppointment.fxml"));
        Scene menuMain = new Scene(menu);
        Scene menuEdit = new Scene(editMenu);
        if (AppointmentMGR.appointmentsOverlap(-1, location, startTime )) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OverlappingAppointments");
            alert.setHeaderText("This Appointment time is already taken.");
            alert.setContentText("Please select another time at least one hour later.");
            alert.showAndWait().ifPresent((new Consumer<ButtonType>() {
            @Override
            public void accept(ButtonType response) {
                if (response == ButtonType.OK) {  
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(menuEdit);
                    window.show(); 
                }
            }
            }));
        } else {
        
                    //show confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Edit Appointment");
        alert.setHeaderText("Do you really want to edit the appointment?");
        alert.setContentText("click ok to edit the appointment.");
        alert.showAndWait().ifPresent((new Consumer<ButtonType>() {
            @Override
            public void accept(ButtonType response) {
                if (response == ButtonType.OK) {
            //do the thing
            AppointmentMGR.editAppointment(id, reason, with, location, startTime, endTime);
           
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(menuMain);
            window.show();  
                }
            }
        }));
        }
        
    }
    
    public void handleCancel(ActionEvent event) throws IOException { 
  
        Parent menu = FXMLLoader.load(getClass().getResource("/view/AppointmentOverview.fxml"));
        Scene menuMain = new Scene(menu);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(menuMain);
        window.show();

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        contactList.addAll("Frank Reynolds", "Mac", "Charlie", "Dee", "Dennis");
        appointmentWith.setItems(contactList);
        hourList.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        appointmentHour.setItems(hourList);
        minuteList.addAll("00", "15", "30", "45");
        appointmentMinutes.setItems(minuteList);
        reasonList.addAll("Initial Appointment", "Consultation", "Instructional", "Retrospective" );
        appointmentType.setItems(reasonList);
        locationList.addAll("Washington", "New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Tokyo", "Toyohashi", "Okazaki", "Toyota", "Akita", "Canberra", "Sydney","Melbourne", "Brisbane", "Perth", "Moscow", "Saint Peter", "Yeketerinba", "Kazan", "Novosibirsk", "London", "Birmingham", "Manchester", "Glasgow", "Leeds", "Liverpool");
        appointmentLocation.setItems(locationList);
    }    
    
      public void setAppointmentID(int id) {
        this.aptID = id;
    }
    
    public int getAppointmentID(){
        return aptID;
    }
    
}
