package micromaintainsys.dao.servico;

import micromaintainsys.model.*;

import java.util.Hashtable;
/**
 * Implementação do gerenciamento das operações de acesso aos dados.
 * Permite criar, remover, autenticar e atualizar informações das ordens.
 */
public class ServicoFakeDAO {
    static Hashtable<Integer, Servico> servicosCadastrados;
    private static int idCounter = 0;

    public int cria(CategoriaServico categoriaServico, double valor, Peca peca, String descricao, int ordemID){
        Servico novoServico = new Servico(categoriaServico, valor, peca, descricao, ordemID);
        novoServico.setServicoID(idCounter);
        servicosCadastrados.put(idCounter, novoServico);
        idCounter++;
        return novoServico.getServicoID();
    }

    public boolean remove(int servicoId) {
        Servico result = servicosCadastrados.remove(servicoId);
        if (result != null){
            return true;
        }
        return false;
    }
}