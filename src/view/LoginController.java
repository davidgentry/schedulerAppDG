/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.UserMGR;
import utilities.LoggerUtility;

/**
 * FXML Controller class
 *
 * @author David Gentry
 */


public class LoginController implements Initializable {
    
@FXML
private TextField usernameField;

@FXML 
private PasswordField passwordField;

@FXML
private Label userLabel;

@FXML
private Label pwdLabel;

@FXML
private Label errorLabel;

@FXML
private Label messageLabel;

@FXML
private Label title;

@FXML
private Button loginButton;

private String alertTitle;
private String alertText;

private final static Logger LOGGER = Logger.getLogger(LoggerUtility.class.getName());
    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void handleLogin(ActionEvent event) throws IOException { 
        //validate login
        String user = usernameField.getText();
        String pass = passwordField.getText();
        System.out.println(user);
        System.out.println(pass);
        boolean valid = UserMGR.login(user, pass);
        //if login is valid go to main menu
        if (valid) {
           LOGGER.log(Level.INFO, "{0} logged in", user);
           Parent menu = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
            Scene menuMain = new Scene(menu);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(menuMain);
            window.show();
       } else {
            Alert incorrect = new Alert(Alert.AlertType.ERROR);
            incorrect.setTitle(alertTitle);
            incorrect.setHeaderText(alertText);
            incorrect.showAndWait();
        }   
        //else show error message
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        //add locale lookup info here
        Locale locale = Locale.getDefault();
        rb = ResourceBundle.getBundle("utilities/login", locale);
        userLabel.setText(rb.getString("user"));
        pwdLabel.setText(rb.getString("pwd"));
        loginButton.setText(rb.getString("login"));
        title.setText(rb.getString("title"));
        alertTitle = rb.getString("alertTitle");
        alertText = rb.getString("alertText");
    }     
}
