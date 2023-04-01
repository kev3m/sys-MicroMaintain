package micromaintainsys.dao.cliente;

import micromaintainsys.model.Cliente;

public interface InterfaceCliente {
    public int cria(String nome, String endereco, String telefone);
    public Cliente pegaPorId(int id);
    public <T> boolean atualiza(int clienteId, String atributo, T valor);

    public boolean remove(int id);


}
