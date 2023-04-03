package micromaintainsys.dao.ordem;

import micromaintainsys.dao.CRUD;
import micromaintainsys.model.Ordem;
import micromaintainsys.model.Servico;

import java.util.ArrayList;

public interface InterfaceOrdem extends CRUD<Ordem> {
    public int cria(int clienteID, int tecnicoID);
}
