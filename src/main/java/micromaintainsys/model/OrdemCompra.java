package micromaintainsys.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 Classe que representa uma ordem de compra.
 */
public class OrdemCompra implements Serializable {
    /**
     Nome da peça.
     */
    private String peca;
    /**
     Quantidade da peça a ser comprada.
     */
    private int quantidade;
    /**
     Valor unitário da peça.
     */
    private double valorUnitario;
    /**
     Data de criação da ordem de compra.
     */
    private Calendar dataCriacao = Calendar.getInstance();
    /**
     Construtor da classe OrdemCompra.
     @param peca Nome da peça.
     @param quantidade Quantidade da peça a ser comprada.
     @param valorUnitario Valor unitário da peça.
     */
    public OrdemCompra(String peca, int quantidade, double valorUnitario){
        this.peca = peca;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }
    /**
     Obtém o valor unitário da peça.
     @return Valor unitário da peça.
     */
    public double getValorUnitario() {
        return valorUnitario;
    }
    /**
     Obtém o valor total da ordem de compra.
     @return Valor total da ordem de compra.
     */
    public double getValorCompra(){ return (valorUnitario * quantidade); }
    /**
     Define o valor unitário da peça.
     @param valorUnitario Valor unitário da peça.
     */
    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    /**
     Obtém a peça.
     @return A peça.
     */
    public String getPeca() {
        return peca;
    }
    /**
     Define a peça.
     @param peca A peça.
     */
    public void setPeca(String peca) {
        this.peca = peca;
    }
    /**
     Obtém a quantidade da peça.
     @return A quantidade da peça.
     */
    public int getQuantidade() {
        return quantidade;
    }
    /**
     Define a quantidade da peça.
     @param quantidade A quantidade da peça.
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    /**
     Retorna a data de criação da ordem de compra.
     @return A data de criação da ordem de compra.
     */
    public Calendar getDataCriacao() {
        return dataCriacao;
    }
    /**
     Define a data de criação da ordem de compra.
     @param dataCriacao A data de criação da ordem de compra.
     */
    public void setDataCriacao(Calendar dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataCriacaoFormatada(){
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yy");
        String data = s.format(this.dataCriacao.getTime());
        return data;
    }
}
