package Layouts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Search extends Stage {

	Label lblId=new Label("ID");
	TextField tf=new TextField();
	ObservableList<String> obList = FXCollections.observableArrayList();
	ListView<String> listView = new ListView<>(obList);
	Button btnSearch = new Button("Search");
	
	public Search(){
		VBox vbox=new VBox();
		vbox.getChildren().addAll(lblId,tf,btnSearch);
	    vbox.setPadding(new Insets(10,10,10,10));
	    vbox.setSpacing(5);
	    ScrollPane scroll=new ScrollPane(listView);
	    listView.setPrefSize(400, 400);
	    BorderPane border=new BorderPane();
	    border.setLeft(vbox);
	    border.setCenter(scroll);
	    
		ListViewPopulator.createHeaderForListView(obList);

	    btnSearch.setOnAction(e -> {
			try {
				populateListView();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	    
		Scene s= new Scene(border,500,200);
		setScene(s);
		sizeToScene();
		setTitle("Search");
	}
	
	public void populateListView() throws IOException {
		ArrayList<FileData> arr = ListViewPopulator.getDataArray();
		int id = -1;
		try {
			id = Integer.parseInt(tf.getText());
		}catch(Exception e) {}
		ListIterator<FileData> iter = arr.listIterator();
		boolean found = false;
		FileData fd = null;
		while (iter.hasNext()) {
			fd = iter.next();
			if (fd.getSno() == id) {
				found = true;
				ListViewPopulator.addStringToListView(obList,fd);
				break;
			}
		}
		if (!found){
			Alert notFoundAlert = new Alert(AlertType.INFORMATION);
			notFoundAlert.setHeaderText("Search Results");
			notFoundAlert.setContentText("No Records found!");
			notFoundAlert.show();
		}
		obList.remove(2, obList.size()-1);
	}

}
