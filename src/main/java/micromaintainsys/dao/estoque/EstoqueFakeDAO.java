package micromaintainsys.dao.estoque;

import micromaintainsys.model.Estoque;
import micromaintainsys.model.OrdemCompra;
import micromaintainsys.model.Peca;
import micromaintainsys.model.TipoDePeca;

import java.util.ArrayList;
import java.util.Hashtable;

public class EstoqueFakeDAO implements InterfaceEstoque {
    private Hashtable<TipoDePeca, Integer> pecas;
    private ArrayList<OrdemCompra> ordensCompra;

    public Estoque cria(){
        Hashtable<TipoDePeca, Integer> pecas = new Hashtable<>();
        ArrayList<OrdemCompra> ordensCompra = new ArrayList<>();
        Estoque estoque = new Estoque(pecas, ordensCompra);
        return estoque;
    }

    @Override
    public void adicionaPeca(TipoDePeca peca) {
        pecas.replace(peca, pecas.get(peca) + 1);
    }

    @Override
    public void removePeca(TipoDePeca peca) {
        pecas.replace(peca, pecas.get(peca) -1);
    }

    @Override
    public int pegaEstoqueDePeca(TipoDePeca peca) {
        return this.pecas.get(peca);
    }

    @Override
    public void criaOrdemCompra(OrdemCompra ordemCompra) {
        ordensCompra.add(ordemCompra);
    }
    @Override
    public void removeOrdemCompra(OrdemCompra ordemCompra) {
        ordensCompra.remove(ordemCompra);
    }
    @Override
    public ArrayList<OrdemCompra> verificaOrdensCompra() {
        return ordensCompra;
    }

}
