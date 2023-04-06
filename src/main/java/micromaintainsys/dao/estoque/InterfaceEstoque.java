package micromaintainsys.dao.estoque;

import micromaintainsys.model.Estoque;
import micromaintainsys.model.Peca;

public interface InterfaceEstoque {
    public Estoque cria();
    public void adicionarPeca(Peca peca);
    public void removerPeca(Peca peca);
    public Peca buscarPeca(String nome);
}
