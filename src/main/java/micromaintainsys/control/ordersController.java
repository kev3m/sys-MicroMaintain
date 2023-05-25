package micromaintainsys.control;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import micromaintainsys.model.SceneSwitch;

import java.io.IOException;

public class ordersController {

    @FXML
    private ImageView closeButton;

    @FXML
    private AnchorPane ordersAnchorPane;

    @FXML
    void switchToOrdem() throws IOException {
        new SceneSwitch(ordersAnchorPane, "main.fxml");
    }
    @FXML
    void switchToTec() throws IOException {
        new SceneSwitch(ordersAnchorPane, "tecnicos.fxml");
    }
    @FXML
    void switchToClientes() throws IOException {
        new SceneSwitch(ordersAnchorPane, "clientes.fxml");
    }
    @FXML
    void switchToEstoque() throws IOException {
        new SceneSwitch(ordersAnchorPane, "estoque.fxml");
    }
    @FXML
    void switchToFatura() throws IOException {
        new SceneSwitch(ordersAnchorPane, "faturas.fxml");
    }
    @FXML
    void switchToOrdem_Compra() throws IOException {
        new SceneSwitch(ordersAnchorPane, "ordem_compra.fxml");
    }



}
