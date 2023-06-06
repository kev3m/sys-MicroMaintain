package micromaintainsys.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import micromaintainsys.dao.DAO;
import micromaintainsys.model.OrdemCompra;
import micromaintainsys.model.SceneSwitch;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ordemCompraController implements Initializable {
    @FXML
    private AnchorPane ordemCompraAnchorPane;
    @FXML
    private TableColumn<OrdemCompra, Calendar> dataColumn;

    @FXML
    private TableColumn<OrdemCompra, String> pecaColumn;

    @FXML
    private TableColumn<OrdemCompra, Integer> quantColumn;

    @FXML
    private TableColumn<OrdemCompra, Double> valorColumn;
    @FXML
    private TableView<OrdemCompra> tableView;

    @FXML
    void switchToOrdem() throws IOException {
        new SceneSwitch(ordemCompraAnchorPane, "main.fxml");
    }

    @FXML
    void switchToTec() throws IOException {
        new SceneSwitch(ordemCompraAnchorPane, "tecnicos.fxml");
    }
    @FXML
    void switchToClientes() throws IOException {
        new SceneSwitch(ordemCompraAnchorPane, "clientes.fxml");
    }
    @FXML
    void switchToEstoque() throws IOException {
        new SceneSwitch(ordemCompraAnchorPane, "estoque.fxml");
    }
    @FXML
    void switchToGerEstoque() throws IOException {
        new SceneSwitch(ordemCompraAnchorPane, "management_Scenes/estoque_ger.fxml");
    }
    @FXML
    void switchToFatura() throws IOException {
        new SceneSwitch(ordemCompraAnchorPane, "faturas.fxml");
    }
    @FXML
    void switchToOrdem_Compra() throws IOException {
        new SceneSwitch(ordemCompraAnchorPane, "ordem_compra.fxml");
    }

    ObservableList<OrdemCompra> observableList = FXCollections.observableArrayList(DAO.getEstoqueDAO().carrega().verificaOrdensCompra());
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataCriacao"));
        this.pecaColumn.setCellValueFactory(new PropertyValueFactory<>("peca"));
        this.quantColumn.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        this.valorColumn.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));



        this.tableView.getItems().setAll(DAO.getEstoqueDAO().carrega().verificaOrdensCompra());
        tableView.setItems(observableList);

    }
}
