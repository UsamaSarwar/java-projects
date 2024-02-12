package livecaption;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Usama
 */
public class LiveCaption extends Application {

    public static Stage stage = null;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Captions.fxml"));
        Scene scene = new Scene(root);
        LiveCaption.stage = stage;
        try {
            Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
            stage.getIcons().add(icon);
        } catch (Exception e) {
            System.out.println(e);
        }
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("");
        stage.setTitle("Handless v1.0.0");
        stage.initStyle(StageStyle.TRANSPARENT);
        //stage.setFullScreen(false);
        stage.alwaysOnTopProperty();

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
