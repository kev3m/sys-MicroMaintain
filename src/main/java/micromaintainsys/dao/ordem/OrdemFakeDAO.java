package micromaintainsys.dao.ordem;

import micromaintainsys.model.Cliente;
import micromaintainsys.model.Ordem;
import micromaintainsys.model.Tecnico;

import java.util.Hashtable;
/**
 * Implementação do gerenciamento das operações de acesso aos dados.
 * Permite criar, remover, autenticar e atualizar informações das ordens.
 */
public class OrdemFakeDAO implements InterfaceOrdem{
    static Hashtable<Integer, Ordem> ordensCadastradas;
    private static int idCounter = 0;

    public int cria(int clienteID, int tecnicoID){
        Ordem novaOrdem = new Ordem(clienteID, tecnicoID);
        novaOrdem.setOrdemID(idCounter);
        ordensCadastradas.put(idCounter, novaOrdem);
        idCounter++;
        return novaOrdem.getOrdemID();
    }
    public Ordem pegaPorId(int ordemId) {return ordensCadastradas.get(ordemId);}

    public <T> boolean atualiza(int ordemId, String atributo, T valor){
        Ordem ordem = ordensCadastradas.get(ordemId);
        if (ordem == null){
            return false;
        }
        switch(atributo){
            case "status":
                ordem.setStatus((Ordem.StatusOrdem) valor);
                break;
            case "avaliacaoFinal":
                ordem.setAvaliacaoFinal((String) valor);
                break;
            // Reaproveitar para setar o ID do técnico
            default:
                return false;
        }
        return true;
    }

    public boolean remove(int ordemId) {
        Ordem result = ordensCadastradas.remove(ordemId);
        if (result != null){
            return true;
        }
        return false;
    }
}
