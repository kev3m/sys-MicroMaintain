package micromaintainsys.control;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import micromaintainsys.model.SceneSwitch;

import java.io.IOException;

public class ordemCompraController {
    @FXML
    private AnchorPane ordemCompraAnchorPane;

    @FXML
    void switchToOrdem() throws IOException {
        new SceneSwitch(ordemCompraAnchorPane, "main.fxml");
    }
    @FXML
    void switchToTec() throws IOException {
        new SceneSwitch(ordemCompraAnchorPane, "tecnicos.fxml");
    }
    @FXML
    void switchToClientes() throws IOException {
        new SceneSwitch(ordemCompraAnchorPane, "clientes.fxml");
    }
    @FXML
    void switchToEstoque() throws IOException {
        new SceneSwitch(ordemCompraAnchorPane, "estoque.fxml");
    }
    @FXML
    void switchToFatura() throws IOException {
        new SceneSwitch(ordemCompraAnchorPane, "faturas.fxml");
    }
    @FXML
    void switchToOrdem_Compra() throws IOException {
        new SceneSwitch(ordemCompraAnchorPane, "ordem_compra.fxml");
    }

}
