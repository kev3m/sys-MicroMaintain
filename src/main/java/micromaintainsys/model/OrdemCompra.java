package micromaintainsys.model;

public class OrdemCompra {
    private Peca peca;
    private int quantidade;
    private double valorUnitario;

    public OrdemCompra(Peca peca, int quantidade, double valorUnitario){
        this.peca = peca;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;

    }
}
