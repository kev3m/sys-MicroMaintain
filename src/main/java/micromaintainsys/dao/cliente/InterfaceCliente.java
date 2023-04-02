package micromaintainsys.dao.cliente;

import micromaintainsys.dao.CRUD;
import micromaintainsys.model.Cliente;

public interface InterfaceCliente extends CRUD<Cliente> {
    public int cria(String nome, String endereco, String telefone);

    /* Extendido pelo CRUD */
    //public <T> boolean update(int clienteId, String atributo, T valor);
    //public boolean remove(int id);


}
