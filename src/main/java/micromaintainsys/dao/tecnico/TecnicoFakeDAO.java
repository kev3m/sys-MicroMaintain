package micromaintainsys.dao.tecnico;

import micromaintainsys.model.Ordem;
import micromaintainsys.model.OrdemCompra;
import micromaintainsys.model.Tecnico;

import java.util.ArrayList;
import java.util.Hashtable;

public class TecnicoFakeDAO implements InterfaceTecnico{
    static Hashtable<Integer, Tecnico> tecnicosCadastrados;
    private static int idCounter = 0;
    public Tecnico pegaPorId(int ID){
        return tecnicosCadastrados.get(ID);
    }
    public int cria(String nome, String senha){
        Tecnico novoTecnico = new Tecnico(nome, senha, new ArrayList<Ordem>(), new ArrayList<OrdemCompra>());
        novoTecnico.setTecnicoID(idCounter);
        tecnicosCadastrados.put(idCounter, novoTecnico);
        idCounter++;
        return novoTecnico.getTecnicoID();
   }

   public boolean autentica(int ID, String senha){
        Tecnico tecnico = tecnicosCadastrados.get(ID);
        if (tecnico != null){
            if (senha == tecnico.getSenha()) return true;
        }
        return false;
   }
}
