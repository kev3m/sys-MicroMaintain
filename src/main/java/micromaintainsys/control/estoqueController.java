package micromaintainsys.control;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import micromaintainsys.model.SceneSwitch;

import java.io.IOException;

public class estoqueController {

    @FXML
    private ImageView closeButton;

    @FXML
    private AnchorPane estoqueAnchorPane;

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
    void switchToFatura() throws IOException {
        new SceneSwitch(estoqueAnchorPane, "faturas.fxml");
    }
    @FXML
    void switchToOrdem_Compra() throws IOException {
        new SceneSwitch(estoqueAnchorPane, "ordem_compra.fxml");
    }
}
