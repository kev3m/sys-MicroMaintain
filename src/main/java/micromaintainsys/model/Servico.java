package micromaintainsys.model;

import java.util.ArrayList;
import java.util.Calendar;

public class Servico {
  private enum Categoria {Formatacao, Montagem, Limpeza}
  private double valor;
  private Calendar horarioAbertura;
  private Calendar horarioFinalizacao;
  private double AvaliacaoCliente;
  private int ordemID;
  private ArrayList<Peca> peca;
  private String descricao;
  private Categoria categoriaServico;

  public Servico(Categoria categoriaServico,double valor, ArrayList<Peca> peca, String descricao){
      this.categoriaServico = categoriaServico;
      this.valor = valor;
      this.peca = peca;
      this.descricao = descricao;
  }
}
