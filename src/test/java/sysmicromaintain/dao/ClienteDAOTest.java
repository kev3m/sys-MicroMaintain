package sysmicromaintain.dao;
import micromaintainsys.dao.cliente.ClienteDAO;
import micromaintainsys.model.Cliente;
import micromaintainsys.utils.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Hashtable;



import static org.junit.Assert.*;

public class ClienteDAOTest {
    private ClienteDAO dao;
    private static final String FILE_PATH = ("src/resources/data/clientes.bin");
    private Hashtable<Integer, Cliente> clientesCadastrados;
    private Cliente cliente,cliente_2, cliente_3, cliente_4,cliente_5;
    @Before
    public void setUp() {
        dao = new ClienteDAO();
        clientesCadastrados = new Hashtable<Integer, Cliente>();
        cliente = dao.cria("Pedro", "Rua Principal, 123", "(55) 8888-8888");
        cliente_2 = dao.cria("João", "Rua Secundaria, 456", "(75) 9999-9999");
        cliente_3 = dao.cria("Maria", "Rua Tercearia, 789", "(85) 7777-7777");
        cliente_4 = dao.cria("Ana", "Rua Quaternaria, 101", "(95) 6666-6666");
        FileUtils.carregaDados(FILE_PATH);
        dao.resetIDCounter();


    }
    @Test
    public void testSalvarCarregarDados() {

        // Salvando os dados no arquivo
        FileUtils.salvaDados(clientesCadastrados, FILE_PATH);

        File file = new File(FILE_PATH);
        assertTrue(file.exists());

        // Resetando os dados do DAO para simular uma nova execução do sistema
        dao.resetIDCounter();
        dao.esvaziarClientesCadastrados();

        // Carregando os dados do arquivo
        FileUtils.carregaDados(FILE_PATH);

        // Verificação dos resultados esperados usando asserções do JUnit
        Cliente clienteRecuperado1 = dao.pegaPorId(cliente.getId());
        Cliente clienteRecuperado2 = dao.pegaPorId(cliente_2.getId());

        assertNotNull("Cliente recuperado 1 é nulo", clienteRecuperado1);
        assertNotNull("Cliente recuperado 2 é nulo", clienteRecuperado2);
        assertEquals("Nome do cliente recuperado 1 não é igual", cliente.getName(), clienteRecuperado1.getName());
        assertEquals("Endereço do cliente recuperado 1 não é igual", cliente.getEndereco(), clienteRecuperado1.getEndereco());
        assertEquals("Telefone do cliente recuperado 1 não é igual", cliente.getTelefone(), clienteRecuperado1.getTelefone());

        assertEquals("Nome do cliente recuperado 2 não é igual", cliente_2.getName(), clienteRecuperado2.getName());
        assertEquals("Endereço do cliente recuperado 2 não é igual", cliente_2.getEndereco(), clienteRecuperado2.getEndereco());
        assertEquals("Telefone do cliente recuperado 2 não é igual", cliente_2.getTelefone(), clienteRecuperado2.getTelefone());
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

        Cliente clienteNaoEncontrado = dao.pegaPorId(999);
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
