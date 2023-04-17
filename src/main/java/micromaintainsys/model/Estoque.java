package micromaintainsys.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
/**
 Classe que representa o estoque de peças.
 */
public class Estoque {

    /**
     Tabela de hash que armazena as peças do estoque e suas quantidades.
     */
    private Hashtable<String, Integer> pecas;
    /**
     Lista de ordens de compra realizadas para o estoque.
     */
    private ArrayList<OrdemCompra> ordensCompra;
    /**
     Cria um estoque vazio.
     */
    public Estoque() {
        pecas = new Hashtable<String,Integer>();
        ordensCompra = new ArrayList<>();
    }
    /**
     Cria um estoque com as peças e quantidades fornecidas.
     @param pecas tabela de hash com as peças e quantidades a serem adicionadas ao estoque
     @param ordensCompra lista de ordens de compra já realizadas para o estoque
     */
    public Estoque(Hashtable<String, Integer> pecas, ArrayList<OrdemCompra> ordensCompra) {
        this.pecas = pecas;
        this.ordensCompra = ordensCompra;
    }
    /**
     Adiciona uma quantidade especificada de uma peça ao estoque.
     Se a peça já existir no estoque, a quantidade é somada à quantidade já existente.
     @param peca nome da peça a ser adicionada ao estoque
     @param quantidade quantidade da peça a ser adicionada ao estoque
     */
    public void adicionaPeca(String peca, int quantidade) {
        peca = peca.toLowerCase();
        if (!pecas.containsKey(peca)){
            pecas.put(peca, quantidade);
        }
        else{
            pecas.replace(peca, pecas.get(peca) + quantidade);
        }
    }
    /**

     Remove uma quantidade especificada de uma peça do estoque.
     Se a quantidade a ser removida for maior que a quantidade em estoque, a quantidade removida
     é ajustada para ser igual à quantidade em estoque.
     @param peca nome da peça a ser removida do estoque
     @param quantidade quantidade da peça a ser removida do estoque
     */
    public void removePeca(String peca, int quantidade) {
        peca = peca.toLowerCase();
//Não pode tirar mais que o que tem estoque/
        if (quantidade > pecas.get(peca))
            quantidade = pecas.get(peca);
        pecas.replace(peca, pecas.get(peca) - quantidade);
    }
    /**

     Remove uma peça do estoque.
     @param peca nome da peça a ser removida do estoque
     */
    public void deletaPeca(String peca){
        peca = peca.toLowerCase();
        if(pecas.containsKey(peca)) {
            pecas.remove(peca);
        }
    }
    /**

     Retorna a quantidade em estoque da peça especificada.
     @param peca a peça cujo estoque será consultado
     @return a quantidade em estoque da peça especificada, ou zero se a peça não existir no estoque
     */
    public int pegaEstoqueDePeca(String peca) {
        peca = peca.toLowerCase();
        if(!pecas.containsKey(peca)){
            return 0;
        }
        return this.pecas.get(peca);
    }
    /**
     Adiciona uma nova ordem de compra ao estoque.
     @param ordemCompra a ordem de compra a ser adicionada ao estoque
     */
    public void criaOrdemCompra(OrdemCompra ordemCompra) {
        ordensCompra.add(ordemCompra);
    }
    /**

     Remove uma ordem de compra do estoque.
     @param ordemCompra a ordem de compra a ser removida do estoque
     */
    public void removeOrdemCompra(OrdemCompra ordemCompra) {
        ordensCompra.remove(ordemCompra);
    }
    /**

     Retorna uma lista de todas as ordens de compra presentes no estoque.
     @return uma lista de todas as ordens de compra presentes no estoque
     */
    public ArrayList<OrdemCompra> verificaOrdensCompra() {
        return ordensCompra;
    }
    /**

     Gera um relatório de compras no período especificado.
     @param inicio a data de início do período
     @param fim a data de término do período
     @return um objeto RelatorioCompras contendo as informações do relatório gerado
     */

    public RelatorioCompras geraRelatorioCompras(Calendar inicio, Calendar fim){
        return new RelatorioCompras(this.ordensCompra, inicio, fim);
    }
    /**

     Retorna um Hashtable contendo todas as peças presentes no estoque e suas respectivas quantidades.
     @return um Hashtable contendo todas as peças presentes no estoque e suas respectivas quantidades
     */
    public Hashtable<String, Integer> getPecas(){
        return this.pecas;
    }
}
