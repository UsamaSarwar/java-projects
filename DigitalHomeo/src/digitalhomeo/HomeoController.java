package digitalhomeo;

import com.mysql.jdbc.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

public class HomeoController implements Initializable {

    // Search Variable
    public static String Description = null;
    public static String Medicine = null;

    @FXML
    public TextField tf_description;
    @FXML
    private TableView<Data> tv_Book;
    @FXML
    private TableColumn<Data, String> col_id;
    @FXML
    private TableColumn<Data, String> col_description;
    @FXML
    private TableColumn<Data, String> col_medicine;
    @FXML
    private TableColumn<Data, String> col_potency;
    @FXML
    private TableView<Data> tv_search;
    @FXML
    private TableColumn<Data, String> col_s_id;
    @FXML
    private TableColumn<Data, String> col_s_des;
    @FXML
    private TableColumn<Data, String> col_s_med;
    @FXML
    private TableColumn<Data, String> col_s_pot;
    @FXML
    private Tab tab_login;
    @FXML
    private Tab tab_search;
    @FXML
    private Tab tab_book;
    @FXML
    private Tab tab_add;
    @FXML
    private TabPane tabPane;

    ObservableList<Data> dataList = FXCollections.observableArrayList();
    ObservableList<Data> s_dataList = FXCollections.observableArrayList();
    @FXML
    private Tab tab_exit;
    @FXML
    private TextField tf_add_medicine;
    @FXML
    private TextField tf_add_potency;
    @FXML
    private TextArea tf_add_symptom;
    @FXML
    private TableView<Data> tv_Book1;
    @FXML
    private TableColumn<Data, String> col_id1;
    @FXML
    private TableColumn<Data, String> col_description1;
    @FXML
    private TableColumn<Data, String> col_medicine1;
    @FXML
    private TableColumn<Data, String> col_potency1;
    @FXML
    private Text t_add_wrong;
    @FXML
    private Text t_add_submit;
    @FXML
    private Text t_del_info;
    @FXML
    private TextField tf_del_id;
    @FXML
    private TextField tf_medicine;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Disabling tabs
        tabPane.getTabs().remove(tab_add);
        tabPane.getTabs().remove(tab_book);
        tabPane.getTabs().remove(tab_search);

        try {
            Connection con = DBC.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM books");
            while (rs.next()) {
                dataList.add(new Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DataBase Error", "DataBase Connection Failed!", JOptionPane.ERROR_MESSAGE);
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("Des"));
        col_medicine.setCellValueFactory(new PropertyValueFactory<>("Med"));
        col_potency.setCellValueFactory(new PropertyValueFactory<>("Pot"));
        tv_Book.setItems(dataList);
        // Add Table Initialization
        col_id1.setCellValueFactory(new PropertyValueFactory<>("Id"));
        col_description1.setCellValueFactory(new PropertyValueFactory<>("Des"));
        col_medicine1.setCellValueFactory(new PropertyValueFactory<>("Med"));
        col_potency1.setCellValueFactory(new PropertyValueFactory<>("Pot"));
        tv_Book1.setItems(dataList);
    }

    @FXML
    private void searchMedicine(ActionEvent event) {

        if (tf_description.getText().isEmpty() && tf_medicine.getText().isEmpty()) {
            tv_search.getItems().clear();
        } else {
            tv_search.getItems().clear();
            HomeoController.Description = tf_description.getText();
            HomeoController.Medicine = tf_medicine.getText();
            // Beta Feature of Comma Seperated Words
            String sql_description = "";
            String logic = "%' OR symptom LIKE '%";

            String str = "Select * from books WHERE symptom LIKE '%" + Description + "%' AND medicine LIKE '%" + Medicine + "%'";

            String strArray[] = str.split("،");

            for (int i = 0; i < strArray.length; i++) {
                sql_description = sql_description.concat(strArray[i] + logic);
            }
            sql_description = sql_description.substring(0, (sql_description.length() - 21)); //22
            //System.out.println(sql_description); // For Debugging 
            // Beta End
            try {
                Connection con = DBC.getConnection();
                ResultSet rss = con.createStatement().executeQuery(sql_description);

                while (rss.next()) {
                    s_dataList.add(new Data(rss.getString(1), rss.getString(2), rss.getString(3), rss.getString(4)));
                }
            } catch (SQLException ex) {
                Logger.getLogger(HomeoController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }

            col_s_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
            col_s_des.setCellValueFactory(new PropertyValueFactory<>("Des"));
            col_s_med.setCellValueFactory(new PropertyValueFactory<>("Med"));
            col_s_pot.setCellValueFactory(new PropertyValueFactory<>("Pot"));
            tv_search.setItems(s_dataList);
        }
    }

    @FXML
    private void searchMedicineLive(javafx.scene.input.KeyEvent event) {

        tf_description.setOnKeyPressed(e -> {
            if (tf_description.getText().isEmpty() && tf_medicine.getText().isEmpty()) {
                tv_search.getItems().clear();
            } else {
                tv_search.getItems().clear();
                this.Description = tf_description.getText();
                this.Medicine = tf_medicine.getText();
                // Beta Feature of Comma Seperated Words
                String sql_description_live = "";
                String logic_live = "%' OR symptom LIKE '%";

                String str_live = "Select * from books WHERE symptom LIKE '%" + Description + "%' AND medicine LIKE '%" + Medicine + "%'";

                String strArray_live[] = str_live.split("،");

                for (int i = 0; i < strArray_live.length; i++) {
                    sql_description_live = sql_description_live.concat(strArray_live[i] + logic_live);
                }
                sql_description_live = sql_description_live.substring(0, (sql_description_live.length() - 21)); //22
                //System.out.println(sql_description); // For Debugging 
                // Beta End

                try {
                    Connection con = DBC.getConnection();
                    ResultSet rss = con.createStatement().executeQuery(sql_description_live);

                    while (rss.next()) {
                        s_dataList.add(new Data(rss.getString(1), rss.getString(2), rss.getString(3), rss.getString(4)));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(HomeoController.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(ex.getMessage());
                }
            }
//            if (e.getCode() == KeyCode.END) {
//                System.exit(0);
//            }
        });
        tf_medicine.setOnKeyPressed(e -> {
            if (tf_description.getText().isEmpty() && tf_medicine.getText().isEmpty()) {
                tv_search.getItems().clear();
            } else {
                tv_search.getItems().clear();
                this.Description = tf_description.getText();
                this.Medicine = tf_medicine.getText();
                try {
                    Connection con = DBC.getConnection();
                    ResultSet rss = con.createStatement().executeQuery("Select * from books WHERE symptom LIKE '%" + Description + "%' AND medicine LIKE '%" + Medicine + "%'");

                    while (rss.next()) {
                        s_dataList.add(new Data(rss.getString(1), rss.getString(2), rss.getString(3), rss.getString(4)));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(HomeoController.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(ex.getMessage());
                }
            }
        });
        col_s_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        col_s_des.setCellValueFactory(new PropertyValueFactory<>("Des"));
        col_s_med.setCellValueFactory(new PropertyValueFactory<>("Med"));
        col_s_pot.setCellValueFactory(new PropertyValueFactory<>("Pot"));
        tv_search.setItems(s_dataList);
    }

    @FXML
    private void loginButtonAction(ActionEvent event) {
        tabPane.getTabs().remove(tab_exit);
        tabPane.getTabs().add(tab_book);
        tabPane.getTabs().add(tab_search);
        tabPane.getTabs().add(tab_add);
        tabPane.getTabs().add(tab_exit);
        tabPane.getTabs().remove(tab_login);
    }

    @FXML
    private void b_add_disease(ActionEvent event) {
        // Check weather fields are empty or have data
        if (tf_add_medicine.getText().isEmpty() || tf_add_potency.getText().isEmpty() || tf_add_symptom.getText().isEmpty()) {
            // Warning Alert
            new Thread(() -> {
                try {
                    t_add_wrong.setText("براۓ مہربانی مکمل معلومات درج کریں");
                    Thread.sleep(5000);
                    t_add_wrong.setText("");
                } catch (InterruptedException ex) {
                    t_add_wrong.setText(ex.getMessage());
                }
            }).start();
        } else {
            // Insertion
            try {
                try (Connection con = DBC.getConnection()) {
                    java.sql.Statement statement = con.createStatement();
                    String update = "INSERT INTO books" + " VALUES (null, '" + tf_add_symptom.getText() + "' , '" + tf_add_medicine.getText() + "' , '" + tf_add_potency.getText() + "' );";
                    statement.executeUpdate(update);
                    con.close();
                }
            } catch (SQLException ex) {
                t_add_wrong.setText(ex.getMessage());
            }

            // Updating Table
            tv_Book1.getItems().clear();
            try {
                Connection con = DBC.getConnection();
                ResultSet rss = con.createStatement().executeQuery("Select * from books ORDER BY id DESC");
                while (rss.next()) {
                    dataList.add(new Data(rss.getString(1), rss.getString(2), rss.getString(3), rss.getString(4)));
                }
            } catch (SQLException ex) {
                t_add_wrong.setText(ex.getMessage());
            }
            col_id1.setCellValueFactory(new PropertyValueFactory<>("Id"));
            col_description1.setCellValueFactory(new PropertyValueFactory<>("Des"));
            col_medicine1.setCellValueFactory(new PropertyValueFactory<>("Med"));
            col_potency1.setCellValueFactory(new PropertyValueFactory<>("Pot"));
            tv_Book1.setItems(dataList);

            // Success Message
            new Thread(() -> {
                tf_add_medicine.setText("");
                tf_add_potency.setText("");
                tf_add_symptom.setText("");
                try {
                    t_add_submit.setText("نسخہ درج کر دیا گیا ہے");
                    Thread.sleep(5000);
                    t_add_submit.setText("");
                } catch (InterruptedException ex) {
                    t_add_wrong.setText(ex.getMessage());
                }
            }).start();
        }
    }

    @FXML
    private void b_del_disease(ActionEvent event) {
        if (!tf_del_id.getText().isEmpty()) {
            // Deletion
            try {
                try (Connection con = DBC.getConnection()) {
                    java.sql.Statement statement = con.createStatement();
                    String del = "DELETE FROM books WHERE id=" + tf_del_id.getText() + "";
                    statement.executeUpdate(del);
                    con.close();
                }
                // Success Notification
                new Thread(() -> {
                    tf_del_id.setText("");
                    try {
                        t_del_info.setText("نسخہ زائل کر دیا گیا ہے");
                        Thread.sleep(5000);
                        t_del_info.setText("");
                    } catch (InterruptedException ex) {
                        t_del_info.setText(ex.getMessage());
                    }
                }).start();
            } catch (SQLException ex) {
                t_del_info.setText(ex.getMessage());
            }

            // Updating Table
            tv_Book1.getItems().clear();

            try {
                Connection con = DBC.getConnection();
                ResultSet rss = con.createStatement().executeQuery("Select * from books");
                while (rss.next()) {
                    dataList.add(new Data(rss.getString(1), rss.getString(2), rss.getString(3), rss.getString(4)));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println(ex);
            }
            col_id1.setCellValueFactory(new PropertyValueFactory<>("Id"));
            col_description1.setCellValueFactory(new PropertyValueFactory<>("Des"));
            col_medicine1.setCellValueFactory(new PropertyValueFactory<>("Med"));
            col_potency1.setCellValueFactory(new PropertyValueFactory<>("Pot"));
            tv_Book1.setItems(dataList);

        } else {
            new Thread(() -> {
                try {
                    t_del_info.setText("براۓ مہربانی شناختی نمبردرج کریں");
                    Thread.sleep(5000);
                    t_del_info.setText("");
                } catch (InterruptedException ex) {
                    t_del_info.setText(ex.getMessage());
                }
            }).start();

        }
    }

}
