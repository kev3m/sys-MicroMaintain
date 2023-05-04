package micromaintainsys.dao.tecnico;

import micromaintainsys.model.Tecnico;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import static micromaintainsys.utils.FileUtils.*;

/**
 * Implementação do gerenciamento das operações de acesso aos dados.
 * Permite ler,criar, remover e autenticar e atualizar informações dos técnicos.
 */

public class TecnicoDAO implements InterfaceTecnico{
    /**
     * Armazena os tecnicos cadastradps.
     */
    static Hashtable<Integer, Tecnico> tecnicosCadastrados = new Hashtable<>();
    /**
     Contador estático usado para gerar IDs únicos para cada novo tecnico criado.
     */
    private static int idCounter;
    private static final String FILE_PATH = getFilePath("tecnicos.bin");


    public TecnicoDAO(){
        Object obj = carregaDados(FILE_PATH);
        tecnicosCadastrados = obj == null? new Hashtable<>() : (Hashtable<Integer, Tecnico>) obj;
        /*Recupera o idCounter com base no último ID utilizado*/
        idCounter = proximoID();
        /*Na primeira execução, cria o usuário admin*/
        if (idCounter == 0){
            Tecnico adm = cria("admin", "admin");
            adm.setAdm(true);
            atualiza(adm);
        }
    }
    public int proximoID(){
        Enumeration<Integer> keys = tecnicosCadastrados.keys();
        int max = -1;
        while (keys.hasMoreElements()){
            int key = keys.nextElement();
            if (key > max){
                max = key;
            }
        }
        return max + 1;
    }
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
        salvaDados(tecnicosCadastrados, FILE_PATH);
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
            if (tecnico.getSenha().equals(senha)){
                return true;
            }
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
            salvaDados(tecnicosCadastrados, FILE_PATH);
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
       salvaDados(tecnicosCadastrados, FILE_PATH);
       return true;
   }
    /**
     Retorna todos os tecnicos cadastrados.
     @return Lista de todos os tecnicos cadastrados.
     */
   public ArrayList<Tecnico>pegaTodos(){
       return (new ArrayList<Tecnico>(tecnicosCadastrados.values()));
    }
    /**
     Reseta o contador de IDs das faturas para 1.
     */
    public void resetIDCounter(){ idCounter = 1;}
    public void removeTodos(){
        tecnicosCadastrados.clear();
    }
}
