package Layouts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Add extends Stage {
	Label lblNum=new Label("S.No ");
	Label lblName=new Label(" Name ");
	Label lblQty=new Label(" Quantity ");
	Label lblPrice=new Label(" Price ");
	TextField tfNum=new TextField();
	TextField tfName=new TextField();
	TextField tfQty=new TextField();
	TextField tfPrice=new TextField();
	Button btnAdd=new Button(" ADD ");
	
	public Add() {
       GridPane grid=new GridPane();
       grid.add(lblNum, 0, 0);
       
       grid.add(tfNum, 1, 0);
       grid.add(lblName, 0, 1);
       grid.add(tfName, 1, 1);
       grid.add(lblQty, 0, 2);
       grid.add(tfQty, 1, 2);
       grid.add(lblPrice, 0, 3);
       grid.add(tfPrice, 1, 3);
       grid.add(btnAdd, 0, 4);
       grid.setPadding(new Insets(10,10,10,10));
       grid.setHgap(5);
       grid.setVgap(5);
       GridPane.setHalignment(lblName, HPos.RIGHT);
       GridPane.setHalignment(lblNum, HPos.RIGHT);
       GridPane.setHalignment(lblQty, HPos.RIGHT);
       GridPane.setHalignment(lblPrice, HPos.RIGHT);
       
       GridPane.setColumnSpan(btnAdd,2);
       GridPane.setHalignment(btnAdd, HPos.CENTER);
       
       btnAdd.setOnAction(e -> {
		String sno = tfNum.getText();
		String name= tfName.getText();
		String qty = tfQty.getText();
		String price = tfPrice.getText();
		
		FileData fd = null;
		try {
			fd = new FileData(sno, name, qty, price);
		}catch(DataMismatchException e2) {
			return;
		}
		
		ArrayList<FileData> al = ListViewPopulator.getDataArray();
		boolean found = false;
		boolean nameExists = false;
		for (int i=0;i<al.size();i++) {
			if (al.get(i).getSno()==Integer.parseInt(sno)) {
				found = true;
				break;
			}
			if (al.get(i).getName().equals(tfName.getText())) {
				nameExists = true;
				break;
			}
		}
		if (nameExists) {
			Alert a3 = new Alert(AlertType.INFORMATION);
			a3.setHeaderText("Addition error");
			a3.setContentText("Specified name already exists");
			a3.show();
			return;
		}
		if (!found) {
			Alert a2 = new Alert(AlertType.INFORMATION);
			a2.setHeaderText("Success");
			a2.setContentText("Record added successfully!");
			a2.show();
			tfName.clear();
			tfNum.clear();
			tfPrice.clear();
			tfQty.clear();
			ListViewPopulator.getDataArray().add(fd);
			ListViewPopulator.addStringToListView(fd);
		}else {
			Alert a = new Alert(AlertType.INFORMATION);
			a.setHeaderText("Addition error");
			a.setContentText("Specified Id already exists");
			a.show();
			return;
		}
		File f = new File("dataFile.txt");
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(f,true));
			bw.write(FileData.castDataAsFileData(fd));
			bw.newLine();
			bw.flush();
			bw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
       });
       
       
       Scene s=new Scene(grid);
       setScene(s);
       sizeToScene();
       setTitle(" ADD ");
       
	}
	
}
