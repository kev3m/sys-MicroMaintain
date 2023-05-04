package sysmicromaintain.dao;
import micromaintainsys.dao.cliente.ClienteDAO;
import micromaintainsys.model.Cliente;
import micromaintainsys.utils.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;


import static org.junit.Assert.*;

public class ClienteDAOTest {
    private static final String FILE_NAME = "clientes.bin";

    private ClienteDAO clienteDAO;

    @Before
    public void setup() {
        clienteDAO = new ClienteDAO();
        clienteDAO.esvaziarClientesCadastrados();
    }

    @After
    public void tearDown() {
        clienteDAO.esvaziarClientesCadastrados();
    }

    @Test
    public void criaNovoCliente() {
        Cliente cliente = clienteDAO.cria("João", "Rua A", "1111-1111");
        assertEquals("João", cliente.getName());
        assertEquals("Rua A", cliente.getEndereco());
        assertEquals("1111-1111", cliente.getTelefone());
    }

    @Test
    public void pegaPorId() {
        Cliente cliente1 = clienteDAO.cria("João", "Rua A", "1111-1111");
        Cliente cliente2 = clienteDAO.cria("Maria", "Rua B", "2222-2222");
        Cliente cliente3 = clienteDAO.cria("Pedro", "Rua C", "3333-3333");
        assertEquals(cliente1, clienteDAO.pegaPorId(cliente1.getId()));
        assertEquals(cliente2, clienteDAO.pegaPorId(cliente2.getId()));
        assertEquals(cliente3, clienteDAO.pegaPorId(cliente3.getId()));
        assertNull(clienteDAO.pegaPorId(-1));
    }

    @Test
    public void atualizaCliente() {
        Cliente cliente = clienteDAO.cria("João", "Rua A", "1111-1111");
        cliente.setNome("Maria");
        cliente.setEndereco("Rua B");
        cliente.setTelefone("2222-2222");
        assertTrue(clienteDAO.atualiza(cliente));
        assertEquals(cliente, clienteDAO.pegaPorId(cliente.getId()));
    }

    @Test
    public void removeCliente() {
        Cliente cliente = clienteDAO.cria("João", "Rua A", "1111-1111");
        assertTrue(clienteDAO.remove(cliente.getId()));
        assertNull(clienteDAO.pegaPorId(cliente.getId()));
        assertFalse(clienteDAO.remove(-1));
    }

    @Test
    public void pegaTodosClientes() {
        Cliente cliente1 = clienteDAO.cria("João", "Rua A", "1111-1111");
        Cliente cliente2 = clienteDAO.cria("Maria", "Rua B", "2222-2222");
        Cliente cliente3 = clienteDAO.cria("Pedro", "Rua C", "3333-3333");
        ArrayList<Cliente> clientes = clienteDAO.pegaTodos();
        assertEquals(3, clientes.size());
        assertTrue(clientes.contains(cliente1));
        assertTrue(clientes.contains(cliente2));
        assertTrue(clientes.contains(cliente3));
    }



}
