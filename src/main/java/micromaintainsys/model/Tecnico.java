package micromaintainsys.model;

import java.util.ArrayList;

public class Tecnico {
    private boolean adm;
    private String nome;
    private String senha;
    private int tecnicoID;
    private ArrayList<Ordem> historicoOrdens;
    private ArrayList<OrdemCompra> historicoCompras;

    /**
     * Construtor da classe.
     * @param nome nome do técnico
     * @param senha senha do técnico
     */
    public Tecnico(String nome, String senha){
        this.nome = nome;
        this.senha = senha;
        this.historicoOrdens = null;
        this.historicoCompras = null;
    }

    /**
     * Modifica o tipo de conta do técnico.
     * @param adm indica se o usuário é administrador (true) ou um técnico normal (false)
     */
    public void setAdm(boolean adm) {
        this.adm = adm;
    }

    /**
     * Modifica o nome do usuário.
     * @param nome novo nome do usuário
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Modifica a senha do usuário.
     * @param senha nova senha do usuário
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Modifica o ID do técnico.
     * @param tecnicoID novo id do técnico.
     */
    public void setTecnicoID(int tecnicoID) {
        this.tecnicoID = tecnicoID;
    }

    /**
     * Modifica o histórico de ordens do técnico.
     * @param historicoOrdens histórico de ordens
     */
    public void setHistoricoOrdens(ArrayList<Ordem> historicoOrdens) {
        this.historicoOrdens = historicoOrdens;
    }

    /**
     * Modifica o histórico de compras do técnico.
     * @param historicoCompras histórico de compras
     */
    public void setHistoricoCompras(ArrayList<OrdemCompra> historicoCompras) {
        this.historicoCompras = historicoCompras;
    }

    /**
     * Indica se o técnico é administrador ou não.
     * @return (true) indica que o usuário é administrador e (false) indica que é um usuário normal.
     */
    public boolean isAdm() {
        return adm;
    }

    /**
     * Retorna o nome do técnico.
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a senha do técnico.
     * @return
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Retorna o ID do técnico.
     * @return
     */
    public int getTecnicoID() {
        return tecnicoID;
    }

    /**
     * Retorna o histórico de ordens do técnico.
     * @return
     */
    public ArrayList<Ordem> getHistoricoOrdens() {
        return historicoOrdens;
    }

    /**
     * Retorna o histórico de compras do técnico.
     * @return
     */
    public ArrayList<OrdemCompra> getHistoricoCompras() {
        return historicoCompras;
    }

    public boolean temOrdemEmAberto(){
        boolean emAberto = this.historicoOrdens
                .get(this.historicoOrdens.size()-1)
                .getStatus() == StatusOrdem.Aberta?
                true
                : false;
        return emAberto;
    }

    public Ordem pegaUltimaOrdem(){
        return this.historicoOrdens.get(this.historicoOrdens.size() -1);
    }

    public void cadastraOrdem(Ordem novaOrdem){
        this.getHistoricoOrdens().add(novaOrdem);
    }

}
