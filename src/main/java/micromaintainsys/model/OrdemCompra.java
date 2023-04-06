package micromaintainsys.model;

public class OrdemCompra {
    private TipoDePeca peca;
    private int quantidade;
    private double valorUnitario;

    public OrdemCompra(TipoDePeca peca, int quantidade, double valorUnitario){
        this.peca = peca;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;

    }
}
