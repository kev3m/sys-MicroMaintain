package micromaintainsys.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import micromaintainsys.dao.DAO;
import micromaintainsys.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static micromaintainsys.utils.ViewUtils.*;


public class ordersController implements Initializable {
    @FXML
    private TableView<Ordem> tableView;
    @FXML
    private TableColumn<Ordem, Integer> idColumn;

    @FXML
    private TableColumn<Ordem, Integer> clienteColumn;
    @FXML
    private TableColumn<Ordem, Integer> tecnicoColumn;
    @FXML
    private TableColumn<Ordem, StatusOrdem> statusColumn;
    @FXML
    private ChoiceBox<StatusOrdem> statusFilter;
    @FXML
    private Button acceptOrder;
    @FXML
    private Label helloLabel;
    private Tecnico tecnicoSessao;
    private int objID;

    @FXML
    private AnchorPane ordersAnchorPane;
    private Queue<Ordem> ordensAbertas;
    private ArrayList<Ordem> ordensServico;
    private ArrayList<Fatura> faturas;
    private Estoque estoque;



    public ordersController() {

    }

    private void atualizarTabelaPorStatus(StatusOrdem status) {
        ObservableList<Ordem> observableList = FXCollections.observableArrayList(DAO.getOrdemDAO().pegaTodasPorStatus(status));
        this.tableView.getItems().setAll(observableList);
        tableView.setItems(observableList);
        callDisplayNameOnInitialize();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){


        ObservableList<StatusOrdem> observableListstatus = FXCollections.observableArrayList(StatusOrdem.values());
        statusFilter.setItems(observableListstatus);
        statusFilter.getSelectionModel().select(0);

        statusFilter.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Chamada da função desejada para filtrar as ordens por status
            atualizarTabelaPorStatus(newValue);
        });

        ObservableList<Ordem> observableList = FXCollections.observableArrayList(DAO.getOrdemDAO().pegaTodasPorStatus(StatusOrdem.Aberta));
        this.tableView.getItems().setAll(observableList);
        tableView.setItems(observableList);


        this.estoque = carregaEstoque();
        ArrayList<Ordem> abertas = DAO.getOrdemDAO().pegaTodasPorStatus(StatusOrdem.Aberta);
        Collections.sort(abertas);
        this.ordensAbertas = new LinkedList<>(abertas);
        this.ordensServico = new ArrayList<>(DAO.getOrdemDAO().pegaTodas());
        this.faturas = new ArrayList<>(DAO.getFaturaDAO().pegaTodas());

        this.idColumn.setCellValueFactory(new PropertyValueFactory<Ordem, Integer>("ordemID"));
        this.clienteColumn.setCellValueFactory(new PropertyValueFactory<Ordem, Integer>("clienteID"));
        this.tecnicoColumn.setCellValueFactory(new PropertyValueFactory<Ordem, Integer>("tecnicoID"));
        this.statusColumn.setCellValueFactory(new PropertyValueFactory<Ordem, StatusOrdem>("status"));


        this.tableView.setRowFactory(tv -> {
            TableRow<Ordem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Ordem rowData = row.getItem();
                    try {
                        new SceneSwitch(ordersAnchorPane, "management_Scenes/servicos_ger.fxml", tecnicoSessao, rowData.getOrdemID());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });





    }
    public Estoque carregaEstoque(){
        //this.estoque = DAO.getEstoqueDAO().cria()
        return DAO.getEstoqueDAO().carrega();
    }

    @FXML
    void switchToOrdem() throws IOException {
        new SceneSwitch(ordersAnchorPane, "main.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToOrdem_ger() throws IOException {
        new SceneSwitch(ordersAnchorPane, "management_Scenes/ordens_ger.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToTec() throws IOException {
        new SceneSwitch(ordersAnchorPane, "tecnicos.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToClientes() throws IOException {
        new SceneSwitch(ordersAnchorPane, "clientes.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToEstoque() throws IOException {
        new SceneSwitch(ordersAnchorPane, "estoque.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToFatura() throws IOException {
        new SceneSwitch(ordersAnchorPane, "faturas.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToOrdem_Compra() throws IOException {
        new SceneSwitch(ordersAnchorPane, "ordem_compra.fxml", tecnicoSessao, objID);
    }

    @FXML
    void logoutTecnico() throws IOException {
        if (this.tecnicoSessao != null){
            this.tecnicoSessao = null;
            showInformationAlert("Logout efetuado", "Logout efetuado com sucesso");
            new SceneSwitch(ordersAnchorPane, "login.fxml", tecnicoSessao, objID);
        }
        else{
            showErrorAlert("Erro ao fazer logout", "Não há nenhum técnico logado no sistema");
        }
        new SceneSwitch(ordersAnchorPane, "login.fxml", tecnicoSessao, objID);
    }
    public void callDisplayNameOnInitialize() {
        // Verifica se o objeto tecnicoSessao não é nulo antes de chamar o displayName
        if (tecnicoSessao != null) {
            System.out.println("TecnicoSessao não é nulo");
            System.out.println(tecnicoSessao.getNome());
            System.out.println(tecnicoSessao.getTecnicoID());
            System.out.println(tecnicoSessao.getSenha());
            displayName();
        }
    }


    @FXML
    public void displayName() {
        helloLabel.setText("Olá, " + this.tecnicoSessao.getNome() + "!");
    }
    public void setTecnicoSessao(Tecnico tecnicoSessao) {
        this.tecnicoSessao = tecnicoSessao;
    }
}
