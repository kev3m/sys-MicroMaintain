package micromaintainsys.control;

import javafx.beans.Observable;
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
import micromaintainsys.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ordersController implements Initializable {
    @FXML
    private TableView<Ordem> tableView;
//
//    @FXML
//    private TableColumn<Ordem, int> idColumn;
//    @FXML
//    private TableColumn<Servico, Calendar> dataColumn;
//    @FXML
//    private TableColumn<Ordem, int> clienteColumn;
//    @FXML
//    private TableColumn<Ordem, int> tecnicoColumn;
//    @FXML
//    private TableColumn<Ordem, StatusOrdem> statusColumn;
//    @FXML
//    private TableColumn<Pagamento, TipoPagamento> pagamentoColumn;
//
//    @FXML
//    private TableColumn<Fatura, double> valorColumn;

    @FXML
    private AnchorPane ordersAnchorPane;
    private Queue<Ordem> ordensAbertas;
    private ArrayList<Ordem> ordensServico;
    private ArrayList<Fatura> faturas;
    private Estoque estoque;

    ObservableList<Ordem> observableList = FXCollections.observableArrayList(ordensAbertas
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        this.estoque = carregaEstoque();
        ArrayList<Ordem> abertas = DAO.getOrdemDAO().pegaTodasPorStatus(StatusOrdem.Aberta);
        Collections.sort(abertas);
        this.ordensAbertas = new LinkedList<>(abertas);
        this.ordensServico = new ArrayList<>(DAO.getOrdemDAO().pegaTodas());
        this.faturas = new ArrayList<>(DAO.getFaturaDAO().pegaTodas());

//        this.idColumn.setCellValueFactory(new PropertyValueFactory<Ordem, int>("ordemID"));
//        this.dataColumn.setCellValueFactory(new PropertyValueFactory<Servico, Calendar>("horarioAbertura"));
//        this.clienteColumn.setCellValueFactory(new PropertyValueFactory<Ordem, int>("clienteID"));
//        this.tecnicoColumn.setCellValueFactory(new PropertyValueFactory<Ordem, int>("tecnicoID"));
//        this.statusColumn.setCellValueFactory(new PropertyValueFactory<Ordem, StatusOrdem>("status"));
//        this.pagamentoColumn.setCellValueFactory(new PropertyValueFactory<Pagamento, TipoPagamento>("tipoPagamento"));
//        this.valorColumn.setCellValueFactory(new PropertyValueFactory<Fatura, double>("valorTotal"));

        tableView.setItems(observableList);

//        TableColumn orderId = new TableColumn("ID");
//        TableColumn orderData = new TableColumn("Data");
//        TableColumn orderCliente = new TableColumn("Cliente");
//        TableColumn orderTecnico = new TableColumn("Tecnico");
//        TableColumn orderStatus = new TableColumn("Status");
//        TableColumn orderPagamento = new TableColumn("Pagamento");
//        TableColumn orderValor = new TableColumn("Valor");
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
