package micromaintainsys.dao.cliente;

import micromaintainsys.model.Cliente;

public interface ClienteDAO {
    public int create(String endereco, String nome, String telefone);
    public Cliente findById(int id);
    public <T> boolean update(int clienteId, String atributo, T valor);

    public boolean delete(int id);


}
