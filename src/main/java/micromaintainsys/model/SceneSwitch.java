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
            if (fxml.equals("main.fxml") ){
                ordersController ordController = fxmlLoader.getController();
                ordController.setTecnicoSessao(tecnico);
            }
            else if (fxml.equals("clientes.fxml") ){
                clientesController cliController = fxmlLoader.getController();
                cliController.setTecnicoSessao(tecnico);
            }
            else if (fxml.equals("estoque.fxml") ){
                estoqueController estController = fxmlLoader.getController();
                estController.setTecnicoSessao(tecnico);
            }
            else if (fxml.equals("faturas.fxml") ){
                faturaController fatController = fxmlLoader.getController();
                fatController.setTecnicoSessao(tecnico);
            }
            else if (fxml.equals("tecnicos.fxml") ){
                tecnicosController tecController = fxmlLoader.getController();
                tecController.setTecnicoSessao(tecnico);
            }
            else if (fxml.equals("ordem_compra.fxml") ){
                ordemCompraController ordCompController = fxmlLoader.getController();
                ordCompController.setTecnicoSessao(tecnico);
            }
            else if (fxml.equals("ordens_Ger.fxml") ){
                ordens_GerController ordGerController = fxmlLoader.getController();
                ordGerController.setTecnicoSessao(tecnico);
            }
            else if (fxml.equals("clientes_Ger.fxml") ){
                cliente_GerController cliGerController = fxmlLoader.getController();
                cliGerController.setTecnicoSessao(tecnico);
            }
            else if (fxml.equals("estoque_Ger.fxml") ){
                estoque_GerController estGerController = fxmlLoader.getController();
                estGerController.setTecnicoSessao(tecnico);
            }
            else if (fxml.equals("faturas_Ger.fxml") ){
                faturas_GerController fatGerController = fxmlLoader.getController();
                fatGerController.setTecnicoSessao(tecnico);
            }
            //GERENCIAMENTO
            else if (fxml.equals("management_Scenes/clientes_ger.fxml") ){
                cliente_GerController cliGerController = fxmlLoader.getController();
                cliGerController.setTecnicoSessao(tecnico);
            }
            else if (fxml.equals("management_Scenes/estoque_ger.fxml") ){
                estoque_GerController estGerController = fxmlLoader.getController();
                estGerController.setTecnicoSessao(tecnico);
            } else if (fxml.equals("management_Scenes/fatura_ger.fxml") ){
                faturas_GerController fatGerController = fxmlLoader.getController();
                fatGerController.setTecnicoSessao(tecnico);
            }
            else if (fxml.equals("management_Scenes/ordens_ger.fxml") ){
                ordens_GerController ordGerController = fxmlLoader.getController();
                ordGerController.setTecnicoSessao(tecnico);
            }
            else if (fxml.equals("management_Scenes/servicos_ger.fxml") ){
                servicos_GerController serGerController = fxmlLoader.getController();
                serGerController.setTecnicoSessao(tecnico);
                if (objId != 0){
                    serGerController.setOrdertoSearch(objId);
                }
            }
            else if(fxml.equals("management_Scenes/tecnicos_ger.fxml")) {
                    tec_GerController teGerController = fxmlLoader.getController();
                    teGerController.setTecnicoSessao(tecnico);

            }

        }

        currentAnchorPane.getChildren().clear();
        currentAnchorPane.getChildren().setAll(nextAnchorPane);
    }
}
