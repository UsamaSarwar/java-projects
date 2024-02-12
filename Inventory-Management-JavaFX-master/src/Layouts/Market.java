package Layouts;

import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Market{
	
	BorderPane pane1 = new BorderPane();
	Button btnBack = new Button("Go back");
	

	public Market() throws IOException, DataMismatchException {
		ScrollBar sbVertical = new ScrollBar();
		sbVertical.setOrientation(Orientation.VERTICAL);
		pane1.setTop(getFlowPane()); 
		pane1.setLeft(getVBox());
//		pane1.setRight(sbVertical);
		
//		ListViewPopulator.loadInitDataFromFile();
		
		ListView<String> lv = ListViewPopulator.getInitListView();
		lv.setPrefSize(400, 400);
		ScrollPane sp = new ScrollPane(lv);
		sp.setFitToWidth(true);	
		pane1.setCenter(sp);
		
//		Scene s = new Scene(pane1, 900, 500);
		
//		primaryStage.setTitle("Super Market");
//		primaryStage.setScene(s);
//		primaryStage.show();
	}
	
	public BorderPane getMarketPane() { //Returns the Constructed BorderPane
		return pane1;
	}
	private FlowPane getFlowPane() {
		FlowPane pane = new FlowPane();
		pane.setHgap(20);
		pane.setPadding(new Insets(5,0,5,5));
		
		String Path = "Images/Sheridan.jpg";
		Image image = new Image(Path);
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(130);
		imageView.setFitWidth(167);
		
		Text t = new Text();
		t.setText("SUPER MARKET");
		t.setFill(Color.NAVY);
		t.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 75));
		
		pane.getChildren().addAll(imageView,t);
		return pane;
	}
	private VBox getVBox() {
		VBox vbox = new VBox(30);
		vbox.setPadding(new Insets(30,10,5,10));
		vbox.setStyle("-fx-background-color:#1A5276");
		
		Button btnAdd = new Button("Add");
		btnAdd.setPrefSize(150, 30);
		btnAdd.setStyle("-fx-background-color:transparent;"
				+ "-fx-background-radius: 5em; "
				+"-fx-text-fill:Black;"
				+ "-fx-background-color: "
				+ "-fx-shadow-highlight-color, "
				+ "-fx-outer-border, "
				+ "-fx-inner-border, -fx-body-color;");
		String path1 = "Images/Add.png";
		Image image1 = new Image(path1);
		ImageView imageView1 = new ImageView(image1);
		imageView1.setFitHeight(20);
		imageView1.setFitWidth(20);
		btnAdd.setGraphic(imageView1);
		btnAdd.setOnAction( e -> new Add().show());
		
		Button btnSearch = new Button("Search");
		btnSearch.setPrefSize(150, 30);
		btnSearch.setStyle("-fx-background-color:transparent;"
				+ "-fx-background-radius: 5em; "
				+"-fx-text-fill:Black;"
				+ "-fx-background-color: "
				+ "-fx-shadow-highlight-color, "
				+ "-fx-outer-border, "
				+ "-fx-inner-border, -fx-body-color;");
		String path2 = "Images/search.png";
		Image image2 = new Image(path2);
		ImageView imageView2 = new ImageView(image2);
		imageView2.setFitHeight(20);
		imageView2.setFitWidth(20);
		btnSearch.setGraphic(imageView2);
		btnSearch.setOnAction(e -> new Search().show());
		
		Button btnUpdate = new Button("Update");
		btnUpdate.setPrefSize(150, 30);
		btnUpdate.setStyle("-fx-background-color:transparent;"
				+ "-fx-background-radius: 5em; "
				+"-fx-text-fill:Black;"
				+ "-fx-background-color: "
				+ "-fx-shadow-highlight-color, "
				+ "-fx-outer-border, "
				+ "-fx-inner-border, -fx-body-color;");
		String path3 = "Images/Update.jpg";
		Image image3 = new Image(path3);
		ImageView imageView3 = new ImageView(image3);
		imageView3.setFitHeight(20);
		imageView3.setFitWidth(20);
		btnUpdate.setGraphic(imageView3);
		btnUpdate.setOnAction(e -> {
			try {
				new Update1().show();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});
		
		Button btnDelete = new Button("Delete");
		btnDelete.setPrefSize(150, 30);
		btnDelete.setStyle("-fx-background-color:transparent;"
				+ "-fx-background-radius: 5em; "
				+"-fx-text-fill:Black;"
				+ "-fx-background-color: "
				+ "-fx-shadow-highlight-color, "
				+ "-fx-outer-border, "
				+ "-fx-inner-border, -fx-body-color;");
		String path4 = "Images/Delete.jpg";
		Image image4 = new Image(path4);
		ImageView imageView4 = new ImageView(image4);
		imageView4.setFitHeight(20);
		imageView4.setFitWidth(20);
		btnDelete.setGraphic(imageView4);
		btnDelete.setOnAction(e -> {
			try {
				new Delete().show();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		btnBack.setPrefSize(150, 30);
		btnBack.setStyle("-fx-background-color:transparent;"
				+ "-fx-background-radius: 5em; "
				+"-fx-text-fill:Black;"
				+ "-fx-background-color: "
				+ "-fx-shadow-highlight-color, "
				+ "-fx-outer-border, "
				+ "-fx-inner-border, -fx-body-color;");
		
		vbox.getChildren().addAll(btnAdd, btnSearch, btnUpdate, btnDelete, btnBack);
				
		return vbox;
	}


}
