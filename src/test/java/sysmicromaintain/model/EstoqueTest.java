package sysmicromaintain.model;
import micromaintainsys.model.Estoque;
import micromaintainsys.model.OrdemCompra;
import micromaintainsys.model.RelatorioCompras;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.*;


public class EstoqueTest {
    private Estoque estoque;
    private OrdemCompra ordemCompra;
    private ArrayList<OrdemCompra> ordensCompra;

    
    @Before
    public void setUp(){
        estoque = new Estoque();
        ordemCompra = new OrdemCompra("SSD", 5,90.00);
    }

    @Test
    public void testAdicionaPeca() {
        estoque.adicionaPeca("Placa de Vídeo", 7);
        estoque.adicionaPeca("Fonte", 5);
        assertEquals(7, estoque.pegaEstoqueDePeca("Placa de Vídeo"));
        assertEquals(5, estoque.pegaEstoqueDePeca("Fonte"));
    }

    @Test
    public void testRemovePeca() {
        estoque.adicionaPeca("Placa-Mãe", 10);
        estoque.removePeca("Placa-Mãe", 5);
        assertEquals(5, estoque.pegaEstoqueDePeca("Placa-Mãe"));
        estoque.removePeca("Placa-Mãe", 10);
        assertEquals(0, estoque.pegaEstoqueDePeca("Placa-Mãe"));
    }

    @Test
    public void testDeletaPeca() {
        estoque.adicionaPeca("HD", 4);
        estoque.deletaPeca("HD");
        assertEquals(0,estoque.pegaEstoqueDePeca("HD"));
    }

    @Test
    public void testCriaOrdemCompra() {
        estoque.criaOrdemCompra(ordemCompra);
        ordensCompra = estoque.verificaOrdensCompra();
        assertEquals(1, ordensCompra.size());
        assertEquals(ordemCompra, ordensCompra.get(0));
    }

    @Test
    public void testRemoveOrdemCompra() {
        estoque.criaOrdemCompra(ordemCompra);
        estoque.removeOrdemCompra(ordemCompra);
        ordensCompra = estoque.verificaOrdensCompra();
        assertEquals(0, ordensCompra.size());
    }

    @Test
    public void testVerificaOrdensCompra() {
        OrdemCompra ordemCompra_2 = new OrdemCompra("RAM", 5,90.00);
        estoque.criaOrdemCompra(ordemCompra);
        estoque.criaOrdemCompra(ordemCompra_2);
        ordensCompra = estoque.verificaOrdensCompra();
        assertEquals(2, ordensCompra.size());
        assertTrue(ordensCompra.contains(ordemCompra));
        assertTrue(ordensCompra.contains(ordemCompra_2));
    }


    //CORRIGIR GERAÇÃO DE RELATÓRIO
    @Test
    public void testGeraRelatorioCompras() {
        OrdemCompra ordemCompra1 = new OrdemCompra("HD", 10,90.00);
        OrdemCompra ordemCompra2 = new OrdemCompra("RAM", 5,120.00);
        OrdemCompra ordemCompra3 = new OrdemCompra("CPU", 2,400.00);

        Calendar inicio = Calendar.getInstance();
        Calendar fim = Calendar.getInstance();
        fim.add(Calendar.MONTH, 1);

        estoque.criaOrdemCompra(ordemCompra1);
        estoque.criaOrdemCompra(ordemCompra2);
        estoque.criaOrdemCompra(ordemCompra3);

        RelatorioCompras relatorio = estoque.geraRelatorioCompras(inicio, fim);
        assertEquals(3, relatorio.getOrdens().size());

        double totalCompra = ordemCompra1.getValorCompra() + ordemCompra2.getValorCompra() + ordemCompra3.getValorCompra();

        assertEquals(totalCompra, relatorio.getValorTotal(), 0);
    }


}
