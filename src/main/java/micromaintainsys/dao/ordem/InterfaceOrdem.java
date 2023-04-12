package micromaintainsys.dao.ordem;
import micromaintainsys.dao.CRUD;
import micromaintainsys.model.Ordem;
import micromaintainsys.model.StatusOrdem;

import java.util.ArrayList;

public interface InterfaceOrdem extends CRUD<Ordem> {
    public Ordem cria(int clienteID);
    public boolean atualiza(Ordem ordem);

    public ArrayList<Ordem> pegaTodasPorStatus(StatusOrdem status);
    public ArrayList<Ordem> pegaTodas();
}
