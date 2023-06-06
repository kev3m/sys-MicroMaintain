package micromaintainsys.control.management_Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import micromaintainsys.dao.DAO;
import micromaintainsys.exceptions.AssemblyWithEmptyComponentException;
import micromaintainsys.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Queue;
import java.util.ResourceBundle;

import static micromaintainsys.utils.ViewUtils.*;


public class servicos_GerController implements Initializable {

    @FXML
    private AnchorPane tecnicosAnchorPane;
    private Queue<Ordem> ordensAbertas;
    private ArrayList<Ordem> ordensServico;
    private ArrayList<Fatura> faturas;
    private Estoque estoque;

    @FXML
    private TableColumn<Servico, Calendar> AbertColumn;

    @FXML
    private TableColumn<Servico, Double> AvColumn;

    @FXML
    private TableColumn<Servico, CategoriaServico> CategoriaColumn;

    @FXML
    private TableColumn<Servico, String> DescColumn;

    @FXML
    private TableColumn<Servico, Calendar> FinColumn;

    @FXML
    private TableColumn<Servico, Integer> IdColumn;

    @FXML
    private TableColumn<Servico, String> PeçaColumn;
    @FXML
    private CheckBox encerradoCheck;

    @FXML
    private Button UpdateButton;

    @FXML
    private Button UpdateButton1;

    @FXML
    private TableColumn<Servico, Double> ValorColumn;

    @FXML
    private ImageView closeButton;

    @FXML
    private TextField PecaAdd;

    @FXML
    private TextField DescAdd;

    @FXML
    private TextField ValorAdd;
    @FXML
    private Button searchOrderButton;

    @FXML
    private TextField idRemoveField1111;

    @FXML
    private TextField idSearchField;
    @FXML
    private TextField notaServico;

    @FXML
    private Button searchButton;

//    @FXML
//    private ChoiceBox<?> statusChoice;

    @FXML
    private ChoiceBox<CategoriaServico> categoriaServico;

    @FXML
    private TableView<Servico> tableView;

    @FXML
    private TextField tecFieldUpdate;

    @FXML
    private TextField OrdemID;


    @FXML
    void addServico() throws IOException {
        int id = Integer.parseInt(OrdemID.getText());
        String peca = PecaAdd.getText();
        String desc = DescAdd.getText();
        String valor = ValorAdd.getText();
        CategoriaServico categoria = categoriaServico.getValue();
        if (desc.isEmpty() || valor.isEmpty() || categoria == null){
            showErrorAlert("Campo vazio", "Por favor, verifique os campos");
        }
        else if (DAO.getOrdemDAO().pegaPorId(id) == null || OrdemID.getText().isEmpty()){
            showErrorAlert("ID de Ordem inválido ou Ordem não encontrada", "Por favor, verifique o campo de ID");
        }
        else if (categoria == CategoriaServico.Montagem && peca.isEmpty()){
            showErrorAlert("Serviço Invalido", "Para serviços de montagem, especifique a peça");
            throw new AssemblyWithEmptyComponentException();
        }
        else{
            DAO.getServicoDAO().cria(categoriaServico.getValue(), Double.parseDouble(valor), peca, desc, id);
            showInformationAlert("Serviço adicionado", "Serviço adicionado com sucesso");
            refreshTable();
        }

    }
    @FXML
    void refreshTable() {
        String ordemIdText = OrdemID.getText();
        if (!ordemIdText.isEmpty()) {
            int ordemId = Integer.parseInt(ordemIdText);
            ObservableList<Servico> servicos = FXCollections.observableArrayList(DAO.getServicoDAO().pegaTodosPorOrdemID(ordemId));

            tableView.setItems(servicos);
            tableView.refresh();
        } else {
            tableView.getItems().clear();
        }
    }


    @FXML
    void searchServico(){
        String id = idSearchField.getText();
        if (id.isEmpty() || DAO.getServicoDAO().pegaPorId(Integer.parseInt(id)) == null){
            showErrorAlert("Campo vazio ou ID inválido", "Por favor, verifique o campo de ID");
        }
        else{
            fillUpdateFields();
        }
    }


    void fillUpdateFields(){
        int id = Integer.parseInt(idSearchField.getText());
        Servico servico = DAO.getServicoDAO().pegaPorId(id);
        if (servico.getHorarioFinalizacao() == null){
            encerradoCheck.setSelected(false);
        }
        else{
            double nota = servico.getAvaliacaoCliente();
            showWarningAlert("Serviço já encerrado", "Este serviço já foi encerrado");
            if(nota == 0.0) {
                showWarningAlert("Avaliação não encontrada", "Avaliação não encontrada para este serviço");
            }
            else {
                notaServico.setText(String.valueOf(nota));
            }
            encerradoCheck.setSelected(true);

        }
    }

    @FXML
    void clearUpdateFields(){
        idSearchField.clear();
        notaServico.clear();
        encerradoCheck.setSelected(false);

    }
    @FXML
    void updateServico(){
        String idText = idSearchField.getText();
        String notaText = notaServico.getText();
        boolean encerrado = encerradoCheck.isSelected();
        if (idText.isEmpty() || notaText.isEmpty()){
            showWarningAlert("Campo vazio", "Para prosseguir, preencha todos os campos");
        }
        else if (DAO.getServicoDAO().pegaPorId(Integer.parseInt(idText)) == null){
            showErrorAlert("Serviço não encontrado", "Por favor, insira um ID válido");
        }
        else{
            Servico servico = DAO.getServicoDAO().pegaPorId(Integer.parseInt(idText));
            servico.avaliaServico(Double.parseDouble(notaText));
            if (encerrado){
                servico.encerraServico();
            }
            DAO.getServicoDAO().atualiza(servico);
            showInformationAlert("Serviço atualizado", "Serviço atualizado com sucesso");
            clearUpdateFields();
        }
    }
//    @FXML
//    void updateOrder(){
//        String idText = idField.getText();
//        String tecText = tecFieldUpdate.getText();
//        StatusOrdem status = statusChoice.getValue();
//        if (idText.isEmpty() || tecText.isEmpty() || status == null){
//            showWarningAlert("Campo vazio", "Para prosseguir, preencha todos os campos");
//        }
//        else if (DAO.getOrdemDAO().pegaPorId(Integer.parseInt(idText)) == null){
//            showErrorAlert("Ordem não encontrada", "Por favor, insira um ID válido");
//        }
//        else if (DAO.getTecnicoDAO().pegaPorId(Integer.parseInt(tecText)) == null){
//            showErrorAlert("Técnico não encontrado", "Por favor, insira um ID válido");
//        }
//        else{
//            Ordem ordem = DAO.getOrdemDAO().pegaPorId(Integer.parseInt(idText));
//            ordem.setStatus(status);
//            ordem.setTecnicoID(Integer.parseInt(tecText));
//            DAO.getOrdemDAO().atualiza(ordem);
//            showInformationAlert("Ordem atualizada", "A ordem foi atualizada com sucesso");
//        }
//    }
//    @FXML
//    void removeServico(){
//        String idText = idRemoveField1.getText();
//        if (idText.isEmpty() || DAO.getServicoDAO().pegaPorId(Integer.parseInt(idText)) == null){
//            showErrorAlert("Serviço não encontrado", "Por favor, insira um ID válido");
//        }
//        else{
//            DAO.getServicoDAO().remove(Integer.parseInt(idText));
//            showInformationAlert("Serviço removido", "O serviço foi removido com sucesso");
//            this.tableView.refresh();
//        }
//    }

    @FXML
    void switchToOrdem() throws IOException {
        new SceneSwitch(tecnicosAnchorPane, "main.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.IdColumn.setCellValueFactory(new PropertyValueFactory<>("servicoID"));
        this.DescColumn.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        this.AbertColumn.setCellValueFactory(new PropertyValueFactory<>("horarioAbertura"));
        this.FinColumn.setCellValueFactory(new PropertyValueFactory<>("horarioFinalizacao"));
        this.ValorColumn.setCellValueFactory(new PropertyValueFactory<>("valor"));
        this.AvColumn.setCellValueFactory(new PropertyValueFactory<>("avaliacaoCliente"));
        this.CategoriaColumn.setCellValueFactory(new PropertyValueFactory<>("categoriaServico"));
        this.PeçaColumn.setCellValueFactory(new PropertyValueFactory<>("peca"));

        ObservableList<CategoriaServico> observableListchoiceBox = FXCollections.observableArrayList(CategoriaServico.values());
        categoriaServico.setItems(observableListchoiceBox);
        categoriaServico.getSelectionModel().selectFirst();

        searchOrderButton.setOnAction(event -> refreshTable());

        numberFilter(OrdemID);
        numberFilter(idSearchField);
//        numberFilter(notaServico);


    }
}
