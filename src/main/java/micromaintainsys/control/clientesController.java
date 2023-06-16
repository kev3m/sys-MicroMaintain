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
import micromaintainsys.model.Cliente;
import micromaintainsys.model.Ordem;
import micromaintainsys.model.SceneSwitch;
import micromaintainsys.model.Tecnico;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static micromaintainsys.utils.ViewUtils.showErrorAlert;
import static micromaintainsys.utils.ViewUtils.showInformationAlert;

public class clientesController implements Initializable {

    @FXML
    private AnchorPane clientesAnchorPane;
    @FXML
    private TableView<Cliente> tableView;
    @FXML
    private TableColumn<Cliente, Integer> clientID;
    @FXML
    private TableColumn<Cliente, String> clientNome;
    @FXML
    private TableColumn<Cliente, String> clientEndereco;
    @FXML
    private TableColumn<Cliente, String> clientTel;
    @FXML
    private TableColumn<Cliente, ArrayList<Ordem>> clientOrdens;
    private Tecnico tecnicoSessao;
    private int objID;



    @FXML
    private ImageView closeButton;

    @FXML
    void switchToOrdem() throws IOException {
        new SceneSwitch(clientesAnchorPane, "main.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToCliente_ger() throws IOException {
        new SceneSwitch(clientesAnchorPane, "management_Scenes/clientes_ger.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToTec() throws IOException {
        new SceneSwitch(clientesAnchorPane, "tecnicos.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToClientes() throws IOException {
        new SceneSwitch(clientesAnchorPane, "clientes.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToEstoque() throws IOException {
        new SceneSwitch(clientesAnchorPane, "estoque.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToFatura() throws IOException {
        new SceneSwitch(clientesAnchorPane, "faturas.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToOrdem_Compra() throws IOException {
        new SceneSwitch(clientesAnchorPane, "ordem_compra.fxml", tecnicoSessao, objID);
    }
    public void setTecnicoSessao(Tecnico tecnicoSessao) {
        this.tecnicoSessao = tecnicoSessao;
    }
    @FXML
    void logoutTecnico() throws IOException {
        if (this.tecnicoSessao != null){
            this.tecnicoSessao = null;
            showInformationAlert("Logout efetuado", "Logout efetuado com sucesso");
            new SceneSwitch(clientesAnchorPane, "login.fxml", tecnicoSessao, objID);
        }
        else{
            showErrorAlert("Erro ao fazer logout", "Não há nenhum técnico logado no sistema");
        }
        new SceneSwitch(clientesAnchorPane, "login.fxml", tecnicoSessao, objID);
    }



    ObservableList<Cliente> observableList = FXCollections.observableArrayList(DAO.getClienteDAO().pegaTodos());
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.clientID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        this.clientNome.setCellValueFactory(new PropertyValueFactory<>("Name"));
        this.clientEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        this.clientTel.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        this.clientOrdens.setCellValueFactory(new PropertyValueFactory<>("ordens"));


        this.tableView.getItems().setAll(DAO.getClienteDAO().pegaTodos());
        tableView.setItems(observableList);


    }
}
