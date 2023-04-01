package micromaintainsys.dao.tecnico;

import micromaintainsys.model.Ordem;
import micromaintainsys.model.OrdemCompra;
import micromaintainsys.model.Tecnico;

import java.util.ArrayList;
import java.util.Hashtable;

public class TecnicoFakeDAO implements InterfaceTecnico{
    static Hashtable<Integer, Tecnico> tecnicosCadastrados;
    private static int idCounter = 0;
    public Tecnico pegaPorId(int tecnicoID){
        return tecnicosCadastrados.get(tecnicoID);
    }
    public int cria(String nome, String senha){
        Tecnico novoTecnico = new Tecnico(nome, senha);
        novoTecnico.setTecnicoID(idCounter);
        tecnicosCadastrados.put(idCounter, novoTecnico);
        idCounter++;
        return novoTecnico.getTecnicoID();
   }

   public boolean autentica(int tecnicoID, String senha){
        Tecnico tecnico = tecnicosCadastrados.get(tecnicoID);
        if (tecnico != null){
            if (senha == tecnico.getSenha()) return true;
        }
        return false;
   }

   public boolean remove(int tecnicoID){
        Tecnico result = tecnicosCadastrados.remove(tecnicoID);
        if (result != null){
            return true;
        }
        return false;
   }

   public <T> boolean atualiza(int tecnicoID, String atributo, T valor){
        Tecnico tecnico = tecnicosCadastrados.get(tecnicoID);
        if (tecnico == null){
            return false;
        }
        switch(atributo){
            case "nome":
                tecnico.setNome((String) valor);
                break;
            case "senha":
                tecnico.setSenha((String) valor);
                break;
            case "adm":
                tecnico.setAdm((boolean) valor);
                break;
            default:
                return false;
       }
       return true;
   }
}
