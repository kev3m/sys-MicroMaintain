package micromaintainsys.model;

import java.util.ArrayList;

public class Tecnico {
    private boolean adm;
    private String nome;
    private String senha;
    private int tecnicoID;
    private ArrayList<Ordem> historicoOrdens;
    private ArrayList<OrdemCompra> historicoCompras;
    private Estoque estoque;

    public Tecnico(String nome, String senha, ArrayList<Ordem> historicoOrdens, ArrayList<OrdemCompra> historicoCompras, Estoque estoque){
        this.nome = nome;
        this.senha = senha;
        this.historicoOrdens = historicoOrdens;
        this.historicoCompras = historicoCompras;
        this.estoque = estoque;
    }

    public void setAdm(boolean adm) {
        this.adm = adm;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTecnicoID(int tecnicoID) {
        this.tecnicoID = tecnicoID;
    }

    public void setHistoricoOrdens(ArrayList<Ordem> historicoOrdens) {
        this.historicoOrdens = historicoOrdens;
    }

    public void setHistoricoCompras(ArrayList<OrdemCompra> historicoCompras) {
        this.historicoCompras = historicoCompras;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public boolean isAdm() {
        return adm;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public int getTecnicoID() {
        return tecnicoID;
    }

    public ArrayList<Ordem> getHistoricoOrdens() {
        return historicoOrdens;
    }

    public ArrayList<OrdemCompra> getHistoricoCompras() {
        return historicoCompras;
    }

    public Estoque getEstoque() {
        return estoque;
    }
}
