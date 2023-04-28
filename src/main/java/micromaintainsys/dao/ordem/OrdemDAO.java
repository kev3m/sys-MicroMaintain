package micromaintainsys.dao.ordem;

import micromaintainsys.model.Ordem;
import micromaintainsys.model.StatusOrdem;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import static micromaintainsys.utils.FileUtils.*;

/**
 * Classe que implementa a interface InterfaceOrdem e fornece uma implementação para as operações de
 * acesso a dados relacionados à ordem
 */
public class OrdemDAO implements InterfaceOrdem{
    /**
     * Armazena as ordens cadastradas, com chave sendo o ID da ordem.
     */
    static Hashtable<Integer, Ordem> ordensCadastradas;
    private static final String FILE_PATH = getFilePath("ordens.bin");
    /**
     Contador estático usado para gerar IDs únicos para cada nova ordem criada.
     */
    private static int idCounter = 0;

    public OrdemDAO(){
        Object obj = carregaDados(FILE_PATH);
        ordensCadastradas = obj == null? new Hashtable<>() : (Hashtable<Integer, Ordem>) obj;
        /*Recupera o idCounter com base no último ID utilizado*/
        idCounter = proximoID();
    }
    public int proximoID(){
        Enumeration<Integer> keys = ordensCadastradas.keys();
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
     * Cria uma nova ordem com o ID do cliente passado como parâmetro e a adiciona na hashtable ordensCadastradas.
     * @param clienteID ID do cliente que fez a ordem.
     * @return Ordem criada.
     */
    public Ordem cria(int clienteID){
        Ordem novaOrdem = new Ordem(clienteID);
        novaOrdem.setOrdemID(idCounter);
        ordensCadastradas.put(idCounter, novaOrdem);
        salvaDados(ordensCadastradas, FILE_PATH);
        idCounter++;
        return novaOrdem;
    }
    /**
     * Retorna a ordem com o ID passado como parâmetro.
     * @param ordemId ID da ordem que se quer buscar.
     * @return Ordem encontrada, ou null se não houver nenhuma com o ID passado.
     */
    public Ordem pegaPorId(int ordemId) {return ordensCadastradas.get(ordemId);}
    /**
     * Atualiza a ordem passada como parâmetro.
     * @param ordem Ordem que se quer atualizar.
     * @return true se a atualização foi bem sucedida, ou false caso contrário.
     */
    public boolean atualiza(Ordem ordem){
        salvaDados(ordensCadastradas, FILE_PATH);
        return true;
    }
    /**
     * Remove a ordem com o ID passado como parâmetro do banco de dados.
     * @param ordemId ID da ordem que se quer remover.
     * @return true se a remoção foi bem sucedida, ou false caso contrário.
     */
    public boolean remove(int ordemId) {
        Ordem result = ordensCadastradas.remove(ordemId);
        if (result != null){
            salvaDados(ordensCadastradas, FILE_PATH);
            return true;
        }
        return false;
    }
    /**
     * Retorna todas as ordens no banco de dados com o status passado como parâmetro.
     * @param status Status das ordens que se quer buscar.
     * @return ArrayList com as ordens encontradas.
     */
    public ArrayList<Ordem> pegaTodasPorStatus(StatusOrdem status){
        ArrayList<Ordem> ordensStatus = new ArrayList<>();
        for (Ordem ordem: ordensCadastradas.values()){
            if (ordem.getStatus() == status){
                ordensStatus.add(ordem);
            }
        }
        return ordensStatus;
    }
    /**
     * Retorna todas as ordens.
     * @return ArrayList com as ordens encontradas.
     */
    public ArrayList<Ordem> pegaTodas(){
        return new ArrayList<>(ordensCadastradas.values());
    }
    /**
     Reseta o contador de IDs das faturas para 1.
     */
    public void resetIDCounter(){ idCounter = 1;}

}
