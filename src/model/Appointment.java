package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author David Gentry
 */
public class Appointment {
    private SimpleIntegerProperty appointmentID = new SimpleIntegerProperty();
    private SimpleIntegerProperty customerID = new SimpleIntegerProperty();
    private SimpleStringProperty appointmentStart = new SimpleStringProperty();
    private SimpleStringProperty appointmentEnd = new SimpleStringProperty();
    private SimpleStringProperty appointmentTitle = new SimpleStringProperty();
    private SimpleStringProperty appointmentDescription = new SimpleStringProperty();
    private SimpleStringProperty appointmentLocation = new SimpleStringProperty();
    private SimpleStringProperty appointmentContact = new SimpleStringProperty();   
    
    public Appointment() {}
    public Appointment(int id, int customerId, String start, String end, String title, String description, String location, String contact) {
        setAppointmentId(id);
        setAppointmentCustId(customerId);
        setAppointmentStart(start);
        setAppointmentEnd(end);
        setAppointmentTitle(title);
        setAppointmentDescription(description);
        setAppointmentLocation(location);
        setAppointmentContact(contact);
    }
    //setters
    public void setAppointmentId(int appointmentId) {
        this.appointmentID.set(appointmentId);
    }
    
    public void setAppointmentCustId(int appointmentCustId) {
        this.customerID.set(appointmentCustId);
    }
    
    public void setAppointmentEnd(String appointmentEnd) {
        this.appointmentEnd.set(appointmentEnd);
    }
    
    public void setAppointmentStart(String appointmentTimeStart) {
        this.appointmentStart.set(appointmentTimeStart);
    } 
    
    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle.set(appointmentTitle);
    }
    
    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription.set(appointmentDescription);
    }
    
    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation.set(appointmentLocation);
    }
    
    public void setAppointmentContact(String appointmentContact) {
        this.appointmentContact.set(appointmentContact);
    }
    
    //getters
    public int getAppointmentID() {
        return appointmentID.get();
    }
    
    public IntegerProperty getAppointmentIdProp() {
        return this.appointmentID;  
    }
    
    
    public int getCustomerID(){
        return customerID.get();
    }
    
    public String getAppointmentStart() {
        return appointmentStart.get();
    }
    
    public StringProperty getAppointmentStartProp() {
        return this.appointmentStart;
    }
    
    public String getAppointmentEnd() {
        return appointmentEnd.get();
    }
    
    public StringProperty getAppointmentEndProp() {
        return this.appointmentEnd;
    }
    
    public String getAppointmentTitle() {
        return appointmentTitle.get();
    }
    
    
    public String getAppointmentDescription() {
        return appointmentDescription.get();
    }
    
    public StringProperty getAppointmentDescriptionProp() {
        return this.appointmentDescription;
    }
    
    public String getAppointmentLocation() {
        return appointmentLocation.get();
    }
    
    public StringProperty getAppointmentLocationProp() {
        return this.appointmentLocation;
    }
    
    public String getAppointmentContact() {
        return appointmentContact.get();
    }
    
    public StringProperty getAppointmentContactProp() {
        return this.appointmentContact;
    }
    
    
    
    
    
}

 