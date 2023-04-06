package micromaintainsys.dao.estoque;

import micromaintainsys.model.Estoque;
import micromaintainsys.model.OrdemCompra;
import micromaintainsys.model.Peca;

import java.util.ArrayList;

public interface InterfaceEstoque {

    public void atualiza(Estoque estoque);
    public Estoque cria();
}
