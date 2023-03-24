package micromaintainsys.model;

import java.util.ArrayList;
import java.util.Calendar;

public class Servico {
  private enum Categoria {Formatacao, Montagem, Limpeza}
  private double valor;
  private Calendar horarioAbertura;
  private Calendar horarioFinalizacao;
  private double avaliacaoCliente;
  private int ordemID;
  private Peca peca;
  private String descricao;
  private Categoria categoriaServico;

  public Servico(Categoria categoriaServico,double valor, Peca peca, String descricao){
      this.categoriaServico = categoriaServico;
      this.valor = valor;
      this.peca = peca;
      this.descricao = descricao;
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
    public void setPecas(Peca peca) {
        this.peca = peca;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setCategoriaServico(Categoria categoriaServico) {
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

    public Peca getPeca() {
        return peca;
    }
    public String getDescricao() {
        return descricao;
    }
    public Categoria getCategoriaServico() {
        return categoriaServico;
    }

}
