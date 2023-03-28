package micromaintainsys.dao.cliente;

import micromaintainsys.model.Cliente;
import micromaintainsys.model.Ordem;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Esta classe implementa um DAO para gerenciar objetos do tipo Cliente
 * Ela fornece métodos para inserir, atualizar, excluir e buscar clientes.
 *
 * @author Keven e Freixo
 * @version 1.0

*/
public class ClienteFakeDAO implements ClienteDAO {
    /*Alterar lógica de banco de dados */
    static Hashtable<Integer, Cliente> clientesCadastrados;
    private static int idCounter = 0;

    /**
     * Cria um objeto do tipo Cliente
     *
     * @param endereco
     * @param nome
     * @param telefone
     * @return O ID do Cliente criado
     */
    public int create(String endereco, String nome, String telefone) {
        Cliente novoCliente = new Cliente(endereco, nome, telefone, new ArrayList<Ordem>());
        novoCliente.setClienteID(idCounter);
        clientesCadastrados.put(idCounter,novoCliente);
        idCounter++;
        return novoCliente.getId();
    }

    /**
     * Busca uma pessoa pelo seu ID
     *
     * @param clienteId
     * @return O cliente encontrado, ou null se não existir
     */
    public Cliente findById(int clienteId) {return clientesCadastrados.get(clienteId);}


    /**
     * Atualiza um atributo de um cliente no mapa de clientes cadastrados.
     *
     * @param clienteId o ID do cliente a ser atualizado
     * @param atributo o nome do atributo a ser atualizado ("nome", "endereco" ou "telefone")
     * @param valor o novo valor para o atributo
     * @return true se o cliente foi atualizado com sucesso, ou false se o ID do cliente não foi encontrado ou se o nome do atributo é inválido
     */
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
