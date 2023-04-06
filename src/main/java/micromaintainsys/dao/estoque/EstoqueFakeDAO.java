package micromaintainsys.dao.estoque;

import micromaintainsys.model.Estoque;
import micromaintainsys.model.OrdemCompra;
import micromaintainsys.model.Peca;

import java.util.ArrayList;

public class EstoqueFakeDAO implements InterfaceEstoque {
    private ArrayList<Peca> pecas;
    private ArrayList<OrdemCompra> ordensCompra;

    public Estoque cria(){
        ArrayList<Peca> pecas = new ArrayList<>();
        ArrayList<OrdemCompra> ordensCompra = new ArrayList<>();
        Estoque estoque = new Estoque(pecas, ordensCompra);
        return estoque;
    }

    @Override
    public void adicionarPeca(Peca peca) {
        pecas.add(peca);
    }

    @Override
    public void removerPeca(Peca peca) {
        pecas.remove(peca);
    }

    @Override
    public Peca buscarPeca(String nome) {
        for (Peca peca : pecas) {
            if (peca.getNome().equals(nome)) {
                return peca;
            }
        }
        return null;
    }

    @Override
    public void criarOrdemCompra(OrdemCompra ordemCompra) {
        ordensCompra.add(ordemCompra);
    }
    @Override
    public void removerOrdemCompra(OrdemCompra ordemCompra) {
        ordensCompra.remove(ordemCompra);
    }
    @Override
    public ArrayList<OrdemCompra> verificarOrdensCompra() {
        return ordensCompra;
    }

}
