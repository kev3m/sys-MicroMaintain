package micromaintainsys.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import micromaintainsys.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class SceneSwitch {
    public SceneSwitch (AnchorPane currentAnchorPane, String fxml) throws IOException{
        String fxmlPath = "src/resources/" + fxml;
        File file = new File(fxmlPath);
        URL url = file.toURI().toURL();

        FXMLLoader fxmlLoader = new FXMLLoader(url);
        AnchorPane nextAnchorPane = fxmlLoader.load();

        currentAnchorPane.getChildren().clear();
        currentAnchorPane.getChildren().setAll(nextAnchorPane);
    }
}
