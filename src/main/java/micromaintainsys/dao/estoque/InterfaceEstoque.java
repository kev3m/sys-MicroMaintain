package micromaintainsys.dao.estoque;

import micromaintainsys.model.Estoque;
import micromaintainsys.model.OrdemCompra;
import micromaintainsys.model.Peca;

import java.util.ArrayList;

public interface InterfaceEstoque {
    public Estoque cria();
    public void adicionarPeca(Peca peca);
    public void removerPeca(Peca peca);
    public Peca buscarPeca(String nome);

    public void criarOrdemCompra(OrdemCompra ordemCompra);
    public void removerOrdemCompra(OrdemCompra ordemCompra);
    public ArrayList<OrdemCompra> verificarOrdensCompra();
}
