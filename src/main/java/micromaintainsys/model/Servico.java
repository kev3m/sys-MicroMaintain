package micromaintainsys.model;

import java.util.Calendar;

public class Servico {
  private double valor;
  private Calendar horarioAbertura;
  private Calendar horarioFinalizacao;
  private double avaliacaoCliente;
  private int ordemID;
  private String peca;
  private String descricao;
  private CategoriaServico categoriaServico;
  private int servicoID;

    public int getServicoID() {
        return servicoID;
    }

    public void setServicoID(int servicoID) {
        this.servicoID = servicoID;
    }

    public Servico(CategoriaServico categoriaServico, double valor, String peca, String descricao, int ordemID){
      this.categoriaServico = categoriaServico;
      this.valor = valor;
      this.peca = peca;
      this.descricao = descricao;
      this.ordemID = ordemID;
      this.horarioAbertura = Calendar.getInstance();
  }

    public void setValor(double valor) {
        this.valor = valor;
    }
    public void setHorarioAbertura(Calendar horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }
    public void setHorarioFinalizacao(Calendar horarioFinalizacao) {
        this.horarioFinalizacao = horarioFinalizacao;
    }
    public void setAvaliacaoCliente(double avaliacaoCliente) {
        this.avaliacaoCliente = avaliacaoCliente;
    }
    public void setOrdemID(int ordemID) {
        this.ordemID = ordemID;
    }
    public void setPeca(String peca) {
        this.peca = peca;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setCategoriaServico(CategoriaServico categoriaServico) {
        this.categoriaServico = categoriaServico;
    }

    public double getValor() {
        return valor;
    }
    public Calendar getHorarioAbertura() {
        return horarioAbertura;
    }
    public Calendar getHorarioFinalizacao() {
        return horarioFinalizacao;
    }
    public double getAvaliacaoCliente() {
        return avaliacaoCliente;
    }
    public int getOrdemID() {
        return ordemID;
    }

    public String getPeca() {
        return peca;
    }
    public String getDescricao() {
        return descricao;
    }
    public CategoriaServico getCategoriaServico() {
        return categoriaServico;
    }

}
