package micromaintainsys;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import micromaintainsys.dao.DAO;


import java.io.File;
import java.net.URL;


public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{

        String fxmlPath = "src/resources/login.fxml";
        File file = new File(fxmlPath);
        URL url = file.toURI().toURL();

        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load());

        primaryStage.setResizable(false);
        primaryStage.setTitle("Micro MaintainSys");
        primaryStage.setScene(scene);
        primaryStage.show();



    }
    public static void main(String[] args){
        /*Apenas pra criar o admin, caso seja a primeira execução*/
        DAO.getTecnicoDAO().pegaTodos();
        launch(args);
    }

}