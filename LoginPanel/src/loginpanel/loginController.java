package loginpanel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.ini4j.Wini;

public class loginController implements Initializable {

    @FXML
    private TextField txt_username;
    @FXML
    private PasswordField txt_password;
    @FXML
    private CheckBox rememberMe;
    @FXML
    private Text label_AdminLogin;
    @FXML
    private Button button_login;
    @FXML
    private Label txt_signUp;
    @FXML
    private Label txt_failSignIn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txt_signUp.setText(" ");
    }

    @FXML
    private void function_LoginButton(ActionEvent event) throws IOException {

        File file = new File("./Encrypted.ini"); 
        Wini wini = new Wini(new File("./Encrypted.ini"));
        if (txt_username.getText() == null ? wini.get("Login", "Username") == null : txt_username.getText().equals(wini.get("Login", "Username")) && (txt_password.getText() == null ? wini.get("Login", "Passowrd") == null : txt_password.getText().equals(wini.get("Login", "Passowrd")))) {
            txt_signUp.setText("Login Successfully!");
        } else {
            txt_failSignIn.setText("Invalid Credentials!");
            txt_signUp.setText("SignUp here");
        }

    }

    @FXML
    private void function_signUp(MouseEvent event) {
        if (rememberMe.isSelected()) {
            try {
                File file = new File("./Encrypted.ini");
                if (!file.exists()) {
                    file.createNewFile();
                }
                Wini wini = new Wini(new File("./Encrypted.ini"));
                wini.put("Login", "Username", txt_username.getText());
                wini.put("Login", "Password", txt_password.getText());
                wini.store();
                txt_signUp.setText("SignUp Successfully!");
                txt_username.setText("");
                txt_password.setText("");
            } catch (IOException e) {
                txt_signUp.setText(e.toString());
            }
        }

    }

    public void CreatingIniFile() {
        try {
            File file = new File("./Encrypted.ini");
            if (!file.exists()) {
                file.createNewFile();
            }
            Wini wini = new Wini(new File("./Encrypted.ini"));
            wini.put("Users", "Username", txt_username.getText());

            wini.put("Passwords", "Password", txt_password.getText());
            wini.store();
        } catch (IOException e) {
            txt_signUp.setText(e.toString());
        }
    }
}
