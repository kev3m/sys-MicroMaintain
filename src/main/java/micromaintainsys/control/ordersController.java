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
    private TableColumn<Servico, Calendar> dataColumn;
    @FXML
    private TableColumn<Ordem, Integer> clienteColumn;
    @FXML
    private TableColumn<Ordem, Integer> tecnicoColumn;
    @FXML
    private TableColumn<Ordem, StatusOrdem> statusColumn;
    @FXML
    private TableColumn<Pagamento, TipoPagamento> pagamentoColumn;

    @FXML
    private TableColumn<Fatura, Double> valorColumn;


    @FXML
    private AnchorPane ordersAnchorPane;
    private Queue<Ordem> ordensAbertas;
    private ArrayList<Ordem> ordensServico;
    private ArrayList<Fatura> faturas;
    private Estoque estoque;

    ObservableList<Ordem> observableList = FXCollections.observableArrayList(DAO.getOrdemDAO().pegaTodas());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
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


        this.tableView.getItems().setAll(observableList);
        tableView.setItems(observableList);

    }
    public Estoque carregaEstoque(){
        //this.estoque = DAO.getEstoqueDAO().cria()
        return DAO.getEstoqueDAO().carrega();
    }

    @FXML
    void switchToOrdem() throws IOException {
        new SceneSwitch(ordersAnchorPane, "main.fxml");
    }
    @FXML
    void switchToOrdem_ger() throws IOException {
        new SceneSwitch(ordersAnchorPane, "management_Scenes/ordens_ger.fxml");
    }
    @FXML
    void switchToTec() throws IOException {
        new SceneSwitch(ordersAnchorPane, "tecnicos.fxml");
    }
    @FXML
    void switchToClientes() throws IOException {
        new SceneSwitch(ordersAnchorPane, "clientes.fxml");
    }
    @FXML
    void switchToEstoque() throws IOException {
        new SceneSwitch(ordersAnchorPane, "estoque.fxml");
    }
    @FXML
    void switchToFatura() throws IOException {
        new SceneSwitch(ordersAnchorPane, "faturas.fxml");
    }
    @FXML
    void switchToOrdem_Compra() throws IOException {
        new SceneSwitch(ordersAnchorPane, "ordem_compra.fxml");
    }

//    @FXML
//    public boolean logoutTecnico(){
//        if (this.tecnicoSessao != null){
//            this.tecnicoSessao = null;
//            return true;
//        }
//        else{
//            return false;
//        }
//    }



}
