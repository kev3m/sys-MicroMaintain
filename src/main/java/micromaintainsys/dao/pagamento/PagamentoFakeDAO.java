package micromaintainsys.dao.pagamento;

import micromaintainsys.model.Pagamento;
import micromaintainsys.model.TipoPagamento;

import java.util.Hashtable;

/**
 * Classe que implementa a interface InterfacePagamento e fornece uma implementação para as operações de
 * acesso a dados relacionados aos pagamentos
 */
public class PagamentoFakeDAO implements InterfacePagamento{
    /**
     * Hashtable que armazena os pagamentos cadastrados.
     */
    static Hashtable<Integer, Pagamento> pagamentosCadastrados = new Hashtable<>();
    /**
     Contador estático usado para gerar IDs únicos para cada novo pagamento criado.
     */
    private static int idCounter = 0;
    /**
     * Cria um pagamento e o armazena na Hashtable pagamentosCadastrados.
     * @param pagamento Tipo do pagamento.
     * @param valor Valor do pagamento.
     * @param faturaID ID da fatura associada ao pagamento.
     * @return Pagamento criado.
     */
    public Pagamento cria(TipoPagamento pagamento, double valor, int faturaID){
       Pagamento novoPagamento = new Pagamento(pagamento, valor, faturaID);
        novoPagamento.setPagamentoID(idCounter);
        pagamentosCadastrados.put(idCounter, novoPagamento);
        idCounter++;
        return novoPagamento;
    }
    /**
     * Atualiza um pagamento existente.
     * @param pagamento Pagamento a ser atualizado.
     * @return True se o pagamento foi atualizado com sucesso, false caso contrário.
     */
    public boolean atualiza(Pagamento pagamento){
        return true;
    }
    /**
     * Retorna um pagamento a partir de seu ID.
     * @param pagamentoID ID do pagamento a ser retornado.
     * @return Pagamento correspondente ao ID informado, ou null caso não exista nenhum pagamento com o ID informado.
     */
    public Pagamento pegaPorId(int pagamentoID){
        return pagamentosCadastrados.get(pagamentoID);
    }
    /**
     * Remove um pagamento a partir de seu ID.
     * @param pagamentoID ID do pagamento a ser removido.
     * @return True se o pagamento foi removido com sucesso, false caso contrário.
     */
    public boolean remove(int pagamentoID) {
        Pagamento result = pagamentosCadastrados.remove(pagamentoID);
        if (result != null){
            return true;
        }
        return false;
    }
    /**
     Reseta o contador de IDs das faturas para 0.
     */
    public void resetIDCounter(){
        idCounter = 0;
    }

}
