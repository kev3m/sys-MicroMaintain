package sysmicromaintain.model;
import static org.junit.Assert.*;

import micromaintainsys.model.CategoriaServico;
import micromaintainsys.model.Servico;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class ServicoTest {
    private Servico servico;

    @Before
    public void setUp(){
        servico = new Servico(CategoriaServico.Montagem, 70.0,"Placa Mãe","Parafusar placa mãe no gabinete",1);
    }

    @Test
    public void testGetValor() {
        assertEquals(70.0, servico.getValor(), 0.0);
    }

    @Test
    public void testGetHorarioAbertura() {
        assertNotNull(servico.getHorarioAbertura());
    }

    @Test
    public void testGetHorarioFinalizacao() {
        assertNull(servico.getHorarioFinalizacao());
    }

    @Test
    public void testAvaliaServico() {
        servico.avaliaServico(6.5);
        assertTrue(servico.foiAvaliado());
        assertEquals(6.5, servico.getAvaliacaoCliente(), 0.0);
    }

    @Test
    public void testGetOrdemID() {
        assertEquals(1, servico.getOrdemID());
    }

    @Test
    public void testGetPeca() {
        assertEquals("Placa Mãe", servico.getPeca());
    }

    @Test
    public void testGetDescricao() {
        assertEquals("Parafusar placa mãe no gabinete", servico.getDescricao());
    }

    @Test
    public void testEncerraServico() {
        assertNull(servico.getHorarioFinalizacao());
        servico.encerraServico();
        assertNotNull(servico.getHorarioFinalizacao());
    }




}
