package sysmicromaintain.model;
import static org.junit.Assert.*;

import micromaintainsys.model.Ordem;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import micromaintainsys.model.Cliente;

public class ClienteTest {
    private Cliente cliente;
    private Ordem ordem, ordem_2;

    @Before
    public void setUp(){
        cliente = new Cliente("Marcos", "Rua Principal, 124","75991148155" );
        ordem = new Ordem(1);
        ordem_2 = new Ordem(1);
    }

    @Test
    public void testCriarCliente() {
        assertEquals("Marcos", cliente.getName());
        assertEquals("Rua Principal, 124", cliente.getEndereco());
        assertEquals("75991148155", cliente.getTelefone());
    }

    @Test
    public void testAlterarCliente() {
        cliente.setNome("Pedro");
        cliente.setEndereco("Rua Quatro, 421");
        cliente.setTelefone("75992259266");
        assertEquals("Pedro", cliente.getName());
        assertEquals("Rua Quatro, 421", cliente.getEndereco());
        assertEquals("75992259266", cliente.getTelefone());
    }

    @Test
    public void testSetarClienteId() {
        cliente.setClienteID(10);
        assertEquals(10, cliente.getId());
    }
    @Test
    public void testAtribuirOrdem() {
        cliente.setOrdem(ordem);
        ArrayList<Ordem> ordens = cliente.getOrdens();
        assertTrue(ordens.contains(ordem));
    }
    @Test
    public void testAtribuirVariasOrdens() {
        cliente.setOrdem(ordem);
        cliente.setOrdem(ordem_2);
        ArrayList<Ordem> ordens = cliente.getOrdens();
        assertTrue(ordens.contains(ordem));
        assertTrue(ordens.contains(ordem_2));
        assertEquals(ordem, ordens.get(0));
        assertEquals(ordem_2, ordens.get(1));
    }

    @Test
    public void testGetOrdensSemOrdens() {
        ArrayList<Ordem> ordens = cliente.getOrdens();
        assertEquals(0, ordens.size());
    }


}
