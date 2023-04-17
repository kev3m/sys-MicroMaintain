package micromaintainsys.model;

import java.util.ArrayList;

/**
 *  Classe que representa uma ordem de serviço.
 */

public class Ordem implements Comparable<Ordem>{
    /**
     * ID do cliente associado à ordem.
     */
    private int clienteID;
    /**
     * ID do técnico responsável pela ordem.
     */
    private int tecnicoID;
    /**
     * ID da ordem.
     */
    private int ordemID;
    /**
     * Avaliação final da ordem.
     */
    private String avaliacaoFinal;
    /**
     * Status da ordem.
     */
    private StatusOrdem status;
    /**
     * Lista de serviços associados à ordem.
     */
    private ArrayList<Servico> servicos;
    /**
     * ID da fatura associada à ordem.
     */
    private int faturaID = -1;
    /**
     * Construtor da classe Ordem.
     * @param clienteID ID do cliente associado à ordem.
     */
    public Ordem(int clienteID){
        this.clienteID = clienteID;
        this.tecnicoID = tecnicoID;
        this.status = StatusOrdem.Aberta;
        this.servicos = new ArrayList<>();

    }
    /**
     * Método de comparação de ordens com base no seu ID.
     * @param outra Ordem a ser comparada.
     * @return Um valor negativo se a ordem atual for menor que a outra, um valor positivo se for maior,
     *         e zero se forem iguais.
     */
    public int compareTo(Ordem outra){
        return this.ordemID - outra.ordemID;
    }
    /**
     * Define o ID do cliente associado à ordem.
     * @param id ID do cliente.
     */
    public void setClienteID(int id) {this.clienteID = id;}
    /**
     * Define o ID do técnico responsável pela ordem.
     * @param id ID do técnico.
     */
    public void setTecnicoID(int id) {this.tecnicoID = id;}
    /**
     * Define o ID da ordem.
     * @param id ID da ordem.
     */

    public void setOrdemID(int id) {this.ordemID = id;}
    /**
     * Define a avaliação final da ordem.
     * @param avaliacao Avaliação final da ordem.
     */
    public void setAvaliacaoFinal(String avaliacao) {this.avaliacaoFinal = avaliacao;}
    /**
     * Define o status da ordem.
     * @param status Status da ordem.
     */
    public  void setStatus(StatusOrdem status) {this.status = status;}
    /**
     * Adiciona um serviço à ordem.
     * @param servico Serviço a ser adicionado.
     */
    public void setServicos(Servico servico) {this.servicos.add(servico);}
    /**
     * Retorna o ID do cliente associado à ordem.
     * @return ID do cliente associado à ordem.
     */
    public int getClienteID() {return clienteID;}
    /**
     * Retorna o ID do técnico responsável pela ordem.
     * @return ID do técnico responsável pela ordem.
     */
    public int getTecnicoID() {return tecnicoID;}
    /**
     * Retorna o ID da ordem.
     * @return ID da ordem.
     */
    public int getOrdemID() {return ordemID;}
    /**
     * Retorna a avaliação final da ordem.
     * @return Avaliação final da ordem.
     */
    public String getAvaliacaoFinal() {return avaliacaoFinal;}
    /**
     * Retorna o status da ordem.
     * @return Status da ordem.
     */
    public StatusOrdem getStatus() {return status;}
    /**
     * Retorna a lista de serviços da ordem.
     * @return Lista de serviços da ordem.
     */
    public ArrayList<Servico> getServicos() {return servicos;}
    /**
     * Retorna o ID da fatura associada à ordem.
     * @return ID da fatura associada à ordem.
     */
    public int getFaturaID() {
        return faturaID;
    }
    /**
     * Define o ID da fatura associada à ordem.
     * @param faturaID ID da fatura associada à ordem.
     */
    public void setFaturaID(int faturaID) {
        this.faturaID = faturaID;
    }
}
