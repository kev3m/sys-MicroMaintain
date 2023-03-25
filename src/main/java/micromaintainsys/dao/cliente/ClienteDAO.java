package micromaintainsys.dao.cliente;

import micromaintainsys.model.Cliente;
import micromaintainsys.model.Ordem;

import java.util.ArrayList;

public interface ClienteDAO {
    public Cliente create(Cliente cliente);
    public Cliente findById(int id);
    public void update(Cliente cliente);
    public void delete(int id);


}
