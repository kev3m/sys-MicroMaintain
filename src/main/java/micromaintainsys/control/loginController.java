package micromaintainsys.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import micromaintainsys.dao.DAO;
import micromaintainsys.exceptions.InvalidUserException;
import micromaintainsys.exceptions.UserAlreadyLoggedInException;
import micromaintainsys.exceptions.WrongPasswordException;
import micromaintainsys.dao.DAO;
import micromaintainsys.exceptions.*;
import micromaintainsys.model.*;
import micromaintainsys.model.Tecnico;

import static micromaintainsys.utils.ViewUtils.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class loginController {
    //Armazena o ID do tecnico logado no sistema
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private AnchorPane loginAnchorPane;
    private Tecnico tecnicoSessao;
    private int objID;
    @FXML
    public void loginButtonClick(ActionEvent event) throws
            InvalidUserException,
            UserAlreadyLoggedInException,
            WrongPasswordException, IOException {
        int tecId = Integer.parseInt(usernameField.getText());
        String password = passwordField.getText();
        loginTecnico(tecId, password);
        if (tecnicoSessao != null){
            new SceneSwitch(loginAnchorPane, "main.fxml", tecnicoSessao, objID);
        }

    }
    public void loginTecnico(int id, String senha){

        /*Não é possível fazer login sem fazer logoff do técnico anterior!*/
        Tecnico loginTecnico = pegaTecnicoPorId(id);
        if (loginTecnico == null){
            showErrorAlert("Técnico não encontrado!", "O técnico com o ID " + id + " não foi encontrado no sistema!");
        }
        else if (DAO.getTecnicoDAO().autentica(id, senha))
            this.tecnicoSessao = loginTecnico;
        else
            showErrorAlert("Senha incorreta!", "A senha digitada está incorreta!");
    }

    public Tecnico pegaTecnicoPorId(int tecnicoID){
        return DAO.getTecnicoDAO().pegaPorId(tecnicoID);
    }
}
