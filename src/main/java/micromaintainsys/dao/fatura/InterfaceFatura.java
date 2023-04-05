package micromaintainsys.dao.fatura;

import micromaintainsys.dao.CRUD;
import micromaintainsys.model.Fatura;

public interface InterfaceFatura extends CRUD<Fatura> {
    public Fatura cria(int clienteID, double valorTotal);

}
