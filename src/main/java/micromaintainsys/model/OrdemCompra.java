package micromaintainsys.model;

public class OrdemCompra {
    private String peca;
    private int quantidade;
    private double valorUnitario;

    public OrdemCompra(String peca, int quantidade, double valorUnitario){
        this.peca = peca;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;

    }
}
