/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.util.Optional.empty;
import static java.util.OptionalDouble.empty;
import static java.util.OptionalInt.empty;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import static java.util.stream.DoubleStream.empty;
import static java.util.stream.IntStream.empty;
import static java.util.stream.LongStream.empty;
import static java.util.stream.Stream.empty;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import model.AppointmentMGR;

/**
 * FXML Controller class
 *
 * @author David Gentry
 */
public class AddAppointmentController implements Initializable {
    
    @FXML 
    private DatePicker appointmentDate;
    
    @FXML 
    private ComboBox<String> appointmentLocation;
    
    @FXML 
    private ComboBox<String> hourTime;
    
    @FXML 
    private ComboBox<String> appointmentWith;
    
     @FXML 
    private ComboBox<String> minuteTime;
    
    @FXML 
    private ComboBox<String> appointmentReason;
    
    private ObservableList<String> reasonList = FXCollections.observableArrayList();

    private ObservableList<String> contactList = FXCollections.observableArrayList();
    
    private ObservableList<String> hourList = FXCollections.observableArrayList();
    
    private ObservableList<String> minuteList = FXCollections.observableArrayList();
    
    private ObservableList<String> locationList = FXCollections.observableArrayList();
      
    private int id;
    
    private int AptId;
   
    @FXML
    public void handleAppointmentCancel(ActionEvent event) throws IOException {
        //return to appointmentMain menu if clicked
        Parent menu = FXMLLoader.load(getClass().getResource("/view/AppointmentOverview.fxml"));
        Scene menuMain = new Scene(menu);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(menuMain);
        window.show();
    }
    
    @FXML
    public void handleAptAdd(ActionEvent event) throws IOException {
        LocalDate date = appointmentDate.getValue();
        String hour = hourTime.getValue();
        String minute = minuteTime.getValue();
        String with = appointmentWith.getValue();
        String reason = appointmentReason.getValue();
        String location = appointmentLocation.getValue();
        int customerId = id;
        LocalDateTime ldt = LocalDateTime.of(date.getYear(), date.getMonthValue(),
                date.getDayOfMonth(), Integer.parseInt(hour), Integer.parseInt(minute));
        LocalDateTime end = ldt.plusHours(1);
        String startTime = ldt.toString();
        String endTime = end.toString();
        Parent menu = FXMLLoader.load(getClass().getResource("/view/AppointmentOverview.fxml"));
        Scene menuMain = new Scene(menu);
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
                    window.setScene(menuMain);
                    window.show(); 
                }
            }
            }));
        } else {
        
                    //show confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add Appointment");
        alert.setHeaderText("Do you really want to add an appointment?");
        alert.setContentText("click ok to add the appointment");
        alert.showAndWait().ifPresent((new Consumer<ButtonType>() {
            @Override
            public void accept(ButtonType response) {
                if (response == ButtonType.OK) {
            //do the thing
            AppointmentMGR.addAppointment(customerId, reason, with, location, startTime, endTime);
           
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(menuMain);
             window.show();
                
                }
            }
        }));
        }

    }
   
    public void setCustomerID(int id) {
        this.id = id;
    }
    
    public int getCustomerID(){
        return id;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contactList.addAll("Frank Reynolds", "Mac", "Charlie", "Dee", "Dennis");
        appointmentWith.setItems(contactList);
        hourList.addAll( "08", "09", "10", "11",
                "12", "13", "14", "15", "16");
        hourTime.setItems(hourList);
        minuteList.addAll("00", "15", "30", "45");
        minuteTime.setItems(minuteList);
        reasonList.addAll("Initial Appointment", "Consultation", "Instructional", "Retrospective" );
        appointmentReason.setItems(reasonList);
        locationList.addAll("Washington", "New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Tokyo", "Toyohashi", "Okazaki", "Toyota", "Akita", "Canberra", "Sydney","Melbourne", "Brisbane", "Perth", "Moscow", "Saint Peter", "Yeketerinba", "Kazan", "Novosibirsk", "London", "Birmingham", "Manchester", "Glasgow", "Leeds", "Liverpool");
        appointmentLocation.setItems(locationList);
        
    }    

    
    
}
