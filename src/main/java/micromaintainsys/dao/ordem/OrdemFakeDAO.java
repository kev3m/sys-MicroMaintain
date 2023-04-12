package micromaintainsys.dao.ordem;

import micromaintainsys.model.Ordem;
import micromaintainsys.model.StatusOrdem;

import java.util.ArrayList;
import java.util.Hashtable;
/**
 * Implementação do gerenciamento das operações de acesso aos dados.
 * Permite criar, remover, autenticar e atualizar informações das ordens.
 */
public class OrdemFakeDAO implements InterfaceOrdem{
    static Hashtable<Integer, Ordem> ordensCadastradas;
    private static int idCounter = 0;

    public Ordem cria(int clienteID){
        Ordem novaOrdem = new Ordem(clienteID);
        novaOrdem.setOrdemID(idCounter);
        ordensCadastradas.put(idCounter, novaOrdem);
        idCounter++;
        return novaOrdem;
    }
    public Ordem pegaPorId(int ordemId) {return ordensCadastradas.get(ordemId);}

    public boolean atualiza(Ordem ordem){
        return true;
    }

    public boolean remove(int ordemId) {
        Ordem result = ordensCadastradas.remove(ordemId);
        if (result != null){
            return true;
        }
        return false;
    }
    public ArrayList<Ordem> pegaTodasPorStatus(StatusOrdem status){
        ArrayList<Ordem> ordensStatus = new ArrayList<>();
        for (Ordem ordem: ordensCadastradas.values()){
            if (ordem.getStatus() == status){
                ordensStatus.add(ordem);
            }
        }
        return ordensStatus;
    }
    public ArrayList<Ordem> pegaTodas(){
        return (ArrayList<Ordem>) ordensCadastradas.values();
    }
}
