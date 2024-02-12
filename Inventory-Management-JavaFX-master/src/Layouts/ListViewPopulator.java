package Layouts;
	
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;


public class ListViewPopulator{
	
	private static ArrayList<FileData> dataArray = new ArrayList<>();
	private static ObservableList<String> dataList = FXCollections.observableArrayList();
	private static ListView<String> root = new ListView<>(dataList);

	/**
	 * Loads the data from the file when the listview is loaded
	 * @throws IOException
	 * @throws DataMismatchException 
	 */
	public static void loadInitDataFromFile() throws IOException, DataMismatchException {
		File f = new File("dataFile.txt");
		if (!f.exists()) {
			System.out.println("File does not exist");
			return;
		}
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = br.readLine();
		StringTokenizer st = null;
		createHeaderForListView();
		while (line!=null) {
			st = new StringTokenizer(line,":");
			dataArray.add(new FileData(st.nextToken(),st.nextToken(), st.nextToken(), st.nextToken()));
			addStringToListView(dataArray.size()-1);
			line = br.readLine();
		}
		br.close();
	}
	
	/**
	 * To add a FileData object  as string to the Main Observable List used for dashboard and inventory
	 * @param i  Index of the added object in the dataArray
	 */
	public static void addStringToListView(int i) {
		StringBuilder s = new StringBuilder();
		s.append(String.format("%6d",dataArray.get(i).getSno()));
		s.append(String.format("%15d",dataArray.get(i).getQty()));
		s.append(String.format("%20.1f",dataArray.get(i).getPrice()));
		s.append(String.format("%30s",dataArray.get(i).getName()));
		dataList.add(s.toString());
	}
	
	/**
	 * To add a FileData object as string to the Main Observable List used for dashboard and inventory
	 * @param fd
	 */
	public static void addStringToListView(FileData fd){
		StringBuilder s = new StringBuilder();
		s.append(String.format("%6d",fd.getSno()));
		s.append(String.format("%15d",fd.getQty()));
		s.append(String.format("%20.1f",fd.getPrice()));
		s.append(String.format("%30s",fd.getName()));
		dataList.add(s.toString());
	}
	
	/**
	 * To add a FileData object as String to any observable list
	 * @param obL Observable List
 	 * @param fd  FileData object
	 */
	public static void addStringToListView(ObservableList<String> obL,FileData fd) {
		StringBuilder s = new StringBuilder();
		s.append(String.format("%6d",fd.getSno()));
		s.append(String.format("%15d",fd.getQty()));
		s.append(String.format("%20.1f",fd.getPrice()));
		s.append(String.format("%30s",fd.getName()));
		obL.add(s.toString());
	}
	
	/**
	 * To add Header to the main Observable List used to populate the listview of dashboard and inventory
	 */
	public static void createHeaderForListView() {
		StringBuilder s = new StringBuilder();
		s.append(String.format("%6s","S No"));
		s.append(String.format("%17s","Quantity"));
		s.append(String.format("%16s","Price"));
		s.append(String.format("%27s","Name"));
		dataList.add(s.toString());
		
		String line = "--------";
		StringBuilder s1 = new StringBuilder();
		s1.append(String.format("%6s",line));
		s1.append(String.format("%17s",line));
		s1.append(String.format("%16s",line));
		s1.append(String.format("%30s",line+"---"));
		dataList.add(s1.toString());
	}
	
	/**
	 * To add the header to any observable list
	 * @param obL Observable List
	 */
	public static void createHeaderForListView(ObservableList<String> obL) {
		StringBuilder s = new StringBuilder();
		s.append(String.format("%6s","S No"));
		s.append(String.format("%17s","Quantity"));
		s.append(String.format("%16s","Price"));
		s.append(String.format("%27s","Name"));
		obL.add(s.toString());
		
		String line = "--------";
		StringBuilder s1 = new StringBuilder();
		s1.append(String.format("%6s",line));
		s1.append(String.format("%17s",line));
		s1.append(String.format("%16s",line));
		s1.append(String.format("%30s",line+"---"));
		obL.add(s1.toString());
	}
	
	public static void reloadArrObList() throws IOException, DataMismatchException{
		dataList.clear();
		dataArray.clear();
		loadInitDataFromFile();
	}
	
	
	public static ListView<String> getInitListView(){
		return root;
	}
	
	public static ArrayList<FileData> getDataArray(){
		return dataArray;
	}
	
}
