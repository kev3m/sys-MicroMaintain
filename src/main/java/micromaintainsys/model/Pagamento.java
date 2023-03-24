package micromaintainsys.model;

public class Pagamento {
    private String tipoPagamento;
    private double valor;
    private int faturaID;

    public Pagamento(String tipoPagamento, double valor){
        this.tipoPagamento = tipoPagamento;
        this.valor = valor;
        /*Implementar a geração de ID único*/
    }
    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
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
