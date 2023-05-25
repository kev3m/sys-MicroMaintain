package micromaintainsys.control;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import micromaintainsys.model.SceneSwitch;

import java.io.IOException;

public class faturaController {
    @FXML
    private AnchorPane faturaAnchor;
    @FXML
    void switchToOrdem() throws IOException {
        new SceneSwitch(faturaAnchor, "main.fxml");
    }
    @FXML
    void switchToTec() throws IOException {
        new SceneSwitch(faturaAnchor, "tecnicos.fxml");
    }
    @FXML
    void switchToClientes() throws IOException {
        new SceneSwitch(faturaAnchor, "clientes.fxml");
    }
    @FXML
    void switchToEstoque() throws IOException {
        new SceneSwitch(faturaAnchor, "estoque.fxml");
    }
    @FXML
    void switchToFatura() throws IOException {
        new SceneSwitch(faturaAnchor, "faturas.fxml");
    }
    @FXML
    void switchToOrdem_Compra() throws IOException {
        new SceneSwitch(faturaAnchor, "ordem_compra.fxml");
    }

}
