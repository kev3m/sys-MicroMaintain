package sysmicromaintain.model;

import micromaintainsys.model.OrdemCompra;
import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;
import static org.junit.Assert.*;

public class OrdemCompraTest {

    private OrdemCompra ordemCompra;

    @Before
    public void setUp(){
        ordemCompra = new OrdemCompra("HD", 2,90.00);
    }

    @Test
    public void testGetValorUnitario() {
        assertEquals(90.00, ordemCompra.getValorUnitario(), 0.0);
    }

    @Test
    public void testSetValorUnitario() {
        ordemCompra.setValorUnitario(125.00);
        assertEquals(125.00, ordemCompra.getValorUnitario(), 0.0);
    }

    @Test
    public void testGetPeca() {
        assertEquals("HD", ordemCompra.getPeca());
    }

    @Test
    public void testSetPeca() {
        ordemCompra.setPeca("SSD");
        assertEquals("SSD", ordemCompra.getPeca());
    }

    @Test
    public void testGetQuantidade() {
        assertEquals(2, ordemCompra.getQuantidade());
    }

    @Test
    public void testSetQuantidade() {
        ordemCompra.setQuantidade(4);
        assertEquals(4, ordemCompra.getQuantidade());
    }

    @Test
    public void testGetDataCriacao() {
        Calendar horarioEsperado = Calendar.getInstance();
        assertEquals(horarioEsperado.getTime().toString(), ordemCompra.getDataCriacao().getTime().toString());
    }

    @Test
    public void testSetDataCriacao() {
        Calendar novaData = Calendar.getInstance();
        novaData.set(2023, 3, 8);
        ordemCompra.setDataCriacao(novaData);
        assertEquals(novaData.getTime().toString(), ordemCompra.getDataCriacao().getTime().toString());
    }
}