package micromaintainsys.model;

import java.io.Serializable;

/**
 Classe que representa um pagamento.
 */
public class Pagamento implements Serializable{
    /**
     Tipo de pagamento.
     */
    private TipoPagamento tipoPagamento;
    /**
     Valor do pagamento.
     */
    private double valor;
    /**
     ID da fatura associada ao pagamento.
     */
    private int faturaID;
    /**
     ID do pagamento.
     */
    private int pagamentoID;
    /**
     Construtor da classe Pagamento.
     @param tipoPagamento Tipo de pagamento.
     @param valor Valor do pagamento.
     @param faturaID ID da fatura associada ao pagamento.
     */
    public Pagamento(TipoPagamento tipoPagamento, double valor, int faturaID){
        this.tipoPagamento = tipoPagamento;
        this.valor = valor;
        this.faturaID = faturaID;
        /*Implementar a geração de ID único*/
    }
    /**
     Obtém o ID do pagamento.
     @return O ID do pagamento.
     */
    public int getPagamentoID() {
        return pagamentoID;
    }
    /**
     Define o ID do pagamento.
     @param pagamentoID O ID do pagamento.
     */
    public void setPagamentoID(int pagamentoID) {
        this.pagamentoID = pagamentoID;
    }
    /**
     Obtém o tipo de pagamento.
     @return O tipo de pagamento.
     */
    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }
    /**
     Define o tipo de pagamento.
     @param tipoPagamento O tipo de pagamento.
     */
    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
    /**
     Obtém o valor do pagamento.
     @return O valor do pagamento.
     */
    public double getValor() {
        return valor;
    }
/**
     Define o valor do pagamento.
     @param valor O valor do pagamento.
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
    /**
     Obtém o ID da fatura associada ao pagamento.
     @return O ID da fatura associada ao pagamento.
     */
    public int getFaturaID() {
        return faturaID;
    }
    /**
     Define o ID da fatura associada ao pagamento.
     @param faturaID O ID da fatura associada ao pagamento.
     */
    public void setFaturaID(int faturaID) {
        this.faturaID = faturaID;
    }
}
