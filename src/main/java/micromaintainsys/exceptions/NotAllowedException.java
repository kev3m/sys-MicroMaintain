package micromaintainsys.exceptions;

public class NotAllowedException extends SystemException{
    int id;
    public NotAllowedException(int id){
        this.mensagem = "Permissão negada.";
        this.id = id;
    }
}
