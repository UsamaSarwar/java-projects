package elements;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ProgressBar pb_Progress;
    @FXML
    private Button b_signUp;
    @FXML
    private TextField tf_FirstName;
    @FXML
    private TextField tf_LastName;
    @FXML
    private TextField tf_Email;
    @FXML
    private TextField tf_ContactNumber;
    @FXML
    private TextField tf_homeAddress;
    @FXML
    private TextField tf_CNIC;
    @FXML
    private TextField tf_Gender;
    @FXML
    private Circle b_Exit;
    @FXML
    private ProgressIndicator pi_Progress;
    @FXML
    private Line line;
    @FXML
    private Text text1;
    @FXML
    private Text text11;
    @FXML
    private Text text111;
    @FXML
    private Text text112;
    @FXML
    private Text text113;
    @FXML
    private Text text1131;
    @FXML
    private Text text1132;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void f_SignUp(ActionEvent event) {
        pb_Progress.setProgress(1);
        pi_Progress.setProgress(1);
    }

    @FXML
    private void f_Exit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void b1(ActionEvent event) {
        text11.setText(tf_FirstName.getText());
        pb_Progress.setProgress(0.2);
        pi_Progress.setProgress(0.2);
    }
    @FXML
    private void b2(ActionEvent event) {
        text11.setText(tf_FirstName.getText()+" "+tf_LastName.getText());
        pb_Progress.setProgress(0.3);
        pi_Progress.setProgress(0.3);
    }
 
    @FXML 
    private void b3(ActionEvent event) {
        text111.setText(tf_CNIC.getText());
        pb_Progress.setProgress(0.5);
        pi_Progress.setProgress(0.5);
    }

    @FXML
    private void b4(ActionEvent event) {
        text113.setText(tf_Gender.getText());
        pb_Progress.setProgress(0.6);
        pi_Progress.setProgress(0.6);
    }
    @FXML
    private void b7(ActionEvent event) {
        text1132.setText(tf_homeAddress.getText());
        pb_Progress.setProgress(0.9);
        pi_Progress.setProgress(0.9);
    }
    @FXML
    private void b6(ActionEvent event) {
        text1131.setText(tf_ContactNumber.getText());
        pb_Progress.setProgress(0.8);
        pi_Progress.setProgress(0.8);
    }
    @FXML
    private void b5(ActionEvent event) {
        text112.setText(tf_Email.getText());
        pb_Progress.setProgress(0.7);
        pi_Progress.setProgress(0.7);
    }
    
}
