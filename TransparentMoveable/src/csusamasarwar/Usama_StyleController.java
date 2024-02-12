package csusamasarwar;

import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import com.gluonhq.charm.glisten.control.ProgressBar;
import com.gluonhq.charm.glisten.control.ProgressIndicator;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Usama_StyleController implements Initializable {

    @FXML
    private ProgressBar progress;
    @FXML
    private ProgressIndicator progressinc;
    @FXML
    private Text t_message;
    @FXML
    private AnchorPane anchorPane;
    
    // Drag Code here
    private double xOffset=0;
    private double yOffset=0;
    @FXML
    private Button b_user;
    @FXML
    private TextField tf_user;
    @FXML
    private PasswordField pf_password;
    @FXML
    private ImageView i_logo_login;
    @FXML
    private ImageView i_logo;
    @FXML
    private Text text_Bottom;
    @FXML
    private Button b_SignUp;
    
    private void makeStageDragable(){
        anchorPane.setOnMousePressed((event) -> {
            xOffset=event.getSceneX();
            yOffset=event.getSceneY();
        });
        anchorPane.setOnMouseDragged((event) -> {
            CsUsamaSarwar.stage.setX(event.getScreenX()-xOffset);
            CsUsamaSarwar.stage.setY(event.getScreenY()-yOffset);
            CsUsamaSarwar.stage.setOpacity(0.8f);
        });
        anchorPane.setOnDragDone((event) -> {
            CsUsamaSarwar.stage.setOpacity(1.0f);
        });
        anchorPane.setOnMouseReleased((event) -> {
            CsUsamaSarwar.stage.setOpacity(1.0f);
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        makeStageDragable();
        i_logo.setVisible(false);
        b_SignUp.setVisible(false);
    }

    // Function for Exit
    @FXML
    private void f_exit(MouseEvent event) {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();        
        stage.close();
        
    }
    
    // Function for Minimize
    @FXML
    private void f_min(MouseEvent event) {
        // Importing Stage Method
        //Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        CsUsamaSarwar.stage.setIconified(true);
    }

    // Function for Full Screen
    @FXML
    private void f_max(MouseEvent event) {
        //Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        //stage.setFullScreen(true);
        CsUsamaSarwar.stage.setFullScreen(true);
    }

    @FXML
    private void f_userButton(ActionEvent event) {
        login();
    }
    // After Login
    private void Main(){
        t_message.setText(tf_user.getText());
        i_logo.setVisible(true);
    }
    
    String real_Username="admin";
    String real_Pssword="admin";
    // Login Screen
    private void login(){
        // Database Login 
        
        // Program Login
        if (tf_user.getText().equalsIgnoreCase(real_Username)){
            if(pf_password.getText().equals(real_Pssword)){
                Main();
                tf_user.setVisible(false);
                pf_password.setVisible(false);
                i_logo_login.setVisible(false);
                
            }else{
                t_message.setText("Wrong Credentials...");
            }
        }else{
            t_message.setText("User doesn't exist...");
            b_SignUp.setVisible(true);
        }
    }

    @FXML
    private void f_SignUp(MouseEvent event) {
        b_SignUp.setVisible(true);
    }

    @FXML
    private void f_SignUpCompleted(ActionEvent event) {
        b_SignUp.setVisible(false);
        t_message.setVisible(false);
        text_Bottom.setText("User Successfully Created\nLogin Now");
        try {
            File f1= new File("./Database.txt");
            f1.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(f1));
            writer.write("\n");
            writer.append(tf_user.getText()+","+ pf_password.getText()+"\n");
            writer.close();
        } catch (IOException e) {
            text_Bottom.setText(String.valueOf(e));
        }
    }
    
}
