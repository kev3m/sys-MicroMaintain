package sysmicromaintain.model;
import micromaintainsys.model.Peca;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class PecaTest {
    private Peca peca;

    @Before
    public void setUp(){
        peca = new Peca("Placa de Vídeo", 500.00);
    }


    @Test
    public void testSetNome() {
        peca.setNome("Placa de Vídeo");
        assertEquals("Placa de Vídeo", peca.getNome());
    }

    @Test
    public void testSetValor() {
        peca.setValor(200.0);
        assertEquals(200.0, peca.getValor(),0.0);
    }

    @Test
    public void testGetNome() {
        assertEquals("Placa de Vídeo", peca.getNome());
    }

    @Test
    public void testGetValor() {
        assertEquals(500.00, peca.getValor(),0.0);
    }

}
