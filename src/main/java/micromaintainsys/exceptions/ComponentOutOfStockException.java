package micromaintainsys.exceptions;

public class ComponentOutOfStockException extends SystemException{
    public ComponentOutOfStockException(){
        this.mensagem = "Sem estoque.";
    }
}
