import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserViewController {
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<Client> data;


    @FXML
    private TextField txt_search;
    @FXML
    private TableView<Client> tableClient;
    @FXML
    private TableColumn<Client, String> colNume;
    @FXML
    private TableColumn<Client, String> colCUI;
    @FXML
    private TableColumn<Client, String> colOras;
    @FXML
    private TableColumn<Client, String> colTel;


    public void initialize() throws SQLException {
        con = DBConnetion.getConnection();
        data = FXCollections.observableArrayList();
        setCellTable();
        loadDataFromDB();

    }

    private void setCellTable() {
        colNume.setCellValueFactory(new PropertyValueFactory<>("numeClient"));
        colCUI.setCellValueFactory(new PropertyValueFactory<>("CUI"));
        colOras.setCellValueFactory(new PropertyValueFactory<>("oras"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("numarTelefon"));
    }

    private void loadDataFromDB() {
        try {
            pst = con.prepareStatement("Select * from client");
            rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new Client("" + rs.getString(4), "" + rs.getString(3), "" + rs.getString(2), "" + rs.getString(5)));
            }
            pst.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tableClient.setItems(data);

    }

    public void arataTotiClientii() {
        try {
            pst = con.prepareStatement("Select * from client");
            rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new Client("" + rs.getString(4).trim(),
                        "" + rs.getString(3).trim(), "" + rs.getString(2).trim(),
                        "" + rs.getString(5).trim()));
            }
            pst.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tableClient.setItems(data);
        txt_search.clear();

    }

    public void searchClient() {

        String cautaText = txt_search.getText();
        System.out.println(cautaText);

                String sql = "Select * from client where nume LIKE '%" + txt_search.getText().trim() + "%' " +
                        "UNION Select * from Client where CUI LIKE '%" + txt_search.getText().trim()+ "%'";

                try {
                    data.clear();
                    pst = con.prepareStatement(sql);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        System.err.println("" + rs.getString(4));
                        data.add(new Client("" + rs.getString(4).trim(), "" + rs.getString(3), "" + rs.getString(2), "" + rs.getString(5)));
                    }
                    tableClient.setItems(data);
                    pst.close();
                    rs.close();

                } catch (Exception r) {

                }
            };


        }







