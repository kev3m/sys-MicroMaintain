package micromaintainsys.utils;
import javafx.scene.control.Alert;

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


}
