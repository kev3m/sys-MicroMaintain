package micromaintainsys.dao.cliente;

import micromaintainsys.model.Cliente;

import java.util.ArrayList;

public class ClienteListImp implements ClienteDAO {
    /*Alterar lógica de banco de dados */
    private ArrayList<Cliente> lista;
    public ClienteListImp(){
        this.lista = new ArrayList<Cliente>();
    }
    @Override
    public Cliente create(Cliente cliente) {
        return null;
    }
    @Override
    public Cliente findById(int id) {
        return null;
    }

    @Override
    public void update(Cliente cliente) {

    }

    @Override
    public void delete(int id) {

    }
}
