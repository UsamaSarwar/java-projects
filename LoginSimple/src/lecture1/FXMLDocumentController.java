/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecture1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Usama
 */
public class FXMLDocumentController implements Initializable {
    
 
    private Label name;
    private TextField tf_name;
    @FXML
    private Button login;
    @FXML
    private Label info;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void Login(ActionEvent event) {
        if ("admin".equalsIgnoreCase(username.getText()) && "admin".equals(password.getText())){
           username.setVisible(false);
           password.setVisible(false);
           login.setVisible(false);
            info.setText("Loged in Successfully!");
        }else{
            info.setText("Try Again/ Wrong Credentials");
        }
    }
}
