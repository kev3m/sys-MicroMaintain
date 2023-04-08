package sysmicromaintain.model;
import micromaintainsys.model.Fatura;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class FaturaTest {

    private Fatura fatura;

    @Before
    public void setUp(){
        fatura = new Fatura(1, 100.0);
    }
    @Test
    public void testGetFaturaID() {
        fatura.setFaturaID(10);
        assertEquals(10, fatura.getFaturaID());
    }

    @Test
    public void testGetValorTotal() {
        assertEquals(100.0, fatura.getValorTotal(), 0.001);
    }

    @Test
    public void testSetValorTotal() {
        fatura.setValorTotal(200.0);
        assertEquals(200.0, fatura.getValorTotal(), 0.001);
    }

    @Test
    public void testGetOrdemID() {
        assertEquals(1, fatura.getOrdemID());
    }

    @Test
    public void testSetOrdemID() {
        fatura.setOrdemID(2);
        assertEquals(2, fatura.getOrdemID());
    }

    @Test
    public void testGetValorPago() {
        fatura.setValorPago(50.0);
        assertEquals(50.0, fatura.getValorPago(), 0.001);
    }

    @Test
    public void testSetValorPago() {
        fatura.setValorPago(50.0);
        assertEquals(50.0, fatura.getValorPago(), 0.001);
    }
}
