package sysmicromaintain.dao;
import micromaintainsys.dao.fatura.FaturaFakeDAO;
import micromaintainsys.model.Fatura;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class FaturaFakeDAOTest {
    private FaturaFakeDAO faturaFakeDAO;
    private Fatura fatura;

    @Before
    public void setUp() {
        faturaFakeDAO = new FaturaFakeDAO();
        fatura = faturaFakeDAO.cria(1, 100.00);
        faturaFakeDAO.resetIDCounter();
    }

    @Test
    public void testCriaFatura() {
        Assert.assertNotNull(fatura);
        Assert.assertEquals(1, fatura.getOrdemID());
        Assert.assertEquals(100.00, fatura.getValorTotal(), 0.0);
    }

    @Test
    public void testPegaPorId() {
        Fatura faturaEncontrada = faturaFakeDAO.pegaPorId(fatura.getFaturaID());

        Assert.assertNotNull(faturaEncontrada);
        Assert.assertEquals(fatura.getFaturaID(), faturaEncontrada.getFaturaID());
        Assert.assertEquals(fatura.getOrdemID(), faturaEncontrada.getOrdemID());
        Assert.assertEquals(fatura.getValorTotal(), faturaEncontrada.getValorTotal(), 0.001);
    }

    @Test
    public void testAtualizaFatura() {
        boolean resultado = faturaFakeDAO.atualiza(fatura);

        Assert.assertTrue(resultado);
        //testando retorno booleano
    }

    @Test
    public void testRemoveFatura() {
        boolean resultado = faturaFakeDAO.remove(fatura.getFaturaID());

        Assert.assertTrue(resultado);
        Assert.assertNull(faturaFakeDAO.pegaPorId(fatura.getFaturaID()));
    }

    @Test
    public void testPegaTodas() {
        int ordemID1 = 1;
        double valorTotal1 = 100.0;
        Fatura fatura1 = faturaFakeDAO.cria(ordemID1, valorTotal1);

        int ordemID2 = 2;
        double valorTotal2 = 200.0;
        Fatura fatura2 = faturaFakeDAO.cria(ordemID2, valorTotal2);

        ArrayList<Fatura> todasAsFaturas = faturaFakeDAO.pegaTodas();

        Assert.assertEquals(2, todasAsFaturas.size());
        Assert.assertTrue(todasAsFaturas.contains(fatura1));
        Assert.assertTrue(todasAsFaturas.contains(fatura2));
    }
}
