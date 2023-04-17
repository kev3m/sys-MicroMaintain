package micromaintainsys.model;

/**
 * Classe que representa uma peça.
 */
public class Peca {
    /**
     * Nome da peça.
     */
    private String nome;
    /**
     * Valor da peça.
     */
    private double valor;
    /**
     * Cria uma instância de Peca.
     * @param nome O nome da peça.
     * @param valor O valor da peça.
     */
    public Peca(String nome, double valor){
        this.nome = nome;
        this.valor = valor;
    }
    /**
     * Altera o nome da peça.
     * @param nome O novo nome da peça.
     */
    public void setNome(String nome) {this.nome = nome;}
    /**
     * Altera o valor da peça.
     * @param valor O novo valor da peça.
     */
    public void setValor(double valor) {this.valor = valor;}

    /**
     * Retorna o nome da peça.
     * @return O nome da peça.
     */
    public String getNome() {return nome;}
    /**
     * Retorna o valor da peça.
     * @return O valor da peça.
     */
    public double getValor() {return valor;}


}
