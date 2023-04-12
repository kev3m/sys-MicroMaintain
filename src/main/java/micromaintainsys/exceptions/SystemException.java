package micromaintainsys.exceptions;

public class SystemException extends RuntimeException{
    String mensagem;
    public String getMensagem(){
        return this.mensagem;
    }
}
