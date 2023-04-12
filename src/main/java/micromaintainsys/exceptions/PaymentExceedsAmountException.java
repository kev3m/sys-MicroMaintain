package micromaintainsys.exceptions;

public class PaymentExceedsAmountException extends SystemException{
    double excedente;
    public PaymentExceedsAmountException(double excedente){
        this.mensagem = "Pagamento excede o total da fatura";
        this.excedente = excedente;
    }
}
