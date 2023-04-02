package micromaintainsys.dao.tecnico;

import micromaintainsys.model.Ordem;
import micromaintainsys.model.OrdemCompra;
import micromaintainsys.model.Tecnico;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Implementação de uma interface para um banco de dados falso de técnicos,
 * permitindo criar, remover, autenticar e atualizar informações dos técnicos.
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
    public int cria(String nome, String senha){
        Tecnico novoTecnico = new Tecnico(nome, senha);
        novoTecnico.setTecnicoID(idCounter);
        tecnicosCadastrados.put(idCounter, novoTecnico);
        idCounter++;
        return novoTecnico.getTecnicoID();
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
     * Atualiza um atributo de técnico
     * @param tecnicoID
     * @param atributo
     * @param valor
     * @return true: atualização efetuada, false: técnico não encontrado ou não foi possível realizar a atualização
     * @param <T>
     */
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
