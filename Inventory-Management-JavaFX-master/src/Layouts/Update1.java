package Layouts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Update1 extends Stage {
	Label lblSno=new Label("S.No");
	Label lblName=new Label("Name");
	Label lblQty=new Label("Quantity");
	Label lblPrice=new Label("Price");
	Label lblM=new Label("Please enter the fields which are to be updated");
	
	TextField tfSno=new TextField();
	TextField tfName=new TextField();
	TextField tfQty=new TextField();
	TextField tfPrice=new TextField();
	
	Button btnUpdate = new Button("Update");
	
	ListView<String> listView = new ListView<String>();
	
	public Update1() throws Exception {
		GridPane grid=new GridPane();
		grid.add(lblSno, 0, 0);
		grid.add(tfSno, 0, 1);
		grid.add(lblM, 0, 2);
		GridPane.setColumnSpan(lblM, 2);
		grid.add(lblName, 0, 3);
		grid.add(tfName, 1, 3);
		grid.add(lblQty, 0, 4);
		grid.add(tfQty, 1, 4);
		grid.add(lblPrice, 0, 5);
		grid.add(tfPrice, 1, 5);
		grid.add(btnUpdate, 0, 6);
		GridPane.setColumnSpan(btnUpdate,2);
		GridPane.setHalignment(btnUpdate,HPos.CENTER);
		grid.setHgap(5);
		grid.setVgap(5);
		grid.setPadding(new Insets(10,10,10,10));
		
		btnUpdate.setOnAction(e -> {
			try {
				update();
			} catch (IOException | DataMismatchException e1) {
				e1.printStackTrace();
			}
		});
		
		BorderPane border=new BorderPane();

//		listView = ListViewPopulator.getInitListView();
//		listView.setPrefSize(350, 400);
//		ScrollPane scroll=new ScrollPane();
		border.setLeft(grid);
//		border.setCenter(scroll);
		Scene s=new Scene(border);
		setScene(s);
		setTitle("Update");
		sizeToScene();
		
	}
	
	public void update() throws IOException, DataMismatchException{
		int id = -1;
		try{
			 id = Integer.parseInt(tfSno.getText());
		}catch(Exception e){
			new DataMismatchException("Please enter a Integer value");
			return;
		}
		
		ArrayList<FileData> fd = ListViewPopulator.getDataArray();
		ListIterator<FileData> iter = fd.listIterator();
				
		boolean found = false;
		FileData curFileObj = null;
		while (iter.hasNext()) {
			curFileObj = iter.next();
			if (curFileObj.getSno()==id) {
				found = true;
				updateFileObj(curFileObj);
				break;
			}
		}
		tfName.clear();
		tfPrice.clear();
		tfQty.clear();
		if (!found) {
			Alert a = new Alert(AlertType.INFORMATION);
			a.setHeaderText("Update Failed");
			a.setContentText("The specified Id does not exist");
			a.show();
		}else {
			Alert a4 = new Alert(AlertType.INFORMATION);
			a4.setHeaderText("Success");
			a4.setContentText("Record Updated successfully");
			a4.show();
			FileData.recreateFileData(fd);  //Recreates the fileData with the updated ArrayList of FileData Objects
			ListViewPopulator.reloadArrObList(); //Reloads the listview with the modified data
		}
	}
	
	public void updateFileObj(FileData f) {
		
		
		//       ***************************************************
		//       *Remember to add code to allow modifying of the id*
		//       ***************************************************
		
		try {
			if (!tfName.getText().isEmpty()) {
				f.setName(tfName.getText());
			}
			if (!tfPrice.getText().isEmpty()) {
				f.setPrice(Double.parseDouble(tfPrice.getText()));
			}
			if (!tfQty.getText().isEmpty()) {
				f.setQty(Integer.parseInt(tfQty.getText()));
			}
		}catch (Exception e) {
			new DataMismatchException();
		}
	}
}
