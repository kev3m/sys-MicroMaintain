package micromaintainsys.dao.pagamento;

import micromaintainsys.dao.CRUD;
import micromaintainsys.model.Pagamento;
import micromaintainsys.model.TipoPagamento;

public interface InterfacePagamento extends CRUD<Pagamento> {
    public Pagamento cria(TipoPagamento pagamento, double valor, int faturaID);
    public boolean atualiza(Pagamento pagamento);

}
