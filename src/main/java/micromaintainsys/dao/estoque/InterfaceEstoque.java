package micromaintainsys.dao.estoque;

import micromaintainsys.model.Estoque;
import micromaintainsys.model.OrdemCompra;
import micromaintainsys.model.Peca;

import java.util.ArrayList;

public interface InterfaceEstoque {
    public Estoque cria();
    public void adicionaPeca(String peca, int quantidade);
    public void removePeca(String peca, int quantidade);
    public int pegaEstoqueDePeca(String peca);

    public void criaOrdemCompra(OrdemCompra ordemCompra);
    public void removeOrdemCompra(OrdemCompra ordemCompra);
    public ArrayList<OrdemCompra> verificaOrdensCompra();
}
