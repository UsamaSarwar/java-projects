package coremanagement;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;

public class CPU_Management implements Initializable {

    @FXML
    private ChoiceBox cb_core;
    @FXML
    private Button b_run;
    @FXML
    private Text t_message;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cb_core.getItems().add("CPU 0");
        cb_core.getItems().add("CPU 1");
    }

    @FXML
    private void f_run(ActionEvent event) {
        String core = (String) cb_core.getValue();
        if (null == core) {
            t_message.setText("Select CPU???");
        } else {
            switch (core) {
                case "CPU 0":
                    run1();
                    t_message.setText("CPU 0 in use");
                    break;
                case "CPU 1":
                    run2();
                    t_message.setText("CPU 1 in use");
                    break;
                default:
                    t_message.setText("Select CPU!");
                    break;
            }
        }
    }

    public synchronized void run1() {
        Runtime rt = Runtime.getRuntime();

        try {
            // C:\Users\Usama\OneDrive\Desktop
            //rt.exec(new String[]{"cmd.exe", "/c", "start /affinity 1 notepad.exe "});
            rt.exec(new String[]{"cmd.exe", "/c", "start /affinity 1 C:\\Users\\Usama\\OneDrive\\Desktop\\Fiverr.txt "});
        } catch (IOException e) {
            t_message.setText(e.toString());
        }
    }

    public synchronized void run2() {
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(new String[]{"cmd.exe", "/c", "start /affinity 2 notepad.exe"});
        } catch (IOException e) {
            t_message.setText(e.toString());
        }
    }
}
