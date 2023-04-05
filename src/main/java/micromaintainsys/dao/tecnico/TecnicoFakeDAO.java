package micromaintainsys.dao.tecnico;

import micromaintainsys.model.Ordem;
import micromaintainsys.model.OrdemCompra;
import micromaintainsys.model.Tecnico;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Implementação do gerenciamento das operações de acesso aos dados.
 * Permite ler,criar, remover e autenticar e atualizar informações dos técnicos.
 */

public class TecnicoFakeDAO implements InterfaceTecnico{
    static Hashtable<Integer, Tecnico> tecnicosCadastrados;
    private static int idCounter = 0;

    /**
     * Busca e retorna um técnico por ID
     * @param tecnicoID
     * @return Objeto Técnico
     */
    public Tecnico pegaPorId(int tecnicoID){
        return tecnicosCadastrados.get(tecnicoID);
    }

    /**
     * Cria um objeto do tipo técnico
     * @param nome
     * @param senha
     * @return ID do técnico criado
     */
    public Tecnico cria(String nome, String senha){
        Tecnico novoTecnico = new Tecnico(nome, senha);
        novoTecnico.setTecnicoID(idCounter);
        tecnicosCadastrados.put(idCounter, novoTecnico);
        idCounter++;
        return novoTecnico;
   }

    /**
     * Autentica o técnico no login
     * @param tecnicoID
     * @param senha
     * @return true: login realizado com sucesso, false: não foi possível realizar o login
     */
   public boolean autentica(int tecnicoID, String senha){
        Tecnico tecnico = tecnicosCadastrados.get(tecnicoID);
        if (tecnico != null){
            if (senha == tecnico.getSenha()) return true;
        }
        return false;
   }

    /**
     * Remove um objeto técnico da lista de técnicos
     * @param tecnicoID
     * @return true: remoção efetuada com sucesso, false: não foi possível realizar a remoção
     */
   public boolean remove(int tecnicoID){
        Tecnico result = tecnicosCadastrados.remove(tecnicoID);
        if (result != null){
            return true;
        }
        return false;
   }

    /**
     * Atualiza os dados do técnico
     * @param tecnico
     * @return
     */
   public boolean atualiza(Tecnico tecnico) {
    /*Na implementação propriamente dita do DAO,
    essa função recebe um objeto e sobrescreve
    os dados
     */
       return true;
   }
}
