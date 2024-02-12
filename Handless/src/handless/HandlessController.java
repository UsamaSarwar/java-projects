package handless;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;
import java.io.File;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import net.sourceforge.javaflacencoder.FLACFileWriter;
import org.controlsfx.control.Notifications;
import org.ini4j.Wini;

public class HandlessController implements Initializable, GSpeechResponseListener {

    final Microphone mic = new Microphone(FLACFileWriter.FLAC);
    final GSpeechDuplex duplex = new GSpeechDuplex("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
    // Drag Code here
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Text message;
    private TextField tf_listen;
    private TextField tf_response;
    private AnchorPane primaryStage;

    private void handleButtonAction(ActionEvent event) {

        try {
            File file = new File("./Encrypted.ini");
            if (!file.exists()) {
                file.createNewFile();
            }
            Wini wini = new Wini(new File("./Encrypted.ini"));
            if (tf_listen.getText().isEmpty() || tf_response.getText().isEmpty()) {
                Notify("Complete Fileds");
            } else {
                wini.put("DB", tf_listen.getText(), tf_response.getText());
            }
            wini.store();
        } catch (IOException e) {
            Notify("Exception: " + e.toString());
        }

        tf_listen.setText(null);
        tf_response.setText(null);
    }

    private void function_LiveCaption(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("Caption.fxml"));
        primaryStage.getScene().setRoot(pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Dragable Stage...
        makeStageDragable();
        // Start Listening...
        new Thread(() -> {
            try {
                duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }).start();

        // Selecting Language
        duplex.setLanguage("en");
        //duplex.setLanguage("ar");

        duplex.addResponseListener((GoogleResponse gr) -> {
            new Thread(() -> {
                try {
                    message.setText(gr.getResponse());
                } catch (Exception e) {
                    message.setText(String.valueOf(e));
                }
            }).start();
            

            new Thread(() -> {
                if (gr.getResponse().contains("exit")) {
                    System.exit(0);
                }
            }).start();
            
            new Thread(() -> {
                if (gr.getResponse().contains("hello")) {
                    Notify("Hello! What's Up?");
                }
            }).start();

            /*
            new Thread(() -> {
            try {
            File file = new File("./Encrypted.ini");
            if (!file.exists()) {
            file.createNewFile();
            }
            Wini wini = new Wini(new File("./Encrypted.ini"));
            response.setText(wini.get("DB", gr.getResponse()));
            } catch (IOException e) {
            message.setText(e.toString());
            }
            
            }).start();
             */
            new Thread(() -> {
                if (gr.getResponse().equalsIgnoreCase("activate input devices")) {
                    Runtime rt = Runtime.getRuntime();
                    try {
                        rt.exec(new String[]{"cmd.exe", "/c", "%windir%\\Speech\\Common\\sapisvr.exe -SpeechUX"});
                    } catch (IOException e) {
                        message.setText(e.toString());
                    }
                }
            }).start();
        }
        );
    }

    @Override
    public void onResponse(GoogleResponse gr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void makeStageDragable() {
        primaryStage.setOnMousePressed((event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        primaryStage.setOnMouseDragged((event) -> {
            Handless.stage.setX(event.getScreenX() - xOffset);
            Handless.stage.setY(event.getScreenY() - yOffset);
            Handless.stage.setOpacity(0.8f);
        });
        primaryStage.setOnDragDone((event) -> {
            Handless.stage.setOpacity(1.0f);
        });
        primaryStage.setOnMouseReleased((event) -> {
            Handless.stage.setOpacity(1.0f);
        });
    }

    void Notify(String Output) {
        Notifications notification = Notifications.create()
                .title("Message")
                .text(Output)
                .position(Pos.BOTTOM_RIGHT)
                .hideCloseButton()
                .darkStyle()
                .graphic(null)
                .hideAfter(Duration.seconds(4));
        Platform.runLater(() -> {
            notification.showWarning();
        });
    }

}

/*
    // Notification Code
    Notifications notification = Notifications.create()
        .title("Title")
        .text("Message")
        .position(Pos.TOP_CENTER)
        .hideCloseButton()
        .darkStyle()
        .graphic(null)
        .hideAfter(Duration.seconds(4));
        Platform.runLater(() -> {
        notification.showWarning();
    });
 */

 /*
    // Stop Code
        mic.close();
        duplex.stopSpeechRecognition();
 */
