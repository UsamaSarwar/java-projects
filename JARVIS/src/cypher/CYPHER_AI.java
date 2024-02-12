package cypher;
// Importing Libraries
import java.sql.*; 
import java.net.URL;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.geometry.Pos;
import javafx.util.Duration;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import org.controlsfx.control.Notifications;
import javax.sound.sampled.LineUnavailableException;
import com.darkprograms.speech.microphone.Microphone;
import net.sourceforge.javaflacencoder.FLACFileWriter;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GoogleResponse;
import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CYPHER_AI implements GSpeechResponseListener, Initializable {
    // Database Connections
    Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    // Microphone & Duplex Initialization
    final Microphone mic = new Microphone(FLACFileWriter.FLAC);
    GSpeechDuplex duplex = new GSpeechDuplex("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
    // Speak Variable
    String output = "";
    // Check Variables
    boolean start=true;
    
    @FXML
    private TextArea screen;
    @FXML
    private TextField listen;
    @FXML
    private TextField speak;
    @FXML
    private Button submit;
    @FXML
    private TextArea speaking;
    @FXML
    private Text ready;
    
    public void handleButtonAction(ActionEvent event) {
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Language of Speech
        duplex.setLanguage("en");
        // Speech Recognition
        new Thread(() -> {  // Thread 1
            try {
                duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
            } catch (InterruptedException | LineUnavailableException ex) {
            }
        }).start();
        // Listening Method Calling
        new Thread(() -> {  //Thread 2
            try {    
            Listen();
             } catch (IOException ex) {
                System.out.println(ex);            
            }
            }).start();
    }
    @Override
    public void onResponse(GoogleResponse paramGoogleResponse) {
            // TODO Auto-generated method stub
    }
    public void Listen() throws IOException{
        duplex.addResponseListener(new GSpeechResponseListener() {
        String old_text = "";
        // Response of Speech Code
        @Override
        public void onResponse(GoogleResponse gr) {
            try {
                // Database Connection Starting...
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://technercia.com:3306/technerc_JARVIS_DB","technerc_JARVIS","JARVIS@404");
            } catch (ClassNotFoundException  | SQLException ex) {
                //System.out.println("Database Error: "+ex);
                Notifications dbnoti= Notifications.create()
                    .title("DATABASE Connection Error")
                    .text(ex.toString())
                    .position(Pos.TOP_CENTER)
                    //.hideCloseButton()
                    .darkStyle()
                    .graphic(null)
                    .hideAfter(Duration.seconds(20));
                    Platform.runLater(() -> {
                        dbnoti.showWarning();
                    });
            }   
            //output
            output = gr.getResponse();
            
            if (gr.getResponse() == null) {
                this.old_text = screen.getText();
                if (this.old_text.contains("(")) {
                    this.old_text = this.old_text.substring(0, this.old_text.indexOf('('));
                }
                System.out.println("Paragraph Line Added");
                this.old_text = ( screen.getText() + "\n" );
                this.old_text = this.old_text.replace(")", "").replace("( ", "");
                screen.setText(this.old_text);
                return;
            }
            if (output.contains("(")) {
                output = output.substring(0, output.indexOf('('));
            }
            if (!gr.getOtherPossibleResponses().isEmpty()) {
                output = output + " (" + (String) gr.getOtherPossibleResponses().get(0) + ")";
            }
            // Console Output
            //System.out.println(output);   // temp
            ready.setText("Cypher is online now...");
            if (output.contains("ok")){
                if (output.contains("shut down")){
                    System.exit(0);
                }
            }
            if (output.contains("launch cypher")){
                try {
                    Runtime.getRuntime().exec("C:\\Users\\Usama\\OneDrive\\Desktop\\run.bat", null, new File("C:\\Users\\Usama\\OneDrive\\Desktop"));
                } catch (IOException ex) {
                    Logger.getLogger(CYPHER_AI.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
//                
//                Notifications noti= Notifications.create()
//                        .title("JARVIS says...")
//                        .text("I love you")
//                        .position(Pos.TOP_CENTER)
//                        .hideCloseButton()
//                        .graphic(null)
//                        .hideAfter(Duration.seconds(10));
//                Platform.runLater(() -> {
//                    noti.show();
//                });
            }
            screen.setText("");
            screen.appendText(this.old_text);
            screen.appendText(output);
//            new Thread(() -> {  //Thread 3



                try {
                    
            String sql="SELECT SPEAK FROM JARVIS_AI_Responses where LISTEN LIKE '"+output+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next())
            //speaking.setText(rs.getString(1));
            //speaking.appendText("");
            //speaking.appendText(this.old_text);
            speaking.appendText(rs.getString(1)+"\n");


            } catch (SQLException e) {
                Notifications exicutenoti= Notifications.create()
                            .title("DATABASE Error!")
                            .text(e.toString())
                            .position(Pos.TOP_CENTER)
                            .hideCloseButton()
                            .graphic(null)
                            .hideAfter(Duration.seconds(10));
                    Platform.runLater(() -> {
                        exicutenoti.showError();
                    });
            }
         //       }).start();



            System.out.println(output);
//          Stop Code
            //mic.close();
//    Stop Code
//    mic.close();
//    duplex.stopSpeechRecognition();
            }
        });
    }

    @FXML
    private void SubmitAction(ActionEvent event) {
        String tempListen,tempSpeak;
            tempListen=listen.getText();
            tempSpeak=speak.getText();
        new Thread(() -> {
            
           if (tempListen != null || tempSpeak != null){
                try {
                    String sql1="INSERT INTO JARVIS_AI_Responses (`LISTEN`, `SPEAK`) VALUES (?,?)";     // Database insertion query
                        pst=con.prepareStatement(sql1);
                        pst.setString(1,listen.getText());
                        pst.setString(2,speak.getText());
                        pst.executeUpdate();
                        
                        Notifications exicutenoti= Notifications.create()
                                    .title("JARVIS Says...")
                                    .text("Data Successfully Updated into the DataBases")
                                    .position(Pos.TOP_CENTER)
                                    .hideCloseButton()
                                    .graphic(null)
                                    .hideAfter(Duration.seconds(10));
                            Platform.runLater(() -> {
                                exicutenoti.showInformation();
                            });
                        
                    } catch (SQLException e) {
                        Notifications exicutenoti= Notifications.create()
                                    .title("JARVIS Says...")
                                    .text(e.toString())
                                    .position(Pos.TOP_CENTER)
                                    .hideCloseButton()
                                    .graphic(null)
                                    .hideAfter(Duration.seconds(10));
                            Platform.runLater(() -> {
                                exicutenoti.showError();
                            });
            }
        }else{
            Notifications exicutenoti= Notifications.create()
                    .title("JARVIS Says...")
                    .text("Complete all the Fields before submit")
                    .position(Pos.TOP_CENTER)
                    .hideCloseButton()
                    .graphic(null)
                    .hideAfter(Duration.seconds(10));
            Platform.runLater(() -> {
                exicutenoti.showWarning();
            });
        }
           
        }).start();
        
    }
}
