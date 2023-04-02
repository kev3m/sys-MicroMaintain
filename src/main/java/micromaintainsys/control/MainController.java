package micromaintainsys.control;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Queue;

import micromaintainsys.dao.DAO;
import micromaintainsys.model.Cliente;
import micromaintainsys.model.Ordem;
import micromaintainsys.model.OrdemCompra;
import micromaintainsys.model.Tecnico;
import micromaintainsys.model.Fatura;

/**
 * Controller responsável por prover a interface com o sistema.
 */
public class MainController {
    private Hashtable<Integer, Tecnico> tecnicosCadastrados;
    private Hashtable<Integer, Cliente> clientesCadastrados;
    private Queue<Ordem> ordensAbertas;
    private ArrayList<Ordem> ordensCanceladas;
    private ArrayList<Ordem> ordensFinalizadas;
    private ArrayList<OrdemCompra> ordensCompras;
    private ArrayList<Fatura> faturas;
    //Armazena o ID do tecnico logado no sistema
    private int tecnicoSessaoID;

    /*
    Métodos relacionados a TÉCNICOS
    */
    /**
     * Cria um novo técnico.
     * @param nome nome do técnico
     * @param senha senha do técnico
     * @return id do novo técnico
     */
    public int criaTecnico(String nome, String senha){
        return DAO.getTecnicoDAO().cria(nome, senha);
    }

    /**
     * Realiza o login de um técnico.
     * @param id id do técnico
     * @param senha senha do técnico
     * @return  true: login realizado com sucesso, false: não foi possível realizar o login
     */
    public boolean loginTecnico(int id, String senha){
        /*Não é possível fazer login sem fazer logoff do técnico anterior!*/
        if (this.tecnicoSessaoID >=0 && DAO.getTecnicoDAO().autentica(id, senha)){
            this.tecnicoSessaoID = id;
            return true;
        }
        return false;
    }

    /**
     * Realiza o log out do técnico.
     */
    public void logoutTecnico(){
        this.tecnicoSessaoID = -1;
    }

    /**
     * Remove um técnico do sistema.
     * @param tecnicoID id do técnico a ser removido
     * @return
     */
    public boolean removeTecnico(int tecnicoID){
        if (tecnicoID >= 0 && DAO.getTecnicoDAO().remove(tecnicoID)){
            return true;
        }
        return false;
    }

    /*
    Métodos relacionados a CLIENTES
     */

    /**
     * Cria um novo cliente.
     * @param nome nome do cliente
     * @param endereco endereço do cliente
     * @param telefone telefone do cliente
     * @return id do cliente
     */
    public int criaCliente(String nome, String endereco, String telefone){
        return DAO.getClienteDAO().create(nome, endereco, telefone);
    }

    /**
     * Remove um cliente.
     * @param clienteID id do cliente
     * @return
     */
    public boolean removeCliente(int clienteID){
        if (clienteID >= 0 && DAO.getClienteDAO().remove(clienteID)){
            return true;
        }
        return false;
    }
}
