package micromaintainsys.model;

import java.util.ArrayList;

public class Fatura {
    private double valorTotal;
    private ArrayList<Pagamento> pagamentos;
    private int ordemID;
    private double valorPago;

    public void addPagamento(String tipo, double valor){
        pagamentos.add(new Pagamento(tipo, valor));
    }

    public ArrayList<Pagamento> getPagamentos(){
        return pagamentos;
    }
    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getOrdemID() {
        return ordemID;
    }

    public void setOrdemID(int ordemID) {
        this.ordemID = ordemID;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }
}
