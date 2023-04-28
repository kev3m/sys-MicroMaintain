package sysmicromaintain.dao;
import static org.junit.Assert.*;

import micromaintainsys.dao.tecnico.TecnicoDAO;
import micromaintainsys.model.Tecnico;
import org.junit.Before;
import org.junit.Test;
public class TecnicoDAOTest {
    private TecnicoDAO tecnicoDAO;
    private Tecnico tecnico;

    @Before
    public void setUp() {
        tecnicoDAO = new TecnicoDAO();
        tecnico = tecnicoDAO.cria("Paulo Tec", "123");
        tecnicoDAO.resetIDCounter();

    }

    @Test
    public void testPegaPorIdExistente() {
        Tecnico tecnico = tecnicoDAO.pegaPorId(0);
        assertEquals("admin", tecnico.getNome());
        assertEquals("admin", tecnico.getSenha());
        assertTrue(tecnico.isAdm());
    }

    @Test
    public void testPegaPorIdNaoExistente() {
        tecnico = tecnicoDAO.pegaPorId(999);
        assertNull(tecnico);
    }

    @Test
    public void testCria() {
        assertEquals(1, tecnico.getTecnicoID());
        assertEquals("Paulo Tec", tecnico.getNome());
        assertEquals("123", tecnico.getSenha());
        assertFalse(tecnico.isAdm());
    }

    @Test
    public void testAutenticaSucesso() {
        boolean resultado = tecnicoDAO.autentica(0, "admin");
        assertTrue(resultado);
    }

    @Test
    public void testAutenticaFalhaSenha() {
        boolean resultado = tecnicoDAO.autentica(0, "senha_errada");
        assertFalse(resultado);
    }

    @Test
    public void testAutenticaFalhaId() {
        boolean resultado = tecnicoDAO.autentica(999, "admin");
        assertFalse(resultado);
    }

    @Test
    public void testRemoveExistente() {
        boolean resultado = tecnicoDAO.remove(0);
        assertTrue(resultado);
        assertNull(tecnicoDAO.pegaPorId(0));
    }

    @Test
    public void testRemoveNaoExistente() {
        boolean resultado = tecnicoDAO.remove(999);
        assertFalse(resultado);
    }

    @Test
    public void testAtualiza() {
        tecnico.setNome("Paulo Tecnico");
        boolean resultado = tecnicoDAO.atualiza(tecnico);
        assertTrue(resultado);
        Tecnico tecnicoAtualizado = tecnicoDAO.pegaPorId(1);
        assertEquals("Paulo Tecnico", tecnicoAtualizado.getNome());
    }

}
