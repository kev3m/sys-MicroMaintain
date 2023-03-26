package micromaintainsys.dao.cliente;

import micromaintainsys.model.Cliente;
import micromaintainsys.model.Ordem;

import java.util.ArrayList;
import java.util.Hashtable;

public class ClienteFakeDAO implements ClienteDAO {
    /*Alterar lógica de banco de dados */
    static Hashtable<Integer, Cliente> clientesCadastrados;
    private static int idCounter = 0;

    public int create(String endereco, String nome, String telefone) {
        Cliente novoCliente = new Cliente(endereco, nome, telefone, new ArrayList<Ordem>());
        novoCliente.setClienteID(idCounter);
        clientesCadastrados.put(idCounter,novoCliente);
        idCounter++;
        return novoCliente.getId();
    }

    public Cliente findById(int clienteId) {return clientesCadastrados.get(clienteId);}


    @Override
    public <T> boolean update(int clienteId, String atributo, T valor) {
        Cliente cliente = clientesCadastrados.get(clienteId);
        if(cliente == null){
            return false;
        }
        switch (atributo){
            case "nome":
                cliente.setNome((String) valor);
                break;
            case "endereco":
                cliente.setEndereco((String) valor);
                break;
            case "telefone":
                cliente.setTelefone((String) valor);
                break;
            default:
                return false;
        }
        return true;

    }

    @Override
    public void delete(int id) {

    }
}
