package micromaintainsys.model;

import java.util.ArrayList;

/**

 Classe que representa uma fatura de uma ordem de compra.
 */
public class Fatura {
    /**
    Valor total da fatura.
     */
    private double valorTotal;
    /**
     ID da ordem de compra associada à fatura.
     */
    private int ordemID;
    /**
     Valor pago na fatura.
     */
    private double valorPago;
    /**
     ID da fatura.
     */
    private int faturaID;

    /**

     Construtor da classe Fatura.
     @param ordemID ID da ordem de compra associada à fatura.
     @param valorTotal Valor total da fatura.
     */
    public Fatura(int ordemID, double valorTotal) {
        this.ordemID = ordemID;
        this.valorTotal = valorTotal;
    }
    /**

     Retorna o ID da fatura.
     @return O ID da fatura.
     */
    public int getFaturaID() {
        return faturaID;
    }
    /**

     Define o ID da fatura.
     @param faturaID O ID da fatura.
     */
    public void setFaturaID(int faturaID) {
        this.faturaID = faturaID;
    }
    /**

     Retorna o valor total da fatura.
     @return O valor total da fatura.
     */
    public double getValorTotal() {
        return valorTotal;
    }
    /**

     Define o valor total da fatura.
     @param valorTotal O valor total da fatura.
     */
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    /**

     Retorna o ID da ordem de compra associada à fatura.
     @return O ID da ordem de compra associada à fatura.
     */
    public int getOrdemID() {
        return ordemID;
    }
    /**

     Define o ID da ordem de compra associada à fatura.
     @param ordemID O ID da ordem de compra associada à fatura.
     */
    public void setOrdemID(int ordemID) {
        this.ordemID = ordemID;
    }
    /**

     Retorna o valor pago na fatura.
     @return O valor pago na fatura.
     */
    public double getValorPago() {
        return valorPago;
    }
    /**

     Define o valor pago na fatura.
     @param valorPago O valor pago na fatura.
     */
    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }
}