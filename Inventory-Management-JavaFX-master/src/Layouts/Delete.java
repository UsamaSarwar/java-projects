package Layouts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Delete extends Stage {
	Label lblNum=new Label("S.No ");
	
	Label lblQty=new Label(" Quantity ");

	TextField tfNum=new TextField();
	
	TextField tfQty=new TextField();
	
	Button btnDelete=new Button(" Delete ");
	
	public Delete() throws Exception {
       GridPane grid=new GridPane();
       grid.add(lblNum, 0, 0);
       
       grid.add(tfNum, 1, 0);
       
       grid.add(lblQty, 0, 2);
       grid.add(tfQty, 1, 2);
       
       grid.add(btnDelete, 0, 4);
       grid.setPadding(new Insets(10,10,10,10));
       grid.setHgap(5);
       grid.setVgap(5);
       GridPane.setHalignment(lblNum, HPos.RIGHT);
       GridPane.setHalignment(lblQty, HPos.RIGHT);
       
       btnDelete.setOnAction(e -> {
		try {
			deleteData();
		} catch (IOException | DataMismatchException e1) {
			e1.printStackTrace();
		}
	});
       GridPane.setColumnSpan(btnDelete,2);
       GridPane.setHalignment(btnDelete, HPos.CENTER);
       Scene s=new Scene(grid);
       setScene(s);
       sizeToScene();
       setTitle(" Delete ");
	
	}
	
	public void deleteData() throws IOException, DataMismatchException {
		int id = -1;
		int qty = 0;
		try{
			id = Integer.parseInt(tfNum.getText());
			qty = Integer.parseInt(tfQty.getText());
			if (qty<0) throw new Exception();
		}catch (Exception e) {
			if (!tfQty.getText().isEmpty()) {
				new DataMismatchException();
				return;
			}
		}
		ArrayList<FileData> fd = ListViewPopulator.getDataArray();
		ListIterator<FileData> iter = fd.listIterator();
		FileData curFdObj = null;
		boolean found = false;
		while (iter.hasNext()) {
			curFdObj = iter.next();
			if (curFdObj.getSno()==id) {
				found = true;
				if (tfQty.getText().isEmpty()) {
					fd.remove(curFdObj);
					Alert a2 = new Alert(AlertType.INFORMATION);
					a2.setHeaderText("Success");
					a2.setContentText("Record deleted successfully");
					a2.show();
					tfNum.clear();
				}else {
					if (qty<=curFdObj.getQty()) {
						tfNum.clear();
						tfQty.clear();
						Alert a3 = new Alert(AlertType.INFORMATION);
						a3.setHeaderText("Success");
						a3.setContentText("Quantity reduced successfully");
						a3.show();
						curFdObj.setQty(curFdObj.getQty()-qty);
					}else {
						Alert a = new Alert(AlertType.INFORMATION);
						a.setHeaderText("Request denied");
						a.setContentText("The specified quantity is unavailable");
						a.show();
					}
				}
				break;
			}
		}
		if (!found) {
			Alert a = new Alert(AlertType.INFORMATION);
			a.setHeaderText("Update Failed");
			a.setContentText("The specified Id does not exist");
			a.show();
		}else{
			FileData.recreateFileData(fd);
			ListViewPopulator.reloadArrObList();
		}
	}

}
