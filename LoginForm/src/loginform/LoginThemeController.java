/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginform;

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

/**
 *
 * @author Usama
 */
public class LoginThemeController implements Initializable {

    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;
    @FXML
    private Button b_login;
    @FXML
    private Text t_login;
    @FXML
    private Text t_username;

    void LoginShow(boolean show) {
        b_login.setVisible(show);
        tf_username.setVisible(show);
        pf_password.setVisible(show);
        t_login.setVisible(show);
    }

    @FXML
    private void f_login(ActionEvent event) {
        if (null == tf_username.getText() ? pf_password.getText() == null : tf_username.getText().equals(pf_password.getText())) {
            LoginShow(false);
            t_username.setText("👤 " + tf_username.getText());
        } else {
            t_login.setText("⚠ Wrong Credentials");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
