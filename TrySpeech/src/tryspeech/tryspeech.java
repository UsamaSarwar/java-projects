package tryspeech;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;

import net.sourceforge.javaflacencoder.FLACFileWriter;

public class tryspeech implements GSpeechResponseListener {

    //public static StringBuilder str1 = new StringBuilder(" "); //temp
    //static String str2; // temp
    public static void main(String[] args) throws IOException {
        final Microphone mic = new Microphone(FLACFileWriter.FLAC);

        GSpeechDuplex duplex = new GSpeechDuplex("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");

        duplex.setLanguage("en");
        //duplex.setLanguage("ar");

        JFrame frame = new JFrame("AudoPad v1.0");
        frame.setDefaultCloseOperation(3);
        JTextArea response = new JTextArea();
        response.setEditable(false);
        response.setWrapStyleWord(true);
        response.setLineWrap(true);

        final JButton record = new JButton("Arabic");
        final JButton stop = new JButton("Stop");
        stop.setEnabled(false);
// Starting Code here
        new Thread(() -> {
            try {
                duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }).start();

        record.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new Thread(() -> {
                    try {
                        duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }).start();

                record.setEnabled(false);
                stop.setEnabled(true);
            }
        });

        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                mic.close();
                duplex.stopSpeechRecognition();
                record.setEnabled(true);
                stop.setEnabled(false);
                //response.setText(str1.toString()); //temp
            }
        });

        JScrollPane scroll = new JScrollPane(response);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), 1));
        frame.getContentPane().add(scroll);
        JPanel recordBar = new JPanel();
        frame.getContentPane().add(recordBar);
        recordBar.setLayout(new BoxLayout(recordBar, 0));
        record.setVisible(false);
        stop.setVisible(false);
        recordBar.add(record);
        recordBar.add(stop);
        frame.setVisible(true);
        frame.pack();
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        duplex.addResponseListener(new GSpeechResponseListener() {
            String old_text = "";

            @Override
            public void onResponse(GoogleResponse gr) {
                String output = "";
                output = gr.getResponse();
                if (gr.getResponse() == null) {
                    this.old_text = response.getText();
                    if (this.old_text.contains("(")) {
                        this.old_text = this.old_text.substring(0, this.old_text.indexOf('('));
                    }
                    System.out.println("Paragraph Line Added");
                    this.old_text = (response.getText() + "\n");
                    this.old_text = this.old_text.replace(")", "").replace("( ", "");
                    response.setText(this.old_text);
                    return;
                }
                if (output.contains("(")) {
                    output = output.substring(0, output.indexOf('('));
                }
                if (!gr.getOtherPossibleResponses().isEmpty()) {
                    output = output + " (" + (String) gr.getOtherPossibleResponses().get(0) + ")";
                }
                System.out.println(this.old_text);
                /*
                  str1.append(output).append(" ");
                  
                 */
                //str1.append(this.old_text+" ");
                System.out.println(this.old_text);
                response.setText("");
                response.append(this.old_text);
                response.append(output);

            }
        });
    }

    @Override
    public void onResponse(GoogleResponse paramGoogleResponse) {
        // TODO Auto-generated method stub

    }
}
