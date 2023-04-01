package micromaintainsys.dao.cliente;

import micromaintainsys.model.Cliente;
import micromaintainsys.model.Ordem;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 * Esta classe implementa um DAO para gerenciar objetos do tipo Cliente
 * Ela fornece métodos para inserir, atualizar, excluir e buscar clientes.
 *
 * @author Keven e Freixo
 * @version 1.0

*/
public class ClienteFakeDAO implements InterfaceCliente {
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
    public int cria(String nome, String endereco, String telefone) {
        Cliente novoCliente = new Cliente(nome, endereco, telefone);
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
    public Cliente pegaPorId(int clienteId) {return clientesCadastrados.get(clienteId);}


    /**
     * Atualiza um atributo de um cliente no mapa de clientes cadastrados.
     *
     * @param clienteId o ID do cliente a ser atualizado
     * @param atributo o nome do atributo a ser atualizado ("nome", "endereco" ou "telefone")
     * @param valor o novo valor para o atributo
     * @return true se o cliente foi atualizado com sucesso, ou false se o ID do cliente não foi encontrado ou se o nome do atributo é inválido
     */
    @Override
    public <T> boolean atualiza(int clienteId, String atributo, T valor) {
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

    /**
     * Remove um cliente da hashtable clientesCadastrados com base no id do cliente.
     * Se o id do cliente especificado não for encontrado na hashtable, nenhum cliente é removido.
     *
     * @param clienteId o id do cliente a ser removido
     * @return True se o cliente for removido, false se o cliente não for encontrado.
     */
    @Override
    public boolean remove(int clienteId) {
        for (Map.Entry<Integer, Cliente> entry : clientesCadastrados.entrySet()) {
            int id = entry.getKey();
            Cliente cliente = entry.getValue();
            if (id == clienteId) {
                clientesCadastrados.remove(id);
                return true;
            }
        }
        return false;
    }

}
