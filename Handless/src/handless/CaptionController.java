/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handless;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Usama
 */
public class CaptionController implements Initializable {

    // Drag Code here
    private double xOffset = 0;
    private double yOffset = 0;
    
    @FXML
    private AnchorPane primaryStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Dragable Stage...
        makeStageDragable();
        // TODO
    }

//    private void back(ActionEvent event) throws IOException {
//        Parent pane = FXMLLoader.load(getClass().getResource("Handless.fxml"));
//        primaryStage.getScene().setRoot(pane);
//    }
    
    private void makeStageDragable(){
        primaryStage.setOnMousePressed((event) -> {
            xOffset=event.getSceneX();
            yOffset=event.getSceneY();
        });
        primaryStage.setOnMouseDragged((event) -> {
            Handless.stage.setX(event.getScreenX()-xOffset);
            Handless.stage.setY(event.getScreenY()-yOffset);
            Handless.stage.setOpacity(0.8f);
        });
        primaryStage.setOnDragDone((event) -> {
            Handless.stage.setOpacity(1.0f);
        });
        primaryStage.setOnMouseReleased((event) -> {
            Handless.stage.setOpacity(1.0f);
        });
    }

    @FXML
    private void back(MouseEvent event) {
    }

}
