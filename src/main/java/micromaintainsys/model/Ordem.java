package micromaintainsys.model;

import java.util.ArrayList;

public class Ordem {
    public enum StatusOrdem {Pagamento, Aberta, Finalizada, Cancelada}
    private int clienteID;
    private int tecnicoID;
    private int ordemID;
    private String avaliacaoFinal;
    private StatusOrdem status;
    private ArrayList<Servico> servicos;
    private int faturaID = -1;

    public Ordem(int clienteID){
        this.clienteID = clienteID;
        this.tecnicoID = tecnicoID;
        this.status = StatusOrdem.Aberta;

    }

    public double getOrdemValor(){
        ArrayList<Servico> servicos = this.servicos;
        return servicos.stream().mapToDouble(Servico::getValor).sum();
    }

    public void setClienteID(int id) {this.clienteID = id;}
    public void setTecnicoID(int id) {this.tecnicoID = id;}
    public void setOrdemID(int id) {this.ordemID = id;}
    public void setAvaliacaoFinal(String avaliacao) {this.avaliacaoFinal = avaliacao;}
    public  void setStatus(StatusOrdem status) {this.status = status;}
    public void setServicos(Servico servico) {this.servicos.add(servico);}

    public int getClienteID() {return clienteID;}
    public int getTecnicoID() {return tecnicoID;}
    public int getOrdemID() {return ordemID;}
    public String getAvaliacaoFinal() {return avaliacaoFinal;}
    public StatusOrdem getStatus() {return status;}
    public ArrayList<Servico> getServicos() {return servicos;}

    public int getFaturaID() {
        return faturaID;
    }
    public void setFaturaID(int faturaID) {
        this.faturaID = faturaID;
    }
}
