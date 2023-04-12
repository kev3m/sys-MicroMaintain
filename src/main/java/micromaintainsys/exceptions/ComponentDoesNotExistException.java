package micromaintainsys.exceptions;

public class ComponentDoesNotExistException extends SystemException{
    public ComponentDoesNotExistException(){
        this.mensagem = "O componente não existe";
    }
}
