package micromaintainsys.exceptions;

public class WrongPasswordException extends SystemException{
    public WrongPasswordException(){
        this.mensagem = "Senha incorreta.";
    }
}
