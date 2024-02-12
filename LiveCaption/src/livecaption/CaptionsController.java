package livecaption;

import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import net.sourceforge.javaflacencoder.FLACFileWriter;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Usama
 */
public class CaptionsController implements Initializable, GSpeechResponseListener {

    // Constructing Microphone
    final Microphone mic = new Microphone(FLACFileWriter.FLAC);
    final GSpeechDuplex duplex = new GSpeechDuplex("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
    // Drag Code here
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private AnchorPane primaryStage;
    @FXML
    private Text caption;
    @FXML
    private Circle Drag;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Custom Dragging...
        try {
            makeStageDragable();
        } catch (Exception e) {
            Notify("Error", e.toString());
        }

        // Selecting Language
        duplex.setLanguage("en");

        // Start Recognition
      
            try {
                duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
            } catch (Exception ex) {
                Notify("Error", ex.toString());
            }
    

        // Initiating Voice Recognition
        duplex.addResponseListener((GoogleResponse gr) -> {

            // Thread for Output Recognition
          
                try {
                    caption.setText(gr.getResponse());
                } catch (Exception e) {
                    caption.setText(String.valueOf(e));
                }
         

            // Custom Commands
//            new Thread(() -> {
//                if (gr.getResponse().contains("ok")) {
//                    if (gr.getResponse().contains("goodbye") || gr.getResponse().contains("good bye")) {
//                        System.exit(0);
//                    } else if (gr.getResponse().contains("switch to light")) {
//                        LightTheme();
//                        Notify("LiveCaption v1.0", "Light Theme Activated:)");
//                    } else if (gr.getResponse().contains("switch to dark")) {
//                        DarkTheme();
//                        Notify("LiveCaption v1.0", "Dark Theme Activated");
//                    } else if (gr.getResponse().contains("are you listening me") || gr.getResponse().contains("are you there")) {
//                        Notify("LiveCaption v1.0", "Yes! I'm listening... :)");
//                    }
//                    // text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50)); 
//                }
//            }).start();

//            new Thread(() -> {
//                if (gr.getResponse().contains("activate devices")) {
//                    Runtime rt = Runtime.getRuntime();
//                    try {
//                        rt.exec(new String[]{"cmd.exe", "/c", " %windir%\\Speech\\Common\\sapisvr.exe -SpeechUX"});
//                        //start /affinity 2
//                    } catch (IOException e) {
//                        caption.setText(e.toString());
//                    }
//                }
//            }).start();
//
//            new Thread(() -> {
//                if (gr.getResponse().contains("deactivate devices")) {
//                    Runtime rt = Runtime.getRuntime();
//                    try {
//                        rt.exec(new String[]{"cmd.exe", "/c", "start  e://Deactivator.exe"});
//                    } catch (IOException e) {
//                        caption.setText(e.toString());
//                    }
//                }
//            }).start();
        });
    }
    // Method for Dragable Stage
    private void makeStageDragable() {
        primaryStage.setOnMousePressed((event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        primaryStage.setOnMouseDragged((event) -> {
            LiveCaption.stage.setX(event.getScreenX() - xOffset);
            LiveCaption.stage.setY(event.getScreenY() - yOffset);
            LiveCaption.stage.setOpacity(0.8f);
        });
        primaryStage.setOnDragDone((event) -> {
            LiveCaption.stage.setOpacity(1.0f);
        });
        primaryStage.setOnMouseReleased((event) -> {
            LiveCaption.stage.setOpacity(1.0f);
        });
    }
    // Method for notification System
    void Notify(String Title, String Message) {
        Notifications notification = Notifications.create()
                .title(Title)
                .text(Message)
                .position(Pos.BOTTOM_RIGHT)
                .hideCloseButton()
                //.darkStyle()
                .graphic(null)
                .hideAfter(Duration.seconds(3));
        Platform.runLater(() -> {
            notification.show();
        });
    }
    void DarkTheme() {
        caption.setFill(Color.BLACK);
    }
    void LightTheme() {
        caption.setFill(Color.WHITE);
    }
    @Override
    public void onResponse(GoogleResponse gr) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void exit(MouseEvent event) {
        System.exit(0);
    }
}
