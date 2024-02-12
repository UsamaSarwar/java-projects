package Layouts;

import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashBoard1 extends Application {
	
	Button btnDas = new Button("DashBoard");
	Button btnCon = new Button("Contact");
	Button btnItems = new Button("Inventory");
	Button btnCheck = new Button("Checkout");
	GridPane pane = new GridPane();
	VBox vbox = new VBox(30);
	Scene s = null;

	@Override
	public void start(Stage primaryStage) throws Exception {

		String path = "/Images/inventory.jpg";
		Image image = new Image(path);
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(150);
		imageView.setFitWidth(900);
		
		vbox.getChildren().addAll(btnDas,btnCon,btnItems,btnCheck);
		vbox.setPadding(new Insets(20, 10, 20, 10));
		vbox.setStyle("-fx-background-color:#34495E;");
		pane.add(vbox, 0, 1);;
		pane.setColumnSpan(vbox, 2);
		pane.setRowSpan(vbox, 4);
		
		//Loads data from the file and populate the dataArray and Observable List
		ListViewPopulator.loadInitDataFromFile();
		

		setScrollPane();
		
		//Dashboard Button
		btnDas.setMaxWidth(100);
		btnDas.setStyle("-fx-background-color:transparent;"
								+ "-fx-background-radius: 5em; "
								+"-fx-text-fill:Black;"
								+ "-fx-background-color: "
								+ "-fx-shadow-highlight-color, "
								+ "-fx-outer-border, "
								+ "-fx-inner-border, -fx-body-color;");
		String path1 = "Images/Home.png";
		Image image1 = new Image(path1);
		ImageView imageView1 = new ImageView(image1);
		imageView1.setFitWidth(20);
		imageView1.setFitHeight(20);
		btnDas.setGraphic(imageView1);
		
		//Contact Button
		btnCon.setMaxWidth(100);
		btnCon.setStyle("-fx-background-color:transparent;" 
								+ "-fx-background-radius: 5em; "
								+ "-fx-text-fill:Black;"
								+ "-fx-background-color: "
								+ "-fx-shadow-highlight-color, "
								+ "-fx-outer-border, "
								+ "-fx-inner-border, -fx-body-color;");
		String path2 = "Images/Contacts.png";
		Image image2 = new Image(path2);
		ImageView imageView2 = new ImageView(image2);
		imageView2.setFitWidth(20);
		imageView2.setFitHeight(20);
		btnCon.setGraphic(imageView2);
		
		//Items Button
		btnItems.setMaxWidth(100);
		btnItems.setStyle("-fx-background-color:transparent;" 
								+ "-fx-background-radius: 5em; "
								+ "-fx-text-fill:Black;"
								+ "-fx-background-color: "
								+ "-fx-shadow-highlight-color, "
								+ "-fx-outer-border, "
								+ "-fx-inner-border, -fx-body-color;");
		String path3 = "Images/Items.png";
		Image image3 = new Image(path3);
		ImageView imageView3 = new ImageView(image3);
		imageView3.setFitWidth(20);
		imageView3.setFitHeight(20);
		btnItems.setGraphic(imageView3);
		
		btnItems.setOnAction(e -> {
			try {
				Market m = new Market();
				m.btnBack.setOnAction(e2 -> {
					setScrollPane();
					primaryStage.setScene(s);
				});
				Scene s2 = new Scene(m.getMarketPane(),900,500);
				primaryStage.setScene(s2);
			} catch (IOException | DataMismatchException e1) {
				e1.printStackTrace();
			}
			
		});
		
		//Checkout Button
		btnCheck.setMaxWidth(100);
		btnCheck.setStyle("-fx-background-color:transparent;" 
								+ "-fx-background-radius: 5em; "
								+"-fx-text-fill:Black;"
								+ "-fx-background-color: "
								+ "-fx-shadow-highlight-color, "
								+ "-fx-outer-border, "
								+ "-fx-inner-border, -fx-body-color;");
		String path4 = "Images/Checkout.png";
		Image image4 = new Image(path4);
		ImageView imageView4 = new ImageView(image4);
		imageView4.setFitWidth(15);
		imageView4.setFitHeight(15);
		btnCheck.setGraphic(imageView4);
		
		//Vertical Separator
		Separator vart = new Separator();
		vart.setOrientation(Orientation.VERTICAL);
		vart.setPrefHeight(350);
		vart.setValignment(VPos.CENTER);
		GridPane.setConstraints(vart, 3, 1);
		GridPane.setRowSpan(vart, 4);
		
		pane.getChildren().add(vart);

		String Path = "Images/Sheridan.jpg";
		Image image7 = new Image(Path,400,350,false,true);
		ImageView imageView7 = new ImageView(image7);
		pane.add(imageView7,3, 2);
		GridPane.setColumnSpan(imageView7, 7);
		GridPane.setRowSpan(imageView7, 7);
		
		pane.add(imageView, 0, 0);
		GridPane.setColumnSpan(imageView, 5);
		
		s = new Scene(pane, 900, 500);
		primaryStage.setTitle("DashBoard");
		primaryStage.setScene(s);
		primaryStage.show();
	}
	
	public void setScrollPane() {
		ListView<String> lv= ListViewPopulator.getInitListView();
		lv.setPrefSize(400, 400);
		ScrollPane sp = new ScrollPane(lv);
		sp.setFitToWidth(true);
		pane.add(sp,2,2);
	}

	public static void main(String[] args) {
		launch(args);

	}

}

