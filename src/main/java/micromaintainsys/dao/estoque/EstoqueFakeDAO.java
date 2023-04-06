package micromaintainsys.dao.estoque;

import micromaintainsys.model.Estoque;
import micromaintainsys.model.OrdemCompra;
import micromaintainsys.model.Peca;

import java.util.ArrayList;
import java.util.Hashtable;

public class EstoqueFakeDAO implements InterfaceEstoque {
    private Hashtable<String, Integer> pecas;
    private ArrayList<OrdemCompra> ordensCompra;

    public Estoque cria(){
        Hashtable<String, Integer> pecas = new Hashtable<>();
        ArrayList<OrdemCompra> ordensCompra = new ArrayList<>();
        Estoque estoque = new Estoque(pecas, ordensCompra);
        return estoque;
    }

    @Override
    public void adicionaPeca(String peca, int quantidade) {
        peca = peca.toLowerCase();
        if (!pecas.containsKey(peca)){
            pecas.put(peca, quantidade);
        }
        pecas.replace(peca, pecas.get(peca) + quantidade);
    }

    @Override
    public void removePeca(String peca, int quantidade) {
        peca = peca.toLowerCase();
        /*Não pode tirar mais que o que tem estoque*/
        if (quantidade > pecas.get(peca))
            quantidade = pecas.get(peca);
        pecas.replace(peca, pecas.get(peca) -quantidade);
    }

    @Override
    public int pegaEstoqueDePeca(String peca) {
        peca = peca.toLowerCase();
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
