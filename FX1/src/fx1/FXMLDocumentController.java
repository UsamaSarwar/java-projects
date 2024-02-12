package fx1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Button b_login;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;
    @FXML
    private Text t_message;
    @FXML
    private Text t_welcome;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void f_login(ActionEvent event) {
        String username = "admin";
        String password = "admin";
        if (username.equalsIgnoreCase(tf_username.getText()) && password.equals(pf_password.getText())) {
            b_login.setVisible(false);
            tf_username.setVisible(false);
            pf_password.setVisible(false);
            t_message.setVisible(false);
            t_welcome.setText("Welcome!");
        } else {
            t_message.setText("Wrong Credentials, Try Again...");
        }
    }

}
