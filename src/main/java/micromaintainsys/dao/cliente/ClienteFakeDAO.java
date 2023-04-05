package micromaintainsys.dao.cliente;

import micromaintainsys.model.Cliente;
import micromaintainsys.model.Ordem;
import micromaintainsys.model.Tecnico;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 * Implementação do gerenciamento das operações de acesso aos dados.
 * Permite Ler, criar, remover e atualizar informações dos clientes.
 *

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
    public Cliente cria(String nome, String endereco, String telefone) {
        Cliente novoCliente = new Cliente(nome, endereco, telefone);
        novoCliente.setClienteID(idCounter);
        clientesCadastrados.put(idCounter,novoCliente);
        idCounter++;
        return novoCliente;
    }

    /**
     * Busca uma pessoa pelo seu ID
     *
     * @param clienteId
     * @return O cliente encontrado, ou null se não existir
     */
    public Cliente pegaPorId(int clienteId) {return clientesCadastrados.get(clienteId);}


    /**
     *
     * @param cliente
     * @return
     */
    @Override
    public boolean atualiza(Cliente cliente) {
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
        Cliente result = clientesCadastrados.remove(clienteId);
        if (result != null){
            return true;
        }

        return false;
    }

}
