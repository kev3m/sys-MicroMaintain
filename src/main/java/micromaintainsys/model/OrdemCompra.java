package micromaintainsys.model;

import java.util.Calendar;

public class OrdemCompra {
    private String peca;
    private int quantidade;
    private double valorUnitario;
    private Calendar dataCriacao = Calendar.getInstance();

    public OrdemCompra(String peca, int quantidade, double valorUnitario){
        this.peca = peca;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }
    public double getValorCompra(){ return (valorUnitario * quantidade); }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getPeca() {
        return peca;
    }

    public void setPeca(String peca) {
        this.peca = peca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
