package sysmicromaintain.dao;

import micromaintainsys.dao.estoque.EstoqueDAO;
import micromaintainsys.model.Estoque;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EstoqueDAOTest {
    private EstoqueDAO estoqueDAO;

    @Before
    public void setUp() {
        estoqueDAO = new EstoqueDAO();
    }

    @Test
    public void testCriaEstoque() {
        Estoque estoque = estoqueDAO.carrega();

        Assert.assertNotNull(estoque);
        Assert.assertNotNull(estoque.getPecas());
        Assert.assertNotNull(estoque.verificaOrdensCompra());
    }

}
