/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author David Gentry
 */
public class Customer {
    private SimpleIntegerProperty customerID = new SimpleIntegerProperty();
    private SimpleStringProperty customerName = new SimpleStringProperty();
    private SimpleStringProperty customerAddress = new SimpleStringProperty();
    private SimpleStringProperty customerCity = new SimpleStringProperty();
    private SimpleStringProperty customerZipCode = new SimpleStringProperty();
    private SimpleStringProperty customerPhone = new SimpleStringProperty();
    private SimpleIntegerProperty customerCityID = new SimpleIntegerProperty();
    private SimpleIntegerProperty addressID = new SimpleIntegerProperty();
    
    
    public Customer() {}
    
    public Customer( int id, String name, String address, String city, String zipcode, String phone ) {
        setCustomerID(id);
        setCustomerName(name);
        setCustomerAddress(address);
        setCustomerCity(city);
        setCustomerZipCode(zipcode);
        setCustomerPhone(phone);
    }
    
     public Customer( int id, String name, String address, String city, String zipcode, String phone, int addressId ) {
        setCustomerID(id);
        setCustomerName(name);
        setCustomerAddress(address);
        setCustomerCity(city);
        setCustomerZipCode(zipcode);
        setCustomerPhone(phone);
        setAddressID(addressId);
    }
    
 
    //setters
    public void setCustomerID(int customerID) {
        this.customerID.set(customerID);
    }
    
    public void setAddressID(int addressID) {
        this.addressID.set(addressID);
    }
    
    public void setCustomerName(String customerName){
        this.customerName.set(customerName);
    }
    
    public void setCustomerAddress(String customerAddress){
        this.customerAddress.set(customerAddress);
      
    }
   
    public void setCustomerCity(String customerCity){
        this.customerCity.set(customerCity);
    }
  
    public void setCustomerZipCode(String customerZipCode){
        this.customerZipCode.set(customerZipCode);
    }
    
    public void setCustomerPhone(String customerPhone){
        this.customerPhone.set(customerPhone);
    }
    
    //getters
    public int getCustomerID() {
        return customerID.get();
    }
    
    public int getAddressID() {
        return addressID.get();
    }
    
    public String getCustomerName(){
        return customerName.get();
    }
    
    public String getCustomerAddress(){
        return customerAddress.get();
    }
    
    public String getCustomerCity(){
        return customerCity.get();
    }
    
    public int getCustomerCityID(){
        return customerCityID.get();
    }
    
    public String getCustomerZipCode(){
        return customerZipCode.get();
    }
    
    public String getCustomerPhone(){
        return customerPhone.get();
    }
}
