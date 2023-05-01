package micromaintainsys.dao.estoque;

import micromaintainsys.model.Estoque;
import micromaintainsys.model.OrdemCompra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

import static micromaintainsys.utils.FileUtils.*;

/**

 * Classe que implementa a interface InterfaceEstoque para gerenciar um estoque de peças.
 * Armazena as informações de peças e ordens de compra.


 */

public class EstoqueDAO implements InterfaceEstoque, Serializable {

    /**
     Tabela hash que armazena as informações de peças, associando cada peça a sua quantidade.
     */
    private static Hashtable<String, Integer> pecas;
    /**
     Lista que armazena as informações das ordens de compra.     */
    private static ArrayList<OrdemCompra> ordensCompra;
    private static final String PURCHASE_ORDERS_FILE_PATH = initFilePath("estoque_ordens_compra.bin");
    private static final String COMPONENTS_FILE_PATH = initFilePath("estoque_pecas.bin");

    private static String initFilePath(String filename) {
        return getFilePath(filename);
    }

    public EstoqueDAO(){
        Object objOrdens = carregaDados(PURCHASE_ORDERS_FILE_PATH);
        Object objPecas = carregaDados(COMPONENTS_FILE_PATH);

        ordensCompra = objOrdens == null? new ArrayList<>() : (ArrayList<OrdemCompra>) objOrdens;
        pecas = objPecas == null? new Hashtable<>(): (Hashtable<String, Integer>) objPecas;
    }

    /**
     Método que atualiza as informações do estoque com base em um objeto Estoque.
     @param estoque Objeto Estoque contendo as informações a serem atualizadas no estoque.
     */
    public void atualiza(Estoque estoque){
        salvaDados(ordensCompra, PURCHASE_ORDERS_FILE_PATH);
        salvaDados(pecas,  COMPONENTS_FILE_PATH);
    }
    /**
     Cria um novo estoque ou carrega um estoque com base nas informações armazenadas.
     @return objeto do tipo Estoque.
     */
    public Estoque carrega(){
        Estoque estoque = new Estoque(pecas, ordensCompra);
        return estoque;
    }
}
