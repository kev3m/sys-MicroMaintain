package micromaintainsys.control.management_Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import micromaintainsys.dao.DAO;
import micromaintainsys.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ResourceBundle;

import static micromaintainsys.utils.ViewUtils.*;


public class ordens_GerController implements Initializable {

    @FXML
    private AnchorPane tecnicosAnchorPane;
    private Queue<Ordem> ordensAbertas;
    private ArrayList<Ordem> ordensServico;
    private ArrayList<Fatura> faturas;
    private Estoque estoque;

    @FXML
    private Button UpdateButton;

    @FXML
    private Button UpdateButton1;

    @FXML
    private Button addTecButton;

    @FXML
    private ImageView closeButton;

    @FXML
    private TextField idField;

    @FXML
    private TextField idRemoveField1;

    @FXML
    private TextField tecField;

    @FXML
    private TextField nameFieldUpdate;

    @FXML
    private Button searchButton;

    @FXML
    private Button servicosButton;

    @FXML
    private Button servicosPageButtonUp;

    @FXML
    private Button tecButton;
    @FXML
    private ChoiceBox<?> statusChoice;

    @FXML
    void addOrdem() throws IOException {
        if (idField.getText().isEmpty()){
            showWarningAlert("Campo vazio", "Para prosseguir, preencha todos os campos");
        }
        else if (DAO.getClienteDAO().pegaPorId(Integer.parseInt(idField.getText())) == null){
            showErrorAlert("Cliente não encontrado", "Por favor, insira um ID válido");
        }
        else{
            int id = Integer.parseInt(idField.getText());
            Cliente cliente = DAO.getClienteDAO().pegaPorId(id);
            Ordem ordem = new Ordem(cliente.getId());
            DAO.getOrdemDAO().cria(ordem.getClienteID());
            new SceneSwitch(tecnicosAnchorPane, "tecnicos.fxml");

        }

    }

    @FXML
    void searchTec(){
        String id = idField.getText();
        if (id.isEmpty() || DAO.getTecnicoDAO().pegaPorId(Integer.parseInt(id)) == null){
            showErrorAlert("Campo vazio ou ID inválido", "Por favor, verifique o campo de ID");
        }
        else{
            fillUpdateFields();
        }
    }


    void fillUpdateFields(){
        int id = Integer.parseInt(idField.getText());
        String name = DAO.getTecnicoDAO().pegaPorId(id).getNome();
        String pass = DAO.getTecnicoDAO().pegaPorId(id).getSenha();
        int order = DAO.getTecnicoDAO().pegaPorId(id).getOrdemEmAndamentoID();
        boolean adm = DAO.getTecnicoDAO().pegaPorId(id).isAdm();
        nameFieldUpdate.setText(name);
        passFieldUpdate.setText(pass);
        OrderFieldUpdate.setText(String.valueOf(order));
        admCheck.setSelected(adm);
    }

    @FXML
    void clearUpdateFields(){
        nameFieldUpdate.setText("");
        passFieldUpdate.setText("");
        OrderFieldUpdate.setText("");
        admCheck.setSelected(false);

    }
    @FXML
    void updateTec(){
        String idText = idField.getText();
        String name = nameFieldUpdate.getText();
        String pass = passFieldUpdate.getText();
        String order = OrderFieldUpdate.getText();
        boolean adm = admCheck.isSelected();
        if (idText.isEmpty() || DAO.getTecnicoDAO().pegaPorId(Integer.parseInt(idText)) == null){
            showErrorAlert("Campo vazio ou ID inválido", "Por favor, verifique o campo de ID");
        }
        else{
            if (nameFieldUpdate.getText().isEmpty() || passFieldUpdate.getText().isEmpty() || OrderFieldUpdate.getText().isEmpty()){
                showWarningAlert("Campo vazio", "Para prosseguir, preencha todos os campos");
            }
            else {
                Tecnico tecnico = DAO.getTecnicoDAO().pegaPorId(Integer.parseInt(idText));
                tecnico.setNome(name);
                tecnico.setSenha(pass);
                tecnico.setOrdemEmAndamentoID(Integer.parseInt(order));
                tecnico.setAdm(adm);
                DAO.getTecnicoDAO().atualiza(tecnico);
                showInformationAlert("Técnico atualizado", "O técnico foi atualizado com sucesso");
            }
        }
    }
    @FXML
    void removeTec(){
        String idText = idRemoveField.getText();
        if (idText.isEmpty() || DAO.getTecnicoDAO().pegaPorId(Integer.parseInt(idText)) == null){
            showErrorAlert("Campo vazio ou ID inválido", "Por favor, verifique o campo de ID");

        }
        else{
            DAO.getTecnicoDAO().remove(Integer.parseInt(idText));
            showInformationAlert("Técnico removido", "O técnico foi removido com sucesso");
        }

    }

    @FXML
    void switchToOrdem() throws IOException {
        new SceneSwitch(tecnicosAnchorPane, "main.fxml");
    }
    @FXML
    void switchToTec() throws IOException {
        new SceneSwitch(tecnicosAnchorPane, "tecnicos.fxml");
    }
    @FXML
    void switchToClientes() throws IOException {
        new SceneSwitch(tecnicosAnchorPane, "clientes.fxml");
    }
    @FXML
    void switchToEstoque() throws IOException {
        new SceneSwitch(tecnicosAnchorPane, "estoque.fxml");
    }
    @FXML
    void switchToFatura() throws IOException {
        new SceneSwitch(tecnicosAnchorPane, "faturas.fxml");
    }
    @FXML
    void switchToOrdem_Compra() throws IOException {
        new SceneSwitch(tecnicosAnchorPane, "ordem_compra.fxml");
    }

    ObservableList<Tecnico> observableList = FXCollections.observableArrayList(DAO.getTecnicoDAO().pegaTodos());
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configurar o filtro de entrada para aceitar somente números
            numberFilter(idField);
            numberFilter(idRemoveField);
//            numberFilter(OrderFieldUpdate);

        // Configura o filtro de entrada para aceitar letras
            letterFilter(nameField);
            letterFilter(nameFieldUpdate);



    }
}
