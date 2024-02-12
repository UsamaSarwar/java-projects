package Layouts;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DataMismatchException extends Exception {
/**
 * this constructor creates a Alert if the input is incorrect
 **/
	public DataMismatchException() {
		Alert a = new Alert(AlertType.ERROR);
		a.setHeaderText("Incorrect Input");
		a.setContentText("Please enter valid value(s) to be set");
		a.show();
	}
	/**
	 * this is a parameterized constructor which creates a Alert if the input is incorrect
	 **/
	public DataMismatchException(String errorMsg) {
		Alert a = new Alert(AlertType.ERROR);
		a.setHeaderText("Incorrect Input");
		a.setContentText(errorMsg);
		a.show();
	}

}
