package micromaintainsys.control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import micromaintainsys.dao.DAO;
import micromaintainsys.model.Estoque;
import micromaintainsys.model.SceneSwitch;

import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.ResourceBundle;

public class estoqueController implements Initializable {

    @FXML
    private ImageView closeButton;

    @FXML
    private AnchorPane estoqueAnchorPane;
    @FXML
    private TableView<PecaQuantidade> tableView;
    @FXML
    private TableColumn<PecaQuantidade, String> pecaColumn;
    @FXML
    private TableColumn<PecaQuantidade, Integer> quantColumn;

    private void exibirEstoque(){
        tableView.getItems().clear();
        Hashtable<String,Integer> pecas = DAO.getEstoqueDAO().carrega().getPecas();
        for(String peca : pecas.keySet()){
            tableView.getItems().add(new PecaQuantidade(peca,pecas.get(peca)));
        }
    }
    public static class PecaQuantidade {
        private String peca;
        private int quantidade;

        public PecaQuantidade(String peca, int quantidade) {
            this.peca = peca;
            this.quantidade = quantidade;
        }
        public String getPeca() {
            return peca;
        }
        public int getQuantidade() {
            return quantidade;
        }
    }

    @FXML
    void switchToOrdem() throws IOException {
        new SceneSwitch(estoqueAnchorPane, "main.fxml");
    }
    @FXML
    void switchToTec() throws IOException {
        new SceneSwitch(estoqueAnchorPane, "tecnicos.fxml");
    }
    @FXML
    void switchToClientes() throws IOException {
        new SceneSwitch(estoqueAnchorPane, "clientes.fxml");
    }
    @FXML
    void switchToEstoque() throws IOException {
        new SceneSwitch(estoqueAnchorPane, "estoque.fxml");
    }
    @FXML
    void switchToGerEstoque() throws IOException {
        new SceneSwitch(estoqueAnchorPane, "management_Scenes/estoque_ger.fxml");
    }
    @FXML
    void switchToFatura() throws IOException {
        new SceneSwitch(estoqueAnchorPane, "faturas.fxml");
    }
    @FXML
    void switchToOrdem_Compra() throws IOException {
        new SceneSwitch(estoqueAnchorPane, "ordem_compra.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pecaColumn.setCellValueFactory(new PropertyValueFactory<>("peca"));
        quantColumn.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        exibirEstoque();
    }
}
