package micromaintainsys.control.management_Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import micromaintainsys.dao.DAO;
import micromaintainsys.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ResourceBundle;

import static micromaintainsys.utils.ViewUtils.*;


public class estoque_GerController implements Initializable {

    @FXML
    private AnchorPane tecnicosAnchorPane;
    private Queue<Ordem> ordensAbertas;
    private ArrayList<Ordem> ordensServico;
    private ArrayList<Fatura> faturas;
    private Estoque estoque;
    private Tecnico tecnicoSessao;
    private int objID;

    @FXML
    private Button addPeca;

    @FXML
    private Button addTecButton;

    @FXML
    private Button deletePeca;

    @FXML
    private TextField pecaDelete;

    @FXML
    private TextField pecaName;

    @FXML
    private TextField pecaNameUpdate;

    @FXML
    private TextField quantPeca;

    @FXML
    private TextField quantUpdate;

    @FXML
    private Button removePecas;

    @FXML
    private Button tecButton;

    @FXML
    private TextField valorUn;


    @FXML
    void efetuarOrdem_Compra(){
        String name = pecaName.getText();
        String quant = quantPeca.getText();
        String valor = valorUn.getText();
        if (name.isEmpty() || quant.isEmpty() || valor.isEmpty()){
            showErrorAlert("Campo vazio", "Por favor, verifique os campos");
        }
        else{
            compraPeca(name, Integer.parseInt(quant), Double.parseDouble(valor));
        }

    }
    public void compraPeca(String peca, int quantidade, double valorUnitario){
        OrdemCompra novaOrdem = new OrdemCompra(peca, quantidade, valorUnitario);
        estoque = DAO.getEstoqueDAO().carrega();
        estoque.criaOrdemCompra(novaOrdem);
        estoque.adicionaPeca(peca, quantidade);
        DAO.getEstoqueDAO().atualiza(estoque);
        showInformationAlert("Ordem de compra efetuada", "A ordem de compra foi efetuada com sucesso");

    }
    @FXML
    void addPeca() throws IOException {
        if (pecaNameUpdate.getText().isEmpty() || quantUpdate.getText().isEmpty()){
            showWarningAlert("Campo vazio", "Para prosseguir, preencha todos os campos");
        }
        else{
            String name = pecaNameUpdate.getText();
            estoque = DAO.getEstoqueDAO().carrega();
            int quant = Integer.parseInt(quantUpdate.getText());
            estoque.adicionaPeca(name, quant);
            DAO.getEstoqueDAO().atualiza(estoque);
            showInformationAlert("Peça adicionada com sucesso", "A peça " + name + " foi adicionada ao estoque");
        }
    }

    @FXML
    void removePeca() {
        if (pecaName.getText().isEmpty() || quantPeca.getText().isEmpty() || valorUn.getText().isEmpty()){
            showWarningAlert("Campo vazio", "Para prosseguir, preencha todos os campos");
        }
        else{
            String name = pecaName.getText();
            int quant = Integer.parseInt(quantPeca.getText());
            DAO.getEstoqueDAO().carrega().removePeca(name, quant);
            showInformationAlert("Peça removida com sucesso", "Foram removidas" + quant + "" + name);
        }

    }

    @FXML
    void deletePeca() {
        String name = pecaDelete.getText();
        int estoquePeca = DAO.getEstoqueDAO().carrega().pegaEstoqueDePeca(name);
        if (name.isEmpty() || estoquePeca == 0){
            showErrorAlert("Campo vazio ou peça não encontrada", "Por favor, verifique o campo de nome");
        }
        else{
            DAO.getEstoqueDAO().carrega().deletaPeca(name);
            showInformationAlert("Peça removida com sucesso", "A peça " + name + " foi removida do estoque");
        }

    }

    @FXML
    void switchToOrdem() throws IOException {
        new SceneSwitch(tecnicosAnchorPane, "main.fxml", tecnicoSessao, objID);
    }
    @FXML
    void switchToTec() throws IOException {
        new SceneSwitch(tecnicosAnchorPane, "tecnicos.fxml", tecnicoSessao, objID);
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
        // Configurar o filtro de entrada para aceitar somente números
            numberFilter(quantPeca);
            numberFilter(quantUpdate);
            priceFilter(valorUn);

        // Configura o filtro de entrada para aceitar letras
            letterFilter(pecaName);
            letterFilter(pecaNameUpdate);
            letterFilter(pecaDelete);



    }
}
