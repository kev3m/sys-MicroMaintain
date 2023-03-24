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

    public Ordem(String avaliacaoFinal, StatusOrdem status, ArrayList<Servico> servicos){
        this.avaliacaoFinal = avaliacaoFinal;
        this.status = status;
        this.servicos = servicos;

    }

    public void setClienteID(int id) {this.clienteID = id;}
    public void setTecnicoID(int id) {this.tecnicoID = id;}
    public void setOrdemID(int id) {this.ordemID = id;}
    public void setAvaliacaoFinal(String avaliacao) {this.avaliacaoFinal = avaliacao;}
    public  void setStatus(StatusOrdem status) {this.status = status;}
    public void setServicos(Servico servico) {this.servicos.add(servico);}

}
