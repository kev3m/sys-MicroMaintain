package sysmicromaintain.dao;
import micromaintainsys.dao.pagamento.PagamentoDAO;
import micromaintainsys.model.Pagamento;
import micromaintainsys.model.TipoPagamento;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;





public class PagamentoDAOTest {
    private PagamentoDAO pagamentoDAO;
    private Pagamento pagamento,resultado;

    @Before
    public void setUp() {
        pagamentoDAO = new PagamentoDAO();
        pagamentoDAO.resetIDCounter();
    }

    @Test
    public void testCriaPagamento() {
        TipoPagamento tipoPagamento = TipoPagamento.Pix;
        double valor = 100.0;
        int faturaID = 1;

        pagamento = pagamentoDAO.cria(tipoPagamento, valor, faturaID);
        Assert.assertNotNull(pagamento);
        Assert.assertEquals(tipoPagamento, pagamento.getTipoPagamento());
        Assert.assertEquals(valor, pagamento.getValor(), 0.0);
        Assert.assertEquals(faturaID, pagamento.getFaturaID());
    }

    @Test
    public void testAtualizaPagamento() {
        pagamento = new Pagamento(TipoPagamento.Credito, 50.0, 1);
        pagamento.setPagamentoID(1);

        boolean resultado = pagamentoDAO.atualiza(pagamento);
        Assert.assertTrue(resultado);
    }


    /*
    @Test
    public void testPegaPagamentoPorId() {
        pagamento = new Pagamento(TipoPagamento.Credito, 50.0, 1);
        pagamento.setPagamentoID(1);

        Assert.assertNotNull(pagamentoFakeDAO.pegaPorId(1));
        Assert.assertEquals(pagamento, pagamentoFakeDAO.pegaPorId(1));
    }
*/
    @Test
    public void testRemovePagamento() {
        Pagamento pagamento = new Pagamento(TipoPagamento.Credito, 50.0, 1);
        pagamento.setPagamentoID(1);
        pagamentoDAO.cria(TipoPagamento.Pix, 100.0, 2);
        pagamentoDAO.cria(TipoPagamento.Dinheiro, 150.0, 3);

        boolean resultado = pagamentoDAO.remove(1);

        Assert.assertTrue(resultado);
        Assert.assertNull(pagamentoDAO.pegaPorId(1));
    }
}
