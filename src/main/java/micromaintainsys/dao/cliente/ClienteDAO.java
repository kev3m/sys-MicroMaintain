package micromaintainsys.dao.cliente;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

import micromaintainsys.model.Cliente;

import static micromaintainsys.utils.FileUtils.*;

import java.util.ArrayList;



/**
 * Implementação do gerenciamento das operações de acesso aos dados.
 * Permite Ler, criar, remover e atualizar informações dos clientes.
 *

*/
public class ClienteDAO implements InterfaceCliente, Serializable {
    /*Alterar lógica de banco de dados */
    private static final String FILE_NAME = "clientes.bin";
    private static final String FILE_PATH = initFilePath();
    private static String initFilePath() {
        return getFilePath(FILE_NAME);
    }

    static Hashtable<Integer, Cliente> clientesCadastrados;
    private static int idCounter = 0;

    public ClienteDAO(){
        Object obj = carregaDados(FILE_PATH);
        if (obj == null){
            clientesCadastrados = new Hashtable<>();
        }
        else {
            clientesCadastrados = (Hashtable<Integer, Cliente>) carregaDados(FILE_PATH);
        }
        /*Recupera o idCounter com base no último ID utilizado*/
        Enumeration<Integer> keys = clientesCadastrados.keys();
        int max = keys.nextElement();
        while (keys.hasMoreElements()){
            int key = keys.nextElement();
            if (key > max){
                max = key;
            }
        }
        idCounter = max + 1;
    }

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
        salvaDados(clientesCadastrados, FILE_PATH);
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
        salvaDados(clientesCadastrados, FILE_PATH);
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
            salvaDados(clientesCadastrados, FILE_PATH);
            return true;
        }

        return false;
    }
    public ArrayList<Cliente> pegaTodos(){
        return new ArrayList<>(clientesCadastrados.values());
    }
    public void resetIDCounter(){
        idCounter = 0;
    }
    public void esvaziarClientesCadastrados() {
        clientesCadastrados.clear();
        salvaDados(clientesCadastrados, FILE_PATH);
    }

}
