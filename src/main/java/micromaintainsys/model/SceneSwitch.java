package micromaintainsys.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import micromaintainsys.Main;
import micromaintainsys.control.*;
import micromaintainsys.control.management_Controllers.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class SceneSwitch {
    public SceneSwitch (AnchorPane currentAnchorPane, String fxml,Tecnico tecnicoLogado, int obj) throws IOException{
        String fxmlPath = "src/resources/" + fxml;
        File file = new File(fxmlPath);
        URL url = file.toURI().toURL();
        Tecnico tecnico = tecnicoLogado;
        int objId = obj;

        FXMLLoader fxmlLoader = new FXMLLoader(url);
        AnchorPane nextAnchorPane = fxmlLoader.load();


        if (fxml != "login.fxml"){
            if (fxml == "main.fxml"){
                ordersController ordController = fxmlLoader.getController();
                ordController.setTecnicoSessao(tecnico);
            }
            else if (fxml == "clientes.fxml"){
                clientesController cliController = fxmlLoader.getController();
                cliController.setTecnicoSessao(tecnico);
            }
            else if (fxml == "estoque.fxml"){
                estoqueController estController = fxmlLoader.getController();
                estController.setTecnicoSessao(tecnico);
            }
            else if (fxml == "faturas.fxml"){
                faturaController fatController = fxmlLoader.getController();
                fatController.setTecnicoSessao(tecnico);
            }
            else if (fxml == "tecnicos.fxml"){
                tecnicosController tecController = fxmlLoader.getController();
                tecController.setTecnicoSessao(tecnico);
            }
            else if (fxml == "ordem_compra.fxml"){
                ordemCompraController ordCompController = fxmlLoader.getController();
                ordCompController.setTecnicoSessao(tecnico);
            }
            else if (fxml == "ordens_Ger.fxml"){
                ordens_GerController ordGerController = fxmlLoader.getController();
                ordGerController.setTecnicoSessao(tecnico);
            }
            else if (fxml == "clientes_Ger.fxml"){
                cliente_GerController cliGerController = fxmlLoader.getController();
                cliGerController.setTecnicoSessao(tecnico);
            }
            else if (fxml == "estoque_Ger.fxml"){
                estoque_GerController estGerController = fxmlLoader.getController();
                estGerController.setTecnicoSessao(tecnico);
            }
            else if (fxml == "faturas_Ger.fxml"){
                faturas_GerController fatGerController = fxmlLoader.getController();
                fatGerController.setTecnicoSessao(tecnico);
            }
            //GERENCIAMENTO
            else if (fxml == "management_Scenes/cliente_GerController.fxml"){
                cliente_GerController cliGerController = fxmlLoader.getController();
                cliGerController.setTecnicoSessao(tecnico);
            }
            else if (fxml == "management_Scenes/estoque_GerController"){
                estoque_GerController estGerController = fxmlLoader.getController();
                estGerController.setTecnicoSessao(tecnico);
            } else if (fxml == "management_Scenes/faturas_GerController"){
                faturas_GerController fatGerController = fxmlLoader.getController();
                fatGerController.setTecnicoSessao(tecnico);
            }
            else if (fxml == "management_Scenes/ordens_GerController"){
                ordens_GerController ordGerController = fxmlLoader.getController();
                ordGerController.setTecnicoSessao(tecnico);
            }
            else if (fxml == "management_Scenes/servicos_GerController"){
                servicos_GerController serGerController = fxmlLoader.getController();
                serGerController.setTecnicoSessao(tecnico);
            }
            else {
                tec_GerController teGerController = fxmlLoader.getController();
                teGerController.setTecnicoSessao(tecnico);
            }

        }

        currentAnchorPane.getChildren().clear();
        currentAnchorPane.getChildren().setAll(nextAnchorPane);
    }
}
