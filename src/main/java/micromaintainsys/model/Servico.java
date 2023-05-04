package micromaintainsys.model;

import java.io.Serializable;
import java.util.Calendar;
/**
 Classe que representa um serviço.
 */

public class Servico implements Serializable {
    /**
     Valor do serviço.
     */
  private double valor;
    /**
     Horário de abertura do serviço.
     */
  private Calendar horarioAbertura;
    /**
     Horário de finalização do serviço.
     */
  private Calendar horarioFinalizacao;
    /**
     Avaliação do serviço pelo cliente.
     */
  private double avaliacaoCliente;
    /**
     ID da ordem de serviço associada ao serviço.
     */
  private int ordemID;
    /**
     Indica se o serviço foi avaliado.
     */
  private boolean avaliado = false;
    /**
     Peça do serviço.
     */
  private String peca;
    /**
     Descrição do serviço.
     */
  private String descricao;
    /**
     Categoria do serviço.
     */
  private CategoriaServico categoriaServico;
    /**
     ID do serviço.
     */
  private int servicoID;
    /**
     Construtor da classe Servico.
     @param categoriaServico Categoria do serviço.
     @param valor Valor do serviço.
     @param peca Peça do serviço.
     @param descricao Descrição do serviço.
     @param ordemID ID da ordem de serviço associada ao serviço.
     */
    public Servico(CategoriaServico categoriaServico, double valor, String peca, String descricao, int ordemID){
        this.categoriaServico = categoriaServico;
        this.valor = valor;
        this.peca = peca;
        this.descricao = descricao;
        this.ordemID = ordemID;
        this.horarioAbertura = Calendar.getInstance();
        this.horarioFinalizacao = null;
    }
    /**
     Obtém o id do serviço.
     @return id do serviço.
     */
    public int getServicoID() {
        return servicoID;
    }
    /**
     Define o id do serviço.
     @param servicoID id do serviço.
     */
    public void setServicoID(int servicoID) {
        this.servicoID = servicoID;
    }
/**
     Define o valor do serviço.
     @param valor Valor do serviço.
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Avalia o serviço.
     * @param avaliacaoCliente
     */
    public void avaliaServico(double avaliacaoCliente) {
        this.avaliado = true;
        this.avaliacaoCliente = avaliacaoCliente;
    }

    /**
     * Define a peça do serviço.
     * @param peca
     */
    public void setPeca(String peca) {
        this.peca = peca;
    }
    /**
     * Define a descrição do serviço.
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    /**
     * Define a categoria do serviço.
     * @param categoriaServico
     */
    public void setCategoriaServico(CategoriaServico categoriaServico) {
        this.categoriaServico = categoriaServico;
    }

    /**
     * Obtém o valor do serviço.
     * @return Valor do serviço.
     */
    public double getValor() {
        return valor;
    }
    /**
     * Obtém o horário de abertura do serviço.
     * @return Horário de abertura do serviço.
     */
    public Calendar getHorarioAbertura() {
        return horarioAbertura;
    }
    /**
     * Obtém o horário de finalização do serviço.
     * @return Horário de finalização do serviço.
     */
    public Calendar getHorarioFinalizacao() {
        return horarioFinalizacao;
    }
    /**
     * Obtém a avaliação do serviço pelo cliente.
     * @return Avaliação do serviço pelo cliente.
     */
    public double getAvaliacaoCliente() {
        return avaliacaoCliente;
    }
    /**
     * Obtém o id da ordem de serviço associada ao serviço.
     * @return Id da ordem de serviço associada ao serviço.
     */
    public int getOrdemID() {
        return ordemID;
    }
    /**
     * Obtém a peça do serviço.
     * @return Peça do serviço.
     */
    public String getPeca() {
        return peca;
    }
    /**
     * Obtém a descrição do serviço.
     * @return Descrição do serviço.
     */
    public String getDescricao() {
        return descricao;
    }
    /**
     * Obtém a categoria do serviço.
     * @return Categoria do serviço.
     */
    public CategoriaServico getCategoriaServico() {
        return categoriaServico;
    }

    /**
     * Verifica se o serviço foi avaliado.
     * @return true se o serviço foi avaliado, false caso contrário.
     */
    public boolean foiAvaliado(){
        return this.avaliado;
    }
    /**
     * Encerra o serviço.
     */
    public void encerraServico(){
        this.horarioFinalizacao = Calendar.getInstance();
    }
    /**
     * Verifica se o serviço foi encerrado.
     * @return true se o serviço foi encerrado, false caso contrário.
     */
    public boolean foiEncerrado(){
        return this.horarioFinalizacao == null? false : true;
    }
}
