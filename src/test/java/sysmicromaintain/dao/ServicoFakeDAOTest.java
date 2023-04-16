package sysmicromaintain.dao;
import micromaintainsys.dao.servico.ServicoFakeDAO;
import micromaintainsys.model.Servico;
import micromaintainsys.model.CategoriaServico;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;

public class ServicoFakeDAOTest {
    private ServicoFakeDAO servicoFakeDAO;
    private Servico servico1, servico2;
    private int ordemID1, ordemID2;

    @Before
    public void setUp() {
        servicoFakeDAO = new ServicoFakeDAO();
        ordemID1 = 1;
        ordemID2 = 2;
        servico1 = servicoFakeDAO.cria(CategoriaServico.Formatacao, 100.00, null, "Formatação e atualização de OS", ordemID1);
        servico2 = servicoFakeDAO.cria(CategoriaServico.Montagem, 200.00, "Placa-Mãe", "Montagem de mobo", ordemID2);
        servicoFakeDAO.resetIDCounter();
    }

    @Test
    public void testCria() {
        assertNotNull(servico1);
        assertNotNull(servico2);

        assertEquals(CategoriaServico.Formatacao, servico1.getCategoriaServico());
        assertEquals(CategoriaServico.Montagem, servico2.getCategoriaServico());

        assertEquals(100.00, servico1.getValor(), 0.0);
        assertEquals(200.00, servico2.getValor(), 0.0);

        assertNull(servico1.getPeca());
        assertEquals("Placa-Mãe",servico2.getPeca());

        assertEquals("Formatação e atualização de OS", servico1.getDescricao());
        assertEquals(ordemID1, servico1.getOrdemID());
        assertEquals("Montagem de mobo", servico2.getDescricao());
        assertEquals(ordemID2, servico2.getOrdemID());

    }
    @Test
    public void testPegaPorId() {
        Servico servico = servicoFakeDAO.pegaPorId(servico1.getServicoID());
        assertNotNull(servico);
        assertEquals(servico1, servico);
    }

    @Test
    public void testAtualiza() {
        boolean resultado = servicoFakeDAO.atualiza(servico2);
        assertTrue(resultado);
    }

    @Test
    public void testRemove() {
        boolean resultado = servicoFakeDAO.remove(servico1.getServicoID());
        assertTrue(resultado);
        assertNull(servicoFakeDAO.pegaPorId(servico1.getServicoID()));
    }

    @Test
    public void testPegaTodosPorOrdemID() {
        ArrayList<Servico> servicos = servicoFakeDAO.pegaTodosPorOrdemID(ordemID1);
        assertEquals(1, servicos.size());
        assertTrue(servicos.contains(servico1));
    }

    @Test
    public void testPegaTodosPorDataCriacao() {
        Calendar inicio = Calendar.getInstance();
        inicio.set(2023, Calendar.JANUARY, 1);
        Calendar fim = Calendar.getInstance();
        fim.set(2023, Calendar.DECEMBER, 31);
        ArrayList<Servico> servicos = servicoFakeDAO.pegaTodosPorDataCriacao(inicio, fim);
        assertEquals(2, servicos.size());
        assertTrue(servicos.contains(servico1));
        assertTrue(servicos.contains(servico2));
    }
}
