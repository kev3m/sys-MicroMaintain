package sysmicromaintain.dao;

import micromaintainsys.dao.estoque.EstoqueFakeDAO;
import micromaintainsys.model.Estoque;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EstoqueFakeDAOTest {
    private EstoqueFakeDAO estoqueFakeDAO;

    @Before
    public void setUp() {
        estoqueFakeDAO = new EstoqueFakeDAO();
    }

    @Test
    public void testCriaEstoque() {
        Estoque estoque = estoqueFakeDAO.cria();

        Assert.assertNotNull(estoque);
        Assert.assertNotNull(estoque.getPecas());
        Assert.assertNotNull(estoque.verificaOrdensCompra());
    }

}
