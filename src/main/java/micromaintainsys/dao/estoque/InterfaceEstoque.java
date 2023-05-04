package micromaintainsys.dao.estoque;
import micromaintainsys.model.Estoque;

import java.util.ArrayList;

public interface InterfaceEstoque {

    public void atualiza(Estoque estoque);
    public Estoque carrega();
}
