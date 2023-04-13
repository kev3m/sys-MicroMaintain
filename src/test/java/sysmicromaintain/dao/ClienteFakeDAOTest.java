package sysmicromaintain.dao;
import micromaintainsys.dao.DAO;
import micromaintainsys.dao.cliente.ClienteFakeDAO;
import micromaintainsys.model.Cliente;

import java.util.Hashtable;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteFakeDAOTest {
    private ClienteFakeDAO dao;
    private Hashtable<Integer, Cliente> clientesCadastrados;
    private Cliente cliente,cliente_2;
    @Before
    public void setUp() {
        dao = new ClienteFakeDAO();
        clientesCadastrados = new Hashtable<Integer, Cliente>();
        cliente = dao.cria("Pedro", "Rua Principal, 123", "(55) 8888-8888");
        cliente_2 = dao.cria("João", "Rua Secundaria, 456", "(75) 9999-9999");

    }

    @Test
    public void testCriaCliente() {
        assertEquals("Pedro", cliente.getName());
        assertEquals("Rua Principal, 123", cliente.getEndereco());
        assertEquals("(55) 8888-8888", cliente.getTelefone());
        assertEquals(0, cliente.getId());

        assertEquals("João", cliente_2.getName());
        assertEquals("Rua Secundaria, 456", cliente_2.getEndereco());
        assertEquals("(75) 9999-9999", cliente_2.getTelefone());
        assertEquals(1, cliente_2.getId());
    }
    @Test
    public void testPegaPorId() {
        clientesCadastrados.put(cliente.getId(), cliente);
        clientesCadastrados.put(cliente_2.getId(), cliente_2);

        Cliente clienteEncontrado = dao.pegaPorId(cliente.getId());
        assertEquals(cliente, clienteEncontrado);

        Cliente cliente_2Encontrado = dao.pegaPorId(cliente_2.getId());
        assertEquals(cliente_2, cliente_2Encontrado);

        Cliente clienteNaoEncontrado = dao.pegaPorId(3);
        assertNull(clienteNaoEncontrado);
    }

    @Test
    public void testRemove() {
        clientesCadastrados.put(cliente.getId(), cliente);
        clientesCadastrados.put(cliente_2.getId(), cliente_2);

        boolean removido = dao.remove(cliente.getId());
        assertTrue(removido);

        //assertFalse(clientesCadastrados.containsKey(cliente.getId())); VERIFICAR

        boolean naoRemovido = dao.remove(999);
        assertFalse(naoRemovido);
    }


}
