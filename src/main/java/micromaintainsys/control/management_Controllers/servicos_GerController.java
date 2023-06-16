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
import micromaintainsys.exceptions.NotAllowedException;
import micromaintainsys.exceptions.UserNotLoggedInException;
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
import java.text.SimpleDateFormat;
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
    private TextField idRemoveField1;


    @FXML
    private TextField OrdemID;


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
        else if (categoria != CategoriaServico.Montagem && !peca.isEmpty()){
            showErrorAlert("Serviço Invalido", "Para serviços que não sejam de montagem, deixe o campo de peça em branco");
        }
        else if (categoria == CategoriaServico.Montagem && estoque.getPecas().get(PecaAdd.getText()) == null){
            showErrorAlert("Peça não encontrada", "Por favor, verifique o estoque");
        }
        else if (categoria == CategoriaServico.Montagem && estoque.getPecas().get(PecaAdd.getText()) == 0){
            showErrorAlert("Quantia insuficiente de peça especificada", "Por favor, verifique o estoque");
        }
        else if (DAO.getOrdemDAO().pegaPorId(id).getStatus() == StatusOrdem.Pagamento || DAO.getOrdemDAO().pegaPorId(id).getStatus() == StatusOrdem.Finalizada){
            showErrorAlert("Ordem encerrada ou aguardando pagamento", "Não é possível remover um serviço de uma ordem encerrada ou aguardando pagamento");
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
                boolean ordensfechadas = verifaTodosOsServicosEncerrados(servico.getOrdemID());
                if (ordensfechadas == true){
                    showInformationAlert("Ordem aguardando pagamento", "Todos os serviços da ordem #" +servico.getOrdemID() + " foram encerrados.\n O status da ordem foi atualizado automaticamente");
                    Ordem ordem = DAO.getOrdemDAO().pegaPorId(servico.getOrdemID());
                    ordem.setStatus(StatusOrdem.Pagamento);
                    DAO.getOrdemDAO().atualiza(ordem);
                }
            }


        }
    }


     private boolean verifaTodosOsServicosEncerrados(int ordemID){
        ArrayList<Servico> servicos = DAO.getServicoDAO().pegaTodosPorOrdemID(ordemID);
        for (Servico servico : servicos){
            if (servico.getHorarioFinalizacao() == null){
                return false;
            }
        }
         return true;
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
            Servico servico = DAO.getServicoDAO().pegaPorId(Integer.parseInt(idText));
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

            //TX - POSIÇÃO HORINZONTAL -- TY: POSIÇÃO VERTICAL

            // Título do relatório
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 770);
            contentStream.showText("Relatório do Serviço: #" + idText);
            contentStream.endText();
// Cabeçalho da tabela
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("Categoria do Serviço");
            contentStream.newLineAtOffset(200, 0);
            contentStream.showText("Descrição do Serviço");
            contentStream.newLineAtOffset(150, 0);
            contentStream.showText("Valor do Serviço");
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(50, 680);
            contentStream.showText("--------------");
            contentStream.newLineAtOffset(200, 0);
            contentStream.showText("--------------");
            contentStream.newLineAtOffset(150, 0);
            contentStream.showText("--------------");
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(50, 660);
            contentStream.showText(servico.getCategoriaServico().name());
            contentStream.newLineAtOffset(200, 0);
            contentStream.showText(servico.getDescricao());
            contentStream.newLineAtOffset(150, 0);
            contentStream.showText("R$ " + (servico.getValor()));
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(50, 540);
            contentStream.showText("Peças Utilizadas");
            contentStream.newLineAtOffset(200, 0);
            contentStream.showText("-------------->");
            contentStream.newLineAtOffset(150, 0);
            if (servico.getPeca() == null) {
                contentStream.showText("Serviço não inclui peça");
            } else {
                contentStream.showText(servico.getPeca());
            }
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(50, 520);
            contentStream.showText("Tempo Médio");
            contentStream.newLineAtOffset(200, 0);
            contentStream.showText("-------------->");
            contentStream.newLineAtOffset(150, 0);
            contentStream.showText(calcularTempoEspera(servico.getHorarioAbertura(), servico.getHorarioFinalizacao()));
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(50, 500);
            contentStream.showText("Avaliação do Cliente");
            contentStream.newLineAtOffset(200, 0);
            contentStream.showText("-------------->");
            contentStream.newLineAtOffset(150, 0);
            contentStream.showText(Double.toString(servico.getAvaliacaoCliente()));
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

    private static String calcularTempoEspera(Calendar horarioAbertura, Calendar horarioFinalizacao) {
        long diferencaMillis = horarioFinalizacao.getTimeInMillis() - horarioAbertura.getTimeInMillis();

        long segundos = diferencaMillis / 1000;
        long minutos = segundos / 60;
        long horas = minutos / 60;
        long dias = horas / 24;

        segundos %= 60;
        minutos %= 60;
        horas %= 24;

        return  dias + " d, " + horas + " h, " + minutos + " m, " + segundos + " s";
    }
    @FXML
    void removeServico(){
        String idText = idRemoveField1.getText();
        if (idText.isEmpty() || DAO.getServicoDAO().pegaPorId(Integer.parseInt(idText)) == null){
            showErrorAlert("Serviço não encontrado", "Por favor, insira um ID válido");
        }
        else if (DAO.getOrdemDAO().pegaPorId(DAO.getServicoDAO().pegaPorId(Integer.parseInt(idText)).getOrdemID()).getStatus() == StatusOrdem.Pagamento || DAO.getOrdemDAO().pegaPorId(DAO.getServicoDAO().pegaPorId(Integer.parseInt(idText)).getOrdemID()).getStatus() == StatusOrdem.Finalizada){
            showErrorAlert("Ordem encerrada", "Não é possível remover um serviço de uma ordem encerrada");
        }
        else if (DAO.getServicoDAO().pegaPorId(Integer.parseInt(idText)).getHorarioFinalizacao() != null){
            showErrorAlert("Serviço encerrado", "Não é possível remover um serviço encerrado");
        }
        else{
            DAO.getServicoDAO().remove(Integer.parseInt(idText));
            showInformationAlert("Serviço removido", "O serviço foi removido com sucesso");
            refreshTable();
        }
    }

    @FXML
    void switchToOrdem() throws IOException {
        new SceneSwitch(tecnicosAnchorPane, "main.fxml", tecnicoSessao, objID);
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
