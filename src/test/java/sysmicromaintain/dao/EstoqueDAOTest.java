package sysmicromaintain.dao;

import micromaintainsys.dao.estoque.EstoqueDAO;
import micromaintainsys.model.Estoque;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EstoqueDAOTest {
    private EstoqueDAO estoqueDAO;
    private Estoque estoque;
    @Before
  public void setUp(){
        estoqueDAO = new EstoqueDAO();
    }

    @Test
    public void testCarregaEstoque(){
        estoque = estoqueDAO.carrega();
        Assert.assertNotNull(estoque);
    }
}
