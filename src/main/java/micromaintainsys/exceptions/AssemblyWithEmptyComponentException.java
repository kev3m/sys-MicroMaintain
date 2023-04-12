package micromaintainsys.exceptions;

public class AssemblyWithEmptyComponentException extends SystemException {
    public AssemblyWithEmptyComponentException(){
        this.mensagem = "Serviço de montagem sem especificação de peça";
    }
}
