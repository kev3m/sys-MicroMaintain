package sysmicromaintain.model;

import micromaintainsys.model.Ordem;
import micromaintainsys.model.OrdemCompra;
import micromaintainsys.model.StatusOrdem;
import micromaintainsys.model.Tecnico;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class TecnicoTest {
    private Tecnico tecnico;
    private Ordem ordem;
    private Ordem ordem_2;


    @Before
    public void setUp() {
        tecnico = new Tecnico("Matheus T", "1234");
        ordem = new Ordem(1);
        ordem_2 = new Ordem(1);
    }

    @Test
    public void testSetarAdm() {
        tecnico.setAdm(true);
        assertTrue(tecnico.isAdm());
        tecnico.setAdm(false);
        assertFalse(tecnico.isAdm());
    }
    @Test
    public void testSetarNome() {
        tecnico.setNome("Paulo T");
        assertEquals("Paulo T", tecnico.getNome());
    }

    @Test
    public void testSetarSenha() {
        tecnico.setSenha("5678");
        assertEquals("5678", tecnico.getSenha());
    }

    @Test
    public void testSetarTecnicoID() {
        tecnico.setTecnicoID(1);
        assertEquals(1, tecnico.getTecnicoID());
    }

    @Test
    public void testIsAdm() {
        assertFalse(tecnico.isAdm());
        tecnico.setAdm(true);
        assertTrue(tecnico.isAdm());
    }

    @Test
    public void testGetNome() {
        assertEquals("Matheus T", tecnico.getNome());
    }

    @Test
    public void testGetSenha() {
        assertEquals("1234", tecnico.getSenha());
    }

    @Test
    public void testGetTecnicoID() {
        assertEquals(0, tecnico.getTecnicoID());
        tecnico.setTecnicoID(1);
        assertEquals(1, tecnico.getTecnicoID());
    }
}
