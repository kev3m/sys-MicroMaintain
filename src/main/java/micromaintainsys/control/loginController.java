package micromaintainsys.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import micromaintainsys.dao.DAO;
import micromaintainsys.exceptions.InvalidUserException;
import micromaintainsys.exceptions.UserAlreadyLoggedInException;
import micromaintainsys.exceptions.WrongPasswordException;
import micromaintainsys.dao.DAO;
import micromaintainsys.exceptions.*;
import micromaintainsys.model.*;
import micromaintainsys.model.Tecnico;

import java.io.IOException;

public class loginController {
    //Armazena o ID do tecnico logado no sistema
    private Tecnico tecnicoSessao;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private AnchorPane loginAnchorPane;
    @FXML
    public void loginButtonClick(ActionEvent event) throws
            InvalidUserException,
            UserAlreadyLoggedInException,
            WrongPasswordException, IOException {
        int tecId = Integer.parseInt(usernameField.getText());
        String password = passwordField.getText();
        loginTecnico(tecId, password);
        if (tecnicoSessao != null){
            new SceneSwitch(loginAnchorPane, "main.fxml");
        }
    }
    public void loginTecnico(int id, String senha) throws
            InvalidUserException,
            UserAlreadyLoggedInException,
            WrongPasswordException, IOException {

        /*Não é possível fazer login sem fazer logoff do técnico anterior!*/
        Tecnico loginTecnico = pegaTecnicoPorId(id);
        if (loginTecnico == null){
            throw new InvalidUserException(id);
        }
        else if(this.tecnicoSessao != null){
            throw new UserAlreadyLoggedInException();
        }
        boolean success = DAO.getTecnicoDAO().autentica(id, senha);
        if (success)
            this.tecnicoSessao = loginTecnico;
        else
            throw new WrongPasswordException();
    }
    public Tecnico pegaTecnicoPorId(int tecnicoID){
        return DAO.getTecnicoDAO().pegaPorId(tecnicoID);
    }
}
