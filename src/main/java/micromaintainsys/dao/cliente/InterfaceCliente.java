package micromaintainsys.dao.cliente;

import micromaintainsys.dao.CRUD;
import micromaintainsys.model.Cliente;

public interface InterfaceCliente extends CRUD<Cliente> {
    public Cliente cria(String nome, String endereco, String telefone);
    public boolean atualiza(Cliente cliente);

}
