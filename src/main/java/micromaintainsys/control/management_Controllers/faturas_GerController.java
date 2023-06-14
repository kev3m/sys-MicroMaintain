package micromaintainsys.control.management_Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import micromaintainsys.dao.DAO;
import micromaintainsys.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static micromaintainsys.utils.ViewUtils.showErrorAlert;
import static micromaintainsys.utils.ViewUtils.showInformationAlert;

public class faturas_GerController implements Initializable {
    @FXML
    private AnchorPane faturaGerAnchorPane;

    @FXML
    private TextField faturaID;

    @FXML
    private TableColumn<Fatura, Double> valorTotalColumn;

    @FXML
    private ImageView closeButton;

    @FXML
    private TextField idFatura;

    @FXML
    private TableColumn<Fatura, Integer> idFaturaColumn;

    @FXML
    private TextField idOrdem;

    @FXML
    private TableColumn<Fatura, Integer> idOrdemColumn;

    @FXML
    private TableColumn<Pagamento, Integer> idPagamentoColumn;

    @FXML
    private Button searchButton;

    @FXML
    private Button searchFatura;

    @FXML
    private ImageView searchOrderServ1;

    @FXML
    private TableView<Fatura> tableView;

    @FXML
    private TableView<Pagamento> tableView1;

    @FXML
    private ChoiceBox<TipoPagamento> tipoPagamento;

    @FXML
    private TableColumn<Pagamento, TipoPagamento> tipoPagamentoColumn;

    @FXML
    private TableColumn<Pagamento, Double> valorPagColumn;

    @FXML
    private TextField valorPagamento;

    @FXML
    private TableColumn<Fatura, Double> valorPagoColumn;
    private Tecnico tecnicoSessao;
    private int objID;

    @FXML
    void switchToFatura() throws IOException {
        new SceneSwitch(faturaGerAnchorPane, "faturas.fxml", tecnicoSessao, objID);
    }


    @FXML
    void efetuaPagamento(ActionEvent event) {
        String id = idFatura.getText();
        String valor = valorPagamento.getText();
        TipoPagamento tipo = tipoPagamento.getValue();

        if(id.isEmpty() || DAO.getFaturaDAO().pegaPorId(Integer.parseInt(id)) == null){
            showErrorAlert("ID de Fatura inválido ou Fatura não encontrada", "Por favor, verifique o campo de ID");
        }
        else if (valor.isEmpty() || Double.parseDouble(valor) <= 0){
            showErrorAlert("Valor de pagamento inválido", "Por favor, verifique o campo de valor");
        }
        else if (tipo == null){
            showErrorAlert("Tipo de pagamento inválido", "Por favor, verifique o campo de tipo de pagamento");
        }
        else{
            Fatura fatura = DAO.getFaturaDAO().pegaPorId(Integer.parseInt(id));
            double valorPago = fatura.getValorPago() + Double.parseDouble(valor);
            if (valorPago > fatura.getValorTotal()){
                showErrorAlert("Valor de pagamento inválido", "O valor de pagamento não pode ser maior que o valor total da fatura");
            }
            else{
                fatura.setValorPago(valorPago);
                DAO.getFaturaDAO().atualiza(fatura);
                DAO.getPagamentoDAO().cria(tipo, Double.parseDouble(valor), Integer.parseInt(id));
                showInformationAlert("Pagamento efetuado com sucesso", "O pagamento foi efetuado com sucesso");
                refreshFatura();
            }
        }






    }

    @FXML
    void geraFatura(ActionEvent event) {
        String id = idOrdem.getText();
        if(id.isEmpty() || DAO.getOrdemDAO().pegaPorId(Integer.parseInt(id)) == null){
            showErrorAlert("ID de Ordem inválido ou Ordem não encontrada", "Por favor, verifique o campo de ID");
        }
        else if(DAO.getOrdemDAO().pegaPorId(Integer.parseInt(idOrdem.getText())).getStatus() != StatusOrdem.Pagamento){
            showErrorAlert("Não é possível realizar o pagamento", "Ordem de serviço não está em fase de pagamento");
        }
        else if (DAO.getOrdemDAO().pegaPorId(Integer.parseInt(id)).getFaturaID() > -1){
            showErrorAlert("Ordem já possui uma fatura", "Por favor, verifique o campo de ID");
        }
        else{
            ArrayList<Servico> servicosOrdem = DAO.getServicoDAO().pegaTodosPorOrdemID(Integer.parseInt(id));
            double valorTotal = servicosOrdem.stream().mapToDouble(Servico::getValor).sum();
            Fatura fatura = DAO.getFaturaDAO().cria(Integer.parseInt(id), valorTotal);
            DAO.getOrdemDAO().pegaPorId(Integer.parseInt(id)).setFaturaID(fatura.getFaturaID());
            DAO.getOrdemDAO().atualiza(DAO.getOrdemDAO().pegaPorId(Integer.parseInt(id)));
            showInformationAlert("Fatura gerada com sucesso", "Fatura ID: " + fatura.getFaturaID());
            refreshFatura();
        }

        }
    @FXML
    void clearFields(ActionEvent event) {
        idFatura.clear();
        idOrdem.clear();
        valorPagamento.clear();
        tipoPagamento.getSelectionModel().selectFirst();

    }


    @FXML
    void searchServico(ActionEvent event) {

    }


    @FXML
    void refreshFatura(){
        ObservableList<Fatura> Faturas = FXCollections.observableArrayList(DAO.getFaturaDAO().pegaTodas());
        tableView.setItems(Faturas);
        tableView.refresh();


    }
    @FXML
    void refreshPagamentos() {
        String id = faturaID.getText();
        if (id.isEmpty() || DAO.getFaturaDAO().pegaPorId(Integer.parseInt(id)) == null) {
            showErrorAlert("ID de Fatura inválido ou Fatura não encontrada", "Por favor, verifique o campo de ID");
        } else {
            int faturaId = Integer.parseInt(id);
            ObservableList<Pagamento> pagamentos = FXCollections.observableArrayList(DAO.getPagamentoDAO().pegaTodosPorFaturaID(faturaId));


            tableView1.setItems(pagamentos);
            tableView1.refresh();
        }
    }

    ObservableList<Fatura> observableFaturaList = FXCollections.observableArrayList(DAO.getFaturaDAO().pegaTodas());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*INICIALIZA TABELA FATURA */
        this.idFaturaColumn.setCellValueFactory(new PropertyValueFactory<>("faturaID"));
        this.idOrdemColumn.setCellValueFactory(new PropertyValueFactory<>("ordemID"));
        this.valorPagoColumn.setCellValueFactory(new PropertyValueFactory<>("valorPago"));
        this.valorTotalColumn.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        this.tableView.getItems().setAll(DAO.getFaturaDAO().pegaTodas());
        this.tableView.setItems(observableFaturaList);

        ObservableList<TipoPagamento> tipoPagamentoList = FXCollections.observableArrayList(TipoPagamento.values());
        tipoPagamento.setItems(tipoPagamentoList);
        tipoPagamento.getSelectionModel().selectFirst();


        /*INICIALIZA TABELA PAGAMENTO */
        this.idPagamentoColumn.setCellValueFactory(new PropertyValueFactory<>("pagamentoID"));
        this.tipoPagamentoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoPagamento"));
        this.valorPagColumn.setCellValueFactory(new PropertyValueFactory<>("valor"));

        searchFatura.setOnAction(event -> refreshPagamentos());



    }
}

