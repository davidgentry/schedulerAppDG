/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import model.CustomerMGR;

/**
 * FXML Controller class
 *
 * @author David Gentry
 */
public class CustomerMainController implements Initializable {

    @FXML
    private TableView<Customer> customerTable;
    
    @FXML
    private TableColumn<Customer, Integer> customerID;
    
    @FXML
    private TableColumn<Customer, String> customerName;
    
    @FXML
    private TableColumn<Customer, String> customerAddress;
    
    @FXML
    private TableColumn<Customer, String> customerPhone;
    
    @FXML
    private TextField customerNameField;
    
    @FXML
    private TextField addressField;
    
    @FXML
    private ComboBox city;
    
    @FXML
    private TextField zipCodeField;
    
    @FXML
    private TextField phoneField;
    
    @FXML 
    private Button addButton;
    
    @FXML 
    private Button editButton;
    
    @FXML 
    private Button deleteButton;
    
    @FXML 
    private Button backButton;
    
    private Customer customerSelection;
    
     private ObservableList<String> cityList = FXCollections.observableArrayList(
    "Washington", "New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Tokyo", "Toyohashi", "Okazaki", "Toyota", "Akita", "Canberra", "Sydney","Melbourne", "Brisbane", "Perth", "Moscow", "Saint Peter", "Yeketerinba", "Kazan", "Novosibirsk", "London", "Birmingham", "Manchester", "Glasgow", "Leeds", "Liverpool");
    /**
     * Initializes the controller class.
     * @param event
     * @throws java.io.IOException
     */
    @FXML
    public void handleMenu(ActionEvent event) throws IOException { 
        Parent menu = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
        Scene menuMain = new Scene(menu);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(menuMain);
        window.show();
    }
    
    public void handleAddCustomer(ActionEvent event) throws IOException { 
        String name = customerNameField.getText();
        String address = addressField.getText();
        int cityID = city.getSelectionModel().getSelectedIndex();
        String zipCode = zipCodeField.getText();
        String phone = phoneField.getText();
         if (!validateName(name) || !validateAddress(address) || !validateCity(cityID) || !validatePostal(zipCode) || !validatePhone(phone)) {
           Alert alert = new Alert(Alert.AlertType.ERROR); 
           alert.setTitle("Oops");
           alert.setHeaderText("One or more form fields is not filled out.");
           alert.setContentText("Please fill out all form fields.");
           alert.showAndWait();
         } else {
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Add Customer Record?");
            alert.setHeaderText("Are you sure you want to add this record?");
            alert.setContentText("Click ok if you really want to add the customer record.");
            alert.showAndWait().ifPresent((new Consumer<ButtonType>() {
            @Override
            public void accept(ButtonType response) {
                if (response == ButtonType.OK) {
                    CustomerMGR.saveCustomer(name, address, cityID, zipCode, phone);
                    customerTable.setItems(CustomerMGR.getCustomers());
                }
            }
    }));
  }
       
}
    
    public void handleEditCustomer(ActionEvent event) throws IOException {
        String name = customerNameField.getText();
        String address = addressField.getText();
        int cityID = city.getSelectionModel().getSelectedIndex() + 1;
        String zipCode = zipCodeField.getText();
        String phone = phoneField.getText();
        customerSelection = customerTable.getSelectionModel().getSelectedItem();
        int id = customerSelection.getCustomerID();
        if (!validateName(name) || !validateAddress(address) || !validateCity(cityID) || !validatePostal(zipCode) || !validatePhone(phone)) {
           Alert alert = new Alert(Alert.AlertType.ERROR); 
           alert.setTitle("Oops");
           alert.setHeaderText("One or more form fields is not filled out.");
           alert.setContentText("Please fill out all form fields.");
           alert.showAndWait();
           
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modify Customer Record?");
            alert.setHeaderText("Are you sure you want to modify this record?");
            alert.setContentText("Click ok if you really want to modify the customer record. This action cannot be undone.");
            alert.showAndWait().ifPresent((new Consumer<ButtonType>() {
            @Override
            public void accept(ButtonType response) {
                if (response == ButtonType.OK) {
                    CustomerMGR.modifyCustomer(id, name, address, cityID, zipCode, phone);
                    customerTable.setItems(CustomerMGR.getCustomers());
                }
            }
        }));
        }
        
    }
    
     public void handleDeleteCustomer(ActionEvent event) throws IOException { 
        customerSelection = customerTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Customer Record?");
        alert.setHeaderText("This is destructive action! Are you sure you want to delete?");
        alert.setContentText("click ok if you really want to delete the customer record. This action cannot be undone.");
        alert.showAndWait().ifPresent((new Consumer<ButtonType>() {
            @Override
            public void accept(ButtonType response) {
                if (response == ButtonType.OK) {
                    CustomerMGR.deleteCustomer(customerSelection.getCustomerID());
                    customerTable.setItems(CustomerMGR.getCustomers());
                }
            }
        }));
     }
     
     public boolean validateAddress(String address) {
        if(address.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
     
     public boolean validateName(String name) {
        if(name.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
     
     public boolean validateCity(int city) {
        if(city == 0) {
            return false;
        } else {
            return true;
        }
    }
     
     public boolean validatePostal(String postal) {
        if(postal.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
     
     public boolean validatePhone(String phone) {
        if(phone.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        city.setItems(cityList);
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerPhone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerTable.setItems(CustomerMGR.getCustomers());
    }    
    
}
