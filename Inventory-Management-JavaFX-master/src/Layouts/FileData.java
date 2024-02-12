package Layouts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ListIterator;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FileData{
	
	private int sno; //Serial Number
	private String name; //Product name
	private int qty; //Quantity
	private double price;  //Price
	
	
	
	public FileData(String sno, String name, String qty, String price) throws DataMismatchException {
		try {
			this.sno = Integer.parseInt(sno);
			this.name = name;
			this.qty = Integer.parseInt(qty);
			this.price = Double.parseDouble(price);
		}catch (Exception e) {
			throw new DataMismatchException(); //To handle incorrect input in the method creating the FileData object
		}
	}
	
	public static String castDataAsFileData(FileData f){
		return f.sno+":"+f.name+":"+f.qty+":"+f.price;
	}
	
	public static void recreateFileData(ArrayList<FileData> a) throws IOException{
		File f = new File("dataFile.txt");
		if (!f.exists()) {
			f.createNewFile();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
		ListIterator<FileData> itr = a.listIterator();
		while (itr.hasNext()) {
			bw.write(castDataAsFileData(itr.next()));
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
