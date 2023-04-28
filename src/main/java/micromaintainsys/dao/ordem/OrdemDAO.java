package micromaintainsys.dao.ordem;

import micromaintainsys.model.Ordem;
import micromaintainsys.model.StatusOrdem;

import java.util.ArrayList;
import java.util.Hashtable;
/**
 * Classe que implementa a interface InterfaceOrdem e fornece uma implementação para as operações de
 * acesso a dados relacionados à ordem
 */
public class OrdemDAO implements InterfaceOrdem{
    /**
     * Armazena as ordens cadastradas, com chave sendo o ID da ordem.
     */
    static Hashtable<Integer, Ordem> ordensCadastradas = new Hashtable<>();
    /**
     Contador estático usado para gerar IDs únicos para cada nova ordem criada.
     */
    private static int idCounter = 0;
    /**
     * Cria uma nova ordem com o ID do cliente passado como parâmetro e a adiciona na hashtable ordensCadastradas.
     * @param clienteID ID do cliente que fez a ordem.
     * @return Ordem criada.
     */
    public Ordem cria(int clienteID){
        Ordem novaOrdem = new Ordem(clienteID);
        novaOrdem.setOrdemID(idCounter);
        ordensCadastradas.put(idCounter, novaOrdem);
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
