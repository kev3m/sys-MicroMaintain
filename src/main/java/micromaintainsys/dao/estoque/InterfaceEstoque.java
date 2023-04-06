package micromaintainsys.dao.estoque;

import micromaintainsys.model.Estoque;
import micromaintainsys.model.OrdemCompra;
import micromaintainsys.model.Peca;
import micromaintainsys.model.TipoDePeca;

import java.util.ArrayList;

public interface InterfaceEstoque {
    public Estoque cria();
    public void adicionaPeca(TipoDePeca peca);
    public void removePeca(TipoDePeca peca);
    public int pegaEstoqueDePeca(TipoDePeca peca);

    public void criaOrdemCompra(OrdemCompra ordemCompra);
    public void removeOrdemCompra(OrdemCompra ordemCompra);
    public ArrayList<OrdemCompra> verificaOrdensCompra();
}
