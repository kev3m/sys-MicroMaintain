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

        private OrdemDAO ordemDao;
        private ArrayList<Ordem> ordens = new ArrayList<>();
        private Ordem ordem;



        @Before
        public void setUp() {
            ordemDao = new OrdemDAO();
            ordem = ordemDao.cria(1);
            ordemDao.resetIDCounter();
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
            Ordem ordemRecuperada = ordemDao.pegaPorId(ordem.getOrdemID());
            assertEquals(ordem, ordemRecuperada);
        }

        @Test
        public void testAtualiza() {
            boolean atualizacao = ordemDao.atualiza(ordem);
            assertTrue(atualizacao);
        }

        @Test
        public void testRemove() {
            boolean remocao = ordemDao.remove(ordem.getOrdemID());
            assertTrue(remocao);
            Ordem ordemRemovida = ordemDao.pegaPorId(ordem.getOrdemID());
            assertNull(ordemRemovida);
        }


        //Pagamento, Aberta, Finalizada, Cancelada, Andamento
        @Test
        public void testPegaTodasPorStatus() {
            int emAndamento = ordemDao.pegaTodasPorStatus(StatusOrdem.Andamento).size();
            int finalizadas = ordemDao.pegaTodasPorStatus(StatusOrdem.Finalizada).size();
            Ordem ordem2 = ordemDao.cria(1);
            Ordem ordem3 = ordemDao.cria(2);
            Ordem ordem4 = ordemDao.cria(2);
            ordem2.setStatus(StatusOrdem.Andamento);
            ordem3.setStatus(StatusOrdem.Finalizada);
            ordem4.setStatus(StatusOrdem.Andamento);


            ArrayList<Ordem> ordensEmAndamento = ordemDao.pegaTodasPorStatus(StatusOrdem.Andamento);
            assertEquals(emAndamento + 2, ordensEmAndamento.size());

            ArrayList<Ordem> ordensFinalizadas = ordemDao.pegaTodasPorStatus(StatusOrdem.Finalizada);
            assertEquals(finalizadas + 1, ordensFinalizadas.size());

            ArrayList<Ordem> ordensCanceladas = ordemDao.pegaTodasPorStatus(StatusOrdem.Cancelada);
            assertEquals(0, ordensCanceladas.size());
        }

        /*
        @Test
        public void testPegaTodas() {
            ordens = ordemDao.pegaTodas();
            assertEquals(1, ordens.size());
        }
        */

}
