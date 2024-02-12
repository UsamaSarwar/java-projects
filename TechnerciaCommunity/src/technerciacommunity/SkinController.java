package technerciacommunity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class SkinController implements Initializable {

    @FXML
    private WebView Technercia_View;
    @FXML
    private Circle bulb;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        run();

    }

    void run() {

        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            WebEngine engine = Technercia_View.getEngine();
            engine.load("http://www.technercia.net");
            //bulb.setFill(Paint.valueOf("#00ff00"));
            bulb.setFill(Paint.valueOf("#ff0000"));
            
        } catch (MalformedURLException e) {
            bulb.setFill(Paint.valueOf("#ff0000"));
        } catch (IOException e) {
            bulb.setFill(Paint.valueOf("#ff00000"));
        }catch(Exception e){
            bulb.setFill(Paint.valueOf("#ff00000"));
        }

    }
}
