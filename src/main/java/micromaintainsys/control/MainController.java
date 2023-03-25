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

    public int criaTecnico(String nome, String senha){
        return DAO.getTecnicoDAO().cria(nome, senha);
    }
    public boolean loginTecnico(int id, String senha){
        /*Não é possível fazer login sem fazer logoff do técnico anterior!*/
        if (this.tecnicoSessaoID >=0 && DAO.getTecnicoDAO().autentica(id, senha)){
            this.tecnicoSessaoID = id;
            return true;
        }
        return false;
    }
    public void logoutTecnico(){
        this.tecnicoSessaoID = -1;
    }

    public boolean removeTecnico(int tecnicoID){
        if (tecnicoID >= 0 && DAO.getTecnicoDAO().remove(tecnicoID)){
            return true;
        }
        return false;
    }
}
