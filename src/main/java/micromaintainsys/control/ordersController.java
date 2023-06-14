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
        new SceneSwitch(ordersAnchorPane, "login.fxml", tecnicoSessao, objID);
    }



}
