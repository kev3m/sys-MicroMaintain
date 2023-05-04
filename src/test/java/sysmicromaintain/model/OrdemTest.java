package sysmicromaintain.model;
import micromaintainsys.model.Ordem;
import micromaintainsys.model.Servico;
import micromaintainsys.model.StatusOrdem;
import micromaintainsys.model.CategoriaServico;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;


public class OrdemTest {
    private Ordem ordem;
    private ArrayList<Servico> servicos;

    @Before
    public void setUp(){
        Ordem ordem = new Ordem(1);
    }
    @Test
    public void testGetClienteID() {
        Ordem ordem = new Ordem(1);
        assertEquals(1, ordem.getClienteID());
    }

    @Test
    public void testSetClienteID() {
        Ordem ordem = new Ordem(1);
        ordem.setClienteID(2);
        assertEquals(2, ordem.getClienteID());
    }

    @Test
    public void testGetTecnicoID() {
        Ordem ordem = new Ordem(1);
        ordem.setTecnicoID(3);
        assertEquals(3, ordem.getTecnicoID());
    }

    @Test
    public void testGetStatus() {
        Ordem ordem = new Ordem(1);
        assertEquals(StatusOrdem.Aberta, ordem.getStatus());
    }

    @Test
    public void testSetStatus() {
        Ordem ordem = new Ordem(1);
        ordem.setStatus(StatusOrdem.Finalizada);
        assertEquals(StatusOrdem.Finalizada, ordem.getStatus());
    }
}