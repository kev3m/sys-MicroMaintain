package micromaintainsys.exceptions;

public class UserNotLoggedInException extends SystemException{
    public UserNotLoggedInException(){
        this.mensagem = "Nenhum usuário logado.";
    }
}
