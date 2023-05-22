package micromaintainsys;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.File;
import java.net.URL;


public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        String fxmlPath = "src/resources/login.fxml";
        File file = new File(fxmlPath);
        URL url = file.toURI().toURL();

        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene);
        primaryStage.show();



    }
    public static void main(String[] args){
        launch(args);
    }

}