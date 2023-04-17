package micromaintainsys.dao.estoque;

import micromaintainsys.model.Estoque;
import micromaintainsys.model.OrdemCompra;
import micromaintainsys.model.Peca;

import java.util.ArrayList;
import java.util.Hashtable;

/**

 * Classe que implementa a interface InterfaceEstoque para gerenciar um estoque de peças.
 * Armazena as informações de peças e ordens de compra.


 */

public class EstoqueFakeDAO implements InterfaceEstoque {

    /**
     Tabela hash que armazena as informações de peças, associando cada peça a sua quantidade.
     */
    private Hashtable<String, Integer> pecas;
    /**
     Lista que armazena as informações das ordens de compra.
     */
    private ArrayList<OrdemCompra> ordensCompra;

    /**
     Método que atualiza as informações do estoque com base em um objeto Estoque.
     @param estoque Objeto Estoque contendo as informações a serem atualizadas no estoque.
     */
    public void atualiza(Estoque estoque){
        return;
    }
    /**
     Cria um novo objeto Estoque vazio, sem nenhuma peça ou ordem de compra cadastrada.
     @return Objeto Estoque recém-criado.
     */
    public Estoque cria(){
        Hashtable<String, Integer> pecas = new Hashtable<>();
        ArrayList<OrdemCompra> ordensCompra = new ArrayList<>();
        Estoque estoque = new Estoque(pecas, ordensCompra);
        return estoque;
    }
}
