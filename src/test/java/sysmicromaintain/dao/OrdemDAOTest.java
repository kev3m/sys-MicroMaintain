package sysmicromaintain.dao;
import java.util.ArrayList;

import micromaintainsys.dao.DAO;
import micromaintainsys.dao.ordem.OrdemDAO;
import micromaintainsys.model.Ordem;
import micromaintainsys.model.StatusOrdem;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrdemDAOTest {

        private OrdemDAO ordemFakeDAO;
        private ArrayList<Ordem> ordens = new ArrayList<>();
        private Ordem ordem;



        @Before
        public void setUp() {
            ordemFakeDAO = new OrdemDAO();
            ordem = ordemFakeDAO.cria(1);
            ordemFakeDAO.resetIDCounter();
        }

        @Test
        public void testCria() {
            assertNotNull(ordem);
            int ordens = DAO.getOrdemDAO().pegaTodas().size();
            assertEquals(ordens-1, ordem.getOrdemID());
            assertEquals(1, ordem.getClienteID());
        }

        @Test
        public void testPegaPorId() {
            Ordem ordemRecuperada = ordemFakeDAO.pegaPorId(ordem.getOrdemID());
            assertEquals(ordem, ordemRecuperada);
        }

        @Test
        public void testAtualiza() {
            boolean atualizacao = ordemFakeDAO.atualiza(ordem);
            assertTrue(atualizacao);
        }

        @Test
        public void testRemove() {
            boolean remocao = ordemFakeDAO.remove(ordem.getOrdemID());
            assertTrue(remocao);
            Ordem ordemRemovida = ordemFakeDAO.pegaPorId(ordem.getOrdemID());
            assertNull(ordemRemovida);
        }


        //Pagamento, Aberta, Finalizada, Cancelada, Andamento
        @Test
        public void testPegaTodasPorStatus() {
            int emAndamento = ordemFakeDAO.pegaTodasPorStatus(StatusOrdem.Andamento).size();
            int finalizadas = ordemFakeDAO.pegaTodasPorStatus(StatusOrdem.Finalizada).size();
            Ordem ordem2 = ordemFakeDAO.cria(1);
            Ordem ordem3 = ordemFakeDAO.cria(2);
            Ordem ordem4 = ordemFakeDAO.cria(2);
            ordem2.setStatus(StatusOrdem.Andamento);
            ordem3.setStatus(StatusOrdem.Finalizada);
            ordem4.setStatus(StatusOrdem.Andamento);


            ArrayList<Ordem> ordensEmAndamento = ordemFakeDAO.pegaTodasPorStatus(StatusOrdem.Andamento);
            assertEquals(emAndamento + 2, ordensEmAndamento.size());

            ArrayList<Ordem> ordensFinalizadas = ordemFakeDAO.pegaTodasPorStatus(StatusOrdem.Finalizada);
            assertEquals(finalizadas + 1, ordensFinalizadas.size());

            ArrayList<Ordem> ordensCanceladas = ordemFakeDAO.pegaTodasPorStatus(StatusOrdem.Cancelada);
            assertEquals(0, ordensCanceladas.size());
        }

        /*
        @Test
        public void testPegaTodas() {
            ordens = ordemFakeDAO.pegaTodas();
            assertEquals(1, ordens.size());
        }
        */

}
