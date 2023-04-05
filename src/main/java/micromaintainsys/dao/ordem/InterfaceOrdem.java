package micromaintainsys.dao.ordem;

import micromaintainsys.dao.CRUD;
import micromaintainsys.model.Ordem;
import micromaintainsys.model.Servico;

import java.util.ArrayList;

public interface InterfaceOrdem extends CRUD<Ordem> {
    public Ordem cria(int clienteID);
}
