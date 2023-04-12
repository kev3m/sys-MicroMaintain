package micromaintainsys.exceptions;

public class UserAlreadyLoggedInException extends SystemException{
    public UserAlreadyLoggedInException(){
        this.mensagem = "Já existe um usuário logado";
    }
}
