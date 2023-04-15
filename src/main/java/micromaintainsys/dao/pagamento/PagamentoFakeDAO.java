package micromaintainsys.dao.pagamento;

import micromaintainsys.model.Pagamento;
import micromaintainsys.model.TipoPagamento;

import java.util.Hashtable;

public class PagamentoFakeDAO implements InterfacePagamento{
    static Hashtable<Integer, Pagamento> pagamentosCadastrados = new Hashtable<>();
    private static int idCounter = 0;
    public Pagamento cria(TipoPagamento pagamento, double valor, int faturaID){
       Pagamento novoPagamento = new Pagamento(pagamento, valor, faturaID);
        novoPagamento.setPagamentoID(idCounter);
        pagamentosCadastrados.put(idCounter, novoPagamento);
        idCounter++;
        return novoPagamento;
    }
    public boolean atualiza(Pagamento pagamento){
        return true;
    }
    public Pagamento pegaPorId(int pagamentoID){
        return pagamentosCadastrados.get(pagamentoID);
    }
    public boolean remove(int pagamentoID) {
        Pagamento result = pagamentosCadastrados.remove(pagamentoID);
        if (result != null){
            return true;
        }
        return false;
    }
}
