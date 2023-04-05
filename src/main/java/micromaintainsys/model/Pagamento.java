package micromaintainsys.model;

public class Pagamento {
    private TipoPagamento tipoPagamento;
    private double valor;
    private int faturaID;
    private int pagamentoID;

    public int getPagamentoID() {
        return pagamentoID;
    }

    public void setPagamentoID(int pagamentoID) {
        this.pagamentoID = pagamentoID;
    }

    public Pagamento(TipoPagamento tipoPagamento, double valor, int faturaID){
        this.tipoPagamento = tipoPagamento;
        this.valor = valor;
        this.faturaID = faturaID;
        /*Implementar a geração de ID único*/
    }
    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getFaturaID() {
        return faturaID;
    }

    public void setFaturaID(int faturaID) {
        this.faturaID = faturaID;
    }
}
