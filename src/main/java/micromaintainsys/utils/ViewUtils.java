package micromaintainsys.utils;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import micromaintainsys.model.Tecnico;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ViewUtils {


    public static void showWarningAlert(String header, String content) {
        showAlert(Alert.AlertType.WARNING, "Warning", header, content);
    }
    public static void showErrorAlert(String header, String content) {
        showAlert(Alert.AlertType.ERROR, "Erro!", header, content);
    }
    public static void showConfirmationAlert(String header, String content) {
        showAlert(Alert.AlertType.CONFIRMATION, "Confirmation", header, content);
    }
    public static void showInformationAlert(String header, String content) {
        showAlert(Alert.AlertType.INFORMATION, "Sucesso!", header, content);
    }

    private static void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void letterFilter(TextField field){
        field.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[a-zA-Z\\s]*")) {
                return change;
            }
            return null;
        }));
    }
    public static void numberFilter(TextField field){
        field.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?\\d*")) {
                return change;
            }
            return null;
        }));

    }
    public static void phoneFilter(TextField field){
        field.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("^-?\\d*\\-?\\d*$")) {
                return change;
            }
            return null;
        }));

    }

    public static void priceFilter(TextField field){
        field.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("^\\d*\\.?\\d*$")) {
                return change;
            }
            return null;
        }));

    }

    public static String dataFormat(Calendar calendar){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateFormat.format(calendar.getTime());

    }
}
