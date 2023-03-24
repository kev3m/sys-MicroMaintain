package micromaintainsys.control;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Queue;

import micromaintainsys.model.Cliente;
import micromaintainsys.model.Ordem;
import micromaintainsys.model.OrdemCompra;
import micromaintainsys.model.Tecnico;
public class MainController {
    private Hashtable<Integer, Tecnico> tecnicosCadastrados;
    private Hashtable<Integer, Cliente> clientesCadastrados;
    private Queue<Ordem> ordensAbertas;
    private ArrayList<Ordem> ordensCanceladas;
    private ArrayList<Ordem> ordensFinalizadas;
    private ArrayList<OrdemCompra> ordensCompras;
    //private ArrayList<Fatura> faturas;
    public void criaTecnico(String nome, String senha){
        Tecnico novoTecnico = new Tecnico(nome, senha, null, null);
        this.tecnicosCadastrados.put(novoTecnico.getTecnicoID(), novoTecnico);
    }
}
