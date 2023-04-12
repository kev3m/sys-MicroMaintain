package micromaintainsys.dao.fatura;

import micromaintainsys.dao.CRUD;
import micromaintainsys.model.Fatura;

import java.util.ArrayList;

public interface InterfaceFatura extends CRUD<Fatura> {
    public Fatura cria(int clienteID, double valorTotal);
    public boolean atualiza(Fatura fatura);
    public ArrayList<Fatura> pegaTodas();

}
