package micromaintainsys.control;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import micromaintainsys.dao.DAO;
import micromaintainsys.model.OrdemCompra;
import micromaintainsys.model.SceneSwitch;
import micromaintainsys.model.Tecnico;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
    private TextField relName;
    private Tecnico tecnicoSessao;
    private int objID;


    @FXML
    void switchToOrdem() throws IOException {
        new SceneSwitch(ordemCompraAnchorPane, "main.fxml", tecnicoSessao, objID);
    }

    @FXML
    void switchToTec() throws IOException {
        new SceneSwitch(ordemCompraAnchorPane, "tecnicos.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToClientes() throws IOException {
        new SceneSwitch(ordemCompraAnchorPane, "clientes.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToEstoque() throws IOException {
        new SceneSwitch(ordemCompraAnchorPane, "estoque.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToGerEstoque() throws IOException {
        new SceneSwitch(ordemCompraAnchorPane, "management_Scenes/estoque_ger.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToFatura() throws IOException {
        new SceneSwitch(ordemCompraAnchorPane, "faturas.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToOrdem_Compra() throws IOException {
        new SceneSwitch(ordemCompraAnchorPane, "ordem_compra.fxml", tecnicoSessao, objID);
    }
    public void setTecnicoSessao(Tecnico tecnicoSessao) {
        this.tecnicoSessao = tecnicoSessao;
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
