package notepad;

import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import com.gluonhq.charm.glisten.control.ProgressBar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 *
 * @author Usama
 */
public class NotePadController implements Initializable {
    
    @FXML
    private Text label;
    
//    String 
    @FXML
    private AutoCompleteTextField<?> username;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private Text close;
    @FXML
    private ProgressIndicator p1;
    @FXML
    private ProgressBar p2;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    

    @FXML
    private void login(ActionEvent event) throws InterruptedException {
        if (  ("".equals(username.getText()) || "".equals(password.getText())) ||  !username.getText().equals(password.getText())){
            label.setText("Invalid Username/Password");
        }else{
            username.setVisible(false);
            password.setVisible(false);
            login.setVisible(false);
            //Thread.sleep(1000);
            p1.setProgress(0.5);
            p2.setProgress(0.5);
            //Thread.sleep(1000);
            p1.setProgress(1);
            p2.setProgress(1);
            label.setText("Welcome to Technercia");
        }
    }

    @FXML
    private void close_exit(MouseEvent event) {
        System.exit(0);
    }
    
}
