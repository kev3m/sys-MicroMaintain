package micromaintainsys.dao.estoque;

import micromaintainsys.model.Estoque;
import micromaintainsys.model.OrdemCompra;
import micromaintainsys.model.Peca;

import java.util.ArrayList;
import java.util.Hashtable;

public class EstoqueFakeDAO implements InterfaceEstoque {

    private Hashtable<String, Integer> pecas;
    private ArrayList<OrdemCompra> ordensCompra;

    public void atualiza(Estoque estoque){
        return;
    }
    public Estoque cria(){
        Hashtable<String, Integer> pecas = new Hashtable<>();
        ArrayList<OrdemCompra> ordensCompra = new ArrayList<>();
        Estoque estoque = new Estoque(pecas, ordensCompra);
        return estoque;
    }
}
