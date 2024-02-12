package audiopadbeta;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import net.sourceforge.javaflacencoder.FLACFileWriter;

public class FXMLDocumentController implements Initializable, GSpeechResponseListener {

    @FXML
    private TextArea audiopad;
    @FXML
    private Circle B_record;
    @FXML
    private Circle B_stop;
    @FXML
    private TextArea typing;

    private String Pad;

    final Microphone mic = new Microphone(FLACFileWriter.FLAC);
    final GSpeechDuplex duplex = new GSpeechDuplex("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        B_stop.setVisible(false);
        duplex.setLanguage("en");
        //duplex.setLanguage("ar");
        duplex.addResponseListener(new GSpeechResponseListener() {
            String old_text = "";
            int num = 0;

            @Override
            public void onResponse(GoogleResponse gr) {
                String output = "";
                output = gr.getResponse();
                if (output.contains("exit")) {
                    System.exit(0);
                }
                if (gr.getResponse() == null) {
                    this.old_text = audiopad.getText();
                    if (this.old_text.contains("(")) {
                        this.old_text = this.old_text.substring(0, this.old_text.indexOf('('));
                    }
                    System.out.println("Paragraph Line Added");
                    this.old_text = (audiopad.getText() + "\n");
                    this.old_text = this.old_text.replace(")", "").replace("( ", "");
                    audiopad.setText(this.old_text);
                    return;
                }
                if (output.contains("(")) {
                    output = output.substring(0, output.indexOf('('));
                }
                if (!gr.getOtherPossibleResponses().isEmpty()) {
                    output = output + " (" + gr.getOtherPossibleResponses().get(0) + ")";
                }
                audiopad.setText("");
                audiopad.appendText(this.old_text);
                audiopad.appendText(output);
            }
        });
    }

    @FXML
    private void record(MouseEvent event) {
        new Thread(() -> {
            try {
                duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
        B_record.setVisible(false);
        B_stop.setVisible(true);
    }

    @FXML
    private void stop(MouseEvent event) {
        mic.close();
        duplex.stopSpeechRecognition();
        B_record.setVisible(true);
        B_stop.setVisible(false);
    }

    @Override
    public void onResponse(GoogleResponse gr) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
