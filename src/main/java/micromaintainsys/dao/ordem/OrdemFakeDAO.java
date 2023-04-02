package micromaintainsys.dao.ordem;

import micromaintainsys.model.Cliente;
import micromaintainsys.model.Ordem;
import micromaintainsys.model.Tecnico;

import java.util.Hashtable;
/**
 * Implementação do gerenciamento das operações de acesso aos dados.
 * Permite criar, remover, autenticar e atualizar informações das ordens.
 */
public class OrdemFakeDAO {
    static Hashtable<Integer, Ordem> ordensCadastradas;
    private static int idCounter = 0;

    public int cria(int clienteID, int tecnicoID){
        Ordem novaOrdem = new Ordem(clienteID, tecnicoID);
        novaOrdem.setOrdemID(idCounter);
        ordensCadastradas.put(idCounter, novaOrdem);
        idCounter++;
        return novaOrdem.getOrdemID();
    }
}
