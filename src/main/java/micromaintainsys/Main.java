package micromaintainsys;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.net.URL;


public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();

        URL fxmlURL = getClass().getResource("src/resources/micromaintain.fxml");
        Parent root = loader.load(fxmlURL);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("MicroMaintainSys");
        primaryStage.show();



    }
    public static void main(String[] args){
        launch(args);
    }

}