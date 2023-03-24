package micromaintainsys.model;

public class Peca {
    private String nome;
    private double valor;

    public Peca(String nome, double valor){
        this.nome = nome;
        this.valor = valor;
    }

    public void setNome(String nome) {this.nome = nome;}
    public void setValor(double valor) {this.valor = valor;}
    public String getNome() {return nome;}
    public double getValor() {return valor;}


}
