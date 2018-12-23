/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.AppointmentMGR;
import model.Customer;
import model.CustomerMGR;

/**
 * FXML Controller class
 *
 * @author David Gentry
 */
public class AppointmentOverviewController implements Initializable {
    
    @FXML
    private TableView<Appointment> monthTable;
    
    @FXML
    private TableColumn<Appointment, String> monthDate;
    
    @FXML
    private TableColumn<Appointment, String> monthStart;
    
    @FXML
    private TableColumn<Appointment, String> monthLocation;
     
    @FXML
    private TableColumn<Appointment, String> monthType;
      
    @FXML
    private TableColumn<Appointment, String> monthWith;
    
    @FXML
    private TableColumn<Appointment, Integer> monthID;
    
    @FXML
    private TableView<Appointment> weekTable;
    
    @FXML
    private TableColumn<Appointment, String> weekDate;
    
    @FXML
    private TableColumn<Appointment, String> weekStart;
    
    @FXML
    private TableColumn<Appointment, String> weekLocation;
     
    @FXML
    private TableColumn<Appointment, String> weekType;
      
    @FXML
    private TableColumn<Appointment, String> weekWith;
    
     @FXML
    private TableColumn<Appointment, Integer> weekID;
    
    @FXML
    private TableView<Customer> customerTable;
    
    @FXML
    private TableColumn<Customer, Integer> customerID;
    
    @FXML 
    private TableColumn<Customer, String> customerName;
    
    @FXML
    private Tab monthView;
    
    @FXML
     private Tab weekView;
    
    boolean isMonthView;
   
    Appointment appointment;
    Customer customer;
   
    Appointment selectedAppointment;
    /**
     *
     * @param event
     * @return 
     */
    
    @FXML
    public void handleCustomerSelect(MouseEvent event)  {
        customer = customerTable.getSelectionModel().getSelectedItem();
        int id = customer.getCustomerID();
        monthTable.setItems(AppointmentMGR.getAppointmentsByMonth(id));
        System.out.println(AppointmentMGR.getAppointmentsByMonth(1));
        weekTable.setItems(AppointmentMGR.getAppointmentsByWeek(id));
    }
    
    @FXML
    public void handleAppointmentEdit(ActionEvent event) throws IOException {
    if(weekView.isSelected()) {
                selectedAppointment = weekTable.getSelectionModel().getSelectedItem();
        } else {
         selectedAppointment = monthTable.getSelectionModel().getSelectedItem();      
        }
    int aptID = selectedAppointment.getAppointmentID();
    System.out.println(aptID);
    FXMLLoader editAPT = new FXMLLoader(getClass().getResource("/view/EditAppointment.fxml"));    
    Parent root = (Parent)editAPT.load();
    EditAppointmentController controller = editAPT.<EditAppointmentController>getController();
    controller.setAppointmentID(aptID);
    Scene editAppointment = new Scene(root);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(editAppointment);
    window.show();

    }
    
    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void handleAppointmentAdd(ActionEvent event) throws IOException { 
        customer = customerTable.getSelectionModel().getSelectedItem();
        int id = customer.getCustomerID();
        FXMLLoader addAPT = new FXMLLoader(getClass().getResource("/view/AddAppointment.fxml"));
        Parent root = (Parent)addAPT.load();
        AddAppointmentController controller = addAPT.<AddAppointmentController>getController();
        controller.setCustomerID(id);
        Scene addAppointment = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addAppointment);
        window.show();

    }
    
    @FXML
    public void handleAppointmentDelete(ActionEvent event) throws IOException {
        if (monthView.isSelected()) {
            isMonthView = true;
            selectedAppointment = monthTable.getSelectionModel().getSelectedItem();
        } 
        if (weekView.isSelected()) {
            isMonthView = false;
            selectedAppointment = monthTable.getSelectionModel().getSelectedItem();
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Appointment?");
        alert.setContentText("This will permanently delete the selected appointment. Click ok to proceed.  This cannot be undone!");
        alert.showAndWait().ifPresent((response -> {
            if(response == ButtonType.OK) {
                AppointmentMGR.deleteAppointment(selectedAppointment.getAppointmentID());
                if(isMonthView) {
                   monthTable.setItems(AppointmentMGR.getAppointmentsByMonth(customer.getCustomerID())); 
                } else {
                    weekTable.setItems(AppointmentMGR.getAppointmentsByWeek(customer.getCustomerID()));
                }
            }
        }));
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerTable.setItems(CustomerMGR.getCustomers());
        monthLocation.setCellValueFactory(cellData -> {
            return cellData.getValue().getAppointmentLocationProp();
        });
        monthDate.setCellValueFactory(cellData -> {
            return cellData.getValue().getAppointmentStartProp();
        });
        monthStart.setCellValueFactory(cellData -> {
            return cellData.getValue().getAppointmentEndProp();
        });
        monthType.setCellValueFactory(cellData -> {
            return cellData.getValue().getAppointmentDescriptionProp();
        });
        monthWith.setCellValueFactory(cellData -> {
            return cellData.getValue().getAppointmentContactProp();
        });
     
       
        weekLocation.setCellValueFactory(cellData -> {
            return cellData.getValue().getAppointmentLocationProp();
        });
        weekDate.setCellValueFactory(cellData -> {
            return cellData.getValue().getAppointmentStartProp();
        });
        weekStart.setCellValueFactory(cellData -> {
            return cellData.getValue().getAppointmentEndProp();
        });
        weekType.setCellValueFactory(cellData -> {
            return cellData.getValue().getAppointmentDescriptionProp();
        });
        weekWith.setCellValueFactory(cellData -> {
            return cellData.getValue().getAppointmentContactProp();
        });
    }    
    
}
