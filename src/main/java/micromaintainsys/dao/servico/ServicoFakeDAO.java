package micromaintainsys.dao.servico;

import micromaintainsys.model.*;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
/**
 * Implementação do gerenciamento das operações de acesso aos dados.
 * Permite criar, remover, autenticar e atualizar informações das ordens.
 */
public class ServicoFakeDAO implements InterfaceServico {
    static Hashtable<Integer, Servico> servicosCadastrados;
    private static int idCounter = 0;

    public Servico cria(CategoriaServico categoriaServico, double valor, Peca peca, String descricao, int ordemID){
        Servico novoServico = new Servico(categoriaServico, valor, peca, descricao, ordemID);
        novoServico.setServicoID(idCounter);
        servicosCadastrados.put(idCounter, novoServico);
        idCounter++;
        return novoServico;
    }

    public Servico pegaPorId(int ordemId) {return servicosCadastrados.get(ordemId);}

    public boolean atualiza(Servico servico){
        return true;
    }
    public boolean remove(int servicoId) {
        Servico result = servicosCadastrados.remove(servicoId);
        if (result != null){
            return true;
        }
        return false;
    }
    public ArrayList<Servico> pegaTodosPorOrdemID(int ordemID){
        ArrayList<Servico> servicos = new ArrayList<Servico>();
        Enumeration<Servico> servicosEnum = this.servicosCadastrados.elements();
        while (servicosEnum.hasMoreElements()){
            Servico servico = servicosEnum.nextElement();
            if (servico.getOrdemID() == ordemID){
                servicos.add(servico);
            }
        }
        return servicos;
    }
}