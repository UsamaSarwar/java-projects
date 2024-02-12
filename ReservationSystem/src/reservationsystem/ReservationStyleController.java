/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationsystem;
import java.util.*; 
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 *
 * @author Usama
 */
public class ReservationStyleController implements Initializable {

    @FXML
    private Circle b_exit;
    @FXML
    private Circle b_min;
    @FXML
    private TextField tf_username;
    @FXML
    private Button b_login;
    @FXML
    private ImageView i_login;
    @FXML
    private PasswordField pf_password;
    @FXML
    private Text t_message;
    @FXML
    private AnchorPane ap_login;
    @FXML
    private ImageView i_mainTicket;
    @FXML
    private ImageView i_s2;
    @FXML
    private ImageView i_s7;
    @FXML
    private ImageView i_s3;
    @FXML
    private ImageView i_s5;
    @FXML
    private ImageView i_s11;
    @FXML
    private ImageView i_s8;
    @FXML
    private ImageView i_s12;
    @FXML
    private ImageView i_s10;
    @FXML
    private ImageView i_s9;
    @FXML
    private ImageView i_s6;
    @FXML
    private ImageView i_s4;
    @FXML
    private ImageView i_s1;
    @FXML
    private Text t_seatsReservedLabel;
    @FXML
    private Text t_seatsReserved;
    @FXML
    private Text t_seatsAvailableLabel;
    @FXML
    private Text t_seatsRemaining;
    @FXML
    private AnchorPane ap_main;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ap_login.setVisible(true);
        ap_main.setVisible(false);
        // TODO
    }

    @FXML
    private void f_exit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void f_min(MouseEvent event) {
        ReservationSystem.stage.setIconified(true);
    }

    @FXML
    private void f_login(ActionEvent event) {

        if (tf_username.getText().equalsIgnoreCase("admin") && pf_password.getText().equals("admin")) {
            ap_login.setVisible(false);
            ap_main.setVisible(true);
        } else {
            t_message.setText("Wrong Credentials");
        }

    }

    @FXML
    private void f_2(MouseEvent event) {
    }

    @FXML
    private void f_7(MouseEvent event) {
    }

    @FXML
    private void f_3(MouseEvent event) {
    }

    @FXML
    private void f_5(MouseEvent event) {
    }

    @FXML
    private void f_11(MouseEvent event) {
    }

    @FXML
    private void f_8(MouseEvent event) {
    }

    @FXML
    private void f_12(MouseEvent event) {
    }

    @FXML
    private void f_10(MouseEvent event) {
    }

    @FXML
    private void f_9(MouseEvent event) {
    }

    @FXML
    private void f_6(MouseEvent event) {
    }

    @FXML
    private void f_4(MouseEvent event) {
    }

    @FXML
    private void f_1(MouseEvent event) {
        i_s1.setOpacity(0.5);
       // if()
        

    }
    void Reservation(){
        Vector seats = new Vector();
        for(int i=1;i<13;i++)
        seats.add(false);
    }
    boolean isReserved(){
        return false;
    }
    Ticket ticket1=new Ticket(true);
}

class Ticket {
    boolean reserved=false;
    Ticket(boolean reserved){
        this.reserved=reserved;
    }
}