package micromaintainsys.control.management_Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import micromaintainsys.dao.DAO;
import micromaintainsys.model.*;
import static micromaintainsys.utils.ViewUtils.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ResourceBundle;




public class cliente_GerController implements Initializable {

    @FXML
    private AnchorPane clientesAnchorPane;
    private Queue<Ordem> ordensAbertas;
    private ArrayList<Ordem> ordensServico;
    private ArrayList<Fatura> faturas;
    private Estoque estoque;

    @FXML
    private TextField nameField;

    @FXML
    private TextField adressField;
    @FXML
    private TextField phoneField;

    @FXML
    private TextField phoneFieldUpdate;
    @FXML
    private Button searchButton;
    @FXML
    private Button UpdateButton;
    @FXML
    private TextField nameFieldUpdate;
    @FXML
    private TextField adressFieldUpdate;
    @FXML
    private TextField idField;
    @FXML
    private TextField idRemoveField;
    @FXML
    private ListView<Ordem> clientOrders;
    @FXML
    private CheckBox admCheck;
    private Tecnico tecnicoSessao;
    private int objID;

    public cliente_GerController() {
    }


    @FXML
    void addClient() throws IOException {
        if (nameField.getText().isEmpty() || adressField.getText().isEmpty() || phoneField.getText().isEmpty()){
            showWarningAlert("Campo vazio", "Para prosseguir, preencha todos os campos");
        }
        else{
            String nome = nameField.getText();
            String endereco = adressField.getText();
            String telefone = phoneField.getText();
            Cliente cliente = new Cliente(nome, endereco, telefone);
            DAO.getClienteDAO().cria(cliente.getName(), cliente.getEndereco(), cliente.getTelefone());
            new SceneSwitch(clientesAnchorPane, "clientes.fxml", tecnicoSessao, objID);
        }
    }

    @FXML
    void searchCliente(){
        String id = idField.getText();
        if (id.isEmpty() || DAO.getClienteDAO().pegaPorId(Integer.parseInt(id)) == null){
            showErrorAlert("Campo vazio ou ID inválido", "Por favor, verifique o campo de ID");
        }
        else{
            fillUpdateFields();
        }
    }

    void fillUpdateFields(){
        int id = Integer.parseInt(idField.getText());
        String name = DAO.getClienteDAO().pegaPorId(id).getName();
        String adress = DAO.getClienteDAO().pegaPorId(id).getEndereco();
        String phone = DAO.getClienteDAO().pegaPorId(id).getTelefone();
        ArrayList<Ordem> ordens = DAO.getClienteDAO().pegaPorId(id).getOrdens();
        nameFieldUpdate.setText(name);
        adressFieldUpdate.setText(adress);
        phoneFieldUpdate.setText(phone);

        ObservableList<Ordem> observableList = FXCollections.observableArrayList(ordens);
        clientOrders.setItems(observableList);

    }

    @FXML
    void clearUpdateFields(){
        nameFieldUpdate.setText("");
        adressFieldUpdate.setText("");
        phoneFieldUpdate.setText("");

    }
    @FXML
    void updateCliente(){
        String idText = idField.getText();
        String name = nameFieldUpdate.getText();
        String adress = adressFieldUpdate.getText();
        String phone = phoneFieldUpdate.getText();
        if (idText.isEmpty() || DAO.getClienteDAO().pegaPorId(Integer.parseInt(idText)) == null){
            showErrorAlert("Campo vazio ou ID inválido", "Por favor, verifique o campo de ID");
        }
        else{
            if (nameFieldUpdate.getText().isEmpty() || adressFieldUpdate.getText().isEmpty() || phoneFieldUpdate.getText().isEmpty()){
                showWarningAlert("Campo vazio", "Para prosseguir, preencha todos os campos");
            }
            else {
                Cliente cliente = DAO.getClienteDAO().pegaPorId(Integer.parseInt(idText));
                cliente.setNome(name);
                cliente.setEndereco(adress);
                cliente.setTelefone(phone);
                DAO.getClienteDAO().atualiza(cliente);
                showInformationAlert("Cliente atualizado", "O cliente foi atualizado com sucesso");
            }
        }
    }
    @FXML
    void removeClient(){
        String idText = idRemoveField.getText();
        if (idText.isEmpty() || DAO.getClienteDAO().pegaPorId(Integer.parseInt(idText)) == null){
            showErrorAlert("Campo vazio ou ID inválido", "Por favor, verifique o campo de ID");

        }
        else{
            DAO.getClienteDAO().remove(Integer.parseInt(idText));
            showInformationAlert("Cliente removido", "O cliente foi removido com sucesso");
        }

    }

    @FXML
    void switchToOrdem() throws IOException {
        new SceneSwitch(clientesAnchorPane, "main.fxml", tecnicoSessao, objID);
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



    ObservableList<Tecnico> observableList = FXCollections.observableArrayList(DAO.getTecnicoDAO().pegaTodos());
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configurar o filtro de entrada para aceitar somente números
            numberFilter(idField);
            numberFilter(idRemoveField);
            phoneFilter(phoneField);
            phoneFilter(phoneFieldUpdate);
//            numberFilter(OrderFieldUpdate);

        // Configura o filtro de entrada para aceitar letras
            letterFilter(nameField);
            letterFilter(nameFieldUpdate);
            letterFilter(adressField);



    }
}
