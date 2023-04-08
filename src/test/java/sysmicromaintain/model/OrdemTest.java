package sysmicromaintain.model;
import micromaintainsys.model.Ordem;
import micromaintainsys.model.Servico;
import micromaintainsys.model.StatusOrdem;
import micromaintainsys.model.CategoriaServico;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;


public class OrdemTest {
    private Ordem ordem;
    private ArrayList<Servico> servicos;

    @Before
    public void setUp(){
        Ordem ordem = new Ordem(1);
    }
    @Test
    public void testGetClienteID() {
        Ordem ordem = new Ordem(1);
        assertEquals(1, ordem.getClienteID());
    }

    @Test
    public void testSetClienteID() {
        Ordem ordem = new Ordem(1);
        ordem.setClienteID(2);
        assertEquals(2, ordem.getClienteID());
    }

    @Test
    public void testGetTecnicoID() {
        Ordem ordem = new Ordem(1);
        ordem.setTecnicoID(3);
        assertEquals(3, ordem.getTecnicoID());
    }

    @Test
    public void testGetStatus() {
        Ordem ordem = new Ordem(1);
        assertEquals(StatusOrdem.Aberta, ordem.getStatus());
    }

    @Test
    public void testSetStatus() {
        Ordem ordem = new Ordem(1);
        ordem.setStatus(StatusOrdem.Finalizada);
        assertEquals(StatusOrdem.Finalizada, ordem.getStatus());
    }

    @Test
    public void testGetServicos() {
        Ordem ordem = new Ordem(1);
        Servico servico1 = new Servico(CategoriaServico.Montagem, 50.0, "Placa Mãe","Montar Componentes",1);
        Servico servico2 = new Servico(CategoriaServico.Limpeza, 70.0, "Computador","Limpar, só",1);
        ArrayList<Servico> servicos = new ArrayList<>();
        ordem.setServicos(servico1);
        ordem.setServicos(servico2);
        servicos.add(servico1);
        servicos.add(servico2);

        assertEquals(servicos, ordem.getServicos());
    }
}