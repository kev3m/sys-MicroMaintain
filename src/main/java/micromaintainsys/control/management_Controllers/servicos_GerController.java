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
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.awt.Desktop;
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
    private Tecnico tecnicoSessao;
    private int objID;

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
    private TextField idServicoRelatorio;
    @FXML
    private TextField idSearchField;
    @FXML
    private TextField notaServico;

    @FXML
    private Button searchButton;

    @FXML
    private ChoiceBox<CategoriaServico> categoriaServico;

    @FXML
    private TableView<Servico> tableView;

    @FXML
    private TextField tecFieldUpdate;

    @FXML
    private TextField OrdemID;

    public void initializeServicoID(int servicoID) {
        this.OrdemID = new TextField(String.valueOf(servicoID));

        // Usa o servicoID na inicialização da página
    }

    @FXML
    void addServico() throws IOException {
        int id = Integer.parseInt(OrdemID.getText());
        String peca = PecaAdd.getText();
        String desc = DescAdd.getText();
        String valor = ValorAdd.getText();
        CategoriaServico categoria = categoriaServico.getValue();
        estoque = DAO.getEstoqueDAO().carrega();
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
        else if (categoria == CategoriaServico.Montagem && estoque.getPecas().get(PecaAdd.getText()) == null || estoque.getPecas().get(PecaAdd.getText()) == 0){
            showErrorAlert("Peça não encontrada ou quantia insuficiente", "Por favor, verifique o estoque");
        }
        else{
            DAO.getServicoDAO().cria(categoriaServico.getValue(), Double.parseDouble(valor), peca, desc, id);
            showInformationAlert("Serviço adicionado", "Serviço adicionado com sucesso");
            refreshTable();
            if (categoria == CategoriaServico.Montagem){
                estoque.getPecas().put(peca,estoque.getPecas().get(peca) - 1);
                DAO.getEstoqueDAO().atualiza(estoque);
            }
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
        else if (Double.parseDouble(notaText) < 0 || Double.parseDouble(notaText) > 10){
            showErrorAlert("Nota inválida", "Por favor, insira uma nota entre 0 e 10");
        }
        else if(encerrado && DAO.getServicoDAO().pegaPorId(Integer.parseInt(idText)).getHorarioFinalizacao() != null){
            showWarningAlert("Serviço encerrado", "Este serviço já foi encerrado e não pode ser atualizado");
        }
        else{
            Servico servico = DAO.getServicoDAO().pegaPorId(Integer.parseInt(idText));
            servico.avaliaServico(Double.parseDouble(notaText));
            if (!encerrado && !notaText.isEmpty()){
                showWarningAlert("Serviço não encerrado", "Por favor, marque o serviço como encerrado para avaliá-lo");
            }
            else {
                servico.avaliaServico(Double.parseDouble(notaText));
                servico.encerraServico();
                DAO.getServicoDAO().atualiza(servico);
                showInformationAlert("Serviço atualizado", "Serviço atualizado com sucesso");
                clearUpdateFields();
                refreshTable();
            }


        }
    }

    @FXML
    void gerarRelatorio() throws IOException {
        String idText = idServicoRelatorio.getText();
        boolean servicoEncerrado = DAO.getServicoDAO().pegaPorId(Integer.parseInt(idText)).foiEncerrado();
        if (idText.isEmpty() || DAO.getServicoDAO().pegaPorId(Integer.parseInt(idText)) == null) {
            showWarningAlert("Serviço não encontrado", "Por favor, insira um ID válido");
        }
        else if(servicoEncerrado == false){
            showWarningAlert("Serviço não encerrado", "Este serviço ainda não foi encerrado");
        }
        else {
            // Cria um novo documento PDF
            PDDocument document = new PDDocument();

            // Cria uma nova página no documento
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            File fontFile = new File("src/resources/assets/fonts/Poppins-Regular.ttf");
            PDType0Font font = PDType0Font.load(document, fontFile);


            // Cria o conteúdo da página
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Define a fonte e o tamanho do texto
            contentStream.setFont(font, 12);

            // Título do relatório
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("Relatório do Serviço: #" + idText);
            contentStream.endText();

            // Cabeçalho da tabela
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 670);
            contentStream.showText("Produto");
            contentStream.newLineAtOffset(150, 0);
            contentStream.showText("Quantidade");
            contentStream.newLineAtOffset(150, 0);
            contentStream.showText("Valor");
            contentStream.endText();

            // Conteúdo da tabela
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 650);
            contentStream.showText("Produto A");
            contentStream.newLineAtOffset(150, 0);
            contentStream.showText("10");
            contentStream.newLineAtOffset(150, 0);
            contentStream.showText("R$ 100,00");
            contentStream.endText();

            // Fecha o conteúdo da página
            contentStream.close();
            if (Desktop.isDesktopSupported()) {
                // Obter a instância do Desktop
                Desktop desktop = Desktop.getDesktop();

                // Verificar se a ação OPEN é suportada
                if (desktop.isSupported(Desktop.Action.OPEN)) {
                    // Obter o diretório da área de trabalho
                    String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
                    document.save(new File(desktopPath + File.separator + "relatorio_servico#"+ idText +".pdf"));

                }
            // Fecha o documento
            document.close();
            System.out.println("Relatório gerado com sucesso!");
        }
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
        new SceneSwitch(tecnicosAnchorPane, "main.fxml", tecnicoSessao, objID);
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
