import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.hibernate.Session;
import org.hibernate.Transaction;


import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class UserViewController {

    ObservableList<Client> clientList = FXCollections.observableArrayList();
    @FXML
    private Button arataTot;

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

    @FXML
    private TableColumn colEdit;


    public void initialize() throws SQLException {
        showClientTable();

    }

    public void showClientTable() {
        Transaction transaction = null;
        try {
            final Session session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            List<Client> clientsListList = session.createQuery("from Client").getResultList();
            ObservableList<Client> obList = FXCollections.observableArrayList();

            for (Client tempList : clientsListList) {
                obList.addAll(new Client(tempList.getIdClient(), tempList.getNumeClient(), tempList.getNumarTelefon(), tempList.getCUI(), tempList.getOras()));
            }
            // aici ii spunem sa adauge toate acele informatii din lista in tabel
            tableClient.setItems(obList);

            colNume.setCellValueFactory(new PropertyValueFactory<>("numeClient"));
            colCUI.setCellValueFactory(new PropertyValueFactory<>("CUI"));
            colOras.setCellValueFactory(new PropertyValueFactory<>("oras"));
            colTel.setCellValueFactory(new PropertyValueFactory<>("numarTelefon"));

            Callback<TableColumn<Client, String>, TableCell<Client, String>> cellFactory = param -> {
                final TableCell<Client, String> cell = new TableCell<Client, String>() {
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            final Button editButton = new Button("Editeaza");
                            editButton.setOnAction(event -> {

                                Client c = getTableView().getItems().get(getIndex());

                                Alert a = new Alert(Alert.AlertType.INFORMATION);
                                a.setContentText("You have clicked \n " + c.getNumeClient() + c.getNumarTelefon());
                                a.show();
                            });
                            setGraphic(editButton);
                            setText(null);
                        }
                    }
                };
                return cell;
            };

            colEdit.setCellFactory(cellFactory);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtils.cleanUp();
        }


    }
}
