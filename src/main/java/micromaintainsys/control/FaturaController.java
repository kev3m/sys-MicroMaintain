package micromaintainsys.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import micromaintainsys.dao.DAO;
import micromaintainsys.model.Fatura;
import micromaintainsys.model.SceneSwitch;
import micromaintainsys.model.Tecnico;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static micromaintainsys.utils.ViewUtils.showErrorAlert;
import static micromaintainsys.utils.ViewUtils.showInformationAlert;

public class FaturaController implements Initializable {
    @FXML
    private AnchorPane faturaAnchor;

    @FXML
    private TableView<Fatura> tableView;
    @FXML
    private TableColumn<Fatura, Integer> idFaturaColumn;

    @FXML
    private TableColumn<Fatura, Integer> idOrdemColumn;

    @FXML
    private TableColumn<Fatura, Double> valorPagoColumn;

    @FXML
    private TableColumn<Fatura, Double> valorTotalColumn;
    private Tecnico tecnicoSessao;
    private int objID;

    @FXML
    void switchToOrdem() throws IOException {
        new SceneSwitch(faturaAnchor, "main.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToTec() throws IOException {
        new SceneSwitch(faturaAnchor, "tecnicos.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToClientes() throws IOException {
        new SceneSwitch(faturaAnchor, "clientes.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToEstoque() throws IOException {
        new SceneSwitch(faturaAnchor, "estoque.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToFatura() throws IOException {
        new SceneSwitch(faturaAnchor, "faturas.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToFaturaGer() throws IOException {
        new SceneSwitch(faturaAnchor, "management_Scenes/fatura_ger.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToOrdem_Compra() throws IOException {
        new SceneSwitch(faturaAnchor, "ordem_compra.fxml", tecnicoSessao, objID);
    }

    public void setTecnicoSessao(Tecnico tecnicoSessao) {
        this.tecnicoSessao = tecnicoSessao;
    }
    @FXML
    void logoutTecnico() throws IOException {
        if (this.tecnicoSessao != null){
            this.tecnicoSessao = null;
            showInformationAlert("Logout efetuado", "Logout efetuado com sucesso");
            new SceneSwitch(faturaAnchor, "login.fxml", tecnicoSessao, objID);
        }
        else{
            showErrorAlert("Erro ao fazer logout", "Não há nenhum técnico logado no sistema");
        }
        new SceneSwitch(faturaAnchor, "login.fxml", tecnicoSessao, objID);
    }

    ObservableList<Fatura> observableFaturaList = FXCollections.observableArrayList(DAO.getFaturaDAO().pegaTodas());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.idFaturaColumn.setCellValueFactory(new PropertyValueFactory<>("faturaID"));
        this.idOrdemColumn.setCellValueFactory(new PropertyValueFactory<>("ordemID"));
        this.valorPagoColumn.setCellValueFactory(new PropertyValueFactory<>("valorPago"));
        this.valorTotalColumn.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        this.tableView.getItems().setAll(DAO.getFaturaDAO().pegaTodas());
        this.tableView.setItems(observableFaturaList);

    }

}
