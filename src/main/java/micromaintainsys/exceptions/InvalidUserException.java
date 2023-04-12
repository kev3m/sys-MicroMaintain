package micromaintainsys.exceptions;

public class InvalidUserException extends SystemException{
    int id;
    public InvalidUserException(int id){
        this.id = id;
        this.mensagem = "Usuário inválido.";
    }
    public int getId(){
        return this.id;
    }
}
