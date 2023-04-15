package micromaintainsys.exceptions;

public class ComponentDoesNotExistException extends SystemException{
    private String peca;
    public ComponentDoesNotExistException(String peca){
        this.peca = peca;
        this.mensagem = "O componente não existe";
    }
}
