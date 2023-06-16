package micromaintainsys.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableView;
import micromaintainsys.dao.DAO;
import micromaintainsys.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static micromaintainsys.utils.ViewUtils.showErrorAlert;
import static micromaintainsys.utils.ViewUtils.showInformationAlert;

public class tecnicosController implements Initializable {

    @FXML
    private AnchorPane tecnicosAnchorPane;
    private Queue<Ordem> ordensAbertas;
    private ArrayList<Ordem> ordensServico;
    private ArrayList<Fatura> faturas;
    private Estoque estoque;
    private Tecnico tecnicoSessao;
    private int objID;

    @FXML
    private TableColumn<Tecnico, Boolean> PermColumn;
    @FXML
    private TableColumn<Tecnico, Integer> idColumn;

    @FXML
    private TableColumn<Tecnico, String> nomeColumn;

    @FXML
    private TableColumn<Tecnico, Integer> ordemAColumn;

    @FXML
    private TableColumn<Tecnico, String> senhaColumn;

    @FXML
    private TableView<Tecnico> tableView;
    @FXML
    void switchToOrdem() throws IOException {
        new SceneSwitch(tecnicosAnchorPane, "main.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToTec() throws IOException {
        new SceneSwitch(tecnicosAnchorPane, "tecnicos.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToTec_edit() throws IOException {
        new SceneSwitch(tecnicosAnchorPane, "management_Scenes/tecnicos_ger.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToClientes() throws IOException {
        new SceneSwitch(tecnicosAnchorPane, "clientes.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToEstoque() throws IOException {
        new SceneSwitch(tecnicosAnchorPane, "estoque.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToFatura() throws IOException {
        new SceneSwitch(tecnicosAnchorPane, "faturas.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToOrdem_Compra() throws IOException {
        new SceneSwitch(tecnicosAnchorPane, "ordem_compra.fxml", tecnicoSessao, objID);
    }
    public void setTecnicoSessao(Tecnico tecnicoSessao) {
        this.tecnicoSessao = tecnicoSessao;
    }
    @FXML
    void logoutTecnico() throws IOException {
        if (this.tecnicoSessao != null){
            this.tecnicoSessao = null;
            showInformationAlert("Logout efetuado", "Logout efetuado com sucesso");
            new SceneSwitch(tecnicosAnchorPane, "login.fxml", tecnicoSessao, objID);
        }
        else{
            showErrorAlert("Erro ao fazer logout", "Não há nenhum técnico logado no sistema");
        }
        new SceneSwitch(tecnicosAnchorPane, "login.fxml", tecnicoSessao, objID);
    }

    ObservableList<Tecnico> observableList = FXCollections.observableArrayList(DAO.getTecnicoDAO().pegaTodos());
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.idColumn.setCellValueFactory(new PropertyValueFactory<Tecnico, Integer>("tecnicoID"));
        this.nomeColumn.setCellValueFactory(new PropertyValueFactory<Tecnico, String>("nome"));
        this.ordemAColumn.setCellValueFactory(new PropertyValueFactory<Tecnico, Integer>("ordemEmAndamentoID"));
        this.senhaColumn.setCellValueFactory(new PropertyValueFactory<Tecnico, String>("senha"));
        this.PermColumn.setCellValueFactory(new PropertyValueFactory<Tecnico, Boolean>("adm"));
        this.tableView.getItems().setAll(DAO.getTecnicoDAO().pegaTodos());
        tableView.setItems(observableList);


    }
}
