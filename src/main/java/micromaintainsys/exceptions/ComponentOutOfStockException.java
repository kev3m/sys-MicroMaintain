package micromaintainsys.exceptions;

public class ComponentOutOfStockException extends SystemException{
    private String peca;
    public ComponentOutOfStockException(String peca){
        this.peca = peca;
        this.mensagem = "Sem estoque.";
    }
}
