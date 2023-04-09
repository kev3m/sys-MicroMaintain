package sysmicromaintain.model;

import micromaintainsys.model.Pagamento;
import micromaintainsys.model.TipoPagamento;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PagamentoTest {
    private Pagamento pagamento;
    @Before
    public void setUp(){
        pagamento = new Pagamento(TipoPagamento.Dinheiro, 100.0, 1);
    }
    @Test
    public void testGetSetPagamentoID() {
        pagamento.setPagamentoID(10);
        assertEquals(10, pagamento.getPagamentoID());
    }

    @Test
    public void testGetSetTipoPagamento() {
        pagamento.setTipoPagamento(TipoPagamento.Credito);
        assertEquals(TipoPagamento.Credito, pagamento.getTipoPagamento());
    }

    @Test
    public void testGetSetValor() {
        pagamento.setValor(150.0);
        assertEquals(150.0, pagamento.getValor(), 0);
    }

    @Test
    public void testGetSetFaturaID() {
        pagamento.setFaturaID(2);
        assertEquals(2, pagamento.getFaturaID());
    }
}
